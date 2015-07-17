package com.voole.epg.view.navigation;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.corelib.model.navigation.FilmClass;

public class NavigationMovieItemView extends RelativeLayout{
	private boolean isSelected = false;
	private FilmClass item = null;
	private AlwaysMarqueeTextView textView = null;
	public NavigationMovieItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NavigationMovieItemView(Context context) {
		super(context);
		init(context);
	}

	public NavigationMovieItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		final int margin = 5;
		textView = new AlwaysMarqueeTextView(context);
		RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param.topMargin = margin;
		param.bottomMargin = margin;
		param.leftMargin = margin;
		param.rightMargin = margin;
		textView.setLayoutParams(param);
		textView.setMarquee(false);
		textView.setGravity(Gravity.CENTER);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager.GetInstance().changeTextSize(22) );
		textView.setBackgroundResource(R.drawable.cs_navigation_movie_noitem_bg);
		textView.setTextColor(Color.rgb(0x47, 0x47, 0x47));
		textView.setText(context.getString(R.string.movie_channel_qidai));
		addView(textView);
	}
	
	public void setSelected(boolean selected){
		if(isSelected != selected){
			isSelected = selected;
			if(isSelected){
				setBackgroundResource(R.drawable.cs_btn_focus);
				textView.setTextColor(Color.rgb(0xe4, 0x7d, 0x38));
			}else{
				setBackgroundResource(0);
				textView.setTextColor(Color.WHITE);
			}
		}
	}
	
	public void setItem(FilmClass item){
		this.item = item;
		if(this.item != null){
			String text_name = item.getFilmClassName();
			textView.setText(text_name);
			textView.setTextColor(Color.WHITE);
			textView.setBackgroundResource(R.drawable.cs_navigation_movie_item_bg);
			textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager.GetInstance().changeTextSize(24) );
		}
	}
	
}
