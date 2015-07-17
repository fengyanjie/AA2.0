package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;

public class ShadeButton extends BaseLinearLayout {
	private TextView button = null;
	protected int PADDING = 0;
	protected int button_bg=0;
	protected int main_bg=0;
	protected int TEXT_SIZE=0;
	protected LinearLayout.LayoutParams param_button=null;
	
	
	public ShadeButton(Context context) {
		super(context);
		init(context);
	}

	public ShadeButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ShadeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	protected  void initImg(){
		 PADDING = DisplayManager.GetInstance().changeWidthSize(6);
		 button_bg=R.drawable.cs_movie_detail_button_unfocuse;
		 main_bg=R.drawable.cs_shade_button_focuse;
		 param_button = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		setGravity(Gravity.CENTER);
		initImg();
		button = new TextView(context);
		if(TEXT_SIZE>0){
			button.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXT_SIZE);
		}else{
			button.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
		}
		button.setGravity(Gravity.CENTER);
		button.setTextColor(Color.WHITE);
		button.setBackgroundResource(button_bg);
		param_button.setMargins(PADDING, PADDING, PADDING, PADDING);
		button.setLayoutParams(param_button);
		addView(button);
	}
	
	public void setText(String string){
		button.setText(string);
	}
	
	public void setTextColor(int color){
		button.setTextColor(color);
	}
	
	public String getText(){
		return button.getText().toString();
	}
	
	public void setTextSize(int size){
		button.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
//		invalidate();
		if (gainFocus) {
			setBackgroundResource(main_bg);
		}else {
			setBackgroundResource(0);
		}
	}
	
	/*@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Rect rect = canvas.getClipBounds();
		if (isFocused) {
			canvas.drawBitmap(focusedBitmap, null, rect, null);
		}
	}*/
}
