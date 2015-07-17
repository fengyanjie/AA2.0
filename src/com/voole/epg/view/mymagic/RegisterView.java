package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.MyEditText;
import com.voole.epg.base.common.ShadeButton;

public class RegisterView extends BaseLinearLayout {

	private LayoutInflater inflater;
	private View view;
	private TextView getCode_tv;
	private ImageView agree_iv;
	private TextView text_tv;
	private TextView click_tv;
	private EditText name_tv;
	private EditText email_tv;
	private EditText pwd_tv;
	private EditText compwd_tv;
	private EditText phone_tv;
	private EditText code_tv;
	private ShadeButton ok_btn;
	private Context mContext;

	private RegisterViewListener registerViewListener = null;

	public interface RegisterViewListener {
		public void onFinishClick(String account, String email,
				String password, String compwd, String mobile, String authCode);

		public void getAuthCode(String mobile);

		public void onAgreementClick();
	}

	public void setRegisterViewListener(
			RegisterViewListener registerViewListener) {
		this.registerViewListener = registerViewListener;
	}

	public RegisterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init(context);
	}

	public RegisterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init(context);
	}

	public RegisterView(Context context) {
		super(context);
		mContext = context;
		init(context);
	}

	private void init(Context context) {
		LinearLayout.LayoutParams param_main_layout = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setLayoutParams(param_main_layout);

		inflater = LayoutInflater.from(context);

		LinearLayout layout = new LinearLayout(context);

		layout.setOrientation(VERTICAL);
		LinearLayout.LayoutParams param_layout = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_layout.leftMargin = DisplayManager.GetInstance().changeWidthSize(
				100);
		param_layout.topMargin = DisplayManager.GetInstance().changeWidthSize(
				20);
		layout.setLayoutParams(param_layout);

		view = inflater.inflate(R.layout.cs_mymagic_um_register, null);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2);
		view.setLayoutParams(params);
		layout.addView(view);

		getCode_tv = (TextView) view.findViewById(R.id.getCode);
		getCode_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (registerViewListener != null) {
					String mobile =phone_tv.getText().toString();
					registerViewListener.getAuthCode(mobile);
				}
			}
		});
		name_tv = (MyEditText) view.findViewById(R.id.name_et);
		name_tv.setId(ID.MyMagicActivity.UM_REGISTER_VIEW_NAME_ID);
		code_tv = (MyEditText) view.findViewById(R.id.code_et);
		code_tv.setNextFocusDownId(ID.MyMagicActivity.UM_REGISTER_VIEW_FINISH_BTN);

		email_tv = (MyEditText) view.findViewById(R.id.email_et);
		pwd_tv = (MyEditText) view.findViewById(R.id.pwd_et);
		pwd_tv.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		compwd_tv = (MyEditText) view.findViewById(R.id.compwd_et);
		compwd_tv.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
		phone_tv = (MyEditText) view.findViewById(R.id.phone_et);

		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setGravity(Gravity.CENTER_VERTICAL);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams param_linearlayout = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 6);
		linearLayout.setLayoutParams(param_linearlayout);
		agree_iv = new ImageView(context);
		agree_iv.setImageResource(R.drawable.agree_iv);
		LinearLayout.LayoutParams param_agree_btn = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		agree_iv.setLayoutParams(param_agree_btn);
		linearLayout.addView(agree_iv);

		text_tv = new TextView(context);
		text_tv.setText("我已阅读并接受优朋影视的");
		text_tv.setTextColor(Color.WHITE);
		text_tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,DisplayManager.GetInstance().changeTextSize(20));
		LinearLayout.LayoutParams param_text_btn = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		text_tv.setLayoutParams(param_text_btn);
		linearLayout.addView(text_tv);

		click_tv = new TextView(context);
		click_tv.setText("注册协议");
		click_tv.setClickable(true);
		click_tv.setFocusable(true);
		click_tv.setSingleLine(true);
		click_tv.setFocusableInTouchMode(true);
		click_tv.setGravity(Gravity.CENTER);
		click_tv.setTextColor(Color.parseColor("#00c1ea"));
		click_tv.setTextSize(DisplayManager.GetInstance().changeTextSize(20));
		click_tv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);// 下划线
		click_tv.setBackgroundResource(R.drawable.cs_mymagic_getcode_selector);
		LinearLayout.LayoutParams param_click_btn = new LinearLayout.LayoutParams(
				DisplayManager.GetInstance().changeWidthSize(120),
				DisplayManager.GetInstance().changeHeightSize(50));
		click_tv.setLayoutParams(param_click_btn);
		click_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (registerViewListener != null) {
					registerViewListener.onAgreementClick();
				}

			}
		});
		linearLayout.addView(click_tv);

		ok_btn = new ShadeButton(context);
		ok_btn.setId(ID.MyMagicActivity.UM_REGISTER_VIEW_FINISH_BTN);
		ok_btn.setText("完成注册");
		ok_btn.setTextSize(DisplayManager.GetInstance().changeTextSize(22));
		LinearLayout.LayoutParams param_ok_btn = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		ok_btn.setLayoutParams(param_ok_btn);
		ok_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (registerViewListener != null) {
					String account = name_tv.getText().toString();
					String email = email_tv.getText().toString();
					String password = pwd_tv.getText().toString();
					String compwd = compwd_tv.getText().toString();
					String mobile = phone_tv.getText().toString();
					String code = code_tv.getText().toString();
					registerViewListener.onFinishClick(account, email,
							password, compwd, mobile, code);
				}

			}
		});
		linearLayout.addView(ok_btn);

		layout.addView(linearLayout);
		addView(layout);
	}

	public void ClickEnable(int time) {
		if (time == 0) {
			getCode_tv.setText(mContext
					.getString(R.string.mymagic_um_register_phone_tip));
			getCode_tv.setClickable(true);
		} else {
			getCode_tv.setText(time + "秒");
		}

	}

	public void ClickDisable() {
		getCode_tv.setText(mContext
				.getString(R.string.mymagic_um_register_phone_disable));
		getCode_tv.setClickable(false);
	}

}
