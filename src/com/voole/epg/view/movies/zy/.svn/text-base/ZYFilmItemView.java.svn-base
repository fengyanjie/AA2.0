package com.voole.epg.view.movies.zy;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;

public class ZYFilmItemView extends BaseLinearLayout {

	private AlwaysMarqueeTextView txtFilmTitle;
	private ZYShouCangView txtFilmShouCang;
	private boolean leftSelected = false;
	private boolean rightSelected = false;

	public ZYFilmItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ZYFilmItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public ZYFilmItemView(Context context) {
		super(context);
		init(context);
	}

	public void setTitle(String title) {
		txtFilmTitle.setText(title);
		txtFilmShouCang.setVisibility(View.VISIBLE);
	}
	
	public void isFavorite(String isFavorite){
		txtFilmShouCang.isFavorite(isFavorite);
	}

	private void init(Context context) {
		setOrientation(HORIZONTAL);

		LinearLayout.LayoutParams param_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		txtFilmTitle = new AlwaysMarqueeTextView(context);
		txtFilmTitle.setTextColor(Color.WHITE);
		txtFilmTitle.setGravity(Gravity.CENTER_VERTICAL);
		txtFilmTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager
				.GetInstance().changeTextSize(30));
		txtFilmTitle.setPadding(20, 5, 10, 5);
		param_title.leftMargin = 10;
		txtFilmTitle.setLayoutParams(param_title);
		addView(txtFilmTitle);

		LinearLayout.LayoutParams param_shoucang = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
		param_shoucang.rightMargin = 20;
		txtFilmShouCang = new ZYShouCangView(context);
		txtFilmShouCang.setVisibility(View.INVISIBLE);
		txtFilmShouCang.setLayoutParams(param_shoucang);
		addView(txtFilmShouCang);

	}

	public void setSelectedItem(boolean left, boolean right) {
		leftSelected = left;
		rightSelected = right;
		updateUI();
	}

	private void updateUI() {
		if (leftSelected) {
			txtFilmTitle.setBackgroundResource(R.drawable.cs_zy_film_selected);
			txtFilmTitle.setPadding(20, 5, 20, 5);
			txtFilmTitle.setTextColor(Color.rgb(0xe4, 0x7b, 0x39));
			txtFilmTitle.setMarquee(true);
		} else {
			txtFilmTitle.setBackgroundColor(Color.TRANSPARENT);
			txtFilmTitle.setMarquee(false);
			txtFilmTitle.setTextColor(Color.WHITE);
		}

		if (rightSelected) {
			txtFilmShouCang.OnSelected(true);
		} else {
			txtFilmShouCang.OnSelected(false);
		}

	}

}
