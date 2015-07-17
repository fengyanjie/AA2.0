package com.voole.epg.base;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class BaseFrameLayout extends FrameLayout {
	protected Context mContext;

	public BaseFrameLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BaseFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BaseFrameLayout(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
	}
}
