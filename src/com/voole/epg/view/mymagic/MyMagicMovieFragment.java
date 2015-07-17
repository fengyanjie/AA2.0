package com.voole.epg.view.mymagic;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.transscreen.ResumeAndPageInfo;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.epg.view.mymagic.MyMagicMovieView.ButtonStatus;

public class MyMagicMovieFragment extends Fragment {
	private MyMagicMovieView myMagicMovieView = null;

	private MovieEditedListener listener = null;

	public void setMovieEditedListener(MovieEditedListener listener) {
		this.listener = listener;
	}

	private MovieViewListener movieViewListener = null;

	public void setMovieViewListener(MovieViewListener movieViewListener) {
		this.movieViewListener = movieViewListener;
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
		myMagicMovieView = new MyMagicMovieView(getActivity());
		myMagicMovieView.setVisibility(View.INVISIBLE);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		myMagicMovieView.setLayoutParams(param);
		if (listener != null) {
			myMagicMovieView.setMovieEditedListener(listener);
		}
		if (movieViewListener != null) {
			myMagicMovieView.setMovieViewListener(movieViewListener);
		}
		return myMagicMovieView;
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
	public void onStop() {
		super.onStop();
	}

	public void showMagicMovieView(boolean isShow){
		if(isShow){
			myMagicMovieView.setVisibility(View.VISIBLE);
		}else{
			myMagicMovieView.setVisibility(View.INVISIBLE);
		}
	}
	
	public void setMovieData(FilmAndPageInfo filmAndPageInfo) {
		myMagicMovieView.setData(filmAndPageInfo);
//		myMagicMovieView.setCurrentStatus(ButtonStatus.unEdited);
		myMagicMovieView.setVisibility(View.VISIBLE);
	}

	public void setMovieData(ResumeAndPageInfo resumeAndPageInfo) {
		myMagicMovieView.setData(resumeAndPageInfo);
//		myMagicMovieView.setCurrentStatus(ButtonStatus.unEdited);
		myMagicMovieView.setVisibility(View.VISIBLE);
	}

	public void setShowPursueVideoBar(boolean isShowPursueVideoBar) {
		if (myMagicMovieView != null) {
			myMagicMovieView.showPursueVideoBar();
		}
	}

	public void setEdited(boolean isEdited) {
		myMagicMovieView.setEdited(isEdited);
	}
}
