package com.voole.epg.view.mymagic;

import com.voole.epg.view.mymagic.RegisterView.RegisterViewListener;
import com.voole.epg.view.mymagic.ResetPwdView.ResetPwdViewListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment") public class UserManagementFragment extends Fragment {

	private UserManagementView userManagementView = null;
	private UserInfoViewListener userInfoViewListener;
	private RegisterViewListener registerViewListener;
	private ResetPwdViewListener resetPwdViewListener;
	
	public UserManagementView getUserManagementView() {
		return userManagementView;
	}

	private boolean isRegister = false;
	
	public UserManagementFragment (boolean isRegister){
		this.isRegister = isRegister;
	}

	public void setUserInfoViewListener(UserInfoViewListener listener) {
		this.userInfoViewListener = listener;
	}

	public void setRegisterViewListener(RegisterViewListener listener) {
		this.registerViewListener = listener;
	}

	
	public void setResetPwdViewListener(ResetPwdViewListener listener) {
		this.resetPwdViewListener = listener;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		userManagementView = new UserManagementView(getActivity(),isRegister);
		if (userInfoViewListener != null) {
			userManagementView.setUserInfoViewListener(userInfoViewListener);
		}
		if (registerViewListener != null) {
			userManagementView.setRegisterViewListener(registerViewListener);
		}
		if(resetPwdViewListener!=null){
			userManagementView.setResetPwdViewListener(resetPwdViewListener);
		}
		
		userManagementView.showLeftContent(isRegister);
		return userManagementView;
	}

	public void setUserInfo(String account,String email,String mobile){
		userManagementView.setUserInfo(account, email, mobile);
		
	}
	
	public void updateName(String account){
		userManagementView.updateName(account);
	}
	
	public void updateEmail(String email){
		userManagementView.updateEmail(email);
	}
	
	public void updateMobile(String mobile){
		userManagementView.updateMobile(mobile);
	}
	
	public int getLeftWidth() {
		return userManagementView.getLeftViewWidt();
	}
	
	public void getCodeEnable(int time){
		userManagementView.getCodeEnable(time);
	}
	
	public void getCodeDisable(){
		userManagementView.getCodeDisable();
	}
	
	/*public void showLeftContent(int[] indexs){
		userManagementView.showLeftContent(indexs);
	}*/
}
