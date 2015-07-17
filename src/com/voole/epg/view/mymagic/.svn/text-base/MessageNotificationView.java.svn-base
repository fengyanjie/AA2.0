package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.corelib.model.account.MessageInfo;

public class MessageNotificationView extends BaseLinearLayout {
	private static final int ITEM_SIZE = 5;
	private MessageNotificationItemView[] itemViews = null;
	private PageNavigator pageNavigator = null;

	private List<MessageInfo> infos = null;
	private List<MessageInfo> currentInfos = null;

	// private int currentFocusedIndex = -1;
	private int currentPage = 0;
	private int totalPage = 0;
	private int totalSize = 0;

//	private int currentSelectIndex = 0;

	private Drawable focusedDrawable = getContext().getResources().getDrawable(
			R.drawable.cs_rank_selected_bg);
	private final int txt_size = DisplayManager.GetInstance()
			.changeTextSize(26);

	private MessageNotificationItemListener listener = null;

	public void setMessageNotifcationItemListener(
			MessageNotificationItemListener listener) {
		this.listener = listener;
	}

	public MessageNotificationView(Context context) {
		super(context);
		init(context);
	}

	public MessageNotificationView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MessageNotificationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void setData(List<MessageInfo> infos) {
		if(infos==null||infos.size()==0){
			for(int i=0;i<ITEM_SIZE;i++){
				itemViews[i].setVisibility(INVISIBLE);
			}
			pageNavigator.setVisibility(INVISIBLE);
		}else{
			pageNavigator.setVisibility(VISIBLE);
		}
		this.infos = infos;
		initPageInfo(this.infos.size());
		getCurrentMessageList();
		setListDate();
	}

	
	
	private void setListDate() {
		if (currentInfos != null && currentInfos.size() > 0) {
			int itemSize = currentInfos.size();
			for (int i = 0; i < ITEM_SIZE; i++) {
				if (i < itemSize) {
					final MessageInfo info = currentInfos.get(i);
					itemViews[i].setVisibility(View.VISIBLE);
					itemViews[i].setTheme(info.getSubject());
					itemViews[i].setSender(info.getFrom());
					itemViews[i].setTime(info.getStartdate().substring(0,10));
				} else {
					itemViews[i].setVisibility(View.INVISIBLE);
				}
			}
		}

	}

	private void initPageInfo(int totalSize) {
		currentPage = 1;
		this.totalSize = totalSize;
		if (totalSize % ITEM_SIZE > 0) {
			this.totalPage = totalSize / ITEM_SIZE + 1;
		} else {
			this.totalPage = totalSize / ITEM_SIZE;
		}
		pageNavigator.setPageInfo(currentPage, totalPage);
	}

	private void GotoPage(int pageNo) {
		this.currentPage = pageNo;
		pageNavigator.setPageInfo(currentPage, totalPage);
		getCurrentMessageList();
		setListDate();
	}

	private void getCurrentMessageList() {
		int startPosition = (currentPage - 1) * ITEM_SIZE;
		int endPosition;
		if (totalSize > currentPage * ITEM_SIZE) {
			// endPosition = currentPage*ITEM_SIZE - 1;
			endPosition = currentPage * ITEM_SIZE;
		} else {
			// endPosition = totalSize - 1;
			endPosition = totalSize;
		}
		currentInfos = infos.subList(startPosition, endPosition);
	}

	/**
	 * up to down : 1:1.5:1.5:1.5:1.5:1.5:1.5
	 * 
	 * @param context
	 */
	private void init(Context context) {
		this.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout layout_title = new LinearLayout(context);
		layout_title.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams param_layout_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 18);
		layout_title.setLayoutParams(param_layout_title);

		TextView theme = new TextView(context);
		theme.setSingleLine(true);
		theme.setGravity(Gravity.CENTER);
		theme.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
		theme.setText("主题");
		theme.setTextColor(Color.WHITE);
		theme.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_theme = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
		theme.setLayoutParams(param_theme);
		layout_title.addView(theme);

		TextView sender = new TextView(context);
		sender.setGravity(Gravity.CENTER);
		sender.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
		sender.setText("发件人");
		sender.setTextColor(Color.WHITE);
		sender.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_sender = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 8);
		param_sender.leftMargin = 5;
		sender.setLayoutParams(param_sender);
		layout_title.addView(sender);

		TextView time = new TextView(context);
		time.setGravity(Gravity.CENTER);
		time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
		time.setText("时间");
		time.setTextColor(Color.WHITE);
		time.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_time = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
		param_time.leftMargin = 5;
		time.setLayoutParams(param_time);
		layout_title.addView(time);

		addView(layout_title);

		itemViews = new MessageNotificationItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 17);
		for (int i = 0; i < ITEM_SIZE; i++) {
			final int index = i;
			itemViews[i] = new MessageNotificationItemView(context);
			if(i==0){
				itemViews[i].setId(ID.MyMagicActivity.MC_MESSAGELIST_FIRST_ID);
			}
			itemViews[i].setLayoutParams(param_item);
			itemViews[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) {
						listener.onKey(infos, currentInfos.get(index));
					}
				}
			});
			addView(itemViews[i]);
		}

		pageNavigator = new PageNavigator(context);
		LinearLayout.LayoutParams param_pageNavigator = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 17);
		pageNavigator.setLayoutParams(param_pageNavigator);
		pageNavigator.setPageNavigatorListener(new PageNavigatorListener() {
			@Override
			public void onGotoPage(int pageNo) {
				GotoPage(pageNo);
			}
		});

		addView(pageNavigator);

	}


	public class MessageNotificationItemView extends BaseLinearLayout {
		private AlwaysMarqueeTextView txt_theme = null;
		private TextView txt_sender = null;
		private TextView txt_time = null;

		private final int txt_size = DisplayManager.GetInstance()
				.changeTextSize(25);
		private final int txt_size_focus = DisplayManager.GetInstance()
				.changeTextSize(28);

		public void setTheme(String string) {
			txt_theme.setText(string);
		}

		public void setSender(String string) {
			txt_sender.setText(string);
		}

		public void setTime(String string) {
			txt_time.setText(string);
		}

		public MessageNotificationItemView(Context context) {
			super(context);
			init(context);
		}

		public MessageNotificationItemView(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public MessageNotificationItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		/**
		 * left to right : 5:2:3
		 * 
		 * @param context
		 */
		private void init(Context context) {
			this.setOrientation(HORIZONTAL);
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);
			
			txt_theme = new AlwaysMarqueeTextView(context);
			txt_theme.setMarquee(true);
			txt_theme.setGravity(Gravity.CENTER_VERTICAL);
			txt_theme.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
			LinearLayout.LayoutParams param_txt_theme = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
			param_txt_theme.leftMargin = 5;
			txt_theme.setLayoutParams(param_txt_theme);
			addView(txt_theme);

			txt_sender = new TextView(context);
			txt_sender.setGravity(Gravity.CENTER);
			txt_sender.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
			LinearLayout.LayoutParams param_txt_sender = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 8);
			txt_sender.setLayoutParams(param_txt_sender);
			addView(txt_sender);

			txt_time = new TextView(context);
			txt_time.setGravity(Gravity.CENTER);
			txt_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
			LinearLayout.LayoutParams param_txt_time = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
			txt_time.setLayoutParams(param_txt_time);
			addView(txt_time);
		}


		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			if (gainFocus) {
				txt_theme.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size_focus);
				txt_sender.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size_focus);
				txt_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size_focus);
				this.setBackgroundDrawable(focusedDrawable);
			} else {
				txt_theme.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
				txt_sender.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
				txt_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,txt_size);
				this.setBackgroundDrawable(null);
			}
		}
	}
}
