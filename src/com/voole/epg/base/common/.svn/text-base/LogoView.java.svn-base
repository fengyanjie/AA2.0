package com.voole.epg.base.common;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.corelib.model.movies.Label;

public class LogoView extends BaseRelativeLayout {
	private static final int MARGIN = DisplayManager.GetInstance()
			.changeWidthSize(30);
	/**
	 * 账号，充值，历史，收藏，搜索
	 */
	private Button btnAccount = null;
	private Button btnRecharge = null;
	private Button btnHistory = null;
	private Button btnFavorite = null;
	private Button btnSearch = null;

	private ImageView logo = null;// 有朋影视图片
	// private TextView channel = null;
	private AlwaysMarqueeTextView channel = null;// 频道
	private ImageView line = null;// 竖线
	private Button find = null;// 查找按钮
	private TextView category = null;//分类按钮右边显示选择的分类

	private Dialog findDialog = null; //对话框
	private FindCatView findCatView = null;//自定义的对话框布局

	private RelativeLayout.LayoutParams param_channel = null;

	public Button getBtnAccount() {
		return btnAccount;
	}

	public Button getBtnRecharge() {
		return btnRecharge;
	}

	public Button getBtnHistory() {
		return btnHistory;
	}

	public Button getBtnFavorite() {
		return btnFavorite;
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public LogoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public LogoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LogoView(Context context) {
		super(context);
		init(context);
	}

	public void setCategory(String name, Label typeLabel, List<Label> list) {
		findCatView.setData(name, typeLabel, list);
	}

	private LogoViewListener logoViewListener = null;

	public void setLogoViewListener(LogoViewListener logoViewListener) {
		this.logoViewListener = logoViewListener;
	}

	public interface LogoViewListener {
		public void onFindCategory();

		public void onStartFinding(String type, String year, String area);
	}

	/**
	 * 分类对话框显示
	 */
	public void showCategory() {
		if (findDialog != null) {
			findDialog.show();
		}
	}

	private void init(final Context context) {
		logo = new ImageView(context);
		logo.setId(ID.LogoView.LOGO_ID);
		RelativeLayout.LayoutParams param_logo = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_logo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_logo.addRule(RelativeLayout.CENTER_VERTICAL);
		param_logo.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(40);
		logo.setLayoutParams(param_logo);
		logo.setBackgroundResource(R.drawable.cs_logoview_logo);
		addView(logo);

		// channel = new TextView(context);
		channel = new AlwaysMarqueeTextView(context);
		channel.setSingleLine(true);
		channel.setEllipsize(TruncateAt.MARQUEE);
		channel.setMarqueeRepeatLimit(-1);
		channel.setHorizontallyScrolling(true);
		channel.setId(ID.LogoView.CHANNEL_ID);
		channel.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
				.GetInstance().changeTextSize(34));
		param_channel = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_channel.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_channel.addRule(RelativeLayout.CENTER_VERTICAL);
		param_channel.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		channel.setLayoutParams(param_channel);
		channel.setVisibility(View.GONE);
		addView(channel);
		// ------------------------------------------------------
		// 频道右边竖线
		line = new ImageView(context);
		line.setId(ID.LogoView.LINE_ID);
		line.setImageResource(R.drawable.shuline);
		RelativeLayout.LayoutParams param_line = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_line.leftMargin = 13;
		param_line.addRule(RelativeLayout.RIGHT_OF, ID.LogoView.CHANNEL_ID);
		param_line.addRule(RelativeLayout.CENTER_VERTICAL);
		line.setLayoutParams(param_line);
		line.setVisibility(View.GONE);
		addView(line);

		findCatView = new FindCatView(context);
		findDialog = new Dialog(context, R.style.alertDialog);
		findDialog.setContentView(findCatView);

		Window window = findDialog.getWindow();
		WindowManager.LayoutParams layoutParams = window.getAttributes();
		layoutParams.width = (int) (DisplayManager.GetInstance()
				.getScreenWidth() * 0.7);
		layoutParams.height = (int) (DisplayManager.GetInstance()
				.getScreenHeight() * 0.6);
		window.setAttributes(layoutParams);

		find = new Button(context);
		find.setId(ID.LogoView.FIND_ID);
		find.setFocusable(true);
		find.setFocusableInTouchMode(true);
		find.setNextFocusLeftId(ID.LogoView.ACCOUNT_ID);
		RelativeLayout.LayoutParams param_find = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_find.leftMargin = 13;
		param_find.addRule(RelativeLayout.RIGHT_OF, ID.LogoView.LINE_ID);
		param_find.addRule(RelativeLayout.CENTER_VERTICAL);
		find.setLayoutParams(param_find);
		find.setVisibility(View.GONE);
		find.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (logoViewListener != null) {
					logoViewListener.onFindCategory();
				}
			}
		});
		find.setBackgroundResource(R.drawable.cs_logoview_find_selector);
		addView(find);

		category = new TextView(context);
		category.setId(ID.LogoView.CATEGORY_ID);
		RelativeLayout.LayoutParams param_category = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_category.leftMargin = 13;
		param_category.addRule(RelativeLayout.RIGHT_OF, ID.LogoView.FIND_ID);
		param_category.addRule(RelativeLayout.CENTER_VERTICAL);
		category.setLayoutParams(param_category);
		category.setVisibility(View.GONE);
		category.setTextColor(Color.WHITE);
		category.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
				.GetInstance().changeTextSize(22));
		addView(category);

		btnSearch = new Button(context);
		btnSearch.setId(ID.LogoView.SEARCH_ID);
		btnSearch.setNextFocusLeftId(ID.LogoView.ACCOUNT_ID);
		btnSearch.setFocusable(true);
		btnSearch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoSearch(context);
			}
		});
		btnSearch.setFocusableInTouchMode(true);
		RelativeLayout.LayoutParams param_search = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_search.addRule(RelativeLayout.LEFT_OF, ID.LogoView.FAVORITE_ID);
		param_search.addRule(RelativeLayout.CENTER_VERTICAL);
		param_search.rightMargin = MARGIN;
		btnSearch.setLayoutParams(param_search);
		btnSearch.setBackgroundResource(R.drawable.cs_logoview_search_selector);
		addView(btnSearch);

		btnFavorite = new Button(context);
		btnFavorite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoMymagic(context, "MYFAVORITE");
			}
		});
		btnFavorite.setId(ID.LogoView.FAVORITE_ID);
		RelativeLayout.LayoutParams param_favorite = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_favorite.addRule(RelativeLayout.LEFT_OF, ID.LogoView.HISTORY_ID);
		param_favorite.addRule(RelativeLayout.CENTER_VERTICAL);
		param_favorite.rightMargin = MARGIN;
		btnFavorite.setLayoutParams(param_favorite);
		btnFavorite
				.setBackgroundResource(R.drawable.cs_logoview_favorite_selector);
		addView(btnFavorite);

		btnHistory = new Button(context);
		btnHistory.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoMymagic(context, "MYHISTORY");
			}
		});
		btnHistory.setId(ID.LogoView.HISTORY_ID);
		RelativeLayout.LayoutParams param_history = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_history.addRule(RelativeLayout.LEFT_OF, ID.LogoView.RECHARGE_ID);
		param_history.addRule(RelativeLayout.CENTER_VERTICAL);
		param_history.rightMargin = MARGIN;
		btnHistory.setLayoutParams(param_history);
		btnHistory
				.setBackgroundResource(R.drawable.cs_logoview_history_selector);
		addView(btnHistory);

		btnRecharge = new Button(context);
		btnRecharge.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				gotoMymagic(context, "CONSUMERCENTER", 0);
			}
		});
		btnRecharge.setId(ID.LogoView.RECHARGE_ID);
		RelativeLayout.LayoutParams param_recharge = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_recharge.addRule(RelativeLayout.LEFT_OF, ID.LogoView.ACCOUNT_ID);
		param_recharge.addRule(RelativeLayout.CENTER_VERTICAL);
		param_recharge.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(40);
		param_recharge.rightMargin = MARGIN;
		btnRecharge.setLayoutParams(param_recharge);
		btnRecharge
				.setBackgroundResource(R.drawable.cs_logoview_recharge_selector);
		addView(btnRecharge);

		btnAccount = new Button(context);
		btnAccount.setId(ID.LogoView.ACCOUNT_ID);
		btnAccount.setNextFocusRightId(ID.LogoView.SEARCH_ID);
		btnAccount.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				gotoMymagic(context, "CONSUMERCENTER", 0);
			}
		});
		RelativeLayout.LayoutParams param_account = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_account.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		param_account.addRule(RelativeLayout.CENTER_VERTICAL);
		param_account.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(40);
		btnAccount.setLayoutParams(param_account);
		btnAccount
				.setBackgroundResource(R.drawable.cs_logoview_account_selector);
		addView(btnAccount);
	}

	private void gotoMymagic(Context context, String where) {
		Intent intent = new Intent();
		intent.setAction("com.voole.epg.action.Mymagic");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("toWhere", where);
		context.startActivity(intent);
	}

	private void gotoMymagic(Context context, String where, int index) {
		Intent intent = new Intent();
		intent.setAction("com.voole.epg.action.Mymagic");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("toWhere", where);
		intent.putExtra("index", index);
		context.startActivity(intent);
	}

	private void gotoSearch(Context context) {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction("com.voole.epg.action.Search");
		context.startActivity(intent);
	}

	public void setChannelName(String name, boolean isShowFind) {
		logo.setVisibility(View.GONE);
		channel.setVisibility(View.VISIBLE);
		channel.setText(name);
		if (isShowFind == true) {
			param_channel = new RelativeLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			line.setVisibility(View.VISIBLE);
			find.setVisibility(View.VISIBLE);
			category.setVisibility(View.VISIBLE);
			btnAccount.setNextFocusRightId(ID.LogoView.FIND_ID);
			btnSearch.setNextFocusLeftId(ID.LogoView.FIND_ID);
		} else {
			param_channel = new RelativeLayout.LayoutParams(DisplayManager
					.GetInstance().getScreenWidth() * 5 / 12,
					LayoutParams.WRAP_CONTENT);
			line.setVisibility(View.GONE);
			find.setVisibility(View.GONE);
			category.setVisibility(View.GONE);
			btnAccount.setNextFocusRightId(ID.LogoView.SEARCH_ID);
		}
		param_channel.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		param_channel.addRule(RelativeLayout.CENTER_VERTICAL);
		param_channel.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(20);
		channel.setLayoutParams(param_channel);
	}

	public void hideSearchLabel() {
		btnSearch.setVisibility(INVISIBLE);
		btnAccount.setNextFocusRightId(ID.LogoView.FAVORITE_ID);
		btnFavorite.setNextFocusLeftId(ID.LogoView.ACCOUNT_ID);
	}

	public void setCategory(String categoryStr) {
		category.setText(categoryStr);
	}

	/**
	 * 自定义分类对话框的布局
	 * 
	 * @author Administrator
	 * 
	 */
	public class FindCatView extends BaseLinearLayout {

		private FindView area = null;
		private FindView type = null;
		private FindView year = null;
		private TextView text = null;

		private ShadeButton ok_btn = null;
		private ShadeButton reset_btn = null;
		private ShadeButton cancel_btn = null;

		public FindCatView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public FindCatView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public FindCatView(Context context) {
			super(context);
			init(context);
		}

		private void init(final Context context) {
			/*
			 * 最外层是个垂直线性布局，分类那块是垂直线性包含三个水平线性
			 */
			setOrientation(VERTICAL);
			setGravity(Gravity.CENTER_VERTICAL);
			setBackgroundResource(R.drawable.cs_navigation_goto_bg);

			text = new TextView(context);
			text.setTextColor(Color.WHITE);
			text.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DisplayManager
					.GetInstance().changeTextSize(24));

			LinearLayout.LayoutParams params_text = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT, 5);
			params_text.topMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			text.setLayoutParams(params_text);
			text.setGravity(Gravity.CENTER);

			addView(text);// 加入上部文本

			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT, 5);
			params.topMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			params.leftMargin = DisplayManager.GetInstance().changeHeightSize(
					50);
			params.rightMargin = DisplayManager.GetInstance().changeHeightSize(
					50);

			LinearLayout.LayoutParams param_btn = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT, 4);
			param_btn.topMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			param_btn.bottomMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			/**
			 * 三个分类加入到布局里
			 */
			area = new FindView(context);
			area.setLayoutParams(params);
			area.setDisplay();
			addView(area);

			type = new FindView(context);
			type.setLayoutParams(params);
			type.setDisplay();
			addView(type);

			year = new FindView(context);
			year.setLayoutParams(params);
			year.setDisplay();
			addView(year);

			LinearLayout oper_layout = new LinearLayout(context);

			oper_layout.setOrientation(HORIZONTAL);
			oper_layout.setGravity(Gravity.CENTER);
			oper_layout.setLayoutParams(param_btn);

			int TextSize = DisplayManager.GetInstance().changeTextSize(22);

			reset_btn = new ShadeButton(context);
			reset_btn.setText(context.getString(R.string.common_reset_button));
			reset_btn.setTextSize(TextSize);
			reset_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					type.resetDisplay();
					area.resetDisplay();
					year.resetDisplay();
				}
			});
			oper_layout.addView(reset_btn);

			ok_btn = new ShadeButton(context);
			ok_btn.setText(context.getString(R.string.common_ok_button));
			ok_btn.setTextSize(TextSize);
			ok_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					findDialog.dismiss();
					if (logoViewListener != null) {

						// Toast.makeText(context,
						// type.getContent()+","+year.getContent(),
						// 3000).show();

						logoViewListener.onStartFinding(type.getContent(),
								year.getContent(), area.getContent());
					}
				}
			});
			oper_layout.addView(ok_btn);

			cancel_btn = new ShadeButton(context);
			cancel_btn
					.setText(context.getString(R.string.common_cancel_button));
			cancel_btn.setTextSize(TextSize);
			cancel_btn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					findDialog.dismiss();
				}
			});
			oper_layout.addView(cancel_btn);
			addView(oper_layout);

		}

		public void setData(String name, Label typeLabel, List<Label> list) {
			text.setText("您可以根据您选定的条件，在" + name + "频道内查找相应的影片");
			type.setData("类型：", typeLabel);
			area.setData("地区：", list.get(0));
			year.setData("时间：", list.get(1));
		}
	}
}
