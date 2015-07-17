package com.voole.epg.view.movies.live;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.navigation.NavigationManager;
import com.voole.epg.view.movies.live.LiveView.LiveViewListener;
import com.voole.epg.view.movies.zy.ZYFilmListActivity;
import com.voole.epg.view.navigation.NavigationItemSelectedListener;
import com.voole.epg.view.navigation.NavigationMovieView;
import com.voole.epg.view.navigation.NavigationProgramView;

public class LiveActivity extends BaseActivity {

	private LogoView logoView = null;
	private NavigationProgramView navigationProgramView = null;
	private NavigationMovieView navigationMovieView = null;
	private List<FilmClass> programList = null;
	private List<FilmClass> filmClass = null;
	private LiveView lifeView;
	private int mIndex = -1;

	private String parentTitle = null;

	private final int GET_DATA_SUCCESS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_movies_live);
		init();
		getData();
	}

	private void getProgramList() {
		showDialog();
		new Thread() {
			public void run() {
				filmClass = NavigationManager.GetInstance().getCategoryListByFilmClass(programList.get(mIndex));

				if (filmClass != null && filmClass.size() > 0) {
					sendMessage(GET_DATA_SUCCESS);
				} else {
					sendMessage(ACCESS_NET_FAIL);
				}

			};
		}.start();
	}

	private void getData() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			programList = (List<FilmClass>) bundle.getSerializable("programList");
			navigationProgramView.setData(programList);
			mIndex = bundle.getInt("index");
			navigationProgramView.setLoseFocusSelectedItemIndex(mIndex);
			navigationProgramView.requestFocus();
			parentTitle = bundle.getString("parentTitle");
			logoView.setChannelName(parentTitle,false);
			getProgramList();
		} else {
			finish();
		}
	}

	private void init() {
		
		logoView = (LogoView) findViewById(R.id.movie_logo);
		navigationProgramView = (NavigationProgramView) findViewById(R.id.movie_program_navigator);
		navigationProgramView.setAlwaysNotifyListener(true);
		navigationProgramView
				.setOnItemSelectedListener(new NavigationItemSelectedListener() {
					@Override
					public void onItemSelected(int index, FilmClass item) {
						mIndex = index;
						if (index == 0) {
							setResult(Activity.RESULT_OK);
							finish();
						}else{
							getProgramList();
						}
					}
				});
		lifeView = (LiveView) findViewById(R.id.life_view);
		lifeView.setLiveViewListener(new LiveViewListener() {

			@Override
			public void onItemSelected(FilmClass item) {
				if(item!=null){
				Bundle bundle = new Bundle();
				bundle.putString("parentTitle", parentTitle);
				bundle.putSerializable("navigation", item);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(LiveActivity.this, ZYFilmListActivity.class);
				startActivity(intent);
				}
			}
		});
		navigationMovieView = (NavigationMovieView)findViewById(R.id.movie_navigation);
	}

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_DATA_SUCCESS:
			lifeView.clearAllData();
			lifeView.setData(filmClass);
			lifeView.setSelectedIndex(0);
			//lifeView.requestFocus();
			cancelDialog();
			break;
		default:
			break;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(navigationMovieView.imageView.getVisibility() != View.VISIBLE){
				lifeView.requestFocus();
				return true;
			}
			break;
		default:
			return super.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

}
