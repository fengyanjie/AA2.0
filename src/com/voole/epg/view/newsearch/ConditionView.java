package com.voole.epg.view.newsearch;

import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.newsearch.ConditionItemView.CurrentState;

public class ConditionView extends BaseLinearLayout {

	private ConditionItemView[] conditionItemViews = null;
	private int ITEM_SIZE = 9;

	private ConditionViewListener conditionViewListener = null;

	private int currentIndex = -1;
	private int currentDisplay = 0;
	
	private List<FilmClass> conditionItems=null;

	public void setConditionViewListener(
			ConditionViewListener conditionViewListener) {
		this.conditionViewListener = conditionViewListener;
	}

	public interface ConditionViewListener {
		public void onItemSelected(String id);
	}

	public void setData(List<FilmClass> items) {
		LogUtil.d("ConditionView---->" + items.size());
		conditionItems=items;
		int len = conditionItems.size();
		for (int i = 0; i < len; i++) {
			conditionItemViews[i] .setText(conditionItems.get(i).getFilmClassName());
			conditionItemViews[i] .setVisibility(View.VISIBLE);
		}
        if(len<ITEM_SIZE){
        	for (int i = len; i < ITEM_SIZE; i++) {
        	conditionItemViews[i] .setVisibility(View.GONE);
           }
        }
	}

	public ConditionView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ConditionView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ConditionView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOrientation(HORIZONTAL);
		setFocusable(true);
		setFocusableInTouchMode(true);

		setGravity(Gravity.CENTER);
		conditionItemViews = new ConditionItemView[ITEM_SIZE];
		LinearLayout.LayoutParams params = new LayoutParams(DisplayManager.GetInstance().changeWidthSize(100),
				LayoutParams.WRAP_CONTENT);
		params.leftMargin = 10;
		params.rightMargin = 10;
		for (int i = 0; i < ITEM_SIZE; i++) {
			conditionItemViews[i] = new ConditionItemView(mContext);
			conditionItemViews[i].setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
			conditionItemViews[i].setLayoutParams(params);
			addView(conditionItemViews[i]);
		}
		updateStatuesUI(currentDisplay, currentIndex);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if (gainFocus) {
			currentIndex = 0;
			updateStatuesUI(currentDisplay, currentIndex);
		} else {
			updateStatuesUI(currentDisplay, -1);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	/**
	 * @param currentSelected
	 *            选中
	 * @param currentFocused
	 *            焦点
	 */
	private void updateStatuesUI(int currentSelected, int currentFocused) {
		for (int i = 0; i < ITEM_SIZE; i++) {
			if (currentSelected == i) {
				if (currentFocused == i) {
					conditionItemViews[i]
							.setStates(CurrentState.selected_hasFocus);
				} else {
					conditionItemViews[i]
							.setStates(CurrentState.selected_noFocus);
				}
			} else {
				if (currentFocused == i) {
					conditionItemViews[i]
							.setStates(CurrentState.unselected_haFocus);
				} else {
					conditionItemViews[i]
							.setStates(CurrentState.unselected_noFocus);
				}
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (currentIndex > 0) {
				currentIndex--;
				updateStatuesUI(currentDisplay, currentIndex);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (currentIndex < ITEM_SIZE - 1) {
				currentIndex++;
				updateStatuesUI(currentDisplay, currentIndex);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			if (currentIndex >= 0 && currentIndex <= ITEM_SIZE - 1) {
				currentDisplay = currentIndex;
				updateStatuesUI(currentDisplay, currentIndex);
				if(conditionViewListener!=null){
					conditionViewListener.onItemSelected(conditionItems.get(currentIndex).getChannelId());
				}
			}
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
