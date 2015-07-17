package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.NumberDialog.NumberListener;

public class PageNavigator extends BaseRelativeLayout {
	private int margin = DisplayManager.GetInstance().changeWidthSize(20);
	private TVButton currentPageNoTextView = null;
	private TextView totalPageTextView = null;
    private PageInputView pageToInputView = null;
    private NumberDialog numberDialog=null;

    private int inputNumber=-1;
    
    
	public void setInputNumber(int inputNumber) {
		this.inputNumber = inputNumber;
	}

	public TVButton getCurrentPageNoTextView() {
		return currentPageNoTextView;
	}

	private int currentPageNo = -1;
	private int totalPageSize = -1;

	private PageNavigatorListener pageNavigatorListener = null;

	private PopupWindow numberInputDialog = null;

	public void setPageNavigatorListener(
			PageNavigatorListener pageNavigatorListener) {
		this.pageNavigatorListener = pageNavigatorListener;
	}

	public PageNavigator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public PageNavigator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PageNavigator(Context context) {
		super(context);
		init(context);
	}

	public void showDialog(Context context){
		numberDialog.setTotalPageSize(totalPageSize);
		numberDialog.show();
		numberDialog.clearNumber();
		if(inputNumber>0){
		numberDialog.setNumber(inputNumber);
		}
			//showNumberDialog(context);
	}
	
	private void init(final Context context) {
		setGravity(Gravity.RIGHT);
		setPadding(0, 0, DisplayManager.GetInstance().changeWidthSize(200), 0);
		
		numberDialog=new NumberDialog(context,R.style.alertDialog);
		numberDialog.setInputLimit(4);
		numberDialog.setNumberListener(new NumberListener() {
			
			@Override
			public void onGotoPage(int pageNo) {
				pageNavigatorListener.onGotoPage(pageNo);
			}

			@Override
			public void onInput(String str) {
				
			}
		});
		
		TVButton preButton = new TVButton(context);
		preButton.setId(ID.MoviePageNavigator.PRE_PAGE_ID);
		preButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				previousPage();
			}
		});
		preButton.setText(context.getString(R.string.movie_navigator_pre));
		preButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
				.GetInstance().changeTextSize(23));
		RelativeLayout.LayoutParams param_preButton = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_preButton.addRule(RelativeLayout.CENTER_VERTICAL);
		param_preButton.rightMargin = margin;
		preButton.setLayoutParams(param_preButton);
		addView(preButton);

		TVButton nextButton = new TVButton(context);
		nextButton.setId(ID.MoviePageNavigator.NEXT_PAGE_ID);
		nextButton.setText(context.getString(R.string.movie_navigator_next));
		nextButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
				.GetInstance().changeTextSize(23));
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				nextPage();
			}
		});
		RelativeLayout.LayoutParams param_nextButton = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_nextButton.addRule(RelativeLayout.CENTER_VERTICAL);
		param_nextButton.addRule(RelativeLayout.RIGHT_OF,
				ID.MoviePageNavigator.PRE_PAGE_ID);
		param_nextButton.rightMargin = margin;
		nextButton.setLayoutParams(param_nextButton);
		addView(nextButton);

		currentPageNoTextView = new TVButton(context);
		currentPageNoTextView.setSingleLine(true);
		currentPageNoTextView.setId(ID.MoviePageNavigator.CURRENT_PAGE_ID);
		currentPageNoTextView.setTextColor(Color.WHITE);
		currentPageNoTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
				DisplayManager.GetInstance().changeTextSize(23));
		RelativeLayout.LayoutParams param_currentPageNoTextView = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT
				, LayoutParams.WRAP_CONTENT);
		param_currentPageNoTextView.addRule(RelativeLayout.CENTER_VERTICAL);
		param_currentPageNoTextView.addRule(RelativeLayout.RIGHT_OF,
				ID.MoviePageNavigator.NEXT_PAGE_ID);
		currentPageNoTextView.setLayoutParams(param_currentPageNoTextView);
		currentPageNoTextView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(context);
			}
		});
		addView(currentPageNoTextView);

		totalPageTextView = new TextView(context);
		totalPageTextView.setId(ID.MoviePageNavigator.TOTAL_PAGE_ID);
		totalPageTextView.setTextColor(Color.WHITE);
		totalPageTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
				DisplayManager.GetInstance().changeTextSize(23));
		RelativeLayout.LayoutParams param_totalPageTextView = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_totalPageTextView.addRule(RelativeLayout.CENTER_VERTICAL);
		param_totalPageTextView.addRule(RelativeLayout.RIGHT_OF,ID.MoviePageNavigator.CURRENT_PAGE_ID);
		param_totalPageTextView.rightMargin = margin;
		totalPageTextView.setLayoutParams(param_totalPageTextView);
		addView(totalPageTextView);
	}


	public void setPageInfo(int pageNo, int totalPageSize) {
		this.currentPageNo = pageNo;
		this.totalPageSize = totalPageSize;
		currentPageNoTextView.setText("" + currentPageNo);
		totalPageTextView.setText("/" + totalPageSize);
	}

	private void nextPage() {
		if (currentPageNo < totalPageSize) {
			currentPageNo++;
			gotoPage(currentPageNo);
		}
	}

	private void previousPage() {
		if (currentPageNo > 1) {
			currentPageNo--;
			gotoPage(currentPageNo);
		}
	}

	private void gotoPage(int pageNo) {
		if (this.pageNavigatorListener != null) {
			pageNavigatorListener.onGotoPage(pageNo);
		}
	}

	public class PageInputView extends TextView {
		public PageInputView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public PageInputView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public PageInputView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
			setSingleLine(true);
			setBackgroundResource(R.drawable.cs_movie_navigator_input_unfocus);
		}

		@Override
		protected void onFocusChanged(boolean focused, int direction,
				Rect previouslyFocusedRect) {
			if (focused) {
				setBackgroundResource(R.drawable.cs_movie_navigator_input_focus);
			} else {
				setBackgroundResource(R.drawable.cs_movie_navigator_input_unfocus);
			}
			super.onFocusChanged(focused, direction, previouslyFocusedRect);
		}
	}


	public interface PageNavigatorListener {
		public void onGotoPage(int pageNo);
	}
}
