package com.voole.epg.base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.voole.epg.R;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.base.common.TVAlertDialog.Builder;
import com.voole.epg.base.common.TVProgressDialog;
import com.voole.epg.base.common.TVToast;
import com.voole.tvutils.NetUtil;

public abstract class BaseActivity extends Activity {
	private static final int TIMEOUT = 4000;
	protected static final int ACCESS_NET_FAIL = 0x10000001;
	protected static final int ACCESS_WWW_FAIL = 0x20000001;
	private TVProgressDialog progressDialog = null;

	protected Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			
			if (BaseActivity.this.isFinishing()) {
				return;
			}
			if (!checkNetWork()) {
				return;
			}
			if (msg.what == ACCESS_WWW_FAIL) {
				if (netErrorDialog==null||!netErrorDialog.isShowing()) {
					netErrorDialog = getNetErrorDialog();
					netErrorDialog.show();
				}
				return;
			}
			
			cancelDialog();
			if (msg.what == ACCESS_NET_FAIL) {
				new TVAlertDialog.Builder(BaseActivity.this)
						.setCancelable(false)
						.setTitle(R.string.common_access_net_fail)
						.setPositiveButton(R.string.common_ok_button,
								new DialogInterface.OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										BaseActivity.this.finish();
									}
								}).create().show();
			} else {
				doHandleMessage(msg.what, msg.obj);
//				Toast.makeText(getApplicationContext(), "ACCESS_WWW_FAIL-----------www is good", 0).show();
				if(netErrorDialog!=null&&netErrorDialog.isShowing()){
					netErrorDialog.cancel();
					netErrorDialog = null;
				}
			}
		}
	};
	private TVAlertDialog netErrorDialog;

	protected abstract void doHandleMessage(int what, Object obj);
	/**
	 * 测试互联网是否联通
	 * @param string
	 */
	protected void checkNetwork(final String string) {
		new Thread(){
			public void run() {
				HttpURLConnection conn = null;
				try {
					URL url = new URL(string);
					conn = (HttpURLConnection) url.openConnection();
					conn.setReadTimeout(TIMEOUT);
					conn.setConnectTimeout(TIMEOUT);
					int code =  conn.getResponseCode();
					Log.e(this.getClass().getName(),code+"         httpcode");
					if (code!=HttpURLConnection.HTTP_OK) {
						handler.sendEmptyMessage(ACCESS_WWW_FAIL);
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					handler.sendEmptyMessage(ACCESS_WWW_FAIL);
				}finally{
					if (conn!=null) {
						conn.disconnect();
					}
				}
			};
		}.start();
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e(this.getClass().getName(), "onCreate");

		// showMessage("完全自定义Toast完全自定义Toast完全自定义Toast完全自定义Toast完全自定义Toast完全自定义Toast");
	}

	protected void showDialog() {
		
		checkNetwork("http://www.baidu.com");
		
		if (progressDialog == null) {
			progressDialog = new TVProgressDialog(this);
			progressDialog.show();
		}
	}

	protected void cancelDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}

	protected void sendMessage(int what) {
		Message message = Message.obtain();
		message.what = what;
		handler.sendMessage(message);
	}
	
	protected void sendMessage(Message message){
		handler.sendMessage(message);
	}

	protected void showMessage(String msg) {
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.cs_message, null);
		TextView textView = (TextView) layout.findViewById(R.id.msg);
		textView.setText(msg);
		Toast toast = new Toast(this);
		toast.setGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL, 0, 0);
		toast.setDuration(Toast.LENGTH_LONG);
		toast.setView(layout);
		TVToast.show(toast, 5000);
	}

	@Override
	protected void onResume() {
		Log.e(this.getClass().getName(), "onResume");
		super.onResume();
	}

	@Override
	protected void onStop() {
		Log.e(this.getClass().getName(), "onStop");
		super.onStop();
	}

	
	@Override
	protected void onStart() {
		Log.e(this.getClass().getName(), "onStart");
		super.onStart();
	}
	
	
	@Override
	protected void onRestart() {
		Log.e(this.getClass().getName(), "onRestart");
		super.onRestart();
	}
	
	
	
	@Override
	protected void onDestroy() {
		Log.e(this.getClass().getName(), "onDestroy");
		super.onDestroy();
	}
	
	/**
	 * 检测内网环境是否联通
	 * @return
	 */
	private boolean checkNetWork(){
    	if(!NetUtil.isNetWorkAvailable(this)){
    		if (netErrorDialog!=null&&netErrorDialog.isShowing()) {
				return false;
			}else{
				netErrorDialog = getNetErrorDialog();
				netErrorDialog.show();
			}
    		return false;
    	}
    	return true;
    }

	private TVAlertDialog getNetErrorDialog() {
		Builder netErrorBuilder = new TVAlertDialog.Builder(BaseActivity.this);
		return netErrorBuilder.setCancelable(false)
		.setTitle(R.string.common_net_error)
		.setPositiveButton(R.string.common_ok_button,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						BaseActivity.this.finish();
					}
			}).create();
	}
	
}
