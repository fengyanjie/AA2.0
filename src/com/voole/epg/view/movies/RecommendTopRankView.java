package com.voole.epg.view.movies;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;

import com.voole.epg.R;
import com.voole.epg.base.common.PosterImage;
import com.voole.tvutils.ImageManager;
import com.voole.tvutils.ImageManager.ImageListener;
import com.voole.tvutils.ImageUtil;

public class RecommendTopRankView extends RecommendItemBaseView{
	public RecommendTopRankView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RecommendTopRankView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RecommendTopRankView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
//		setBackgroundResource(R.drawable.cs_recommend_toprank_selector);
	}

	@Override
	public void initImg(final Context context) {
		bmp_title = ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_toprank_title);
//		bmp_bg = ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_toprank_unfocus_bg);
		bmp_bg_id = R.drawable.cs_recommend_toprank_unfocus_bg;
		bmp_bg_foucs_id=R.drawable.cs_recommend_toprank_focus_bg;
		poster.setImageResource(R.drawable.cs_recommend_toprank_loading);
		
	}
	
	public void setItem(String rank1, String rank2, String rank3, String imgUrl){
		/*text2.setText("1.特种部队");
		text3.setText("2.霍比特人");
		text4.setText("3.虎胆龙威5");
		poster.setImageResource(R.drawable.p6);*/
		text2.setText("1." + rank1);
		text3.setText("2." + rank2);
		text4.setText("3." + rank3);
		ImageManager.GetInstance().displayImage(imgUrl, poster);
	}

	@Override
	public void initPoster(Context context) {
		 poster_bit= ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_toprank_poster_bg);
	}
}
