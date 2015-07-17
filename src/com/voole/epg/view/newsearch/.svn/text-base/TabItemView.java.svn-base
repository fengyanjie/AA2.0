package com.voole.epg.view.newsearch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.voole.epg.R;
import com.voole.epg.base.BaseView;
import com.voole.tvutils.ImageUtil;

public class TabItemView extends BaseView {

	private Paint paint = null;
	private Bitmap bit_tab_btn_bg = null;
	private NinePatch tab_btn_bg = null;
	private Bitmap bit_tab_btn_focus_bg = null;
	private NinePatch tab_btn_focus_bg = null;
	private String content = null;
	private Rect rect;

	public TabItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TabItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TabItemView(Context context) {
		super(context);
		init();
	}

	public TabItemView(Context context, String content) {
		super(context);
		init();
		this.content = content;
	}

	private void init() {
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setTextSize(25);
		paint.setColor(Color.WHITE);
		bit_tab_btn_bg = ImageUtil.getResourceBitmap(mContext,
				R.drawable.cs_search_tab_btn_bg);
		tab_btn_bg = new NinePatch(bit_tab_btn_bg,
				bit_tab_btn_bg.getNinePatchChunk(), null);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (rect==null) {
			rect = canvas.getClipBounds();
		}
		tab_btn_bg.draw(canvas, rect);
		float baseX = 0;
		float baseY = 100;
		canvas.drawText(content, baseX, baseY, paint);

	}

}
