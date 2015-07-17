package com.voole.epg.view.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.PosterImage;
import com.voole.tvutils.ImageUtil;

public abstract class RecommendItemBaseView extends BaseRelativeLayout {
	private static final int MARGIN = 20;
	private static final int TEXT_SIZE = DisplayManager.GetInstance()
			.changeTextSize(24);
	private LinearLayout layout_base = null;
	private ImageView title = null;
	protected PosterImage poster = null;
	protected TextView text1 = null;
	protected TextView text2 = null;
	protected TextView text3 = null;
	protected TextView text4 = null;

	protected Bitmap bmp_title = null;
	protected int bmp_bg_id = 0;
	protected int bmp_bg_foucs_id = 0;
	protected int poster_img=0;
	
	protected Bitmap poster_bit=null;
	
	RelativeLayout.LayoutParams param_unfocus = null;
	
	RelativeLayout.LayoutParams param_focus =null;
	

	private ItemSelectedListener itemSelectedListener = null;

	public void setOnItemSelectedListener(//选项的监听
			ItemSelectedListener itemSelectedListener) {
		this.itemSelectedListener = itemSelectedListener;
	}

	public RecommendItemBaseView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RecommendItemBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RecommendItemBaseView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		layout_base = new LinearLayout(context);
		param_unfocus = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_focus = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_unfocus.setMargins(10, 10, 7, 0);
		param_focus.setMargins(5, 5, 2, -2);
		layout_base.setLayoutParams(param_unfocus);
		addView(layout_base);

		initLeft(context);
		initRight(context);
		initImg(context);
		layout_base.setBackgroundResource(bmp_bg_id);
		title.setImageBitmap(bmp_title);
	}

	public abstract void initImg(Context context);
	public abstract void initPoster(Context context);

	private void initLeft(Context context) {
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 46);
		param.setMargins(17, 12 , MARGIN, MARGIN / 2);
		layout.setLayoutParams(param);

		title = new ImageView(context);//左边上部标题Image
		LinearLayout.LayoutParams param_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 70);
		param_title.bottomMargin = 13;
		title.setLayoutParams(param_title);
		title.setScaleType(ScaleType.FIT_XY);
		layout.addView(title);

		initPoster(context);
		poster = new PosterImage(context, poster_bit);//标题下面的图片
		int padding = 3;
		LinearLayout.LayoutParams param_poster = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 30);
		param_poster.bottomMargin=12;
		//poster.setPadding(padding, padding, padding, padding);
		poster.setLayoutParams(param_poster);
		poster.setScaleType(ScaleType.FIT_XY);
		//poster.setBackgroundResource(R.drawable.cs_recommend_poster_bg);
		layout.addView(poster);//左边垂直线性布局加入标题，图片

		layout_base.addView(layout);//整体布局加入左边部分
	}

	private void initRight(Context context) {
		LinearLayout layout = new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);//右边文字列表布局
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 54);
		param.setMargins(0, MARGIN / 2, MARGIN, MARGIN / 2);
		layout.setLayoutParams(param);

		LinearLayout.LayoutParams param_text = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		text1 = new TextView(context);
		text1.setLayoutParams(param_text);
		layout.addView(text1);

		text2 = new TextView(context);
		text2.setLayoutParams(param_text);
		text2.setTextSize(TypedValue.COMPLEX_UNIT_SP,TEXT_SIZE);
		text2.setSingleLine(true);
		text2.setEllipsize(TruncateAt.END);
		text2.setTextColor(Color.WHITE);
		layout.addView(text2);

		text3 = new TextView(context);
		text3.setLayoutParams(param_text);
		text3.setTextSize(TypedValue.COMPLEX_UNIT_SP,TEXT_SIZE);
		text3.setSingleLine(true);
		text3.setEllipsize(TruncateAt.END);
		text3.setTextColor(Color.WHITE);
		layout.addView(text3);

		text4 = new TextView(context);
		text4.setLayoutParams(param_text);
		text4.setTextSize(TypedValue.COMPLEX_UNIT_SP,TEXT_SIZE);
		text4.setSingleLine(true);
		text4.setEllipsize(TruncateAt.END);
		text4.setTextColor(Color.WHITE);
		layout.addView(text4);

		layout_base.addView(layout);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER://按下确定键事件
			if (itemSelectedListener != null) {
				itemSelectedListener.onItemSelected();//监听接口回调
				return true;
			}
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}
//根据焦点的变化来切换 背景和参数
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if (gainFocus) {
			layout_base.setBackgroundResource(bmp_bg_foucs_id);
			layout_base.setLayoutParams(param_focus);
		} else {
			layout_base.setBackgroundResource(bmp_bg_id);
			layout_base.setLayoutParams(param_unfocus);		
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	public interface ItemSelectedListener {//监听接口
		public void onItemSelected();
	}

}
