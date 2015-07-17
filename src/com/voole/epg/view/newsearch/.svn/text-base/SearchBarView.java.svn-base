package com.voole.epg.view.newsearch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.ShadeButton;

public class SearchBarView extends BaseLinearLayout {

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

	private void init(Context context) {
		setOrientation(VERTICAL);
		LinearLayout layout=new LinearLayout(context);
		LinearLayout.LayoutParams param_layout = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(param_layout);
		layout.setOrientation(HORIZONTAL);
		layout.setGravity(Gravity.CENTER_VERTICAL);
		layout.setBackgroundResource(R.drawable.cs_search_bar_bg);
		
		
		inputText = new SearcheBarListItemView(context);
		LinearLayout.LayoutParams param_inputText = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		inputText.setLayoutParams(param_inputText);
		layout.addView(inputText);

		searchBtn = new ShadeButton(context);
		searchBtn.setText("清空");
		LinearLayout.LayoutParams param_searchBtn = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		searchBtn.setLayoutParams(param_searchBtn);
		layout.addView(searchBtn);
		
		addView(layout);
	}

	class SearcheBarListItemView extends BaseLinearLayout {

		private TextView txt_content = null;
		private static final int PADDING = 8;

		public SearcheBarListItemView(Context context) {
			super(context);
			init(context);
		}

		public void setContent(String text) {
			txt_content.setText(text);
		}

		private void init(Context context) {
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);

			txt_content = new TextView(context);
			LinearLayout.LayoutParams param_txt_content = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			txt_content.setTextColor(Color.WHITE);
			txt_content.setGravity(Gravity.CENTER_VERTICAL);
			txt_content
					.setBackgroundResource(R.drawable.cs_search_bar_list_item_unfocused);
			param_txt_content.setMargins(PADDING, PADDING, PADDING, PADDING);
			txt_content.setLayoutParams(param_txt_content);
			addView(txt_content);
		}

	}

}
