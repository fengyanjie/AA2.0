package com.voole.epg.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class BaseImageView extends ImageView{
	public BaseImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BaseImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BaseImageView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setScaleType(ScaleType.FIT_XY);
	}
	
	
	
}
