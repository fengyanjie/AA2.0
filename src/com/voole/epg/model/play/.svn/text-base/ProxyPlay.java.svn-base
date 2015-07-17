package com.voole.epg.model.play;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;

import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.auth.User;
import com.voole.epg.corelib.model.cooperation.CooperationManager;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.play.Ad;
import com.voole.epg.corelib.model.play.IPlay;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.corelib.model.transscreen.TransScreenManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.player.PlayItem;
import com.voole.epg.player.VoolePlayer;
import com.voole.epg.player.VoolePlayer.AdInteractionUrlListener;
import com.voole.epg.player.VoolePlayer.PageReportUrlListener;
import com.voole.epg.player.VoolePlayer.PlayUrlListener;
import com.voole.epg.player.VoolePlayer.ProxyServerUrlListener;
import com.voole.epg.player.VoolePlayer.VoolePlayerListener;
import com.voole.epg.player.VoolePlayerActivity;

public class ProxyPlay implements IPlay {
	
	private Activity activity;
	private String playUrl;
	private int continuePlayTime;
	private int titilTime = 0;
	private int endTime = 0;
	private Film film;
	private List<Content> contentList;
	private int index;
	private Product product;
	private List<PlayItem> list;
	private String adxml = null;
	private boolean isAggregate = false;
	
	private boolean isPlaying = false;
	
	private Activity playerActivity;
	
	private int mCurrentIndex = 0;
	private long bufferStartTime = 0;
	private String playFrom;

	@Override
	public void play(Activity activity, String url, int playTime, Film film,
			List<Content> contentList, int index, Product product, String adxml, boolean isAggregate) {
		if(isPlaying){
			return;
		}
		this.isPlaying = true;
		this.activity = activity;
		this.continuePlayTime = playTime;
		this.film = film;
		this.contentList = contentList;
		this.index = index;
		this.product = product;
		this.adxml = adxml;
		this.isAggregate = isAggregate;
		
		getPlayItems();
		
		this.mCurrentIndex = this.index;
		
		startPlay();
	}
	
	private void getPlayItems(){
		list = new ArrayList<PlayItem>();
		int size = contentList.size();
		for (int i = 0; i < size; i++) {
			PlayItem item = new PlayItem();
			item.setMid(film.getMid());
			item.setFid(contentList.get(i).getFid());
			item.setSid(contentList.get(i).getContentIndex());
			int time= i==index?continuePlayTime:0;
			item.setContinuePlayTime(time);
			item.setPainTouTime(titilTime);
			item.setEndTime(endTime);
			String url= i==index?playUrl:"";
			item.setPlayUrl(url);
			item.setFilmName(film.getFilmName());
			item.setContentName(contentList.get(i).getContentName());
			item.setIs3d(contentList.get(i).getIs3dvideo());
			item.setDownUrl(contentList.get(i).getDownUrl());
			item.setPoster_small(film.getImgUrl());
			item.setPoster_big(film.getImgUrlB());
			item.setIsCollect(film.getIsFavorite());
			list.add(item);
		}
	}
	
	private void startPlay(){
		VoolePlayer.GetInstance().setPlayUrlListener(new PlayUrlListener() {
			@Override
			public String getPlayUrl(final PlayItem playItem) {
				String nextUrl = getNextUrl(playItem);
				playItem.setPlayUrl(nextUrl);
				return nextUrl;
			}
		});
		
		VoolePlayer.GetInstance().setPageReportUrlListener(new PageReportUrlListener() {
			@Override
			public String getPageReportUrl() {
				return AuthManager.GetInstance().getUrlList().getReport();
			}
		});
		
		VoolePlayer.GetInstance().setAdInteractionUrlListener(new AdInteractionUrlListener() {
			@Override
			public String getAdInteractionUrl() {
				return AuthManager.GetInstance().getUrlList().getInteractionAdUrl();
			}
		});
		
		
		VoolePlayer.GetInstance().setProxyInfoListener(new ProxyServerUrlListener() {
			@Override
			public String getProxyServerUrl() {
				return ProxyManager.GetInstance().getProxyServer();
			}
		});
		
		VoolePlayer.GetInstance().setVoolePlayerListener(new VoolePlayerListener() {
			@Override
			public void onStopPlay(List<PlayItem> playList, int currentIndex, int time, int duration) {
				updateHistory(playList.get(currentIndex).getMid(), playList.get(currentIndex).getSid(), time,playList.get(currentIndex).getFilmName(),playList.get(currentIndex).getPoster_small());
				CooperationManager.GetInstance().getCooperation().stopPlay(film, contentList.get(currentIndex), time, duration);
			}
			
			@Override
			public void onStartPlay(List<PlayItem> playList, int currentIndex, int time,Context context) {
				mCurrentIndex = currentIndex;
				updateHistory(playList.get(currentIndex).getMid(), playList.get(currentIndex).getSid(), time,playList.get(currentIndex).getFilmName(),playList.get(currentIndex).getPoster_small());
				CooperationManager.GetInstance().getCooperation().startPlay(context,film,contentList.get(currentIndex),playUrl);
			}
			
			@Override
			public void onResumePlay(List<PlayItem> playList, int currentIndex, int time) {
			}
			
			@Override
			public void onRestartProxy() {
				ProxyManager.GetInstance().startProxy();
			}
			
			@Override
			public void onPlayCompleted(List<PlayItem> playList, int currentIndex, int time) {
				updateHistory(playList.get(currentIndex).getMid(), playList.get(currentIndex).getSid(), 0,playList.get(currentIndex).getFilmName(),playList.get(currentIndex).getPoster_small());
				CooperationManager.GetInstance().getCooperation().playCompleted(playerActivity, film, contentList.get(mCurrentIndex));
			}
			
			@Override
			public void onPause(List<PlayItem> playList, int currentIndex, int time) {
				
			}
			
			@Override
			public void onFinishProxy() {
				ProxyManager.GetInstance().finishPlay();
//				ProxyManager.GetInstance().exitProxy();
			}
			
			@Override
			public boolean isProxyRunning() {
				return ProxyManager.GetInstance().isProxyRunning();
			}

			@Override
			public boolean onKeyDown(int keyCode, KeyEvent event,
					boolean isPause, Context context) {
				return false;
			}

			@Override
			public void getCurrentPlayInfo(List<PlayItem> playList,
					int currentIndex, int time, int totalTime, Context context) {
				
			}


			@Override
			public void onActivityCreate(Activity activity) {
			}

			@Override
			public void onActivityDestroy(Activity activity) {
				isPlaying = false;
//				PlayManager.GetInstance().chargeDialog(false);
				CooperationManager.GetInstance().getCooperation().onDestroy(activity,film,contentList.get(mCurrentIndex));
			}

			@Override
			public boolean onKeyUp(int keyCode, KeyEvent event, Context context) {
				return false;
			}

			@Override
			public void onPrepared(List<PlayItem> playList, int currentIndex, Activity activity) {
				playerActivity = activity;
				PlayManager.GetInstance().showPlayHintDialog(activity);
			}

			@Override
			public void onActivityStop(Activity activity) {
				isPlaying = false;
				PlayManager.GetInstance().hidePlayHintDialog();
			}

			@Override
			public String getProxyServer() {
				return ProxyManager.GetInstance().getProxyServer();
			}

			@Override
			public void bufferStart(List<PlayItem> playList, int currentIndex,
					int time, Context context) {
				bufferStartTime = System.currentTimeMillis()/1000;
			}

			@Override
			public void bufferEnd(List<PlayItem> playList, int currentIndex,
					int time, Context context) {
				long bufferEndTime = System.currentTimeMillis()/1000;
				CooperationManager.GetInstance().getCooperation().PlayBufferEnd(context, film, contentList.get(mCurrentIndex), time, bufferStartTime, bufferEndTime);
			}

			@Override
			public void onActivityResume(Activity activity) {
				// TODO Auto-generated method stub
				
			}


		});
		if (playerActivity!=null&&!playerActivity.isFinishing()) {
			playerActivity.finish();
		}
		
		if(adxml != null){
			User user = AuthManager.GetInstance().getUser();
			VoolePlayer.GetInstance().startPlay(activity, list, index, adxml, user.getOemid(), user.getUid(), user.getHid(),  START_VIDEOPLAYER_REQUEST);
		}else{
			VoolePlayer.GetInstance().startPlay(activity, list, index, START_VIDEOPLAYER_REQUEST);
		}
		if (isAggregate) {
			activity.finish();
		}
	}
	
	private String getNextUrl(PlayItem playItem) {
		Ad result = PlayManager.GetInstance().getAuthPlayUrl(
				playItem.getMid(), playItem.getSid(), playItem.getFid(),
				product.getPid(), product.getPtype(), playItem.getDownUrl());
		/*if (result.getReturn_url() != null && !"".equals(result.getReturn_url())) {
			return ProxyManager.GetInstance().getProxyServer()
					+ "/play?url='" + result.getReturn_url().substring(7) + "'"
					+ "&time='0'" + "&is3d="
					+ ("1".equals(playItem.getIs3d()) ? "1" : "0");
		}*/
		if(result != null && result.getAdxml() != null && !"".equals(result.getAdxml())){
			LogUtil.d("ProxyPlay--->getPlayUrl-->" + result.getAdxml());
			return result.getAdxml();
		}
		LogUtil.d("ProxyPlay--->getPlayUrl-->ad is null or adxml is null");
		return null;
	}
	
	private void updateHistory(final String mid, final String sid, final int playTime,final String title,final String imgUrl){
		Ad ad = PlayManager.GetInstance().getAd();
		if(ad != null && !"0".equals(ad.getPriview()) && !"0".equals(ad.getPriviewTime())){
			return;
		}
		new Thread(){
			public void run() {
				LogUtil.d("updateHistory---->" + playTime/1000);
				TransScreenManager.GetInstance().addResume(mid, sid, playTime / 1000, title, imgUrl);
			};
		}.start();
	}

 

	@Override
	public void charge(List<Content> contentList, int index, String adxml,
			Product product) {
		this.contentList = contentList;
		this.index = index;
		this.adxml = adxml;
		getPlayItems();
		
		((VoolePlayerActivity)playerActivity).chargePlay(list, index, adxml);
		
		/**
		 * 海信_创维聚合
		 */
//		if (playerActivity instanceof IPlayerFace) {
//			((IPlayerFace)playerActivity).getMediaController().refreshPlayUrl();
//		}
		
	}
}
