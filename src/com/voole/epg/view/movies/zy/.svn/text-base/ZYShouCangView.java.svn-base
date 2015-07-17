package com.voole.epg.view.movies.zy;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;

public class ZYShouCangView extends BaseLinearLayout {
	private TextView button = null;
	private static final int PADDING = 6;

	public ZYShouCangView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ZYShouCangView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ZYShouCangView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setGravity(Gravity.CENTER);
		button = new TextView(context);
		LinearLayout.LayoutParams param_button = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		button.setGravity(Gravity.CENTER);
		button.setBackgroundResource(R.drawable.cs_zy_film_btn);
		param_button.setMargins(PADDING, PADDING, PADDING, PADDING);
		button.setLayoutParams(param_button);
		addView(button);
	}
	
	public void isFavorite(String isFavorite){
		if("0".equals(isFavorite)){
			button.setBackgroundResource(R.drawable.cs_zy_film_btn_yes);
		}else{
			button.setBackgroundResource(R.drawable.cs_zy_film_btn);
		}
		
	}

	public void OnSelected(boolean selected) {
		if (selected) {
			setBackgroundResource(R.drawable.cs_shade_button_focuse);
		} else {
			this.setBackgroundResource(0);
		}

	}

}
