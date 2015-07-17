package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.corelib.model.account.RechargeInfo;
import com.voole.epg.corelib.model.account.RechargeListInfo;
import com.voole.epg.corelib.model.utils.LogUtil;

public class RechargeHistoryView extends BaseLinearLayout{
	private static final int textSize = DisplayManager.GetInstance().changeTextSize(28);
	private static final int ITEM_SIZE = 6;
	private RechargeHistoryItemView[] itemViews = null;
	private PageNavigator pageNavigator = null;
	
    private List<RechargeInfo> rechargeInfos = null; //数据类型
	
	private int currentPage = 0;
	private int totalPage = 0;
	private int totalSize = 0;
	private static List<RechargeInfo> currentRechargeInfos = null;
	
	public RechargeHistoryView(Context context) {
		super(context);
		initTitle(context);
		initHistoryList(context);
		initPage(context);
	}
	
	/**
	 * left to right 
	 * up to down 1:6:1
	 * @param context
	 */
	private void initTitle(Context context){
		setOrientation(VERTICAL);
		LinearLayout layout_title = new LinearLayout(context);
		layout_title.setOrientation(LinearLayout.HORIZONTAL);
		layout_title.setId(1001);
		LinearLayout.LayoutParams param_layout_title = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,7);
		param_layout_title.topMargin = DisplayManager.GetInstance().changeHeightSize(15);
		param_layout_title.leftMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_title.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		layout_title.setLayoutParams(param_layout_title);
		
		TextView title_id = new TextView(context);
		title_id.setText("订单流水号");
		title_id.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		title_id.setTextColor(Color.WHITE);
		title_id.setGravity(Gravity.CENTER);
		title_id.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_title_id = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		title_id.setLayoutParams(param_title_id);
		param_title_id.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		layout_title.addView(title_id);
		
		TextView title_time = new TextView(context);
		title_time.setText("时间");
		title_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		title_time.setTextColor(Color.WHITE);
		title_time.setGravity(Gravity.CENTER);
		title_time.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_title_time = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		title_time.setLayoutParams(param_title_time);
		param_title_time.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		layout_title.addView(title_time);
		
		TextView title_money = new TextView(context);
		title_money.setText("充值金额");
		title_money.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		title_money.setTextColor(Color.WHITE);
		title_money.setGravity(Gravity.CENTER);
		title_money.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_title_money = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		title_money.setLayoutParams(param_title_money);
		param_title_money.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		layout_title.addView(title_money);
		
		TextView title_type = new TextView(context);
		title_type.setText("充值状态");
		title_type.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		title_type.setTextColor(Color.WHITE);
		title_type.setGravity(Gravity.CENTER);
		title_type.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_title_type = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		title_type.setLayoutParams(param_title_type);
		param_title_type.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		layout_title.addView(title_type);
		addView(layout_title);
	}
	
	private void initHistoryList(Context context){
		LinearLayout layout_history_list = new LinearLayout(context);
		layout_history_list.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams param_layout_history_list = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,2);
		param_layout_history_list.leftMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_history_list.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_history_list.topMargin = DisplayManager.GetInstance().changeWidthSize(10);
		layout_history_list.setLayoutParams(param_layout_history_list);
		
		itemViews = new RechargeHistoryItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_content_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,1);
		param_content_item.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		param_content_item.gravity = Gravity.CENTER;
		for(int i = 0; i < ITEM_SIZE; i++){
			itemViews[i] = new RechargeHistoryItemView(context);
			itemViews[i].setLayoutParams(param_content_item);
			layout_history_list.addView(itemViews[i]);
		}
		addView(layout_history_list);
	}
	
	private void initPage(Context context){
		pageNavigator = new PageNavigator(context);
		pageNavigator.setVisibility(View.INVISIBLE);
		pageNavigator.setPageNavigatorListener(new PageNavigatorListener() {
			@Override
			public void onGotoPage(int pageNo) {
				setListData(pageNo);
				pageNavigator.setPageInfo(pageNo, totalPage);
			}
		});
		pageNavigator.setId(1003);
		LinearLayout.LayoutParams param_pageNavigator = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,7);
		param_pageNavigator.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		param_pageNavigator.bottomMargin = DisplayManager.GetInstance().changeHeightSize(20);
		pageNavigator.setLayoutParams(param_pageNavigator);
		addView(pageNavigator);
	}
	
	/**
	 * 加载数据
	 */
	public void setDate(RechargeListInfo rechargeListInfo){
		this.rechargeInfos = rechargeListInfo.getRechargelist();
		//填充数据
		if (rechargeInfos != null && rechargeInfos.size() > 0) {
			initPageInfo(rechargeInfos.size());
			setListData(currentPage);
			pageNavigator.setVisibility(View.VISIBLE);
		}else {
			pageNavigator.setVisibility(INVISIBLE);
		}
	}
	
	private void initPageInfo(int totalSize){
		currentPage = 1;
		this.totalSize = totalSize;
		if(totalSize % ITEM_SIZE > 0){
			this.totalPage = totalSize / ITEM_SIZE + 1;
		}else{
			this.totalPage = totalSize / ITEM_SIZE ;
		}
		pageNavigator.setPageInfo(currentPage, totalPage);
	}
	
	private void getCurrentRechargeList(){
		int startPosition =(currentPage - 1)*ITEM_SIZE;
		int endPosition;
		if (totalSize > currentPage * ITEM_SIZE) {
//			endPosition = currentPage*ITEM_SIZE - 1;
			endPosition = currentPage*ITEM_SIZE;
		}else {
//			endPosition = totalSize - 1;
			endPosition = totalSize;
		}
		currentRechargeInfos = rechargeInfos.subList(startPosition, endPosition);
		LogUtil.d("totalSize--->" + totalSize + "---totalPage--->" + totalPage + "---currentPage---->" + currentPage
				+ "----startPosition-->" + startPosition + "---endPosition--->" + endPosition + "currentRechargeInfos---Size===" + currentRechargeInfos.size());
	}
	
	private void setListData(int pageNo){
		currentPage = pageNo;
		getCurrentRechargeList();
		if (currentRechargeInfos != null && currentRechargeInfos.size() > 0) {
			for (int i = 0; i < ITEM_SIZE; i++) {
				if (i < currentRechargeInfos.size()) {
					itemViews[i].setVisibility(VISIBLE);
					itemViews[i].setRechargeId(currentRechargeInfos.get(i).getCardno());
					String creatTime = currentRechargeInfos.get(i).getCreatetime();
					creatTime = creatTime.substring(0, creatTime.indexOf("/"));
					itemViews[i].setRechargeTime(creatTime);
					itemViews[i].setRechargePrice("￥" + Integer.parseInt(currentRechargeInfos.get(i).getMoney())/100);
					if("1".equals(currentRechargeInfos.get(i).getStatus())){
						itemViews[i].setRechargeType("成功");
					}else{
						itemViews[i].setRechargeType("失败");
					}
					
				}else {
					itemViews[i].setVisibility(View.INVISIBLE);
				}
			}
		}
	}
	
	static class RechargeHistoryItemView extends BaseLinearLayout{
		private AlwaysMarqueeTextView rechargeId = null;
		private AlwaysMarqueeTextView rechargeTime = null;
		private AlwaysMarqueeTextView rechargePrice = null;
		private AlwaysMarqueeTextView rechargeType = null;
		
		private final int textColor = Color.GRAY;
		private final int textSize = DisplayManager.GetInstance().changeTextSize(28);

		public RechargeHistoryItemView(Context context) {
			super(context);
			setOrientation(HORIZONTAL);
			setGravity(Gravity.CENTER_VERTICAL);
			init(context);
		}
		
		private void init(Context context){
			rechargeId = new AlwaysMarqueeTextView(context);
			rechargeId.setSingleLine(true);
			rechargeId.setHorizontallyScrolling(true);
			rechargeId.setEllipsize(TruncateAt.MARQUEE);
			rechargeId.setMarqueeRepeatLimit(-1);
			rechargeId.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			rechargeId.setTextColor(textColor);
			rechargeId.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_rechargeId = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
			rechargeId.setLayoutParams(param_rechargeId);
			addView(rechargeId);
			
			rechargeTime = new AlwaysMarqueeTextView(context);
			rechargeTime.setSingleLine(true);
			rechargeTime.setHorizontallyScrolling(true);
			rechargeTime.setEllipsize(TruncateAt.MARQUEE);
			rechargeTime.setMarqueeRepeatLimit(-1);
			rechargeTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			rechargeTime.setTextColor(textColor);
			rechargeTime.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_rechargeTime = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
			rechargeTime.setLayoutParams(param_rechargeTime);
			addView(rechargeTime);
			
			rechargePrice = new AlwaysMarqueeTextView(context);
			rechargePrice.setSingleLine(true);
			rechargePrice.setHorizontallyScrolling(true);
			rechargePrice.setEllipsize(TruncateAt.MARQUEE);
			rechargePrice.setMarqueeRepeatLimit(-1);
			rechargePrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			rechargePrice.setTextColor(Color.parseColor("#00c1ea"));
			rechargePrice.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_rechargePrice = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
			rechargePrice.setLayoutParams(param_rechargePrice);
			addView(rechargePrice);
			
			rechargeType = new AlwaysMarqueeTextView(context);
			rechargeType.setSingleLine(true);
			rechargeType.setHorizontallyScrolling(true);
			rechargeType.setEllipsize(TruncateAt.MARQUEE);
			rechargeType.setMarqueeRepeatLimit(-1);
			rechargeType.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			rechargeType.setTextColor(textColor);
			rechargeType.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_rechargeType = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
			rechargeType.setLayoutParams(param_rechargeType);
			addView(rechargeType);
		}
		
		public void setRechargeId(String str){
			rechargeId.setText(str);
		}
		
		public void setRechargeType(String str){
			rechargeType.setText(str);
		}
		
		public void setRechargePrice(String str){
			rechargePrice.setText(str);
		}
		
		public void setRechargeTime(String str){
			rechargeTime.setText(str);
		}
		
		/*public void setIsFocused(boolean isFocused){
			this.isFocused = isFocused;
			updateUI();
		}*/
		
		/*public boolean isFocused(){
			return isFocused;
		}*/
		
		/*private void updateUI(){
			if (isFocused) {
				setBackgroundResource(resid);
			}else {
				setBackgroundResource(0);
			}
		}*/
	}
	
}
