package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.view.mymagic.UserManagementLeftView.UserManagementLeftItemView.CurrentState;

public class UserManagementLeftView extends  BaseLinearLayout {
	
	private static final int REGISTER = 1;
	private static final int USER_INFO = 2;
	private static final int RESET_PASSWORD = 3;
	
	private static final int ITEM_SIZE = 3;
	
	private int currentSelected = 0; //当前选中
	private int currentFocused = -1;  //当前焦点
	private UserManagementLeftItemView[] itemViews = null;
	private static final int[] itemNames = {
		R.string.mymagic_um_register,
		R.string.mymagic_um_userinfo,
		R.string.mymagic_um_reset_password
	};
	private static final int[] itemID = {
		REGISTER,
		USER_INFO,
		RESET_PASSWORD
	};
	
	protected UserManagementItemSelectedListener listener = null;
	
	public void setUserManagementItemSelectedListener(UserManagementItemSelectedListener listener){
		this.listener = listener;
	}

	public UserManagementLeftView(Context context) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		setBackgroundResource(R.drawable.cs_mymagic_consumer_left_bg);
		setOrientation(VERTICAL);
		init(context);
		updateStatuesUI(currentSelected, currentFocused);
	}
	
	
	
	private void init(Context context){
		setPadding(0, 30, 0,30);
		itemViews = new UserManagementLeftItemView[ITEM_SIZE +5];
		LinearLayout.LayoutParams param_content_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,1);
		param_content_item.topMargin = 2;
		param_content_item.gravity = Gravity.CENTER_HORIZONTAL;
		for(int i = 0; i < ITEM_SIZE + 5; i++){
			itemViews[i] = new UserManagementLeftItemView(context);
			itemViews[i].setLayoutParams(param_content_item);
			if (i < ITEM_SIZE) {
				itemViews[i].setText(itemNames[i]);
				itemViews[i].setTag(itemID[i]);
			}else {
				itemViews[i].setVisibility(INVISIBLE);
			}
			addView(itemViews[i]);
		}
		showLeftContent(true);
	}
	
	public void showLeftContent(boolean isRegister){
		if (isRegister) {
			currentSelected=1;
			itemViews[0].setVisibility(GONE);
			itemViews[1].setVisibility(VISIBLE);
			itemViews[2].setVisibility(VISIBLE);
		}else {
			currentSelected=0;
			itemViews[0].setVisibility(VISIBLE);
			itemViews[1].setVisibility(GONE);
			itemViews[2].setVisibility(GONE);
		}
	}
	
	public void showLeftContent(int[] indexs){
		for (int i = 0; i < indexs.length; i++) {
			itemViews[i].setVisibility(INVISIBLE);
		}
	}
	
	/**
	 * 
	 * @param currentSelected  选中
	 * @param currentFocused   焦点
	 */
	private void updateStatuesUI(int currentSelected,int currentFocused){
		for (int i = 0; i < ITEM_SIZE; i++) {
			if (currentSelected == i) {
				if (currentFocused == i) {
					itemViews[i].setStates(CurrentState.selected_hasFocus);
				}else {
					itemViews[i].setStates(CurrentState.selected_noFocus);
				}
			}else {
				if (currentFocused == i) {
					itemViews[i].setStates(CurrentState.unselected_haFocus);
				}else {
					itemViews[i].setStates(CurrentState.unselected_noFocus);
				}
			}
		}
	}
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (currentFocused < ITEM_SIZE -1 ) {
				currentFocused++;
				updateStatuesUI(currentSelected, currentFocused);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_UP:
			if (currentFocused > 0) {
				currentFocused--;
				updateStatuesUI(currentSelected, currentFocused);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (currentFocused >=0 && currentFocused <= ITEM_SIZE -1) {
				currentSelected = currentFocused;
				updateStatuesUI(currentSelected, currentFocused);
				//接口回调
				if (listener != null) {
					listener.onItemSelected(itemViews[currentSelected]);
				}
			}
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if(gainFocus){
			currentFocused = 0;
			updateStatuesUI(currentSelected, currentFocused);
		}else{
			updateStatuesUI(currentSelected, -1);
		}
	}
	
	
	/**
	 * item
	 * @author Administrator
	 *
	 */
	public static class UserManagementLeftItemView extends TextView{
		
		public enum CurrentState{
			unselected_noFocus,
			unselected_haFocus,
			selected_noFocus,
			selected_hasFocus
		}
		
		private CurrentState currentStatue = CurrentState.unselected_haFocus;

		public UserManagementLeftItemView(Context context) {
			super(context);
			init(context);
		}
		
		private void init(Context context){
			setGravity(Gravity.CENTER);
			setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(26));
			refresh();
			setStates(CurrentState.selected_hasFocus);
		}
		
		public void setStates(CurrentState currentState){
			this.currentStatue = currentState;
			refresh();
		}
		
		private void refresh(){
			switch (currentStatue) {
			case unselected_noFocus:
				setBackgroundResource(0);
				setTextColor(Color.WHITE);
				break;
			case unselected_haFocus:
				setBackgroundResource(R.drawable.cs_mymagic_consumer_left_unselected_focused);
				setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
				break;
			case selected_noFocus:
				setBackgroundResource(R.drawable.cs_mymagic_consumer_left_selected_unfocus);
				setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
				break;
			case selected_hasFocus:
				setBackgroundResource(R.drawable.cs_mymagic_consumer_left_selected_hasfocus);
				setTextColor(Color.WHITE);
				break;
			default:
				break;
			}
		}
	}
	 
	public interface UserManagementItemSelectedListener{
		public void onItemSelected(UserManagementLeftItemView leftItemView);
	}
	
}
