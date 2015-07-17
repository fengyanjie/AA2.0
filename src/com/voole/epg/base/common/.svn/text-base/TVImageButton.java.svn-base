package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import com.voole.epg.R;

public class TVImageButton extends TextView{

	public TVImageButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public TVImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TVImageButton(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setGravity(Gravity.BOTTOM);
	}
	
	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if(focused){
			setBackgroundResource(R.drawable.cs_recommend_selected_bg);
		}else{
			setBackgroundResource(0);
		}
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}
}
