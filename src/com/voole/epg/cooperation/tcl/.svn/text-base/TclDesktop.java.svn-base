package com.voole.epg.cooperation.tcl;

import android.content.Context;
import android.content.Intent;

import com.voole.epg.LauncherApplication;
import com.voole.epg.corelib.model.cooperation.ICooperation;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.utils.LogUtil;

public class TclDesktop implements ICooperation{
	@Override
	public void startPlay(Context context, Film film, Content content,
			String playUrl) {
		
	}

	@Override
	public void stopPlay(Film film, Content content, int position, int duration) {
		Intent intent = new Intent();
		intent.setAction("com.tv.playstop");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("videoId", film.getMid());
		LogUtil.d("TCL-->stopPlay-->videoId-->" + film.getMid());
		intent.putExtra("videoName", film.getFilmName());
		LogUtil.d("TCL-->stopPlay-->videoName-->" + film.getFilmName());
		intent.putExtra("videoImgUrl", film.getImgUrlB());
		LogUtil.d("TCL-->stopPlay-->videoImgUrl-->" + film.getImgUrlB());
		intent.putExtra("episodeId", content.getContentIndex());
		LogUtil.d("TCL-->stopPlay-->episodeId-->" + content.getContentIndex());
		if("1".equals(film.getContentCount())){
			intent.putExtra("episodeName", "");
			LogUtil.d("TCL-->stopPlay-->episodeName-->" + "");
		}else{
			intent.putExtra("episodeName", "第" + content.getContentIndex() + "集");
			LogUtil.d("TCL-->stopPlay-->episodeName-->" + "第" + content.getContentIndex() + "集");
		}
		intent.putExtra("episodeCount", film.getContentCount());
		LogUtil.d("TCL-->stopPlay-->episodeCount-->" + film.getContentCount());
		intent.putExtra("currentPosition", position); 
		LogUtil.d("TCL-->stopPlay-->currentPosition-->" + position);
		intent.putExtra("duration", duration);
		LogUtil.d("TCL-->stopPlay-->duration-->" + duration);
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public boolean isSupportMediaPlayerInfo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deletePlayHistory(String mid) {
		Intent intent = new Intent();
		intent.setAction("com.tv.totcl.delsingle");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("videoId", mid);
		LogUtil.d("TCL-->deletePlayHistory-->videoId-->" + mid);
		intent.putExtra("identifierType", "0");
		LogUtil.d("TCL-->deletePlayHistory-->identifierType-->" + "0");
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void deleteAllPlayHistory() {
		Intent intent = new Intent();
		intent.setAction("com.tv.totcl.delall");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("identifierType", "0");
		LogUtil.d( "TCL-->deleteAllPlayHistory-->identifierType-->" + "0");
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void addFavorite(Film film) {
		Intent intent = new Intent();
		intent.setAction("com.tv.collection");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("videoId", film.getMid());
		LogUtil.d( "TCL-->addFavorite-->videoId-->" + film.getMid());
		intent.putExtra("videoName", film.getFilmName());
		LogUtil.d( "TCL-->addFavorite-->videoName-->" + film.getFilmName());
		intent.putExtra("videoImgUrl", film.getImgUrlB());
		LogUtil.d( "TCL-->addFavorite-->videoImgUrl-->" + film.getImgUrlB());
		intent.putExtra("episodeCount", film.getContentCount());
		LogUtil.d( "TCL-->addFavorite-->episodeCount-->" + film.getContentCount());
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void deleteFavorite(String mid) {
		Intent intent = new Intent();
		intent.setAction("com.tv.totcl.delsingle");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("videoId", mid);
		LogUtil.d( "TCL-->deleteFavorite-->videoId-->" + mid);
		intent.putExtra("identifierType", "1");
		LogUtil.d( "TCL-->deleteFavorite-->identifierType-->" + "1");
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void deleteAllFavorite() {
		Intent intent = new Intent();
		intent.setAction("com.tv.totcl.delall");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("identifierType", "1");
		LogUtil.d( "TCL-->deleteAllFavorite-->identifierType-->" + "1");
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void initPlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void releasePlayer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDestroy(Context context, Film film, Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addZhuiJu(Film film) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setAction("com.tv.chasedrama");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("videoId", film.getMid());
		LogUtil.d( "TCL-->addZhuiJu-->videoId-->" + film.getMid());
		intent.putExtra("videoName", film.getFilmName());
		LogUtil.d( "TCL-->addZhuiJu-->videoName-->" + film.getFilmName());
		intent.putExtra("videoImgUrl", film.getImgUrlB());
		LogUtil.d( "TCL-->addZhuiJu-->videoImgUrl-->" + film.getImgUrlB());
		intent.putExtra("episodeCount", film.getContentCount());
		LogUtil.d( "TCL-->addZhuiJu-->episodeCount-->" + film.getContentCount());
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void deleteZhuiJu(String mid) {
		Intent intent = new Intent();
		intent.setAction("com.tv.totcl.delsingle");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("videoId", mid);
		LogUtil.d( "TCL-->deleteZhuiJu-->videoId-->" + mid);
		intent.putExtra("identifierType", "2");
		LogUtil.d( "TCL-->deleteZhuiJu-->identifierType-->" + "2");
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void deleteAllZhuiju() {
		Intent intent = new Intent();
		intent.setAction("com.tv.totcl.delall");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("identifierType", "2");
		LogUtil.d( "TCL-->deleteAllZhuiju-->identifierType-->" + "2");
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
	}

	@Override
	public void playCompleted(Context context, Film film, Content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PlayBufferStart(Context context, Film film, Content content,
			int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PlayBufferEnd(Context context, Film film, Content content,
			int position,long startTime,long endTime) {
		// TODO Auto-generated method stub
		
	}

}
