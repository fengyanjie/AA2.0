package com.voole.epg.view.newsearch;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.movies.Label;
import com.voole.epg.view.newsearch.FindItemView.Status;
import com.voole.tvutils.ImageUtil;

public class FindView extends BaseLinearLayout {

	private TextView label_tv = null;
	private FindItemView[] itemViews = null;
	private int ITEM_SIZE =5;
	private List<String> LabelNames = null;
	private List<String> currentLabelNames = null;

	private ImageView leftArrow = null;
	private ImageView rightArrow = null;
	
	private int currentDisplayingIndex = 0;
	private int currentSelectedIndex = 0;
	private int currentSelectedViewIndex = 0;
	private int startIndex = 0;
	
	private boolean isCategory=false;
	
	
	public void setCategory(boolean isCategory) {
		this.isCategory = isCategory;
	}

	public interface FindViewListener{
		public void onItemClick(String content,boolean isCategory);
	}
	
	private FindViewListener findViewListener=null;

	public void setFindViewListener(FindViewListener findViewListener) {
		this.findViewListener = findViewListener;
	}

	public FindView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public FindView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public FindView(Context context) {
		
		super(context);
		init(context);
	}

	public void setData(String title, Label label) {
		label_tv.setText(title);
		
		if(currentLabelNames!=null&&currentLabelNames.size()>0){
			for (int i = 0; i < ITEM_SIZE; i++) {
				itemViews[i].setText(currentLabelNames.get(i));
			}
		}else{
			LabelNames = label.getLabelNameList();
			int len=LabelNames.size();
			if(len>ITEM_SIZE){
				len=ITEM_SIZE;
			}
			for (int i = 0; i <len; i++) {
				itemViews[i].setVisibility(View.VISIBLE);
				itemViews[i].setText(LabelNames.get(i));
			}
			if(len<ITEM_SIZE){
				for (int i = len; i <ITEM_SIZE; i++) {
					itemViews[i].setVisibility(View.INVISIBLE);
				}
			}
		}
		
		updateArrow();
	}

	private void init(Context context) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setOrientation(HORIZONTAL);
		
		currentLabelNames=new ArrayList<String>();
		
		itemViews = new FindItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_main = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setLayoutParams(param_main);
		LinearLayout layout_label = new LinearLayout(context);
		layout_label.setGravity(Gravity.CENTER);
		label_tv = new TextView(context);
		LinearLayout.LayoutParams param_label = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
		layout_label.setLayoutParams(param_label);
		label_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager
				.GetInstance().changeTextSize(24));
		label_tv.setGravity(Gravity.CENTER);
		label_tv.setTextColor(Color.WHITE);
		layout_label.addView(label_tv);
		addView(layout_label);

		leftArrow = new ImageView(context);
		LinearLayout.LayoutParams param_leftArrow = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_leftArrow.gravity=Gravity.CENTER_VERTICAL;
		leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context, R.drawable.cs_navigation_program_left_arrow));
		leftArrow.setLayoutParams(param_leftArrow);
		leftArrow.setScaleType(ScaleType.FIT_XY);
		addView(leftArrow);
		
		LinearLayout layout_content = new LinearLayout(context);
		layout_content.setOrientation(HORIZONTAL);
		LinearLayout.LayoutParams param_layout_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2);
		layout_content.setLayoutParams(param_layout_content);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT,1);
		params.rightMargin = 5;
		for (int i = 0; i < ITEM_SIZE; i++) {
			itemViews[i] = new FindItemView(context);
			itemViews[i] .setLayoutParams(params);
			layout_content.addView(itemViews[i]);
		}
		addView(layout_content);
		
		rightArrow = new ImageView(context);
		LinearLayout.LayoutParams param_rightArrow = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_rightArrow.gravity=Gravity.CENTER_VERTICAL;
		rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context, R.drawable.cs_navigation_program_right_arrow));
		rightArrow.setLayoutParams(param_rightArrow);
		rightArrow.setScaleType(ScaleType.FIT_XY);
		addView(rightArrow);
	}

	private void gotoNext() {
		if (currentSelectedIndex < LabelNames.size() - 1) {
			currentSelectedIndex++;
			if (currentSelectedViewIndex < ITEM_SIZE - 1) {
				if (currentSelectedIndex - 1 == currentDisplayingIndex) {
					itemViews[currentSelectedViewIndex]
							.setCurrentStatus(Status.Dislaying);
				} else {
					itemViews[currentSelectedViewIndex]
							.setCurrentStatus(Status.Normal);
				}
				itemViews[++currentSelectedViewIndex]
						.setCurrentStatus(Status.Selected);
			} else {
				startIndex++;
				updateData();
			}
		}
	}

	private void gotoPre() {
		if (currentSelectedIndex > 0) {
			currentSelectedIndex--;
			if (currentSelectedViewIndex > 0) {
				if (currentSelectedIndex + 1 == currentDisplayingIndex) {
					itemViews[currentSelectedViewIndex]
							.setCurrentStatus(Status.Dislaying);
				} else {
					itemViews[currentSelectedViewIndex]
							.setCurrentStatus(Status.Normal);
				}
				itemViews[--currentSelectedViewIndex]
						.setCurrentStatus(Status.Selected);
			} else {
				startIndex--;
				updateData();
			}
		}
	}

	private void updateArrow(){
		if(startIndex > 0){
			leftArrow.setVisibility(View.VISIBLE);
		}else{
			leftArrow.setVisibility(View.INVISIBLE);
		}
		if(LabelNames.size() > startIndex + ITEM_SIZE){
			rightArrow.setVisibility(View.VISIBLE);
		}else{
			rightArrow.setVisibility(View.INVISIBLE);
		}
	}
	
	public void setDisplay(){
		itemViews[currentSelectedViewIndex]
				.setCurrentStatus(Status.Dislaying);
	}
	
	public void resetDisplay(){
		itemViews[currentDisplayingIndex - startIndex]
				.setCurrentStatus(Status.Normal);
		currentDisplayingIndex=0;
		currentSelectedIndex = 0;
		currentSelectedViewIndex = 0;
		startIndex = 0;
		
		int len=LabelNames.size();
		if(len>ITEM_SIZE){
			len=ITEM_SIZE;
		}
		
		for (int i = 0; i < len; i++) {
			itemViews[i].setText(LabelNames.get(i));
		}
		updateArrow();
		setDisplay();
	}
	
	public String getContent(){
		if(currentDisplayingIndex==0&&isCategory==false){
			return "";
		}else{
			return itemViews[currentDisplayingIndex - startIndex].getText().toString();
		}
	}
	
	private void updateData(){
		currentLabelNames.clear();
		for(int i = 0, j = startIndex; i<ITEM_SIZE; i++, j++){
			itemViews[i].setText(LabelNames.get(j));
			currentLabelNames.add(LabelNames.get(j));
			itemViews[i].setCurrentStatus(Status.Normal);
			if(j == currentDisplayingIndex){
				itemViews[i].setCurrentStatus(Status.Dislaying);
			}
		}
		itemViews[currentSelectedViewIndex].setCurrentStatus(Status.Selected);
		updateArrow();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (LabelNames == null || LabelNames.size() < 1) {
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
			updateDisplaying();
			currentDisplayingIndex = currentSelectedIndex;
			if(findViewListener!=null){
				findViewListener.onItemClick(getContent(), isCategory);
			}
			return true;
		default:
			return false;
		}

	}

	private void updateDisplaying(){
		for(int i = startIndex; i<startIndex+ITEM_SIZE; i++){
			if(currentDisplayingIndex == i && currentDisplayingIndex != currentSelectedIndex){
				itemViews[currentDisplayingIndex - startIndex].setCurrentStatus(Status.Normal);
			}
		}
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if (LabelNames == null || LabelNames.size() < 1) {
			return;
		}
		if (gainFocus) {
			if(currentSelectedViewIndex==currentDisplayingIndex){
			itemViews[currentSelectedViewIndex]
						.setCurrentStatus(Status.Selected_Dislaying);
			}else{
			itemViews[currentSelectedViewIndex]
					.setCurrentStatus(Status.Selected);
			}
		} else {
			if (currentSelectedIndex == currentDisplayingIndex) {
				itemViews[currentSelectedViewIndex]
						.setCurrentStatus(Status.Dislaying);
			} else {
				itemViews[currentSelectedViewIndex]
						.setCurrentStatus(Status.Normal);
			}
		}

		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

}
