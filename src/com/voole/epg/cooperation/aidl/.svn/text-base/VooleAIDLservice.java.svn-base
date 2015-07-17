package com.voole.epg.cooperation.aidl;

import java.util.HashMap;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.voole.epg.corelib.model.account.AccountManager;
import com.voole.epg.corelib.model.account.MessageInfoResult;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.auth.User;
import com.voole.epg.corelib.model.play.Ad;
import com.voole.epg.corelib.model.play.BasePlayManager;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.corelib.model.transscreen.TransScreenManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.model.play.PlayManager;
import com.voole.util.prop.PropertiesUtil;


public class VooleAIDLservice extends Service {
	private MyBinder myBinder = new MyBinder();
	
	@Override
	public void onCreate() {
		Log.d("TestAIDLservice", "onCreate---");
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d("TestAIDLservice", "onBind---");
		return myBinder;
	}
	
	private final class MyBinder extends VooleAIDL.Stub{
		@Override
		public VooleUser getVooleUser() throws RemoteException {
			Log.d("MyBinder", "getUser---->");
			VooleUser vooleUser = new VooleUser();
			User user = AuthManager.GetInstance().getUser();
			if (user != null) {
				vooleUser.setUid(user.getUid());
				vooleUser.setHid(user.getHid());
				vooleUser.setOemid(user.getOemid());
			}
			return vooleUser;
		}

		@Override
		public void startAuth() throws RemoteException {
			AuthManager.GetInstance().runAuth();
		}

		@Override
		public String getPortalUrl() throws RemoteException {
			User user = AuthManager.GetInstance().getUser();
			if (user != null) {
				String portal = user.getPortal();
				String uid = user.getUid();
				String oemid = user.getOemid();
				String hid = user.getHid();
				portal = portal + "?uid=" + uid + "&oemid=" + oemid + "&hid=" + hid + "&v=1.0";
				LogUtil.d("VooleAIDLservice-------getPortalUrl------>" + portal);
				return portal;
			}else {
				LogUtil.d("VooleAIDLservice-------getPortalUrl------>fail");
				return "";
			}
		}

		@Override
		public void startProxy() throws RemoteException {
			new Thread(new Runnable() {
				@Override
				public void run() {
					ProxyManager.GetInstance().startProxy();
				}
			}).start();
		}

		@Override
		public String getProviderID() throws RemoteException {
			String providerID = PropertiesUtil.getProperty(getApplicationContext(), "providerID");
			LogUtil.d("getProviderID---->" + providerID);
			if ("".equals(providerID)) {
				providerID = "0";
			}
			return providerID;
		}

		@Override
		public String getMessageCount() throws RemoteException {
			SharedPreferences sp= getSharedPreferences("localInfo", MODE_PRIVATE);
			String msgId=sp.getString("msgId", "");
			if("".equals(msgId)){
				return "";
			}else{
				MessageInfoResult result=AccountManager.GetInstance().getUnReadMessage(msgId);
				if(result==null){
					return "";
				}else{
					return result.getCount();
				}
			}
		}
		
		/**
		 * return 001 Net error
		 *        002 film unfinded 
		 *        003 product unfinded
		 *        004 orderInfo unfinded
		 */
		@Override
		public String getPlayUrl(String mid, String sid) throws RemoteException {
			// TODO Auto-generated method stub
			HashMap<String, Ad> mp = BasePlayManager.GetInstance().doGetAd(mid, sid);
			String status = "";
			Ad ad = null;
			if (mp.containsKey("001")) {
				status = "001";
			}else if(mp.containsKey("002")) {
				status = "002";
			}else if(mp.containsKey("003")) {
				status = "003";
			}else if(mp.containsKey("004")) {
				status = "004";
			}else if(mp.containsKey("005")) {
				status = "005";
			}
			if (!"005".equals(status)) {
				return status;
			}else {
				ad = mp.get(status);
				return BasePlayManager.GetInstance().doGetPlayUrl(ad);
			}
			
			/*Ad ad = BasePlayManager.GetInstance().doGetAd(mid, sid);
			return BasePlayManager.GetInstance().doGetPlayUrl(ad);*/
		}
		
		
		/**
		 * return 001 Net error
		 *        002 film unfinded 
		 *        003 product unfinded
		 *        004 orderInfo unfinded
		 */       
		@Override
		public String formatAuthPlayUrl(String mid, String sid) throws RemoteException {
			return BasePlayManager.GetInstance().formatAuthPlayUrlForDeskTop(mid, sid);
		}

		@Override
		public String getAddResumeUrl(String mid, String sid, int playtime, String title, String imgUrl) throws RemoteException {
			return TransScreenManager.GetInstance().getBaseUrl() + "&action=DoPlayRecord&operation=CreatePlayRecord&mid=" + mid
					+ "&sid=" + sid + "&playtime=" + playtime + "&title=" + title + "&imgurl=" + imgUrl;
//			return null;
		}

		@Override
		public String getResumeInfo(String mid, String sid)
				throws RemoteException {
			return TransScreenManager.GetInstance().getBaseUrl() + "&action=DoResume&operation=PlayResumeInfo&mid=" + mid + "&sid=" + sid;
//			return null;
		}
	}
	
}
