package com.voole.epg.cooperation.ali;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.voole.epg.cooperation.CooperationStartActivity;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.tvutils.DeviceUtil;
import com.yunos.tv.idc.service.IDCService;
import com.yunos.tv.idc.service.IDCStringPacket;

public class AliPlayerService extends Service {

	private final String TAG = "PlayerService";
//	public final String MODULE_NAME = "com.yunos.tv.moretv.player";
	private final String MODULE_NAME = "com.voole.epg";
	private IDCService mIdcService;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onStartCommand");
		mIdcService = new IDCService(this);
		int versionCode = DeviceUtil.getAppVersionCode(getApplicationContext());
		mIdcService.initIdcService(clientCallback, MODULE_NAME, versionCode);
		Toast.makeText(this, "start player services", Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}

	IDCService.ClientCallback clientCallback = new IDCService.ClientCallback() {

		@Override
		public void onClientCreate(int cid) {
			LogUtil.d("onClientCreate");
			mIdcService.sendStringPacket(0, "{\"port\":\"123456\"}");
		}

		@Override
		public void onClientDestroy(int cid) {
			LogUtil.d("onClientDestroy");
		}

		@Override
		public void onClientData(int cid, byte[] buf) {
			LogUtil.d("onClientData---cid--->" + cid);
			IDCStringPacket packet = new IDCStringPacket();
			try {
				packet.decode(buf);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			String content = packet.getStrConent();
			LogUtil.d("onClientData---->content-->" + content);
			AliPlayerContentParser parser = new AliPlayerContentParser(content);
			parser.parser();
			String mid = parser.getMid();
			String sid = parser.getSid();
			Intent intent = new Intent();
			intent.putExtra("mid", mid);
			intent.putExtra("sid", sid);
			intent.setClass(AliPlayerService.this, CooperationStartActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			AliPlayerService.this.startActivity(intent);
		}

	};

}
