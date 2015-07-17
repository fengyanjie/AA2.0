package com.voole.epg.view.movies.zy;

import java.io.Serializable;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.base.common.LogoView.LogoViewListener;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.movies.Label;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.movies.SearchManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.navigation.NavigationManager;
import com.voole.epg.corelib.model.play.IPlay;
import com.voole.epg.model.play.PlayManager;
import com.voole.epg.view.movies.live.LiveActivity;
import com.voole.epg.view.movies.movie.MovieView;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.epg.view.navigation.NavigationItemSelectedListener;
import com.voole.epg.view.navigation.NavigationMovieView;
import com.voole.epg.view.navigation.NavigationProgramView;

public class ZYActivity extends BaseActivity {
	// private static final String CHANNEL_ALL_ID = "-1";

	private static final int CHANNEL_ZY = 1;
	private static final int CHANNEL_LIFE = 2;
	private LogoView logoView = null;
	private MovieView movieView = null;
	private NavigationProgramView navigationProgramView = null;
	private PageNavigator moviePageNavigator = null;
	private NavigationMovieView navigationMovieView = null;

	private FilmAndPageInfo filmAndPageInfo = null;
	private List<FilmClass> programList = null;
	private List<FilmClass> displayProgramList = null;
	private String movieUrl = null;
	private FilmClass parentNavItem = null;
	private int channelType = CHANNEL_ZY;

	private final int GET_CATEGORY_SUCCESS = 10002;
	private final int GET_CATEGORY_FAIL = 10003;
	private final int GET_SEARCH_FAIL = 10004;
	private Label typeLabel = null;
	private List<Label> labels = null;

	private boolean isSearch = false;
	private String type = "";
	private String year = "";
	private String area = "";

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case GET_CATEGORY_SUCCESS:
			cancelDialog();
			logoView.setCategory(parentNavItem.getFilmClassName(),typeLabel, labels);
			logoView.showCategory();
			break;
		case GET_CATEGORY_FAIL:
			break;
		case GET_SEARCH_FAIL:
			cancelDialog();
			new TVAlertDialog.Builder(ZYActivity.this)
 			.setCancelable(false)
			.setTitle(R.string.movie_get_search_fail)
			.setPositiveButton(R.string.common_ok_button, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			}).create().show();
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_movies_zy);
		init();
		getData();
	}

	private void getData() {
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			parentNavItem = (FilmClass) bundle.getSerializable("navigation");
			
			
			int jumpType = bundle.getInt("jumpType");
			switch (jumpType) {
			case 1:
				navigationMovieView.setVisibility(View.GONE);
				break;
			default:
				break;
			}
			
			
			if (parentNavItem != null) {
				logoView.setChannelName(parentNavItem.getFilmClassName(), true);
				if (NavigationManager.ZY.equals(parentNavItem.getTemplate())) {
					channelType = CHANNEL_ZY;
				} else if (NavigationManager.LIFE.equals(parentNavItem
						.getTemplate())) {
					channelType = CHANNEL_LIFE;
				}
				getProgramList();
			}
		} else {
			finish();
		}
	}

	private void getCategory() {
		showDialog();
		new Thread() {
			public void run() {
				typeLabel = SearchManager.GetInstance().getTypeLabel(
						parentNavItem.getKeyValue());
				if (typeLabel == null) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					if (typeLabel.getLabelNameList() == null
							|| typeLabel.getLabelNameList().size() == 0) {
						sendMessage(GET_CATEGORY_FAIL);
					} else {
						labels = SearchManager.GetInstance()
								.getYearAndAreaLabel();
						if (labels == null) {
							sendMessage(ACCESS_NET_FAIL);
						} else {
							if (labels.get(0).getLabelNameList() == null
									|| labels.get(0).getLabelNameList().size() == 0) {
								sendMessage(GET_CATEGORY_FAIL);
							} else if (labels.get(1).getLabelNameList() == null
									|| labels.get(1).getLabelNameList().size() == 0) {
								sendMessage(GET_CATEGORY_FAIL);
							} else {
								sendMessage(GET_CATEGORY_SUCCESS);
							}
						}
					}
				}
			};
		}.start();
	}

	private void getSearchList(final String type, final String year,
			final String area, final int pageNo) {
		showDialog();
		new Thread() {
			public void run() {

				filmAndPageInfo = SearchManager.GetInstance()
						.searchMoviesInChannel(parentNavItem.getChannelId(),
								year, area, type, pageNo,
								movieView.getITEM_SIZE());
				if (filmAndPageInfo != null
						&& filmAndPageInfo.getFilmList() != null
						&& filmAndPageInfo.getFilmList().size() > 0) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							String category = "";
							isSearch = true;
							if (!"".equals(area)) {
								category += area + "/";
							}
							if (!"".equals(type)) {
								category += type + "/";
							}
							if (!"".equals(year)) {
								category += year + "/";
							}

							int len = category.length();
							if (len > 0) {
								category = category.substring(0, len - 1);
							}

							logoView.setCategory(category);
							movieView.setData(filmAndPageInfo.getFilmList());
							movieView.setPageInfo(
									filmAndPageInfo.getPageIndex(),
									filmAndPageInfo.getPageCount());
							moviePageNavigator.setPageInfo(
									filmAndPageInfo.getPageIndex(),
									filmAndPageInfo.getPageCount());
							cancelDialog();
						}
					});
				} else {
					sendMessage(GET_SEARCH_FAIL);
				}
			};
		}.start();
	}

	private void init() {
		logoView = (LogoView) findViewById(R.id.movie_logo);
		logoView.setLogoViewListener(new LogoViewListener() {
			@Override
			public void onFindCategory() {
				getCategory();
			}

			@Override
			public void onStartFinding(String type, String year, String area) {
				ZYActivity.this.type = type;
				ZYActivity.this.year = year;
				ZYActivity.this.area = area;
				getSearchList(type, year, area, 1);
			}
		});
		navigationProgramView = (NavigationProgramView) findViewById(R.id.movie_program_navigator);
		navigationProgramView.setAlwaysNotifyListener(true);
		navigationProgramView
				.setOnItemSelectedListener(new NavigationItemSelectedListener() {
					@Override
					public void onItemSelected(int index, FilmClass item) {
						if (index == 0) {
							return;
						} else if (index == NavigationProgramView.ITEM_SIZE - 1) {
							if (channelType == CHANNEL_ZY) {
								/* gotoZYProgramListActivity(); */
								gotoZYFilmListActivity(item);
							} else {
								gotoLifeProgramListActivity(index);
							}
						} else {
							if (channelType == CHANNEL_ZY) {
								gotoZYFilmListActivity(item);
							} else {
								gotoLifeProgramListActivity(index);
							}
						}

						/*
						 * if(index==0){ return; }else if (index ==
						 * NavigationProgramView.ITEM_SIZE - 1) { if
						 * (channelType == CHANNEL_ZY) {
						 * gotoZYProgramListActivity(); } else {
						 * gotoLifeProgramListActivity(index); } } else { if
						 * (channelType == CHANNEL_ZY) {
						 * gotoZYFilmListActivity(item); } else {
						 * gotoLifeProgramListActivity(index); } }
						 */
					}
				});
		navigationProgramView.setNextFocusUpId(ID.LogoView.FIND_ID);
		movieView = (MovieView) findViewById(R.id.movie_list);
		movieView.hiddenActor();
		movieView.setMovieViewListener(new MovieViewListener() {
			@Override
			public void onPlay(Film item) {

			}

			@Override
			public void onItemSelected(Film item,int index) {
				PlayManager.GetInstance().play(ZYActivity.this, item);
			}

			@Override
			public void onGotoPage(int pageNo) {
				if (isSearch) {
					getSearchList(type, year, area, pageNo);
				} else {
					getFilmList(pageNo);
				}
			}
		});
		moviePageNavigator = (PageNavigator) findViewById(R.id.movie_page);
		moviePageNavigator
				.setPageNavigatorListener(new PageNavigatorListener() {
					@Override
					public void onGotoPage(int pageNo) {
						if (isSearch) {
							getSearchList(type, year, area, pageNo);
						} else {
							getFilmList(pageNo);
						}
					}
				});
		navigationMovieView = (NavigationMovieView) findViewById(R.id.movie_navigation);
	}

	/*
	 * private void gotoZYProgramListActivity() { Bundle bundle = new Bundle();
	 * bundle.putString("parentTitle", parentNavItem.getFilmClassName());
	 * bundle.putSerializable("programList", (Serializable) programList); Intent
	 * intent = new Intent(); intent.putExtras(bundle);
	 * intent.setClass(ZYActivity.this, ZYProgramListActivity.class);
	 * startActivity(intent); }
	 */

	private void gotoZYFilmListActivity(FilmClass item) {
		Bundle bundle = new Bundle();
		bundle.putString("parentTitle", parentNavItem.getFilmClassName());
		bundle.putSerializable("navigation", item);
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(ZYActivity.this, ZYFilmListActivity.class);
		startActivity(intent);
	}

	private void gotoLifeProgramListActivity(int index) {
		Bundle bundle = new Bundle();
		bundle.putString("parentTitle", parentNavItem.getFilmClassName());
		bundle.putSerializable("programList", (Serializable) programList);
		bundle.putInt("index", index);
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(ZYActivity.this, LiveActivity.class);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(IPlay.START_VIDEOPLAYER_REQUEST!=requestCode){
			navigationProgramView.setSelectedItemIndex(0);
			navigationProgramView.requestFocus();
		}
	}

	private void getFilmList(final int pageNo) {
		showDialog();
		new Thread() {
			public void run() {
				filmAndPageInfo = MovieManager.GetInstance().getMovies(
						movieUrl, pageNo, movieView.getITEM_SIZE());
				if (filmAndPageInfo == null
						|| filmAndPageInfo.getFilmList() == null
						|| filmAndPageInfo.getFilmList().size() < 1) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					
					final List<Film> priceFilmList = MovieManager.GetInstance()
							.getMoviesListForPrice(
									filmAndPageInfo.getFilmList());
					if (priceFilmList != null && priceFilmList.size() > 0) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								isSearch = false;
								movieView.setData(priceFilmList);
								movieView.setPageInfo(
										filmAndPageInfo.getPageIndex(),
										filmAndPageInfo.getPageCount());
								// navigationProgramView.requestFocus();
								moviePageNavigator.setPageInfo(
										filmAndPageInfo.getPageIndex(),
										filmAndPageInfo.getPageCount());
								cancelDialog();
							}
						});
					}
				}
			};
		}.start();
	}

	/*
	 * private void getProgramList() { showDialog(); new Thread() { public void
	 * run() { programList = NavigationManager.GetInstance()
	 * .getCategoryListByFilmClass(parentNavItem); if (programList == null ||
	 * programList.size() < 1) { sendMessage(ACCESS_NET_FAIL); } else { if
	 * (channelType == CHANNEL_LIFE) { displayProgramList = programList; } else
	 * if (channelType == CHANNEL_ZY) { displayProgramList = new
	 * ArrayList<FilmClass>(); int endIndex = programList.size(); if (endIndex >
	 * NavigationProgramView.ITEM_SIZE) { endIndex =
	 * NavigationProgramView.ITEM_SIZE - 1; for (int i = 0; i < endIndex; i++) {
	 * displayProgramList.add(programList.get(i)); } FilmClass all = new
	 * FilmClass(); // all.setFilmClassID(CHANNEL_ALL_ID);
	 * all.setFilmClassName("更多综艺"); displayProgramList.add(all); } else { for
	 * (int i = 0; i < endIndex; i++) {
	 * displayProgramList.add(programList.get(i)); } } } runOnUiThread(new
	 * Runnable() {
	 * 
	 * @Override public void run() {
	 * navigationProgramView.setData(displayProgramList);
	 * navigationProgramView.requestFocus(); movieUrl =
	 * displayProgramList.get(0) .getFilmClassUrl(); getFilmList(1); } }); } };
	 * }.start(); }
	 */

	private void getProgramList() {
		showDialog();
		new Thread() {
			public void run() {
				programList = NavigationManager.GetInstance()
						.getCategoryListByFilmClass(parentNavItem);
				if (programList == null || programList.size() < 1) {
					sendMessage(ACCESS_NET_FAIL);
				} else {
					displayProgramList = programList;
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							navigationProgramView.setData(displayProgramList);
							navigationProgramView.requestFocus();
							movieUrl = displayProgramList.get(0)
									.getFilmClassUrl();
							getFilmList(1);
						}
					});
				}
			};
		}.start();
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
