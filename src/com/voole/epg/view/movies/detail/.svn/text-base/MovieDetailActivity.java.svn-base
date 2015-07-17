package com.voole.epg.view.movies.detail;

import java.util.ArrayList;
import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.base.common.MessageManager;
import com.voole.epg.base.common.NumberNavigator;
import com.voole.epg.base.common.NumberNavigator.NumberNavigatorListener;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.base.common.TVProgressDialog;
import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.auth.UrlList;
import com.voole.epg.corelib.model.cooperation.CooperationManager;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Detail;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.movies.SearchManager;
import com.voole.epg.corelib.model.play.Ad;
import com.voole.epg.corelib.model.play.IPlay;
import com.voole.epg.corelib.model.play.PlayCheckInfo;
import com.voole.epg.corelib.model.transscreen.DataResult;
import com.voole.epg.corelib.model.transscreen.ResumePlay;
import com.voole.epg.corelib.model.transscreen.TransScreenManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.model.play.PlayManager;
import com.voole.epg.model.play.PlayManager.ChargeState;
import com.voole.epg.view.movies.detail.MovieDetailView.DetailButtonListener;
import com.voole.epg.view.movies.movie.MovieViewListener;

public class MovieDetailActivity extends BaseActivity {
	private static final int SUCCESS_DETAIL = 0x10000002;
	private static final int SUCCESS_FILMANDRPAGEINFO = 0x10000003;
	private static final int SUCCESS_PRODUCT = 0x10000004;
	private static final int RESULT_CHECKINFO = 0x10000005;
	private static final int GET_FILM_FAIL = 0x10000006;
	private static final int FAVORITE_SUCCESS = 0x10000007;
	private static final int NET_FAIL = 0x10000008;
	private static final int PURSUE_SUCCESS = 0x10000009;

	private static final int ISFOVORITE_STATUES = 0x10000010;
	private static final int ISTELE_STATUES = 0x10000011;
	private static final int GET_PREVIEW_INFO = 0x10000012;

	private static final int CANCLE_FAVORITE_SUCCESS = 0x10000013;
	private static final int CANCEL_PURSUE_SUCCESS = 0x10000014;

	private SingleLineMovieView movieView = null;
	private NumberNavigator numberNavigator = null;
	private MovieDetailView movieDetailView = null;

	private Detail detail = null;
	private Film film = null;
	private List<Product> products = null;
	private PlayCheckInfo playCheckInfo = null;
	private List<Film> releatedFilms = null;
	private List<Content> contents = null;

	private Product currentProduct = null;
	private ChargeState isCharge = ChargeState.Charge;
	private DataResult favoriteResult = null;
	private DataResult pursueResult = null;
	private Ad ad = null;

	private LogoView logoView = null;

	private boolean isFovorite = false;
	private boolean isTele = false;

	private TVProgressDialog dialog = null;
	private boolean isMovie = false;

	private boolean isFirstCheck = true;
	private boolean isRecommend = false;
	
	private boolean isFirst=true;
	
	private static final String VOOLE_SCHEMA = "voole://movieDetail";
	private static final Uri DETAIL_URI = Uri.parse(VOOLE_SCHEMA);

	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case SUCCESS_DETAIL:
			movieDetailView.setDetailData(detail, film);
			movieDetailView.setLeftFocus();
			movieDetailView.showButton(true);
			contents = detail.getContentList();
			if (contents.size() > 1) {
				isMovie = false;
				if (detail.getTotalSize() != null
						&& !detail.getTotalSize()
								.equals(detail.getOnLineSize())) {
					getIsTele();
					movieDetailView.setIsPursue(true);
				} else {
					movieDetailView.setIsPursue(false);
				}
				numberNavigator.setTotalSize(contents.size());
				showNumberView();
				getResumePlay();
			} else {
				isMovie = true;
			}
			getProductList();
			getReleatedFilmList();
			break;
		case SUCCESS_PRODUCT:
			movieDetailView.setProductsData(products);
			getCheckInfo();
			getIsFavorite();
			break;
		case RESULT_CHECKINFO:
			isFirstCheck = false;
			cancelCheckInfoDialog();
			if (playCheckInfo == null || !"0".equals(playCheckInfo.getStatus())) {
				currentProduct = null;
				movieDetailView
						.setCheckInfo(getString(R.string.movie_detail_checkinfo_fail));
			} else {
				String checkInfo = getCheckInfoResult(playCheckInfo, products);
				if (!"".equals(checkInfo)) {
					movieDetailView.setCheckInfo(checkInfo);
				} else {
					getPreviewInfo();
				}
			}
			break;
		case GET_PREVIEW_INFO:
			StringBuffer sb = new StringBuffer();
			sb.append("您未订购本片");
			if (ad != null && ad.getPriview() != null
					&& !"0".equals(ad.getPriview())
					&& !"0".equals(ad.getPriviewTime())) {
				sb.append("，您可以免费预览");
				sb.append(Integer.parseInt(ad.getPriviewTime()) / 60);
				sb.append("分钟");
			}
			movieDetailView.setCheckInfo(sb.toString());
			break;
		case SUCCESS_FILMANDRPAGEINFO:
			if (releatedFilms == null || releatedFilms.size() == 0) {
				// movieDetailView.showLove(false);
				// movieView.setVisibility(View.INVISIBLE);
			} else {
				// movieDetailView.showLove(true);
				// movieView.setVisibility(View.VISIBLE);
				isRecommend = true;
				movieView.setData(releatedFilms);
				movieView.setPageInfo(1, 1);
				if (isMovie) {
					showMovieView();
				}
			}
			break;
		case GET_FILM_FAIL:
			new TVAlertDialog.Builder(MovieDetailActivity.this)
					.setCancelable(false)
					.setTitle(R.string.movie_detail_film_fail)
					.setPositiveButton(R.string.common_ok_button,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									MovieDetailActivity.this.finish();
								}
							}).create().show();
			break;
		case FAVORITE_SUCCESS:
			isFovorite = true;
			movieDetailView.setIsFavorite(isFovorite);
			showToast("收藏成功");
//			new TVAlertDialog.Builder(MovieDetailActivity.this)
//					.setTitle(favoriteResult.getResultText())
//					.setPositiveButton(R.string.common_ok_button,
//							new DialogInterface.OnClickListener() {
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//
//								}
//							}).create().show();
			break;
		case PURSUE_SUCCESS:
			isTele = true;
			movieDetailView.setIsTele(isTele);
			showToast("追剧成功");
//			new TVAlertDialog.Builder(MovieDetailActivity.this)
//					.setTitle(pursueResult.getResultText())
//					.setPositiveButton(R.string.common_ok_button,
//							new DialogInterface.OnClickListener() {
//								@Override
//								public void onClick(DialogInterface dialog,
//										int which) {
//
//								}
//							}).create().show();
			break;
		case ISFOVORITE_STATUES:
			isFirst=false;
			movieDetailView.setIsFavorite(isFovorite);
			break;
		case ISTELE_STATUES:
			movieDetailView.setIsTele(isTele);
			break;
		case NET_FAIL:
			String alertMsg = MessageManager.GetInstance().getAlert(
					R.string.common_get_data_fail, this);
			new TVAlertDialog.Builder(MovieDetailActivity.this)
					.setTitle(alertMsg)
					.setPositiveButton(R.string.common_ok_button,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).create().show();
			break;
		case CANCEL_PURSUE_SUCCESS:
			cancelDialog();
			isTele = false;
			movieDetailView.setIsTele(isTele);
			showToast("取消追剧");
			CooperationManager.GetInstance().getCooperation().deleteZhuiJu(film.getMid());
			break;
		case CANCLE_FAVORITE_SUCCESS:
			cancelDialog();
			isFovorite = false;
			movieDetailView.setIsFavorite(isFovorite);
			showToast("取消收藏");
			CooperationManager.GetInstance().getCooperation().deleteFavorite(film.getMid());
			break;
		default:
			break;
		}
	}
	
	
	private void showToast(String str){
		Toast.makeText(MovieDetailActivity.this, str, 3000).show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_movies_detail);
		init();
		getData();
		this.setResult(RESULT_OK);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		LogUtil.d("MovieDetailActivity---->onNewIntent-->");
		setIntent(intent);
		getData();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (!isFirstCheck) {
			getCheckInfo();
		}
		
		if(isFirst==false&&film!=null){
			getIsFavorite();
			if(!isMovie){
				getIsTele();
			}
		}
	}

	private void init() {
		logoView = (LogoView) findViewById(R.id.detail_logo);
		logoView.getBtnAccount().setNextFocusDownId(
				ID.MovieDetaiView.DETAIL_LEFT_BTN_ID);
		logoView.getBtnRecharge().setNextFocusDownId(
				ID.MovieDetaiView.DETAIL_LEFT_BTN_ID);
		logoView.getBtnHistory().setNextFocusDownId(
				ID.MovieDetaiView.DETAIL_LEFT_BTN_ID);
		logoView.getBtnFavorite().setNextFocusDownId(
				ID.MovieDetaiView.DETAIL_LEFT_BTN_ID);
		logoView.getBtnSearch().setNextFocusDownId(
				ID.MovieDetaiView.DETAIL_LEFT_BTN_ID);
		movieView = (SingleLineMovieView) findViewById(R.id.detail_list);
		movieView.setVisibility(View.INVISIBLE);
		numberNavigator = (NumberNavigator) findViewById(R.id.detail_num);
		numberNavigator.setVisibility(View.GONE);
		movieDetailView = (MovieDetailView) findViewById(R.id.detail_content);
		movieDetailView.showLove(false);
		movieDetailView.showButton(false);
		logoView.setChannelName("", false);
		addListener();
	}

	private void getResumePlay() {
		new Thread() {
			public void run() {
				final ResumePlay resume = TransScreenManager.GetInstance()
						.getAllResumeInfo(film.getMid());
				if (resume != null && resume.getResumeInfoList().size() > 0 && resume.getResumeInfoList().get(0) != null) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (contents != null) {
								Toast.makeText(
										MovieDetailActivity.this,
										"您上次看到"
												+ resume.getResumeInfoList()
														.get(0)
														.getContentIndex()
												+ "集!", 3000).show();
								List<String> num = new ArrayList<String>();
								for (int i = 0; i < resume
										.getResumeInfoList().size(); i++) {
									num.add(resume.getResumeInfoList()
											.get(i).getContentIndex());
								}
								numberNavigator.setClickedNumber(num);
								numberNavigator.setCurrentNumber(Integer
										.parseInt(resume
												.getResumeInfoList().get(0)
												.getContentIndex()));
								numberNavigator.getFocus();
							}
						}
					});
				}else{
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							numberNavigator.setCurrentNumber(1);
							numberNavigator.getFocus();
						}
					});
				}
			};

		}.start();

	}

	/**
	 * 是否收藏
	 */
	private void getIsFavorite() {
		new Thread() {
			public void run() {
				isFovorite = TransScreenManager.GetInstance().isFavorite(
						film.getMid());
				sendMessage(ISFOVORITE_STATUES);
			};
		}.start();

	}

	private void cancelFavorite(final List<? extends Film> films) {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				favoriteResult = TransScreenManager.GetInstance()
						.deleteFavorite((List<Film>) films);
				if (favoriteResult != null) {
					CooperationManager.GetInstance().getCooperation().deleteFavorite(films.get(0).getMid());
					sendMessage(CANCLE_FAVORITE_SUCCESS);
				}
			}
		}).start();

	}

	private void cancelPursueVideo(final List<? extends Film> films) {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				pursueResult = TransScreenManager.GetInstance().deleteTele(
						(List<Film>) films);
				if (pursueResult != null) {
					sendMessage(CANCEL_PURSUE_SUCCESS);
				}
			}
		}).start();

	}

	/**
	 * 是否追剧
	 */
	private void getIsTele() {
		new Thread() {
			public void run() {
				isTele = TransScreenManager.GetInstance().isTele(film.getMid());
				sendMessage(ISTELE_STATUES);
			};
		}.start();

	}

	private void addListener() {
		movieView.setMovieViewListener(new MovieViewListener() {
			@Override
			public void onPlay(Film item) {

			}

			@Override
			public void onItemSelected(Film item,int index) {
				// doGetFilm(item.getMid());
				Intent intent = new Intent();
				intent.putExtra("intentMid", item.getMid());
				intent.setClass(MovieDetailActivity.this,
						MovieDetailActivity.class);
				startActivity(intent);
				finish();
			}

			@Override
			public void onGotoPage(int pageNo) {

			}
		});

		numberNavigator
				.setNumberNavigatorListener(new NumberNavigatorListener() {
					@Override
					public void onGotoNumber(int number) {

					}
				});

		movieDetailView.setDetailButtonListener(new DetailButtonListener() {
			@Override
			public void onSelected() {
				showNumberView();
//				numberNavigator.setFocus();
			}

			@Override
			public void onRecommend() {
				if (isRecommend) {
					showMovieView();
				} else {
					hiddenAllView();
				}
			}

			@Override
			public void onPursueVideo(Film film) {
				if (isTele) {
//					new TVAlertDialog.Builder(MovieDetailActivity.this)
//							.setTitle("已经追剧")
//							.setPositiveButton(R.string.common_ok_button,
//									new DialogInterface.OnClickListener() {
//										@Override
//										public void onClick(
//												DialogInterface dialog,
//												int which) {
//										}
//									}).create().show();
					List<Film> films=new ArrayList<Film>();
					films.add(film);
					cancelPursueVideo(films);
				} else {
					addPursueVide(film);
				}
			}

			@Override
			public void onPlay(Film film) {
				PlayManager.GetInstance().play(MovieDetailActivity.this, film,
						contents, 0, currentProduct, isCharge, "");
			}

			@Override
			public void onFavorite(Film film) {
				if (isFovorite) {
//					new TVAlertDialog.Builder(MovieDetailActivity.this)
//							.setTitle("已经收藏")
//							.setPositiveButton(R.string.common_ok_button,
//									new DialogInterface.OnClickListener() {
//										@Override
//										public void onClick(
//												DialogInterface dialog,
//												int which) {
//										}
//									}).create().show();
					List<Film> films=new ArrayList<Film>();
					films.add(film);
					cancelFavorite(films);
				} else {
					addFavorite(film);
				}
			}
		});
		numberNavigator
				.setNumberNavigatorListener(new NumberNavigatorListener() {
					@Override
					public void onGotoNumber(int number) {
						// Toast.makeText(MovieDetailActivity.this,
						// "-NumberNavigator----click-->" + number,
						// Toast.LENGTH_LONG).show();
						PlayManager.GetInstance().play(
								MovieDetailActivity.this, film, contents,
								number - 1, currentProduct, isCharge, "");
					}
				});
	}

	private void getData() {
		Uri uri = getIntent().getData();
		if (uri != null && DETAIL_URI.getScheme().equals(uri.getScheme())) {
			final String mid = uri.getQueryParameter("mid");
			final String sid = uri.getQueryParameter("sid");
			LogUtil.d("MovieDetail---Uri---->mid--->" + mid + "----sid---->" + sid);
			if (mid != null && !mid.equals("")) {
				doGetFilm(mid);
				return;
			}
		}
		final String intentMid = getIntent().getStringExtra("intentMid");
		if (intentMid != null && !intentMid.equals("")) {
			doGetFilm(intentMid);
			return;
		}
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			film = (Film) bundle.getSerializable("film");
			if (film == null) {
				finish();
			}
			getDetail();
		} else {
			finish();
		}
	}
	
	private void getDetail() {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				detail = MovieManager.GetInstance().getDetailFromSrcUrl(
						film.getSourceUrl());
				if (detail != null) {
					if (detail.getContentList() != null
							&& detail.getContentList().size() > 0) {
						sendMessage(SUCCESS_DETAIL);
					} else {
						sendMessage(GET_FILM_FAIL);
					}
				} else {
					sendMessage(ACCESS_NET_FAIL);
				}
			}
		}).start();
	}

	private void doGetFilm(final String mid) {
		showDialog();
		new Thread() {
			public void run() {
				UrlList urlList = AuthManager.GetInstance().getUrlList();
				if (urlList != null) {
					detail = MovieManager.GetInstance().getDetailFromMid(mid);
					if (detail == null) {
						sendMessage(ACCESS_NET_FAIL);
					} else {
						if (detail.getContentList() == null
								|| detail.getContentList().size() < 1) {
							sendMessage(GET_FILM_FAIL);
						} else {
							film = detail.getFilm();
							getReleatedFilmList();
							sendMessage(SUCCESS_DETAIL);
						}
					}
				}
			};
		}.start();
	}

	private void getProductList() {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				products = detail.getProductList();
				if (products != null && products.size() > 0) {
					sendMessage(SUCCESS_PRODUCT);
				} else {
					sendMessage(ACCESS_NET_FAIL);
				}
			}
		}).start();
	}

	private void getCheckInfo() {
		cancelDialog();
		if ("0".equals(products.get(0).getFee())) {
			movieDetailView.setCheckInfo("该影片可以免费观看");
			currentProduct = products.get(0);
			isCharge = ChargeState.Viewed;
			return;
		}
		showCheckInfoDialog();
		final PlayManager playManager = PlayManager.GetInstance();
		new Thread(new Runnable() {
			@Override
			public void run() {
				playCheckInfo = playManager.getPlayCheckInfo(film.getMid(),
						contents.get(0).getFid(), contents.get(0)
								.getContentIndex());
				sendMessage(RESULT_CHECKINFO);
			}
		}).start();
	}

	private String getCheckInfoResult(PlayCheckInfo playCheckInfo,
			List<Product> productList) {
		if ("0".equals(playCheckInfo.getOrder())) {
			int productSize = productList.size();
			for (int i = 0; i < productSize; i++) {
				if (playCheckInfo.getPid().equals(productList.get(i).getPid())) {
					currentProduct = productList.get(i);
					String stopTime = playCheckInfo.getEndtime();
					isCharge = ChargeState.Ordered;
					return "您已订购" + productList.get(i).getName() + "，可在"
							+ stopTime + "前免费观看";
				}
			}
		}
		if ("0".equals(playCheckInfo.getViewed())) {
			currentProduct = products.get(0);
			isCharge = ChargeState.Viewed;
			String endTime = playCheckInfo.getEndtime();
			return "您已看过该影片，可在" + endTime + "前免费观看";
		}
		currentProduct = products.get(0);
		return "";
	}

	private void getPreviewInfo() {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				ad = PlayManager.GetInstance()
						.getAuthPlayUrl(film.getMid(),
								contents.get(0).getContentIndex(),
								contents.get(0).getFid(),
								currentProduct.getPid(),
								currentProduct.getPtype(),
								contents.get(0).getDownUrl());
				sendMessage(GET_PREVIEW_INFO);
			}
		}).start();
	}

	private void getReleatedFilmList() {
		new Thread() {
			public void run() {
				releatedFilms = SearchManager.GetInstance().getRelatedMovies(
						film.getMid(), 8);
				sendMessage(SUCCESS_FILMANDRPAGEINFO);
			};
		}.start();
	}

	private void addFavorite(final Film film) {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				favoriteResult = TransScreenManager.GetInstance().addFavorite(
						film.getMid(),film.getFilmName(),film.getImgUrl());
				if (favoriteResult != null) {
					if("0".equals(favoriteResult.getResultCode())){
						CooperationManager.GetInstance().getCooperation().addFavorite(film);
						sendMessage(FAVORITE_SUCCESS);
					}else{
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MovieDetailActivity.this, favoriteResult.getResultText(), 3000).show();
							}
						});
					}
				} else {
					sendMessage(NET_FAIL);
				}
			}
		}).start();
	}

	private void addPursueVide(final Film film) {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				pursueResult = TransScreenManager.GetInstance().addTele(
						film.getMid(),film.getFilmName(),film.getImgUrl());
				if (pursueResult != null) {
					if("0".equals(pursueResult.getResultCode())){
						CooperationManager.GetInstance().getCooperation().addZhuiJu(film);
						sendMessage(PURSUE_SUCCESS);
					}else{
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(MovieDetailActivity.this, pursueResult.getResultText(), 3000).show();
							}
						});
					}
				} else {
					sendMessage(NET_FAIL);
				}
			}
		}).start();
	}

	private void hiddenAllView() {
		movieView.setVisibility(View.GONE);
		numberNavigator.setVisibility(View.GONE);
		movieDetailView.showLove(false);
	}

	private void showMovieView() {
		movieView.setVisibility(View.VISIBLE);
		numberNavigator.setVisibility(View.GONE);
		movieDetailView.showLove(true);
	}

	private void showNumberView() {
		movieView.setVisibility(View.GONE);
		numberNavigator.setVisibility(View.VISIBLE);
		movieDetailView.showLove(false);
	}

	private void showCheckInfoDialog() {
		if (dialog == null) {
			dialog = new TVProgressDialog(this);
		}
		if (!this.isFinishing()){
			dialog.show();
		}
	}

	private void cancelCheckInfoDialog() {
		if (dialog != null) {
			dialog.cancel();
			dialog = null;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		LogUtil.d("MovieDetailActivity-->onActivityResult");
		switch (requestCode) {
		case IPlay.START_VIDEOPLAYER_REQUEST:
			if (resultCode == RESULT_OK) {
				LogUtil.d("MovieDetailActivity-->onActivityResult-----START_VIDEOPLAYER_REQUEST");
				Bundle bundle = data.getExtras();
				if (bundle != null) {
					if (numberNavigator.getVisibility() == View.VISIBLE) {
						// final int playItem = bundle.getInt("PlayItem");
						// LogUtil.d("MovieDetailActivity-->onActivityResult-----playItem-->"
						// + playItem);
						ArrayList<String> playedList = bundle
								.getStringArrayList("PlayedItemList");
						if (playedList != null && playedList.size() > 0) {
							numberNavigator.addClickedNumber(playedList);
							numberNavigator
									.setCurrentNumber(Integer
											.parseInt(playedList.get(playedList
													.size() - 1)));
						}
						/*
						 * if (playItem >= 0) {
						 * numberNavigator.setCurrentNumber(playItem + 1); }
						 */
					}
				}
			}
			break;
		default:
			break;
		}
	}
 
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	
}
