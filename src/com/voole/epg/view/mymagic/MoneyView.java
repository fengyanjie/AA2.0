package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;

public class MoneyView extends BaseLinearLayout {

	private MoneyItemView[] moneyItemViews = null;
	private int ITEM_SIZE = 3;
	private String[] money_str = { "￥50", "￥200", "￥400" };
	private String[] money_int = { "50", "200", "400" };
	private int currentSelectedIndex = -1;
	private boolean isClear=false;
	private boolean isRadio=false;
	
	private String price="";

	public interface MoneyItemSelectListener{
		public void onSelected();
	}
	
	private MoneyItemSelectListener moneyItemSelectListener=null;
	
	public void setMoneyItemSelectListener(
			MoneyItemSelectListener moneyItemSelectListener) {
		this.moneyItemSelectListener = moneyItemSelectListener;
	}

	public MoneyView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MoneyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MoneyView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setOrientation(HORIZONTAL);
		setFocusable(true);
		setFocusableInTouchMode(true);
		currentSelectedIndex = 0;
		// LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		// LinearLayout.LayoutParams.WRAP_CONTENT,
		// LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(140,
				50);
		params.rightMargin = DisplayManager.GetInstance().changeWidthSize(80);
		moneyItemViews = new MoneyItemView[ITEM_SIZE];
		for (int i = 0; i < ITEM_SIZE; i++) {
			moneyItemViews[i] = new MoneyItemView(context);
			moneyItemViews[i].setText(money_str[i]);
			moneyItemViews[i].setGravity(Gravity.CENTER);
			moneyItemViews[i].setLayoutParams(params);
			addView(moneyItemViews[i]);
		}

	}

	
	public String getPrice(){
		return price;
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {

		if (gainFocus) {
			moneyItemViews[currentSelectedIndex].setFocus(true);
		} else {
			moneyItemViews[currentSelectedIndex].setFocus(false);
			if(isClear){
				isClear=false;
				for (int i = 0; i < ITEM_SIZE; i++) {
						moneyItemViews[i].setSelected(false);
				}
			}
		}

		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (currentSelectedIndex > 0) {
				moneyItemViews[currentSelectedIndex].setFocus(false);
				currentSelectedIndex = currentSelectedIndex - 1;
				moneyItemViews[currentSelectedIndex].setFocus(true);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (currentSelectedIndex < ITEM_SIZE - 1) {
				moneyItemViews[currentSelectedIndex].setFocus(false);
				currentSelectedIndex = currentSelectedIndex + 1;
				moneyItemViews[currentSelectedIndex].setFocus(true);
				return true;
			}else if(currentSelectedIndex==2){
				isRadio=false;
				isClear=true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (currentSelectedIndex >= 0 || currentSelectedIndex < 3) {
				for (int i = 0; i < ITEM_SIZE; i++) {
					if (i != currentSelectedIndex) {
						moneyItemViews[i].setSelected(false);
					}
				}
				moneyItemViews[currentSelectedIndex].setSelected(true);
				if(moneyItemSelectListener!=null){
					moneyItemSelectListener.onSelected();
				}
				isRadio=true;
				price=money_int[currentSelectedIndex];
				return true;
			}
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
}
