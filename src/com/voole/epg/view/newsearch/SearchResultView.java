package com.voole.epg.view.newsearch;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.tvutils.BitmapUtil;

public class SearchResultView extends BaseLinearLayout {
	private static final int ITEM_SIZE = 7;
	private TextView txt_result = null;
	private TextView txt_page = null;
	private SearchResultItemView[] itemViews = null;
	
	private int currentIndex = -1;
	private int currentPage = 0;
	private int totalPage = 0;
	
	private List<Film> films = null;
	
	
	private MovieViewListener listener = null;
	public void setMovieViewListener(MovieViewListener listener){
		this.listener = listener;
	}
	
	public SearchResultView(Context context) {
		super(context);
		long startTime = System.currentTimeMillis();
		init(context);
		long stopTime = System.currentTimeMillis();
		Log.d("SearchResultView", "SearchResultView------->" + (stopTime - startTime));
	}
	
	public SearchResultView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SearchResultView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		setOrientation(VERTICAL);
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		RelativeLayout layout_title = new RelativeLayout(context);
		LinearLayout.LayoutParams param_layout_title = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 14);
		param_layout_title.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		layout_title.setLayoutParams(param_layout_title);
		
		
		txt_result = new TextView(context);
		txt_result.setText("搜索结果");
		txt_result.setTextColor(Color.WHITE);
		txt_result.setGravity(Gravity.CENTER);
		txt_result.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
		RelativeLayout.LayoutParams param_txt_result = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		param_txt_result.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		txt_result.setLayoutParams(param_txt_result);
		layout_title.addView(txt_result);
		
		txt_page = new TextView(context);
//		txt_page.setText("1/5页");
		txt_page.setTextColor(Color.WHITE);
		txt_page.setGravity(Gravity.CENTER);
		txt_page.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
		RelativeLayout.LayoutParams param_txt_page = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		param_txt_page.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		txt_page.setLayoutParams(param_txt_page);
		layout_title.addView(txt_page);
		
		addView(layout_title);
		
		LinearLayout layout_list = new LinearLayout(context);
		layout_list.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout_list = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2);
		param_layout_list.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		layout_list.setLayoutParams(param_layout_list);
		itemViews = new SearchResultItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_item.bottomMargin = DisplayManager.GetInstance().changeHeightSize(15);
		for (int i = 0; i < ITEM_SIZE; i++) {
			itemViews[i] = new SearchResultItemView(context);
			itemViews[i].setLayoutParams(param_item);
			itemViews[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					
				}
			});
			layout_list.addView(itemViews[i]);
		}
		
		addView(layout_list);
		
		LinearLayout layout_arrow = new LinearLayout(context);
		LinearLayout.LayoutParams param_layout_arrow = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 14);
		param_layout_arrow.gravity = Gravity.CENTER_HORIZONTAL;
		layout_arrow.setLayoutParams(param_layout_arrow);
		
		ImageView arrow = new ImageView(context);
		arrow.setBackgroundResource(R.drawable.cs_search_result_list_down_arrow);
		RelativeLayout.LayoutParams param_arrow = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_arrow.addRule(RelativeLayout.CENTER_IN_PARENT);
		arrow.setLayoutParams(param_arrow);
		layout_arrow.addView(arrow);
		
		addView(layout_arrow);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (currentIndex > 0) {
				currentIndex--;
				updateStatusUI();
				return true;
			}
			if (currentIndex == 0 && currentPage > 1) {
				previousPage();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (films != null && currentIndex < films.size() -1) {
				currentIndex++;
				updateStatusUI();
				return true;
			}
			if (currentIndex == ITEM_SIZE-1 && currentPage < totalPage) {
				nextPage();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (listener != null && currentIndex != -1) {
				listener.onItemSelected(films.get(currentIndex),currentIndex);
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
		currentIndex = 0;
		if (gainFocus) {
			for (int i = 0; i < ITEM_SIZE; i++) {
				if (i== currentIndex) {
					itemViews[i].setFocused(true);
				}else {
					itemViews[i].setFocused(false);
				}
			}
		}else {
			for (int i = 0; i < ITEM_SIZE; i++) {
				itemViews[i].setFocused(false);
			}
		}
	}
	
	public void setData(List<Film> films,int currentPage, int totalPage){
		defense(films);
		this.films = films;
		currentIndex = 0;
		for (int i = 0; i < ITEM_SIZE; i++) {
			if (i < films.size()) {
				itemViews[i].setContent(films.get(i).getFilmName());
				if (i== currentIndex) {
					itemViews[i].setFocused(true);
				}else {
					itemViews[i].setFocused(false);
				}
			}else {
				itemViews[i].setContent("");
				itemViews[i].setFocused(false);
			}
		}
		setPageInfo(currentPage, totalPage);
	}
	
	private void setPageInfo(int currentPage, int totalPage){
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		txt_page.setText(currentPage + "/" + totalPage + "页");
	}
	
	private void updateStatusUI(){
		defense(this.films);
		for (int i = 0; i < ITEM_SIZE; i++) {
			if (i== currentIndex) {
				itemViews[i].setFocused(true);
			}else {
				itemViews[i].setFocused(false);
			}
		}
	}
	
	private void defense(List<Film> films){
		if (films == null || films.size() == 0) {
			return;
		}
	}
	
	private void previousPage(){
		if (currentPage > 1 && listener != null) {
			currentPage--;
			listener.onGotoPage(currentPage);
		}
	}
	
	private void nextPage(){
		if (currentPage < totalPage && listener != null) {
			currentPage++;
			listener.onGotoPage(currentPage);
		}
	}
	
	class SearchResultItemView extends BaseLinearLayout{
		private AlwaysMarqueeTextView txt_content = null;
		private static final int PADDING = 5;
		private boolean isFocused = false;
		private BitmapDrawable focusedDrawable = new BitmapDrawable(BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_result_item_bg_focused));
		
		
		public SearchResultItemView(Context context) {
			super(context);
			init(context);
		}
		
		public SearchResultItemView(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public SearchResultItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}


		public void setContent(String text){
			txt_content.setText(text);
		}
		
		public void setFocused(boolean isFocused){
			this.isFocused = isFocused;
			updateUI();
		}
		
		private void init(Context context){
			txt_content = new AlwaysMarqueeTextView(context);
			txt_content.setMarquee(true);
			LinearLayout.LayoutParams param_txt_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			txt_content.setTextColor(Color.WHITE);
			txt_content.setGravity(Gravity.CENTER_VERTICAL);
			BitmapDrawable drawable = new BitmapDrawable(BitmapUtil.readBitmap(context, R.drawable.cs_search_result_item_bg_unfocused));
			txt_content.setBackgroundDrawable(drawable);
			txt_content.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
			txt_content.setPadding(20, 0, 20, 0);
			param_txt_content.setMargins(PADDING, PADDING, PADDING, PADDING);
			txt_content.setLayoutParams(param_txt_content);
			addView(txt_content);
		}
		
		private void updateUI(){
			if (isFocused) {
				setBackgroundDrawable(focusedDrawable);
				txt_content.setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
			}else {
				setBackgroundDrawable(null);
				txt_content.setTextColor(Color.WHITE);
			}
		}
		
		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			if (gainFocus) {
				setBackgroundDrawable(focusedDrawable);
			}else {
				setBackgroundDrawable(null);
			}
		}
	}
	
}
