package com.voole.epg.base.common;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.tvutils.ImageUtil;

public class NumberNavigator extends BaseRelativeLayout {
	private static final int ITEM_SIZE = 20;
	private static final int PAGE_SIZE = 5;
	private NumberView numberView = null;
	private PageView pageView = null;
	private int totalSize = 0;
	private int totalPageSize = 0;
	
	private List<String> num = null;

	private NumberNavigatorListener numberNavigatorListener = null;

	public void setNumberNavigatorListener(NumberNavigatorListener l) {
		numberNavigatorListener = l;
	}

	public NumberNavigator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NumberNavigator(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public NumberNavigator(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		initDown(context);
		initUp(context);
	}

	private void initUp(Context context) {
		pageView = new PageView(context);
		pageView.setNextFocusUpId(ID.MovieDetaiView.DETAIL_LEFT_BTN_ID );
		RelativeLayout.LayoutParams layout_param = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, DisplayManager.GetInstance().changeHeightSize(120));
		layout_param.addRule(RelativeLayout.ABOVE, ID.NumberNavigator.DOWN_ID);
		layout_param.leftMargin = - DisplayManager.GetInstance().changeWidthSize(2);
		layout_param.bottomMargin = -DisplayManager.GetInstance().changeHeightSize(7);
		pageView.setLayoutParams(layout_param);
		addView(pageView);
	}

	/*public void setFocus() {
		pageView.setPageViewFocus();
	}*/

	private void initDown(Context context) {
		numberView = new NumberView(context);
		numberView.setId(ID.NumberNavigator.DOWN_ID);
		RelativeLayout.LayoutParams layout_param = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, DisplayManager.GetInstance().changeHeightSize(150));
		layout_param.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		numberView.setLayoutParams(layout_param);
		addView(numberView);
	}

	public void setTotalSize(int size) {
		this.totalSize = size;
		this.totalPageSize = (totalSize - 1) / ITEM_SIZE + 1;
		pageView.setPages();
		numberView.setPageNo(1);
	}

	public void setCurrentNumber(int current) {
		int pageViewCurrentPage = (current - 1) / ITEM_SIZE + 1;
		pageView.clearPageViewFocus();
		pageView.setCurrentPage(pageViewCurrentPage);
		pageView.setPageViewFocus();
		int index = (current - 1) % ITEM_SIZE;
		numberView.setPageNo(pageViewCurrentPage);
		numberView.setCurrentIndex(index);
	}

	public void setClickedNumber(List<String> numbers) {
		this.num = numbers;
		numberView.updateClicked();
	}
	
	public void addClickedNumber(List<String> numbers) {
		if(numbers == null || numbers.size() < 1){
			return;
		}
		if(this.num == null || this.num.size() < 1){
			this.num = numbers;
		}else{
			for(String s : numbers){
				if(!this.num.contains(s)){
					this.num.add(s);
				}
			}
		}
		numberView.updateClicked();
	}

	public void getFocus() {
		pageView.setPageViewFocus();
		numberView.requestFocus();
	}

	public class PageView extends BaseRelativeLayout {
		private PageTextView[] pageViews = null;
		// private ImageView leftArrow = null;
		// private ImageView rightArrow = null;
		private ImageView arrow = null;
		private LinearLayout layout = null;

		private int selectIndex = 0;
		private int currentTotalPageSize = 1;
		private int currentPageNo = 1;

		public PageView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public PageView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public PageView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
			pageViews = new PageTextView[PAGE_SIZE];

			/*
			 * rightArrow = new ImageView(context);
			 * rightArrow.setId(ID.NumberNavigator.RIGHT_ARROW_ID);
			 * RelativeLayout.LayoutParams param_arrow_right = new
			 * RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
			 * LayoutParams.WRAP_CONTENT);
			 * param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			 * param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
			 * rightArrow.setLayoutParams(param_arrow_right);
			 * rightArrow.setScaleType(ScaleType.FIT_XY);
			 * rightArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
			 * R.drawable.cs_number_navigator_right_arrow));
			 * rightArrow.setVisibility(View.INVISIBLE); addView(rightArrow);
			 * 
			 * leftArrow = new ImageView(context);
			 * leftArrow.setId(ID.NumberNavigator.LEFT_ARROW_ID);
			 * RelativeLayout.LayoutParams param_arrow_left = new
			 * RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
			 * LayoutParams.WRAP_CONTENT);
			 * param_arrow_left.addRule(RelativeLayout.LEFT_OF,
			 * ID.NumberNavigator.RIGHT_ARROW_ID);
			 * param_arrow_left.addRule(RelativeLayout.CENTER_VERTICAL);
			 * leftArrow.setLayoutParams(param_arrow_left);
			 * leftArrow.setImageBitmap(ImageUtil.getResourceBitmap(context,
			 * R.drawable.cs_number_navigator_left_arrow));
			 * leftArrow.setScaleType(ScaleType.FIT_XY);
			 * leftArrow.setVisibility(View.INVISIBLE); addView(leftArrow);
			 */

			arrow = new ImageView(context);
			arrow.setId(ID.NumberNavigator.RIGHT_ARROW_ID);
			RelativeLayout.LayoutParams param_arrow_right = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			param_arrow_right.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			param_arrow_right.addRule(RelativeLayout.CENTER_VERTICAL);
			arrow.setLayoutParams(param_arrow_right);
			arrow.setScaleType(ScaleType.FIT_XY);
			addView(arrow);

			layout = new LinearLayout(context);
			RelativeLayout.LayoutParams layout_param = new RelativeLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			layout_param.addRule(RelativeLayout.LEFT_OF,
					ID.NumberNavigator.RIGHT_ARROW_ID);
			layout_param.rightMargin = DisplayManager.GetInstance()
					.changeWidthSize(30);
			layout.setLayoutParams(layout_param);

			LinearLayout.LayoutParams item_param = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			item_param.rightMargin = DisplayManager.GetInstance()
					.changeWidthSize(30);

			for (int i = 0; i < PAGE_SIZE; i++) {
				pageViews[i] = new PageTextView(context);
				pageViews[i].setLayoutParams(item_param);
				layout.addView(pageViews[i]);
			}

			/*
			 * for(int i = 0; i < PAGE_SIZE; i++){ itemViews[i] = new
			 * PageTextView(context);
			 * itemViews[i].setId(ID.NumberNavigator.FIRST_ID + i);
			 * RelativeLayout.LayoutParams item_param = new
			 * RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
			 * LayoutParams.WRAP_CONTENT); if(i == 0){
			 * item_param.addRule(RelativeLayout.ALIGN_PARENT_LEFT); }else{
			 * item_param.addRule(RelativeLayout.RIGHT_OF,
			 * ID.NumberNavigator.FIRST_ID + i -1); } item_param.rightMargin =
			 * DisplayManager.GetInstance().changeWidthSize(30);
			 * itemViews[i].setLayoutParams(item_param);
			 * layout.addView(itemViews[i]); }
			 */

			addView(layout);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if (selectIndex == 0) {
					prePage();
				} else if (selectIndex > 0) {
					pageViews[selectIndex]
							.setCurrentStatus(PageTextView.NORMAL);
					pageViews[--selectIndex]
							.setCurrentStatus(PageTextView.SELECTED);
					setCurrentNumber();
				}
				return true;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (selectIndex == PAGE_SIZE - 1) {
					nextPage();
				} else {
					int end = PAGE_SIZE;
					if (this.currentPageNo == this.currentTotalPageSize
							&& totalPageSize % PAGE_SIZE != 0) {
						end = totalPageSize % PAGE_SIZE;
					}
					if (selectIndex < end - 1) {
						pageViews[selectIndex]
								.setCurrentStatus(PageTextView.NORMAL);
						pageViews[++selectIndex]
								.setCurrentStatus(PageTextView.SELECTED);
						setCurrentNumber();
					}
				}

				return true;
			default:
				break;
			}
			return super.onKeyDown(keyCode, event);
		}

		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
			if (gainFocus) {
				pageViews[selectIndex].setCurrentStatus(PageTextView.SELECTED);
			} else {
				pageViews[selectIndex].setCurrentStatus(PageTextView.FOCUSED);
			}
		}

		private void prePage() {
			if (currentPageNo > 1) {
				this.currentPageNo--;
				pageViews[selectIndex].setCurrentStatus(PageTextView.NORMAL);
				this.selectIndex = PAGE_SIZE - 1;
				pageViews[selectIndex].setCurrentStatus(PageTextView.SELECTED);
				setCurrentNumber();
				updateUI();
			}
		}

		public void setPageViewFocus() {
			pageViews[selectIndex].setCurrentStatus(PageTextView.FOCUSED);
		}
		
		public void clearPageViewFocus() {
			pageViews[selectIndex].setCurrentStatus(PageTextView.NORMAL);
		}
		
		public void setPageViewSelected(){
			pageViews[selectIndex].setCurrentStatus(PageTextView.SELECTED);
		}

		private void nextPage() {
			if (currentPageNo < currentTotalPageSize) {
				this.currentPageNo++;
				pageViews[selectIndex].setCurrentStatus(PageTextView.NORMAL);
				this.selectIndex = 0;
				pageViews[selectIndex].setCurrentStatus(PageTextView.SELECTED);
				setCurrentNumber();
				updateUI();
			}
		}

		private void updateUI() {
			int currentStartPageIndex = (this.currentPageNo - 1) * PAGE_SIZE;
			int end = PAGE_SIZE;
			if (this.currentPageNo == this.currentTotalPageSize
					&& totalPageSize % PAGE_SIZE != 0) {
				end = totalPageSize % PAGE_SIZE;
			}

			for (int i = 0, j = currentStartPageIndex; i < end; i++, j++) {
				pageViews[i].setVisibility(View.VISIBLE);
				if (j == totalPageSize - 1) {
					if (totalSize % ITEM_SIZE == 0) {
						pageViews[i].setText((j * ITEM_SIZE + 1) + "-"
								+ ((j + 1) * ITEM_SIZE));
					} else {
						pageViews[i].setText((j * ITEM_SIZE + 1) + "-"
								+ (j * ITEM_SIZE + totalSize % ITEM_SIZE));
					}
				} else {
					pageViews[i].setText((j * ITEM_SIZE + 1) + "-"
							+ ((j + 1) * ITEM_SIZE));
				}
			}

			for (int i = end; i < PAGE_SIZE; i++) {
				pageViews[i].setVisibility(View.INVISIBLE);
			}

			if (currentTotalPageSize == 1) {
				arrow.setVisibility(View.INVISIBLE);
			} else {
				arrow.setVisibility(View.VISIBLE);
				if (currentPageNo > 1) {
					arrow.setImageBitmap(ImageUtil.getResourceBitmap(mContext,
							R.drawable.cs_number_navigator_left_arrow));
				} else {
					arrow.setImageBitmap(ImageUtil.getResourceBitmap(mContext,
							R.drawable.cs_number_navigator_right_arrow));
				}
			}
		}

		private void setCurrentNumber() {
			numberView.setPageNo((this.currentPageNo - 1) * PAGE_SIZE
					+ selectIndex + 1);
			numberView.setSelectIndex(0);
		}

		public void setPages() {
			this.selectIndex = 0;
			this.currentPageNo = 1;
			this.currentTotalPageSize = (totalPageSize - 1) / PAGE_SIZE + 1;
			updateUI();
		}

		public void setCurrentPage(int page) {
			this.selectIndex = (page - 1) % PAGE_SIZE;
			this.currentPageNo = (page - 1) / PAGE_SIZE + 1;;
			updateUI();
		}
	}

	public class NumberView extends BaseLinearLayout {
		private NumberTextView[] itemViews = null;
		private int selectNumIndex = 0;
		private int pageNo = 0;
		private int currentTotalSize = 0;

		public NumberView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public NumberView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public NumberView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setBackgroundResource(R.drawable.cs_number_navigator_number_bg);
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
			setOrientation(VERTICAL);
			itemViews = new NumberTextView[ITEM_SIZE];

			LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			param_item.gravity = Gravity.CENTER;
			param_item.setMargins(5, 5, 5, 5);
			// param_item.addRule(RelativeLayout.CENTER_IN_PARENT);

			LinearLayout.LayoutParams layout_param_up = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			layout_param_up.leftMargin = 10;
			layout_param_up.rightMargin = 10;
			layout_param_up.topMargin = 10;
			// number up
			LinearLayout layout_up = new LinearLayout(context);
			layout_up.setLayoutParams(layout_param_up);
			for (int i = 0; i < ITEM_SIZE / 2; i++) {
				/*
				 * RelativeLayout layout_item = new RelativeLayout(context);
				 * layout_item.setLayoutParams(layout_param_up);
				 */
				itemViews[i] = new NumberTextView(context);
				itemViews[i].setLayoutParams(param_item);
				itemViews[i].setText("" + i);
				// layout_item.addView(itemViews[i]);
				// layout_up.addView(layout_item);
				layout_up.addView(itemViews[i]);
			}
			addView(layout_up);

			// number down
			LinearLayout.LayoutParams layout_param_down = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			layout_param_down.leftMargin = 10;
			layout_param_down.rightMargin = 10;
			layout_param_down.bottomMargin = 10;
			LinearLayout layout_down = new LinearLayout(context);
			layout_down.setLayoutParams(layout_param_down);
			for (int i = ITEM_SIZE / 2; i < ITEM_SIZE; i++) {
				// RelativeLayout layout_item = new RelativeLayout(context);
				// layout_item.setLayoutParams(layout_param_down);
				itemViews[i] = new NumberTextView(context);
				itemViews[i].setLayoutParams(param_item);
				itemViews[i].setText("" + i);
				// layout_item.addView(itemViews[i]);
				// layout_down.addView(layout_item);
				layout_down.addView(itemViews[i]);
			}
			addView(layout_down);
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			switch (keyCode) {
			case KeyEvent.KEYCODE_DPAD_UP:
				if (selectNumIndex >= ITEM_SIZE / 2) {
					itemViews[selectNumIndex].setFocusedItem(false);
					selectNumIndex -= ITEM_SIZE / 2;
					itemViews[selectNumIndex].setFocusedItem(true);
					return true;
				}
				break;
			case KeyEvent.KEYCODE_DPAD_DOWN:
				if (selectNumIndex < ITEM_SIZE / 2
						&& currentTotalSize > ITEM_SIZE / 2) {
					itemViews[selectNumIndex].setFocusedItem(false);
					if ((selectNumIndex + ITEM_SIZE / 2) < currentTotalSize) {
						selectNumIndex += ITEM_SIZE / 2;
					} else {
						selectNumIndex = ITEM_SIZE / 2;
					}
					itemViews[selectNumIndex].setFocusedItem(true);
					return true;
				}
				break;
			case KeyEvent.KEYCODE_DPAD_LEFT:
				if (selectNumIndex > 0) {
					itemViews[selectNumIndex].setFocusedItem(false);
					itemViews[--selectNumIndex].setFocusedItem(true);
				}
				return true;
			case KeyEvent.KEYCODE_DPAD_RIGHT:
				if (selectNumIndex < currentTotalSize - 1) {
					itemViews[selectNumIndex].setFocusedItem(false);
					itemViews[++selectNumIndex].setFocusedItem(true);
				}
				return true;
			case KeyEvent.KEYCODE_ENTER:
			case KeyEvent.KEYCODE_DPAD_CENTER:
				if (numberNavigatorListener != null) {
					numberNavigatorListener.onGotoNumber(Integer
							.parseInt(itemViews[selectNumIndex].getText()
									.toString()));
					/*if(num == null){
						num = new ArrayList<String>();
					}
					if(!num.contains(itemViews[selectNumIndex].getText().toString())){
						num.add(itemViews[selectNumIndex].getText().toString());
					}*/
					return true;
				}
				break;
			default:
				break;
			}
			return super.onKeyDown(keyCode, event);
		}

		/*
		 * public void setPageNo(int pageNo){ this.pageNo = pageNo;
		 * if(this.pageNo < totalPageSize){ this.currentTotalSize = ITEM_SIZE;
		 * }else{ if(totalSize % ITEM_SIZE == 0){ this.currentTotalSize =
		 * ITEM_SIZE; }else{ this.currentTotalSize = (totalSize % ITEM_SIZE); }
		 * } for(int i=0; i<currentTotalSize; i++){
		 * itemViews[i].setVisibility(View.VISIBLE);
		 * itemViews[i].setText(String.valueOf((pageNo - 1) * ITEM_SIZE + i +
		 * 1)); } for(int j=currentTotalSize; j<ITEM_SIZE; j++){
		 * itemViews[j].setVisibility(View.INVISIBLE); } setCurrentIndex(0); }
		 */

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
			if (this.pageNo < totalPageSize) {
				this.currentTotalSize = ITEM_SIZE;
			} else {
				if (totalSize % ITEM_SIZE == 0) {
					this.currentTotalSize = ITEM_SIZE;
				} else {
					this.currentTotalSize = (totalSize % ITEM_SIZE);
				}
			}
			for (int i = 0; i < currentTotalSize; i++) {
				itemViews[i].setVisibility(View.VISIBLE);
				itemViews[i].setText(String.valueOf((pageNo - 1) * ITEM_SIZE
						+ i + 1));
			}
			for (int j = currentTotalSize; j < ITEM_SIZE; j++) {
				itemViews[j].setVisibility(View.INVISIBLE);
			}
			updateClicked();
		}

		private void setCurrentIndex(int index) {
			itemViews[selectNumIndex].setFocusedItem(false);
			this.selectNumIndex = index;
			itemViews[selectNumIndex].setFocusedItem(true);
		}
		
		public void setSelectIndex(int index){
			this.selectNumIndex = index;
		}
		
		public void updateClicked(){
			if(num == null || num.size() < 1){
				return;
			}
			for(int i=0; i<ITEM_SIZE; i++){
				if(itemViews[i].getVisibility() == View.VISIBLE){
					boolean isFind = false;
					for(int j=0; j<num.size(); j++){
						if(num.get(j).equals(itemViews[i].getText())){
							itemViews[i].setClicked(true);
							isFind = true;
							break;
						}
					}
					if(!isFind){
						itemViews[i].setClicked(false);
					}
				}
			}
		}

		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			if (gainFocus) {
				if(selectNumIndex >= currentTotalSize){
					selectNumIndex = 0;
				}
				itemViews[selectNumIndex].setFocusedItem(true);
			} else {
				itemViews[selectNumIndex].setFocusedItem(false);
			}
			super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		}
	}

	public class NumberTextView extends TextView {
		private boolean isClicked = false;
		private boolean isFocus = false;
		public NumberTextView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public NumberTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public NumberTextView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);
			setTextColor(Color.rgb(0x55, 0x55, 0x55));
			setPadding(5, 3, 5, 3);
			// setFocusable(true);
			// setFocusableInTouchMode(true);
			// setClickable(true);
			setGravity(Gravity.CENTER);
		}
		
		public void setClicked(boolean isClicked){
			this.isClicked = isClicked;
			if(this.isFocus){
				return;
			}
			if(this.isClicked){
				//setTextColor(Color.rgb(0x86, 0x4e, 0x21));
				setTextColor(Color.WHITE);
			}else{
				setTextColor(Color.rgb(0x55, 0x55, 0x55));
			}
		}

		public void setFocusedItem(boolean isFocus) {
			this.isFocus = isFocus;
			if (isFocus) {
				setTextColor(Color.WHITE);
				setBackgroundResource(R.drawable.cs_btn_focus);
			} else {
				if(this.isClicked){
					//setTextColor(Color.rgb(0x86, 0x4e, 0x21));
					setTextColor(Color.WHITE);
				}else{
					setTextColor(Color.rgb(0x55, 0x55, 0x55));
				}
				setBackgroundResource(0);
			}
			setPadding(5, 3, 5, 3);
		}
	}

	public class PageTextView extends TextView {
		public static final int SELECTED = 1;
		public static final int NORMAL = 2;
		public static final int FOCUSED = 3;

		public PageTextView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public PageTextView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public PageTextView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setTextSize(TypedValue.COMPLEX_UNIT_DIP,24);
			setTextColor(Color.rgb(0x55, 0x55, 0x55));
			setBackgroundResource(R.drawable.cs_number_navigator_page_unselected_bg);
			// setFocusable(true);
			// setFocusableInTouchMode(true);
			// setClickable(true);
			setPadding(0, 5, 0, 0);
			setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CLIP_VERTICAL);
		}

		private void setCurrentStatus(int status) {
			if (SELECTED == status) {
				setTextColor(Color.rgb(0xe4, 0x7d, 0x38));
				// setBackgroundResource(R.drawable.cs_btn_focus);
				setBackgroundResource(R.drawable.cs_number_navigator_page_selected_bg);
			} else if (FOCUSED == status) {
				setBackgroundResource(R.drawable.cs_number_navigator_page_selected_bg);
				setTextColor(Color.rgb(0x55, 0x55, 0x55));
			} else if (NORMAL == status) {
				setBackgroundResource(R.drawable.cs_number_navigator_page_unselected_bg);
				setTextColor(Color.rgb(0x55, 0x55, 0x55));
			}
		}
	}

	public interface NumberNavigatorListener {
		public void onGotoNumber(int number);
	}
}
