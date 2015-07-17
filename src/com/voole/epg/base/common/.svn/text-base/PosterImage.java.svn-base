package com.voole.epg.base.common;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

public class PosterImage extends ImageView {

	private Context context;
	private Bitmap post_img=null;

	public PosterImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public PosterImage(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public PosterImage(Context context) {
		super(context);
		this.context = context;
	}
	
	public PosterImage(Context context,Bitmap bit) {
		super(context);
		post_img=bit;
		this.context = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (post_img != null) {
			Rect posterRect = canvas.getClipBounds();
			canvas.drawBitmap(post_img, null, posterRect, null);
		}
	}

}
