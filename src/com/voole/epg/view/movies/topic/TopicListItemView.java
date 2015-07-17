package com.voole.epg.view.movies.topic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.tvutils.ImageManager;

public class TopicListItemView extends BaseLinearLayout {

	private ImageView imageView;
	private TextView textView;

	public TopicListItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public TopicListItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TopicListItemView(Context context) {
		super(context);
		init(context);
	}

	public void setData(FilmClass fc) {
		imageView.setVisibility(View.VISIBLE);
		ImageManager.GetInstance().displayImage(fc.getBigImgUrl(), imageView);
		textView.setVisibility(View.VISIBLE);
		textView.setText(fc.getFilmClassName());

	}

	private void init(Context context) {
		// RelativeLayout layout = new RelativeLayout(mContext);
		// RelativeLayout.LayoutParams layout_params = new
		// RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.MATCH_PARENT,
		// RelativeLayout.LayoutParams.MATCH_PARENT);
		// layout_params.setMargins(20, 20, 20, 20);
		// layout.setLayoutParams(layout_params);
		// // addView(layout);
		//
		// imageView = new ImageView(mContext);
		// RelativeLayout.LayoutParams param_img = new
		// RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.MATCH_PARENT,
		// RelativeLayout.LayoutParams.MATCH_PARENT);
		// param_img.bottomMargin = 37;
		// imageView.setLayoutParams(param_img);
		// imageView.setPadding(0, 0, 5, 5);
		// imageView.setScaleType(ScaleType.FIT_XY);
		// imageView.setBackgroundResource(R.drawable.live_in9_yinying);
		// imageView.setImageResource(R.drawable.p2);
		// layout.addView(imageView);
		//
		// textView = new TextView(mContext);
		// RelativeLayout.LayoutParams param_tv = new
		// RelativeLayout.LayoutParams(
		// RelativeLayout.LayoutParams.MATCH_PARENT, 40);
		// param_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		// textView.setText("时尚装苑");
		// textView.setGravity(Gravity.CENTER_VERTICAL);
		// textView.setPadding(10, 0, 0, 5);
		// textView.setTextSize(DisplayManager.GetInstance().changeWidthSize(22));
		// textView.setTextColor(Color.WHITE);
		// textView.setBackgroundResource(R.drawable.zhuanti_title_bg);
		// textView.setLayoutParams(param_tv);
		// layout.addView(textView);
		//
		// addView(layout);
		// // this.setBackgroundColor(Color.TRANSPARENT);
		setOrientation(VERTICAL);
		LinearLayout.LayoutParams img_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT, 1);
		imageView = new ImageView(context);
		imageView.setLayoutParams(img_params);
		imageView.setPadding(0, 0, 3, 5);
		imageView.setScaleType(ScaleType.FIT_XY);
		imageView.setBackgroundResource(R.drawable.live_in9_yinying);
		imageView.setVisibility(View.INVISIBLE);
		addView(imageView);

		LinearLayout.LayoutParams txt_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT, 5);
		textView = new TextView(mContext);
		/*
		 * RelativeLayout.LayoutParams param_tv = new
		 * RelativeLayout.LayoutParams(
		 * RelativeLayout.LayoutParams.MATCH_PARENT, 50);
		 * param_tv.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		 */
		txt_params.setMargins(0, -5, 0, 0);
		textView.setGravity(Gravity.CENTER_VERTICAL);
		textView.setPadding(10, 0, 0, 5);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
		textView.setTextColor(Color.WHITE);
		textView.setBackgroundResource(R.drawable.zhuanti_title_bg);
		textView.setLayoutParams(txt_params);
		textView.setVisibility(View.INVISIBLE);
		setPadding(8, 8, 8, 8);
		addView(textView);
	}

	public void clearData() {
		imageView.setVisibility(View.INVISIBLE);
		textView.setVisibility(View.INVISIBLE);

	}

	public void OnItemSeletced(boolean selected) {
		if (selected) {
			this.setBackgroundResource(R.drawable.cs_rank_selected_bg);
		} else {
			this.setBackgroundColor(Color.TRANSPARENT);
		}

	}

}
