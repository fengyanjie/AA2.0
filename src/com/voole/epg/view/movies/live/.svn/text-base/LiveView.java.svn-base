package com.voole.epg.view.movies.live;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.corelib.model.navigation.FilmClass;

public class LiveView extends BaseLinearLayout {

	private LiveBigItemView itemBigView;
	private LiveItemView[] itemView;
	private Map<Integer, int[]> sizeAndIndex;
	private int selectedIndex = 0;
	private int[] indexMap;

	private int len = 0;
	private int indexSize = 0;

	private List<FilmClass> filmClass = null;

	private LiveViewListener liveViewListener;
	
	private String indexStr=null;

	private List<Integer> upList = new ArrayList<Integer>();
	private List<Integer> midList = new ArrayList<Integer>();
	private List<Integer> downList = new ArrayList<Integer>();

	public LiveView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public LiveView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LiveView(Context context) {
		super(context);
		init(context);
	}

	
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public void setData(List<FilmClass> fl) {
		filmClass = fl;
		len = filmClass.size();
		if (len > 10) {
			len = 10;
		}
		if (len >= 1) {
			itemBigView.setData(filmClass.get(0));
			if (len >= 2) {
				indexMap = sizeAndIndex.get(len);
				StringBuilder strBuilder=new StringBuilder();
				for(int i=0;i<indexMap.length;i++){
					strBuilder.append(indexMap[i]);
				}
				indexStr=strBuilder.toString();
				setIndexList();
				indexSize = indexMap[indexMap.length - 1] + 1;
				for (int i = 1; i < len; i++) {
					itemView[indexMap[i - 1]].setData(filmClass.get(i),i);
					itemView[indexMap[i - 1]].setNoEmpty(true);
				}

			}

		}
	}

	public void setLiveViewListener(LiveViewListener liveViewListener) {
		this.liveViewListener = liveViewListener;
	}

	public interface LiveViewListener {
		public void onItemSelected(FilmClass item);
	}

	private void init(Context context) {
		setFocusable(true);
		setFocusableInTouchMode(true);
		setClickable(true);

		sizeAndIndex = new HashMap<Integer, int[]>();
		sizeAndIndex.put(10, new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
		sizeAndIndex.put(9, new int[] { 0, 1, 2, 3, 4, 5, 6, 7 });
		sizeAndIndex.put(8, new int[] { 0, 1, 2, 4, 5, 6, 7 });
		sizeAndIndex.put(7, new int[] { 0, 1, 4, 6, 7, 8 });
		sizeAndIndex.put(6, new int[] { 0, 1, 4, 5, 6 });
		sizeAndIndex.put(5, new int[] { 0, 1, 4, 5 });
		sizeAndIndex.put(4, new int[] { 0, 1, 2 });
		sizeAndIndex.put(3, new int[] { 0, 1 });
		sizeAndIndex.put(2, new int[] { 0 });

		setOrientation(HORIZONTAL);
		itemView = new LiveItemView[9];
		initLeft(context);
		initRight(context);
	}

	private void initLeft(Context context) {
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		itemBigView = new LiveBigItemView(context);
		param.leftMargin = 70;
		param.rightMargin = 10;
		itemBigView.setLayoutParams(param);
		itemBigView.setPadding(7, 7, 7, 7);
		addView(itemBigView);
	}

	private void initRight(Context context) {
		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param.leftMargin = 10;
		param.rightMargin = 70;
		LinearLayout right_layout = new LinearLayout(context);
		right_layout.setOrientation(VERTICAL);
		right_layout.setLayoutParams(param);
		addView(right_layout);

		LinearLayout.LayoutParams layout_item_parma = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);

		LinearLayout layout_up = new LinearLayout(context);
		layout_up.setLayoutParams(layout_item_parma);
		right_layout.addView(layout_up);
		for (int i = 0; i < 3; i++) {
			itemView[i] = new LiveItemView(context);
			itemView[i].setLayoutParams(layout_item_parma);
			itemView[i].setPadding(7, 7, 7, 7);
			itemView[i].setImg(i);
			layout_up.addView(itemView[i]);
		}

		LinearLayout layout_middle = new LinearLayout(context);
		layout_middle.setLayoutParams(layout_item_parma);
		right_layout.addView(layout_middle);
		for (int i = 3; i < 6; i++) {
			itemView[i] = new LiveItemView(context);
			itemView[i].setPadding(7, 7, 7, 7);
			itemView[i].setLayoutParams(layout_item_parma);
			itemView[i].setImg(i);
			layout_middle.addView(itemView[i]);
		}

		LinearLayout layout_down = new LinearLayout(context);
		layout_down.setLayoutParams(layout_item_parma);
		right_layout.addView(layout_down);
		for (int i = 6; i < 9; i++) {
			itemView[i] = new LiveItemView(context);
			itemView[i].setPadding(7, 7, 7, 7);
			itemView[i].setLayoutParams(layout_item_parma);
			itemView[i].setImg(i);
			layout_down.addView(itemView[i]);
		}

	}

	private void setIndexList() {
		int size = indexMap.length;
		for (int i = 0; i < size; i++) {
			int index = indexMap[i];
			if (index >= 0 && index <= 2) {
				upList.add(index);
			} else if (index >= 3 && index <= 5) {
				midList.add(index);
			} else if (index >= 6 && index <= 8) {
				downList.add(index);
			}
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (selectedIndex > 0 && selectedIndex - 3 > 0) {
				int index = selectedIndex - 3;
				if (itemView[index - 1].isNoEmpty()) {
					setItemSelected(false);
					selectedIndex = index;
					setItemSelected(true);
				} else {
					if ((index - 1) >= 3 && (index - 1) <= 5) {
						if (midList.size() > 0) {
							setItemSelected(false);
							selectedIndex = midList.get(0) + 1;
							setItemSelected(true);
						} else {
							if (upList.size() > 0) {
								setItemSelected(false);
								selectedIndex = upList.get(0) + 1;
								setItemSelected(true);
							} else {
								break;
							}
						}
					} else if ((index - 1) >= 0 && (index - 1) <= 2) {
						if (upList.size() > 0) {
							setItemSelected(false);
							selectedIndex = upList.get(0) + 1;
							setItemSelected(true);
						} else {
							break;
						}
					}

				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:

			if (selectedIndex > 0 && selectedIndex + 3 <= 9) {
				int index = selectedIndex + 3;
				if (itemView[index - 1].isNoEmpty()) {
					setItemSelected(false);
					selectedIndex = index;
					setItemSelected(true);
				} else {

					if ((index - 1) >= 3 && (index - 1) <= 5) {
						if (itemView[index - 1].isNoEmpty()) {
							setItemSelected(false);
							selectedIndex = index;
							setItemSelected(true);
						} else {
							if (midList.size() > 0) {
								setItemSelected(false);
								selectedIndex = midList.get(0) + 1;
								setItemSelected(true);
							} else {
								if (downList.size() > 0) {
									setItemSelected(false);
									selectedIndex = downList.get(0) + 1;
									setItemSelected(true);
								} else {
									break;
								}
							}

						}
					} else if ((index - 1) >= 6 && (index - 1) <= 8) {
						if (downList.size() > 0) {
							setItemSelected(false);
							selectedIndex = downList.get(0) + 1;
							setItemSelected(true);
						} else {
							break;
						}
					}
				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (selectedIndex > 0 && selectedIndex - 1 >= 0) {
				setItemSelected(false);
				boolean flag = true;
				while (flag) {
					selectedIndex = selectedIndex - 1;
					if (selectedIndex < 1) {
						setItemSelected(true);
						flag = false;
					} else {
						if (itemView[selectedIndex - 1].isNoEmpty()) {
							setItemSelected(true);
							flag = false;
						}
					}

				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (selectedIndex >= 0 && selectedIndex + 1 <= indexSize) {
				setItemSelected(false);
				boolean flag = true;
				while (flag) {
					selectedIndex = selectedIndex + 1;
					if (selectedIndex > 9) {
						flag = false;
					} else {
						if (itemView[selectedIndex - 1].isNoEmpty()) {
							setItemSelected(true);
							flag = false;
						}
					}

				}
				return true;
			}
			break;
		case KeyEvent.KEYCODE_ENTER:
		case KeyEvent.KEYCODE_DPAD_CENTER:
			if (selectedIndex >= 0) {
				if(selectedIndex>0){
					liveViewListener.onItemSelected(filmClass.get(indexStr.indexOf((selectedIndex-1)+"")+1));
				}else{
					liveViewListener.onItemSelected(filmClass.get(selectedIndex));
				}
				
				return true;
			}
			break;
		default:
			break;
		}

		return super.onKeyDown(keyCode, event);
	}

	private void setItemSelected(boolean selected) {

		if (selected) {

			if (selectedIndex == 0) {
				itemBigView
						.setBackgroundResource(R.drawable.cs_recommend_selected_bg);
				itemBigView.setPadding(7, 7, 7, 7);
			} else {
				itemView[selectedIndex - 1]
						.setBackgroundResource(R.drawable.cs_recommend_selected_bg);
				itemView[selectedIndex - 1].setPadding(7, 7, 7, 7);
			}

		} else {
			if (selectedIndex == 0) {
				itemBigView.setBackgroundColor(Color.TRANSPARENT);
				itemBigView.setPadding(7, 7, 7, 7);
			} else {
				itemView[selectedIndex - 1]
						.setBackgroundColor(Color.TRANSPARENT);
				itemView[selectedIndex - 1].setPadding(7, 7, 7, 7);
			}
		}

	}

	
	public void clearAllData(){
		itemBigView.clearData();
		for(int i=0;i<9;i++){
			itemView[i].clearData(i);
		}
		upList.clear();
		midList.clear();
		downList.clear();
		
	}
	
	private boolean isEmpty(int index) {

		for (int i : indexMap) {
			if (i == index) {
				return true;
			}
		}
		return false;

	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {

		if (len == 0) {
			return;
		}

		if (gainFocus) {
			setItemSelected(true);
		} else {
			setItemSelected(false);
		}

		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

}
