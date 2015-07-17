package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.voole.epg.R;
import com.voole.epg.base.BaseButton;

public class TVButton extends BaseButton{
	private int disableID = R.drawable.cs_btn_unfocus;
	private int enableID = R.drawable.cs_btn_focus;
	
	private static final int PADDING = 18;

	public TVButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public TVButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TVButton(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		setTextColor(Color.WHITE);
		setGravity(Gravity.CENTER);
		setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager.GetInstance().changeTextSize(26) );
		setBackgroundResource(this.disableID);
		setPadding(PADDING, PADDING / 2, PADDING, PADDING / 2);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		if(focused){
			setBackgroundResource(this.enableID);
			setPadding(PADDING, PADDING / 2, PADDING, PADDING / 2);
		}else{
			setBackgroundResource(this.disableID);
			setPadding(PADDING, PADDING / 2, PADDING, PADDING / 2);
		}
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}
	
	public void setBgImgs(int disableID, int enableID){
		this.disableID = disableID;
		this.enableID = enableID;
		setBackgroundResource(disableID);
	}
	
}
