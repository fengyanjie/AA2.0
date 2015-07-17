package com.voole.epg.cooperation.haier;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;

import com.voole.epg.LauncherApplication;
import com.voole.epg.corelib.model.cooperation.ICooperation;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Film;

public class Haier implements ICooperation {

	private final String ACTION_ADD_HISTORY = "com.haier.video.action.ACTION_ADD_HISTORY";
	private final String ACTION_DELETE_PLAYRECORD = "com.haier.video.action.ACTION_DELETE_PLAYRECORD";
	private final String ACTION_CLEAR_PLAYRECORD = "com.haier.video.action.ACTION_CLEAR_PLAYRECORD";
	private final String ACTION_ADD_FAVRECORD = "com.haier.video.action.ACTION_ADD_FAVRECORD";
	private final String ACTION_DELETE_FAVRECORD = "com.haier.video.action.ACTION_DELETE_FAVRECORD";
	private final String ACTION_CLEAR_FAVRECORD = "com.haier.video.action.ACTION_CLEAR_FAVRECORD";


	@Override
	public void startPlay(Context context, Film film, Content content,
			String playUrl) {
		JSONObject data = new JSONObject();
		try {
			data.put("videoId", film.getMid());
			data.put("videoTitle", film.getFilmName());
			data.put("videoImgUrl", film.getImgUrlB());
			data.put("videoSource", "优朋");
			data.put("Link_data", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sendBroadcast(ACTION_ADD_HISTORY, data.toString());
	}

	@Override
	public void stopPlay(Film film, Content content, int position, int duration) {

//		Intent intent = new Intent();
//		intent.setAction("com.hisense.recordwrite");
//		intent.putExtra("srcApp", "com.voole.epg");
//		intent.putExtra("startUpMode", "activity");
//		String cmdString = "com.voole.epg.view.movies.RecommendActivity";
//		intent.putExtra("cmdstring", cmdString);
//		intent.putExtra("cmdInfo", film.getMid());
//		LogUtil.d("Hisense-->stopPlay-->cmdInfo-->" + film.getMid());
//		intent.putExtra("videoName", film.getFilmName());
//		LogUtil.d("Hisense-->stopPlay-->videoName-->" + film.getFilmName());
//		intent.putExtra("videoType", film.getFilmType());
//		LogUtil.d("Hisense-->stopPlay-->videoType-->" + film.getFilmType());
//		intent.putExtra("videoImgUrl", film.getImgUrlB());
//		LogUtil.d("Hisense-->stopPlay-->videoImgUrl-->" + film.getImgUrlB());
//		if ("1".equals(film.getContentCount())) {
//			intent.putExtra("episodeName", "");
//			LogUtil.d("Hisense-->stopPlay-->episodeName-->" + "");
//		} else {
//			intent.putExtra("episodeName", "第" + content.getContentIndex()
//					+ "集");
//			LogUtil.d("Hisense-->stopPlay-->episodeName-->" + "第"
//					+ content.getContentIndex() + "集");
//		}
//		intent.putExtra("currentPosition", position);
//		LogUtil.d("Hisense-->stopPlay-->currentPosition-->" + position);
//		intent.putExtra("duration", duration);
//		LogUtil.d("Hisense-->stopPlay-->duration-->" + duration);
//		LauncherApplication.GetInstance().getApplicationContext()
//				.sendBroadcast(intent);
	}

	@Override
	public boolean isSupportMediaPlayerInfo() {
		return false;
	}

	@Override
	public void deletePlayHistory(String mid) {
		JSONObject data = new JSONObject();
		try {
			data.put("srcApp", "com.voole.epg");
			data.put("videoId", mid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sendBroadcast(ACTION_DELETE_PLAYRECORD, data.toString());
	}

	@Override
	public void deleteAllPlayHistory() {
		JSONObject data = new JSONObject();
		try {
			data.put("srcApp", "com.voole.epg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sendBroadcast(ACTION_CLEAR_PLAYRECORD, data.toString());
	}

	@Override
	public void addFavorite(Film film) {
		JSONObject data = new JSONObject();
		try {
			data.put("videoId", film.getFilmID());
			data.put("videoTitle", film.getFilmName());
			data.put("videoImgUrl", film.getImgUrlB());
			data.put("videoSource", "优朋");
			data.put("episode",film.getCurrentEpisodeCount());
			data.put("contentType", film.getContentType());
			data.put("Link_data", "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sendBroadcast(ACTION_ADD_FAVRECORD, data.toString());
	}

	@Override
	public void deleteFavorite(String mid) {
		JSONObject data = new JSONObject();
		try {
			data.put("srcApp", "com.voole.epg");
			data.put("videoId", mid);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sendBroadcast(ACTION_DELETE_FAVRECORD, data.toString());

	}

	@Override
	public void deleteAllFavorite() {
		JSONObject data = new JSONObject();
		try {
			data.put("srcApp", "com.voole.epg");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		sendBroadcast(ACTION_CLEAR_FAVRECORD, data.toString());
	}

	@Override
	public void initPlayer() {

	}

	@Override
	public void releasePlayer() {

	}

	@Override
	public void onDestroy(Context context, Film film, Content content) {
	}

	@Override
	public void playCompleted(Context context, Film film, Content content) {

	}

	@Override
	public void addZhuiJu(Film film) {

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

	}

	@Override
	public void PlayBufferEnd(Context context, Film film, Content content,
			int position, long startTime, long endTime) {

	}

	private void sendBroadcast(String action, String jsonData) {
		Intent intent = new Intent();
		intent.setAction(action);
		intent.putExtra("data", jsonData);
		LauncherApplication.GetInstance().getApplicationContext()
				.sendBroadcast(intent);
	}

}
