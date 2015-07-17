package com.voole.epg.cooperation;

import java.io.File;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;

import com.voole.epg.Config;
import com.voole.epg.LauncherApplication;
import com.voole.epg.R;
import com.voole.epg.base.common.TVAlertDialog;
import com.voole.epg.corelib.model.proxy.ProxyManager;
import com.voole.tvutils.DeviceUtil;
import com.voole.tvutils.FileUtil;

public class MagicTVManager {
	private static final String MAGIC_TV_PACKAGE_NAME = "com.voole.magictv";
	private static final String MAGIC_TV_CLASS_NAME = "com.voole.magictv.view.PlayerActivity";
	private static boolean isDownLoading = false;
	public static void startMagicTV(Context context){
		new Thread(){
			public void run() {
				ProxyManager.GetInstance().exitProxy();
			};
		}.start();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		ComponentName comp = new ComponentName(MAGIC_TV_PACKAGE_NAME, MAGIC_TV_CLASS_NAME);
		intent.setComponent(comp);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}
	
	public static void downloadMagicTV(final Context context){
		if (isDownLoading) {
			Toast.makeText(LauncherApplication.GetInstance().getApplicationContext(), "应用正在下载中", Toast.LENGTH_LONG).show();
			return;
		}
		new TVAlertDialog.Builder(context)
		.setCancelable(false)
		.setTitle(R.string.common_download_tv)
		.setPositiveButton(R.string.common_ok_button,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						isDownLoading = true;
						Toast.makeText(LauncherApplication.GetInstance().getApplicationContext(), "应用已开始下载,下载完毕会提示安装.", Toast.LENGTH_LONG).show();
						new Thread(new Runnable() {
							@Override
							public void run() {
								Looper.prepare();
								String filePath = LauncherApplication.GetInstance().getFilePath();
								String apkName = "MagicTV.apk";
								File tmpFile = new File(filePath, apkName);
								if (tmpFile.exists()) {
									tmpFile.delete();
								}
								int downloadResult = FileUtil.downLoadFile(Config.GetInstance().getDownloadUrl(), filePath, apkName);
								if(downloadResult == 1 || downloadResult == 0){
									Toast.makeText(LauncherApplication.GetInstance().getApplicationContext(), "下载完成", Toast.LENGTH_SHORT).show();
									DeviceUtil.setFileToPermission(filePath + File.separator + apkName);
									SystemClock.sleep(1500);
									File apkFile = new File(filePath + File.separator + apkName);
									DeviceUtil.installApk(LauncherApplication.GetInstance().getApplicationContext(), apkFile);
								}else{
									Toast.makeText(LauncherApplication.GetInstance().getApplicationContext(), "下载失败", Toast.LENGTH_SHORT).show();
								}
								isDownLoading = false;
								Looper.loop();
							}
						}).start();
						
					}
				})
		.setNegativeButton(R.string.common_cancel_button, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int which) {
						
					}
				})
		.create().show();
	}
}
