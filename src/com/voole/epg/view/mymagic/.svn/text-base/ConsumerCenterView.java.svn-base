package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.corelib.model.account.ConsumeListInfo;
import com.voole.epg.corelib.model.account.OrderListInfo;
import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.corelib.model.account.RechargeListInfo;
import com.voole.epg.corelib.model.account.RechargeResult;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.view.mymagic.OrderView.GotoRechargeListener;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemSelectedListener;
import com.voole.epg.view.mymagic.UserCenterLeftView.UserCenterLeftItemView;
import com.voole.tvutils.BitmapUtil;

public class ConsumerCenterView extends BaseLinearLayout{
	
	private static final int RECHARGE = 0;
	private static final int ORDER = 1;
	private static final int ORDER_HISTORY = 2;
	private static final int RECHARGE_HISTORY = 3;
	
	private UserCenterLeftView leftView = null;
	private OrderView orderView = null;               
	private OrderHistoryView orderHistoryView = null;
	private RechargeView rechargeView = null;
	private RechargeHistoryView rechargeHistoryView = null;
	
	
	public void showAli(){
		if(rechargeView!=null){
			rechargeView.showAli();
		}
	}
	
	private LinearLayout layout_right = null;
	
	private static final String[] itemNames = { 
		"我要充值",
	    "我要订购",
		"订购记录", 
		"充值记录"};

	public ConsumerCenterView(Context context) {
		super(context);
		init(context);
	}
	
	public ConsumerCenterView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public ConsumerCenterView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public void setOrderListener(OrderListener listener){
		if (listener != null) {
			orderView.setOrderListener(listener);
		}
	}
	
	public void setGotoRechargeListener(GotoRechargeListener listener){
		if (listener != null) {
			orderView.setGotoRechargeListener(listener);
		}
	}
	
	public void setRechargeListener(RechargeListener listener){
		if (listener != null) {
			rechargeView.setRechargeListener(listener);
		}
	}
	
	private ConsumerCenterUpdataListner listner = null;
	public void setConsumerCenterUpdataListner(ConsumerCenterUpdataListner listner){
		this.listner = listner;
	}
	
	private void init(Context context){
		setOrientation(HORIZONTAL);
		leftView = new UserCenterLeftView(context);
		leftView.setId(ID.MyMagicActivity.CC_LEFT_VIEW_NAVIGATION_ID);
		leftView.setData(itemNames);
		LinearLayout.LayoutParams param_left = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,43);
		param_left.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(35);
		param_left.bottomMargin = DisplayManager.GetInstance().changeHeightSize(55);
		leftView.setLayoutParams(param_left);
		addView(leftView);
		
		layout_right = new LinearLayout(context);
		LinearLayout.LayoutParams param_right = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,10);
		param_right.rightMargin = DisplayManager.GetInstance().changeWidthSize(
				30);
		param_right.bottomMargin = DisplayManager.GetInstance().changeHeightSize(55);
		BitmapDrawable drawable = new BitmapDrawable(getResources(),BitmapUtil.readBitmap(context, R.drawable.cs_mymagic_consumer_right_bg));
		layout_right.setBackgroundDrawable(drawable);
		layout_right.setLayoutParams(param_right);
		
		orderView = new OrderView(context);
		LinearLayout.LayoutParams param_orderView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_orderView.topMargin = 15;
		param_orderView.leftMargin = 20;
		param_orderView.rightMargin = 30;
		orderView.setLayoutParams(param_orderView);
		orderView.setGotoRechargeListener(new GotoRechargeListener() {
			@Override
			public void gotoRecharge() {
				gotoRechargeView();
			}
		});
		layout_right.addView(orderView);
		
		orderHistoryView = new OrderHistoryView(context);
		LinearLayout.LayoutParams param_historyView = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		param_historyView.topMargin = 15;
		orderHistoryView.setLayoutParams(param_historyView);
		layout_right.addView(orderHistoryView);
		
		rechargeView = new RechargeView(context);
		layout_right.addView(rechargeView);
		
		rechargeHistoryView = new RechargeHistoryView(context);
		rechargeHistoryView.setLayoutParams(param_historyView);
		layout_right.addView(rechargeHistoryView);
		
		addView(layout_right);
		showRightView(RECHARGE);
		
		addViewListener();
	}
	
	public void gotoRechargeView(){
		setLeftViewPosition(RECHARGE);
		leftView.requestFocus();
	}
	
	public void gotoOrderView(){
		setLeftViewPosition(ORDER);
		leftView.requestFocus();
	}
	
	private void addViewListener(){
		if (leftView != null) {
			leftView.setUserCenterLeftItemSelectedListener(new UserCenterLeftItemSelectedListener() {
				@Override
				public void onItemSelected(
						UserCenterLeftItemView leftItemView) {
					int currentPosition = (Integer)leftItemView.getTag();
					showRightView(currentPosition);
					if (listner != null) {
						listner.getData(currentPosition);
					}
				}
			});
		}
	}
	
	public void setLeftViewPosition(int currentSelected){
		leftView.setSelectedIndex(currentSelected);
		showRightView(currentSelected);
	}
	
	public void setOrderViewProductData(List<Product> products){
		orderView.setProductDate(products);
//		leftView.requestFocus();
	}
	
	public void setOrderViewUserInfo(OrderListInfo orderListInfo){
		orderView.setOrderInfo(orderListInfo);
		rechargeView.setMoney(orderListInfo);
	}
	
	/*public void updateOrderResultUI(Product product){
		orderView.updateOrderResultUI(product);
	}*/
	
	public void setOrderHistoryViewData(ConsumeListInfo consumeListInfo){
		orderHistoryView.setDate(consumeListInfo);
	}
	
	public void setRechargeViewData(RechargeResult rechargeResult){
		rechargeView.setData(rechargeResult);
	}
	
	public void setRechargeViewData(String uid){
		rechargeView.setData(uid);
	}
	
	public void setRechargeHistoryViewData(RechargeListInfo rechargeListInfo){
		rechargeHistoryView.setDate(rechargeListInfo);
	}
	
	private void showRightView(int currentPosition){
		switch (currentPosition) {
		case RECHARGE:
			showRechargeView();
			leftView.setNextFocusRightId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ID);
			break;
		case ORDER:
			showOrderView();
			break;
		case ORDER_HISTORY:
			showOrderHistoryView();
			break;
		case RECHARGE_HISTORY:
			showRechargeHistoryView();
			break;
		default:
			break;
		}
	}
	
	private void showOrderView(){
		/*orderView.setVisibility(VISIBLE);
		orderHistoryView.setVisibility(GONE);
		rechargeView.setVisibility(GONE);
		rechargeHistoryView.setVisibility(GONE);*/
		layout_right.removeAllViews();
		if (orderView == null) {
			orderView = new OrderView(getContext());
			orderView.setGotoRechargeListener(new GotoRechargeListener() {
				@Override
				public void gotoRecharge() {
					setLeftViewPosition(RECHARGE);
				}
			});
		}
		layout_right.addView(orderView);
	}
	
	private void showOrderHistoryView(){
		/*orderHistoryView.setVisibility(VISIBLE);
		orderView.setVisibility(GONE);
		rechargeView.setVisibility(GONE);
		rechargeHistoryView.setVisibility(GONE);*/
		layout_right.removeAllViews();
		if (orderHistoryView == null) {
			orderHistoryView = new OrderHistoryView(getContext());
		}
		layout_right.addView(orderHistoryView);
	}
	
	private void showRechargeView(){
		/*rechargeView.setVisibility(VISIBLE);
		orderView.setVisibility(GONE);
		orderHistoryView.setVisibility(GONE);
		rechargeHistoryView.setVisibility(GONE);*/
		layout_right.removeAllViews();
		if (rechargeView == null) {
			rechargeView = new RechargeView(getContext());
		}
		if (rechargeView.getUid() == null || "".equals(rechargeView.getUid())) {
			if (AuthManager.GetInstance().getUser() != null && AuthManager.GetInstance().getUser().getUid() != null) {
				rechargeView.setData(AuthManager.GetInstance().getUser().getUid());
			}
		}
		layout_right.addView(rechargeView);
	}
	
	private void showRechargeHistoryView(){
		/*rechargeHistoryView.setVisibility(VISIBLE);
		orderView.setVisibility(GONE);
		orderHistoryView.setVisibility(GONE);
		rechargeView.setVisibility(GONE);*/
		layout_right.removeAllViews();
		if (rechargeHistoryView == null) {
			rechargeHistoryView = new RechargeHistoryView(getContext());
		}
		layout_right.addView(rechargeHistoryView);
	}
	
	public void requestLeftView(){
		leftView.requestFocus();
	}
	
	public interface ConsumerCenterUpdataListner{
		public void getData(int currentPosition);
	}
	
}

