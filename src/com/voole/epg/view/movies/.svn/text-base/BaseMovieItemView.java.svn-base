package com.voole.epg.view.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

import com.voole.epg.R;
import com.voole.epg.base.BaseView;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.tvutils.ImageManager;
import com.voole.tvutils.ImageManager.ImageListener;

public class BaseMovieItemView extends BaseView{
	protected Film data = null;
	protected boolean isSelected = false;
	private Animation scaleOut = null;
	private Animation scaleIn = null;
	protected Bitmap poster = null;
	public BaseMovieItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public BaseMovieItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public BaseMovieItemView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
//		setFocusable(true);
//		setFocusableInTouchMode(true);
	}
	
	public void setData(Film film){
		this.data = film;
//		this.invalidate();
		ImageManager.GetInstance().displayImage(film.getImgUrl(), this,new ImageListener() {
			@Override
			public void onLoadingStarted(String uri, View view) {
				 BitmapFactory.decodeResource(getResources(), R.drawable.cs_recommend_toprank_loading);
			}
			@Override
			public void onLoadingFailed(String uri, View view) {
				
			}
			@Override
			public void onLoadingComplete(String uri, View view, Bitmap bitmap) {
				poster = bitmap;
				BaseMovieItemView.this.invalidate();
			}
		});
	}
	
	public Film getData(){
		return this.data;
	}
	
	public void setSelectedItem(boolean isSelected){
		if(isSelected){
			if(scaleOut != null){
//		        startAnimation(scaleOut);
			}
	        this.isSelected = true;
		}else{
			this.isSelected = false;
			if(scaleIn != null){
//		        startAnimation(scaleIn);
			}
		}
		invalidate();
	}
	
	public void setScaleOut(Animation scaleOut) {
		this.scaleOut = scaleOut;
	}

	public void setScaleIn(Animation scaleIn) {
		this.scaleIn = scaleIn;
	}
}
