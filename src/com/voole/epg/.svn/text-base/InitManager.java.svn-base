package com.voole.epg;

import com.voole.epg.cooperation.StandardCooperation;
import com.voole.epg.cooperation.haier.Haier;
import com.voole.epg.cooperation.hisense.Hisense;
import com.voole.epg.cooperation.tcl.TclDesktop;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.auth.AuthManager.AuthManagerListener;
import com.voole.epg.corelib.model.cooperation.CooperationManager;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.model.auth.StandardAuth;
import com.voole.epg.model.play.PlayManager;
import com.voole.epg.model.play.ProxyPlay;
import com.voole.epg.model.proxy.StandardProxy;
import com.voole.tvutils.ImageManager;

public class InitManager {
	private static InitManager instance = null;

	private InitManager() {

	}

	public static InitManager GetInstance() {
		if (instance == null) {
			instance = new InitManager();
		}
		return instance;
	}
	
	public void init(){
		initAuth();
		initPlay();
		initProxy();
		initCooperation();
		initImageManager();
	}
	
	private void initAuth(){
		if (AuthManager.GetInstance().getAuth()==null) {
			AuthManager.GetInstance().init(new StandardAuth());
			AuthManager.GetInstance().setAuthManagerListener(new AuthManagerListener() {
				@Override
				public String getInterfaceVersionCode() {
					 return Config.GetInstance().getInterfaceVersion();
				}
			});
		}
	}
	
	private void initPlay(){
		PlayManager.GetInstance().init(new ProxyPlay());
	}
	
	private void initProxy(){
		if (ProxyManager.GetInstance().getProxy()==null) {
			ProxyManager.GetInstance().init(new StandardProxy());
		}
	}
	
	private void initCooperation(){
		switch (Config.OemType.getOemType(Config.GetInstance().getOemType())) {
			case Hisense:
				CooperationManager.GetInstance().setCooperation(new Hisense());
				break;
			case TCL_Desktop:
				CooperationManager.GetInstance().setCooperation(new TclDesktop());
				break;
			case Haier:
				CooperationManager.GetInstance().setCooperation(new Haier());
				break;
			case TCL_4K:
			default:
				CooperationManager.GetInstance().setCooperation(new StandardCooperation());
				break;
		}
	}
	
	private void initImageManager(){
		ImageManager.GetInstance().init(LauncherApplication.GetInstance().getApplicationContext());
	}
}
