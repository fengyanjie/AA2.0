package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.voole.epg.R;
import com.voole.epg.base.BaseButton;
import com.voole.tvutils.BitmapUtil;

public class ShadeButton2 extends BaseButton {
	private Paint paint = new Paint();
	private boolean isFocused = false;
	private String string = "";
	
	private Bitmap unfocusedBitmap = BitmapUtil.readBitmap(getContext(), R.drawable.cs_movie_detail_button_unfocuse);
	
	private Bitmap focusedBitmap = BitmapUtil.readBitmap(getContext(), R.drawable.cs_shade_button_focuse);
	
	
	public ShadeButton2(Context context) {
		super(context);
		init(context);
	}

	public ShadeButton2(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ShadeButton2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		/*setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);*/
		BitmapDrawable drawable = new BitmapDrawable(unfocusedBitmap);
		setBackgroundDrawable(drawable);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		params.setMargins(PADDING, PADDING, PADDING, PADDING);
		this.setLayoutParams(params);
		paint.setStrokeWidth(0);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Align.CENTER);
	    paint.setStyle(Paint.Style.FILL);  
	    paint.setTextSize(DisplayManager.GetInstance().changeTextSize(28));
	}
	
	public void setText(String string){
		this.string = string;
	}
	
	public void setTextColor(int color){
		paint.setColor(color);
	}
	
	public String getText(){
		return string;
	}
	
	public void setTextSize(int size){
		paint.setTextSize(size);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		this.isFocused = gainFocus;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Rect rect = canvas.getClipBounds();
		if (isFocused) {
			canvas.drawBitmap(focusedBitmap, null, rect, null);
		}
		FontMetrics fontMetrics = paint.getFontMetrics();
    	float fontHeight = fontMetrics.bottom - fontMetrics.top; 
    	float textBaseY = rect.height() - (rect.height() - fontHeight) / 2 - fontMetrics.bottom; 
		canvas.drawText(string, rect.width()/2, textBaseY, paint);
	}
}
