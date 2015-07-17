package com.voole.epg.view.navigation;

import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.view.navigation.NavigationProgramItemView.Status;
import com.voole.tvutils.ImageUtil;

public class NavigationProgramView extends BaseRelativeLayout{
	public static final int ITEM_SIZE = 8;
	private NavigationProgramItemView[] itemViews = null;
	
	private ImageView leftArrow = null;
	private ImageView rightArrow = null;
	
	private int currentDisplayingIndex = 0;
	private int currentSelectedIndex = 0;
	private int currentSelectedViewIndex = 0;
	private int startIndex = 0;
	
	private List<FilmClass> list = null;
	
	private final int TEXT_SIZE=26 ;
//	private final int TEXT_SIZE=DisplayManager.GetInstance().changeTextSize(28) ;
	
	private boolean isAlwaysNotifyListener = false;
	
	protected NavigationItemSelectedListener listener = null;
	public void setOnItemSelectedListener(NavigationItemSelectedListener l){
		listener = l;
	}
	public NavigationProgramView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationProgramView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationProgramView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		itemViews = new NavigationProgramItemView[ITEM_SIZE];
		initLeftArrow(context);
		initItemList(context);
		initRightArrow(context);
	}
	
	private void initLeftArrow(Context context){
		leftArrow = new ImageView(context);
		leftArrow.setId(ID.NavigationProgramView.LEFT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_left = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_arrow_left.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_left.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		leftArrow.setLayoutParams(param_arrow_left);
		leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context, R.drawable.cs_navigation_program_left_arrow));
		leftArrow.setScaleType(ScaleType.FIT_XY);
		addView(leftArrow);
	}
	
	private void initItemList(Context context){
		LinearLayout layout_middle = new LinearLayout(context);
		RelativeLayout.LayoutParams layout_middle_param = new RelativeLayout.LayoutParams
		(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout_middle_param.addRule(RelativeLayout.RIGHT_OF, ID.NavigationProgramView.LEFT_ARROW_ID);
		layout_middle_param.addRule(RelativeLayout.LEFT_OF, ID.NavigationProgramView.RIGHT_ARROW_ID);
		layout_middle.setLayoutParams(layout_middle_param);
		
		LinearLayout.LayoutParams layout_item = new LinearLayout.LayoutParams
				(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		for(int i=0; i<ITEM_SIZE; i++){
			itemViews[i] = new NavigationProgramItemView(context);
			itemViews[i].setLayoutParams(layout_item);
			itemViews[i].setTextSize(TEXT_SIZE);
			layout_middle.addView(itemViews[i]);
		}
		
		addView(layout_middle);
	}
	
	private void initRightArrow(Context context){
		rightArrow = new ImageView(context);
		rightArrow.setId(ID.NavigationProgramView.RIGHT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_right = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param_arrow_right.rightMargin = DisplayManager.GetInstance().changeWidthSize(5);
		rightArrow.setLayoutParams(param_arrow_right);
		rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context, R.drawable.cs_navigation_program_right_arrow));
		rightArrow.setScaleType(ScaleType.FIT_XY);
		addView(rightArrow);
	}
	
	public void setData(List<FilmClass> items){
		this.list = items;
		int endIndex = ITEM_SIZE;
		if(items.size() < ITEM_SIZE){
			endIndex = items.size();
		}
		for(int i = 0; i<endIndex; i++){
			itemViews[i].setData(items.get(i));
		}
		itemViews[currentDisplayingIndex].setCurrentStatus(Status.Dislaying);
		updateArrow();
	}
	
	private void updateData(){
		for(int i = 0, j = startIndex; i<ITEM_SIZE; i++, j++){
			itemViews[i].setData(list.get(j));
			itemViews[i].setCurrentStatus(Status.Normal);
			if(j == currentDisplayingIndex){
				itemViews[i].setCurrentStatus(Status.Dislaying);
			}
		}
		itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Selected);
		updateArrow();
	}
	
	private void updateDisplaying(){
		for(int i = startIndex; i<startIndex+ITEM_SIZE; i++){
			if(currentDisplayingIndex == i && currentDisplayingIndex != currentSelectedIndex){
				itemViews[currentDisplayingIndex - startIndex].setCurrentStatus(Status.Normal);
			}
		}
	}
	
	private void updateArrow(){
		if(startIndex > 0){
			leftArrow.setVisibility(View.VISIBLE);
		}else{
			leftArrow.setVisibility(View.INVISIBLE);
		}
		if(list.size() > startIndex + ITEM_SIZE){
			rightArrow.setVisibility(View.VISIBLE);
		}else{
			rightArrow.setVisibility(View.INVISIBLE);
		}
	}
	
	private void gotoNext(){
		if(currentSelectedIndex < list.size() - 1){
			currentSelectedIndex ++;
			if(currentSelectedViewIndex < ITEM_SIZE - 1){
				if(currentSelectedIndex - 1 == currentDisplayingIndex){
					itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Dislaying);
				}else{
					itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Normal);
				}
				itemViews[++currentSelectedViewIndex].setCurrentStatus(Status.Selected);
			}else{
				startIndex ++;
				updateData();
			}
		}
	}
	
	private void gotoPre(){
		if(currentSelectedIndex > 0){
			currentSelectedIndex --;
			if(currentSelectedViewIndex > 0){
				if(currentSelectedIndex + 1 == currentDisplayingIndex){
					itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Dislaying);
				}else{
					itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Normal);
				}
				itemViews[--currentSelectedViewIndex].setCurrentStatus(Status.Selected);
			}else{
				startIndex --;
				updateData();
			}
		}
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(this.list == null || this.list.size() < 1){
			return false;
		}
		switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				gotoPre();
				return true;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				gotoNext();
				return true;
			case KeyEvent.KEYCODE_ENTER:
			case KeyEvent.KEYCODE_DPAD_CENTER:
 				if(isAlwaysNotifyListener || currentSelectedIndex != currentDisplayingIndex){
					if (list == null || list.size() <= 0) {
						return false;
					}
					if(listener != null && currentSelectedIndex < list.size()){
						listener.onItemSelected(currentSelectedIndex, list.get(currentSelectedIndex));
						updateDisplaying();
						currentDisplayingIndex = currentSelectedIndex;
						return true;
					}
				}
				return true;
			default:
				return false;
		}
	}
	
	public void setAlwaysNotifyListener(boolean b){
		this.isAlwaysNotifyListener = b;
	}
	
	public void setLoseFocusSelectedItemIndex(int selectedItemIndex) {
		if(list == null || list.size() < 1){
			return;
		}
		this.currentSelectedIndex = selectedItemIndex;
		this.currentDisplayingIndex = selectedItemIndex;
		
		itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Normal);
		
		if(selectedItemIndex < ITEM_SIZE){
			startIndex = 0;
			currentSelectedViewIndex = selectedItemIndex;
			itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Dislaying);
		}else{
			startIndex = currentSelectedIndex - ITEM_SIZE + 1 ;
			currentSelectedViewIndex = ITEM_SIZE - 1;
			for(int i = 0, j = startIndex; i<ITEM_SIZE; i++, j++){
				itemViews[i].setData(list.get(j));
				itemViews[i].setCurrentStatus(Status.Normal);
				if(j == currentDisplayingIndex){
					itemViews[i].setCurrentStatus(Status.Dislaying);
				}
			}
			updateArrow();
		}
	}
	
	public void setSelectedItemIndex(int selectedItemIndex) {
		if(list == null || list.size() < 1){
			return;
		}
		this.currentSelectedIndex = selectedItemIndex;
		this.currentDisplayingIndex = selectedItemIndex;;
		
		itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Normal);
		
		if(selectedItemIndex < ITEM_SIZE){
			startIndex = 0;
			currentSelectedViewIndex = selectedItemIndex;
			itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Selected);
		}else{
			startIndex = currentSelectedIndex - ITEM_SIZE + 1 ;
			currentSelectedViewIndex = ITEM_SIZE - 1;
			for(int i = 0, j = startIndex; i<ITEM_SIZE; i++, j++){
				itemViews[i].setData(list.get(j));
				itemViews[i].setCurrentStatus(Status.Normal);
				if(j == currentDisplayingIndex){
					itemViews[i].setCurrentStatus(Status.Selected);
				}
			}
			updateArrow();
		}
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if(list == null || list.size() < 1){
			return;
		}
		if(gainFocus){
			itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Selected);
		}else{
			if(currentSelectedIndex == currentDisplayingIndex){
				itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Dislaying);
			}else{
				itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Normal);
			}
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
	
}
