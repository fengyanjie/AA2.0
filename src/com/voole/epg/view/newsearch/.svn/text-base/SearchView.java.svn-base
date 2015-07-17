package com.voole.epg.view.newsearch;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.epg.view.newsearch.KeyboardView.KeyboardListener;
import com.voole.epg.view.newsearch.SearchTitleBarView.SearchBarListener;

public class SearchView extends BaseLinearLayout {
	private SearchTitleBarView searchBarView = null;
	private KeyboardView keyboardView = null;
	private HotView hotView = null;
	private SearchResultView searchResultView = null;
	
	public void setSearchResultListener(MovieViewListener listener){
		if (searchResultView != null) {
			searchResultView.setMovieViewListener(listener);
		}
	}
	
	public void setHotViewListener(MovieViewListener listener){
		if (hotView != null) {
			hotView.setMovieViewListener(listener);
		}
	}
	
	public void setKeyboardViewListener(KeyboardListener listener){
		if (keyboardView != null) {
			keyboardView.setOnKeyboardListener(listener);
		}
	}
	
	public void setSearchBarListener(SearchBarListener listener){
		if (listener != null) {
			searchBarView.setOnSearchBarListener(listener);
		}
	}
	
	public SearchView(Context context) {
		super(context);
		init(context);
	}
	
	public SearchView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SearchView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		setOrientation(HORIZONTAL);
		
		LinearLayout layout_left = new LinearLayout(context);
		layout_left.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout_left = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,301);
		param_layout_left.leftMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_layout_left.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_left.bottomMargin = DisplayManager.GetInstance().changeHeightSize(45);
		layout_left.setLayoutParams(param_layout_left);
		
		searchBarView = new SearchTitleBarView(context);
		LinearLayout.LayoutParams param_searchBarView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		searchBarView.setLayoutParams(param_searchBarView);
		layout_left.addView(searchBarView);
		
		keyboardView = new KeyboardView(context);
		LinearLayout.LayoutParams param_keyboardView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_keyboardView.topMargin = DisplayManager.GetInstance().changeHeightSize(5);
		keyboardView.setLayoutParams(param_keyboardView);
		layout_left.addView(keyboardView);
		
		addView(layout_left);
		
		ImageView middleLine = new ImageView(context);
		middleLine.setImageResource(R.drawable.cs_search_middle_line);
		LinearLayout.LayoutParams param_middleLine = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,600);
		param_middleLine.bottomMargin = DisplayManager.GetInstance().changeHeightSize(30);
		middleLine.setLayoutParams(param_middleLine);
		
		addView(middleLine);
		
		RelativeLayout layout_right = new RelativeLayout(context);
		LinearLayout.LayoutParams param_layout_right = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,301);
		param_layout_right.leftMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_layout_right.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_right.bottomMargin = DisplayManager.GetInstance().changeHeightSize(15);
		layout_right.setLayoutParams(param_layout_right);
		
		hotView = new HotView(context);
		LinearLayout.LayoutParams param_hotView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		hotView.setLayoutParams(param_hotView);
		layout_right.addView(hotView);
		
		searchResultView = new SearchResultView(context);
		LinearLayout.LayoutParams param_searchResultView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		searchResultView.setLayoutParams(param_searchResultView);
		layout_right.addView(searchResultView);
		
		addView(layout_right);
		
		showHotView();
	}
	
	public void setHotViewData(List<Film> films){
		hotView.requestFocus();
		hotView.setData(films);
	}
	
	public void setSearchResultData(FilmAndPageInfo filmAndPageInfo,int currentPage,int totalPage){
		showSearchResult();
		searchResultView.requestFocus();
		searchResultView.setData(filmAndPageInfo.getFilmList(), currentPage, totalPage);
	}
	
	public void showSearchResult(){
		searchResultView.setVisibility(View.VISIBLE);
		hotView.setVisibility(View.INVISIBLE);
	}
	
	public void showHotView(){
		hotView.setVisibility(View.VISIBLE);
		searchResultView.setVisibility(View.INVISIBLE);
	}
	
	public void showNoSearchResultToast(String keyword){
		showHotView();
		hotView.showNoSearchResultToast(keyword);
	}
	
	public void hideNoSearchResultToast(){
		hotView.hideNoSearchResultToast();
	}
	
	public void addInputText(String string){
		searchBarView.addInputText(string);
	}
	
	public void deleteInputText(){
		searchBarView.deleteInputText();
	}
	
	public void clearInputText(){
		searchBarView.clearInputText();
	}
	
	public void hideRelateList(){
	}
	
	public void showRelateList(List<String> relatedKeyWords){
	}
	
	public void setFocusedToA(){
		keyboardView.setFocusedToA();
	}
	
	public String getSearchKeyword(){
		return searchBarView.getSearchText();
	}
}
