package com.voole.epg.view.navigation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.voole.epg.R;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.tvutils.BitmapUtil;

public class NavigationProgramItemView extends BaseView{
	private Paint paint = new Paint();
	private String string = "";
	
	private Bitmap focusedBitmap = BitmapUtil.readBitmap(getContext(), R.drawable.cs_btn_navigation_focus);
	private Bitmap unfocusedBitmap = BitmapUtil.readBitmap(getContext(), R.drawable.cs_btn_unfocus);
	
	private NinePatch ninePatchBg_focused = null;
	private NinePatch ninePatchBg_unfocused = null;
	
	public enum Status{
		Normal,
		Selected,
		Dislaying
	}
	private FilmClass item = null;
	
	private Status currentStatus = Status.Normal;
	private Rect rect;
	public void setCurrentStatus(Status currentStatus) {
		if(this.currentStatus != currentStatus){
			this.currentStatus = currentStatus;
			invalidate();
		}
	}

	public NavigationProgramItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationProgramItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationProgramItemView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		paint.setStrokeWidth(0);
		paint.setTextAlign(Align.CENTER);
	    paint.setStyle(Paint.Style.FILL);
	    paint.setTextSize(DisplayManager.GetInstance().changeWidthSize(28));
	    paint.setAntiAlias(true);
	    
	    ninePatchBg_focused = new NinePatch(focusedBitmap, focusedBitmap.getNinePatchChunk(), null);
	    ninePatchBg_unfocused = new NinePatch(unfocusedBitmap, unfocusedBitmap.getNinePatchChunk(), null);
	}
	
	public void setData(FilmClass item){
		this.item = item;
		if(this.item != null){
			string = item.getFilmClassName();
			invalidate();
		}
	}
	
	public void setTextSize(int textSize){
		paint.setTextSize(DisplayManager.GetInstance().changeWidthSize(textSize));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (rect==null) {
			rect = canvas.getClipBounds();
		}
		if(currentStatus == Status.Normal){
			paint.setColor(Color.WHITE);
		}else if(currentStatus == Status.Selected){
			paint.setColor(Color.rgb(0xe4, 0x7d, 0x38));
			ninePatchBg_focused.draw(canvas, rect);
//			canvas.drawBitmap(focusedBitmap, null, rect, null);
		}else if(currentStatus == Status.Dislaying){
			//paint.setColor(Color.rgb(0xe4, 0x7d, 0x38));
			paint.setColor(Color.WHITE);
			ninePatchBg_unfocused.draw(canvas, rect);
//			canvas.drawBitmap(unfocusedBitmap, null, rect, null);
		}
		FontMetrics fontMetrics = paint.getFontMetrics();
    	float fontHeight = fontMetrics.bottom - fontMetrics.top; 
    	float textBaseY = rect.height() - (rect.height() - fontHeight) / 2 - fontMetrics.bottom; 
		canvas.drawText(string, rect.width()/2, textBaseY, paint);
	}
}
