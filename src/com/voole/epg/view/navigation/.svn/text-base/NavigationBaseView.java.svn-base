package com.voole.epg.view.navigation;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.corelib.model.navigation.FilmClass;

public abstract class NavigationBaseView extends BaseLinearLayout{
	protected List<FilmClass> navigationItems = null;
	
	protected NavigationItemSelectedListener listener = null;
	
	protected int selectedItemIndex = 0;
	public NavigationBaseView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationBaseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NavigationBaseView(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
	}
	
	public void setOnItemSelectedListener(NavigationItemSelectedListener l){
		listener = l;
	}

}
