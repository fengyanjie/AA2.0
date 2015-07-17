package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;

public class MoneyItemView extends BaseLinearLayout {

	private ImageView img = null;
	private TextView textView = null;

	public MoneyItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MoneyItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MoneyItemView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setOrientation(HORIZONTAL);
		img = new ImageView(context);
		img.setImageResource(R.drawable.c_no);
		addView(img);
		textView = new TextView(context);
		textView.setTextColor(Color.WHITE);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager.GetInstance().changeTextSize(28));
		addView(textView);
	}

	public void setText(String text) {
		textView.setText(text);
	}

	public void setSelected(boolean isSelected) {
		if (isSelected) {
			img.setImageResource(R.drawable.c_yes);
		} else {
			img.setImageResource(R.drawable.c_no);
		}
	}

	public void setFocus(boolean isFocus) {
		if (isFocus) {
			setBackgroundResource(R.drawable.cs_btn_focus);
		} else {
			setBackgroundResource(0);
		}
	}

}
