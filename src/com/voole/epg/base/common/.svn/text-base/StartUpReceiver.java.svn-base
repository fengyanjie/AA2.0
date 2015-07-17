package com.voole.epg.base.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.voole.epg.Config;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.corelib.model.utils.LogUtil;

public class StartUpReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		LogUtil.d("StartUpReceiver--->onReceive");
		if ("1".equals(Config.GetInstance().getBootStartAuth())) {
			startAuthAndProxy();
		}
	}
	
	private void startAuthAndProxy(){
		new Thread(){
			public void run() {
				AuthManager.GetInstance().startAuth();
				ProxyManager.GetInstance().startProxy();
			};
		}.start();
	}
}
