package com.voole.epg.view.movies.live;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.voole.epg.R;
import com.voole.epg.base.BaseFrameLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.tvutils.ImageManager;

public class LiveBigItemView extends BaseFrameLayout {
	private ImageView life_img;
	private TextView life_tv;

	public LiveBigItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LiveBigItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LiveBigItemView(Context context) {
		super(context);
		init();
	}

	private void init() {
		RelativeLayout layout = new RelativeLayout(mContext);
		RelativeLayout.LayoutParams layout_params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(layout_params);
		addView(layout);

		life_img = new ImageView(mContext);
		RelativeLayout.LayoutParams param_img = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		life_img.setLayoutParams(param_img);
		life_img.setPadding(0, 0, 2, 3);
		life_img.setScaleType(ScaleType.FIT_XY);
		life_img.setBackgroundResource(R.drawable.live_big_yinying);
		life_img.setImageResource(R.drawable.live_big);
		layout.addView(life_img);

		life_tv = new TextView(mContext);
		RelativeLayout.LayoutParams param_tv = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		param_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		life_tv.setGravity(Gravity.CENTER_VERTICAL);
		life_tv.setPadding(10, 0, 5, 0);
		life_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeWidthSize(30));
		life_tv.setTextColor(Color.WHITE);
		life_tv.setBackgroundColor(Color.parseColor("#b4000000"));
		life_tv.setLayoutParams(param_tv);
		life_tv.setVisibility(View.INVISIBLE);
		layout.addView(life_tv);
	}

	public void setData(FilmClass fc) {
		ImageManager.GetInstance().displayImage(fc.getBigImgUrl(), life_img);
		life_tv.setVisibility(View.VISIBLE);
		life_tv.setText(fc.getFilmClassName());
	}

	public void clearData() {
		life_img.setImageResource(R.drawable.live_big);
		life_tv.setVisibility(View.INVISIBLE);
		life_tv.setText("");
	}

}
