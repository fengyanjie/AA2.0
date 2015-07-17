package com.voole.epg.view.mymagic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment") public class MessageCenterFragment extends Fragment {
	private MessageCenterView messageCenterView = null;
	private int currentIndex = 0;
	
	
	
	public MessageCenterFragment() {
		super();
	}
	
	public MessageCenterFragment(int currentIndex) {
		super();
		this.currentIndex = currentIndex;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		messageCenterView = new MessageCenterView(getActivity());
		messageCenterView.setLeftViewPosition(currentIndex);
		return messageCenterView;
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
	
	public MessageCenterView getMessageCenterView() {
		return messageCenterView;
	}

	public MessageEmailView getMessageEmailView(){
		return messageCenterView.getMessageEmailView();
	}
	
}
