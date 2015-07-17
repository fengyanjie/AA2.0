package com.voole.epg.base.common;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.voole.epg.R;

public class NumberButton extends ShadeButton {

	public NumberButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public NumberButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NumberButton(Context context) {
		super(context);
	}

	@Override
	protected void initImg() {
		PADDING = 0;
		TEXT_SIZE=DisplayManager.GetInstance().changeTextSize(26);
		button_bg = R.drawable.cs_navigation_number_unfocuse;
		main_bg = R.drawable.cs_navigation_main_unfocuse;
		param_button = new LinearLayout.LayoutParams(DisplayManager.GetInstance().changeWidthSize(80-12),
				DisplayManager.GetInstance().changeHeightSize(70-12));
	}

}
