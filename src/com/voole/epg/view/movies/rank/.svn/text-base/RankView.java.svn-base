package com.voole.epg.view.movies.rank;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore.RightsStatus;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.view.movies.detail.MovieDetailActivity;
import com.voole.epg.view.movies.movie.MovieActivity;

public class RankView extends BaseLinearLayout {

	private RankItemView[] movie_item;
	private RankItemView[] tv_item;
	private List<Film> movie_data = null;
	private List<Film> tv_data = null;
	private int ITEM_SIZE = 10;

	private int selectedItemIndex = 0;

	private int selected = -1;
	private final int selectedLeft = 0;
	private final int selectedRight = 1;

	private int leftLen = 0;
	private int rightLen = 0;

	public interface RankViewListener {
		public void OnItemSelected(Film film);

	}

	private RankViewListener rankViewListener = null;

	public void setRankViewListener(RankViewListener rankViewListener) {
		this.rankViewListener = rankViewListener;
	}

	public void setLeftData(List<Film> data) {
		movie_data = data;
		leftLen = movie_data.size();
		for (int i = 0; i < leftLen; i++) {
			movie_item[i].setData(i+1, movie_data.get(i));
		}
	}

	public void setRightData(List<Film> data) {
		tv_data = data;
		rightLen = tv_data.size();
		for (int i = 0; i < rightLen; i++) {
			tv_item[i].setData(i+1, tv_data.get(i));
		}
	}

	public RankView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RankView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RankView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		 setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true); 
		setOrientation(HORIZONTAL);
		movie_item = new RankItemView[ITEM_SIZE];
		tv_item = new RankItemView[ITEM_SIZE];
		selected = selectedLeft;
		initLeft(context);
		initMiddle(context);
		initRight(context);
	}

	private void initLeft(Context context) {
		LinearLayout layout_left = new LinearLayout(context);
		layout_left.setPadding(0, 0, 0, 25);
		layout_left.setOrientation(VERTICAL);
		LinearLayout.LayoutParams params_left = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_left.setLayoutParams(params_left);
		addView(layout_left);

		ImageView img_movie = new ImageView(context);
		LinearLayout.LayoutParams params_img = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params_img.gravity = Gravity.CENTER_HORIZONTAL;
		params_img.topMargin=30;
		img_movie.setLayoutParams(params_img);
		img_movie.setImageResource(R.drawable.cs_rank_movie);
		layout_left.addView(img_movie);
		
		LinearLayout.LayoutParams param_text = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_text.leftMargin = DisplayManager.GetInstance().changeTextSize(30);
		param_text.rightMargin = DisplayManager.GetInstance()
				.changeTextSize(30);
		param_text.topMargin = -10;
		param_text.topMargin = -10;
		for (int i = 0; i < ITEM_SIZE; i++) {
			movie_item[i] = new RankItemView(context);
			movie_item[i].setLayoutParams(param_text);
			layout_left.addView(movie_item[i]);
		}

	}

	private void initMiddle(Context context) {
		LinearLayout.LayoutParams params_line = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params_line.gravity = Gravity.CENTER_VERTICAL;
		ImageView img_line = new ImageView(context);
		img_line.setImageResource(R.drawable.cs_rank_line);
		img_line.setLayoutParams(params_line);
		addView(img_line);
	}

	private void initRight(Context context) {
		LinearLayout layout_right = new LinearLayout(context);
		layout_right.setOrientation(VERTICAL);
		layout_right.setPadding(0, 0, 0, 25);
		LinearLayout.LayoutParams params_right = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_right.setLayoutParams(params_right);
		addView(layout_right);

		ImageView img_tv = new ImageView(context);
		LinearLayout.LayoutParams params_img = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params_img.gravity = Gravity.CENTER_HORIZONTAL;
		params_img.topMargin=30;
		img_tv.setLayoutParams(params_img);
		img_tv.setImageResource(R.drawable.cs_rank_tv);
		layout_right.addView(img_tv);
		
		LinearLayout.LayoutParams param_text = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_text.leftMargin = DisplayManager.GetInstance().changeTextSize(30);
		param_text.rightMargin = DisplayManager.GetInstance()
				.changeTextSize(30);
		param_text.topMargin = -10;
		param_text.topMargin = -10;
		for (int i = 0; i < ITEM_SIZE; i++) {
			tv_item[i] = new RankItemView(context);
			tv_item[i].setLayoutParams(param_text);
			layout_right.addView(tv_item[i]);
		}
	}


	 @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (selectedItemIndex > 0) {
				if (selected == selectedLeft) {
					movie_item[selectedItemIndex].setFocused(false);
					selectedItemIndex -= 1;
					movie_item[selectedItemIndex].setFocused(true);
				} else if (selected == selectedRight) {
					tv_item[selectedItemIndex].setFocused(false);
					selectedItemIndex -= 1;
					tv_item[selectedItemIndex].setFocused(true);
				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (selected == selectedLeft) {
				if (selectedItemIndex < leftLen - 1) {
					movie_item[selectedItemIndex].setFocused(false);
					selectedItemIndex += 1;
					movie_item[selectedItemIndex].setFocused(true);
					return true;
				}
			} else if (selected == selectedRight) {
				if (selectedItemIndex < rightLen - 1) {
					tv_item[selectedItemIndex].setFocused(false);
					selectedItemIndex += 1;
					tv_item[selectedItemIndex].setFocused(true);
					return true;
				}
			}
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (selected == selectedRight) {
				tv_item[selectedItemIndex].setFocused(false);
				movie_item[selectedItemIndex].setFocused(true);
				selected = selectedLeft;
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (selected == selectedLeft) {
				movie_item[selectedItemIndex].setFocused(false);
				tv_item[selectedItemIndex].setFocused(true);
				selected = selectedRight;
				return true;
			}

			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			if (selectedItemIndex >= 0 && rankViewListener != null) {
				if (selected == selectedLeft) {
					rankViewListener.OnItemSelected(movie_data
							.get(selectedItemIndex));
				} else if (selected == selectedRight) {
					rankViewListener.OnItemSelected(tv_data
							.get(selectedItemIndex));
				}
				return true;
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
		if (gainFocus) {
			 if(selected == selectedLeft){
				 movie_item[selectedItemIndex].setFocused(true);
			 }else if(selected == selectedRight){
				 tv_item[selectedItemIndex].setFocused(true);
			 }
		}else{
			 if(selected == selectedLeft){
				 movie_item[selectedItemIndex].setFocused(false);
			 }else if(selected == selectedRight){
				 tv_item[selectedItemIndex].setFocused(false);
			 }
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	} 
	
	private class RankItemView extends TextView {
		private Film film = null;
		public RankItemView(Context context) {
			super(context);
			init(context);
		}

		public RankItemView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public RankItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}
		
		private void init(Context context){
			setPadding(60, 0, 0, 0);
			setGravity(Gravity.CENTER_VERTICAL);
			setTextColor(Color.WHITE);
			setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
					.GetInstance().changeTextSize(27));
			setBackgroundDrawable(null);
		}
		
		public void setData(int index,Film film){
			this.film = film;
			if(index<10){
				setText(index + ".     "+ film.getFilmName());
			}else{
			setText(index + ".   "+ film.getFilmName());
			}
		}
		
		public void setFocused(boolean isFocused){
			if (film == null) {
				return;
			}
			if (isFocused) {
				setBackgroundResource(R.drawable.cs_rank_selected_bg);
				setTextSize(TypedValue.COMPLEX_UNIT_DIP,33);
				setTextColor(Color.rgb(0xe4, 0x7d, 0x38));
				setPadding(55, -5, 0, -5);
			} else {
				setBackgroundColor(Color.TRANSPARENT);
				setTextColor(Color.WHITE);
				setTextSize(TypedValue.COMPLEX_UNIT_DIP,27);
				setPadding(60, 0, 0, 0);
			}
			setGravity(Gravity.CENTER_VERTICAL);
		}
		
	}

}
