package com.voole.epg.upgrade;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;

import com.voole.epg.Config;
import com.voole.epg.LauncherApplication;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.tvutils.DeviceUtil;


public class UpgradeManager {
	private static UpgradeManager instance = null;

	private UpgradeManager() {

	}

	public static UpgradeManager GetInstance() {
		if (instance == null) {
			instance = new UpgradeManager();
		}
		return instance;
	}
	
	public String getOemID(Context context){
		InputStream is = null;
		try {
			is = LauncherApplication.GetInstance().getApplicationContext().getAssets().open(Config.AUTH_CONF);
		} catch (IOException e1) {
			e1.printStackTrace();
			return "";
		}
		BufferedReader br = new BufferedReader( new InputStreamReader(is) );
		String temp;
		try {
			while ( (temp = br.readLine()) != null ) {
				if(temp.contains("oemid")){
					String[] array = temp.split("=");
					return array[1].trim().replaceAll("\"", "");
				}
			}
			is.close();
		} catch (IOException e) {
			LogUtil.d("getOemID exception-->" + e.toString());
		}
		return "";
	}
	
	public void stopAndDeleteFiles(){
		ProxyManager.GetInstance().exitProxy();
    	AuthManager.GetInstance().exitAuth();
    	ProxyManager.GetInstance().deleteProxyFiles();
    	AuthManager.GetInstance().deleteAuthFiles();
	}
	
	public void install(Context context, File apkFile){
		DeviceUtil.installApk(context, apkFile);
	}
}
