package com.voole.epg.view.movies.topic;

import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.PageIconNavigator;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.movies.movie.MovieView;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.tvutils.ImageManager;

public class TopicFilmListVerActivity extends BaseActivity {
	private LinearLayout mainLayout = null;
	private LogoView logoView = null;
	private PageIconNavigator pageIconNavigator = null;
	private MovieView movieView = null;

	private static final int GET_DATA_SUCCESS = 1;
	private static final int GET_DATA_FAILURE = 2;
   
	private FilmAndPageInfo filmAndPageInfo = null;
//	private FilmClass parentNavItem = null;
	
	private String topicUrl = null;
	private String topicName = null;
	private String topicBigUrl = null;
	
	private List<Film> priceFilmList=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_topic_film_list_ver);
		init();
		getData();
	}

	private void getData() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			/*parentNavItem = (FilmClass) bundle.getSerializable("navigation");
			if (parentNavItem != null) {
				logoView.setChannelName(getString(R.string.topic_zuanti)
						+ parentNavItem.getFilmClassName());
				setBg(parentNavItem.getBigImgUrl());
				getFilmList(1);
			}*/
			topicUrl = bundle.getString("topicUrl");
			topicName = bundle.getString("topicName");
			topicBigUrl = bundle.getString("topicBigUrl");

			if(!TextUtils.isEmpty(topicName)){
				logoView.setChannelName(getString(R.string.topic_zuanti)+" : "+ topicName,false);
			}
			 
			getFilmList(1);
			if(topicBigUrl!=null&&!"".equals(topicBigUrl)){
				setBg(topicBigUrl);
			}
		} else {
			finish();
		}
	}

	private void getFilmList(final int pageNo) {

		showDialog();
		new Thread() {
			@Override
			public void run() {
				
				if(TextUtils.isEmpty(topicUrl)){
					String id=getIntent().getExtras().getString("id");
					LogUtil.d("id--->"+id);
					topicUrl=AuthManager.GetInstance().getUrlList().getPayList()+"&ctype=3&column="+id;
					LogUtil.d("topicUrl--->"+topicUrl);
				} 
				
				filmAndPageInfo = MovieManager.GetInstance().getFilmsOfTopic(
						topicUrl, pageNo, movieView.getITEM_SIZE());
				if (filmAndPageInfo == null) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					if(filmAndPageInfo.getFilmList()!=null&&filmAndPageInfo.getFilmList().size()>0){
						priceFilmList = MovieManager.GetInstance().getMoviesListForPrice(filmAndPageInfo.getFilmList());
						if(priceFilmList != null && priceFilmList.size()>0){
							sendMessage(GET_DATA_SUCCESS);
						}else{
							sendMessage(GET_DATA_FAILURE);
						}
					}else{
						sendMessage(GET_DATA_FAILURE);
					}
				}
			}

		}.start();

	}

	private void init() {
		mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		movieView = (MovieView) findViewById(R.id.movie_list);
		movieView.setNextFocusUpId(ID.LogoView.SEARCH_ID);
		movieView.setDisplayDetail(false);
		movieView.setITEM_SIZE(6);
		movieView.setMovieViewListener(new MovieViewListener() {
			@Override
			public void onPlay(Film item) {

			}

			@Override
			public void onItemSelected(Film item,int index) {
				Bundle bundle = new Bundle();
				bundle.putSerializable("film", item);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(TopicFilmListVerActivity.this,
						MovieDetailActivity.class);
				startActivity(intent);
			}

			@Override
			public void onGotoPage(int pageNo) {
				getFilmList(pageNo);
			}
		});
		logoView = (LogoView) findViewById(R.id.topic_logo);
		logoView.setBackgroundResource(R.drawable.cs_logoview_topic_bg);
		pageIconNavigator = (PageIconNavigator) findViewById(R.id.topic_page);
	}

	private void setBg(String imgUrl) {
		ImageManager.GetInstance().displayImage(imgUrl, mainLayout);
	}

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_DATA_SUCCESS:
			if(topicBigUrl==null||"".equals(topicBigUrl)){
				setBg(filmAndPageInfo.getImageUrl());
			}
			
			if(TextUtils.isEmpty(topicName)){
				topicName = filmAndPageInfo.getName();
				logoView.setChannelName(getString(R.string.topic_zuanti)+" : "+ topicName,false);
			}
			
			movieView.setData(priceFilmList);
			movieView.setPageInfo(filmAndPageInfo.getPageIndex(),
					filmAndPageInfo.getPageCount());
			movieView.requestFocus();
			pageIconNavigator.setPageInfo(filmAndPageInfo.getPageIndex(),
					filmAndPageInfo.getPageCount());
			break;
		case GET_DATA_FAILURE:
			new TVAlertDialog.Builder(TopicFilmListVerActivity.this)
			.setCancelable(false)
			.setTitle("该专题下没有影片")
			.setPositiveButton(R.string.common_ok_button,
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							finish();
						}
					}).create().show();
			break;
		default:
			break;
		}
	}

}
