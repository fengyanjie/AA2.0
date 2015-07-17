package com.voole.epg.model.play;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.voole.epg.LauncherApplication;
import com.voole.epg.R;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.base.common.TVProgressDialog;
import com.voole.epg.corelib.model.account.AccountManager;
import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.movies.Content;
import com.voole.epg.corelib.model.movies.Detail;
import com.voole.epg.corelib.model.movies.Film;
import com.voole.epg.corelib.model.movies.MovieManager;
import com.voole.epg.corelib.model.play.Ad;
import com.voole.epg.corelib.model.play.AdParser;
import com.voole.epg.corelib.model.play.IPlay;
import com.voole.epg.corelib.model.play.PlayCheckInfo;
import com.voole.epg.corelib.model.play.PlayCheckInfoParser;
import com.voole.epg.corelib.model.transscreen.ResumePlay;
import com.voole.epg.corelib.model.transscreen.TransScreenManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.player.VoolePlayerActivity;
import com.voole.epg.player.ad.vo.ADUserCacheManager;
import com.voole.epg.view.mymagic.MyMagicActivity;
import com.voole.tvutils.DateUtil;
import com.voole.tvutils.NetUtil;

public class PlayManager {
	private static PlayManager instance = new PlayManager();

	private PlayManager() {
	}

	public static PlayManager GetInstance() {
		return instance;
	}
	
	private IPlay play = null;
	public void init(IPlay play){
		this.play = play;
	}
	
	public enum ChargeState{
		Charge,
		Ordered,
		Viewed
	}
	
	public static final int START_ACCOUNT_REQUEST = 1;
	
	private static final int GET_PLAYURL_SUCCESS = 1;
	private static final int GET_PLAYURL_FAIL = 2;
	private static final int ACCESS_NET_FAIL = 3;
	private static final int CHARGE = 4;
	private static final int RESUME = 5;
	private static final int NOT_EXIST = 6;
	private static final int IS_PREVIEW = 7;
	private static final int CAN_NOT_PREVIEW = 8;
	private static final int DIRECT_PLAY = 11;
	private static final int PREVIEW_PLAY_FAIL = 9;
	private static final int FIND_PRODUCT_ERROR = 10;
	private static final int PREVIEW_PLAYURL_SUCCESS = 12;
	
	private static final int RESUME_FAIL = 15;

	private Activity mActivity = null;
	private Film mFilm;
	private List<Content> mContentList;
	private int mIndex = 0;
	private Product mCurrentProduct = null;
	private boolean isAggregate = false;
	private boolean isPlayBack = false;
	private String mMagicClassName = MyMagicActivity.class.getName();
	
//	private PlayUrl playUrl = null;
	
	private Ad ad = null;
	public Ad getAd() {
		return ad;
	}

	private Activity playerActivity = null;
	
	private TVProgressDialog progressDialog = null;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			if (mActivity.isFinishing()) {
				LogUtil.d("---->PlayManager activity isFinishing");
				return;
			}
			switch (msg.what) {
				case PREVIEW_PLAYURL_SUCCESS:
					play.charge(mContentList, mIndex, ad.getAdxml(),mCurrentProduct);
					break;
				case GET_PLAYURL_SUCCESS:
					getPlayResume();
					break;
				case IS_PREVIEW:
//					previewDialog();
					List<Content> contentList = new ArrayList<Content>();
					Content content = mContentList.get(mIndex);
					contentList.add(content);
					play.play(mActivity, "", 0, mFilm, contentList, 0, mCurrentProduct, ad.getAdxml(), isAggregate);
					break;
				case DIRECT_PLAY:
					chargeDialog(true);
					break;
				case CAN_NOT_PREVIEW:
					new TVAlertDialog.Builder(mActivity)
					.setTitle(R.string.player_movie_balance_not_enough)
					.setPositiveButton(R.string.common_ok_button, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							gotoMyMagic(mActivity,"CONSUMERCENTER", 0);
						}
					}).create().show();
					break;
				case NOT_EXIST:
					new TVAlertDialog.Builder(mActivity)
					.setCancelable(false)
					.setTitle(R.string.player_movie_offline)
					.setPositiveButton(R.string.common_ok_button, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (isAggregate) {
								mActivity.finish();
							}
						}
					}).create().show();
					break;
				case ACCESS_NET_FAIL:
					new TVAlertDialog.Builder(mActivity)
					.setCancelable(false)
					.setTitle(R.string.common_access_net_fail)
					.setPositiveButton(R.string.common_ok_button, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (isAggregate) {
								mActivity.finish();
							}
						}
					}).create().show();
					break;
				case FIND_PRODUCT_ERROR:
					new TVAlertDialog.Builder(mActivity)
					.setCancelable(false)
					.setTitle(R.string.player_movie_get_fail)
					.setPositiveButton(R.string.common_ok_button, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (isAggregate) {
								mActivity.finish();
							}
						}
					}).create().show();
					break;
				case CHARGE:
					/*chargeDialog();*/
					new Thread(){
						public void run() {
							Content c = mContentList.get(mIndex);
							getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
						};
					}.start();
					break;
				case RESUME:
					int arg1 = msg.arg1;
					if(isAggregate){
						if(isPlayBack){
							play.play(mActivity, "", 0, mFilm, mContentList, mIndex, mCurrentProduct, ad.getAdxml(), isAggregate);
						}else{
							play.play(mActivity, "", arg1, mFilm, mContentList, mIndex, mCurrentProduct, ad.getAdxml(),isAggregate);
						}
					}else{
						if(arg1 > 0){
							resumeDialog(arg1);
						}else{
							play.play(mActivity, "", 0, mFilm, mContentList, mIndex, mCurrentProduct, ad.getAdxml(), isAggregate);
						}
					}
//					mActivity.finish();
					break;
				case RESUME_FAIL:
					Toast.makeText(mActivity, "因网络故障无法续播，将从头开始播放",3000).show();
					PlayManager.this.sendMessage(RESUME, 0);
					break;
				/*case GET_PLAYURL_FAIL:
					checkStatus(Integer.parseInt(playUrl.getStatus()), mActivity);
					break;*/
				case GET_PLAYURL_FAIL:
					String playurl_str = "您的网络不稳定，请重试";
					if(ad != null&&!TextUtils.isEmpty(ad.getResultdesc())){
						playurl_str = ad.getResultdesc();
					}
					new TVAlertDialog.Builder(mActivity)
					.setTitle(playurl_str)
					.setCancelable(false)
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									if(isAggregate){
										mActivity.finish();
									}
								}
							}).create().show();
					break;
				case PREVIEW_PLAY_FAIL:
					String preview_str = "您的网络不稳定，请重试";
					if(ad != null&&!TextUtils.isEmpty(ad.getResultdesc())){
						preview_str = ad.getResultdesc();
					}
					new TVAlertDialog.Builder(playerActivity)
					.setTitle(preview_str)
					.setCancelable(false)
					.setPositiveButton(R.string.ok,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) { 
//									gotoMyMagic(mActivity,"CONSUMERCENTER", 0);
									if(isAggregate){
										mActivity.finish();
									}
								}
							}).create().show();
					break;
				default:
					break;
			}
			
//			if ("Hisense".equalsIgnoreCase(PropertiesUtil.getProperty(mActivity, "oemType"))) {
//				cancelDialog();
//				PlayStatusDialogManager.getInstance(mActivity).cancelPlayStatus();
//			}else
			cancelDialog();
			super.handleMessage(msg);
		}
		
	};
	
	public PlayCheckInfo getPlayCheckInfo(String mid, String fid, String sid){
		String url = AuthManager.GetInstance().getAuthServer() + "/query?reqinfo=" + "<freetime><mid>" +mid +
		"</mid><fid>"+fid+"</fid><sid>"+sid+"</sid></freetime>&rand=" + DateUtil.getDateTime();
		String httpMessage= "";
		StringBuffer buffer = new StringBuffer();
		LogUtil.d("getPlayCheckInfo-->" + url);
		if (NetUtil.connectServer(url, buffer , 1 , 10)) {
			httpMessage = buffer.toString();
			LogUtil.d("getPlayCheckInfo--httpMessage-->" + httpMessage);
			PlayCheckInfoParser parser = new PlayCheckInfoParser(httpMessage);
			parser.parser();
			return parser.getInfo();
		}
		return null;
	}
	
	public void play(final Activity activity, final Film film, final List<Content> contentList, int contentIndex, Product product, ChargeState chargeState, String endTime){
		this.mActivity = activity;
		this.mFilm = film;
		this.mContentList = contentList;
		this.mIndex = contentIndex;
		
		if(product != null){
			this.mCurrentProduct = product;
			if(chargeState == ChargeState.Charge){
				/*chargeDialog();*/
				showDialog();
				new Thread(){
					public void run() {
						Content c = mContentList.get(mIndex);
						getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
					};
				}.start();
			}else if(chargeState == ChargeState.Viewed){
				showDialog();
				new Thread(){
					public void run() {
						Content c = mContentList.get(mIndex);
						getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
					};
				}.start();
			}else if(chargeState == ChargeState.Ordered){
				showDialog();
				new Thread(){
					public void run() {
						Content c = mContentList.get(mIndex);
						getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
					};
				}.start();
			}
		}else{
			showDialog();
			new Thread(){
				public void run() {
					findCurrentProduct();
				}
			}.start();
		}
	}
	
	
	public void play(final Activity activity, final Film film){
		this.mActivity = activity;
		this.mFilm = film;
		showDialog();
		new Thread(){
			public void run() {
				Detail detail = MovieManager.GetInstance().getDetailFromSrcUrl(film.getSourceUrl());
				if(detail == null){
					sendMessage(ACCESS_NET_FAIL);
				}else if(detail.getContentList() != null && detail.getContentList().size() > 0){
					mContentList = detail.getContentList();
					mIndex = 0;
					findCurrentProduct();
				}else{
					sendMessage(NOT_EXIST);
				}
			};
		}.start();
	}
	
	public void play(final Activity activity, final String mid){
		play(activity, mid, null);
	}
	
	public void play(final Activity activity, final String mid, final String sid){
		play(activity, mid, sid, "0", false, false,"","");
	}
	
	
	/**
	 * 
	 * @param activity
	 * @param mid
	 * @param sid
	 * @param isFavorite
	 * @param isAggregate 是否聚合跳转。默认false
	 */
	public void play(final Activity activity, final String mid, final String sid ,final String isFavorite,final boolean isPlayBack,final boolean isAggregate,final String hisenseMid,final String hisenseSid){
		this.isAggregate = isAggregate;
		this.isPlayBack = isPlayBack;
		this.mActivity = activity;
//		if ("Hisense".equalsIgnoreCase(PropertiesUtil.getProperty(activity, "oemType"))) {
//			cancelDialog();
//			PlayStatusDialogManager.getInstance(activity).showPlaystatus(PlayStatus.Prepare);
//		}else
		showDialog();
		new Thread(){
			public void run() {
				if (!AuthManager.GetInstance().isAuthRunning()) {
					AuthManager.GetInstance().startAuth();
				}
				if (!AuthManager.GetInstance().getAuthInfo()) {
					AuthManager.GetInstance().getAuthInfo();
					AuthManager.GetInstance().getUrlList();
				}
				Detail detail = MovieManager.GetInstance().getDetailFromMid(mid);
				if(detail == null){
					sendMessage(ACCESS_NET_FAIL);
				}else if(detail.getContentList() != null && detail.getContentList().size() > 0){
					mFilm = detail.getFilm();
                    if (isAggregate) {
                    	mFilm.setIsFavorite(isFavorite);
                    	mFilm.setHisenseMid(hisenseMid);
                    	mFilm.setHisenseSid(hisenseSid);
                    	mFilm.setAggregate(isAggregate);
					}
					mContentList = detail.getContentList();
					findSidIndex(sid);
					findCurrentProduct();
				}else{
					sendMessage(NOT_EXIST);
				}
			};
		}.start();
	}
	
	public String formatAuthPlayUrlForDeskTop(String mid, String sid){
		if (!AuthManager.GetInstance().isAuthRunning()) {
			AuthManager.GetInstance().startAuth();
		}
		if (!AuthManager.GetInstance().getAuthInfo()) {
			AuthManager.GetInstance().getAuthInfo();
		}
		Detail detail = MovieManager.GetInstance().getDetailFromMid(mid);
		if (detail == null) {
			return "001";
		}else if(detail.getContentList() != null && detail.getContentList().size() > 0){
			Film mFilm = detail.getFilm();
			findSidIndex(sid);
			List<Content> mContentList = detail.getContentList();
			List<Product> productList = AccountManager.GetInstance().getFilmProduct(mFilm.getMid(), 
					mContentList.get(0).getContentIndex(), mContentList.get(0).getFid(), mFilm.getMType());
			if(productList != null && productList.size() > 0){
				Product product = null;
				Content content = null;
				if ("0".equals(productList.get(0).getFee())) {
					//免费
					product = productList.get(0);
					content = mContentList.get(mIndex);
				}else{
					PlayCheckInfo playCheckInfo = getPlayCheckInfo(mFilm.getMid(), mContentList.get(0).getFid(), mContentList.get(0).getContentIndex());
					if(!"0".equals(playCheckInfo.getStatus())){
						return "004";
					}
					if ("0".equals(playCheckInfo.getOrder())) {
						//订购了产品包
						int productSize = productList.size();
						for (int i = 0; i < productSize; i++) {
							if (playCheckInfo.getPid().equals(productList.get(i).getPid())) {
								product = productList.get(i);
								content = mContentList.get(mIndex);
							}
						}
					}else if ("0".equals(playCheckInfo.getViewed())) {
						//看过，已经单点过
						product = productList.get(0);
						content = mContentList.get(mIndex);
					}else {
						//单点扣费
						product = productList.get(0);
						content = mContentList.get(mIndex);
					}
				}
				return formatAuthPlayUrl(mFilm.getMid(), content.getContentIndex(), content.getFid(), product.getPid(), product.getPtype(), content.getDownUrl());
			}else{
				return "003";
			}
			
		}else{
			return "002";
		}
	}
	
	public Ad doGetAd(final String mid, final String sid){
//		this.mActivity = activity;
		showDialog();
		Detail detail = MovieManager.GetInstance().getDetailFromMid(mid);
		if(detail == null){
			sendMessage(ACCESS_NET_FAIL);
			return null;
		}else if(detail.getContentList() != null && detail.getContentList().size() > 0){
			mFilm = detail.getFilm();
			mContentList = detail.getContentList();
			findSidIndex(sid);
			List<Product> productList = AccountManager.GetInstance().getFilmProduct(mFilm.getMid(), 
					mContentList.get(0).getContentIndex(), mContentList.get(0).getFid(), mFilm.getMType());
			if(productList != null && productList.size() > 0){
				if ("0".equals(productList.get(0).getFee())) {
					mCurrentProduct = productList.get(0);
					Content c = mContentList.get(mIndex);
//					getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
					ad = getAuthPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
				}else{
					PlayCheckInfo playCheckInfo = getPlayCheckInfo(mFilm.getMid(), mContentList.get(0).getFid(), mContentList.get(0).getContentIndex());
					if(!"0".equals(playCheckInfo.getStatus())){
						sendMessage(FIND_PRODUCT_ERROR);
						return null;
					}
					if ("0".equals(playCheckInfo.getOrder())) {
						int productSize = productList.size();
						for (int i = 0; i < productSize; i++) {
							if (playCheckInfo.getPid().equals(productList.get(i).getPid())) {
								mCurrentProduct = productList.get(i);
								Content c = mContentList.get(mIndex);
								getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
								return ad;
							}
						}
					}
					if ("0".equals(playCheckInfo.getViewed())) {
						mCurrentProduct = productList.get(0);
						Content c = mContentList.get(mIndex);
						getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
						return ad;
					}
					mCurrentProduct = productList.get(0);
					sendMessage(CHARGE);
				}
			}else{
//				sendMessage(ACCESS_NET_FAIL);
				return null;
			}
		}else{
//			sendMessage(NOT_EXIST);
			return null;
		}
		return ad;
	}
	
	public String doGetPlayUrl(Ad ad){
		if(ad == null ){
			return "";
		}
		if("0".equals(ad.getStatus()) || "4".equals(ad.getStatus())){
			if("0".equals(ad.getPriview())){
//				sendMessage(GET_PLAYURL_SUCCESS);
				return ad.getAdxml();
			}
			else if(ad.getPriview() == null){
//				sendMessage(CAN_NOT_PREVIEW);
				return "";
			}
			else{
				if("1".equals(ad.getPriview()) && "0".equals(ad.getPriviewTime())){
//					sendMessage(DIRECT_PLAY);
					return ad.getAdxml();
				}else{
//					sendMessage(IS_PREVIEW);
					return ad.getAdxml();
				}
			}
		}else{
//			sendMessage(GET_PLAYURL_FAIL);
			return "";
		}
	}
	
	private void findSidIndex(String sid){
		if(sid == null || "".equals(sid)){
			mIndex = 0;
			return;
		}
		for(int i = 0; i < mContentList.size(); i++){
			Content c = mContentList.get(i);
			if(sid.equals(c.getContentIndex())){
				mIndex = i;
				return;
			}
		}
		mIndex = 0;
	}
	
	private void findCurrentProduct(){
		List<Product> productList = AccountManager.GetInstance().getFilmProduct(mFilm.getMid(), 
				mContentList.get(0).getContentIndex(), mContentList.get(0).getFid(), mFilm.getMType());
		if(productList != null && productList.size() > 0){
			if ("0".equals(productList.get(0).getFee())) {
				mCurrentProduct = productList.get(0);
				Content c = mContentList.get(mIndex);
				getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
				return;
			}else{
				PlayCheckInfo playCheckInfo = getPlayCheckInfo(mFilm.getMid(), mContentList.get(0).getFid(), mContentList.get(0).getContentIndex());
				if(!"0".equals(playCheckInfo.getStatus())){
					sendMessage(FIND_PRODUCT_ERROR);
					return;
				}
				if ("0".equals(playCheckInfo.getOrder())) {
					int productSize = productList.size();
					for (int i = 0; i < productSize; i++) {
						if (playCheckInfo.getPid().equals(productList.get(i).getPid())) {
							mCurrentProduct = productList.get(i);
							Content c = mContentList.get(mIndex);
							getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
							return;
						}
					}
				}
				if ("0".equals(playCheckInfo.getViewed())) {
					mCurrentProduct = productList.get(0);
					Content c = mContentList.get(mIndex);
					getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
					return;
				}
				mCurrentProduct = productList.get(0);
				sendMessage(CHARGE);
			}
		}else{
			sendMessage(ACCESS_NET_FAIL);
		}
	}
	
	
	/*private void chargeDialog(){
		String strMessage = "本部影片价格为";
		strMessage += Integer.parseInt(mCurrentProduct.getFee()) / 100;
		strMessage += "元，观片有效期为" + Integer.parseInt(mCurrentProduct.getUsefulLife()) / 24 + "天。";
		new TVAlertDialog.Builder(mActivity)
		.setTitle(strMessage)
		.setPositiveButton(R.string.player_movie_confirm_play,
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) { 
					showDialog();
					new Thread(){
						public void run() {
							Content c = mContentList.get(mIndex);
							getPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid(), mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
						};
					}.start();
				}
			})
		.setNegativeButton(R.string.player_movie_more_discount,
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					gotoMyMagic(mActivity,"CONSUMERCENTER");
				}
		}).create().show();
	}*/
	
	/*private void previewDialog(){
		String strMessage = "本部影片为收费影片，价格";
		strMessage += Integer.parseInt(mCurrentProduct.getFee()) / 100;
		strMessage += "元，您可免费预览";
		strMessage += Integer.parseInt(ad.getPriviewTime()) / 60;
		strMessage += "分钟";
		new TVAlertDialog.Builder(mActivity)
		.setTitle(strMessage)
		.setPositiveButton("确定",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) { 
//					play.play(mActivity, "", 0, mFilm, mContentList, mIndex, mCurrentProduct, ad.getAdxml());
					List<Content> contentList = new ArrayList<Content>();
					Content content = mContentList.get(mIndex);
					contentList.add(content);
					play.play(mActivity, "", 0, mFilm, contentList, 0, mCurrentProduct, ad.getAdxml());
				}
		}).setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).create().show();
	}*/
	
	public void chargeDialog(final boolean isDirectPlay){
		if(ad == null || "0".equals(ad.getPriview())){
			return;
		}
		if(!isDirectPlay && "1".equals(ad.getPriview()) && "0".equals(ad.getPriviewTime())){
			return;
		}
		
		String strMessage = "本部影片价格为";
		strMessage += Integer.parseInt(mCurrentProduct.getFee()) / 100;
		strMessage += "元，观片有效期为" + Integer.parseInt(mCurrentProduct.getUsefulLife()) / 24 + "天。";
		new TVAlertDialog.Builder(mActivity)
		.setTitle(strMessage)
		.setPositiveButton(R.string.player_movie_confirm_play,
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) { 
					if(isDirectPlay){
						startPlay();
					}else{
						startPreviewPlay();
					}
				}
			})
		.setNegativeButton(R.string.player_movie_more_discount,
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					gotoMyMagic(mActivity,"CONSUMERCENTER", 1);
				}
		}).create().show();
	}
	
	private PlayDialog playDialog = null;
	public void showPlayHintDialog(final Activity activity){
		if (ad == null || ad.getPriview()==null||ad.getPriviewTime() == null ||"0".equals(ad.getPriview())) {
			return;
		}
		if("1".equals(ad.getPriview()) && "0".equals(ad.getPriviewTime())){
			return;
		}
		this.playerActivity = activity;
		if("1".equals(ad.getPriview())){
			String strMessage = "本片价格";
			strMessage += Integer.parseInt(mCurrentProduct.getFee()) / 100;
			strMessage += "元，您可以免费预览"+ Integer.parseInt(ad.getPriviewTime()) / 60+"分钟\n您可以选择如下方式订购本片：\n １.单点：服务有效期为" + Integer.parseInt(mCurrentProduct.getUsefulLife()) / 24 + "天\n ２.订购：进入我的优朋，订购服务";
			playDialog = new PlayDialog.Builder(activity)
			.setTitle(strMessage)
			.setPositiveButton("单点",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) { 
						startPreviewPlay();
					}
				})
			.setNegativeButton(R.string.cancel,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
			}).create(0.84,0.85);
			playDialog.show();
		}else{
			String strMessage = "本片价格为";
			strMessage += Integer.parseInt(mCurrentProduct.getFee()) / 100;
			strMessage += "元，您可以免费预览"+ Integer.parseInt(ad.getPriviewTime()) / 60+"分钟。您的余额不足，请及时充值，充值卡购买方式：\n １.请拨打优朋影视7*24小时服务热线400-655-7899；\n ２.登陆优朋影视官网dk.voole.com";
			playDialog = new PlayDialog.Builder(activity)
			.setTitle(strMessage)
			.setPositiveButton("充值",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) { 
//						if (activity instanceof IPlayerFace) {
//							((IPlayerFace)playerActivity).getMediaController().doStop();
//						}
						((VoolePlayerActivity)activity).doStop();
						gotoMyMagic(mActivity,"CONSUMERCENTER", 0);
					}
				})
			.setNegativeButton("取消",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
			}).create(0.9,1.0);
			playDialog.show();
		}
	}
	
	public void hidePlayHintDialog(){
		if(playDialog != null && playDialog.isShowing()){
			playDialog.dismiss();
			playDialog = null;
		}
	}
	
	private void startPlay(){
		showDialog();
		new Thread(){
			@Override
			public void run() {
				httpCharge();
				sendMessage(GET_PLAYURL_SUCCESS);
			}
		}.start();
	}
	
	/*private void startPreviewPlay(){
		showDialog();
		new Thread(){
			@Override
			public void run() {
				Content c = mContentList.get(mIndex);
				byte[] b1 = Base64.decode(c.getDownUrl(), Base64.DEFAULT);
				String str1 = new String(b1);
				String str2 = str1 + "&time=" + ad.getPriviewTime();
				String downUrl = Base64.encodeToString(str2.getBytes(), Base64.NO_WRAP);
				ad = getAuthPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid()
						, mCurrentProduct.getPid(), mCurrentProduct.getPtype(), downUrl);
				if(ad == null ){
					sendMessage(ACCESS_NET_FAIL);
					return;
				}
				
				if("0".equals(ad.getStatus()) || "4".equals(ad.getStatus())){
					if("0".equals(ad.getPriview())){
						if("1".equals(ad.getDelayDeduct())){
							httpCharge();
						}
						sendMessage(GET_PLAYURL_SUCCESS);
					}else{
						sendMessage(PREVIEW_PLAY_FAIL);
					}
				}else{
					sendMessage(GET_PLAYURL_FAIL);
				}
			}
		}.start();
	}*/
	
	private void startPreviewPlay(){
		new Thread(){
			@Override
			public void run() {
				httpCharge();
				Content c = mContentList.get(mIndex);
				ad = getAuthPlayUrl(mFilm.getMid(), c.getContentIndex(), c.getFid()
						, mCurrentProduct.getPid(), mCurrentProduct.getPtype(), c.getDownUrl());
				if(ad == null ){
					return;
				}
				
				if("0".equals(ad.getStatus()) || "4".equals(ad.getStatus())){
					if("0".equals(ad.getPriview())){
						sendMessage(PREVIEW_PLAYURL_SUCCESS);
						return;
					}
				}
				sendMessage(PREVIEW_PLAY_FAIL);
			}
		}.start();
	}
	
	private void httpCharge(){
		StringBuffer buffer = new StringBuffer();
		String url = "http://userauth.voole.com/Service.do?action=delaydeduct&oemid=" + AuthManager.GetInstance().getUser().getOemid()
			+ "&uid=" + AuthManager.GetInstance().getUser().getUid() + "&hid=" + AuthManager.GetInstance().getUser().getHid()
			+ "&mid=" + mFilm.getMid() + "&sid=" + mContentList.get(mIndex).getContentIndex() + "&fid=" + mContentList.get(mIndex).getFid()
			+ "&pid=" + ad.getPid() + "&version=v1.0";
		LogUtil.d("httpCharge------->"+ url);
		NetUtil.connectServer(url, buffer);
	}
	
	private void resumeDialog(final int playTime){
		String strMessage = "您上次播放到  " + DateUtil.secondToString(playTime) + ", 是否继续播放？";
		new TVAlertDialog.Builder(mActivity)
		.setTitle(strMessage)
		.setPositiveButton("继续播放",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) { 
					play.play(mActivity, "", playTime, mFilm, mContentList, mIndex, mCurrentProduct, ad.getAdxml(),isAggregate);
				}
			})
		.setNegativeButton("重新播放",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					play.play(mActivity, "", 0, mFilm, mContentList, mIndex, mCurrentProduct, ad.getAdxml(), isAggregate);
				}
		}).create().show();
	}
	
	private void getPlayResume(){
		
//		if ("Hisense".equalsIgnoreCase(PropertiesUtil.getProperty(mActivity, "oemType"))) {
//			cancelDialog();
//			PlayStatusDialogManager.getInstance(mActivity).showPlaystatus(PlayStatus.Prepare);
//		}else
		showDialog();
		new Thread(){
			public void run() {
				ResumePlay resumePlay = TransScreenManager.GetInstance().getResumeInfo(mFilm.getMid(), mContentList.get(mIndex).getContentIndex());
				if(resumePlay != null){
					int playTime = 0;
					if(resumePlay.getResumeInfoList().size() > 0 && resumePlay.getResumeInfoList().get(0) != null && resumePlay.getResumeInfoList().get(0).getPlayProgress() != null){
						playTime = Integer.parseInt(resumePlay.getResumeInfoList().get(0).getPlayProgress());
					}else{
						TransScreenManager.GetInstance().addResume(mFilm.getMid(), mContentList.get(mIndex).getContentIndex(), 0,mFilm.getFilmName(),mFilm.getImgUrl());
					}
					sendMessage(RESUME, playTime);
				}else{
					sendMessage(RESUME_FAIL);
				}
			};
		}.start();
	}
	
	public Ad getPlayUrl(String mid, String sid, String fid, String mtype, String downUrl){
		Product p = findCurrentProduct(mid, sid, fid, mtype);
		if(p != null){
			return getAuthPlayUrl(mid, sid, fid, p.getPid(), p.getPtype(), downUrl);
		}
		return null;
	}
	
	private Product findCurrentProduct(String mid, String sid, String fid, String mtype){
		List<Product> productList = AccountManager.GetInstance().getFilmProduct(mid, sid, fid, mtype);
		Product p = null;
		if(productList != null && productList.size() > 0){
			if ("0".equals(productList.get(0).getFee())) {
				p = productList.get(0);
			}else{
				PlayCheckInfo playCheckInfo = getPlayCheckInfo(mid, fid, sid);
				if (playCheckInfo==null) {
					return null;   
				}
				if(!"0".equals(playCheckInfo.getStatus())){
					return null;   
				}
				if ("0".equals(playCheckInfo.getOrder())) {
					int productSize = productList.size();
					for (int i = 0; i < productSize; i++) {
						if (playCheckInfo.getPid().equals(productList.get(i).getPid())) {
							p = productList.get(i);
							break;
						}
					}
				}
				if ("0".equals(playCheckInfo.getViewed())) {
					p = productList.get(0);
				}
				p = productList.get(0);
			}
		}
		return p;
	}
	
	private void getPlayUrl(String mid, String sid, String fid, String pid, String playtype, String downUrl){
		ad = getAuthPlayUrl(mid, sid, fid, pid, playtype, downUrl);
		if(ad == null ){
			sendMessage(ACCESS_NET_FAIL);
			return;
		}
		
		if("0".equals(ad.getStatus()) || "4".equals(ad.getStatus())){
			if("0".equals(ad.getPriview())){
				sendMessage(GET_PLAYURL_SUCCESS);
			}
			else if(ad.getPriview() == null){
				sendMessage(CAN_NOT_PREVIEW);
//				sendMessage(GET_PLAYURL_SUCCESS);
			}
			else{
				if("1".equals(ad.getPriview()) && "0".equals(ad.getPriviewTime())){
					sendMessage(DIRECT_PLAY);
				}else{
					sendMessage(IS_PREVIEW);
				}
			}
		}else{
			sendMessage(GET_PLAYURL_FAIL);
		}
		/*if(playUrl.getReturn_url() != null && !"".equals(playUrl.getReturn_url())){
			sendMessage(GET_PLAYURL_SUCCESS);
		}else{
			sendMessage(GET_PLAYURL_FAIL);
		}*/
	}
	
	/*private String formatAuthPlayUrl(String mid, String sid, String fid, String pid, String playtype) {
		String url = AuthManager.GetInstance().getAuthServer() + "/playauth?mid=" + mid + "&sid=" + sid
				+ "&fid=" + fid + "&pid=" + pid + "&playtype=" + playtype
				+ "&output=xml" + "&rand=" + DateUtil.getDateTime();
		return url;
	}*/
	
	private String formatAuthPlayUrl(String mid, String sid, String fid, String pid, String playtype, String downloadUrl) {
		if(downloadUrl == null){
			downloadUrl = "";
		}
//		StringBuffer sb = new StringBuffer();
//		sb.append("{\"action\":\"playauth\",\"mid\":");
//		sb.append("\"" + mid + "\",\"sid\":");
//		sb.append("\"" + sid + "\",\"fid\":");
//		sb.append("\"" + fid + "\",\"pid\":");
//		sb.append("\"" + pid + "\",\"playtype\":");
//		sb.append("\"" + playtype + "\",\"ext\":");
//		sb.append("\"" + downloadUrl + "\"}");
		
		StringBuffer sb = new StringBuffer();
		sb.append("{\"action\":\"playauth\",");
		sb.append("\"mid\":"+"\"" + mid + "\",");
		sb.append("\"sid\":"+"\"" + sid + "\",");
		sb.append("\"usercache\":"+"\"" + ADUserCacheManager.getInstatce().getUerCache(LauncherApplication.GetInstance())+ "\",");
		sb.append("\"fid\":"+"\"" + fid + "\",");
		sb.append("\"pid\":"+"\"" + pid + "\",");
		sb.append("\"playtype\":"+"\"" + playtype + "\",");
		sb.append("\"ext\":"+"\"" + downloadUrl + "\"");
		sb.append("}");
		
		
		
		/*String authUrl = "http://userauth.voole.com/Agent.do?uid=" + AuthManager.GetInstance().getUser().getUid() 
		+ "&hid=" + AuthManager.GetInstance().getUser().getHid() + "&oemid=" + AuthManager.GetInstance().getUser().getOemid() 
		+ "&param=" + sb.toString();*/
		String authUrl = AuthManager.GetInstance().getUrlList().getPlayAuth() + "&param=" + sb.toString();
		LogUtil.d("formatAuthPlayUrl--adv--authUrl----->"+ authUrl);
		/*String url = AuthManager.GetInstance().getAuthServer() + "/url?q=" + authUrl;
		return url;*/
		return authUrl;
	}
	
	/*public PlayUrl getAuthPlayUrl(String mid, String sid, String fid, String pid, String playtype, String downUrl) {
		StringBuffer buffer = new StringBuffer();
		String httpMessage = "";
		String url = formatAuthPlayUrl(mid, sid, fid, pid, playtype, downUrl);
		if (NetUtil.connectServer(url, buffer)) {
			httpMessage = buffer.toString();
			PlayUrlParser parser = new PlayUrlParser(httpMessage);
			parser.parser();
			return parser.getPlayUrl();
		} 
		return null;
	}*/
	
	public Ad getAuthPlayUrl(String mid, String sid, String fid, String pid, String playtype, String downUrl) {
		StringBuffer buffer = new StringBuffer();
		String httpMessage = "";
		String url = formatAuthPlayUrl(mid, sid, fid, pid, playtype, downUrl);
		if (NetUtil.connectServer(url, buffer,1,2)) {
			httpMessage = buffer.toString();
			AdParser parser = new AdParser(httpMessage);
			parser.parser();
			return parser.getAd();
		} 
		return null;
	}
	
	private void sendMessage(int what){
		Message message = Message.obtain();
		message.what = what;
		handler.sendMessage(message);
	}
	
	private void sendMessage(int what, int arg1){
		Message message = Message.obtain();
		message.what = what;
		message.arg1 = arg1;
		handler.sendMessage(message);
	}
	
	private void showDialog() {
		if (progressDialog == null && !mActivity.isFinishing()) {
			progressDialog = new TVProgressDialog(mActivity);
			progressDialog.show();
		}
	}
	
	private void cancelDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	public void checkStatus(final int status, final Activity context){
		String MsgInfo = "";
		switch (status) {
		case 0:
			//MsgInfo = "成功";
			MsgInfo=context.getString(R.string.player_movie_check_status_0);
			break;
		case 1:
			//MsgInfo = "抱歉，此节目暂时无法观看，或已移除，试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_1);
			break;
		case 2:
			//MsgInfo = "抱歉，系统出错啦，请稍后再尝试吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_2);
			break;
		case 3:
			//MsgInfo = "抱歉，网络出错啦，请检查网络连接是否正常后重试。";
			MsgInfo=context.getString(R.string.player_movie_check_status_3);
			break;
		case 4:
			//MsgInfo = "抱歉，操作超时啦，请稍后再尝试吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_4);
			break;
		case 101:
			//MsgInfo = "抱歉，服务器出错啦，请试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_101);
			break;
		case 102:
			//MsgInfo = "抱歉，登录认证平台失败，请稍后再尝试吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_102);
			break;
		case 103:
			//MsgInfo = "抱歉，您没有订购该产品，请试试其它节目吧";
			MsgInfo=context.getString(R.string.player_movie_check_status_103);
			break;
		case 105:
			//MsgInfo = "抱歉，登录认证平台失败，请稍后重试。";
			MsgInfo=context.getString(R.string.player_movie_check_status_105);
			break;
		case 106:
			//MsgInfo = "抱歉，登录认证平台失败，请稍后重试。";
			MsgInfo=context.getString(R.string.player_movie_check_status_106);
			break;
		case 112:
			//MsgInfo = "抱歉，此节目暂时无法观看，或已移除，请试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_112);
			break;
		case 113:
			//MsgInfo = "抱歉，当前节目不属于您的订购产品，请试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_113);
			break;
		case 114:
			//MsgInfo = "抱歉，余额不足，请您及时支付。";
			MsgInfo=context.getString(R.string.player_movie_check_status_114);
			break;
		case 115:
			//MsgInfo = "抱歉，服务器出错啦，请试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_115);
			break;
		case 119:
			//MsgInfo = "抱歉，服务器出错啦，请试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_119);
			break;
		case 121:
			//MsgInfo = "抱歉，服务器出错啦，请试试其它节目吧。";
			MsgInfo=context.getString(R.string.player_movie_check_status_121);
			break;
		case 123:
			//MsgInfo = "抱歉，您已被强制下线，请检查账号是否被滥用，如有疑问，请致电客服。";
			MsgInfo=context.getString(R.string.player_movie_check_status_123);
			break;
		case 200:
			//MsgInfo = "抱歉，您的认证模块未正常工作，请检查认证模块是否在运行，如有疑问，请致电客服。";
			MsgInfo=context.getString(R.string.player_movie_check_status_200);
			break;
		default:
			MsgInfo = "错误，未定义（" + status + "）";
			break;
		}

		if (status != 0){
			new TVAlertDialog.Builder(context)
			.setCancelable(false)
			.setTitle(MsgInfo)
			.setPositiveButton(R.string.common_ok_button,
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if(status == 114){
						gotoMyMagic(context,"CONSUMERCENTER", 0);
					}
				}
			})
			.setNegativeButton(R.string.common_cancel_button, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (isAggregate) {
						mActivity.finish();
					}
				}
			})
			.create().show();
		}
	}
	
	private void gotoMyMagic(Activity activity,String toWhere, int index){
		Intent intent = new Intent();
		intent.putExtra("toWhere", toWhere);
		intent.putExtra("index", index);
		intent.setClassName(activity, mMagicClassName);
		activity.startActivityForResult(intent, START_ACCOUNT_REQUEST);
		if (isAggregate) {
			activity.finish();
		}
	}
}
