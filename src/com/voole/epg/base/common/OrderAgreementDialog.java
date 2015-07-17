package com.voole.epg.base.common;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.corelib.model.account.AccountManager;
import com.voole.epg.corelib.model.account.OrderListInfo;
import com.voole.epg.corelib.model.account.Product;

public class OrderAgreementDialog extends Dialog {

	private Context mContext = null;

	private TextView content_tv = null;
	private TextView title_tv = null;
	private TextView user_tv = null;
	private TextView server_tv = null;
	private TextView resume_tv = null;
	private ShadeButton cancelBtn = null;
	private ShadeButton okBtn = null;
	private View view = null;

	private int screenWidth = 0;
	private int screenHeight = 0;

	private OnOrderButtonClickListener listener = null;

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

	public void setTitle(String title) {
		title_tv.setText(title);
		getOrder();
	}

	public void setContent(String content) {
		content_tv.setText(content);
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

	public void setListener(OnOrderButtonClickListener listener) {
		this.listener = listener;
	}

	public interface OnOrderButtonClickListener {
		public void onClickOk();

		public void onClickCancel();
	}

	public OrderAgreementDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		init();
		mContext = context;
	}

	public OrderAgreementDialog(Context context, int theme) {
		super(context, theme);
		init();
		mContext = context;
	}

	public OrderAgreementDialog(Context context) {
		super(context);
		init();
		mContext = context;
	}

	private void init() {
		screenHeight = DisplayManager.GetInstance().getScreenHeight();
		screenWidth = DisplayManager.GetInstance().getScreenWidth();
	}

	public void setUserInfo(OrderListInfo orderListInfo, Product product) {
		if (orderListInfo != null) {
			String info = "";
			info = "用户ID：" + orderListInfo.getUid();
			if (orderListInfo.getBalance() != null) {
				info += "    余额：￥"
						+ Integer.parseInt(orderListInfo.getBalance()) / 100;
			} else {
				info += "    余额：";
			}
			user_tv.setText(info);
			String serverType="     订购服务："+product.getName();
			String serverPrice="    价格：￥"+Integer.parseInt(product.getCostFee())/100;
			server_tv.setText(serverType+serverPrice);
		}

	}

	public void showResume(){
		resume_tv.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		view = LayoutInflater.from(mContext).inflate(
				R.layout.cs_mymagic_um_order_agreement_dialog, null);

		setContentView(view, new ViewGroup.LayoutParams(screenWidth * 4 / 5,
				screenHeight * 4 / 5));

		title_tv = (TextView) view.findViewById(R.id.title);
		user_tv = (TextView) view.findViewById(R.id.userInfo);
		server_tv = (TextView) view.findViewById(R.id.server);
		content_tv = (TextView) view.findViewById(R.id.content);
		resume_tv = (TextView) view.findViewById(R.id.resumeTV);
		resume_tv.setVisibility(View.GONE);

		okBtn = (ShadeButton) view.findViewById(R.id.ok);
		okBtn.setText("确定");
		okBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClickOk();
				}
				OrderAgreementDialog.this.dismiss();
			}

		});
		cancelBtn = (ShadeButton) view.findViewById(R.id.cancel);
		cancelBtn.setText("返回");
		cancelBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (listener != null) {
					listener.onClickCancel();
				}
				OrderAgreementDialog.this.dismiss();
			}
		});

	}
}
