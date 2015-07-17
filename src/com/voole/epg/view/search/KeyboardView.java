package com.voole.epg.view.search;

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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.tvutils.BitmapUtil;

public class KeyboardView extends BaseLinearLayout {
	private static final int LINE_SIZE = 5;
	private static final int LINE_ELEMENT_SIZE = 8;
	private static final int ELEMENT_SIZE = 36;
	private static final int LAST_LINE_ELEMENT_SIZE = 7;

	private LinearLayout[] layout_line = null;
	private KeyboardElementView[] elementViews = null;
	private KeyboardButton deleteBtn = null;
	private KeyboardButton clearBtn = null;
	private KeyboardButton searchBtn = null;

	private Bitmap eleFocused = BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_keyboard_ele_bg_focused);

	private Bitmap eleUnfocuse = BitmapUtil.readBitmap(getContext(), R.drawable.cs_search_keyboard_ele_bg_unfocused);

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

		elementViews = new KeyboardElementView[ELEMENT_SIZE];
		layout_line = new LinearLayout[LINE_SIZE];

		LinearLayout.LayoutParams param_layout_line = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);

		LinearLayout.LayoutParams param_elementView_front = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);

		LinearLayout.LayoutParams param_elementView_later = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 35);

		LinearLayout.LayoutParams param_button = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 34);

		LinearLayout.LayoutParams param_button_search = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 32);

		for (int i = 0; i < LINE_SIZE; i++) {
			layout_line[i] = new LinearLayout(context);
			layout_line[i].setOrientation(HORIZONTAL);
			layout_line[i].setLayoutParams(param_layout_line);
			if (i < LINE_SIZE - 1) {
				for (int j = 0; j < LINE_ELEMENT_SIZE; j++) {
					final int index = j + i*LINE_ELEMENT_SIZE;
					elementViews[index] = new KeyboardElementView(context);
					elementViews[index].setLayoutParams(param_elementView_front);
					elementViews[index].setText("" + matrix[index]);
					elementViews[index].setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							if (listener != null) {
								listener.onKey("" + matrix[index]);
							}
						}
					});
					layout_line[i].addView(elementViews[index]);
				}
			}else {
				for (int j = 0; j < LAST_LINE_ELEMENT_SIZE; j++) {
					final int index = j + i*LINE_ELEMENT_SIZE;
					if (j < LINE_ELEMENT_SIZE/2) {
						elementViews[index] = new KeyboardElementView(context);
						elementViews[index].setLayoutParams(param_elementView_later);
						elementViews[index].setText("" + matrix[index]);
						elementViews[index].setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								if (listener != null) {
									listener.onKey("" + matrix[index]);
								}
							}
						});
						layout_line[i].addView(elementViews[index]);
					}else if(j < LAST_LINE_ELEMENT_SIZE - 2){
						deleteBtn = new KeyboardButton(context);
						deleteBtn.setLayoutParams(param_button);
						deleteBtn.setText("删除");
						deleteBtn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								if (listener != null) {
									listener.onDelete();
								}
							}
						});
						layout_line[i].addView(deleteBtn);
					}else if (j < LAST_LINE_ELEMENT_SIZE - 1) {
						clearBtn = new KeyboardButton(context);
						clearBtn.setLayoutParams(param_button);
						clearBtn.setText("清空");
						clearBtn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								if (listener != null) {
									listener.onClear();
								}
							}
						});
						layout_line[i].addView(clearBtn);
					}else if (j < LAST_LINE_ELEMENT_SIZE) {
						searchBtn = new KeyboardButton(context);
						searchBtn.setLayoutParams(param_button_search);
						searchBtn.setText("搜索");
						searchBtn.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								if (listener != null) {
									listener.onSearch();
								}
							}
						});
						layout_line[i].addView(searchBtn);
					}
				}
			}
			addView(layout_line[i]);
		}
	}

	public void setFocusedToA(){
		elementViews[0].requestFocus();
	}

	public class KeyboardElementVieww extends SurfaceView implements SurfaceHolder.Callback{

		private Paint paint = new Paint();
		private boolean isFocused = false;
		private String string = "";
		private SurfaceHolder Holder=null;
		public KeyboardElementVieww(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			init();
		}

		
		public KeyboardElementVieww(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}

		public KeyboardElementVieww(Context context) {
			super(context);
			init();
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {
			
		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			
		}

		@Override
		public void surfaceDestroyed(SurfaceHolder holder) {
			
		}
		
		private void init(){
			Holder = this.getHolder(); 
	        Holder.addCallback(this);
		}
		
		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
			
		}
		
		
		
	}
	
	public class KeyboardElementView extends BaseView {

		private final int TEXT_SIZE = DisplayManager.GetInstance().changeWidthSize(28);
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
			paint.setAntiAlias(true);
			paint.setTextSize(TEXT_SIZE);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			if (isFocused) {
				paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				paint.setTextSize(DisplayManager.GetInstance().changeWidthSize(40));
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
			super.onDraw(canvas);
		}

		@Override
		protected void onFocusChanged(boolean focused, int direction,
				Rect previouslyFocusedRect) {
			isFocused = focused;
			invalidate();
		}
	}

	public class KeyboardButton extends BaseView{
		private final int TEXT_SIZE = DisplayManager.GetInstance().changeWidthSize(28);
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
			paint.setAntiAlias(true);
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
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			if (isFocused) {
				paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				paint.setTextSize(DisplayManager.GetInstance().changeWidthSize(36));
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
			super.onDraw(canvas);
		}
	}

	public interface KeyboardListener{
		public void onKey(String text);
		public void onDelete();
		public void onClear();
		public void onSearch();
	}

}
