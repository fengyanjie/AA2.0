package com.voole.epg;

import android.app.Activity;
import android.app.Application;

import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.download.FilmDetailDialog.DetailDialogf4kListener;
import com.voole.epg.model.play.PlayManager;


public class LauncherApplication extends Application{
	private static LauncherApplication instance; 
	public LauncherApplication() {
		instance = this;
	}

	public static LauncherApplication GetInstance() {
		return instance;
	}
	
	public String getFilePath(){
		return getApplicationContext().getFilesDir().getAbsolutePath();
	}

	@Override
	public void onCreate() {
		LogUtil.d("LauncherApplication-->onCreate");
		InitManager.GetInstance().init();
//		COM.VOOLE.EPG.F4K_DOWNLOAD.DETAILDIALOGF4K.SETLISTENER(NEW DETAILDIALOGF4KLISTENER<STRING>() {
//			@OVERRIDE
//			PUBLIC VOID ONPLAY(ACTIVITY ACTIVITY,STRING MID) {
//				 PLAYMANAGER.GETINSTANCE().PLAY(ACTIVITY, MID);
//			}
//		});
		com.voole.epg.download.FilmDetailDialog.setListener(new DetailDialogf4kListener<String>() {
			@Override
			public void onPlay(Activity activity,String mid) {
				 PlayManager.GetInstance().play(activity, mid);
			}
		});
		/*SharedPreferences voolesharedPreferences = this.getSharedPreferences(Config.VOOLE_SHARE, Context.MODE_PRIVATE);
		String versionCodeInShare = voolesharedPreferences.getString("versionCode", "");
		String versionCodeInProp = PropertiesUtil.getProperty(getApplicationContext(), "versionCode");
		if (!versionCodeInShare.equalsIgnoreCase(versionCodeInProp)) {
			Editor edit = voolesharedPreferences.edit();
			edit.putString("versionCode", versionCodeInProp);
			edit.commit();
//			AuthManager.GetInstance().deleteAuthFiles();
		}*/
	}
	
	@Override
	public void onTerminate() {
		LogUtil.d("LauncherApplication-->onTerminate");
	}
}
