package com.voole.epg.view.movies;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.tvutils.BitmapUtil;
import com.voole.tvutils.ImageManager;
import com.voole.tvutils.ImageManager.ImageListener;
import com.voole.tvutils.ImageUtil;

public class RecommendWatchFocusView extends BaseLinearLayout{
	private static final int MARGIN = 20;
	private static final int VIEW_SIZE = 4;
	private WatchFocusBigItemView image_big = null;
	private WatchFocusItemView[] image_small_list = null;
	private Bitmap[] bigPosterList = null;
	private List<Film> dataList = null;
	private NinePatch ninePatchBg_selected = null;
	private NinePatch ninePatchBg_unselected = null;
	
	private MovieViewListener movieViewListener = null;
	
	public void setMovieViewListener(MovieViewListener l){
		movieViewListener = l;
	}
	public RecommendWatchFocusView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public RecommendWatchFocusView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public RecommendWatchFocusView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		setOrientation(VERTICAL);
		Bitmap bg_selected = ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_selected_bg);
		ninePatchBg_selected = new NinePatch(bg_selected, bg_selected.getNinePatchChunk(), null);
		Bitmap bg_unselected = ImageUtil.getResourceBitmap(context, R.drawable.cs_recommend_unselected_bg);
		ninePatchBg_unselected = new NinePatch(bg_unselected, bg_unselected.getNinePatchChunk(), null);
		image_small_list = new WatchFocusItemView[VIEW_SIZE];
		bigPosterList = new Bitmap[VIEW_SIZE];
		initUp(context);
		initDown(context);
	}
	
	private void initUp(Context context){
		image_big = new WatchFocusBigItemView(context);
		image_big.setNextFocusRightId(ID.RecommendActivity.TOP_RANK_ID);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 1);
		param.leftMargin = MARGIN;
		param.rightMargin = MARGIN;
		param.topMargin = MARGIN / 2;
		//param.bottomMargin = 3;
		image_big.setLayoutParams(param);
		Animation scaleOut =  AnimationUtils.loadAnimation(mContext, R.anim.recommend_watchfocus_up_scale_out);
		Animation scaleIn =  AnimationUtils.loadAnimation(mContext, R.anim.recommend_watchfocus_up_scale_in);
		image_big.setScaleOut(scaleOut);
		image_big.setScaleIn(scaleIn);
		Bitmap map=BitmapUtil.readBitmap(mContext, R.drawable.cs_recommend_big_loading);
		image_big.setPoster(map);
		image_big.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtil.d("WatchFocusBigItemView-------->onClick");
				if(movieViewListener != null){
					movieViewListener.onItemSelected(image_big.getData(),0);
				}
			}
		});
		image_big.setNextFocusDownId(ID.RecommendActivity.WATCHFOCUSVIEW_FIRST_ID);
		addView(image_big);
	}
	
	private void initDown(Context context){
		LinearLayout layout = new LinearLayout(context);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, 2);
		param.leftMargin = MARGIN / 2;
		param.rightMargin = MARGIN / 2;
		param.topMargin = 0;
		layout.setLayoutParams(param);
		
		for(int i=0; i<VIEW_SIZE; i++){
			image_small_list[i] = new WatchFocusItemView(context);
			if(i==0){
				image_small_list[i].setId(ID.RecommendActivity.WATCHFOCUSVIEW_FIRST_ID);
			}
			LinearLayout.LayoutParams param_small = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT, 1);
//			param_small.leftMargin = DisplayManager.GetInstance().changeWidthSize(MARGIN / 2);
//			param_small.rightMargin = DisplayManager.GetInstance().changeWidthSize(MARGIN / 2);
//			param_small.topMargin = DisplayManager.GetInstance().changeWidthSize(MARGIN / 2);
//			param_small.bottomMargin = DisplayManager.GetInstance().changeWidthSize(2);
			image_small_list[i].setLayoutParams(param_small);
			Animation scaleOut =  AnimationUtils.loadAnimation(mContext, R.anim.recommend_watchfocus_down_scale_out);
			Animation scaleIn =  AnimationUtils.loadAnimation(mContext, R.anim.recommend_watchfocus_down_scale_in);
			image_small_list[i].setScaleOut(scaleOut);
			image_small_list[i].setScaleIn(scaleIn);
			final int index = i;
			image_small_list[i].setOnFocusChangeListener(new OnFocusChangeListener() {
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if(hasFocus){
						if(image_small_list[index].getData() != null){
							image_big.setData(image_small_list[index].getData());
							if(bigPosterList[index] != null){
								image_big.setPoster(bigPosterList[index]);
							}else{
								ImageManager.GetInstance().displayImage(image_small_list[index].getData().getImgUrlZ(),image_big, new ImageListener() {
									@Override
									public void onLoadingStarted(String uri, View view) {
										
									}
									@Override
									public void onLoadingFailed(String uri, View view) {
										
									}
									@Override
									public void onLoadingComplete(String uri, View view, Bitmap bitmap) {
										bigPosterList[index] = bitmap;
										image_big.setPoster(bigPosterList[index]);
									}
								});
							}
						}
					}
				}
			});
			image_small_list[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LogUtil.d("image_small_list" + index + "-------->onClick");
					if(movieViewListener != null){
						movieViewListener.onItemSelected(image_small_list[index].getData(),index);
					}
				}
			});
			layout.addView(image_small_list[i]);
		}
		addView(layout);
	}
	
	public void requestFocusedItem(){
		image_small_list[0].requestFocus();
	}
	
	public void setData(List<Film> data){
		this.dataList = data;
		image_big.setData(this.dataList.get(0));
		ImageManager.GetInstance().displayImage(dataList.get(0).getImgUrlZ(), image_big,new ImageListener() {
			@Override
			public void onLoadingStarted(String uri, View view) {
				
			}
			@Override
			public void onLoadingFailed(String uri, View view) {
				
			}
			@Override
			public void onLoadingComplete(String uri, View view, Bitmap bitmap) {
				bigPosterList[0] = bitmap;
				image_big.setPoster(bigPosterList[0]);
			}
		});
		for(int i=0; i<VIEW_SIZE; i++){
			image_small_list[i].setData(dataList.get(i));
		}
	}
	
	public class WatchFocusBigItemView extends BaseMovieItemView{
		private Rect rect;

		public WatchFocusBigItemView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public WatchFocusBigItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public WatchFocusBigItemView(Context context) {
			super(context);
			init(context);
		}
		
		private void init(Context context){
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
		}
		
		public void setData(Film film){
			this.data = film;
		}
		
		public void setPoster(Bitmap bitmap){
			this.poster = bitmap;
			invalidate();
		}
//		private static final int BORDER = 10;
		
		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
			setSelectedItem(gainFocus);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (rect==null) {
				rect = canvas.getClipBounds();  
			}
			if(isSelected){
				Rect posterRect1 = new Rect();
				posterRect1.left = rect.left-3 ;
				posterRect1.top = rect.top - 3;
				posterRect1.right = rect.right+3;
				posterRect1.bottom = rect.bottom+4;
				ninePatchBg_selected.draw(canvas, posterRect1);
				Rect posterRect = new Rect();
				posterRect.left = rect.left+3;
				posterRect.top = rect.top +3;
				posterRect.right = rect.right- 3;
				posterRect.bottom = rect.bottom - 3;
				if(poster != null){
					canvas.drawBitmap(poster, null, posterRect, null);
				}
			}else{
				Rect posterRect = new Rect();
				posterRect.left = rect.left;
				posterRect.top = rect.top;
				posterRect.right = rect.right -3;
				posterRect.bottom = rect.bottom-3 ;
				ninePatchBg_unselected.draw(canvas, rect);
				if(poster != null){
					canvas.drawBitmap(poster, null, posterRect, null);
				}
			}
		}
	}
	
	public class WatchFocusItemView extends BaseMovieItemView{
		private Bitmap defautPoster = null;
		public WatchFocusItemView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public WatchFocusItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public WatchFocusItemView(Context context) {
			super(context);
			init(context);
		}
		
		private void init(Context context){
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
			defautPoster = BitmapUtil.readBitmap(mContext, R.drawable.cs_recommend_small_loading);
		}
		
		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
			setSelectedItem(gainFocus);
		}

		
		private static final int BORDER = 10;
		private Rect rect;
		@SuppressLint("DrawAllocation")
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if (rect==null) {
				rect = canvas.getClipBounds();  
			}
			if(isSelected){
				Rect posterRect1 = new Rect();
				posterRect1.left = rect.left +3;
				posterRect1.top = rect.top+3;
				posterRect1.right = rect.right + 2;
				posterRect1.bottom = rect.bottom + 3;
				ninePatchBg_selected.draw(canvas, posterRect1);
				Rect posterRect = new Rect();
				posterRect.left = rect.left+BORDER;
				posterRect.top = rect.top+BORDER;
				posterRect.right = rect.right -BORDER / 2;
				posterRect.bottom = rect.bottom -3;
				if(poster != null){
					canvas.drawBitmap(poster, null, posterRect, null);
				}else{
					canvas.drawBitmap(defautPoster, null, posterRect, null);
				}
			}else{
				Rect posterRect1 = new Rect();
				posterRect1.left = rect.left+BORDER;
				posterRect1.top = rect.top+BORDER;
				posterRect1.right = rect.right -BORDER;
				posterRect1.bottom = rect.bottom-1;
				ninePatchBg_unselected.draw(canvas, posterRect1);
				Rect posterRect = new Rect();
				posterRect.left = posterRect1.left;
				posterRect.top = posterRect1.top;
				posterRect.right = posterRect1.right -2;
				posterRect.bottom = posterRect1.bottom-2;
				if(poster != null){
					canvas.drawBitmap(poster, null, posterRect, null);
					  Paint p = new Paint();
				      p.setColor(Color.parseColor("#b4000000"));
				      canvas.drawRect(posterRect, p);
				}else{
					canvas.drawBitmap(defautPoster, null, posterRect, null);
				}
			}
		}
	}
}
