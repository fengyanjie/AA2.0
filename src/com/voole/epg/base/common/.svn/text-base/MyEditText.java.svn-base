package com.voole.epg.base.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

import com.voole.epg.R;

public class MyEditText extends EditText {

	private boolean isFocused = false;
	private Bitmap focusBitmap = null;
	private NinePatch focus_ninePatch = null;

	private Bitmap bgBitmap = null;
	private NinePatch bg_ninePatch = null;

	private int height = DisplayManager.GetInstance().changeHeightSize(8);
	private int weight = DisplayManager.GetInstance().changeWidthSize(8);

	public MyEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {

		setTextColor(Color.WHITE);
		setBackgroundColor(0);
		setSingleLine(true);
		focusBitmap = new BitmapFactory().decodeResource(getResources(),
				R.drawable.cs_btn_focus);
		focus_ninePatch = new NinePatch(focusBitmap,
				focusBitmap.getNinePatchChunk(), null);

		bgBitmap = new BitmapFactory().decodeResource(getResources(),
				R.drawable.cs_input);
		bg_ninePatch = new NinePatch(bgBitmap, bgBitmap.getNinePatchChunk(),
				null);
	}

	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public MyEditText(Context context) {
		super(context);
		init(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Rect rect = canvas.getClipBounds();
		Rect background = new Rect();
		background.left = rect.left + weight;
		background.right = rect.right - weight;
		background.top = rect.top + height;
		background.bottom = rect.bottom - height;
		if (isFocused) {
			focus_ninePatch.draw(canvas, rect);
		}
		bg_ninePatch.draw(canvas, background);
		super.onDraw(canvas);

	}

	@Override
	protected void onFocusChanged(boolean focused, int direction,
			Rect previouslyFocusedRect) {
		isFocused = focused;
		invalidate();
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
	}

}
