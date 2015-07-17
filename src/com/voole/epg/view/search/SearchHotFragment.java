package com.voole.epg.view.search;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SearchHotFragment extends Fragment {
	private HotView hotView = null;
	
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
		hotView = new HotView(getActivity());
		return hotView;
	}
	
	@Override
	public void onResume() {
		Log.d("SearchHotFragment", "---onResume");
		super.onResume();
	}
	
	@Override
	public void onStart() {
		Log.d("SearchHotFragment", "---onStart");
		super.onStart();
	}
	
	@Override
	public void onStop() {
		Log.d("SearchHotFragment", "---onStop");
		super.onStop();
	}
	
	@Override
	public void onDestroy() {
		Log.d("SearchHotFragment", "---onDestroy");
		super.onDestroy();
	}
}
