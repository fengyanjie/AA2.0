package com.voole.epg.view.newsearch;

import com.voole.epg.R;
import com.voole.epg.view.mymagic.UserManagementLeftView.UserManagementLeftItemView.CurrentState;
import com.voole.tvutils.ImageUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

public class ConditionItemView extends TextView {

	public enum CurrentState{
		unselected_noFocus,
		unselected_haFocus,
		selected_noFocus,
		selected_hasFocus
	}
	private Bitmap displayBitmap = null;
	
	private CurrentState currentStatue = CurrentState.unselected_noFocus;
	
	public void setStates(CurrentState currentState){
		this.currentStatue = currentState;
		refresh();
	}
	
	private Bitmap undisplayBitmap = null;

	private boolean isFocus = false;
	private boolean isDisplay = false;

	public ConditionItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ConditionItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ConditionItemView(Context context) {
		super(context);
		init(context);
	}

	public void setFocus(boolean isFocus) {
		this.isFocus = isFocus;
		invalidate();
	}

	public void setDisplay(boolean isDisplay) {
		this.isDisplay = isDisplay;
		invalidate();
	}

	private void init(Context context) {
		setTextColor(Color.WHITE);
		setBackgroundColor(0);
		setGravity(Gravity.CENTER);
		setSingleLine(true);

//		displayBitmap = ImageUtil.getResourceBitmap(context,
//				R.drawable.cs_searach_condition_display);
//
//		undisplayBitmap = ImageUtil.getResourceBitmap(context,
//				R.drawable.cs_searach_condition_undisplay);

	}
	
	private void refresh(){
		switch (currentStatue) {
		case unselected_noFocus:
			//setBackgroundResource(0);
			//setTextColor(Color.WHITE); 
			setBackgroundResource(R.drawable.cs_searach_condition_undisplay);
			setTextColor(Color.parseColor("#949494"));
			break;
		case unselected_haFocus:
			setBackgroundResource(R.drawable.cs_searach_condition_undisplay_focus);
			setTextColor(Color.WHITE); 
			//setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
			break;
		case selected_noFocus:
			setBackgroundResource(R.drawable.cs_searach_condition_display);
			setTextColor(Color.WHITE); 
			//setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
			break;
		case selected_hasFocus:
			setBackgroundResource(R.drawable.cs_searach_condition_display_focus);
			setTextColor(Color.WHITE); 
			break;
		default:
			break;
		}
	}
	

//	@Override
//	protected void onDraw(Canvas canvas) {
//		Rect rect = canvas.getClipBounds();
//		if (isDisplay) {
//			canvas.drawBitmap(displayBitmap, null, rect, null);
//		} else {
//			canvas.drawBitmap(undisplayBitmap, null, rect, null);
//		}
//		super.onDraw(canvas);
//	}
}
