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
import com.voole.epg.corelib.model.account.ConsumeInfo;
import com.voole.epg.corelib.model.account.ConsumeListInfo;

public class OrderHistoryView extends BaseLinearLayout{
	
	private static final int textSize = DisplayManager.GetInstance().changeTextSize(28);
	
	private static final int ITEM_SIZE = 6;
	private OrderHistoryItemView[] itemViews = null;
	private PageNavigator pageNavigator = null;
	
	private List<ConsumeInfo> consumeInfos = null; //数据类型
	
	private int currentPage = 0;
	private int totalPage = 0;
	private int totalSize = 0;
	private List<ConsumeInfo> currentConsumeInfos = null;
	
	public OrderHistoryView(Context context) {
		super(context);
		initTitle(context);
		initList(context);
		initPage(context);
	}
	
	/**
	 * left to right 3:1:1:2:4
	 * up to down 1:6:1
	 * @param context
	 */
	private void initTitle(Context context){
		setOrientation(VERTICAL);
		LinearLayout layout_title = new LinearLayout(context);
		layout_title.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams param_layout_title = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,7);
		param_layout_title.topMargin = 15;
		param_layout_title.leftMargin = 15;
		param_layout_title.rightMargin = 15;
		layout_title.setLayoutParams(param_layout_title);
		
		TextView orderId_title = new TextView(context);
		orderId_title.setText("订单流水号");
		orderId_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		orderId_title.setTextColor(Color.WHITE);
		orderId_title.setGravity(Gravity.CENTER);
		orderId_title.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_orderId_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 9);
		orderId_title.setLayoutParams(param_orderId_title);
		param_orderId_title.leftMargin = 5;
		layout_title.addView(orderId_title);
		
		TextView orderType_title = new TextView(context);
		orderType_title.setText("类型");
		orderType_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		orderType_title.setTextColor(Color.WHITE);
		orderType_title.setGravity(Gravity.CENTER);
		orderType_title.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_orderType_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 10);
		orderType_title.setLayoutParams(param_orderType_title);
		param_orderType_title.leftMargin = 5;
		layout_title.addView(orderType_title);
		
		TextView orderPrice_title = new TextView(context);
		orderPrice_title.setText("价格");
		orderPrice_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		orderPrice_title.setTextColor(Color.WHITE);
		orderPrice_title.setGravity(Gravity.CENTER);
		orderPrice_title.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_orderPrice_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 10);
		orderPrice_title.setLayoutParams(param_orderPrice_title);
		param_orderPrice_title.leftMargin = 5;
		layout_title.addView(orderPrice_title);
		
		TextView orderTime_title = new TextView(context);
		orderTime_title.setText("下单时间");
		orderTime_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		orderTime_title.setTextColor(Color.WHITE);
		orderTime_title.setGravity(Gravity.CENTER);
		orderTime_title.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_orderTime_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 8);
		orderTime_title.setLayoutParams(param_orderTime_title);
		param_orderTime_title.leftMargin = 5;
		layout_title.addView(orderTime_title);
		
		TextView productTime_title = new TextView(context);
		productTime_title.setText("服务时间");
		productTime_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
		productTime_title.setTextColor(Color.WHITE);
		productTime_title.setGravity(Gravity.CENTER);
		productTime_title.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		LinearLayout.LayoutParams param_productTime_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
		productTime_title.setLayoutParams(param_productTime_title);
		param_productTime_title.leftMargin = 5;
		layout_title.addView(productTime_title);
		addView(layout_title);
	}
	
	private void initList(Context context){
		LinearLayout layout_content = new LinearLayout(context);
		layout_content.setOrientation(LinearLayout.VERTICAL);
		LinearLayout.LayoutParams param_layout_content = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
				LayoutParams.MATCH_PARENT,2);
		param_layout_content.leftMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_content.rightMargin = DisplayManager.GetInstance().changeWidthSize(15);
		param_layout_content.topMargin = DisplayManager.GetInstance().changeWidthSize(10);
		param_layout_content.bottomMargin = DisplayManager.GetInstance().changeWidthSize(5);
		layout_content.setLayoutParams(param_layout_content);
		layout_content.setNextFocusDownId(1003);
		itemViews = new OrderHistoryItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_content_item = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, 
				LayoutParams.FILL_PARENT, 1);
		param_content_item.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		param_content_item.gravity = Gravity.CENTER;
		for(int i = 0; i < ITEM_SIZE; i++){
			itemViews[i] = new OrderHistoryItemView(context);
			itemViews[i].setLayoutParams(param_content_item);
			layout_content.addView(itemViews[i]);
		}
		addView(layout_content);
	}
	
	private void initPage(Context context){
		pageNavigator = new PageNavigator(context);
		pageNavigator.setVisibility(View.INVISIBLE);
		pageNavigator.setPageNavigatorListener(new PageNavigatorListener() {
			@Override
			public void onGotoPage(int pageNo) {
				setListDate(pageNo);
				pageNavigator.setPageInfo(pageNo, totalPage);
			}
		});
		LinearLayout.LayoutParams param_pageNavigator = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,7);
		param_pageNavigator.rightMargin = DisplayManager.GetInstance().changeWidthSize(20);
		param_pageNavigator.bottomMargin = DisplayManager.GetInstance().changeHeightSize(20);
		pageNavigator.setLayoutParams(param_pageNavigator);
		addView(pageNavigator);
	}
	
	/**
	 * 加载数据
	 * @param 
	 */
	public void setDate(ConsumeListInfo consumeListInfo){
		this.consumeInfos = consumeListInfo.getConsumeList();
		
		if(consumeInfos != null && consumeInfos.size() > 0){
			initPageInfo(consumeInfos.size());
			setListDate(currentPage);
			pageNavigator.setVisibility(View.VISIBLE);
		}else{
			pageNavigator.setVisibility(View.INVISIBLE);
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
	
	private void getCurrentConsumeList(){
		int startPosition =(currentPage - 1)*ITEM_SIZE;
		int endPosition;
		if (totalSize > currentPage * ITEM_SIZE) {
			endPosition = currentPage*ITEM_SIZE;
		}else {
			endPosition = totalSize;
		}
		currentConsumeInfos = consumeInfos.subList(startPosition, endPosition);
	}
	
	/**
	 * 填充数据
	 */
	private void setListDate(int pageNo){
		currentPage = pageNo;
		getCurrentConsumeList();
		if(currentConsumeInfos != null && currentConsumeInfos.size() > 0){
			int itemSize = currentConsumeInfos.size();
			for(int i = 0; i < ITEM_SIZE; i++){
				if(i < itemSize){
					itemViews[i].setVisibility(View.VISIBLE);
					itemViews[i].setOrderId(currentConsumeInfos.get(i).getSeqno());
					itemViews[i].setOrderType(currentConsumeInfos.get(i).getType());
					itemViews[i].setOrderPrice("￥" + Integer.parseInt(currentConsumeInfos.get(i).getPrice())/100);
					String orderTime = currentConsumeInfos.get(i).getOrderTime();
					orderTime = orderTime.substring(0, orderTime.lastIndexOf("/"));
					itemViews[i].setOrderTime(orderTime);
					String startTime = currentConsumeInfos.get(i).getServiceStartTime();
					String stopTime = currentConsumeInfos.get(i).getServiceEndTime();
					if(startTime!=null&&!"".equals(startTime)&&stopTime!=null&&!"".equals(stopTime)){
						startTime = startTime.substring(0, startTime.lastIndexOf("/"));
						stopTime = stopTime.substring(0, stopTime.lastIndexOf("/"));
						itemViews[i].setProductTime(startTime + "——" + stopTime);
					}else{
						itemViews[i].setProductTime("未获取到服务时间");
					}
				}else{
					itemViews[i].setVisibility(View.INVISIBLE);
				}
			}
		}
	}
	
	public void previousPage(){
		if (currentPage > 1) {
			currentPage--;
			setListDate(currentPage);
		}
	}
	
	public void nextPage(){
		if (currentPage < totalPage) {
			currentPage++;
			setListDate(currentPage);
		}
	}
	
	/**
	 * 
	 * @author Administrator
	 *
	 */
	static class OrderHistoryItemView extends BaseLinearLayout{

		private AlwaysMarqueeTextView orderId = null;
		private AlwaysMarqueeTextView orderType = null;
		private AlwaysMarqueeTextView orderPrice = null;
		private AlwaysMarqueeTextView orderTime = null;
		private AlwaysMarqueeTextView productTime = null;
		
		private final int textSize = DisplayManager.GetInstance().changeTextSize(28);
		private final int textColor = Color.GRAY;
		
		public OrderHistoryItemView(Context context) {
			super(context);
			setOrientation(HORIZONTAL);
			setGravity(Gravity.CENTER_VERTICAL);
			init(context);
		}
		
		/**
		 * left to right 2:1:1:3:4
		 * @param context
		 */
		private void init(Context context){
			orderId = new AlwaysMarqueeTextView(context);
			orderId.setSingleLine(true);
			orderId.setHorizontallyScrolling(true);
			orderId.setEllipsize(TruncateAt.MARQUEE);
			orderId.setMarqueeRepeatLimit(-1);
			orderId.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			orderId.setTextColor(textColor);
			orderId.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_orderId = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 9);
			param_orderId.leftMargin = 5;
			orderId.setLayoutParams(param_orderId);
			addView(orderId);
			
			orderType = new AlwaysMarqueeTextView(context);
			orderType.setSingleLine(true);
			orderType.setHorizontallyScrolling(true);
			orderType.setEllipsize(TruncateAt.MARQUEE);
			orderType.setMarqueeRepeatLimit(-1);
			orderType.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			orderType.setTextColor(textColor);
			orderType.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_orderType = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 10);
			param_orderType.leftMargin = 5;
			orderType.setLayoutParams(param_orderType);
			addView(orderType);
			
			orderPrice = new AlwaysMarqueeTextView(context);
			orderPrice.setSingleLine(true);
			orderPrice.setHorizontallyScrolling(true);
			orderPrice.setEllipsize(TruncateAt.MARQUEE);
			orderPrice.setMarqueeRepeatLimit(-1);
			orderPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			orderPrice.setTextColor(textColor);
			orderPrice.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_orderPrice = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 10);
			param_orderPrice.leftMargin = 5;
			orderPrice.setLayoutParams(param_orderPrice);
			addView(orderPrice);
			
			orderTime = new AlwaysMarqueeTextView(context);
			orderTime.setSingleLine(true);
			orderTime.setHorizontallyScrolling(true);
			orderTime.setEllipsize(TruncateAt.MARQUEE);
			orderTime.setMarqueeRepeatLimit(-1);
			orderTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			orderTime.setTextColor(textColor);
			orderTime.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_orderTime = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 8);
			param_orderTime.leftMargin = 5;
			orderTime.setLayoutParams(param_orderTime);
			addView(orderTime);
			
			productTime = new AlwaysMarqueeTextView(context);
			productTime.setSingleLine(true);
			productTime.setHorizontallyScrolling(true);
			productTime.setEllipsize(TruncateAt.MARQUEE);
			productTime.setMarqueeRepeatLimit(-1);
			productTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			productTime.setTextColor(textColor);
			productTime.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_productTime = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 7);
			param_productTime.leftMargin = 5;
			productTime.setLayoutParams(param_productTime);
			addView(productTime);
		}
		
		public void setOrderId(String str){
			orderId.setText(str);
		}
		
		public void setOrderType(String str){
			orderType.setText(str);
		}
		
		public void setOrderPrice(String str){
			orderPrice.setText(str);
		}
		
		public void setOrderTime(String str){
			orderTime.setText(str);
		}
		
		public void setProductTime(String str){
			productTime.setText(str);
		}
		
	}
	
}
