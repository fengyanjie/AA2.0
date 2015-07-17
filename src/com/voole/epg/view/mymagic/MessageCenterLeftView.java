package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.view.mymagic.MessageCenterLeftView.MessageCenrerLeftItemView.CurrentState;

public class MessageCenterLeftView extends BaseLinearLayout {
	private static final int NOTIFICATION = 1;
	private static final int ABOUTMAGIC = 2;

	private static final int ITEM_SIZE = 2;
	private static final int ITEM_TOTAL = 8;

	private int currentSelected = 0; // 当前选中
	private int currentFocused = -1; // 当前焦点
	private MessageCenrerLeftItemView[] itemViews = null;
	private static final int[] itemNames = { R.string.mymagic_mc_notification,
			R.string.mymagic_mc_recharge_aboutmagic, };
	private static final int[] itemID = { NOTIFICATION, ABOUTMAGIC, };

	protected MessageCenrerLeftItemViewSelectedListener listener = null;

	public void setMessageCenrerLeftItemViewSelectedListener(
			MessageCenrerLeftItemViewSelectedListener listener) {
		this.listener = listener;
	}

	public MessageCenterLeftView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setBackgroundResource(R.drawable.cs_mymagic_consumer_left_bg);
		setOrientation(VERTICAL);
		init(context);
		updateStatuesUI(currentSelected, currentFocused);
	}

	private void init(Context context) {

		setPadding(0, 30, 0, 30);
		itemViews = new MessageCenrerLeftItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_content_item = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1);
		param_content_item.topMargin = 2;
		TextView textView = null;
		param_content_item.gravity = Gravity.CENTER_HORIZONTAL;
		for (int i = 0; i < ITEM_TOTAL; i++) {
			if (i < ITEM_SIZE) {
				itemViews[i] = new MessageCenrerLeftItemView(context);
				itemViews[i].setLayoutParams(param_content_item);
				itemViews[i].setText(itemNames[i]);
				itemViews[i].setTag(itemID[i]);
				addView(itemViews[i]);
			} else {
				textView = new TextView(context);
				textView.setLayoutParams(param_content_item);
				addView(textView);
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
	private void updateStatuesUI(int currentSelected, int currentFocused) {
		for (int i = 0; i < ITEM_SIZE; i++) {
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (currentFocused < ITEM_SIZE - 1) {
				currentFocused++;
				updateStatuesUI(currentSelected, currentFocused);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			if (currentFocused > 0) {
				currentFocused--;
				updateStatuesUI(currentSelected, currentFocused);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (currentFocused >= 0 && currentFocused <= ITEM_SIZE - 1) {
				currentSelected = currentFocused;
				updateStatuesUI(currentSelected, currentFocused);
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
			currentFocused = 0;
			updateStatuesUI(currentSelected, currentFocused);
		} else {
			updateStatuesUI(currentSelected, -1);
		}
	}

	/**
	 * item
	 * 
	 * @author Administrator
	 * 
	 */
	public static class MessageCenrerLeftItemView extends TextView {

		private Drawable unselected_haFocus_Drawable = getResources()
				.getDrawable(
						R.drawable.cs_mymagic_consumer_left_unselected_focused);
		private Drawable selected_noFocus_Drawable = getResources()
				.getDrawable(
						R.drawable.cs_mymagic_consumer_left_selected_unfocus);
		private Drawable selected_hasFocus_Drawable = getResources()
				.getDrawable(
						R.drawable.cs_mymagic_consumer_left_selected_hasfocus);

		public enum CurrentState {
			unselected_noFocus, unselected_haFocus, selected_noFocus, selected_hasFocus
		}

		private CurrentState currentStatue = CurrentState.unselected_haFocus;

		public MessageCenrerLeftItemView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setGravity(Gravity.CENTER);
			setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
			refresh();
			setStates(CurrentState.selected_hasFocus);
		}

		public void setStates(CurrentState currentState) {
			this.currentStatue = currentState;
			refresh();
		}

		private void refresh() {
			switch (currentStatue) {
			case unselected_noFocus:
				setBackgroundDrawable(null);
				setTextColor(Color.WHITE);
				break;
			case unselected_haFocus:
				setBackgroundDrawable(unselected_haFocus_Drawable);
				setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
				break;
			case selected_noFocus:
				setBackgroundDrawable(selected_noFocus_Drawable);
				setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
				break;
			case selected_hasFocus:
				setBackgroundDrawable(selected_hasFocus_Drawable);
				setTextColor(Color.WHITE);
				break;
			default:
				break;
			}
		}
	}

	public interface MessageCenrerLeftItemViewSelectedListener {
		public void onItemSelected(MessageCenrerLeftItemView leftItemView);
	}
}
