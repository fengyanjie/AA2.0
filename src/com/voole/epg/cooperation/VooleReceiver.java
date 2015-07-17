package com.voole.epg.cooperation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.TextUtils;

import com.voole.epg.corelib.model.account.AccountManager;
import com.voole.epg.corelib.model.account.MessageInfoResult;

public class VooleReceiver extends BroadcastReceiver {

	Context mContext;

	String count = "";

	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			Intent intent = new Intent();
			intent.setAction("com.voole.epg.action.broadcast.returnLetterNum");
			switch (msg.what) {
			case 0:
				intent.putExtra("letterNum", Integer.parseInt(count));
				break;
			case 1:
				intent.putExtra("letterNum",0);
				break;
			default:
				break;
			}
			mContext.sendBroadcast(intent);
		};

	};

	@Override
	public void onReceive(final Context context, Intent intent) {
		mContext = context;
		new Thread() {
			public void run() {
				SharedPreferences sp = mContext.getSharedPreferences(
						AccountManager.SP_NAME, mContext.MODE_PRIVATE);
				String msgId = sp.getString("msgId", "");
				if (!"".equals(msgId)) {
					MessageInfoResult result = AccountManager.GetInstance()
							.getUnReadMessage(msgId);
					if (result != null) {
						count = result.getCount();
						if (!TextUtils.isEmpty(count)) {
							handler.sendEmptyMessage(0);
						} else {
							handler.sendEmptyMessage(1);
						}
					}
				}
			};
		}.start();

	}
}
