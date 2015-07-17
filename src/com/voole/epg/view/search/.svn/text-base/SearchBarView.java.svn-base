package com.voole.epg.view.search;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ShadeButton;

public class SearchBarView extends BaseRelativeLayout {
//	private static final int ITEM_SIZE = 5;
//	private SearcheBarListItemView[] itemViews = null;
	private SearcheBarListItemView inputText = null;
	private ShadeButton searchBtn = null;
	

	public SearchBarView(Context context) {
		super(context);
		init(context);
	}

	public SearchBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SearchBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		RelativeLayout layout_bar = new RelativeLayout(context);
		layout_bar.setId(101);
		layout_bar.setBackgroundResource(R.drawable.cs_search_bar_bg);
		RelativeLayout.LayoutParams param_layout_bar = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_layout_bar.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		param_layout_bar.addRule(RelativeLayout.CENTER_HORIZONTAL);
		layout_bar.setLayoutParams(param_layout_bar);
		
		RelativeLayout layout_bar_middle = new RelativeLayout(context);
		RelativeLayout.LayoutParams param_layout_bar_middle = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_layout_bar_middle.setMargins(DisplayManager.GetInstance().changeWidthSize(10), DisplayManager.GetInstance().changeHeightSize(5), DisplayManager.GetInstance().changeWidthSize(15), DisplayManager.GetInstance().changeHeightSize(10));
		layout_bar_middle.setLayoutParams(param_layout_bar_middle);
		inputText = new SearcheBarListItemView(context);
		inputText.setId(1001);
		RelativeLayout.LayoutParams param_inputText = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_inputText.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		inputText.setLayoutParams(param_inputText);
		layout_bar_middle.addView(inputText);
		
		searchBtn = new ShadeButton(context);
		RelativeLayout.LayoutParams param_searchBtn = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		param_searchBtn.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		searchBtn.setLayoutParams(param_searchBtn);
		layout_bar_middle.addView(searchBtn);
		
		layout_bar.addView(layout_bar_middle);
		
		addView(layout_bar);
		
		LinearLayout layout_list = new LinearLayout(context);
		layout_list.setOrientation(LinearLayout.VERTICAL);
//		layout_list.setBackgroundResource(R.drawable.cs_search_bar_list_bg);
		RelativeLayout.LayoutParams param_layout_list = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_layout_list.addRule(RelativeLayout.ALIGN_BOTTOM, 101);
		layout_list.setLayoutParams(param_layout_list);
//		itemViews = new SearcheBarListItemView[ITEM_SIZE];
//		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
//		for (int i = 0; i < ITEM_SIZE; i++) {
//			itemViews[i] = new SearcheBarListItemView(context);
//			itemViews[i].setLayoutParams(param_item);
//			layout_list.addView(itemViews[i]);
//		}
		
		addView(layout_list);
		
	}
	
	class SearcheBarListItemView extends BaseLinearLayout{
		
		private TextView txt_content = null;
		private static final int PADDING = 8;
		private boolean isFocused = false;

		public SearcheBarListItemView(Context context) {
			super(context);
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
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
			
			txt_content = new TextView(context);
			LinearLayout.LayoutParams param_txt_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			txt_content.setTextColor(Color.WHITE);
			txt_content.setGravity(Gravity.CENTER_VERTICAL);
			txt_content.setBackgroundResource(R.drawable.cs_search_bar_list_item_unfocused);
			param_txt_content.setMargins(PADDING, PADDING, PADDING, PADDING);
			txt_content.setLayoutParams(param_txt_content);
			addView(txt_content);
		}
		
		private void updateUI(){
			if (isFocused) {
				setBackgroundResource(R.drawable.cs_search_bar_list_item_focused);
			}else {
				setBackgroundResource(0);
			}
		}
		
		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			if (gainFocus) {
				setBackgroundResource(R.drawable.cs_search_result_item_bg_focused);
			}else {
				setBackgroundResource(0);
			}
		}
	}
	
}
