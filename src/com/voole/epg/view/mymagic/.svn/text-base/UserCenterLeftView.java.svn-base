package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemView.CurrentState;
import com.voole.tvutils.BitmapUtil;

public class UserCenterLeftView extends BaseLinearLayout {
	private int itemSize = 4;
	private static final int ITEM_TOTAL = 7;

	private int currentSelected = -1; // 当前选中
	private int currentFocused = -1; // 当前焦点
	private UserCenterLeftItemView[] itemViews = null;

	protected UserCenterLeftItemSelectedListener listener = null;

	public void setUserCenterLeftItemSelectedListener(
			UserCenterLeftItemSelectedListener listener) {
		this.listener = listener;
	}

	public UserCenterLeftView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		BitmapDrawable drawable = new BitmapDrawable(getResources(),BitmapUtil.readBitmap(context, R.drawable.cs_mymagic_consumer_left_bg));
		setBackgroundDrawable(drawable);
		setOrientation(VERTICAL);
		init(context);
		updateStatuesUI();
	}
	
	public UserCenterLeftView(Context context,int itemSize) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		BitmapDrawable drawable = new BitmapDrawable(getResources(),BitmapUtil.readBitmap(context, R.drawable.cs_mymagic_consumer_left_bg));
		setBackgroundDrawable(drawable);
		setOrientation(VERTICAL);
		this.itemSize = itemSize;
		
		init(context);
		updateStatuesUI();
	}

	private void init(Context context) {
		setPadding(0, 30, 0, 30);
		itemViews = new UserCenterLeftItemView[ITEM_TOTAL];
		LinearLayout.LayoutParams param_content_item = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_content_item.topMargin = 2;
		param_content_item.leftMargin=1;
		param_content_item.gravity = Gravity.CENTER_HORIZONTAL;
		
		for (int i = 0; i < ITEM_TOTAL; i++) {
			itemViews[i] = new UserCenterLeftItemView(context);
			itemViews[i].setLayoutParams(param_content_item);
			addView(itemViews[i]);
		}
	}
	
	public void setSelectedIndex(int index){
		this.currentSelected = index;
		updateStatuesUI();
	}
	
	public void setItemSize(int itemSize){
		this.itemSize = itemSize;
	}
	
	public void setData(String[]itemNames){
		if (itemNames.length > 0) {
			this.itemSize = itemNames.length;
			for (int i = 0; i < ITEM_TOTAL; i++) {
				if (i < itemNames.length) {
					itemViews[i].setText(itemNames[i]);
					itemViews[i].setTag(i);
				}else {
					itemViews[i].setVisibility(INVISIBLE);
				}
			}
		}
	}
	
	public void setData(int[] resIds){
		if (resIds.length > 0) {
			this.itemSize = resIds.length;
			for (int i = 0; i < ITEM_TOTAL; i++) {
				if (i < resIds.length) {
					itemViews[i].setText(resIds[i]);
					itemViews[i].setTag(i);
				}else {
					itemViews[i].setVisibility(INVISIBLE);
				}
			}
		}
	}

	/**
	 * 
	 * @param currentSelected
	 *            选中
	 * @param currentFocused
	 *            焦点
	 */
	private void updateStatuesUI() {
		for (int i = 0; i < itemSize; i++) {
			if (currentSelected == i) {
				if (currentFocused == i) {
					itemViews[i].setStates(CurrentState.selected_hasFocus);
				} else {
					itemViews[i].setStates(CurrentState.selected_noFocus);
				}
			} else {
				if (currentFocused == i) {
					itemViews[i].setStates(CurrentState.unselected_haFocus);
				} else {
					itemViews[i].setStates(CurrentState.unselected_noFocus);
				}
			}
		}
	}
	
	private void clearFocusUI() {
		for (int i = 0; i < itemSize; i++) {
			if (currentSelected == i) {
				itemViews[i].setStates(CurrentState.selected_noFocus);
			}else{
				itemViews[i].setStates(CurrentState.unselected_noFocus);
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (currentFocused < itemSize - 1) {
				currentFocused++;
				updateStatuesUI();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			if (currentFocused > 0) {
				currentFocused--;
				updateStatuesUI();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (currentFocused >= 0 && currentFocused <= itemSize - 1) {
				currentSelected = currentFocused;
				updateStatuesUI();
				// 接口回调
				if (listener != null) {
					listener.onItemSelected(itemViews[currentSelected]);
				}
			}
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if (gainFocus) {
			currentFocused = currentSelected;
			updateStatuesUI();
		} else {
			clearFocusUI();
		}
	}

	/**
	 * item
	 * 
	 * @author Administrator
	 * 
	 */
	public static class UserCenterLeftItemView extends BaseView {		
		private Bitmap unselected_haFocus_Drawable = BitmapUtil.readBitmap(getContext(), R.drawable.cs_mymagic_consumer_left_unselected_focused);
		private Bitmap selected_noFocus_Drawable = BitmapUtil.readBitmap(getContext(), R.drawable.cs_mymagic_consumer_left_selected_unfocus);
		private Bitmap selected_hasFocus_Drawable = BitmapUtil.readBitmap(getContext(), R.drawable.cs_mymagic_consumer_left_selected_hasfocus);
		
		private Paint paint = new Paint();
		private String string = "";

		public enum CurrentState {
			unselected_noFocus, unselected_haFocus, selected_noFocus, selected_hasFocus
		}

		private CurrentState currentStatue = CurrentState.unselected_haFocus;
		private Rect rect;

		public UserCenterLeftItemView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			paint.setStrokeWidth(0);
			paint.setTextAlign(Align.CENTER);
		    paint.setStyle(Paint.Style.FILL);
		    paint.setTextSize(DisplayManager.GetInstance().changeWidthSize(28));
		    paint.setAntiAlias(true);
			
			setStates(CurrentState.selected_hasFocus);
		}
		
		public void setText(String string){
			this.string = string;
		}
		
		public void setText(int resID){
			this.string = getContext().getResources().getString(resID);
		}

		public void setStates(CurrentState currentState) {
			this.currentStatue = currentState;
			invalidate();
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			switch (currentStatue) {
			case unselected_noFocus:
				paint.setColor(Color.WHITE);
				break;
			case unselected_haFocus:
				//paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				paint.setColor(Color.WHITE);
				canvas.drawBitmap(unselected_haFocus_Drawable, null, rect, null);
				break;
			case selected_noFocus:
				//paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				paint.setColor(Color.WHITE);
				canvas.drawBitmap(selected_noFocus_Drawable, null, rect, null);
				break;
			case selected_hasFocus:
				paint.setColor(Color.WHITE);
				canvas.drawBitmap(selected_hasFocus_Drawable, null, rect, null);
				break;
			}
			FontMetrics fontMetrics = paint.getFontMetrics();
        	float fontHeight = fontMetrics.bottom - fontMetrics.top; 
        	float textBaseY = rect.height() - (rect.height() - fontHeight) / 2 - fontMetrics.bottom; 
			canvas.drawText(string, rect.width()/2, textBaseY, paint);
		}
	}

	public interface UserCenterLeftItemSelectedListener {
		public void onItemSelected(UserCenterLeftItemView leftItemView);
	}

}
