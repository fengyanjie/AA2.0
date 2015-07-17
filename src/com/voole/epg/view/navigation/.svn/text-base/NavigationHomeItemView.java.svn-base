 package com.voole.epg.view.navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.voole.epg.Config;
import com.voole.epg.Config.OemType;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;

public class NavigationHomeItemView extends BaseView{
	private boolean isSelected = false;
	private FilmClass item = null;
	private Paint paint = new Paint();
	private Bitmap unselected_img = null;
	private Bitmap selected_img = null;
	private final int TEXT_SIZE=DisplayManager.GetInstance().changeWidthSize(26) ;
	private Rect rect;
	public NavigationHomeItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public NavigationHomeItemView(Context context) {
		super(context);
		init();
	}

	public NavigationHomeItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
	    paint.setStrokeWidth(0);  
	    paint.setStyle(Paint.Style.FILL);  
	    paint.setTextSize(TEXT_SIZE);
	    paint.setAntiAlias(true);
	}
	
	public void setSelected(boolean selected){
		isSelected = selected;
		invalidate();
	}
	
	public void setItem(FilmClass item){
		this.item = item;
		invalidate();
	}
	
	public void setNumber(Bitmap unselected,Bitmap selected){
		unselected_img = unselected;
		selected_img=selected;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (rect==null) {
			rect = canvas.getClipBounds(); 
		}
		if(item != null){
			String text_name = item.getFilmClassName();
			Rect imgRect = new Rect();
			imgRect.left = rect.left;
			imgRect.top = rect.top+ DisplayManager.GetInstance().changeHeightSize(10);
			imgRect.right = rect.right *35/100;
			imgRect.bottom = rect.bottom *50/100;
			if(isSelected){
				if(selected_img != null){
					/*if (Config.OemType.getOemType(Config.GetInstance().getOemType()) != OemType.Hisense) {
						canvas.drawBitmap(selected_img, null, imgRect, null);
					}*/
				}
				paint.setColor(Color.rgb(0xe4, 0x7d, 0x38));
				paint.setTextSize(TEXT_SIZE);
				canvas.drawText(text_name, imgRect.right - DisplayManager.GetInstance().changeWidthSize(5), rect.bottom/2, paint);
			}else{
				if(unselected_img != null){
					/*if (Config.OemType.getOemType(Config.GetInstance().getOemType()) != OemType.Hisense) {
						canvas.drawBitmap(unselected_img, null, imgRect, null);
					}*/
				}
				paint.setColor(Color.WHITE);
				paint.setTextSize(TEXT_SIZE);
				canvas.drawText(text_name, imgRect.right - DisplayManager.GetInstance().changeWidthSize(5), rect.bottom/2, paint);
			}
		}
		super.onDraw(canvas);
	}
}
