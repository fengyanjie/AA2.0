package com.voole.epg.view.newsearch;

import com.voole.epg.R;
import com.voole.epg.base.common.DisplayManager;
import com.voole.tvutils.ImageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

public class FindItemView extends TextView {
	public enum Status {
		Normal, Selected, Dislaying,Selected_Dislaying
	}

	private Bitmap displaying_bit = null;
	private Bitmap onfocus_bit = null;
	private NinePatch displaying_ninepatch = null;
	private NinePatch onfocus_ninepatch = null;

	private Status currentStatus = Status.Normal;

	private boolean isFocus = false;
	private boolean isDisplay = false;

	public void setCurrentStatus(Status currentStatus) {
		if (this.currentStatus != currentStatus) {
			this.currentStatus = currentStatus;
			updateUI();
		}
	}

	public FindItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public FindItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public FindItemView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setGravity(Gravity.CENTER);
		setSingleLine(true);
		setTextColor(Color.WHITE);
		setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager.GetInstance()
				.changeTextSize(24));
		setPadding(0, 5, 0, 5);
		displaying_bit = ImageUtil.getResourceBitmap(context,
				R.drawable.cs_navigation_find_focus);
		displaying_ninepatch = new NinePatch(displaying_bit,
				displaying_bit.getNinePatchChunk(), null);

		onfocus_bit = ImageUtil.getResourceBitmap(context,
				R.drawable.cs_btn_focus);
		onfocus_ninepatch = new NinePatch(onfocus_bit,
				onfocus_bit.getNinePatchChunk(), null);
	}

	@Override
	protected void onDraw(Canvas canvas) {

		Rect rect = canvas.getClipBounds();

		Rect displayRect=new Rect();
		displayRect.top = rect.top + 2;
		displayRect.bottom = rect.bottom - 2;
		displayRect.left = rect.left + 2;
		displayRect.right = rect.right - 2;
		Rect focusRect = new Rect();
		focusRect.top = rect.top - 2;
		focusRect.bottom = rect.bottom + 2;
		focusRect.left = rect.left - 2;
		focusRect.right = rect.right + 2;

		if (isDisplay) {
			displaying_ninepatch.draw(canvas, displayRect);
		}
		if (isFocus) {
			onfocus_ninepatch.draw(canvas, focusRect);
		}
		super.onDraw(canvas);
	}

	private void updateUI() {
		if (currentStatus == Status.Normal) {
			isFocus = false;
			isDisplay = false;
			setBackgroundResource(0);
			invalidate();
		} else if (currentStatus == Status.Selected) {
			isFocus = true;
			isDisplay=false;
			invalidate();
		} else if (currentStatus == Status.Dislaying) {
			isDisplay = true;
			isFocus = false;
			invalidate();
		}else if(currentStatus==Status.Selected_Dislaying){
			isDisplay = true;
			isFocus = true;
			invalidate();
		}
		// setPadding(0, 5, 0, 5);
	}

}
