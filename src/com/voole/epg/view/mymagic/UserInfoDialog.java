package com.voole.epg.view.mymagic;

import java.util.Timer;
import java.util.TimerTask;

import com.voole.epg.R;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.MyEditText;
import com.voole.epg.base.common.ShadeButton;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserInfoDialog extends Dialog {

	private Context mContext = null;
	private TextView label_tv = null;
	private EditText input_et = null;
	private TextView hint_tv = null;
	private LinearLayout code_layout = null;
	private EditText code_et = null;
	private TextView getCode_tv = null;
	private ShadeButton ok_btn = null;
	private ShadeButton cancel_btn = null;

	private LayoutInflater inflater = null;
	private View mainView = null;

	public static final int NAME = 0;
	public static final int EMAIL = 1;
	public static final int PHONE = 2;
	public static final int PASSWORD = 3;

	private int screenWidth = 0;
	private int screenHeight = 0;

	private int code = 0;
	
	private Timer countDownTimer=null;
	private TimerTask countDownTask=null;
	
	private int countDownTime = 60;
	

	private OnOkClickListener onOkClickListener = null;

	private OnCancelClickListener onCancelClickListener = null;
	
	private OnCodeClickListener onCodeClickListener=null;

	public void setOnOkClickListener(OnOkClickListener onOkClickListener) {
		this.onOkClickListener = onOkClickListener;
	}

	public void setOnCancelClickListener(
			OnCancelClickListener onCancelClickListener) {
		this.onCancelClickListener = onCancelClickListener;
	}
	

	public void setOnCodeClickListener(OnCodeClickListener onCodeClickListener) {
		this.onCodeClickListener = onCodeClickListener;
	}



	public interface OnOkClickListener {
		public void onClick(String content);
		public void onClick(String content,String code);
		
	}

	public interface OnCodeClickListener{
		public void onClick(String mobile);
	}
	
	public interface OnCancelClickListener {
		public void onClick();
	}

	public void setPosition(int leftWidth) {
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = lp.x + leftWidth / 4;
		dialogWindow.setAttributes(lp);
	}

	public void setCode(int code) {
		this.code = code;
	}

	private void init() {
		screenHeight = DisplayManager.GetInstance().getScreenHeight();
		screenWidth = DisplayManager.GetInstance().getScreenWidth();
	}

	private void setContent(int code) {
		switch (code) {
		case NAME:
			label_tv.setText("用  户  名 : ");
			code_layout.setVisibility(View.GONE);
			hint_tv.setText("5-20 位英文字母或数字，不区分大小写");
			hint_tv.setVisibility(View.VISIBLE);
			break;
		case EMAIL:
			label_tv.setText("邮箱地址 : ");
			code_layout.setVisibility(View.GONE);
			hint_tv.setVisibility(View.VISIBLE);
			hint_tv.setText("请输入您真实有效的邮箱");
			break;
		case PHONE:
			label_tv.setText("手  机  号 : ");
			code_layout.setVisibility(View.VISIBLE);
			hint_tv.setVisibility(View.GONE);
			hint_tv.setText("请输入有效手机号码");
			input_et.setInputType(PHONE);
			break;
		default:
			break;
		}
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
	
	Handler codeHanlder = new Handler() {

		public void handleMessage(Message msg) {
			int time = msg.what;
			if (time == 0) {
				countDownTime = 60;
				stopCountDownTimer();
				ClickEnable(time);
			} else {
				ClickEnable(time);
			}
		};

	};
	
	private void startCountDownTimer() {
		countDownTimer = new Timer();
		countDownTask = new TimerTask() {

			@Override
			public void run() {
				Message msg = codeHanlder.obtainMessage();
				msg.what = countDownTime;
				codeHanlder.sendMessage(msg);
				countDownTime--;
			}
		};
	}

	public void startCountDown(){
		startCountDownTimer();
		ClickDisable();
		countDownTimer.schedule(countDownTask, 1000, 1000);
	}
	
	public void ClickDisable() {
		getCode_tv.setText(mContext
				.getString(R.string.mymagic_um_register_phone_disable));
		getCode_tv.setClickable(false);
	}
	
	private void stopCountDownTimer() {
		if (countDownTimer != null) {
			countDownTimer.cancel();
			countDownTimer = null;
		}
		if (countDownTask != null) {
			countDownTask.cancel();
			countDownTask = null;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		inflater = LayoutInflater.from(mContext);
		mainView = inflater.inflate(R.layout.cs_magic_um_userinfo, null);

		label_tv = (TextView) mainView.findViewById(R.id.label_tv);
		input_et = (MyEditText) mainView.findViewById(R.id.input_et);
		hint_tv = (TextView) mainView.findViewById(R.id.hint_tv);
		code_layout = (LinearLayout) mainView.findViewById(R.id.code_layout);

		code_et = (MyEditText) mainView.findViewById(R.id.code_et);
		getCode_tv = (TextView) mainView.findViewById(R.id.getCode);
		getCode_tv.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 if(onCodeClickListener!=null){
					 onCodeClickListener.onClick(input_et.getText().toString());
				 }
			}
		});

		ok_btn = (ShadeButton) mainView.findViewById(R.id.ok);
		ok_btn.setText("确定");
		ok_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (onOkClickListener != null) {
					if(code==PHONE){
						onOkClickListener.onClick(input_et.getText().toString(),code_et.getText().toString());
					}else{
						onOkClickListener.onClick(input_et.getText().toString());	
					}
					
				}
			}
		});

		cancel_btn = (ShadeButton) mainView.findViewById(R.id.cancel);
		cancel_btn.setText("取消");
		cancel_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (onCancelClickListener != null) {
					onCancelClickListener.onClick();
				}
			}
		});
		setContent(code);
		setContentView(mainView, new ViewGroup.LayoutParams(
				screenWidth * 2 / 5, screenHeight * 2 / 5));
	}

	public UserInfoDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		init();
		mContext = context;
	}

	public UserInfoDialog(Context context, int theme) {
		super(context, theme);
		init();
		mContext = context;
	}

	public UserInfoDialog(Context context) {
		super(context);
		init();
		mContext = context;
	}

}
