package com.voole.epg.view.movies.zy;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.corelib.model.cooperation.CooperationManager;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.transscreen.DataResult;
import com.voole.epg.corelib.model.transscreen.TransScreenManager;
import com.voole.epg.model.play.PlayManager;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.epg.view.navigation.NavigationMovieView;

public class ZYFilmListActivity extends BaseActivity {
	private LogoView logoView = null;
	private ZYFilmView zyFilmView = null;
	private FilmClass parentNavItem = null;
	private FilmAndPageInfo filmAndPageInfo = null;
	private String movieUrl = null;
	private PageNavigator moviePageNavigator = null;
	private NavigationMovieView navigationMovieView = null;
	private String resultText = "";

	private List<Film> films = null;

	private static final int GET_FILMS_SUCCESS = 0;
	private static final int GET_FILMS_FAIL = 1;
	private static final int ADD_FAVORITE_SUCCESS = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_zy_film);
		init();
//		getData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getData();
	}

	private void getData() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String parentTitle = bundle.getString("parentTitle");
			parentNavItem = (FilmClass) bundle.getSerializable("navigation");
			logoView.setChannelName(parentTitle, false);
			int pageIndex = 1;
			if (filmAndPageInfo != null) {
				pageIndex = filmAndPageInfo.getPageIndex();
			}
			getFilmList(pageIndex);
		} else {
			finish();
		}

	}

	private void showToast(String str){
		Toast.makeText(ZYFilmListActivity.this, str, 3000).show();
	}
	
	private void addFavorite(final Film film, final int index) {
		final String mid = film.getMid();
		final String title = film.getFilmName();
		final String imgUrl = film.getImgUrl();
		showDialog();
		new Thread() {
			public void run() {
				final DataResult result = TransScreenManager.GetInstance()
						.addFavorite(mid,title,imgUrl);
				if (result == null) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					//resultText = result.getResultText();
					//sendMessage(ADD_FAVORITE_SUCCESS);
					if("0".equals(result.getResultCode())){
						CooperationManager.GetInstance().getCooperation().addFavorite(film);
						 runOnUiThread(new Runnable() {
								@Override
								public void run() {
									 cancelDialog();
									 zyFilmView.set(index, "0");
									 showToast("收藏成功");
								}
							});
					}else{
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(ZYFilmListActivity.this, result.getResultText(), 3000).show();
							}
						});
					}
				}
			};
		}.start();

	}

	private void cancelFavorite(final List<? extends Film> films,
			final int index) {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				DataResult favoriteResult = TransScreenManager.GetInstance()
						.deleteFavorite((List<Film>) films);
				if (favoriteResult != null) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							 cancelDialog();
							 zyFilmView.set(index, "1");
							 showToast("取消收藏");
						}
					});
					
				}
			}
		}).start();

	}

	private void getFilmList(final int pageNo) {
		showDialog();
		movieUrl = parentNavItem.getFilmClassUrl();
		new Thread() {
			public void run() {
				filmAndPageInfo = MovieManager.GetInstance().getMovies(
						movieUrl, pageNo, zyFilmView.getITEM_SIZE());
				if (filmAndPageInfo == null) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					if (filmAndPageInfo.getFilmList() != null
							&& filmAndPageInfo.getFilmList().size() > 0) {
						films = TransScreenManager.GetInstance()
								.getFavoriteList(filmAndPageInfo.getFilmList());
						sendMessage(GET_FILMS_SUCCESS);
					} else {
						sendMessage(GET_FILMS_FAIL);
					}
				}
			};
		}.start();

	}

	private void init() {
		logoView = (LogoView) findViewById(R.id.movie_logo);
		zyFilmView = (ZYFilmView) findViewById(R.id.zy_list);
		zyFilmView.setMovieViewListener(new MovieViewListener() {

			@Override
			public void onPlay(Film item) {
				PlayManager.GetInstance().play(ZYFilmListActivity.this, item);
			}

			@Override
			public void onItemSelected(Film item, int index) {
				if ("0".equals(item.getIsFavorite())) {
					List<Film> films = new ArrayList<Film>();
					films.add(item);
					cancelFavorite(films,index);
				} else {
					addFavorite(item, index);
				}
			}

			@Override
			public void onGotoPage(int pageNo) {
				getFilmList(pageNo);
			}
		});
		moviePageNavigator = (PageNavigator) findViewById(R.id.movie_page);
		moviePageNavigator
				.setPageNavigatorListener(new PageNavigatorListener() {

					@Override
					public void onGotoPage(int pageNo) {
						getFilmList(pageNo);
					}
				});
		navigationMovieView = (NavigationMovieView) findViewById(R.id.movie_navigation);
	}

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_FILMS_SUCCESS:
			zyFilmView.setData(films);
			zyFilmView.setPageInfo(filmAndPageInfo.getPageIndex(),
					filmAndPageInfo.getPageCount());
			zyFilmView.setTitle(parentNavItem.getFilmClassName());
			moviePageNavigator.setPageInfo(filmAndPageInfo.getPageIndex(),
					filmAndPageInfo.getPageCount());
			zyFilmView.requestFocus();
			cancelDialog();
			break;
		case ADD_FAVORITE_SUCCESS:
			cancelDialog();
			new TVAlertDialog.Builder(ZYFilmListActivity.this)
					.setCancelable(false)
					.setTitle(resultText)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).create().show();
			break;
		case GET_FILMS_FAIL:
			cancelDialog();
			new TVAlertDialog.Builder(ZYFilmListActivity.this)
					.setCancelable(false)
					.setTitle("获取数据有误")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
									finish();
								}
							}).create().show();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (navigationMovieView.imageView.getVisibility() != View.VISIBLE) {
				moviePageNavigator.requestFocus();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_1:
			moviePageNavigator.setInputNumber(1);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_2:
			moviePageNavigator.setInputNumber(2);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_3:
			moviePageNavigator.setInputNumber(3);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_4:
			moviePageNavigator.setInputNumber(4);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_5:
			moviePageNavigator.setInputNumber(5);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_6:
			moviePageNavigator.setInputNumber(6);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_7:
			moviePageNavigator.setInputNumber(7);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_8:
			moviePageNavigator.setInputNumber(8);
			moviePageNavigator.showDialog(this);
			return true;
		case KeyEvent.KEYCODE_9:
			moviePageNavigator.setInputNumber(9);
			moviePageNavigator.showDialog(this);
			return true;
		default:
			return super.onKeyDown(keyCode, event);
		}
		return super.onKeyDown(keyCode, event);
	}

}
