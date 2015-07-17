package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.base.common.TVButton;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.FilmAndPageInfo;
import com.voole.epg.corelib.model.transscreen.ResumeAndPageInfo;
import com.voole.epg.corelib.model.transscreen.ResumeFilm;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.movie.MovieView;
import com.voole.epg.view.movies.movie.MovieViewListener;

public class MyMagicMovieView extends BaseLinearLayout {
	private MovieView movieView = null;
	private static final int ITEM_SIZE = 12;
	private PageNavigator pageNavigator = null;
	private TVButton edit = null;
	private TVButton clean = null;
	
	private List<Film> films = null;
	private List<Film> selectedList = null;
	private int currentPageNo = 0;
	private int totalPageSize = 0;
	
	private List<ResumeFilm> resumeFilms = null;
	
	
	private MovieEditedListener listener = null;
	public void setMovieEditedListener(MovieEditedListener listener){
		this.listener = listener;
	}
	
	private MovieViewListener movieViewListener = null;
	public void setMovieViewListener(MovieViewListener movieViewListener){
		if (movieViewListener != null && movieView != null) {
			this.movieViewListener = movieViewListener;
			movieView.setMovieViewListener(movieViewListener);
		}
	}
	
	private ButtonStatus currentStatus = ButtonStatus.unEdited;
	public enum ButtonStatus{
		unEdited,
		Edited
	}
	
	public MyMagicMovieView(Context context) {
		super(context);
		init(context);
	}
	
	public MyMagicMovieView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MyMagicMovieView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	/**
	 * up to down : 10:1
	 * @param context
	 */
	private void init(Context context){
		this.setOrientation(VERTICAL);
		movieView = new MovieView(context, ITEM_SIZE,6);
		movieView.setDisplayDetail(false);
		LinearLayout.LayoutParams param_movie = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,4);
		param_movie.rightMargin = DisplayManager.GetInstance().changeWidthSize(50);
//		param_movie.topMargin = DisplayManager.GetInstance().changeHeightSize(10);
		movieView.setLayoutParams(param_movie);
		addView(movieView);
		
		LinearLayout layout_bottom = new LinearLayout(context);
		layout_bottom.setOrientation(HORIZONTAL);
		LinearLayout.LayoutParams param_layout_bottom = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,26);
//		param_layout_bottom.leftMargin = DisplayManager.GetInstance().changeWidthSize(size);
		param_layout_bottom.rightMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_layout_bottom.bottomMargin = DisplayManager.GetInstance().changeWidthSize(30);
		layout_bottom.setLayoutParams(param_layout_bottom);
		
		pageNavigator = new PageNavigator(context);
		pageNavigator.setPageNavigatorListener(new PageNavigatorListener() {
			@Override
			public void onGotoPage(int pageNo) {
				if (movieViewListener != null) {
					movieViewListener.onGotoPage(pageNo);
					pageNavigator.setPageInfo(pageNo, totalPageSize);
					setCurrentStatus(currentStatus);
				}
			}
		});
		pageNavigator.setGravity(Gravity.RIGHT);
		LinearLayout.LayoutParams param_pageNavigator = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,2);
		param_pageNavigator.leftMargin = DisplayManager.GetInstance().changeWidthSize(30);
		pageNavigator.setLayoutParams(param_pageNavigator);
		layout_bottom.addView(pageNavigator);
		
		/*TextView tv = new TextView(context);
		LinearLayout.LayoutParams param_tv = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,2);
		tv.setLayoutParams(param_tv);
		layout_bottom.addView(tv);*/
		
		edit = new TVButton(context);
		edit.setText(R.string.mymagic_favorite_edit);
		edit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null /*&& selectedList != null*/) {
					if (currentStatus == ButtonStatus.unEdited) {
						listener.onEdited();
						setCurrentStatus(ButtonStatus.Edited);
					}else {
						selectedList = movieView.getSelectedItems();
						if (selectedList != null && selectedList.size() > 0) {
							listener.onDelete(selectedList);
//							currentStatus = ButtonStatus.unEdited;
						}
					}
				}
			}
		});
		LinearLayout.LayoutParams param_edit = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,9);
		param_edit.rightMargin = DisplayManager.GetInstance().changeWidthSize(10);
		edit.setLayoutParams(param_edit);
		layout_bottom.addView(edit);
		
		clean = new TVButton(context);
		clean.setText(R.string.mymagic_favorite_clean);
		clean.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (listener != null /*&& films != null*/) {
					if (currentStatus == ButtonStatus.unEdited) {
						if (films != null||resumeFilms!=null) {
							listener.onClear(films);
//							currentStatus = ButtonStatus.Edited;
						}
					}else {
						listener.onCancel();
						currentStatus = ButtonStatus.unEdited;
						setCurrentStatus(ButtonStatus.unEdited);
					}
				}
			}
		});
		LinearLayout.LayoutParams param_clean = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,9);
		param_clean.rightMargin = DisplayManager.GetInstance().changeWidthSize(10);
		clean.setLayoutParams(param_clean);
		layout_bottom.addView(clean);
		addView(layout_bottom);
	}
	
	public void setData(FilmAndPageInfo filmAndPageInfo){
		this.films = filmAndPageInfo.getFilmList();
		setPageInfo(filmAndPageInfo.getPageIndex(), filmAndPageInfo.getPageCount());
		movieView.setData(films);
		setCurrentStatus(ButtonStatus.unEdited);
	}
	
	public void setData(ResumeAndPageInfo resumeAndPageInfo){
		/*this.films = filmAndPageInfo.getFilmList();
		setPageInfo(filmAndPageInfo.getPageIndex(), filmAndPageInfo.getPageCount());*/
		this.resumeFilms = resumeAndPageInfo.getFilmList();
		setPageInfo(resumeAndPageInfo.getPageIndex(), resumeAndPageInfo.getPageCount());
		movieView.setData(this.resumeFilms);
		setCurrentStatus(ButtonStatus.unEdited);
	}
	
	private void setPageInfo(int currentPageNo, int totalPageSize){
		LogUtil.d("currentPageNo----->" + currentPageNo + "---totalPageSize---->" + totalPageSize);
		this.currentPageNo = currentPageNo;
		this.totalPageSize = totalPageSize;
		movieView.setPageInfo(this.currentPageNo, this.totalPageSize);
		pageNavigator.setPageInfo(this.currentPageNo, this.totalPageSize);
	}
	
	private void setCurrentStatus(ButtonStatus currentStatus){
		this.currentStatus = currentStatus;
		if (this.currentStatus == ButtonStatus.unEdited) {
			pageNavigator.setVisibility(View.VISIBLE);
			edit.setText("编辑");
			clean.setText("清空");
		}else if(this.currentStatus == ButtonStatus.Edited){
			pageNavigator.setVisibility(View.INVISIBLE);
			edit.setText("删除");
			clean.setText("取消");
		}
	}
	
	public void setEdited(boolean isEditable){
		movieView.setEditable(isEditable);
	}
	
	public void showPursueVideoBar(){
		movieView.setShowPursueVideoBar(true);
	}
}
