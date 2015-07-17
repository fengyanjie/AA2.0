package com.voole.epg.base.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.tvutils.ImageUtil;

public class PageIconNavigator extends BaseLinearLayout{
	private ImageView[] itemViews = null;
	private int currentPageNo = -1;
	private int totalPageSize = -1;
	public PageIconNavigator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public PageIconNavigator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public PageIconNavigator(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
	}
	
	public void setPageInfo(int pageNo, int totalPageSize){
		this.currentPageNo = pageNo;
		if(this.totalPageSize > 1){
			updateUI();
		}else{
			this.totalPageSize = totalPageSize;
			setUI();
		}
	}
	
	private void setUI(){
		itemViews = new ImageView[this.totalPageSize];
		LinearLayout.LayoutParams item_param = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		item_param.gravity = Gravity.CENTER_VERTICAL;
		for(int i=0; i<this.totalPageSize; i++){
			itemViews[i] = new ImageView(mContext);
			itemViews[i].setLayoutParams(item_param);
			itemViews[i].setScaleType(ScaleType.FIT_XY);
			if(currentPageNo == i+1){
				itemViews[i].setImageBitmap(ImageUtil.getResourceBitmap(mContext, R.drawable.cs_pageiconnavigator_current));
			}else{
				itemViews[i].setImageBitmap(ImageUtil.getResourceBitmap(mContext, R.drawable.cs_pageiconnavigator_normal));
			}
			addView(itemViews[i]);
		}
	}
	
	private void updateUI(){
		for(int i=0; i<this.totalPageSize; i++){
			if(currentPageNo == i+1){
				itemViews[i].setImageBitmap(ImageUtil.getResourceBitmap(mContext, R.drawable.cs_pageiconnavigator_current));
			}else{
				itemViews[i].setImageBitmap(ImageUtil.getResourceBitmap(mContext, R.drawable.cs_pageiconnavigator_normal));
			}
		}
	}
}
