package com.voole.epg.view.movies.movie;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.tvutils.ImageUtil;

public class MovieView extends BaseLinearLayout{
//	public static final int ITEM_SIZE = 10;
	
	private int ITEM_SIZE = 10;
	public int getITEM_SIZE() {
		return ITEM_SIZE;
	}

	public void setITEM_SIZE(int iTEM_SIZE) {
		ITEM_SIZE = iTEM_SIZE;
	}

	private MovieItemView[] itemViews = null;
	private List<? extends Film> films = null;
	
	private int selectedItemIndex = 0;
	private int currentPageNo = -1;
	private int totalPageSize = -1;
	
	private RelativeLayout layout_detail = null;
	private AlwaysMarqueeTextView txtTitle = null;
	private TextView txtDate = null;
	private TextView txtJieShao = null;
	private TextView txtZuYan = null;
	
	private boolean isEditable = false;
	
	private MovieViewListener movieViewListener = null;
	
	private int margins=DisplayManager.GetInstance().changeWidthSize(2);
	
	private boolean isDisplayDetail = true;
	
	public void setMovieViewListener(MovieViewListener l){
		movieViewListener = l;
	}
	
	public void hiddenActor(){
		txtZuYan.setVisibility(INVISIBLE);
	}
	
	public MovieView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MovieView);
		int itemSize = a.getInt(R.styleable.MovieView_itemSize, ITEM_SIZE);
		ITEM_SIZE = itemSize;
		a.recycle();
		init(context);
	}

	public MovieView(Context context) {
		super(context);
		init(context);
	}

	public MovieView(Context context, int itemSize) {
		super(context);
		this.ITEM_SIZE = itemSize;
		init(context);
	}
	
	public MovieView(Context context, int itemSize,int margins) {
		super(context);
		this.ITEM_SIZE = itemSize;
		this.margins=DisplayManager.GetInstance().changeWidthSize(margins);
		init(context);
	}
	
	private void init(Context context){
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		setOrientation(HORIZONTAL);
		itemViews = new MovieItemView[ITEM_SIZE];
		initLeft(context);
		initRight(context);
	}
	
	private void initLeft(Context context){
		// root layout
		RelativeLayout layout_left = new RelativeLayout(context);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param.leftMargin = DisplayManager.GetInstance().changeWidthSize(40);
		layout_left.setLayoutParams(param);
		addView(layout_left);
		
		// left arrow
		ImageView leftArrow = new ImageView(context);
		leftArrow.setId(ID.MovieView.LEFT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_left = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_arrow_left.addRule(RelativeLayout.CENTER_VERTICAL);
		leftArrow.setLayoutParams(param_arrow_left);
		leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context, R.drawable.cs_movie_left_arrow));
		leftArrow.setScaleType(ScaleType.FIT_XY);
		layout_left.addView(leftArrow);
		
		// movie layout
		LinearLayout layout_middle = new LinearLayout(context);
		layout_middle.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams layout_middle_param = new RelativeLayout.LayoutParams
		(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout_middle_param.addRule(RelativeLayout.RIGHT_OF, ID.MovieView.LEFT_ARROW_ID);
		layout_middle_param.addRule(RelativeLayout.LEFT_OF, ID.MovieView.RIGHT_ARROW_ID);
		layout_middle.setLayoutParams(layout_middle_param);
		layout_left.addView(layout_middle);
		
		LinearLayout.LayoutParams layout_middle_item_parma = new LinearLayout.LayoutParams
		(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_middle_item_parma.setMargins(margins, 0, margins, 0);
		
		// movie up 
		LinearLayout layout_middle_up = new LinearLayout(context);
		layout_middle_up.setLayoutParams(layout_middle_item_parma);
		layout_middle.addView(layout_middle_up);
		
		for(int i = 0; i < ITEM_SIZE / 2; i++){
			itemViews[i] = new MovieItemView(context);
			itemViews[i].setLayoutParams(layout_middle_item_parma);
			layout_middle_up.addView(itemViews[i]);
		}
		
		// movie down
		LinearLayout layout_middle_down = new LinearLayout(context);
		layout_middle_down.setLayoutParams(layout_middle_item_parma);
		layout_middle.addView(layout_middle_down);
		
		for(int i = ITEM_SIZE / 2; i < ITEM_SIZE; i++){
			itemViews[i] = new MovieItemView(context);
			itemViews[i].setLayoutParams(layout_middle_item_parma);
			layout_middle_down.addView(itemViews[i]);
		}
		
		//right arrow
		ImageView rightArrow = new ImageView(context);
		rightArrow.setId(ID.MovieView.RIGHT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_right = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rightArrow.setLayoutParams(param_arrow_right);
		rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context, R.drawable.cs_movie_right_arrow));
		rightArrow.setScaleType(ScaleType.FIT_XY);
		layout_left.addView(rightArrow);
	}
	
	private void initRight(Context context){
		//root layout
		layout_detail = new RelativeLayout(context);
		layout_detail.setPadding(0, 15, 10, 0);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3);
		param.rightMargin = DisplayManager.GetInstance().changeWidthSize(40);
		param.bottomMargin = DisplayManager.GetInstance().changeWidthSize(35);
		param.topMargin = DisplayManager.GetInstance().changeWidthSize(0);
		layout_detail.setLayoutParams(param);
		layout_detail.setBackgroundResource(R.drawable.cs_movie_detail_bg);
		
		final int margin = DisplayManager.GetInstance().changeHeightSize(10);
		// title
		txtTitle = new AlwaysMarqueeTextView(context);
		txtTitle.setMarquee(true);
		txtTitle.setId(ID.MovieView.TITLE_ID);
		txtTitle.setTextColor(Color.WHITE);
		txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager.GetInstance().changeTextSize(32));
		RelativeLayout.LayoutParams param_title = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_title.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		param_title.topMargin = margin;
		param_title.leftMargin = margin;
		param_title.rightMargin = margin;
		txtTitle.setLayoutParams(param_title);
		layout_detail.addView(txtTitle);
		
		// date
		txtDate = new TextView(context);
		txtDate.setSingleLine(true);
		txtDate.setEllipsize(TruncateAt.END);
		txtDate.setTextColor(Color.WHITE);
		txtDate.setId(ID.MovieView.DATE_ID);
		txtDate.setTextColor(Color.WHITE);
		txtDate.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager.GetInstance().changeTextSize(22));
		RelativeLayout.LayoutParams param_date = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_date.addRule(RelativeLayout.BELOW, ID.MovieView.TITLE_ID);
		param_date.topMargin = 10;
		param_date.leftMargin = margin;
		param_date.rightMargin = margin;
		txtDate.setLayoutParams(param_date);
		layout_detail.addView(txtDate);
		
		// detail
		txtJieShao = new TextView(context);
		RelativeLayout.LayoutParams param_jieshao = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		//param_jieshao.addRule(RelativeLayout.ABOVE, ID.MovieView.ZUYAN_ID);
		param_jieshao.addRule(RelativeLayout.BELOW, ID.MovieView.DATE_ID);
		param_jieshao.leftMargin = margin;
		param_jieshao.topMargin = 10;
		param_jieshao.rightMargin = margin;
		txtJieShao.setId(ID.MovieView.JIESHAO_ID);
		txtJieShao.setEllipsize(TruncateAt.END);
		txtJieShao.setTextColor(Color.WHITE);
		txtJieShao.setLayoutParams(param_jieshao);
		txtJieShao.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager.GetInstance().changeTextSize(22) );
		txtJieShao.setMaxLines(8);
		txtJieShao.setLineSpacing(3f, 1.1f);
		layout_detail.addView(txtJieShao);
		
		// zu yan
		txtZuYan = new TextView(context);
		txtZuYan.setId(ID.MovieView.ZUYAN_ID);
		txtZuYan.setTextColor(Color.WHITE);
		RelativeLayout.LayoutParams param_zuyan = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_zuyan.addRule(RelativeLayout.BELOW,ID.MovieView.JIESHAO_ID);
		param_zuyan.topMargin = 10;
		param_zuyan.bottomMargin = margin;
		param_zuyan.leftMargin = margin;
		param_zuyan.rightMargin = margin;
		txtZuYan.setLayoutParams(param_zuyan);
		txtZuYan.setEllipsize(TruncateAt.END);
		txtZuYan.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager.GetInstance().changeTextSize(22) );
		txtZuYan.setMaxLines(2);
		txtZuYan.setLineSpacing(3.4f, 1f);
		layout_detail.addView(txtZuYan);

		addView(layout_detail);
	}
	
	private void setDetails(){
		if(this.isDisplayDetail == true){
			Film film = films.get(selectedItemIndex);
			txtTitle.setText(film.getFilmName());
			String str_year="";
			if(!film.getYear().contains("不")){
				str_year=film.getYear();
			}
			txtDate.setText(str_year + "    " + film.getLongTime() + mContext.getString(R.string.movie_longtime));
			txtJieShao.setText(film.getDescription());
			txtZuYan.setText( mContext.getString(R.string.movie_zuyan) + film.getActor());
		}
	}
	
	public void setData(List<? extends Film> films){
		this.films = films;
		int endIndex = ITEM_SIZE;
		if(films.size() < ITEM_SIZE){
			endIndex = films.size();
		}
		for(int i = 0; i < endIndex; i++){
			itemViews[i].setData(films.get(i));
			itemViews[i].setVisibility(View.VISIBLE);
		}
		
		for(int i=endIndex; i<ITEM_SIZE; i++){
			itemViews[i].setVisibility(View.INVISIBLE);
		}
		if(this.isFocused()){
			itemViews[selectedItemIndex].setFocusedItem(false);
		}
		selectedItemIndex = 0;
		if(this.isFocused()){
			itemViews[selectedItemIndex].setFocusedItem(true);
		}
		if(endIndex>0){
			setDetails();
		}
	}
	
	
	public void setPageInfo(int pageNo, int totalPageSize){
		this.currentPageNo = pageNo;
		this.totalPageSize = totalPageSize;
	}
	
	public void setDisplayDetail(boolean isDisplayDetail){
		this.isDisplayDetail = isDisplayDetail;
		if(isDisplayDetail){
			layout_detail.setVisibility(View.VISIBLE);
		}else{
			layout_detail.setVisibility(View.GONE);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				if( selectedItemIndex >= ITEM_SIZE / 2 ){
					itemViews[selectedItemIndex].setFocusedItem(false);
					selectedItemIndex -= ITEM_SIZE / 2;
					itemViews[selectedItemIndex].setFocusedItem(true);
					setDetails();
					return true;
				}
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if(films != null && selectedItemIndex < ITEM_SIZE / 2 && films.size() > ITEM_SIZE / 2){
					itemViews[selectedItemIndex].setFocusedItem(false);
					if((selectedItemIndex + ITEM_SIZE / 2) < films.size()){
						selectedItemIndex += ITEM_SIZE / 2;
					}else{
						selectedItemIndex = ITEM_SIZE / 2;
					}
					itemViews[selectedItemIndex].setFocusedItem(true);
					setDetails();
					return true;
				}
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if(selectedItemIndex > 0){
					if(selectedItemIndex == ITEM_SIZE / 2){
						previousPage();
					}else{
						itemViews[selectedItemIndex].setFocusedItem(false);
						itemViews[--selectedItemIndex].setFocusedItem(true);
						setDetails();
					}
				}else{
					previousPage();
				}
				return true;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if(films != null && selectedItemIndex < films.size() - 1){
					if(selectedItemIndex == ITEM_SIZE / 2 - 1){
						nextPage();
					}else{
						itemViews[selectedItemIndex].setFocusedItem(false);
						itemViews[++selectedItemIndex].setFocusedItem(true);
						setDetails();
					}
				}else{
					nextPage();
				}
				return true;
			case KeyEvent.KEYCODE_ENTER:
			case KeyEvent.KEYCODE_DPAD_CENTER:
				if(isEditable){
					itemViews[selectedItemIndex].setSelectedItem(!itemViews[selectedItemIndex].isSelectedItem());
				}else{
					if(movieViewListener != null && films != null){
						movieViewListener.onItemSelected(films.get(selectedItemIndex),selectedItemIndex);
						return true;
					}
				}
				break;
			default:
				break;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void nextPage(){
		if(isEditable){
			return;
		}
		if(currentPageNo < totalPageSize){
			currentPageNo ++;
			gotoPage(currentPageNo);
		}
	}
	
	private void previousPage(){
		if(isEditable){
			return;
		}
		if(currentPageNo > 1){
			currentPageNo --;
			gotoPage(currentPageNo);
		}
	}
	
	private void gotoPage(int pageNo) {
		if(isEditable){
			return;
		}
		if(this.movieViewListener != null){
			movieViewListener.onGotoPage(pageNo);
		}
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
		if (films == null || films.size() <= 0) {
			return;
		}
		if(gainFocus){
			itemViews[selectedItemIndex].setFocusedItem(true);
			setDetails();
		}else{
			itemViews[selectedItemIndex].setFocusedItem(false);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
	
	public void setEditable(boolean isEditable){
		this.isEditable = isEditable;
		for(int i=0; i<ITEM_SIZE; i++){
			itemViews[i].setEditable(isEditable);
			itemViews[i].setSelectedItem(false);
		}
	}
	
	public List<Film> getSelectedItems(){
		List<Film> retLit = new ArrayList<Film>();
		int endIndex = ITEM_SIZE;
		if(films.size() < ITEM_SIZE){
			endIndex = films.size();
		}
		for(int i = 0; i < endIndex; i++){
			if(itemViews[i].isSelectedItem()){
				retLit.add(films.get(i));
			}
		}
		return retLit;
	}
	
	public void setShowPursueVideoBar(boolean isShowPursueVideoBar) {
		for(int i=0; i<ITEM_SIZE; i++){
			itemViews[i].setShowPursuerTV(isShowPursueVideoBar);
		}
	}
}
