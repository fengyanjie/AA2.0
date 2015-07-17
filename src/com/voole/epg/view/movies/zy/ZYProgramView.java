package com.voole.epg.view.movies.zy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.voole.epg.R;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.navigation.FilmClass;
import com.voole.epg.view.navigation.NavigationItemSelectedListener;
import com.voole.tvutils.ImageUtil;

public class ZYProgramView extends BaseRelativeLayout {
	private int ITEM_SIZE = 14;
	private TextView[] textViewItem;
	private int selectedItemIndex = 0;
	private int currentPageNo = -1;
	private int totalPageSize = -1;
	private List<FilmClass> filmClass = null;
	private Map<Integer, Integer> pageAndIndex = null;

	private ZYProgramViewListener zyProgramViewListener;

	public int getITEM_SIZE() {
		return ITEM_SIZE;
	}
	

	public void setSelectedItemIndex(int selectedItemIndex) {
		this.selectedItemIndex = selectedItemIndex;
	}





	public void setZyProgramViewListener(
			ZYProgramViewListener zyProgramViewListener) {
		this.zyProgramViewListener = zyProgramViewListener;
	}

	public void setPageInfo(int pageNo, int totalPageSize) {
		this.currentPageNo = pageNo;
		this.totalPageSize = totalPageSize;
	}

	public interface ZYProgramViewListener {
		public void onItemSelected(FilmClass item);

		public void onGotoPage(int pageNo);
	}

	public void setData(List<FilmClass> fc) {
		filmClass = fc;
		int len = filmClass.size();
		for (int i = 0; i < len; i++) {
			if (len < ITEM_SIZE) {
				for (int x = ITEM_SIZE - 1; x >= len; x--) {
					textViewItem[x].setText("");
				}
			}
			textViewItem[i].setText(filmClass.get(i).getFilmClassName());
		}
	}

	public ZYProgramView(Context context) {
		super(context);
		init(context);
	}

	public ZYProgramView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ZYProgramView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);
		textViewItem = new TextView[ITEM_SIZE];
		initProgram(context);
	}

	private void initProgram(Context context) {
		// leftArrow
		ImageView leftArrow = new ImageView(context);
		leftArrow.setId(ID.ZYProgramView.LEFT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_left = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_left.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_arrow_left.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_left.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		leftArrow.setLayoutParams(param_arrow_left);
		leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
				R.drawable.cs_movie_left_arrow));
		leftArrow.setScaleType(ScaleType.FIT_XY);
		addView(leftArrow);

		// program layout
		LinearLayout layout_middle = new LinearLayout(context);

		layout_middle.setOrientation(LinearLayout.VERTICAL);
		RelativeLayout.LayoutParams layout_middle_param = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		layout_middle_param.addRule(RelativeLayout.RIGHT_OF,
				ID.ZYProgramView.LEFT_ARROW_ID);
		layout_middle_param.addRule(RelativeLayout.LEFT_OF,
				ID.ZYProgramView.RIGHT_ARROW_ID);
		layout_middle.setLayoutParams(layout_middle_param);

		for (int i = 0; i < ITEM_SIZE; i++) {
			textViewItem[i] = getTextView(context, "");
		}
		for (int i = 0; i < ITEM_SIZE / 2; i++) {
			layout_middle.addView(getLinearLayoutItem(context, i * 2));
		}
		addView(layout_middle);
		// rightArrow
		ImageView rightArrow = new ImageView(context);
		rightArrow.setId(ID.ZYProgramView.RIGHT_ARROW_ID);
		RelativeLayout.LayoutParams param_arrow_right = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
		param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param_arrow_right.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		rightArrow.setLayoutParams(param_arrow_right);
		rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
				R.drawable.cs_movie_right_arrow));
		rightArrow.setScaleType(ScaleType.FIT_XY);
		addView(rightArrow);
	}

	private LinearLayout getLinearLayoutItem(Context context, int index) {
		LinearLayout layout_middle_item = new LinearLayout(context);

		layout_middle_item.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams layout_middle_item_param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		layout_middle_item_param.topMargin = DisplayManager.GetInstance()
				.changeWidthSize(10);
		layout_middle_item_param.bottomMargin = DisplayManager.GetInstance()
				.changeWidthSize(10);
		layout_middle_item.setLayoutParams(layout_middle_item_param);
		layout_middle_item.addView(textViewItem[index]);
		layout_middle_item.addView(textViewItem[index + 1]);
		return layout_middle_item;
	}

	private TextView getTextView(Context context, String textValue) {
		TextView textView = new TextView(context);
		textView.setText(textValue);
		LinearLayout.LayoutParams param_text = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		// param_text.setMargins(25, 10, 25, 10);
		param_text.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(25);
		param_text.rightMargin = DisplayManager.GetInstance().changeWidthSize(
				25);
		textView.setLayoutParams(param_text);
		textView.setPadding(0, 2, 2, 0);
		textView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		textView.setTextColor(Color.WHITE);
		textView.setBackgroundResource(R.drawable.cs_zy_program);
		textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, DisplayManager.GetInstance().changeTextSize(22));
		return textView;

	}

	private void nextPage() {
		gotoPage(currentPageNo);
		if (currentPageNo < totalPageSize) {
			currentPageNo++;
			itemChanged(textViewItem[selectedItemIndex], false);
			selectedItemIndex = 0;
			itemChanged(textViewItem[selectedItemIndex], true);
			gotoPage(currentPageNo);
		}
	}

	private void previousPage() {
		if (currentPageNo > 1) {
			currentPageNo--;
			itemChanged(textViewItem[selectedItemIndex], false);
			selectedItemIndex = 0;
			itemChanged(textViewItem[selectedItemIndex], true);
			gotoPage(currentPageNo);
		}
	}

	private void gotoPage(int pageNo) {

		if (zyProgramViewListener != null) {
			zyProgramViewListener.onGotoPage(pageNo);
		}

		// int i = pageAndIndex.get(pageNo);
		// List<FilmClass> l = null;
		// int len = 0;
		// if (pageNo == totalPageSize) {
		// l = filmClass.subList(i, filmClass.size());
		// } else {
		// l = filmClass.subList(i, i + ITEM_SIZE);
		// }
		// len = l.size();
		// for (int j = 0; j < len; j++) {
		// if (len < ITEM_SIZE) {
		// for (int x = ITEM_SIZE - 1; x >= len; x--) {
		// textViewItem[x].setText("");
		// }
		// }
		// textViewItem[j].setText(filmClass.get(j).getFilmClassName());
		// }
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (selectedItemIndex > 1) {
				itemChanged(textViewItem[selectedItemIndex], false);
				selectedItemIndex -= 2;
				itemChanged(textViewItem[selectedItemIndex], true);
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (selectedItemIndex < 12) {
				if ("".equals(textViewItem[2 + selectedItemIndex].getText())) {
					break;
				} else {
					itemChanged(textViewItem[selectedItemIndex], false);
					selectedItemIndex = selectedItemIndex + 2;
					itemChanged(textViewItem[selectedItemIndex], true);
				}
				return true;
			}

			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (selectedItemIndex >= 0) {
				if (selectedItemIndex % 2 == 0) {
					previousPage();
				} else {
					itemChanged(textViewItem[selectedItemIndex], false);
					itemChanged(textViewItem[--selectedItemIndex], true);
				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if ((selectedItemIndex < ITEM_SIZE)) {
				if (selectedItemIndex % 2 == 0) {
					if (!"".equals(textViewItem[1 + selectedItemIndex]
							.getText())) {
						itemChanged(textViewItem[selectedItemIndex], false);
						itemChanged(textViewItem[++selectedItemIndex], true);
					}
				} else {
					nextPage();
				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			if (zyProgramViewListener != null && filmClass != null) {
				zyProgramViewListener.onItemSelected(filmClass
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

		if (gainFocus) {
			itemChanged(textViewItem[selectedItemIndex], true);
		} else {
			itemChanged(textViewItem[selectedItemIndex], false);
		}
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	/**
	 * 控件选中或未选中背景的变换
	 * 
	 * @param tv
	 * @param isSelectd
	 */
	private void itemChanged(TextView tv, boolean isSelectd) {
		if (isSelectd) {
			tv.setTextColor(Color.rgb(0xe4, 0x7d, 0x38));
			tv.setBackgroundResource(R.drawable.cs_zy_program_selected);
		} else {
			tv.setTextColor(Color.WHITE);
			tv.setBackgroundResource(R.drawable.cs_zy_program);
		}
		tv.setPadding(0, 2, 2, 0);
	}

}
