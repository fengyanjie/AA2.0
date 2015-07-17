package com.voole.epg.base.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.corelib.model.account.AccountManager;

public class AgreementDialog extends Dialog {

	private Context mContext;
	private TextView content_tv;
	private TextView title_tv;
	private int screenWidth = 0;
	private int screenHeight = 0;

	public static final int TYPE_ORDER = 1;
	public static final int TYPE_REGISTER = 2;

	private String agreementContent = "";

	private TVProgressDialog progressDialog = null;

	protected void showDialog() {
		if (progressDialog == null) {
			progressDialog = new TVProgressDialog(mContext);
			progressDialog.show();
		}
	}

	protected void cancelDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				if ("".equals(agreementContent)) {
					setContent("暂时没有协议");
				} else {
					setContent(agreementContent);
				}
				cancelDialog();
				break;

			default:
				break;
			}
		};
	};

	public void setType(int type) {
		if (type == TYPE_ORDER) {
			getOrder();
		} else if (type == TYPE_REGISTER) {
			getRegister();
		}
	}

	private void getOrder() {
		showDialog();
		new Thread() {
			public void run() {
				agreementContent = AccountManager.GetInstance()
						.getOrderAgreementContent();
				handler.sendEmptyMessage(0);
			};
		}.start();
	}

	private void getRegister() {
		showDialog();
		new Thread() {
			public void run() {
				agreementContent = AccountManager.GetInstance()
						.getRegisterAgreementContent();
				handler.sendEmptyMessage(0);
			};
		}.start();
	}

	public AgreementDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		init();
		mContext = context;
	}

	public AgreementDialog(Context context, int theme) {
		super(context, theme);
		init();
		mContext = context;
	}

	public AgreementDialog(Context context) {
		super(context);
		init();
		mContext = context;
	}

	public void setPosition(int leftWidth) {
		Window dialogWindow = getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = lp.x + leftWidth / 4;
		lp.y = lp.y + DisplayManager.GetInstance().changeHeightSize(40);
		dialogWindow.setAttributes(lp);
	}

	public void setTitle(String title) {
		title_tv.setText(title);
	}

	public void setContent(String content) {
		content_tv.setText(content);
	}

	public void setOkButton(String string) {
	}

	public void setCancelButton(String string) {
	}

	private void init() {
		screenHeight = DisplayManager.GetInstance().getScreenHeight();
		screenWidth = DisplayManager.GetInstance().getScreenWidth();
	}

	public void setShowButton(boolean isShow) {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		View view = LayoutInflater.from(mContext).inflate(
				R.layout.cs_mymagic_um_agreement_dialog, null);

		setContentView(view, new ViewGroup.LayoutParams(screenWidth * 3 / 5,
				screenHeight * 3 / 5));

		content_tv = (TextView) findViewById(R.id.textview);
		content_tv.setMovementMethod(ScrollingMovementMethod.getInstance());
		content_tv.setScrollbarFadingEnabled(false);

		title_tv = (TextView) findViewById(R.id.title);
	}

}
