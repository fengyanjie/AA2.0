package com.voole.epg.view.movies.movie;

import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.LogoView.LogoViewListener;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.movies.Label;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.movies.SearchManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.navigation.NavigationManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.navigation.NavigationItemSelectedListener;
import com.voole.epg.view.navigation.NavigationMovieView;
import com.voole.epg.view.navigation.NavigationProgramView;

public class MovieActivity extends BaseActivity {
	private static final int FILM_FAIL = 10001;

	private LogoView logoView = null;
	private MovieView movieView = null; 
	private NavigationProgramView navigationProgramView = null;
	private PageNavigator moviePageNavigator = null;
	private NavigationMovieView navigationMovieView = null;

	private FilmAndPageInfo filmAndPageInfo = null;
	private List<FilmClass> programList = null;
	private String movieUrl = null;
	// private List<FilmClass> navigationItems = null;
	private FilmClass parentNavItem = null;

	private final int GET_CATEGORY_SUCCESS = 10002;
	private final int GET_CATEGORY_FAIL = 10003;
	private final int GET_SEARCH_FAIL = 10005;

	private Label typeLabel = null;
	private List<Label> labels = null;

	private boolean isSearch = false;
	private String type = "";
	private String year = "";
	private String area = "";

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case FILM_FAIL:
			new TVAlertDialog.Builder(MovieActivity.this)
					.setCancelable(false)
					.setTitle(R.string.movie_get_film_fail)
					.setPositiveButton(R.string.common_ok_button,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}
							}).create().show();
			break;
		case GET_CATEGORY_SUCCESS:
			cancelDialog();
			logoView.setCategory(parentNavItem.getFilmClassName(),typeLabel, labels);
			logoView.showCategory();
			break;
		case GET_CATEGORY_FAIL:
			cancelDialog();
			break;
		case GET_SEARCH_FAIL:
			new TVAlertDialog.Builder(MovieActivity.this)
					.setCancelable(false)
					.setTitle(R.string.movie_get_search_fail)
					.setPositiveButton(R.string.common_ok_button,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
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
		setContentView(R.layout.cs_movies_movie);
		init();
		
		getData();
		// getNavigation();
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
			
			String subID = bundle.getString("subID");
			LogUtil.d("MovieActivity-->subID--->" + subID);
			
			if (parentNavItem != null) {
				if(NavigationManager.THREEDIMENSIONS.equals(parentNavItem.getTemplate())){
					Toast.makeText(MovieActivity.this, "为保证您正常观看3D影片，请确保您的电视支持3D且已经将电视调整为3D模式", Toast.LENGTH_LONG).show();
				}
				logoView.setChannelName(parentNavItem.getFilmClassName(), true);
				getProgramList(subID);
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

	private void init() {
		logoView = (LogoView) findViewById(R.id.movie_logo);
		logoView.setLogoViewListener(new LogoViewListener() {

			@Override
			public void onFindCategory() {
				getCategory();
			}

			@Override
			public void onStartFinding(String type, String year, String area) {
				MovieActivity.this.type = type;
				MovieActivity.this.year = year;
				MovieActivity.this.area = area;
				getSearchList(type, year, area, 1);
			}

		});
		navigationProgramView = (NavigationProgramView) findViewById(R.id.movie_program_navigator);
		navigationProgramView.setAlwaysNotifyListener(true);
		navigationProgramView.setNextFocusUpId(ID.LogoView.FIND_ID);
		navigationProgramView
				.setOnItemSelectedListener(new NavigationItemSelectedListener() {
					@Override
					public void onItemSelected(int index, FilmClass item) {
						movieUrl = item.getFilmClassUrl();
						getFilmList(1);
					}
				});
		movieView = (MovieView) findViewById(R.id.movie_list);
//		Animation animation = AnimationUtils.loadAnimation(this, R.anim.scale_rotate);  
//		movieView.startAnimation(animation);
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
				intent.setClass(MovieActivity.this, MovieDetailActivity.class);
				startActivity(intent);
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

	private void getFilmList(final int pageNo) {
		showDialog();
		new Thread() {
			public void run() {
				filmAndPageInfo = MovieManager.GetInstance().getMovies(
						movieUrl, pageNo, movieView.getITEM_SIZE());
				if (filmAndPageInfo != null
						&& filmAndPageInfo.getFilmList() != null
						&& filmAndPageInfo.getFilmList().size() > 0) {
					final List<Film> priceFilmList = MovieManager.GetInstance()
							.getMoviesListForPrice(
									filmAndPageInfo.getFilmList());
					if (priceFilmList != null && priceFilmList.size() > 0) {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								isSearch = false;
								logoView.setCategory("");
								movieView.setData(priceFilmList);
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
						sendMessage(FILM_FAIL);
					}
				} else {
					sendMessage(FILM_FAIL);
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
					final List<Film> priceFilmList = MovieManager.GetInstance()
							.getMoviesListForPrice(
									filmAndPageInfo.getFilmList());
					if (priceFilmList != null && priceFilmList.size() > 0) {
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
								movieView.setData(priceFilmList);
								movieView.setPageInfo(
										filmAndPageInfo.getPageIndex(),
										filmAndPageInfo.getPageCount());
								moviePageNavigator.setPageInfo(
										filmAndPageInfo.getPageIndex(),
										filmAndPageInfo.getPageCount());
								cancelDialog();
							}
						});
					}else {
						sendMessage(GET_SEARCH_FAIL);
					}
				} else {
					sendMessage(GET_SEARCH_FAIL);
				}
			};
		}.start();
	}

	private void getProgramList(final String subID) {
		showDialog();
		new Thread() {
			public void run() {

				programList = NavigationManager.GetInstance()
						.getCategoryListByFilmClass(parentNavItem);
				if (programList != null && programList.size() > 0) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							navigationProgramView.setData(programList);
							int index = findSubID(subID);
							navigationProgramView.setSelectedItemIndex(index);
							navigationProgramView.requestFocus();
							movieUrl = programList.get(index).getFilmClassUrl();
							getFilmList(1);
						}
					});
				} else {
					sendMessage(ACCESS_NET_FAIL);
				}
			};
		}.start();
	}
	
	private int findSubID(String subID){
		if(subID == null || "".equals(subID)){
			return 0;
		}
		for(int i=0; i<programList.size(); i++){
			if(subID.equals(programList.get(i).getChannelCode())){
				return i;
			}
		}
		return 0;
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

	/*
	 * private void getNavigation(){ new Thread(){ public void run() {
	 * navigationItems = NavigationManager.GetInstance().getMainCategoryList();
	 * runOnUiThread(new Runnable() {
	 * 
	 * @Override public void run() {
	 * navigationMovieView.setItems(navigationItems); } }); }; }.start(); }
	 */

}
