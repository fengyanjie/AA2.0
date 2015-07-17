package com.voole.epg.view.newsearch;

import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.view.movies.movie.MovieItemView;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.tvutils.BitmapUtil;

public class HotView extends BaseLinearLayout {
	private static final int ITEM_SIZE = 6;
	private int currentIndex = -1;
	private int currentPage = 0;
	private int totalPage = 0;
	private int currentFilmSize = 0;
	private int totalSize = 0;
	
	private MovieItemView[] itemViews = null;
	private AlwaysMarqueeTextView txt_result = null;
	private List<Film> totalfilmList = null;
	private static List<Film> currentFilmList = null;
	
	private MovieViewListener listener = null;
	private ImageView arrow=null;
	public void setMovieViewListener(MovieViewListener listener){
		this.listener = listener;
	}
	
	
	public HotView(Context context) {
		super(context);
		init(context);
	}

	public HotView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public HotView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		setOrientation(VERTICAL);
		
		txt_result = new AlwaysMarqueeTextView(context);
		txt_result.setText("很抱歉，未找到 \" " + "XXX" + "\"相关结果");
		txt_result.setSingleLine(true);
		txt_result.setHorizontallyScrolling(true);
		txt_result.setEllipsize(TruncateAt.MARQUEE);
		txt_result.setMarqueeRepeatLimit(-1);
		txt_result.setGravity(Gravity.CENTER_VERTICAL);
		txt_result.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
		txt_result.setVisibility(GONE);
		LinearLayout.LayoutParams param_txt_result = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 11);
		param_txt_result.leftMargin = DisplayManager.GetInstance().changeWidthSize(20);
		param_txt_result.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		txt_result.setLayoutParams(param_txt_result);
		
		addView(txt_result);
		
		TextView txt_title = new TextView(context);
		txt_title.setText("热门推荐：");
		txt_title.setGravity(Gravity.CENTER_VERTICAL);
		txt_title.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
		LinearLayout.LayoutParams param_txt_title = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 11);
		param_txt_title.leftMargin = DisplayManager.GetInstance().changeWidthSize(20);
		param_txt_title.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		txt_title.setLayoutParams(param_txt_title);
		
		addView(txt_title);
		
		LinearLayout layout_movie = new LinearLayout(context);
		layout_movie.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout_movie = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2);
		param_layout_movie.leftMargin = DisplayManager.GetInstance().changeWidthSize(20);
		param_layout_movie.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		layout_movie.setLayoutParams(param_layout_movie);
		
		LinearLayout layout_movie_up = new LinearLayout(context);
		layout_movie_up.setOrientation(HORIZONTAL);
		LinearLayout.LayoutParams param_layout_movie_up = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_movie_up.setLayoutParams(param_layout_movie_up);
		itemViews = new MovieItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_movie_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_movie_item.rightMargin = 5;
		for (int i = 0; i < ITEM_SIZE/2; i++) {
			itemViews[i] = new MovieItemView(context);
			itemViews[i].setLayoutParams(param_movie_item);
			layout_movie_up.addView(itemViews[i]);
		}
		layout_movie.addView(layout_movie_up);
		
		LinearLayout layout_movie_down = new LinearLayout(context);
		layout_movie_down.setOrientation(HORIZONTAL);
		LinearLayout.LayoutParams param_layout_movie_down = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_layout_movie_down.topMargin = 10;
		layout_movie_down.setLayoutParams(param_layout_movie_down);
		for (int i = ITEM_SIZE/2; i < ITEM_SIZE; i++) {
			itemViews[i] = new MovieItemView(context);
			itemViews[i].setLayoutParams(param_movie_item);
			layout_movie_down.addView(itemViews[i]);
		}
		layout_movie.addView(layout_movie_down);
		
		addView(layout_movie);
		
		LinearLayout layout_arrow = new LinearLayout(context);
		LinearLayout.LayoutParams param_layout_arrow = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 11);
		param_layout_arrow.gravity = Gravity.CENTER_HORIZONTAL;
		param_layout_arrow.topMargin = DisplayManager.GetInstance().changeHeightSize(15);
		layout_arrow.setLayoutParams(param_layout_arrow);
		
		arrow = new ImageView(context);
		arrow.setImageBitmap(BitmapUtil.readBitmap(context, R.drawable.cs_search_result_list_down_arrow));
		RelativeLayout.LayoutParams param_arrow = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_arrow.addRule(RelativeLayout.CENTER_IN_PARENT);
		arrow.setLayoutParams(param_arrow);
		arrow.setVisibility(View.INVISIBLE);
		layout_arrow.addView(arrow);
		
		addView(layout_arrow);
	}
	
	public void setData(List<Film> films){
		if (films != null && films.size() > 0) {
			this.totalfilmList = films;
			initPageInfo(films.size());
			setMovieData(currentPage);
		}
	}
	
	private void initPageInfo(int totalSize){
		this.currentPage = 1;
		this.totalSize = totalSize;
		if(totalSize % ITEM_SIZE > 0){
			this.totalPage = totalSize / ITEM_SIZE + 1;
		}else{
			this.totalPage = totalSize / ITEM_SIZE ;
		}
	}
	
	private void getCurrentFilmList(){
		int startPosition = (currentPage-1)*ITEM_SIZE;
		int endPosition;
		if (totalSize > currentPage*ITEM_SIZE) {
//			endPosition = currentPage*ITEM_SIZE - 1;
			endPosition = currentPage*ITEM_SIZE;
		}else {
//			endPosition = totalSize - 1;
			endPosition = totalSize;
		}
		currentFilmList = totalfilmList.subList(startPosition, endPosition);
	}
	
	public void showNoSearchResultToast(String keyword){
		txt_result.setText("很抱歉，未找到 \" " + keyword + "\"相关结果");
		txt_result.setVisibility(View.VISIBLE);
	}
	
	public void hideNoSearchResultToast(){
		txt_result.setVisibility(View.GONE);
	}
	
	private void setMovieData(int pageNo){
		
		if(pageNo<totalPage){
			arrow.setVisibility(View.VISIBLE);
		}
		else if(pageNo==totalPage){
			arrow.setVisibility(View.INVISIBLE);
		}
		getCurrentFilmList();
		currentFilmSize = currentFilmList.size();
		if (currentFilmList != null && currentFilmList.size() > 0) {
			int filmsSize = currentFilmList.size();
			for (int i = 0; i < ITEM_SIZE; i++) {
				if(i < filmsSize){
					itemViews[i].setVisibility(View.VISIBLE);
					itemViews[i].setData(currentFilmList.get(i));
					currentIndex = 0;
					if(i == currentIndex){
						itemViews[i].setFocusedItem(true);
					}else{
						itemViews[i].setFocusedItem(false);
					}
				}else{
					itemViews[i].setVisibility(View.INVISIBLE);
					itemViews[i].setFocusedItem(false);
				}
			}
		}
	}
	
	private void updateStatusUI(){
		if (currentFilmList != null && currentFilmSize > 0) {
			for (int i = 0; i < ITEM_SIZE; i++) {
				if(i < currentFilmSize){
					itemViews[i].setVisibility(View.VISIBLE);
					if(i == currentIndex){
						itemViews[i].setFocusedItem(true);
					}else{
						itemViews[i].setFocusedItem(false);
					}
				}else{
					itemViews[i].setVisibility(View.INVISIBLE);
					itemViews[i].setFocusedItem(false);
				}
			}
		}
	}
	
	private void previousPage(){
		if (currentPage > 1) {
			currentPage--;
			setMovieData(currentPage);
		}
	}
	
	private void nextPage(){
		if (currentPage < totalPage) {
			currentPage++;
			setMovieData(currentPage);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (currentIndex >= ITEM_SIZE/2) {
				currentIndex -= ITEM_SIZE/2;
				updateStatusUI();
			}else {
				if (currentPage > 1) {
					previousPage();
				}else {
					return false;
				}
			}
			return true;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (currentFilmList != null) {
				if (currentIndex < ITEM_SIZE/2) {
					if (currentFilmList.size() - ITEM_SIZE/2 >= 0) {
						if(currentIndex+ITEM_SIZE/2<currentFilmList.size()){
							currentIndex +=ITEM_SIZE/2;
						}else{
							currentIndex=currentFilmList.size()-1;
						}
						updateStatusUI();
					}
				}else {
					nextPage();
				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (currentFilmList != null) {
				if (currentIndex>0) {
					currentIndex--;
					updateStatusUI();
					return true;
				}
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (currentFilmList != null) {
				if (currentIndex < currentFilmList.size() - 1) {
					currentIndex++;
					updateStatusUI();
					return true;
				}
			}
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (listener != null && currentFilmList != null) {
				listener.onItemSelected(currentFilmList.get(currentIndex),currentIndex);
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		Log.d("VoolePlay", "HotView---------->onFocused------" + gainFocus);
		if (gainFocus) {
//			updateStatusUI();
			for (int i = 0; i < ITEM_SIZE; i++) {
				if(i == currentIndex){
					itemViews[i].setFocusedItem(true);
				}else{
					itemViews[i].setFocusedItem(false);
				}
			}
		}else {
			for (int i = 0; i < ITEM_SIZE; i++) {
				/*if(i < filmsSize){
					itemViews[i].setVisibility(View.VISIBLE);
					if(i == currentIndex){
						itemViews[i].setFocusedItem(true);
					}else{
						itemViews[i].setFocusedItem(false);
					}
				}else{
					itemViews[i].setVisibility(View.INVISIBLE);
					itemViews[i].setFocusedItem(false);
				}*/
				itemViews[i].setFocusedItem(false);
			}
		}
	}
	
}
