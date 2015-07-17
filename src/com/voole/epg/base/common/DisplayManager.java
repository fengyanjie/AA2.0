package com.voole.epg.base.common;

import android.util.DisplayMetrics;

import com.voole.epg.LauncherApplication;
import com.voole.epg.corelib.model.utils.LogUtil;


public class DisplayManager {
	private static DisplayManager instance = new DisplayManager();

	private DisplayManager() {
		displayMetrics = new DisplayMetrics();
		displayMetrics = LauncherApplication.GetInstance().getApplicationContext().getResources().getDisplayMetrics();
		screenWidth = displayMetrics.widthPixels;
		screenHeight = displayMetrics.heightPixels;
		screenDpi = displayMetrics.densityDpi;
		
		LogUtil.d("dddddddddddddddd:"+screenWidth+"x"+screenHeight+":"+screenDpi);
	}
	
	public static DisplayManager GetInstance() {
		return instance;
	}
	
	private static final int STANDARD_WIDTH = 1280;
	private static final int STANDARD_HEIGHT = 720;
	private static final int STANDARD_DENSITY = 160;
	
	private int screenWidth = 0;
	private int screenHeight = 0;
	private int screenDpi = 0;
	private DisplayMetrics displayMetrics;
	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public int getScreenDpi() {
		return screenDpi;
	}
	public int changeTextSize(int size){
//		return size;
		float rate_density = (float) STANDARD_DENSITY / screenDpi;
		float value = displayMetrics.scaledDensity;
		System.out.println("value  "+value + "---density-->" + rate_density);
//		return (int) (size*rate_density);
		return (int) (size*rate_density);
	}
	
	public int changeTextSize(int size ,boolean isPX){
		if (isPX) {
			float rate_density = (float) STANDARD_DENSITY / screenDpi;
			float value = displayMetrics.scaledDensity;
			System.out.println("value  "+value + "---density-->" + rate_density);
//			return (int) (size*rate_density);
			return (int) (size*rate_density);
		}else {
			return size;
		}
	}
	
	public int changeWidthSize(int size){
		float rate = (float) screenWidth / STANDARD_WIDTH;
		return (int)(size * rate);
	}
	
	public int changeHeightSize(int size){
		float rate = (float) screenHeight / STANDARD_HEIGHT;
		return (int)(size * rate);
	}
}
