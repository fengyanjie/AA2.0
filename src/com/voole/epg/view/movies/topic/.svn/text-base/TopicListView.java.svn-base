package com.voole.epg.view.movies.topic;

import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.tvutils.ImageUtil;

public class TopicListView extends BaseLinearLayout {

	private TopicListItemView[] topicListItemView;
	private int selectedItemIndex = 0;
	private final int ITEM_SIZE = 6;
	private List<FilmClass> filmClass = null;
	private int currentPageNo = -1;
	private int totalPageSize = -1;

	public void setPageInfo(int pageNo, int totalPageSize) {
		this.currentPageNo = pageNo;
		this.totalPageSize = totalPageSize;
	}

	public int getITEM_SIZE() {
		return ITEM_SIZE;
	}

	private TopicListViewListener topicListViewListener = null;

	public void setData(List<FilmClass> fc) {
		filmClass = fc;
		int endIndex = filmClass.size();
		selectedItemIndex = 0;
		LogUtil.d("TopicList----setData---size--->" + endIndex);
		/*if (endIndex < ITEM_SIZE) {
			for (int i = 0; i < endIndex; i++) {
				topicListItemView[i].setData(filmClass.get(i));
			}
			if (endIndex < ITEM_SIZE) {
				for (int i = endIndex; i < ITEM_SIZE; i++) {
					topicListItemView[i].clearData();
				}
			}
		} else {
			for (int i = 0; i < ITEM_SIZE; i++) {
				topicListItemView[i].setData(filmClass.get(i));
			}
		}*/
		for (int i = 0; i < ITEM_SIZE; i++) {
			if (i < endIndex) {
				topicListItemView[i].setData(filmClass.get(i));
				if (i == selectedItemIndex) {
					topicListItemView[i].OnItemSeletced(true);
				}else {
					topicListItemView[i].OnItemSeletced(false);
				}
			}else {
				topicListItemView[i].clearData();
				topicListItemView[i].OnItemSeletced(false);
			}
		}
	}

	public void setTopicListViewListener(TopicListViewListener listener) {
		this.topicListViewListener = listener;
	}

	public interface TopicListViewListener {
		public void onItemSelected(FilmClass item);

		public void onGotoPage(int pageNo);
	}

	public TopicListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public TopicListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public TopicListView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		setOrientation(HORIZONTAL);
		topicListItemView = new TopicListItemView[ITEM_SIZE];
		initLayout(context);
	}

	private void initLayout(Context context) {
		RelativeLayout layout = new RelativeLayout(context);
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param.leftMargin = DisplayManager.GetInstance().changeWidthSize(45);
		layout.setBackgroundResource(R.drawable.cs_zy_film_bg);
		layout.setLayoutParams(param);
		addView(layout);

		// left arrow
		ImageView leftArrow = new ImageView(context);
		leftArrow.setId(ID.TopicListView.LEFT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_left = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_arrow_left.addRule(RelativeLayout.CENTER_VERTICAL);
		leftArrow.setLayoutParams(param_arrow_left);
		leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
				R.drawable.cs_movie_left_arrow));
		leftArrow.setScaleType(ScaleType.FIT_XY);
		layout.addView(leftArrow);

		// movie layout
		LinearLayout layout_middle = new LinearLayout(context);
		layout_middle.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams layout_middle_param = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		layout_middle_param.addRule(RelativeLayout.RIGHT_OF,
				ID.TopicListView.LEFT_ARROW_ID);
		layout_middle_param.addRule(RelativeLayout.LEFT_OF,
				ID.TopicListView.RIGHT_ARROW_ID);
		layout_middle.setLayoutParams(layout_middle_param);
		layout.addView(layout_middle);

		LinearLayout.LayoutParams layout_middle_item_parma = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_middle_item_parma.setMargins(30, 5, 30, 5);
		// movie up
		LinearLayout layout_middle_up = new LinearLayout(context);
		layout_middle_up.setLayoutParams(layout_middle_item_parma);
		layout_middle.addView(layout_middle_up);

		LinearLayout.LayoutParams layout_middle_up_parma = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_middle_up_parma.setMargins(10, 30, 10, 5);
		for (int i = 0; i < ITEM_SIZE / 2; i++) {
			topicListItemView[i] = new TopicListItemView(context);
			topicListItemView[i].setLayoutParams(layout_middle_up_parma);
			layout_middle_up.addView(topicListItemView[i]);
		}

		// movie down
		LinearLayout layout_middle_down = new LinearLayout(context);
		layout_middle_down.setLayoutParams(layout_middle_item_parma);
		layout_middle.addView(layout_middle_down);

		LinearLayout.LayoutParams layout_middle_down_parma = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_middle_down_parma.setMargins(10, 5, 10, 30);
		for (int i = ITEM_SIZE / 2; i < ITEM_SIZE; i++) {
			topicListItemView[i] = new TopicListItemView(context);
			topicListItemView[i].setLayoutParams(layout_middle_down_parma);
			layout_middle_down.addView(topicListItemView[i]);
		}

		// right arrow
		ImageView rightArrow = new ImageView(context);
		rightArrow.setId(ID.TopicListView.RIGHT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_right = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		rightArrow.setLayoutParams(param_arrow_right);
		rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
				R.drawable.cs_movie_right_arrow));
		rightArrow.setScaleType(ScaleType.FIT_XY);
		layout.addView(rightArrow);

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

		if (this.topicListViewListener != null) {
			topicListViewListener.onGotoPage(pageNo);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		LogUtil.d("TopicListView----onKeyDown----" + selectedItemIndex + "---FilmClassSize--->" + filmClass.size());
		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (selectedItemIndex > 0) {
				if (selectedItemIndex == ITEM_SIZE / 2) {
					previousPage();
				} else {
					topicListItemView[selectedItemIndex].OnItemSeletced(false);
					topicListItemView[--selectedItemIndex].OnItemSeletced(true);
				}
			}else {
				previousPage();
			}
			return true;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (filmClass != null && selectedItemIndex < filmClass.size() - 1) {
				if (selectedItemIndex == ITEM_SIZE / 2 - 1) {
					nextPage();
				} else {
					topicListItemView[selectedItemIndex].OnItemSeletced(false);
					topicListItemView[++selectedItemIndex].OnItemSeletced(true);
				}
			} else {
				nextPage();
			}
			return true;
		case KeyEvent.KEYCODE_DPAD_UP:
			if (selectedItemIndex >= ITEM_SIZE / 2) {
				topicListItemView[selectedItemIndex].OnItemSeletced(false);
				selectedItemIndex -= ITEM_SIZE / 2;
				topicListItemView[selectedItemIndex].OnItemSeletced(true);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (filmClass != null && selectedItemIndex < ITEM_SIZE / 2
					&& filmClass.size() > ITEM_SIZE / 2) {
				topicListItemView[selectedItemIndex].OnItemSeletced(false);
				if ((selectedItemIndex + ITEM_SIZE / 2) < filmClass.size()) {
					selectedItemIndex += ITEM_SIZE / 2;
				} else {
					selectedItemIndex = ITEM_SIZE / 2;
				}
				topicListItemView[selectedItemIndex].OnItemSeletced(true);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			if (topicListViewListener != null && filmClass != null) {
				topicListViewListener.onItemSelected(filmClass
						.get(selectedItemIndex));
				return true;
			}
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {

		if (filmClass == null || filmClass.size() == 0) {
			return;
		}

		// LinearLayout.LayoutParams layout_middle_up_parma = new
		// LinearLayout.LayoutParams(
		// LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		if (gainFocus) {
			// layout_middle_up_parma.setMargins(5, 25, 5, 0);
			topicListItemView[selectedItemIndex].OnItemSeletced(true);
			// topicListItemView[selectedItemIndex]
			// .setLayoutParams(layout_middle_up_parma);
		} else {
			// layout_middle_up_parma.setMargins(10, 30, 10, 5);
			topicListItemView[selectedItemIndex].OnItemSeletced(false);
			// topicListItemView[selectedItemIndex]
			// .setLayoutParams(layout_middle_up_parma);

		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

}
