package com.voole.epg.cooperation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.voole.epg.corelib.model.utils.LogUtil;

public class ControllerReceiver extends BroadcastReceiver{
	

	@Override
	public void onReceive(Context context, Intent intent) {
		String data = intent.getStringExtra("data");
		LogUtil.d("ControllerReceiver-->data-->" + data);
		ControllerParser controllerParser = new ControllerParser(data);
		controllerParser.parser();
		String message = controllerParser.getMessage();
		LogUtil.d("ControllerReceiver-->message-->" + message);
		ControllerParser controllerParser_type = new ControllerParser(message);
		controllerParser_type.parser();
		String type = controllerParser_type.getType();
		LogUtil.d("ControllerReceiver-->type-->" + type);
		if("sendContent2TV".equals(type)){
			/*if(context instanceof VoolePlayerActivity){
				Toast.makeText(context, "有视频正在播放，不能播放其它视频。", Toast.LENGTH_LONG).show();
				return;
			}*/
			String message_content = controllerParser_type.getMessage();
			ControllerParser controllerParser_content = new ControllerParser(message_content);
			controllerParser_content.parser();
			String mid = controllerParser_content.getMid();
			String sid = controllerParser_content.getSid();
			LogUtil.d("ControllerReceiver-->mid-->" + mid + "-->sid-->" + sid  );
			if(mid != null && sid != null){
				Intent i = new Intent();
				i.setClass(context, CooperationStartActivity.class);
				i.putExtra("mid", mid);
				i.putExtra("sid", sid);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(i);
			}
		}
	}

}
