package com.voole.epg.view.movies.zy;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.transscreen.TransScreenManager;
import com.voole.epg.view.movies.movie.MovieViewListener;
import com.voole.tvutils.ImageManager;
import com.voole.tvutils.ImageManager.ImageListener;
import com.voole.tvutils.ImageUtil;

public class ZYFilmView extends BaseLinearLayout {

	private LinearLayout layout_detail = null;
	private TextView txtTitle = null;
	private ImageView imgContent = null;
	private TextView txtJieShao = null;
	private int selectedItemIndex = 0;
	private int currentPageNo = -1;
	private int totalPageSize = -1;

	private MovieViewListener movieViewListener = null;

	private List<Film> films = null;

	private int selected = 0;

	private static int leftSelected = 0;
	private static int rightSelected = 1;

	private int ITEM_SIZE = 8;
	private ZYFilmItemView[] itemViews;

	public void setMovieViewListener(MovieViewListener movieViewListener) {
		this.movieViewListener = movieViewListener;
	}

	public int getITEM_SIZE() {
		return ITEM_SIZE;
	}

	public ZYFilmView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inint(context);
	}

	public ZYFilmView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inint(context);
	}

	public ZYFilmView(Context context) {
		super(context);
		inint(context);
	}

	private void inint(Context context) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		setOrientation(HORIZONTAL);
		itemViews = new ZYFilmItemView[ITEM_SIZE];
		inintLeft(context);
		inintRight(context);
	}
	
	public void set(int index,String isFavorite){
		films.get(index).setIsFavorite(isFavorite);
		itemViews[index].isFavorite(isFavorite);
	}

	private void inintLeft(Context context) {
		layout_detail = new LinearLayout(context);
		layout_detail.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 4);
		param.rightMargin = DisplayManager.GetInstance().changeWidthSize(25);
		param.leftMargin = DisplayManager.GetInstance().changeWidthSize(35);
		param.bottomMargin = DisplayManager.GetInstance().changeWidthSize(35);
		param.topMargin = DisplayManager.GetInstance().changeWidthSize(20);
		layout_detail.setLayoutParams(param);

		txtTitle = new TextView(context);
		txtTitle.setSingleLine(true);
		txtTitle.setEllipsize(TruncateAt.END);
		txtTitle.setId(ID.ZYFilmView.TITLE_ID);
		txtTitle.setTextColor(Color.WHITE);
		txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager
				.GetInstance().changeTextSize(32));

		// RelativeLayout.LayoutParams param_title = new
		// RelativeLayout.LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		LinearLayout.LayoutParams param_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 30);
		txtTitle.setLayoutParams(param_title);
		layout_detail.addView(txtTitle);
		imgContent = new ImageView(context);
		LinearLayout.LayoutParams param_img = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 18);
		param_img.topMargin=10;
		imgContent.setPadding(3, 3, 5, 23);
		imgContent.setBackgroundResource(R.drawable.cs_zy_film_img_bg);
		imgContent.setLayoutParams(param_img);
		imgContent.setScaleType(ScaleType.FIT_XY);
		layout_detail.addView(imgContent);

		txtJieShao = new TextView(context);
		LinearLayout.LayoutParams param_jieshao = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 18);
		txtJieShao.setId(ID.ZYFilmView.JIESHAO_ID);
		txtJieShao.setEllipsize(TruncateAt.END);
		txtJieShao.setTextColor(Color.WHITE);
		txtJieShao.setLayoutParams(param_jieshao);
		txtJieShao.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager
				.GetInstance().changeTextSize(23));
		txtJieShao.setPadding(5, 0, 0, 5);
		txtJieShao.setMaxLines(7);
		layout_detail.addView(txtJieShao);
		addView(layout_detail);

	}

	private void inintRight(Context context) {
		RelativeLayout layout_right = new RelativeLayout(context);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2);
		param.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param.topMargin = DisplayManager.GetInstance().changeWidthSize(0);
		layout_right.setLayoutParams(param);
		addView(layout_right);

		// left arrow
		ImageView leftArrow = new ImageView(context);
		leftArrow.setId(ID.ZYFilmView.LEFT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_left = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_arrow_left.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_left.rightMargin=DisplayManager.GetInstance().changeTextSize(10);
		leftArrow.setLayoutParams(param_arrow_left);
		leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
				R.drawable.cs_movie_left_arrow));
		leftArrow.setScaleType(ScaleType.FIT_XY);
		layout_right.addView(leftArrow);

		// layout_middle
		LinearLayout layout_middle = new LinearLayout(context);
		layout_middle.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams layout_middle_param = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout_middle_param.addRule(RelativeLayout.RIGHT_OF,
				ID.ZYFilmView.LEFT_ARROW_ID);
		layout_middle_param.addRule(RelativeLayout.LEFT_OF,
				ID.ZYFilmView.RIGHT_ARROW_ID);
		layout_middle.setLayoutParams(layout_middle_param);
		layout_middle.setBackgroundResource(R.drawable.cs_zy_film_bg);
		layout_middle.setPadding(0, 40, 0, 40);
		layout_right.addView(layout_middle);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		for (int i = 0; i < ITEM_SIZE; i++) {
			itemViews[i] = new ZYFilmItemView(context);
			itemViews[i].setLayoutParams(params);
			layout_middle.addView(itemViews[i]);
		}

		// right arrow
		ImageView rightArrow = new ImageView(context);
		rightArrow.setId(ID.ZYFilmView.RIGHT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_right = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param_arrow_right.leftMargin=DisplayManager.GetInstance().changeTextSize(10);
		rightArrow.setLayoutParams(param_arrow_right);
		rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
				R.drawable.cs_movie_right_arrow));
		rightArrow.setScaleType(ScaleType.FIT_XY);
		layout_right.addView(rightArrow);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (selectedItemIndex > 0) {
				itemViews[selectedItemIndex].setSelectedItem(false, false);
				if (selected == leftSelected) {
					itemViews[--selectedItemIndex].setSelectedItem(true, false);
				} else if (selected == rightSelected) {
					itemViews[--selectedItemIndex].setSelectedItem(false, true);
				}
				setDetails();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (selectedItemIndex < films.size() - 1) {
				itemViews[selectedItemIndex].setSelectedItem(false, false);
				if (selected == leftSelected) {
					itemViews[++selectedItemIndex].setSelectedItem(true, false);
				} else if (selected == rightSelected) {
					itemViews[++selectedItemIndex].setSelectedItem(false, true);
				}
				setDetails();
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (selected == leftSelected) {
				previousPage();
			} else {
				selected = leftSelected;
				itemViews[selectedItemIndex].setSelectedItem(true, false);
				setDetails();
			}

			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (selected == rightSelected) {
				nextPage();
			} else {
				selected = rightSelected;
				itemViews[selectedItemIndex].setSelectedItem(false, true);
				setDetails();
			}
			return true;
		case KeyEvent.KEYCODE_DPAD_CENTER:
		case KeyEvent.KEYCODE_ENTER:
			if (selected == leftSelected) {
				movieViewListener.onPlay(films.get(selectedItemIndex));
				return true;
			}
			if (selected == rightSelected) {
				movieViewListener.onItemSelected(films.get(selectedItemIndex),selectedItemIndex);
				return true;
			}

			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void nextPage() {
		if (currentPageNo < totalPageSize) {
			currentPageNo++;
			gotoPage(currentPageNo);
		}
	}

	private void previousPage() {
		if (currentPageNo > 1) {
			currentPageNo--;
			gotoPage(currentPageNo);
		}
	}

	private void gotoPage(int pageNo) {
		if (this.movieViewListener != null) {
			movieViewListener.onGotoPage(pageNo);
		}
	}

	public void setData(List<Film> films) {
		this.films = films;
		int endIndex = ITEM_SIZE;
		if (films.size() < ITEM_SIZE) {
			endIndex = films.size();
		}

		for (int i = 0; i < endIndex; i++) {
			itemViews[i].setTitle(films.get(i).getFilmName());
			itemViews[i].isFavorite(films.get(i).getIsFavorite());
			itemViews[i].setVisibility(View.VISIBLE);
		}

		for (int i = endIndex; i < ITEM_SIZE; i++) {
			itemViews[i].setVisibility(View.INVISIBLE);
		}
		if (this.isFocused()) {
			itemViews[selectedItemIndex].setSelectedItem(false, false);
		}
		selectedItemIndex = 0;
		if (this.isFocused()) {
			selected = leftSelected;
			itemViews[selectedItemIndex].setSelectedItem(true, false);
		}
		setDetails();
	}

	public void setPageInfo(int pageNo, int totalPageSize) {
		this.currentPageNo = pageNo;
		this.totalPageSize = totalPageSize;
	}

	private void setDetails() {
		Film film = films.get(selectedItemIndex);
		txtJieShao.setText(film.getDescription());
		ImageManager.GetInstance().displayImage(film.getImgUrlB(), imgContent,
				new ImageListener() {
					@Override
					public void onLoadingStarted(String uri, View view) {

					}

					@Override
					public void onLoadingFailed(String uri, View view) {

					}

					@Override
					public void onLoadingComplete(String uri, View view,
							Bitmap bitmap) {
						imgContent.setPadding(3, 3, 5, 25);
					}
				});
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		if (films == null || films.size() <= 0) {
			return;
		}
		if (gainFocus) {
			if (selected == leftSelected) {
				itemViews[selectedItemIndex].setSelectedItem(true, false);
			} else {
				itemViews[selectedItemIndex].setSelectedItem(false, true);
			}
		} else {
			itemViews[selectedItemIndex].setSelectedItem(false, false);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	public void setTitle(String title) {
		txtTitle.setText(title);
	}

}
