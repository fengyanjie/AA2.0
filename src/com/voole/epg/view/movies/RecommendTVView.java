package com.voole.epg.view.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

import com.voole.epg.Config;
import com.voole.epg.R;
import com.voole.tvutils.ImageManager;
import com.voole.tvutils.ImageUtil;
/**
 * 主页面优朋TV模块
 * @author Administrator
 *
 */
public class RecommendTVView extends RecommendItemBaseView{
	public RecommendTVView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RecommendTVView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RecommendTVView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
//		setBackgroundResource(R.drawable.cs_recommend_tv_selector);
	}

	@Override
	public void initImg(Context context) {
		if ("1".equals(Config.GetInstance().getShowtv())) {
			bmp_title = ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_tv_title);
//			bmp_bg = ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_tv_unfocus_bg);
			bmp_bg_id = R.drawable.cs_recommend_tv_unfocus_bg;
			bmp_bg_foucs_id = R.drawable.cs_recommend_tv_focus_bg;
			poster.setImageResource(R.drawable.cs_recommend_toprank_loading);
		}else {
			bmp_bg_id = R.drawable.cs_recommend_tv_unfocus_bg_voole;
			bmp_bg_foucs_id = R.drawable.cs_recommend_tv_focus_bg_voole;
		}
	}
	
	public void setItem(String name, String imgUrl){
		if ("1".equals(Config.GetInstance().getShowtv())) {
			text2.setText("24小时轮播");
			text3.setText("正在播出：");
			text4.setText(name);
			ImageManager.GetInstance().displayImage(imgUrl, poster);
		}
	}

	@Override
	public void initPoster(Context context) {
		if ("1".equals(Config.GetInstance().getShowtv())) {
			poster_bit= ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_tv_poster_bg);
		}
	}
}
