package com.voole.epg.view.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;

import com.voole.epg.R;
import com.voole.tvutils.ImageManager;
import com.voole.tvutils.ImageManager.ImageListener;
import com.voole.tvutils.ImageUtil;

/**
 * 首界面的主题模块
 * 
 * @author Administrator
 * 
 */
public class RecommendTopicView extends RecommendItemBaseView {
	public RecommendTopicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RecommendTopicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RecommendTopicView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		// setBackgroundResource(R.drawable.cs_recommend_topic_selector);
	}

	// 设置标题为专题，加上获取焦点跟失去焦点的背景图片。默认下载图片
	
	@Override
	public void initImg(Context context) {
		bmp_title = ImageUtil.getResourceBitmap(context,
				R.drawable.cs_recommend_topic_title);
		// bmp_bg = ImageUtil.getResourceBitmap(context,
		// R.drawable.cs_recommend_topic_unfocus_bg);
		bmp_bg_id = R.drawable.cs_recommend_topic_unfocus_bg;
		bmp_bg_foucs_id = R.drawable.cs_recommend_topic_focus_bg;
		poster.setImageResource(R.drawable.cs_recommend_toprank_loading);
	}

	//设置主题模块 三个文本内容，中间图片
	public void setItem(String count, String name, String imgUrl) {
		/*
		 * text2.setText("78个"); text3.setText("最近更新：");
		 * text4.setText("第85届奥斯卡"); poster.setImageResource(R.drawable.p2);
		 */
		text2.setText(count + "个");
		text3.setText("最近更新：");
		text4.setText(name);
		ImageManager.GetInstance().displayImage(imgUrl, poster);
	}

	@Override
	public void initPoster(Context context) {
		poster_bit = ImageUtil.getResourceBitmap(context,
				R.drawable.cs_recommend_topic_poster_bg);
	}
}
