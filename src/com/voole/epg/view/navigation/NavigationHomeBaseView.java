package com.voole.epg.view.navigation;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.voole.epg.R;

public class NavigationHomeBaseView extends NavigationBaseView {
	protected static final int[] UNSELECTED_IMG = {
			R.drawable.cs_navigation_unselected_0,
			R.drawable.cs_navigation_unselected_1,
			R.drawable.cs_navigation_unselected_2,
			R.drawable.cs_navigation_unselected_3,
			R.drawable.cs_navigation_unselected_4,
			R.drawable.cs_navigation_unselected_5,
			R.drawable.cs_navigation_unselected_6,
			R.drawable.cs_navigation_unselected_7,
			R.drawable.cs_navigation_unselected_8,
			R.drawable.cs_navigation_unselected_9,
			R.drawable.cs_navigation_unselected_10,
			R.drawable.cs_navigation_unselected_11,
			R.drawable.cs_navigation_unselected_12,
			R.drawable.cs_navigation_unselected_13,
			R.drawable.cs_navigation_unselected_14,
			R.drawable.cs_navigation_unselected_15
	};

	protected static final int[] SELECTED_IMG = {
			R.drawable.cs_navigation_selected_0,
			R.drawable.cs_navigation_selected_1,
			R.drawable.cs_navigation_selected_2,
			R.drawable.cs_navigation_selected_3,
			R.drawable.cs_navigation_selected_4,
			R.drawable.cs_navigation_selected_5,
			R.drawable.cs_navigation_selected_6,
			R.drawable.cs_navigation_selected_7,
			R.drawable.cs_navigation_selected_8,
			R.drawable.cs_navigation_selected_9,
			R.drawable.cs_navigation_selected_10,
			R.drawable.cs_navigation_selected_11,
			R.drawable.cs_navigation_selected_12,
			R.drawable.cs_navigation_selected_13,
			R.drawable.cs_navigation_selected_14,
			R.drawable.cs_navigation_selected_15,
			R.drawable.cs_navigation_selected_16
			
	};

	protected NavigationHomeItemView[] itemViews = null;

	public NavigationHomeBaseView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationHomeBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationHomeBaseView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if (navigationItems == null || navigationItems.size() <= 0) {
			return;
		}
		if (gainFocus) {
			itemViews[selectedItemIndex].setSelected(true);
		} else {
			itemViews[selectedItemIndex].setSelected(false);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
}
