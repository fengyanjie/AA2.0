package com.voole.epg.view.navigation;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.tvutils.ImageUtil;

public class NavigationHomeSingleLineView extends NavigationHomeBaseView{
	private static final int ITEM_SIZE = 10;
	
	public NavigationHomeSingleLineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationHomeSingleLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationHomeSingleLineView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setGravity(Gravity.CENTER);
		itemViews = new NavigationHomeItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 1);
		for(int i=0; i<ITEM_SIZE; i++){
			itemViews[i] = new NavigationHomeItemView(context);
			itemViews[i].setLayoutParams(param_item);
			Bitmap img_unselected = ImageUtil.getResourceBitmap(context, UNSELECTED_IMG[i]);
			Bitmap img_selected = ImageUtil.getResourceBitmap(context, SELECTED_IMG[i]);
			itemViews[i].setNumber(img_unselected,img_selected);
			addView(itemViews[i]);
		}
	}
	
	public void setItems(List<FilmClass> items){
		navigationItems = items;
		int endIndex = navigationItems.size();
		if(navigationItems != null){
//			if(navigationItems.size() < ITEM_SIZE){
//				endIndex = navigationItems.size();
//			}
//			for(int i = 0; i < endIndex; i++){
//				itemViews[i].setItem(navigationItems.get(i));
//			}
			
			for(int i=0;i<endIndex;i++){
				if (i<ITEM_SIZE) {
					itemViews[i].setItem(navigationItems.get(i));
					itemViews[i].setVisibility(View.VISIBLE);
				}
			}
			
			for(int j=endIndex;j<ITEM_SIZE;j++){
				itemViews[j].setVisibility(View.GONE);
			}
			
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if(selectedItemIndex > 0){
					itemViews[selectedItemIndex].setSelected(false);
					itemViews[--selectedItemIndex].setSelected(true);
					return true;
				}else if(selectedItemIndex==0){
					itemViews[selectedItemIndex].setSelected(false);
					selectedItemIndex=navigationItems.size() - 1;
					itemViews[selectedItemIndex].setSelected(true);
					return true;
				}
				return false;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if(selectedItemIndex < navigationItems.size() - 1){
					itemViews[selectedItemIndex].setSelected(false);
					itemViews[++selectedItemIndex].setSelected(true);
					return true;
				}else if(selectedItemIndex == navigationItems.size() - 1){
					itemViews[selectedItemIndex].setSelected(false);
					selectedItemIndex=0;
					itemViews[selectedItemIndex].setSelected(true);
					return true;
				}
				return false;
			case KeyEvent.KEYCODE_ENTER:
			case KeyEvent.KEYCODE_DPAD_CENTER:
				if (navigationItems == null || navigationItems.size() <= 0) {
					return false;
				}
				if(listener != null && selectedItemIndex < navigationItems.size()){
					listener.onItemSelected(selectedItemIndex, navigationItems.get(selectedItemIndex));
					return true;
				}
				return false;
			    case KeyEvent.KEYCODE_0:
				case KeyEvent.KEYCODE_1:
				case KeyEvent.KEYCODE_2:
				case KeyEvent.KEYCODE_3:
				case KeyEvent.KEYCODE_4:
				case KeyEvent.KEYCODE_5:
				case KeyEvent.KEYCODE_6:
				case KeyEvent.KEYCODE_7:
				case KeyEvent.KEYCODE_8:
				case KeyEvent.KEYCODE_9:
					int num = keyCode - 7;
					if(listener != null && num < navigationItems.size()){
						listener.onItemSelected(num, navigationItems.get(num));
						return true;
					}
					return false;
			default:
				return false;
		}
	}
}
