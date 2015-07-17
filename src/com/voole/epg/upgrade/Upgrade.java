package com.voole.epg.upgrade;

import java.io.File;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.voole.android.client.UpAndAu.constants.AuxiliaryConstants;
import com.voole.android.client.UpAndAu.downloader.FileDownLoaderListener;
import com.voole.android.client.UpAndAu.downloader.FileDownloader;
import com.voole.android.client.UpAndAu.model.UpdateInfo;
import com.voole.android.client.UpAndAu.upgrade.AppUpgradeAuxiliaryer;
import com.voole.android.client.UpAndAu.upgrade.AppUpgradeAuxiliaryer.UpgradeVersionCheckCallBack;
import com.voole.epg.Config;
import com.voole.epg.LauncherApplication;
import com.voole.epg.R;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.tvutils.DeviceUtil;


public class Upgrade {
	private static final int END_UPGRADE = 100;
	public static final int HAS_UPGRADE = 101;
	private static final int ERROR_UPGRADE = 102;
//	private static final int UPGRADE_RESULT = 103;
	
	private final String apkName = "VoolePlay.apk";
	private final String apkPath = LauncherApplication.GetInstance().getFilePath();
	
	private boolean isError = false;
	
	private boolean isUpgrade = true;
	private Context mContext = null;
	private ProgressDialog downloadProgressDialog = null;
	private Handler mHandler = null;
	
	private UpdateInfo updateInfo = null;
//	private int downLoadCount = 0;
	private int fileSize = 0;
	
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case HAS_UPGRADE:
				boolean isUpgrade = updateInfo.upgradeRequired();
				if (isUpgrade) {
					mHandler.sendEmptyMessage(HAS_UPGRADE);
					new TVAlertDialog.Builder(mContext)
		        	.setCancelable(false)
		            .setTitle("您的版本已经不能使用，请更新新版本！")
//		            .setMessage(updateInfo.getIntroduction())
		            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
		                @Override
		                public void onClick(DialogInterface dialog, int whichButton){
		                	startToUpdate(updateInfo.getDownloadUrl(),apkPath,apkName, updateInfo.getFid(), updateInfo.getCurrentVersion());
		                }
		            }).create().show();
				}else{
					new TVAlertDialog.Builder(mContext)
		        	.setCancelable(false)
		            .setTitle("您的版本已经不能使用，请更新新版本！")
//		            .setMessage(updateInfo.getIntroduction())
		            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
		                @Override
		                public void onClick(DialogInterface dialog, int whichButton){
		                	mHandler.sendEmptyMessage(HAS_UPGRADE);
		                	startToUpdate(updateInfo.getDownloadUrl(),apkPath,apkName, updateInfo.getFid(), updateInfo.getCurrentVersion());
		                }
		            })
		            .setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int which) {
							
						}
					}).create().show();
				}
				break;
			case END_UPGRADE:
				if(downloadProgressDialog != null){
					downloadProgressDialog.setProgress( 100 );
					downloadProgressDialog.setMessage("下载完毕");
		            downloadProgressDialog.dismiss();
		            downloadProgressDialog = null;
				}
	            new TVAlertDialog.Builder(mContext)
				.setTitle("软件包下载完毕,请您安装")
		        .setCancelable(false)
		        .setPositiveButton("确定", new DialogInterface.OnClickListener(){
		            @Override
		            public void onClick(DialogInterface dialog, int whichButton){
		            	installApp();
		            }
		        }).create().show();
				break;
			case ERROR_UPGRADE:
				String title = "";
				if("0001".equals((String)msg.obj)){
					title = "磁盘空间不足";
				}else{
					title ="下载文件时出现异常";
				}
				new TVAlertDialog.Builder(mContext)
	        	.setCancelable(false)
	            .setTitle(title)
//	            .setMessage(updateInfo.getIntroduction())
	            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
	                @Override
	                public void onClick(DialogInterface dialog, int whichButton){
	                	doExit();
	                }
	            }).create().show();
//				mHandler.sendEmptyMessage(UPGRADE_RESULT);
				/*downLoadCount++;
				LogUtil.d("downLoad-----error--->count" + downLoadCount);
				if(downLoadCount > 3){
					String errorMsg = (String) msg.obj;
					if(downloadProgressDialog != null){
						downloadProgressDialog.dismiss();
						downloadProgressDialog = null;
					}
		            Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
		            mHandler.sendEmptyMessage(UPGRADE_RESULT);
				}else{
					if(updateInfo != null){
						startToUpdate(updateInfo.getDownloadUrl(),apkPath,apkName);
					}else{
						mHandler.sendEmptyMessage(UPGRADE_RESULT);
					}
				}*/
				break;
			default:
				break;
			}
		}
		
	};
	
	public Upgrade(Context context, Handler handler) {
		this.mContext = context;
		this.mHandler = handler;
	}
	
	/**
	 * 版本检测
	 */
	public void checkVersion(String checkVersionUrl) {
		if (isUpgrade) {
//			versionCode = DeviceUtil.getAppVersionCode(mContext);	
			String host = null;
			if(checkVersionUrl != null && checkVersionUrl.contains("?")){
				host = checkVersionUrl.substring(0, checkVersionUrl.indexOf("?"));
			}
			String versionCode = Config.GetInstance().getVersionCode();
			String oemid = UpgradeManager.GetInstance().getOemID(mContext);
			String packageName = DeviceUtil.getAppName(LauncherApplication.GetInstance().getApplicationContext());
			String appid = Config.GetInstance().getAppId();
			String sn = DeviceUtil.getMacAddress();
			String ip = DeviceUtil.getLocalIpAddress();
			String mac = DeviceUtil.getMacAddress();
			String hid = mac;
			if(AuthManager.GetInstance().getUser() != null){
				hid = AuthManager.GetInstance().getUser().getHid();
			}
//			checkVersionUrl = checkVersionUrl + "&oemid=" + oemid + "&appId=" + appid + "&packagename=" + packageName + "&version=" + versionCode;
			LogUtil.d("checkVersion---checkUrl===" + checkVersionUrl);
			
			AuxiliaryConstants.isCheckProxySpeed = false;
			if(host != null){
				new AppUpgradeAuxiliaryer(host, oemid, appid, packageName, versionCode, hid, new MyUpgradeVersionCheckCallBack()).checkVersion();
			}
//			new AppUpgradeAuxiliaryer(oemid, appid, packageName, versionCode, null, sn, ip, new MyUpgradeVersionCheckCallBack()).checkVersion();
			
			
		}/*else{
			mHandler.sendEmptyMessage(UPGRADE_RESULT);
		}*/
	}
	
	/**
	 * 实现检测升级回调
	 * @version V1.0
	 */
	private class MyUpgradeVersionCheckCallBack implements UpgradeVersionCheckCallBack {
		@Override
		public void onHasUpgrade(UpdateInfo updateInfoArg) {
			LogUtil.d("Upgrade-->onHasUpgrade");
			Message message = Message.obtain();
			message.what = HAS_UPGRADE;
			handler.sendMessage(message);
			updateInfo = updateInfoArg;
		}

		@Override
		public void onError(String errorMsg) {
			LogUtil.d("Upgrade-->onError");
			/*Message message = Message.obtain();
			message.what = ERROR_UPGRADE;
			handler.sendMessage(message);*/
		}

		@Override
		public void onNOUpgrade(UpdateInfo updateInfo) {
			LogUtil.d("Upgrade-->onNOUpgrade");
//			mHandler.sendEmptyMessage(UPGRADE_RESULT);
		}
	}

	/**
	 * 
	 * @param downLoadUrl 下载apk地址
	 * @param saveFilePath  本地保存apk路径
	 * @param apkName 下载后保存到本地的apk名称
	 */
	public synchronized void startToUpdate(final String downLoadUrl,
			final String saveFilePath, final String apkName, final String fid, final String currentversion) {
		// 这里开启一个线程避免ANR错误
		initProgressDialog();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					FileDownloader loader = new FileDownloader(downLoadUrl,
							saveFilePath, apkName, 1, fid, currentversion,new FileDownLoaderListener() {
								@Override
								public void onFileDownLoadBegin(int size) {
									downloadProgressDialog.setMax(size/1024);
									fileSize = size/1024;
								}

								@Override
								public void onFileDownLoadProgress(int size) {
									// 实时获知文件已经下载的数据长度
									/*Message msg = new Message();
									// 设置消息标签
									msg.what = 1;
									msg.getData().putInt("size", size);
									// 使用Handler对象发送消息
									handler.sendMessage(msg);*/
									downloadProgressDialog.setProgress(size/1024);
									downloadProgressDialog.setProgressNumberFormat(size/1024 + "k/" + fileSize + "k");
								}
								
								@Override
								public void onFileDownLoadEnd() {
//									DeviceUtil.setFileToPermission(saveFilePath + "/" + apkName);
									File apkFile = new File(saveFilePath + "/" + apkName);
									apkFile.setReadable(true, false);
									handler.sendEmptyMessage(END_UPGRADE);
									/*if (!isError) {
										isError = false;
										handler.sendEmptyMessage(END_UPGRADE);
									}*/
								}

								@Override
								public void onFileDownLoadError(String errorMsg) {
									Message message = Message.obtain();
									message.what = ERROR_UPGRADE;
									message.obj = errorMsg;
									handler.sendMessage(message);
								}
							});
					// 设置进度条的最大刻度为文件的长度
//					downloadProgressDialog.setMax(loader.getFileSize()/1024);
//					fileSize = loader.getFileSize()/1024;
					LogUtil.d("startToUpdate--->downLoadUrl--->" + downLoadUrl);
					loader.download();
				} catch (Exception e) {
					// 发送一个空消息到消息队列
					Message message = Message.obtain();
					message.what = ERROR_UPGRADE;
					message.obj = mContext.getResources().getString(
							R.string.server_conn_error);
					handler.sendMessage(message);
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	/**
	 * 初始化进度条弹窗
	 */
	private void initProgressDialog() {
		if (downloadProgressDialog == null) {
			downloadProgressDialog = new ProgressDialog(mContext);
			downloadProgressDialog = new ProgressDialog(mContext);
			downloadProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			downloadProgressDialog.setMessage(mContext.getResources().getString(R.string.download_update));
			downloadProgressDialog.setCancelable(updateInfo.upgradeRequired());
			downloadProgressDialog.setCancelable(false);
			downloadProgressDialog.setCanceledOnTouchOutside(false);
		}
		downloadProgressDialog.show();
	}
	
	private void installApp(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				UpgradeManager.GetInstance().stopAndDeleteFiles();
			}
		}).start();
    	File dir = new File(apkPath, apkName);
        if (!dir.exists()) {  
        	Toast.makeText(mContext, "找不到安装文件", Toast.LENGTH_SHORT).show();
        	return;
        }
        UpgradeManager.GetInstance().install(mContext, dir);
	}
	
	private void doExit() {
		new Thread(){
			public void run() {
				ProxyManager.GetInstance().exitProxy();
			};
		}.start();
		// android.os.Process.killProcess(android.os.Process.myPid());
		((Activity)mContext).finish();
	}
}
