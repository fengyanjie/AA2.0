package com.voole.epg.cooperation.hisense;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.jamdeo.tv.vod.player.thirdparty.VodSourcePlayerHelper;
import com.jamdeo.tv.vod.player.thirdparty.VodSourcePlayerHelper.EVENT;
import com.jamdeo.tv.vod.player.thirdparty.VodSourcePlayerHelper.MapKey;
import com.jamdeo.tv.vod.player.thirdparty.VodSourcePlayerHelper.SOURCE;
import com.voole.epg.LauncherApplication;
import com.voole.epg.corelib.model.cooperation.ICooperation;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.utils.LogUtil;

public class Hisense implements ICooperation{

	@Override
	public void startPlay(Context context,Film film, Content content, String playUrl) {
		if ("1".equals(content.getIs3dvideo())) {
			LogUtil.d("Hisense-->startPlay-->sendIntent-->action--->" + "com.hisense.set.threed");
			Intent newIntent = new Intent();
			newIntent.setAction("com.hisense.set.threed");
			newIntent.putExtra("threedMode",8);
			context.sendBroadcast(newIntent);
		}
		if (film.isAggregate()) {
			Map<MapKey, String> values = new HashMap<MapKey, String>();
			values.put(MapKey.PROGRAM_ID, film.getHisenseMid());
			values.put(MapKey.EPISODE_ID, film.getHisenseSid());
			values.put(MapKey.RESOLUTION, content.getRate());
			long ts = System.currentTimeMillis()/1000;
			values.put(MapKey.TIMESLOT, Long.toString(ts));
			VodSourcePlayerHelper.journalReport(context, SOURCE.VOOLE, EVENT.VIDEO_START, values);
			
		}
	}

	@Override
	public void stopPlay(Film film, Content content, int position, int duration) {
		Intent intent = new Intent();
		intent.setAction("com.hisense.recordwrite");
		intent.putExtra("srcApp", "com.voole.epg");
		intent.putExtra("startUpMode", "activity");
		String cmdString = "com.voole.epg.view.movies.RecommendActivity";
		intent.putExtra("cmdstring", cmdString);
		intent.putExtra("cmdInfo", film.getMid());
		LogUtil.d("Hisense-->stopPlay-->cmdInfo-->" + film.getMid());
		intent.putExtra("videoName", film.getFilmName());
		LogUtil.d("Hisense-->stopPlay-->videoName-->" + film.getFilmName());
		intent.putExtra("videoType", film.getFilmType());
		LogUtil.d("Hisense-->stopPlay-->videoType-->" + film.getFilmType());
		intent.putExtra("videoImgUrl", film.getImgUrlB());
		LogUtil.d("Hisense-->stopPlay-->videoImgUrl-->" + film.getImgUrlB());
		if("1".equals(film.getContentCount())){
			intent.putExtra("episodeName", "");
			LogUtil.d("Hisense-->stopPlay-->episodeName-->" + "");
		}else{
			intent.putExtra("episodeName", "第" + content.getContentIndex() + "集");
			LogUtil.d("Hisense-->stopPlay-->episodeName-->" + "第" + content.getContentIndex() + "集");
		}
		intent.putExtra("currentPosition", position);
		LogUtil.d("Hisense-->stopPlay-->currentPosition-->" + position);
		intent.putExtra("duration", duration);
		LogUtil.d("Hisense-->stopPlay-->duration-->" + duration);
		LauncherApplication.GetInstance().getApplicationContext().sendBroadcast(intent);
		
		if (film.isAggregate()) {
			Map<MapKey, String> values = new HashMap<MapKey, String>();
			values.put(MapKey.PROGRAM_ID, film.getHisenseMid());
			values.put(MapKey.EPISODE_ID, film.getHisenseSid());
			values.put(MapKey.RESOLUTION, content.getRate());
			long ts = System.currentTimeMillis()/1000;
			values.put(MapKey.TIMESLOT, Long.toString(ts));
			values.put(MapKey.POSITION, position+"");
			VodSourcePlayerHelper.journalReport(LauncherApplication.GetInstance().getApplicationContext(), SOURCE.VOOLE, EVENT.VIDEO_EXIT, values);
		}
	}

	@Override
	public boolean isSupportMediaPlayerInfo() {
		return false;
	}

	@Override
	public void deletePlayHistory(String mid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllPlayHistory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFavorite(Film film) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFavorite(String mid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllFavorite() {
		// TODO Auto-generated method stub
		
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
		if ("1".equals(content.getIs3dvideo())) {
			Intent newIntent = new Intent();
			newIntent.setAction("com.hisense.set.threed");
			newIntent.putExtra("threedMode",0);
			context.sendBroadcast(newIntent);
		}
	}

	@Override
	public void playCompleted(Context context, Film film, Content content) {
		if (film.isAggregate()) {
			Map<MapKey, String> values = new HashMap<MapKey, String>();
			values.put(MapKey.PROGRAM_ID, film.getHisenseMid());
			values.put(MapKey.EPISODE_ID, film.getHisenseSid());
			values.put(MapKey.RESOLUTION, content.getRate());
			long ts = System.currentTimeMillis()/1000;
			values.put(MapKey.TIMESLOT, Long.toString(ts));
			VodSourcePlayerHelper.journalReport(LauncherApplication.GetInstance().getApplicationContext(), SOURCE.VOOLE, EVENT.VIDEO_END, values);
			if (context instanceof Activity) {
				System.out.println(context +"  finish()");
				((Activity)context).onBackPressed();
			}
		}
	}

	@Override
	public void addZhuiJu(Film film) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteZhuiJu(String mid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllZhuiju() {
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
		if (film.isAggregate()) {
			Map<MapKey, String> values = new HashMap<MapKey, String>();
			values.put(MapKey.PROGRAM_ID, film.getHisenseMid());
			values.put(MapKey.EPISODE_ID, film.getHisenseSid());
			values.put(MapKey.RESOLUTION, content.getRate());
			values.put(MapKey.START_TIMESLOT, Long.toString(startTime));
			values.put(MapKey.END_TIMESLOT, Long.toString(endTime));
			VodSourcePlayerHelper.journalReport(context, SOURCE.VOOLE, EVENT.VIDEO_BUFFERING, values);
		}
		
	}


}
