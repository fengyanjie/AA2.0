package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.detail.SingleLineMovieView;
import com.voole.epg.view.movies.movie.MovieViewListener;

public class UserCenterHintView extends BaseLinearLayout{
	private static final int MOVIEITEMS = 6;
	
	private TextView hint = null;
	private SingleLineMovieView movieView = null;
	
	public void setMovieViewListener(MovieViewListener movieViewListener){
		if (movieViewListener != null && movieView != null) {
			movieView.setMovieViewListener(movieViewListener);
		}
	}

	public UserCenterHintView(Context context) {
		super(context);
		init(context);
	}

	public UserCenterHintView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public UserCenterHintView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		this.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setLayoutParams(param);
		
		hint = new TextView(context);
		hint.setGravity(Gravity.CENTER);
		hint.setTextColor(Color.WHITE);
		hint.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(55));
		LinearLayout.LayoutParams param_hint = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		hint.setLayoutParams(param_hint);
		addView(hint);
		
		movieView = new SingleLineMovieView(context, MOVIEITEMS,16,84,24);
		LinearLayout.LayoutParams param_movie = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_movie.bottomMargin = DisplayManager.GetInstance().changeHeightSize(45);
		movieView.setLayoutParams(param_movie);
		movieView.setDisplayArrow(false);
		addView(movieView);
	}
	
	public void setHint(String string){
		hint.setText(string);
	}
	
	public void setData(List<Film> films){
		if (films != null ) {
			if (films.size() >6) {
				films = films.subList(0, 6);
				LogUtil.d("UserCenterHintView----setData----filmName=" + films.get(0).getFilmName() );
			}
			movieView.setData(films);
		}
	}
	
	
}
