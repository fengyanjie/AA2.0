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

public class LiveItemView extends BaseFrameLayout {

	private ImageView life_img;
	private TextView life_tv;
	private boolean isNoEmpty = false;

	public boolean isNoEmpty() {
		return isNoEmpty;
	}

	public void setNoEmpty(boolean isNoEmpty) {
		this.isNoEmpty = isNoEmpty;
	}

	public LiveItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LiveItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public LiveItemView(Context context) {
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
		life_img.setBackgroundResource(R.drawable.live_in9_yinying);
		layout.addView(life_img);

		life_tv = new TextView(mContext);
		RelativeLayout.LayoutParams param_tv = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		param_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		life_tv.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
		life_tv.setPadding(10, 0, 0, 0);
		life_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeWidthSize(20));
		life_tv.setTextColor(Color.WHITE);
		life_tv.setBackgroundColor(Color.parseColor("#c8000000"));
		life_tv.setLayoutParams(param_tv);
		life_tv.setVisibility(View.INVISIBLE);
		layout.addView(life_tv);
	}

	public void setImg(int index) {
		switch (index) {
		case 0:
		case 5:
		case 7:
			life_img.setImageResource(R.drawable.live_y);
			break;
		case 1:
		case 8:
			life_img.setImageResource(R.drawable.live_g);
			break;
		case 2:
		case 3:
			life_img.setImageResource(R.drawable.live_r);
			break;
		case 4:
		case 6:
			life_img.setImageResource(R.drawable.live_b);
			break;
		default:
			break;
		}
	}

	public void setData(FilmClass fc, final int index) {
		ImageManager.GetInstance().displayImage(fc.getSmallImgUrl(), life_img);
		life_tv.setVisibility(View.VISIBLE);
		life_tv.setText(fc.getFilmClassName());
	}

	public void clearData(int index) {
		setImg(index);
		this.isNoEmpty = false;
		life_tv.setVisibility(View.INVISIBLE);
		life_tv.setText("");
	}

}
