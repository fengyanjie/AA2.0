package com.voole.epg.view.movies.topic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.PageIconNavigator;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.navigation.FilmClassAndPageInfo;
import com.voole.epg.view.movies.topic.TopicListView.TopicListViewListener;

public class TopicListActivity extends BaseActivity {
	private LogoView logoView;
	private PageIconNavigator pageIconNavigator = null;
	private final int GET_DATA_SUCCESS = 1;
	private final int GET_DATA_FAILURE = 2;
	private TopicListView topicListView = null;
	private FilmClassAndPageInfo filmClassAndPageInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_topic_list);
		init();
		getData(1);
	}

	private void getData(final int pageNo) {
		showDialog();
		new Thread() {
			public void run() {
				filmClassAndPageInfo = MovieManager.GetInstance().getTopicList(
						pageNo, topicListView.getITEM_SIZE());
				if (filmClassAndPageInfo == null) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					if(filmClassAndPageInfo.getFilmList()==null||filmClassAndPageInfo.getFilmList().size()==0){
						sendMessage(GET_DATA_FAILURE);
					}else{
					    sendMessage(GET_DATA_SUCCESS);
					}
				}
			};
		}.start();

	}

	private void init() {
		logoView = (LogoView) findViewById(R.id.topic_logo);
		logoView.setChannelName(getString(R.string.topic_zuanti),false);
		topicListView = (TopicListView) findViewById(R.id.topic_list_view);
		topicListView.setTopicListViewListener(new TopicListViewListener() {

			@Override
			public void onItemSelected(FilmClass item) {
				Intent intent = new Intent();
				if(MovieManager.TOPIC_VER.equals(item.getTemplate())){
					intent.setClass(TopicListActivity.this, TopicFilmListVerActivity.class);
				}else{
					intent.setClass(TopicListActivity.this, TopicFilmListHorActivity.class);
				}
//				intent.putExtra("navigation", item);
				intent.putExtra("topicUrl", item.getFilmClassUrl());
				intent.putExtra("topicBigUrl", item.getSmallImgUrl());
				intent.putExtra("topicName", item.getFilmClassName());
				startActivity(intent);

			}

			@Override
			public void onGotoPage(int pageNo) {
				getData(pageNo);
			}

		});
		pageIconNavigator = (PageIconNavigator) findViewById(R.id.topic_list_page);
	}

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_DATA_SUCCESS:
			topicListView.setData(filmClassAndPageInfo.getFilmList());
			topicListView.setPageInfo(filmClassAndPageInfo.getPageIndex(),
					filmClassAndPageInfo.getPageCount());
			topicListView.requestFocus();
			pageIconNavigator.setPageInfo(filmClassAndPageInfo.getPageIndex(),
					filmClassAndPageInfo.getPageCount());
			cancelDialog();
			break;
		case GET_DATA_FAILURE:
			new TVAlertDialog.Builder(TopicListActivity.this)
			.setTitle(R.string.topic_get_topiclsit_fail)
			.setPositiveButton(R.string.common_ok_button, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					TopicListActivity.this.finish();
				}
			}).create().show();
			break;
		default:
			break;
		}
	}

}
