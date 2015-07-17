package com.voole.epg.view.mymagic;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.common.AlwaysMarqueeTextView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.PageNavigator;
import com.voole.epg.base.common.PageNavigator.PageNavigatorListener;
import com.voole.epg.base.common.ShadeButton2;
import com.voole.epg.corelib.model.account.OrderInfo;
import com.voole.epg.corelib.model.account.OrderListInfo;
import com.voole.epg.corelib.model.account.Product;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.epg.view.mymagic.OrderView.ProductItemView.OrderStatus;

public class OrderView extends BaseLinearLayout{
	
	private TextView user_id = null;
	private TextView user_money = null;
	private TextView user_status = null;
	
	private static final int ITEM_SIZE = 4;
	private ProductItemView[] itemViews = null;
	private PageNavigator pageNavigator = null;
	private int currentFocused = -1;
	
	private List<Product> products = null;
	private List<Product> currentProducts = null;
	private OrderListInfo orderListInfo = null;
	private List<OrderInfo> orderInfos = null;
	
	private int currentPage = 0;
	private int totalPage = 0;
	private int totalSize = 0;
	
	
	private static final int TEXTSIZE = DisplayManager.GetInstance().changeTextSize(28);
	
	private OrderListener listener = null;
	public void setOrderListener(OrderListener listener){
		this.listener = listener;
	}
	private GotoRechargeListener gotoRechargeListener = null;
	public void setGotoRechargeListener(GotoRechargeListener listener){
		this.gotoRechargeListener = listener;
	}
	
	public OrderView(Context context) {
		super(context);
		init(context);
	}
	
	public OrderView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public OrderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	/**
	 * 1:1:1:4:1
	 * @param context
	 */
	private void init(Context context){
		setOrientation(VERTICAL);
		LinearLayout layout_id = new LinearLayout(context);
		LinearLayout.LayoutParams param_layout_id = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
		param_layout_id.topMargin = DisplayManager.GetInstance().changeHeightSize(15);
		param_layout_id.rightMargin = DisplayManager.GetInstance().changeWidthSize(30);
		layout_id.setLayoutParams(param_layout_id);
		user_id = new TextView(context);
		user_id.setGravity(Gravity.CENTER_VERTICAL);
		user_id.setText("用户ID：");
		user_id.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(32));
		LinearLayout.LayoutParams param_user_id = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 3);
		user_id.setLayoutParams(param_user_id);
		layout_id.addView(user_id);
		user_money = new TextView(context);
		user_money.setGravity(Gravity.CENTER_VERTICAL);
		user_money.setText("用户余额：");
		user_money.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(32));
		LinearLayout.LayoutParams param_user_money = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 5);
		user_money.setLayoutParams(param_user_money);
		layout_id.addView(user_money);
		ShadeButton2 orderButton = new ShadeButton2(context);
		orderButton.setText("充值");
		orderButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXTSIZE);
		orderButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (gotoRechargeListener != null) {
					gotoRechargeListener.gotoRecharge();
				}
			}
		});
		LinearLayout.LayoutParams param_orderButton = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 6);
//		param_orderButton.setMargins(5, 5, 5, 5);
		param_orderButton.leftMargin = DisplayManager.GetInstance().changeWidthSize(25);
		orderButton.setLayoutParams(param_orderButton);
		layout_id.addView(orderButton);
		addView(layout_id);
		
		
		user_status = new TextView(context);
		user_status.setGravity(Gravity.CENTER_VERTICAL);
		user_status.setText("用户状态：");
		user_status.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(32));
		LinearLayout.LayoutParams param_user_status = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 7);
		user_status.setLayoutParams(param_user_status);
		addView(user_status);
		
		LinearLayout layout_order_info_title = new LinearLayout(context);
		layout_order_info_title.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams param_layout_order_info_title = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,7);
		param_layout_order_info_title.topMargin = DisplayManager.GetInstance().changeHeightSize(15);
		layout_order_info_title.setLayoutParams(param_layout_order_info_title);
		
		TextView order_info_type = new TextView(context);
		order_info_type.setTextSize(TypedValue.COMPLEX_UNIT_DIP,ITEM_SIZE);
		order_info_type.setTextColor(Color.WHITE);
		order_info_type.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams param_order_info_type = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 14);
		param_order_info_type.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		order_info_type.setLayoutParams(param_order_info_type);
		order_info_type.setText("服务类型");
		order_info_type.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXTSIZE);
		order_info_type.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		layout_order_info_title.addView(order_info_type);
		
		TextView order_info_time = new TextView(context);
		order_info_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXTSIZE);
		order_info_time.setTextColor(Color.WHITE);
		order_info_time.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		order_info_time.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams param_order_info_time = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 10);
		param_order_info_time.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		order_info_time.setLayoutParams(param_order_info_time);
		order_info_time.setText("服务时间");
		layout_order_info_title.addView(order_info_time);
		
		TextView order_Price = new TextView(context);
		order_Price.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXTSIZE);
		order_Price.setTextColor(Color.WHITE);
		order_Price.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		order_Price.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams param_order_Price = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 15);
		param_order_Price.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		order_Price.setLayoutParams(param_order_Price);
		order_Price.setText("原价");
		layout_order_info_title.addView(order_Price);
		
		TextView order_bargin_Price = new TextView(context);
		order_bargin_Price.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXTSIZE);
		order_bargin_Price.setTextColor(Color.WHITE);
		order_bargin_Price.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		order_bargin_Price.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams param_order_bargin_Price = new LinearLayout.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 15);
		param_order_bargin_Price.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		order_bargin_Price.setLayoutParams(param_order_bargin_Price);
		order_bargin_Price.setText("促销价");
		layout_order_info_title.addView(order_bargin_Price);
		
		TextView order_product = new TextView(context);
		order_product.setTextSize(TypedValue.COMPLEX_UNIT_DIP,TEXTSIZE);
		order_product.setTextColor(Color.WHITE);
		order_product.setBackgroundColor(Color.rgb(0x21, 0x21, 0x21));
		order_product.setGravity(Gravity.CENTER);
		LinearLayout.LayoutParams param_order_product = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 14);
		param_order_product.leftMargin = DisplayManager.GetInstance().changeWidthSize(5);
		order_product.setLayoutParams(param_order_product);
		order_product.setText("订购服务");
		layout_order_info_title.addView(order_product);
		addView(layout_order_info_title);
		
		
		
		LinearLayout layout_product_list = new LinearLayout(context);
		layout_product_list.setOrientation(LinearLayout.VERTICAL);
		layout_product_list.setFocusable(true);
		layout_product_list.setFocusableInTouchMode(true);
		layout_product_list.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View arg0, boolean hasFocus) {
				if(hasFocus){
					if (currentFocused == -1) {
						currentFocused = 0;
					}
					updateStatuesUI();
				}else{
					currentFocused = -1;
					/*for(int i = 0; i < ITEM_SIZE; i++){
						if(itemViews[i].isFocused()){
							itemViews[i].setIsFocused(false);
							return;
						}
					}*/
				}
				updateStatuesUI();
			}
		});
		layout_product_list.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(View arg0, int keyCode, KeyEvent event) {
				if (currentProducts == null || currentProducts.size() == 0) {
					return true;
				}
				if (event.getAction() != KeyEvent.ACTION_DOWN) {
					return false;
				}
				switch (keyCode) {
				case KeyEvent.KEYCODE_DPAD_UP:
					if (currentFocused > 0) {
						currentFocused--;
						updateStatuesUI();
						return true;
					}
					return false;
				case KeyEvent.KEYCODE_DPAD_DOWN:
					if (currentFocused < currentProducts.size() - 1) {
						currentFocused++;
						updateStatuesUI();
						return true;
					}
					return false;
				case KeyEvent.KEYCODE_ENTER:
				case KeyEvent.KEYCODE_DPAD_CENTER:
					if (currentFocused >= 0 && currentFocused <= currentProducts.size() -1) {
						//回调接口
						if (listener != null && currentProducts != null && currentProducts.size() > 0) {
							listener.order(currentProducts.get(currentFocused),itemViews[currentFocused].currentOrderStatus);
						}
					}
					return false;
				default:
					return false;
				}
			}
		});
		LinearLayout.LayoutParams param_layout_product_list = new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT,4);
		param_layout_product_list.topMargin = DisplayManager.GetInstance().changeHeightSize(15);
		layout_product_list.setLayoutParams(param_layout_product_list);

		itemViews = new ProductItemView[ITEM_SIZE];
		LinearLayout.LayoutParams param_item = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		param_item.leftMargin = 5;
		param_item.topMargin = 5;
		for(int i = 0; i < ITEM_SIZE; i++){
			itemViews[i] = new ProductItemView(context);
			itemViews[i].setLayoutParams(param_item);
			itemViews[i].setVisibility(View.INVISIBLE);
			final int index = i;
			itemViews[i].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null && currentProducts != null && currentProducts.size() > 0) {
						listener.order(currentProducts.get(index),itemViews[index].currentOrderStatus);
					}
				}
			});
			layout_product_list.addView(itemViews[i]);
		}
		addView(layout_product_list);
		
		
		pageNavigator = new PageNavigator(context);
		pageNavigator.setPageNavigatorListener(new PageNavigatorListener() {
			@Override
			public void onGotoPage(int pageNo) {
				setProductListDate(pageNo);
				pageNavigator.setPageInfo(pageNo, totalPage);
			}
		});
		LinearLayout.LayoutParams param_pageNavigator = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT,7);
		param_pageNavigator.bottomMargin = DisplayManager.GetInstance().changeHeightSize(20);
		pageNavigator.setLayoutParams(param_pageNavigator);
		addView(pageNavigator);
	}
	
	public void setOrderInfo(OrderListInfo orderListInfo){
		this.orderListInfo = orderListInfo;
		setUserInfo(this.orderListInfo);
	}
	
	public void setProductDate(List<Product> products){
		this.products = products;
		initPageInfo(products.size());
		setProductListDate(currentPage);
		LogUtil.d("product--->" + products.size());
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
	
	private void getCurrentProductList(){
		int startPosition =(currentPage - 1)*ITEM_SIZE;
		int endPosition;
		if (totalSize > currentPage * ITEM_SIZE) {
			endPosition = currentPage*ITEM_SIZE;
		}else {
			endPosition = totalSize;
		}
		LogUtil.d("products==" + products.size() + "---startPosition==" + startPosition + "---endPostion==" + endPosition);
		currentProducts = products.subList(startPosition, endPosition);
	}
	
	private void setProductListDate(int pageNo){
		/*if (currentProducts != null) {
			currentProducts.clear();
		}*/
		currentPage = pageNo;
		getCurrentProductList();
		if (currentProducts != null && currentProducts.size() > 0) {
			for (int i = 0; i < ITEM_SIZE; i++) {
				if (i < currentProducts.size()) {
					itemViews[i].setVisibility(VISIBLE);
					itemViews[i].setProductType(currentProducts.get(i).getName());
					itemViews[i].setProductPrice("￥" + Integer.parseInt(currentProducts.get(i).getCostFee())/100);
					itemViews[i].setProductBarginPrice("￥" + Integer.parseInt(currentProducts.get(i).getFee())/100);
					String status = currentProducts.get(i).getIsOrder();
					if ("0".equals(status)) {
						itemViews[i].setOrderStatus(OrderStatus.ORDER_NEW);
						if (currentProducts.get(i).getUsefulLife() != null && !"".equals(currentProducts.get(i).getUsefulLife())) {
							int userfulLife = Integer.parseInt(currentProducts.get(i).getUsefulLife());
							if (userfulLife/24 >= 1) {
								itemViews[i].setProductTime(userfulLife/24 + "天");
							}else {
								itemViews[i].setProductTime(userfulLife + "小时");
							}
						}else {
							itemViews[i].setProductTime("获取服务时间出错");
						}
					}else if ("1".equals(status)) {
						String cancelStatus = currentProducts.get(i).getCancelOrder();
						if ("0".equals(cancelStatus)) {
							itemViews[i].setOrderStatus(OrderStatus.ORDER_CONTINUE);
						}else if ("1".equals(cancelStatus)) {
							itemViews[i].setOrderStatus(OrderStatus.ORDER_CANCEL);
						}
						String startTime = currentProducts.get(i).getStartTime();
						startTime = startTime.substring(0, startTime.lastIndexOf("/"));
						String stopTime = currentProducts.get(i).getStopTime();
						stopTime = stopTime.substring(0, stopTime.lastIndexOf("/"));
						itemViews[i].setProductTime(startTime + "——" + stopTime);
					}
					if (i == currentFocused) {
						itemViews[i].setIsFocused(true);
					}else {
						itemViews[i].setIsFocused(false);
					}
				}else {
					itemViews[i].setVisibility(View.INVISIBLE);
				}
			}
		}
	}
	
	/*public void updateOrderResultUI(Product product){
		if (product != null && currentProducts != null && currentProducts.size() > 0) {
			String status = product.getIsOrder();
			if ("0".equals(status)) {
				itemViews[currentFocused].setOrderStatus(OrderStatus.ORDER_NEW);
			}else if ("1".equals(status)) {
				String cancelStatus = product.getCancelOrder();
				if ("0".equals(cancelStatus)) {
					itemViews[currentFocused].setOrderStatus(OrderStatus.ORDER_CONTINUE);
				}else if ("1".equals(cancelStatus)) {
					itemViews[currentFocused].setOrderStatus(OrderStatus.ORDER_CANCEL);
				}
			}
//			itemViews[currentFocused].setOrderStatus(OrderStatus.ORDER_CONTINUE);
			String startTime = product.getStartTime();
			startTime = startTime.substring(0, startTime.lastIndexOf("/"));
			String stopTime = product.getStopTime();
			stopTime = stopTime.substring(0, stopTime.lastIndexOf("/"));
			itemViews[currentFocused].setProductTime(stopTime + "——" + stopTime);
//			updateStatuesUI();
		}
	}*/
	
	private void updateStatuesUI(){
		if (currentProducts != null && currentProducts.size() > 0) {
			for (int i = 0; i < ITEM_SIZE; i++) {
				if (currentFocused == i) {
					itemViews[i].setIsFocused(true);
				}else {
					itemViews[i].setIsFocused(false);
				}
			}
		}
	}
	
	private void setUserInfo(OrderListInfo orderListInfo){
		if (orderListInfo != null) {
			user_id.setText("用户ID：" + orderListInfo.getUid());
			if (orderListInfo.getBalance() != null) {
				user_money.setText("用户余额：￥" + Integer.parseInt(orderListInfo.getBalance())/100);
			}else {
				user_money.setText("用户余额：未获取到");
			}
			orderInfos = orderListInfo.getOrderList();
			if (orderInfos == null || orderInfos.size() == 0) {
				user_status.setText("用户状态：您还未订购任何服务");
			}else {
				StringBuffer sb = new StringBuffer();
				sb.append("用户状态：您已订购" );
				for (int i = 0; i < orderInfos.size(); i++) {
					sb.append(orderInfos.get(i).getName());
					sb.append(",");
				}
				String string = sb.toString().substring(0, sb.lastIndexOf(","));
//				sb.append("服务");
				user_status.setText(string + "服务");
			}
		}
	}
	
	public static class ProductItemView extends BaseLinearLayout{
		private static final String ORDER_NEW_TEXT = "立即订购";
		private static final String ORDER_CONTINUE_TEXT = "恢复续订";
		private static final String ORDER_CANCEL_TEXT = "取消续订";
		private AlwaysMarqueeTextView productType = null;
		private AlwaysMarqueeTextView productTime = null;
		private TextView productPrice = null;
		private TextView productBarginPrice = null;
		private ProductOrderView productOrder = null;
		
		private boolean focused = false;
		
		private static final int textSize = DisplayManager.GetInstance().changeTextSize(28);
		private static final int textColor = Color.GRAY;
		
		public OrderStatus currentOrderStatus = OrderStatus.ORDER_NEW;
		
		public enum OrderStatus{
			ORDER_NEW,
			ORDER_CONTINUE,
			ORDER_CANCEL
		}

		public boolean isFocused() {
			return focused;
		}

		public void setIsFocused(boolean focused) {
			this.focused = focused;
			productOrder.setFocused(this.focused);
		}

		public ProductItemView(Context context, AttributeSet attrs) {
			super(context, attrs);
			setOrientation(HORIZONTAL);
			init(context);
		}

		public ProductItemView(Context context) {
			super(context);
			setOrientation(HORIZONTAL);
			init(context);
		}
		
		/**
		 * left to right 3:7:2:2:3
		 * @param context
		 */
		private void init(Context context){
			
			productType = new AlwaysMarqueeTextView(context);
			productType.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
			productType.setMarquee(true);
			productType.setTextColor(textColor);
			productType.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_productType = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 14);
			productType.setLayoutParams(param_productType);
			addView(productType);
			
			productTime = new AlwaysMarqueeTextView(context);
			productTime.setSingleLine(true);
			productTime.setHorizontallyScrolling(true);
			productTime.setEllipsize(TruncateAt.MARQUEE);
			productTime.setMarqueeRepeatLimit(-1);
			productTime.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
			productTime.setTextColor(textColor);
			productTime.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_productTime = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 10);
			productTime.setLayoutParams(param_productTime);
			addView(productTime);
			
			productPrice = new TextView(context);
			productPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
			productPrice.setTextColor(textColor);
			productPrice.setGravity(Gravity.CENTER);
			productPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
			LinearLayout.LayoutParams param_productPrice = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 15);
			productPrice.setLayoutParams(param_productPrice);
			addView(productPrice);
			
			productBarginPrice = new TextView(context);
			productBarginPrice.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
			productBarginPrice.setTextColor(textColor);
			productBarginPrice.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams param_productBarginPrice = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 15);
			productBarginPrice.setLayoutParams(param_productBarginPrice);
			addView(productBarginPrice);
			
			productOrder = new ProductOrderView(context);
			LinearLayout.LayoutParams param_productOrder = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 14);
			param_productOrder.setMargins(5, 5, 5, 5);
			productOrder.setLayoutParams(param_productOrder);
			addView(productOrder);
			
		}
		
		public void setProductType(String str){
			productType.setText(str);
		}
		
		public void setProductTime(String str){
			productTime.setText(str);
		}
		
		public void setProductPrice(String str){
			productPrice.setText(str);
		}
		
		public void setProductBarginPrice(String str){
			productBarginPrice.setText(str);
		}
		
		public void setOrderStatus(OrderStatus status){
			currentOrderStatus = status;
			productOrder.setTag(status);
			updateStatus();
		}
		
		private void updateStatus(){
			switch (currentOrderStatus) {
			case ORDER_NEW:
				productOrder.setText(ORDER_NEW_TEXT);
				break;
			case ORDER_CONTINUE:
				productOrder.setText(ORDER_CONTINUE_TEXT);
				break;
			case ORDER_CANCEL:
				productOrder.setText(ORDER_CANCEL_TEXT);
				break;
			default:
				break;
			}
		}
		
		public void setOrderStatus(String status){
			productOrder.setText(status);
		}
		
		public OrderStatus getOrderStatus(){
			return (OrderStatus)productOrder.getTag();
		}
		
	}
	
	public interface GotoRechargeListener{
		public void gotoRecharge();
	}
}
