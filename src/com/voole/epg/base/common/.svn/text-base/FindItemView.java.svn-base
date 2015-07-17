package com.voole.epg.base.common;

import com.voole.epg.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

public class FindItemView extends TextView {
	public enum Status {
		Normal, Selected, Dislaying
	}

	private Status currentStatus = Status.Normal;

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
		setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager.GetInstance()
				.changeTextSize(24));
		setPadding(0, 5, 0, 5);
	}

	private void updateUI() {
		if (currentStatus == Status.Normal) {
			setBackgroundResource(0);
			setPadding(0, 5, 0, 5);
		} else if (currentStatus == Status.Selected) {
			setBackgroundResource(R.drawable.cs_btn_focus);
			setPadding(0, 8, 0, 8);
		} else if (currentStatus == Status.Dislaying) {
			setBackgroundResource(R.drawable.cs_navigation_find_focus);
			setPadding(0, 5, 0, 5);
		}
	}
}
