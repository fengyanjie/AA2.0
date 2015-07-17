package com.voole.epg.view.newsearch;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ShadeButton;
import com.voole.tvutils.BitmapUtil;
import com.voole.tvutils.ImageUtil;

public class SearchTitleBarView extends BaseLinearLayout {
	private static final int ITEM_SIZE = 5;
	private SearcheBarListItemView[] itemViews = null;
	private TextView inputText = null;
	private ShadeButton searchBtn = null;

	private SearchBarListener listener = null;

	public void setOnSearchBarListener(SearchBarListener listener) {
		this.listener = listener;
	}

	public SearchTitleBarView(Context context) {
		super(context);
		init(context);
	}

	public SearchTitleBarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public SearchTitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		setOrientation(VERTICAL);
        BitmapDrawable drawable = new BitmapDrawable(context.getResources(),BitmapUtil.readBitmap(context, R.drawable.cs_search_bar_new_bg));
 		setBackgroundDrawable(drawable);
		
		LinearLayout layout_bar = new LinearLayout(context);
		layout_bar.setOrientation(HORIZONTAL);
		layout_bar.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams param_layout_bar = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_layout_bar.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		param_layout_bar.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(15);
		layout_bar.setLayoutParams(param_layout_bar); 

		inputText = new TextView(context);
		inputText.setHint("请按影片首字母搜索");
		inputText.setSingleLine(true);
		inputText
				.setBackgroundResource(R.drawable.cs_search_title_bar_bg_unfocused);
		inputText.setGravity(Gravity.CENTER_VERTICAL);
		inputText.setMaxLines(1);
		inputText.setTextColor(Color.WHITE);
		inputText.setPadding(DisplayManager.GetInstance().changeWidthSize(25),
				0, DisplayManager.GetInstance().changeWidthSize(25), 0);
		inputText.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(28));
		LinearLayout.LayoutParams param_inputText = new LinearLayout.LayoutParams(
				400, LayoutParams.WRAP_CONTENT);
		param_inputText.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		param_inputText.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		inputText.setLayoutParams(param_inputText);
		layout_bar.addView(inputText);
		//addView(inputText);

		searchBtn = new ShadeButton(context);
		searchBtn.setText("清空");
		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				clearInputText();
			}
		});
		searchBtn.setTextSize(DisplayManager.GetInstance().changeTextSize(26));
		LinearLayout.LayoutParams param_searchBtn = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_searchBtn.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(10);
		searchBtn.setLayoutParams(param_searchBtn);
		layout_bar.addView(searchBtn);
 		addView(layout_bar);
		
	}

 

	 

	public void addInputText(String string) {
		inputText.setText(inputText.getText().toString() + string);
	}

	public void deleteInputText() {
		String string = inputText.getText().toString();
		if (string != null && !"".equals(string) && string.length() > 0) {
			inputText.setText(string.substring(0, string.length() - 1));
		}
	}

	public void clearInputText() {
		inputText.setText("");
	}
	

	public String getSearchText() {
		return inputText.getText().toString();
	}

	private class SearcheBarListItemView extends TextView {

		private final int PADDING = DisplayManager.GetInstance()
				.changeWidthSize(20);

		public SearcheBarListItemView(Context context) {
			super(context);
			init(context);
		}

		public SearcheBarListItemView(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public SearcheBarListItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		private void init(Context context) {
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);

			setGravity(Gravity.CENTER_VERTICAL);
			setTextColor(Color.WHITE);
			setPadding(PADDING, 0, 0, 0);
			setTextSize(DisplayManager.GetInstance().changeTextSize(28));
		}

		 
	}
	
	public interface SearchBarListener {
		public void onSearch(String keyword);

		public void onSelected(String string);

		public void onInput(String string);
	}
	
}
