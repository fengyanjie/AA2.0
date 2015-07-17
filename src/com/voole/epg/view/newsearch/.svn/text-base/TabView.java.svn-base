package com.voole.epg.view.newsearch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.TVButton;

public class TabView extends BaseLinearLayout {

	private TVButton tabItemLeft = null;
	private TVButton tabItemRight = null;
	private ImageView splitIV = null;

	private TabViewListener tabViewListener = null;

	public void setTabViewListener(TabViewListener tabViewListener) {
		this.tabViewListener = tabViewListener;
	}

	public interface TabViewListener {
		public void onLeftClick();

		public void onRightClick();
	}

	public TabView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public TabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public TabView(Context context) {
		super(context);
		init();
	}

	private void init() {
		setOrientation(VERTICAL);
		setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams layout_params = new LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout tab_btn_layout = new LinearLayout(mContext);
		tab_btn_layout.setOrientation(HORIZONTAL);
		tab_btn_layout.setGravity(Gravity.CENTER);
		tab_btn_layout.setLayoutParams(layout_params);
		addView(tab_btn_layout);

		LinearLayout.LayoutParams params_btn_left = new LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams params_btn_right = new LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams params_split = new LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		params_btn_left.rightMargin = 20;
		params_btn_right.leftMargin = 20;

		tabItemLeft = new TVButton(mContext);
		tabItemLeft.setText("  搜索  ");
		tabItemLeft.setLayoutParams(params_btn_left);
		tabItemLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(tabViewListener!=null){
					splitIV.setImageResource(R.drawable.cs_search_tab_split_left);
					tabViewListener.onLeftClick();
				}
			}
		});
		tabItemRight = new TVButton(mContext);
		tabItemRight.setText("  检索  ");
		tabItemRight.setLayoutParams(params_btn_right);
		tabItemRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(tabViewListener!=null){
					splitIV.setImageResource(R.drawable.cs_search_tab_split_right);
					tabViewListener.onRightClick();
				}
			}
		});
		tab_btn_layout.addView(tabItemLeft);
		tab_btn_layout.addView(tabItemRight);
		splitIV = new ImageView(mContext);
		splitIV.setImageResource(R.drawable.cs_search_tab_split_left);
		splitIV.setLayoutParams(params_split);
		addView(splitIV);

	}
}
