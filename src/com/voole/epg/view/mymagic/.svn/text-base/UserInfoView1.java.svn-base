package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.voole.epg.base.BaseRelativeLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ShadeButton;

public class UserInfoView1 extends BaseRelativeLayout {

	private TextView name_label = null;
	private TextView email_label = null;
	private TextView phone_label = null;

	private TextView name_show = null;
	private TextView email_show = null;
	private TextView phone_show = null;

	private ShadeButton name_change = null;
	private ShadeButton email_change = null;
	private ShadeButton phone_change = null;

	private final int textSize = DisplayManager.GetInstance()
			.changeTextSize(24);

	public UserInfoView1(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public UserInfoView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public UserInfoView1(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		
		
		
		
		RelativeLayout main = new RelativeLayout(context);
		RelativeLayout.LayoutParams param_main = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		param_main.topMargin = 30;
		param_main.leftMargin = 15;
		param_main.rightMargin = 15;
		main.setLayoutParams(param_main);
		addView(main);

		TableLayout Layout = new TableLayout(context);
		TableLayout.LayoutParams layout_params = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.MATCH_PARENT);
		Layout.setLayoutParams(layout_params);
		main.addView(Layout);

		TableLayout.LayoutParams row_params = new TableLayout.LayoutParams(
				TableLayout.LayoutParams.MATCH_PARENT,
				TableLayout.LayoutParams.WRAP_CONTENT);

		TableRow row_name = new TableRow(context);
		row_name.setLayoutParams(row_params);
		name_label = new TextView(context);
		name_label.setText("用  户  名：");
		name_label.setTextColor(Color.WHITE);
		name_label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		row_name.addView(name_label);
		name_show = new TextView(context);
		name_show.setTextColor(Color.WHITE);
		name_show.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		name_show.setText("22");
		row_name.addView(name_show);
		name_change = new ShadeButton(context);
		name_change.setText("修改");
		name_change.setTextSize(textSize);
		row_name.addView(name_change);
		Layout.addView(row_name);

		TableRow row_email = new TableRow(context);
		row_email.setLayoutParams(row_params);
		email_label = new TextView(context);
		email_label.setText("邮箱地址：");
		email_label.setTextColor(Color.WHITE);
		email_label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		row_email.addView(email_label);
		email_show = new TextView(context);
		email_show.setTextColor(Color.WHITE);
		email_show.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		row_email.addView(email_show);
		email_change = new ShadeButton(context);
		email_change.setText("修改");
		email_change.setTextSize(textSize);
		row_email.addView(email_change);

		Layout.addView(row_email);

		TableRow row_phone = new TableRow(context);
		row_phone.setLayoutParams(row_params);
		phone_label = new TextView(context);
		phone_label.setText("手  机  号：");
		phone_label.setTextColor(Color.WHITE);
		phone_label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		row_phone.addView(phone_label);
		phone_show = new TextView(context);
		phone_show.setTextColor(Color.WHITE);
		phone_show.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
		row_phone.addView(phone_show);
		phone_change = new ShadeButton(context);
		phone_change.setText("修改");
		phone_change.setTextSize(textSize);
		row_phone.addView(phone_change);
		Layout.addView(row_phone);

	}

}
