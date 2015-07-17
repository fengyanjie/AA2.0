package com.voole.epg.view.newsearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.tvutils.BitmapUtil;

public class KeyboardView extends BaseLinearLayout {
	private SearchTitleBarView searchBarView=null;
	private static final int LINE_SIZE = 3;
	private static final int LINE_ELEMENT_SIZE = 12;
	private static final int ELEMENT_SIZE = 36;
	private static final int TEXT_SIZE = 28;

	private LinearLayout[] layout_line = null;
	private KeyboardElementView[] elementViews = null;

	private Bitmap eleFocused = BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_key_focus);

	private Bitmap eleUnfocuse = BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_key_unfocus);

	private Bitmap btnFocused = BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_keyboard_bt_bg_focused);
	private Bitmap btnUnfocused = BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_keyboard_bt_bg_unfocused);

	private KeyboardListener listener = null;
	public void setOnKeyboardListener(KeyboardListener listener){
		this.listener = listener;
	}


	private static final char[] matrix = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
		'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', };



	public KeyboardView(Context context) {
		super(context);
		init(context);
	}

	public KeyboardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public KeyboardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		this.setOrientation(VERTICAL);
		setGravity(Gravity.CENTER);
		searchBarView=new SearchTitleBarView(mContext);
		elementViews = new KeyboardElementView[ELEMENT_SIZE];
		layout_line = new LinearLayout[LINE_SIZE];
		LinearLayout.LayoutParams param_elementView_bar = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT,1);
		searchBarView.setLayoutParams(param_elementView_bar);
		addView(searchBarView);
		LinearLayout.LayoutParams param_layout_line = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		LinearLayout.LayoutParams param_elementView_front = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);

		for (int i = 0; i < LINE_SIZE; i++) {
			layout_line[i] = new LinearLayout(context);
			layout_line[i].setOrientation(HORIZONTAL);
			layout_line[i].setLayoutParams(param_layout_line);
				for (int j = 0; j < LINE_ELEMENT_SIZE; j++) {
					final int index = j + i*LINE_ELEMENT_SIZE;
					elementViews[index] = new KeyboardElementView(context);
					elementViews[index].setLayoutParams(param_elementView_front);
					elementViews[index].setText("" + matrix[index]);
					elementViews[index].setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							searchBarView.addInputText(""+matrix[index]);
							if (listener != null) {
								listener.onKey(searchBarView.getSearchText());
							}
						}
					});
					layout_line[i].addView(elementViews[index]);
				}
				addView(layout_line[i]);
			} 
		setFocusedToA();
	}

	public void setFocusedToA(){
		elementViews[0].requestFocus();
	}

	public class KeyboardElementView extends BaseView {

		private Paint paint = new Paint();
		private boolean isFocused = false;
		private String string = "";
		private Rect rect;

		public KeyboardElementView(Context context) {
			super(context);
			init(context);
		}

		public KeyboardElementView(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public KeyboardElementView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		private void setText(String text){
			string = text;
		}

		private void init(Context context) {
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);

			paint.setStrokeWidth(0);
			paint.setTextAlign(Align.CENTER);
			paint.setStyle(Paint.Style.FILL);  
			paint.setTextSize(TEXT_SIZE);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			if (isFocused) {
				paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				paint.setTextSize(TEXT_SIZE);
				canvas.drawBitmap(eleFocused, null, rect, null);
			}else {
				paint.setColor(Color.WHITE);
				paint.setTextSize(TEXT_SIZE);
				canvas.drawBitmap(eleUnfocuse, null, rect, null);
			}
			FontMetrics fontMetrics = paint.getFontMetrics();
			float fontHeight = fontMetrics.bottom - fontMetrics.top; 
			float textBaseY = rect.height() - (rect.height() - fontHeight) / 2 - fontMetrics.bottom; 
			canvas.drawText(string, rect.width()/2, textBaseY, paint);
		}

		@Override
		protected void onFocusChanged(boolean focused, int direction,
				Rect previouslyFocusedRect) {
			isFocused = focused;
			invalidate();
		}
	}

	public class KeyboardButton extends BaseView{
		private Paint paint = new Paint();
		private boolean isFocused = false;
		private String string = "";
		private Rect rect;

		public KeyboardButton(Context context) {
			super(context);
			init(context);
		}

		public KeyboardButton(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public KeyboardButton(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		private void setText(String text){
			string = text;
		}

		private void init(Context context){
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);

			paint.setStrokeWidth(0);
			paint.setTextAlign(Align.CENTER);
			paint.setStyle(Paint.Style.FILL);  
			paint.setTextSize(TEXT_SIZE);
		}

		@Override
		protected void onFocusChanged(boolean focused, int direction,
				Rect previouslyFocusedRect) {
			isFocused = focused;
			invalidate();
		}

		@SuppressLint("DrawAllocation")
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			if (isFocused) {
				paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				paint.setTextSize(36);
				canvas.drawBitmap(btnFocused, null, rect, null);
			}else {
				paint.setColor(Color.WHITE);
				paint.setTextSize(TEXT_SIZE);
				canvas.drawBitmap(btnUnfocused, null, rect, null);
			}
			FontMetrics fontMetrics = paint.getFontMetrics();
			float fontHeight = fontMetrics.bottom - fontMetrics.top; 
			float textBaseY = rect.height() - (rect.height() - fontHeight) / 2 - fontMetrics.bottom; 
			canvas.drawText(string, rect.width()/2, textBaseY, paint);
		}
	}

	public interface KeyboardListener{
		public void onKey(String text);
		public void onDelete();
		public void onClear();
		public void onSearch();
	}

}
