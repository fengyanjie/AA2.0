package com.voole.epg.cooperation;

import java.io.Serializable;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import com.voole.epg.base.BaseActivity;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.navigation.NavigationManager;
import com.voole.epg.corelib.model.play.IPlay;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.download.FilmListActivity;
import com.voole.epg.model.play.PlayManager;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.movies.live.LiveActivity;
import com.voole.epg.view.movies.movie.MovieActivity;
import com.voole.epg.view.movies.zy.ZYActivity;
import com.voole.epg.view.movies.zy.ZYFilmListActivity;

public class CooperationStartActivity extends BaseActivity {
	private static final int DITAIL_REQUEST_CODE = 0x0020;
	private FilmClass filmClass = null;
	private final int GET_NAVIGATION_SUCCESS = 001;
	private final int GET_NAVIGATION_FAILURE = 002;

	private String subID = "";
	// 栏目下标
//	private int index = -1;

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_NAVIGATION_SUCCESS:
			cancelDialog();
			if (filmClass != null) {
				/**
				 * jumpType 为 1 时 隐藏导航
				 */
				int jumpType = getIntent().getIntExtra("jumpType", -1);
				Bundle bundle = new Bundle();
				bundle.putSerializable("navigation", filmClass);
				bundle.putInt("jumpType", jumpType);
				Intent intent = new Intent();

				if (NavigationManager.ZY.equals(filmClass.getTemplate())) {
					if (subID != null && !"".equals(subID)) {
						gotoNav("zy");
					} else {
						intent.setClass(CooperationStartActivity.this,
								ZYActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
					}
				} else if (NavigationManager.LIFE.equals(filmClass
						.getTemplate())) {
					if (subID != null && !"".equals(subID)) {
						gotoNav("life");
					} else {
						intent.setClass(CooperationStartActivity.this,
								ZYActivity.class);
						intent.putExtras(bundle);
						startActivity(intent);
						finish();
					}
				} else {
					if (subID != null && !"".equals(subID)) {
						bundle.putString("subID", subID);
					}
					intent.setClass(CooperationStartActivity.this,
							MovieActivity.class);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			}
			break;
		case GET_NAVIGATION_FAILURE:
			cancelDialog();
			finish();
			break;
		default:
			break;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogUtil.d("CooperationStartActivity-->onCreate");
		super.onCreate(savedInstanceState);
		getData();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		getData();
	}

	private void getData() {
		initVooleLib();
		String action = getIntent().getAction();
		if ("com.voole.epg.action.Channel".equals(action)) {
			// go to channel
			gotoChannel();
		} else if ("com.voole.epg.action.Detail".equals(action)) {
			final String intentMid = getIntent().getStringExtra("intentMid");
			if (intentMid != null && !intentMid.equals("")) {
				Intent intent = new Intent();
				intent.putExtra("intentMid", intentMid);
				intent.setClass(this, MovieDetailActivity.class);
				startActivityForResult(intent, DITAIL_REQUEST_CODE);
			}
		} else if ("com.voole.epg.F4k".equalsIgnoreCase(action)) {
			Bundle extras = getIntent().getExtras();
			Intent intent = new Intent();
//			intent.setClass(this, F4KListActivity.class);
			intent.setClass(this, FilmListActivity.class);
			if (extras != null) {
				intent.putExtras(extras);
			}
			startActivityForResult(intent, DITAIL_REQUEST_CODE);
		} else if ("com.voole.epg.Aggregate".equals(action)) {
			String isFavorite = "";
			String mid = getIntent().getStringExtra("mid");
			String sid = getIntent().getStringExtra("sid");
			boolean isCollect = getIntent().getBooleanExtra("isCollect", false);
			if (isCollect) {
				isFavorite = "1";
			} else {
				isFavorite = "0";
			}
//			int playTime = getIntent().getExtras().getInt("playTime");
			int playTime = getIntent().getIntExtra("playTime", 0);
			boolean isPlayBack = getIntent().getBooleanExtra("isPlayBack",
					false);
			LogUtil.d("CooperationStartActivity-->getData-->mid-->" + mid
					+ "-->sid-->" + sid + "-->playTime-->" + playTime);
			if (playTime < 0) {
				playTime = 0;
			}
			String hisenseMid = getIntent().getStringExtra("program_id");
			String hisenseSid = getIntent().getStringExtra("episode_id");
			PlayManager.GetInstance().play(this, mid, sid, isFavorite,
					isPlayBack, true, hisenseMid, hisenseSid);
		}else if("com.voole.epg.play.one".equals(action)){
			String mid = getIntent().getStringExtra("mid");
			String sid = getIntent().getStringExtra("sid");
			int playTime = getIntent().getExtras().getInt("playTime");
			LogUtil.d("CooperationStartActivity-->getData-->mid-->" + mid
					+ "-->sid-->" + sid + "-->playTime-->" + playTime);
			if (playTime < 0) {
				playTime = 0;
			}
			PlayManager.GetInstance().play(this, mid, sid);
		} else {
			String mid = getIntent().getStringExtra("mid");
			String sid = getIntent().getStringExtra("sid");
			int playTime = getIntent().getIntExtra("playTime", 0);
//			int playTime = getIntent().getExtras().getInt("playTime");
			LogUtil.d("CooperationStartActivity-->getData-->mid-->" + mid
					+ "-->sid-->" + sid + "-->playTime-->" + playTime);
			if (playTime < 0) {
				playTime = 0;
			}
			PlayManager.GetInstance().play(this, mid, sid);
		}
	}

	private void gotoNav(final String type) {
		new Thread(){
			public void run() {
				final List<FilmClass> programList = NavigationManager.GetInstance().getCategoryListByFilmClass(filmClass);
				if (programList != null && programList.size() > 0) {
					for (int i = 0; i < programList.size(); i++) {
						if (subID.equals(programList.get(i).getChannelCode())) {
//							index=i;
							if(i == 0){
								Bundle bundle = new Bundle();
								bundle.putString("parentTitle", filmClass.getFilmClassName());
								bundle.putSerializable("navigation", filmClass);
								Intent intent = new Intent();
								intent.setClass(CooperationStartActivity.this, ZYActivity.class);
								intent.putExtras(bundle);
								startActivity(intent);
								finish();
							}else{
								if(type.equals("zy")){
									Bundle bundle = new Bundle();
									bundle.putString("parentTitle", filmClass.getFilmClassName());
									bundle.putSerializable("navigation", programList.get(i));
									Intent intent = new Intent();
									intent.putExtras(bundle);
									intent.setClass(CooperationStartActivity.this, ZYFilmListActivity.class);
									startActivity(intent);
									finish();
								}else if(type.equals("life")){
									Bundle bundle = new Bundle();
									bundle.putString("parentTitle", programList.get(i).getFilmClassName());
									bundle.putSerializable("programList", (Serializable) programList);
									bundle.putInt("index", i);
									Intent intent = new Intent();
									intent.putExtras(bundle);
									intent.setClass(CooperationStartActivity.this, LiveActivity.class);
									startActivity(intent);
									finish();
								}
							}
							return ;
						}
					}
				}else{
					sendMessage(GET_NAVIGATION_FAILURE);
				}
			};
		}.start();
	}

	private void gotoChannel() {
		showDialog();
		String launcherParam = getIntent().getStringExtra("launcherParam");
		if(TextUtils.isEmpty(launcherParam)){
			finish();
			return;
		}
		LogUtil.d("gotoChannel-->launcherParam--->" + launcherParam);
		final String channelID = getValueFromUrl("http://127.0.0.1?"
				+ launcherParam, "id");
		if(TextUtils.isEmpty(channelID)){
			finish();
			return;
		}
		subID = getValueFromUrl("http://127.0.0.1?" + launcherParam, "subID");
		LogUtil.d("gotoChannel-->channelID--->" + channelID);
		LogUtil.d("gotoChannel-->subID--->" + subID);

		new Thread() {
			public void run() {
				List<FilmClass> navigationItems = NavigationManager
						.GetInstance().getMainCategoryList();
				if (navigationItems != null && navigationItems.size() > 0) {
					for (int i = 0; i < navigationItems.size(); i++) {
						if (channelID.equals(navigationItems.get(i)
								.getChannelCode())) {
							filmClass = navigationItems.get(i);
						}
					}
					sendMessage(GET_NAVIGATION_SUCCESS);
				} else {
					sendMessage(GET_NAVIGATION_FAILURE);
				}
			};
		}.start();
	}

	private String getValueFromUrl(String url, String param) {
		Uri uri = Uri.parse(url);
		return uri.getQueryParameter(param);
	}

	private void initVooleLib() {
		new Thread() {
			@Override
			public void run() {
				ProxyManager.GetInstance().startProxy();
			}
		}.start();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		LogUtil.d("MovieDetailActivity-->onActivityResult");
		switch (requestCode) {
		case IPlay.START_VIDEOPLAYER_REQUEST:
			if (resultCode == RESULT_OK) {
				finish();
			}
			break;
		case PlayManager.START_ACCOUNT_REQUEST:
			finish();
			break;
		case DITAIL_REQUEST_CODE:
			if (resultCode == RESULT_OK) {
				finish();
			}
			break;
		default:
			break;
		}
	}
}
