package com.voole.epg.cooperation.tcl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.voole.epg.cooperation.CooperationStartActivity;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.movies.detail.MovieDetailActivity;

public class TCLRecevier extends BroadcastReceiver{
	private final String PLAY_VIDEO = "playvideo";
	private final String SHOW_DETAIL = "showvideodetail";
	private final String SEARCH = "search";
//	private final String VOICE_SEARCH = "voicesearch";
	/*private final String DEL_SINGLE = "com.tv.tothird.delsingle";
	private final String DEL_ALL = "com.tv.tothird.delall";
	private final String DEL_FAVORITE = "1";
	private final String DEL_HISTORY = "0";*/
	

	@Override
	public void onReceive(Context context, Intent intent) {
		LogUtil.d("TCLRecevier onReceive");
		if(intent != null && intent.getExtras() != null){
			String cmdString = intent.getExtras().getString("cmdstring");
			LogUtil.d( "TCLRecevier-->onReceive-->cmdstring-->" + cmdString + "-->");
			if(PLAY_VIDEO.equals(cmdString)){
				playVideo(context, intent);
			}
			else if(SHOW_DETAIL.equals(cmdString)){
				showDetail(context, intent);
			}
			else if(SEARCH.equals(cmdString)){
				doSearch(context, intent);
			}
			/*else if(VOICE_SEARCH.equals(cmdString)){
				doSearch(context, intent);
			}*/
			/*else if(DEL_SINGLE.equals(cmdString)){
				delSingle(context, intent);
			}
			else if(DEL_ALL.equals(cmdString)){
				delAll(context, intent);
			}*/
		}
	}
	
	private void playVideo(Context context, Intent intent){
		final String videoId = intent.getExtras().getString("videoId");
		final String episodeId = intent.getExtras().getString("episodeId");
		final int playTime = intent.getExtras().getInt("currentPosition") / 1000;
		Intent i = new Intent();
		i.putExtra("mid", videoId);
		i.putExtra("sid", episodeId);
		i.putExtra("playTime", playTime);
		i.setClass(context, CooperationStartActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
	}
	
	private void showDetail(Context context, Intent intent){
		final String intentMid = intent.getExtras().getString("videoId");
		LogUtil.d( "TCLRecevier-->onReceive-->videoId-->" + intentMid);
		if (intentMid != null && !intentMid.equals("")) {
			Intent i  = new Intent();
			i.putExtra("intentMid", intentMid);
			i.setClass(context, MovieDetailActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
	}
	
	private void doSearch(Context context, Intent i){
		String keyword = i.getExtras().getString("keyword");
		LogUtil.d("TCLRecevier-->doSearch-->keyword-->" + keyword);
		if(keyword != null && !"".equals(keyword)){
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setAction("com.voole.epg.action.Search");
			intent.putExtra("type", "keyword");
			intent.putExtra("keyword", keyword);
			context.startActivity(intent);
		}
	}
	
	/*private void delSingle(Context context, Intent intent){
		final String videoId = intent.getExtras().getString("videoId");
		final String identifierType = intent.getExtras().getString("identifierType");
		new Thread(){
			public void run() {
				if(DEL_FAVORITE.equals(identifierType)){
					MovieManager.GetInstance().deleteFavorite(videoId);
				}else if(DEL_HISTORY.equals(identifierType)){
				}
			};
		}.start();
	}
	
	private void delAll(Context context, Intent intent){
		
	}*/
	
	/*private void initVooleLib(){
		new Thread(){
			public void run() {
				AuthManager.GetInstance().startAuth();
				ProxyManager.GetInstance().startProxy();
			};
		}.start();
	}*/
	
	/*private void doSearch(Context context, Intent intent){
		String keyword = intent.getExtras().getString("keyword");
		Log.d(Config.TAG, "TCLRecevier-->doSearch-->keyword-->" + keyword);
		if(keyword != null && !"".equals(keyword)){
			initVooleLib();
			Bundle bundle = new Bundle();
			bundle.putString("keyword", keyword);
			bundle.putInt("method", 2);
			Intent i = new Intent();
			i.putExtras(bundle);
			i.setClass(context, SearchResultActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(i);
		}
	}*/
	
}
