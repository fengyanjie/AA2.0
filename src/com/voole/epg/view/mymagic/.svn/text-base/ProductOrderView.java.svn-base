package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;

import com.voole.epg.R;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.tvutils.BitmapUtil;

public class ProductOrderView extends BaseView{
	
	private Bitmap focusedBitmap = BitmapUtil.readBitmap(getContext(), R.drawable.cs_mymagic_consumer_order_text_focused);
	private Bitmap unfocusedBitmap = BitmapUtil.readBitmap(getContext(), R.drawable.cs_mymagic_consumer_order_text_unfocused);
	
	private Paint paint = new Paint();
	private boolean isFocused = false;
	private String string = "";
	private Rect rect;
	
	public ProductOrderView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		/*setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);*/
		
		paint.setStrokeWidth(0);
		paint.setColor(Color.WHITE);
		paint.setTextAlign(Align.CENTER);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setTextSize(28);
	    
	}
	
	public void setText(String text){
		string = text;
	}
	
	public void setFocused(boolean isFocused){
		this.isFocused = isFocused;
		invalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (rect==null) {
			rect = canvas.getClipBounds();
		}
		if (isFocused) {
			canvas.drawBitmap(focusedBitmap, null, rect, null);
		}else {
			canvas.drawBitmap(unfocusedBitmap, null, rect, null);
		}
		FontMetrics fontMetrics = paint.getFontMetrics();
    	float fontHeight = fontMetrics.bottom - fontMetrics.top; 
    	float textBaseY = rect.height() - (rect.height() - fontHeight) / 2 - fontMetrics.bottom; 
		canvas.drawText(string, rect.width()/2, textBaseY, paint);
	}
	
}
