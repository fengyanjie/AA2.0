package com.voole.epg.view.mymagic;

import com.voole.epg.view.mymagic.ConsumerCenterView.ConsumerCenterUpdataListner;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment") public class ConsumerCenterFragment extends Fragment {
	private ConsumerCenterView consumerCenterView = null;
	
	public void showAli(){
		if(consumerCenterView!=null){
			consumerCenterView.showAli();
		}
	}
	
	private int currentIndex = 0;
	
	public ConsumerCenterFragment() {
		super();
	}
	
	public ConsumerCenterFragment(int currentIndex) {
		super();
		this.currentIndex = currentIndex;
	}
	
	private OrderListener orderlistener = null;
	public void setOrderListener(OrderListener listener){
		this.orderlistener = listener;
	}
	
	private RechargeListener rechargeListener = null;
	public void setRechargeListener(RechargeListener listener){
		this.rechargeListener = listener;
	}
	
	private ConsumerCenterUpdataListner consumerCenterUpdataListner = null;
	public void setConsumerCenterUpdataListner(ConsumerCenterUpdataListner listner){
		this.consumerCenterUpdataListner = listner;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		consumerCenterView = new ConsumerCenterView(getActivity());
 		consumerCenterView.setLeftViewPosition(currentIndex);
		if (orderlistener != null) {
			consumerCenterView.setOrderListener(orderlistener);
		}
		if (rechargeListener != null) {
			consumerCenterView.setRechargeListener(rechargeListener);
		}
		if (consumerCenterUpdataListner != null) {
			consumerCenterView.setConsumerCenterUpdataListner(consumerCenterUpdataListner);
		}
		return consumerCenterView;
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
	
	public ConsumerCenterView getConsumerCenterView(){
		return consumerCenterView;
	}
	
}
