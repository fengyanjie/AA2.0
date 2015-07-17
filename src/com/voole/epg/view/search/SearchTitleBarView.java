package com.voole.epg.view.search;

import java.util.List;

import android.content.Context;
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
import com.voole.tvutils.BitmapUtil;

public class SearchTitleBarView extends BaseLinearLayout {
	private static final int ITEM_SIZE = 5;
	private SearcheBarListItemView[] itemViews = null;
	private TextView inputText = null;
//	private ShadeButton searchBtn = null;
	private LinearLayout layout_list = null;

	private BitmapDrawable listItemDrawable = new BitmapDrawable(BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_result_item_bg_focused));

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
		BitmapDrawable drawable = new BitmapDrawable(BitmapUtil.readBitmap(context, R.drawable.cs_search_bar_bg));
		setBackgroundDrawable(drawable);

		/*LinearLayout layout_bar = new LinearLayout(context);
		layout_bar.setOrientation(HORIZONTAL);
		LinearLayout.LayoutParams param_layout_bar = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 16);
		param_layout_bar.topMargin = DisplayManager.GetInstance()
				.changeHeightSize(20);
		param_layout_bar.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		param_layout_bar.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(15);
		layout_bar.setLayoutParams(param_layout_bar);*/

		inputText = new TextView(context);
		inputText.setHint("请按影片首字母搜索");
		inputText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (listener != null && s != null && !s.toString().equals("")) {
					listener.onInput(s.toString());
				} else {
					hideList();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		inputText
				.setBackgroundResource(R.drawable.cs_search_title_bar_bg_unfocused);
		inputText.setGravity(Gravity.CENTER_VERTICAL);
		inputText.setMaxLines(1);
		inputText.setTextColor(Color.WHITE);
		inputText.setPadding(DisplayManager.GetInstance().changeWidthSize(25),
				0, 0, 0);
		inputText.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(28));
		LinearLayout.LayoutParams param_inputText = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,16);
		param_inputText.topMargin = DisplayManager.GetInstance()
				.changeHeightSize(20);
		param_inputText.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		param_inputText.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		inputText.setLayoutParams(param_inputText);
//		layout_bar.addView(inputText);
		addView(inputText);

		/*searchBtn = new ShadeButton(context);
		searchBtn.setText("搜索");
		searchBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null && inputText.getText().toString() != null) {
					listener.onSearch(inputText.getText().toString());
					hideList();
				}
			}
		});
		searchBtn.setTextSize(DisplayManager.GetInstance().changeTextSize(26));
		LinearLayout.LayoutParams param_searchBtn = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 10);
		param_searchBtn.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(10);
		searchBtn.setLayoutParams(param_searchBtn);
		layout_bar.addView(searchBtn);*/

//		addView(layout_bar);

		/*LinearLayout layout_down = new LinearLayout(context);
		layout_down.setOrientation(HORIZONTAL);
		LinearLayout.LayoutParams param_layout_down = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
		param_layout_down.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		param_layout_down.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(10);
		param_layout_down.topMargin = -5;
		layout_down.setLayoutParams(param_layout_down);*/

		layout_list = new LinearLayout(context);
		layout_list.setOrientation(LinearLayout.VERTICAL);
		layout_list.setBackgroundResource(R.drawable.cs_search_bar_list_bg);
		LinearLayout.LayoutParams param_layout_list = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
		param_layout_list.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		param_layout_list.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
//		param_layout_list.topMargin = -5;
		layout_list.setLayoutParams(param_layout_list);
		itemViews = new SearcheBarListItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_item.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		param_item.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(5);
		param_item.topMargin = DisplayManager.GetInstance().changeHeightSize(5);
		param_item.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(5);
		for (int i = 0; i < ITEM_SIZE; i++) {
			itemViews[i] = new SearcheBarListItemView(context);
			itemViews[i].setLayoutParams(param_item);
			final int index = i;
			itemViews[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) {
						listener.onSelected(itemViews[index].getText()
								.toString());
						inputText
								.setText(itemViews[index].getText().toString());
						hideList();
					}
				}
			});
			layout_list.addView(itemViews[i]);
		}
		addView(layout_list);
		hideList();
	}

	public void showList(List<String> relatedKeyWords) {
		if (relatedKeyWords != null && relatedKeyWords.size() > 0) {
			int allSize = relatedKeyWords.size();
			for (int i = 0; i < allSize; i++) {
				itemViews[i].setText(relatedKeyWords.get(i));
			}
		}
		layout_list.setVisibility(View.VISIBLE);
	}

	public void hideList() {
		layout_list.setVisibility(View.INVISIBLE);
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
			setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(28));
		}

		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			if (gainFocus) {
				setBackgroundDrawable(listItemDrawable);
			} else {
				setBackgroundDrawable(null);
			}
		}
	}
	
	public interface SearchBarListener {
		public void onSearch(String keyword);

		public void onSelected(String string);

		public void onInput(String string);
	}
}
