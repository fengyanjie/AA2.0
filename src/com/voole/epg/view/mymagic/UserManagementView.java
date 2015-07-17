package com.voole.epg.view.mymagic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.view.mymagic.RegisterView.RegisterViewListener;
import com.voole.epg.view.mymagic.ResetPwdView.ResetPwdViewListener;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemSelectedListener;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemView;

public class UserManagementView extends BaseLinearLayout {

//	private UserManagementLeftView leftView;
	private UserCenterLeftView leftView;
	private RegisterView registerView;
	private UserInfoView userInfoView;
	private ResetPwdView resetPwdView;
	
	private boolean isRegister = false;

	int titleHeight = -1;
	
	private static final int[] itemNamesUnregister = {
		R.string.mymagic_um_register,
	};
	
	private static final int[] itemNamesRegister = {
		R.string.mymagic_um_userinfo,
		R.string.mymagic_um_reset_password
	};

	public int getLeftViewWidt() {
		return titleHeight;
	}

	public void setUserInfoViewListener(UserInfoViewListener listener) {
		if (listener != null) {
			userInfoView.setUserInfoViewListener(listener);
		}
	}

	public void setRegisterViewListener(RegisterViewListener listener) {
		if (listener != null) {
			registerView.setRegisterViewListener(listener);
		}
	}

	public void setResetPwdViewListener(ResetPwdViewListener listener) {
		if (listener != null) {
			resetPwdView.setResetPwdViewListener(listener);
		}
	}

	public UserManagementView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public UserManagementView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public UserManagementView(Context context,boolean isRegister) {
		super(context);
		this.isRegister = isRegister;
		init(context);
	}

	private void init(Context context) {
		setOrientation(HORIZONTAL);
		leftView = new UserCenterLeftView(context);
		leftView.setId(ID.MyMagicActivity.UM_LEFT_VIEW_NAVIGATION_ID);
		LinearLayout.LayoutParams param_left = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,43);
		param_left.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(35);
		param_left.bottomMargin = DisplayManager.GetInstance().changeHeightSize(55);
		leftView.setLayoutParams(param_left);
		leftView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						int[] position = new int[2];
						leftView.getLocationInWindow(position);
						titleHeight = leftView.getWidth() + position[1];
						leftView.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
					}
				});

		addView(leftView);

		RelativeLayout layout_right = new RelativeLayout(context);
		LinearLayout.LayoutParams param_right = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,10);
		param_right.rightMargin = DisplayManager.GetInstance().changeWidthSize(
				30);
		param_right.bottomMargin = DisplayManager.GetInstance().changeHeightSize(55);
		layout_right
				.setBackgroundResource(R.drawable.cs_mymagic_consumer_right_bg);
		layout_right.setLayoutParams(param_right);

		registerView = new RegisterView(context);
		layout_right.addView(registerView);

		userInfoView = new UserInfoView(context);
		layout_right.addView(userInfoView);

		resetPwdView = new ResetPwdView(context);
		layout_right.addView(resetPwdView);

		addView(layout_right);

		addViewListener();
	}

	private void showRegisterView() {
		registerView.setVisibility(View.VISIBLE);
		userInfoView.setVisibility(View.GONE);
		resetPwdView.setVisibility(View.GONE);
	}

	private void showUserInfoView() {
		userInfoView.setVisibility(View.VISIBLE);
		registerView.setVisibility(View.GONE);
		resetPwdView.setVisibility(View.GONE);
	}

	private void showResetPwdView() {
		resetPwdView.setVisibility(View.VISIBLE);
		userInfoView.setVisibility(View.GONE);
		registerView.setVisibility(View.GONE);
	}

	public void clearPwd(){
		resetPwdView.clearPwd();
	}
	
	private void showRightView(int currentPosition) {
		switch (currentPosition) {
		case 0:
			if (isRegister) {
				showUserInfoView();
				leftView.setNextFocusRightId(ID.MyMagicActivity.UM_USERINFO_EMAIL_CHANGE_BTN_ID);
			}else {
				showRegisterView();
				leftView.setNextFocusRightId(ID.MyMagicActivity.UM_REGISTER_VIEW_NAME_ID);
			}
			break;
		case 1:
			if (isRegister) {
				showResetPwdView();
				leftView.setNextFocusRightId(ID.MyMagicActivity.UM_USERINFO_PWD_RESET_INPUT_ID);
			}
			break;
		}
	}

	private void addViewListener() {
		if (leftView != null) {
			leftView.setUserCenterLeftItemSelectedListener(new UserCenterLeftItemSelectedListener() {
				@Override
				public void onItemSelected(UserCenterLeftItemView leftItemView) {
					int currentPosition = (Integer) leftItemView.getTag();
					showRightView(currentPosition);
				}
			});
		}
	}

	public void showLeftContent(boolean isRegister) {
		this.isRegister = isRegister;
		leftView.setSelectedIndex(0);
		if (isRegister) {
			leftView.setData(itemNamesRegister);
			showRightView(0);
		} else {
			leftView.setData(itemNamesUnregister);
			showRightView(0);
		}
	}

	
	public void setUserInfo(String account,String email,String mobile){
		userInfoView.setData(account, email, mobile);
	}
	
	public void updateName(String account){
		userInfoView.updateName(account);
	}
	
	public void updateEmail(String email){
		userInfoView.updateEmail(email);
	}
	
	public void updateMobile(String mobile){
		userInfoView.updateMobile(mobile);
	}
	
	public void getCodeEnable(int time){
		registerView.ClickEnable(time);
	}
	
	public void getCodeDisable(){
		registerView.ClickDisable();
	}
	
	/*public void showLeftContent(int[] indexs) {
		leftView.showLeftContent(indexs);
	}*/

}
