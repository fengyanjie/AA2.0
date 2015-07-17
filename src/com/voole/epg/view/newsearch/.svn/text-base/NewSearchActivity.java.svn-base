package com.voole.epg.view.newsearch;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.voole.epg.R;
import com.voole.epg.base.BaseActivity;
import com.voole.epg.base.common.LogoView;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.movies.Label;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.movies.SearchManager;
import com.voole.epg.corelib.model.movies.SearchManager.SearchType;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.navigation.NavigationManager;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.movies.detail.SingleLineMovieView;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.epg.view.mymagic.MyMagicActivity;
import com.voole.epg.view.newsearch.ConditionView.ConditionViewListener;
import com.voole.epg.view.newsearch.FindView.FindViewListener;
import com.voole.epg.view.newsearch.KeyboardView.KeyboardListener;
import com.voole.epg.view.newsearch.SearchTitleBarView.SearchBarListener;
import com.voole.epg.view.newsearch.TabView.TabViewListener;

public class NewSearchActivity extends BaseActivity {
	private static final int DOWNLOAD_UI = 10001;
	private static final int SEARCH_SUCCESS = 10002;
	private static final int SEARCH_FAIL = 10003;
	private static final int GOTO_PAGE_FAIL = 10004;
	private static final int HOTFILM_SUCCESS = 10005;
	private static final int HOTFILM_FAIL = 10006;
	private static final int NAVIGATION_SUCCESS=10007;
	
	private static final int GET_TYPE_SUCCESS=10008;
	private static final int GET_YEARAREA_SUCCESS=10009;

	private LinearLayout ll_searchLeft = null;
	private LinearLayout ll_searchRight = null;

	
	private LogoView logoView = null;

	private TabView tabView = null;
	private KeyboardView keyboardView = null;
	private FindCatView findCatView = null;

	private SingleLineMovieView movieView=null; 
	
	private List<Film> hotFilms = null;
	private FilmAndPageInfo searchResult = null;
	private String keyword = "";
	
	private ConditionView conditionView=null;
	private List<FilmClass> navigationItems = null;
	private List<FilmClass> methodItems = null;
	
	private String channelID="";
	
	private Label typeLabel=null;
	private List<Label> yearArea=null;
	
	@Override
	protected void doHandleMessage(int what, Object obj) {
		switch (what) {
		case DOWNLOAD_UI:
			init();
			// getHotFilms();
			break;
		case HOTFILM_SUCCESS:
			 
			break;
		case HOTFILM_FAIL:
			Toast.makeText(NewSearchActivity.this, "没有热播影片", Toast.LENGTH_LONG)
					.show();
			break;
		case SEARCH_SUCCESS:
			movieView.setData(searchResult.getFilmList());
			movieView.setPageInfo(searchResult.getPageIndex(), searchResult.getPageCount());
			break;
		case SEARCH_FAIL:
			break;
		case GOTO_PAGE_FAIL:
			Toast.makeText(NewSearchActivity.this, "抱歉，翻页失败!",
					Toast.LENGTH_SHORT).show();
			break;
		case NAVIGATION_SUCCESS:
			FilmClass fc=new FilmClass();
			fc.setChannelId("");
			fc.setFilmClassName("全部");
			List<FilmClass> filmlist=new ArrayList<FilmClass>();
			filmlist.add(fc);
			filmlist.addAll(navigationItems);
			conditionView.setData(filmlist);
			Label catLabel = new Label();
			ArrayList<String> cats = new ArrayList<String>();
			for(int i=0;i<navigationItems.size();i++){
				cats.add(navigationItems.get(i).getFilmClassName());
			}
			catLabel.setLabelNameList(cats);
			findCatView.setCategory(catLabel);
			break;
		case GET_TYPE_SUCCESS:
			findCatView.setType(typeLabel);
			break;
		case GET_YEARAREA_SUCCESS:
			findCatView.setYearArea(yearArea);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cs_search_new);
		init();
		// downloadUI();
	}

	private void downloadUI() {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				sendMessage(DOWNLOAD_UI);
			}
		}).start();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	private void init() {
		// showDialog();
		LinearLayout mainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		mainLayout.setBackgroundResource(R.drawable.cs_recommend_bg);
		logoView = (LogoView) findViewById(R.id.logo);
		logoView.setChannelName("搜索", false);
		logoView.hideSearchLabel();
		tabView = (TabView) findViewById(R.id.tabView);
		tabView.setTabViewListener(new TabViewListener() {

			@Override
			public void onRightClick() {
				ll_searchLeft.setVisibility(View.GONE);
				ll_searchRight.setVisibility(View.VISIBLE);
				if(yearArea==null){
					getTypeLabel(navigationItems.get(0));
					getYearAreaLabel();
				}
				conditionView.setData(methodItems);
			}

			@Override
			public void onLeftClick() {
				ll_searchRight.setVisibility(View.GONE);
				ll_searchLeft.setVisibility(View.VISIBLE);
				conditionView.setData(navigationItems);
			}
		});
		ll_searchLeft = (LinearLayout) findViewById(R.id.ll_searchLeft);
		ll_searchRight = (LinearLayout) findViewById(R.id.ll_searchRight);
		// searchView = (SearchView)findViewById(R.id.searchView);
		keyboardView = (KeyboardView) findViewById(R.id.keyboardView);
		findCatView = (FindCatView) findViewById(R.id.findCatView);
		movieView=(SingleLineMovieView) findViewById(R.id.search_movieView);
		conditionView=(ConditionView) findViewById(R.id.condition);
		conditionView.setConditionViewListener(new ConditionViewListener() {
			@Override
			public void onItemSelected(String id) {
				channelID=id;
			}
		});
		navigationItems=new ArrayList<FilmClass>();
		methodItems=new ArrayList<FilmClass>();
		FilmClass recommend=new FilmClass();
		recommend.setFilmClassName("推荐");
		FilmClass news=new FilmClass();
		news.setFilmClassName("最新");
		methodItems.add(recommend);
		methodItems.add(news);
		getNavigation();
		addListener();
	}

	private void addListener() {
		
		keyboardView.setOnKeyboardListener(new KeyboardListener() {
			
			@Override
			public void onSearch() {
				
			}
			
			@Override
			public void onKey(String text) {
				keyword=text;
				searchMovie(keyword, 1, SearchType.JianPin,false);
			}
			
			@Override
			public void onDelete() {
				
			}
			
			@Override
			public void onClear() {
				
			}
		});
		
		findCatView.setListener(new FindViewListener() {
			
			@Override
			public void onItemClick(String content, boolean isCategory) {
				if(isCategory){
					for(FilmClass fc:navigationItems){
						if(content.equals(fc.getFilmClassName())){
							getTypeLabel(fc);
						}
					}
				}else{
					
				}
			}
		});
		
		movieView.setMovieViewListener(new MovieViewListener() {
			
			@Override
			public void onPlay(Film item) {
				
			}
			
			@Override
			public void onItemSelected(Film item, int index) {
				gotoDetail(item);
			}
			
			@Override
			public void onGotoPage(int pageNo) {
				searchMovie(keyword, pageNo, SearchType.JianPin,true);
			}
		});
		

	}

	private void getHotFilms() {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				hotFilms = MovieManager.GetInstance().getTopViewMovies();
				if (hotFilms != null && hotFilms.size() > 0) {
					sendMessage(HOTFILM_SUCCESS);
				} else {
					sendMessage(HOTFILM_FAIL);
				}
			}
		}).start();
	}
	
	private void getNavigation() {
		showDialog();
		new Thread() {
			public void run() {
				 List<FilmClass> items= NavigationManager.GetInstance()
						.getMainCategoryList();
				if (items != null && items.size() > 0) {
					navigationItems.addAll(items);
					sendMessage(NAVIGATION_SUCCESS);
				}  
			};
		}.start();
	}

	private void getTypeLabel(final FilmClass fc){
		new Thread(){
			public void run() {
				typeLabel=SearchManager.GetInstance().getTypeLabel(fc.getKeyValue());
				if(typeLabel!=null){
					sendMessage(GET_TYPE_SUCCESS);
				}
			};
			
		}.start();
	}
	
	private void getYearAreaLabel(){
		new Thread(){
			public void run() {
				yearArea=SearchManager.GetInstance().getYearAndAreaLabel();
				if(yearArea!=null&&yearArea.size()>0){
					sendMessage(GET_YEARAREA_SUCCESS);
				}
			};
		}.start();
	}
	
	private void searchMovie(final String string, final int currentPage,
			final SearchType searchType, final boolean isGotoPage) {
		showDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				searchResult = SearchManager.GetInstance().searchIndex(string,
						currentPage, 6, searchType,channelID);
				if (searchResult != null && searchResult.getFilmList() != null
						&& searchResult.getFilmList().size() > 0) {
					sendMessage(SEARCH_SUCCESS);
				} else {
					if (isGotoPage) {
						sendMessage(GOTO_PAGE_FAIL);
					} else {
						sendMessage(SEARCH_FAIL);
					}
				}
			}
		}).start();
	}

	private void gotoDetail(Film film) {
		Intent intent = new Intent();
		intent.putExtra("intentMid", film.getMid());
		// intent.putExtra("film", film);
		intent.setClass(NewSearchActivity.this, MovieDetailActivity.class);
		startActivity(intent);
	}
}

