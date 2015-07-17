package com.voole.epg.view.navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;

public class NavigationItemView extends BaseView{
	private boolean isSelected = false;
	private FilmClass item = null;
	private Paint paint = new Paint();
	private Bitmap selected_img = null;
	private Bitmap unselected_img = null;
	private Rect rect;
	public NavigationItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public NavigationItemView(Context context) {
		super(context);
		init();
	}

	public NavigationItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
	    paint.setStrokeWidth(0);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setTextSize(23);
	}
	
	public void setSelected(boolean selected){
		isSelected = selected;
		invalidate();
	}
	
	public void setItem(FilmClass item){
		this.item = item;
		invalidate();
	}
	
	public void setNumber(Bitmap selected, Bitmap unselected){
		selected_img = selected;
		unselected_img = unselected;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if(item != null){
			String text_name = item.getFilmClassName();
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			Rect imgRect = new Rect();
			imgRect.left = rect.left;
			imgRect.top = rect.top;
			imgRect.right = rect.right / 3;
			imgRect.bottom = rect.bottom / 2;
			if(isSelected){
				paint.setColor(Color.rgb(0xe4, 0x7d, 0x38));
				if(selected_img != null){
		        	canvas.drawBitmap(selected_img, null, imgRect, null);
				}
			}else{
				paint.setColor(Color.WHITE);
				if(unselected_img != null){
		        	canvas.drawBitmap(unselected_img, null, imgRect, null);
				}
			}
    	    canvas.drawText(text_name, imgRect.right - DisplayManager.GetInstance().changeWidthSize(15), rect.bottom/2, paint);
		}
		super.onDraw(canvas);
	}
}
