package com.voole.epg.view.navigation;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.tvutils.ImageUtil;

public class NavigationHomeMultiLineView extends NavigationHomeBaseView{
	private static final int ITEM_SIZE = 16;
	
	private LinearLayout layoutUp = null;
	private LinearLayout layoutDown = null;
	
	private int height=0;
	
	public NavigationHomeMultiLineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationHomeMultiLineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationHomeMultiLineView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
//		setBackgroundResource(R.drawable.cs_navigation_multi_line_bg);
		setPadding(15, 25 , 15, 0);
		itemViews = new NavigationHomeItemView[ITEM_SIZE];
		setOrientation(VERTICAL);
		height = DisplayManager.GetInstance().getScreenHeight()/7;
		height = 90;
		initUp(context);
		initDown(context);
	}
	
	private void initUp(Context context){
		layoutUp = new LinearLayout(context);
		/*LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 3);*/
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				85);
		param.topMargin = DisplayManager.GetInstance().changeHeightSize(20);
		layoutUp.setLayoutParams(param);
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 1);
		for(int i=0; i<ITEM_SIZE/2; i++){
			itemViews[i] = new NavigationHomeItemView(context);
			itemViews[i].setLayoutParams(param_item);
			Bitmap img_unselected = ImageUtil.getResourceBitmap(context, UNSELECTED_IMG[i]);
			Bitmap img_selected = ImageUtil.getResourceBitmap(context, SELECTED_IMG[i]);
			itemViews[i].setNumber(img_unselected,img_selected);
			layoutUp.addView(itemViews[i]);
		}
		addView(layoutUp);
	}
	
	private void initDown(Context context){
		layoutDown = new LinearLayout(context);
		/*LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 7);*/
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				80);
//		param.bottomMargin = 10;
		layoutDown.setLayoutParams(param);
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 1);
		for(int i=ITEM_SIZE/2; i<ITEM_SIZE; i++){
			itemViews[i] = new NavigationHomeItemView(context);
			itemViews[i].setLayoutParams(param_item);
			Bitmap img_unselected = ImageUtil.getResourceBitmap(context, UNSELECTED_IMG[i]);
			Bitmap img_selected = ImageUtil.getResourceBitmap(context, SELECTED_IMG[i]);
			itemViews[i].setNumber(img_unselected,img_selected);
			layoutDown.addView(itemViews[i]);
		}
		addView(layoutDown);
		//layoutDown.setVisibility(View.GONE);
	}

	public void setItems(List<FilmClass> items){
		navigationItems = items;
		int endIndex = ITEM_SIZE;
		if(navigationItems != null){
			if(navigationItems.size() < ITEM_SIZE){
				endIndex = navigationItems.size();
			}
			for(int i = 0; i < endIndex; i++){
				itemViews[i].setItem(navigationItems.get(i));
			}
		}
	}
	
	/**@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if(gainFocus){
//			if(navigationItems != null && navigationItems.size() > ITEM_SIZE / 2){
			Animation animationUp = new TranslateAnimation(0, 0,80, 0);
			animationUp.setDuration(1000);
			animationUp.setFillAfter(false); 
			layoutDown.startAnimation(animationUp);
			layoutDown.setVisibility(View.VISIBLE);
			setBackgroundResource(R.drawable.cs_navigation_multi_line_bg);
//			}
		}else{
			setBackgroundResource(0);
			layoutDown.setVisibility(View.GONE);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}*/

	public void setGoTo(int number){
		int num=number-1;
		if(listener != null && num < navigationItems.size()){
			listener.onItemSelected(num, navigationItems.get(num));
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
				}
				return false;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if(selectedItemIndex < navigationItems.size() - 1){
					itemViews[selectedItemIndex].setSelected(false);
					itemViews[++selectedItemIndex].setSelected(true);
					return true;
				}
				return false;
			case KeyEvent.KEYCODE_DPAD_UP:
				if( selectedItemIndex >= ITEM_SIZE / 2 ){
					itemViews[selectedItemIndex].setSelected(false);
					selectedItemIndex -= ITEM_SIZE / 2;
					itemViews[selectedItemIndex].setSelected(true);
					return true;
				}
				return false;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if(selectedItemIndex < ITEM_SIZE / 2 && navigationItems.size() > ITEM_SIZE / 2){
					itemViews[selectedItemIndex].setSelected(false);
					if((selectedItemIndex + ITEM_SIZE / 2) < navigationItems.size()){
						selectedItemIndex += ITEM_SIZE / 2;
					}else{
						selectedItemIndex = ITEM_SIZE / 2;
					}
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
//			case KeyEvent.KEYCODE_1:
//			case KeyEvent.KEYCODE_2:
//			case KeyEvent.KEYCODE_3:
//			case KeyEvent.KEYCODE_4:
//			case KeyEvent.KEYCODE_5:
//			case KeyEvent.KEYCODE_6:
//			case KeyEvent.KEYCODE_7:
//			case KeyEvent.KEYCODE_8:
//			case KeyEvent.KEYCODE_9:
//				int num = keyCode - 7 - 1;
//				if(listener != null && num < navigationItems.size()){
//					listener.onItemSelected(num, navigationItems.get(num));
//					return true;
//				}
//				return false;
			default:
				return false;
		}
	}
}
