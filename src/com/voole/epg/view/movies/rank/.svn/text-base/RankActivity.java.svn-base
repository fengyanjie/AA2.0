package com.voole.epg.view.movies.rank;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.movies.rank.RankView.RankViewListener;

public class RankActivity extends BaseActivity {

	private static final int GET_RANK_SUCCESS = 1;
	private LogoView logoView;
	private RankView rankview;

	private List<Film> movies = null;
	private List<Film> dsj = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_movies_rank);
		init();
		getRankData();
	}

	/**
	 * 获取排行列表数据
	 */
	private void getRankData() {
		showDialog();
		new Thread() {
			public void run() {
				movies = MovieManager.GetInstance().getFilmTopRank();
				if (movies == null) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					dsj = MovieManager.GetInstance().getDSJTopRank();
					if (dsj == null) {
						sendMessage(ACCESS_NET_FAIL);
					} else {
						sendMessage(GET_RANK_SUCCESS);
					}
				}

			}
		}.start();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		logoView = (LogoView) findViewById(R.id.recommend_logo);
		logoView.setChannelName(getString(R.string.rank_name),false);
		rankview = (RankView) findViewById(R.id.rankView);
		rankview.setRankViewListener(new RankViewListener() {

			@Override
			public void OnItemSelected(Film film) {
				Intent intent = new Intent();
				intent.putExtra("intentMid", film.getMid());
				intent.setClass(RankActivity.this, MovieDetailActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_RANK_SUCCESS:
			rankview.setLeftData(movies);
			rankview.setRightData(dsj);
			rankview.requestFocus();
			cancelDialog();
			break;
		default:
			break;
		}
	}

}
