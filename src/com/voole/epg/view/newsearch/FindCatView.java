package com.voole.epg.view.newsearch;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ShadeButton;
import com.voole.epg.corelib.model.movies.Label;
import com.voole.epg.view.newsearch.FindView.FindViewListener;

public class FindCatView extends BaseLinearLayout {

	private FindView category = null;
	private FindView area = null;
	private FindView type = null;
	private FindView year = null;


	public void setListener(FindViewListener listener){
		category.setFindViewListener(listener);
		category.setCategory(true);
		area.setFindViewListener(listener);
		type.setFindViewListener(listener);
		year.setFindViewListener(listener);
	}

	public FindCatView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public FindCatView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public FindCatView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setOrientation(VERTICAL);
		setGravity(Gravity.CENTER_VERTICAL);
		setBackgroundResource(R.drawable.cs_search_findcat_bg);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT, 1);
		params.topMargin = 10;
		params.leftMargin = 50;
		params.rightMargin = 50;
		params.bottomMargin=10;


		category = new FindView(context);
		category.setLayoutParams(params);
		category.setDisplay();
		addView(category);

		area = new FindView(context);
		area.setLayoutParams(params);
		area.setDisplay();
		addView(area);

		type = new FindView(context);
		type.setLayoutParams(params);
		type.setDisplay();
		addView(type);

		year = new FindView(context);
		year.setLayoutParams(params);
		year.setDisplay();
		addView(year);
	}

	public void setType(Label typeLabel) {
		type.setData("类型：", typeLabel);
	}

	public void setCategory(Label catLabel) {
		category.setData("分类：", catLabel);
	}

	public void setYearArea(List<Label> list) {
		area.setData("地区：", list.get(0));
		year.setData("时间：", list.get(1));
	}

	public void setData(Label catLabel, Label typeLabel, List<Label> list) {
		category.setData("分类：", catLabel);
		type.setData("类型：", typeLabel);
		area.setData("地区：", list.get(0));
		year.setData("时间：", list.get(1));
	}
}
