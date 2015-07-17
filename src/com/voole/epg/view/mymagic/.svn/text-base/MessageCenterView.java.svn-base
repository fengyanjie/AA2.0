package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.account.MessageInfo;
import com.voole.epg.corelib.model.account.MessageInfoResult;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.mymagic.MessageEmailView.EmailButtonListener;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemSelectedListener;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemView;
import com.voole.tvutils.BitmapUtil;

public class MessageCenterView extends BaseLinearLayout{
	
	private static final int NOTIFICATION = 0;
	private static final int ABOUTMAGIC = 1;
	
	private UserCenterLeftView leftView = null;   //left navigation
	
	private MessageNotificationView notificationView = null;
	private MessageEmailView messageEmailView = null;
	private MessageAboutView aboutView = null;
	
	
	public MessageEmailView getMessageEmailView() {
		return messageEmailView;
	}

	private static final int[] itemNames = {
		R.string.mymagic_mc_notification,
		R.string.mymagic_mc_recharge_aboutmagic,
	};
	
	public MessageCenterView(Context context) {
		super(context);
		init(context);
	}
	
	public MessageCenterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public MessageCenterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	private void init(Context context){
		setOrientation(HORIZONTAL);
		leftView = new UserCenterLeftView(context,2);
		leftView.setData(itemNames);
		leftView.setId(ID.MyMagicActivity.MM_LEFT_VIEW_NAVIGATION_ID);
		LinearLayout.LayoutParams param_left = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,43);
		param_left.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(35);
		param_left.bottomMargin = DisplayManager.GetInstance().changeHeightSize(55);
		leftView.setLayoutParams(param_left);
		addView(leftView);
		
		RelativeLayout layout_right = new RelativeLayout(context);
		LinearLayout.LayoutParams param_right = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,10);
		param_right.rightMargin = DisplayManager.GetInstance().changeWidthSize(30);
		param_right.bottomMargin = DisplayManager.GetInstance().changeHeightSize(55);
		BitmapDrawable drawable = new BitmapDrawable(getResources(),BitmapUtil.readBitmap(context, R.drawable.cs_mymagic_consumer_right_bg));
		layout_right.setBackgroundDrawable(drawable);
		layout_right.setLayoutParams(param_right);
		
		notificationView = new MessageNotificationView(context);
		RelativeLayout.LayoutParams param_notificationView = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_notificationView.leftMargin = 5;
		param_notificationView.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_notificationView.topMargin = DisplayManager.GetInstance().changeHeightSize(20);
		notificationView.setLayoutParams(param_notificationView);
		layout_right.addView(notificationView);
		
		messageEmailView = new MessageEmailView(context);
		layout_right.addView(messageEmailView);
		
		aboutView = new MessageAboutView(context);
		layout_right.addView(aboutView);
		
		addView(layout_right);
		showRightView(NOTIFICATION);
		addViewListener();
	}
	
	private void addViewListener(){
		if (leftView != null) {
			leftView.setUserCenterLeftItemSelectedListener(new UserCenterLeftItemSelectedListener() {
				@Override
				public void onItemSelected(UserCenterLeftItemView leftItemView) {
					int currentPosition = (Integer)leftItemView.getTag();
					showRightView(currentPosition);
				}
			});
		}
		if (notificationView != null) {
			notificationView.setMessageNotifcationItemListener(new MessageNotificationItemListener() {
				@Override
				public void onKey(List<MessageInfo> list,MessageInfo info) {
					showEmailView(list,info);
				}
			});
		}
		if (messageEmailView != null) {
			messageEmailView.setEmailButtonListener(new EmailButtonListener() {
				@Override
				public void previous(List<MessageInfo> list,MessageInfo info) {
					int index=list.indexOf(info);
					if(index-1>=0){
						messageEmailView.setData(list,list.get(index-1));
					}
				}
				
				@Override
				public void next(List<MessageInfo> list,MessageInfo info) {
					int len=list.size();
					int index=list.indexOf(info);
					if(index+1<=len-1){
						messageEmailView.setData(list,list.get(index+1));
					}
				}
				
				@Override
				public void back() {
					hideEmailView();
				}
			});
		}
	}
	
	public void setLeftViewPosition(int currentSelected){
		leftView.setSelectedIndex(currentSelected);
		showRightView(currentSelected);
	}
	
	private void showRightView(int currentPosition){
		switch (currentPosition) {
		case NOTIFICATION:
			showNotificationView();
			leftView.setNextFocusRightId(ID.MyMagicActivity.MC_MESSAGELIST_FIRST_ID);
			break;
		case ABOUTMAGIC:
			showAboutView(); 
			leftView.setNextFocusRightId(R.id.srcoll);
			break;
		default:
			break;
		}
	}
	
	public void setNotificationViewData(MessageInfoResult result){
		notificationView.setData(result.getList());
	}
	
	public void showNotificationView(){
		notificationView.setVisibility(VISIBLE);
		aboutView.setVisibility(GONE);
		messageEmailView.setVisibility(GONE);
		leftView.setNextFocusRightId(ID.MyMagicActivity.MC_MESSAGELIST_FIRST_ID);
	}
	
	private void showAboutView(){
		aboutView.setVisibility(VISIBLE);
		notificationView.setVisibility(GONE);
		messageEmailView.setVisibility(GONE);
	}
	
	private void showEmailView(List<MessageInfo> list,MessageInfo info){
		messageEmailView.setData(list,info);
		messageEmailView.setVisibility(VISIBLE);
		notificationView.setVisibility(GONE);
		aboutView.setVisibility(GONE);
		messageEmailView.requestFocus();
		leftView.setNextFocusRightId(ID.MyMagicActivity.MC_MESSAGEDETAIL_PRE_BTN_ID);
	}
	
	public void hideEmailView(){
		notificationView.setVisibility(VISIBLE);
		notificationView.requestFocus();
		messageEmailView.setVisibility(GONE);
		aboutView.setVisibility(GONE);
		leftView.setNextFocusRightId(ID.MyMagicActivity.MC_MESSAGELIST_FIRST_ID);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		LogUtil.d("MessageCenterView onKeyDown 1");
		if(keyCode==KeyEvent.KEYCODE_BACK&&messageEmailView.getVisibility()==View.GONE){
			LogUtil.d("MessageCenterView onKeyDown 2");
				//showNotificationView();
			hideEmailView();
				return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
