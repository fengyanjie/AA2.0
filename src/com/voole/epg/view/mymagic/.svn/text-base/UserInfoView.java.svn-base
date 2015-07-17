package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.ShadeButton;

public class UserInfoView extends BaseLinearLayout {

	private TextView name_label = null;
	private TextView email_label = null;
	private TextView phone_label = null;

	private TextView name_show = null;
	private TextView email_show = null;
	private TextView phone_show = null;

	private ShadeButton name_change = null;
	private ShadeButton email_change = null;
	private ShadeButton phone_change = null;
	
	private int TEXT_SIZE=DisplayManager.GetInstance().changeTextSize(26);
	private final int BTN_TEXT_SIZE = DisplayManager.GetInstance()
			.changeTextSize(24);
	
	
	public void setData(String account,String email,String mobile){
		name_show.setText(account);
		email_show.setText(email);
		phone_show.setText(mobile);
	}
	
	public void updateName(String account){
		name_show.setText(account);
	}
	
	public void updateEmail(String email){
		email_show.setText(email);
	}
	
	public void updateMobile(String mobile){
		phone_show.setText(mobile);
	}

	

	public UserInfoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public UserInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public UserInfoView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {

		setOrientation(VERTICAL);

		LinearLayout main_layout = new LinearLayout(context);
		main_layout.setOrientation(VERTICAL);
		LinearLayout.LayoutParams main_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		main_params.topMargin = 60;
		main_params.leftMargin = 70;
		main_params.rightMargin = 15;
		main_layout.setLayoutParams(main_params);

		LinearLayout.LayoutParams name_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams label_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams show_params = new LinearLayout.LayoutParams(
				DisplayManager.GetInstance().changeWidthSize(300),
				LinearLayout.LayoutParams.WRAP_CONTENT);
		LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		LinearLayout name_layout = new LinearLayout(context);
		name_layout.setLayoutParams(name_params);
		name_layout.setOrientation(HORIZONTAL);
		name_layout.setGravity(Gravity.CENTER_VERTICAL);

		name_label = new TextView(context);
		name_label.setText("用  户  名 ：");
		name_label.setTextColor(Color.WHITE);
		name_label.setGravity(Gravity.CENTER_VERTICAL);
		name_label.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
		name_label.setLayoutParams(label_params);
		name_layout.addView(name_label);
		name_show = new TextView(context);
		name_show.setSingleLine(true);
		name_show.setTextColor(Color.WHITE);
		name_show.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
		name_show.setLayoutParams(show_params);
		name_layout.addView(name_show);
		name_change = new ShadeButton(context);
		name_change.setVisibility(INVISIBLE);
		name_change.setText("修改");
		name_change.setTextSize(BTN_TEXT_SIZE);
		name_change.setLayoutParams(btn_params);
		name_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.nameChangeOnClick();
				}

			}
		});
		name_layout.addView(name_change);
		main_layout.addView(name_layout);

		LinearLayout email_layout = new LinearLayout(context);
		email_layout.setLayoutParams(name_params);
		email_layout.setOrientation(HORIZONTAL);
		email_layout.setGravity(Gravity.CENTER_VERTICAL);

		email_label = new TextView(context);
		email_label.setText("邮箱地址 ：");
		email_label.setTextColor(Color.WHITE);
		email_label.setGravity(Gravity.CENTER_VERTICAL);
		email_label.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
		email_label.setLayoutParams(label_params);
		email_layout.addView(email_label);
		email_show = new TextView(context);
		email_show.setSingleLine(true);
		email_show.setTextColor(Color.WHITE);
		email_show.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
		email_show.setLayoutParams(show_params);
		email_layout.addView(email_show);
		email_change = new ShadeButton(context);
		email_change.setId(ID.MyMagicActivity.UM_USERINFO_EMAIL_CHANGE_BTN_ID);
		email_change.setNextFocusLeftId(ID.MyMagicActivity.UM_LEFT_VIEW_NAVIGATION_ID);
		email_change.setText("修改");
		email_change.setTextSize(BTN_TEXT_SIZE);
		email_change.setLayoutParams(btn_params);
		email_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.emailChangeOnClick();
				}

			}
		});
		email_layout.addView(email_change);
		main_layout.addView(email_layout);

		LinearLayout phone_layout = new LinearLayout(context);
		phone_layout.setLayoutParams(name_params);
		phone_layout.setOrientation(HORIZONTAL);
		phone_layout.setGravity(Gravity.CENTER_VERTICAL);

		phone_label = new TextView(context);
		phone_label.setText("手  机  号 ：");
		phone_label.setTextColor(Color.WHITE);
		phone_label.setGravity(Gravity.CENTER_VERTICAL);
		phone_label.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
		phone_label.setLayoutParams(label_params);
		phone_layout.addView(phone_label);
		phone_show = new TextView(context);
		phone_show.setTextColor(Color.WHITE);
		phone_show.setSingleLine(true);
		phone_show.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
		phone_show.setLayoutParams(show_params);
		phone_layout.addView(phone_show);
		phone_change = new ShadeButton(context);
		phone_change.setText("修改");
		phone_change.setTextSize(BTN_TEXT_SIZE);
		phone_change.setLayoutParams(btn_params);
		phone_change.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.phoneChangeOnClick();
				}

			}
		});
		phone_layout.addView(phone_change);
		main_layout.addView(phone_layout);

		addView(main_layout);
	}

	private UserInfoViewListener listener = null;

	public void setUserInfoViewListener(UserInfoViewListener listener) {
		this.listener = listener;
	}

 

}

