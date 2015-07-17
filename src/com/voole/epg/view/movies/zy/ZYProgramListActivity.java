package com.voole.epg.view.movies.zy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.view.movies.zy.ZYProgramView.ZYProgramViewListener;

public class ZYProgramListActivity extends BaseActivity {

	private LogoView logoView;
	private ZYProgramView zyProgramView = null;
	private PageNavigator moviePageNavigator = null;
	private int ITEM_SIZE;
	private List<FilmClass> filmClass = null;
	private Map<Integer, Integer> pageAndIndex = null;
	private int totalPageSize;
	private String parentTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_zy_porgram);
		init();
		getProgramData();
	}

	/**
	 * 获取节目数据
	 */
	private void getProgramData() {
		Bundle bundle = getIntent().getExtras();
		filmClass = (List<FilmClass>) bundle.getSerializable("programList");
		parentTitle = bundle.getString("parentTitle");
		logoView.setChannelName(parentTitle,false);
		// zyProgramView.setData(filmClass);
		ITEM_SIZE = zyProgramView.getITEM_SIZE();
		int len = filmClass.size();
		totalPageSize = (len + ITEM_SIZE - 1) / ITEM_SIZE;
		for (int i = 0, j = 0; i < totalPageSize; i++, j += ITEM_SIZE) {
			pageAndIndex.put(i + 1, j);
		}
		getList(1);
	}

	private void getList(int pageNo) {
		int i = pageAndIndex.get(pageNo);
		List<FilmClass> l = null;
		if (pageNo == totalPageSize) {
			l = filmClass.subList(i, filmClass.size());
		} else {
			l = filmClass.subList(i, i + ITEM_SIZE);
		}
		zyProgramView.setPageInfo(pageNo, totalPageSize);
		moviePageNavigator.setPageInfo(pageNo, totalPageSize);
		zyProgramView.setData(l);
	}

	private void init() {

		logoView = (LogoView) findViewById(R.id.movie_logo);
		zyProgramView = (ZYProgramView) findViewById(R.id.program_list);
		zyProgramView.setZyProgramViewListener(new ZYProgramViewListener() {

			@Override
			public void onItemSelected(FilmClass item) {
				Bundle bundle = new Bundle();
				bundle.putString("parentTitle", parentTitle);
				bundle.putSerializable("navigation", item);
				Intent intent = new Intent();
				intent.putExtras(bundle);
				intent.setClass(ZYProgramListActivity.this,
						ZYFilmListActivity.class);
				startActivity(intent);
			}

			@Override
			public void onGotoPage(int pageNo) {
				getList(pageNo);
			}
		});
		moviePageNavigator = (PageNavigator) findViewById(R.id.movie_page);
		moviePageNavigator
				.setPageNavigatorListener(new PageNavigatorListener() {
					@Override
					public void onGotoPage(int pageNo) {
						zyProgramView.setSelectedItemIndex(0);
						getList(pageNo);
					}
				});
		pageAndIndex = new HashMap<Integer, Integer>();
	}

	@Override
	protected void doHandleMessage(int what, Object obj) {

	}

}
