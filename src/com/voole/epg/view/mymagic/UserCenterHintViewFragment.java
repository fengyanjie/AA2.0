package com.voole.epg.view.mymagic;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.movie.MovieViewListener;

public class UserCenterHintViewFragment extends Fragment {
	private UserCenterHintView hintView = null;
	
	private MovieViewListener listener = null;
	public void setMovieViewListener(MovieViewListener listener){
		this.listener = listener;
	}
	
	public UserCenterHintViewFragment() {
		super();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		hintView = new UserCenterHintView(getActivity());
		LogUtil.d("UserCenterHintViewFragment------------onCreateView");
		if (listener != null) {
			hintView.setMovieViewListener(listener);
		}
		return hintView;
	}
	
	public void setData(List<Film> films){
		hintView.setData(films);
	}
	
	public void setHint(String string){
		hintView.setHint(string);
	}
}
