package com.voole.epg.view.mymagic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.voole.epg.R;
import com.voole.epg.base.BaseLinearLayout;
import com.voole.epg.base.BaseView;
import com.voole.epg.base.common.DisplayManager;
import com.voole.epg.base.common.ID;
import com.voole.epg.base.common.InputText;
import com.voole.epg.base.common.MessageManager;
import com.voole.epg.base.common.NumberDialog;
import com.voole.epg.base.common.NumberDialog.NumberListener;
import com.voole.epg.base.common.ShadeButton;
import com.voole.epg.cooperation.ali.AliPayManager;
import com.voole.epg.corelib.model.account.OrderListInfo;
import com.voole.epg.corelib.model.account.RechargeResult;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.view.mymagic.MoneyView.MoneyItemSelectListener;
import com.voole.tvutils.BitmapUtil;

public class RechargeView extends BaseLinearLayout {
	private RechargeViewTitleView card_recharge = null;
	private RechargeViewTitleView official_recharge = null;
	private RechargeViewTitleView lianTong_recharge = null;
	private RechargeViewTitleView other_recharge = null;
	private RechargeViewTitleView ali_recharge = null;
	private String uid = null;

	private Bitmap focusedBitmap = BitmapUtil.readBitmap(getContext(),
			R.drawable.cs_mymagic_recharge_title_bg);

	// private RechargeViewTitleView[] titleItems = null;

	public void showAli() {
		if (ali_recharge != null) {
			ali_recharge.setVisibility(View.VISIBLE);
		}
	}

	private CardView cardView = null;
	private OfficialView officialView = null;
	private OtherView otherView = null;
	private LianTongView lianTongView = null;
	private ALiView aliView = null;

	public void setRechargeListener(RechargeListener listener) {
		if (listener != null && cardView != null&&aliView!=null) {
			cardView.setRechargeListener(listener);
			aliView.setRechargeListener(listener);
		}
	}

	public RechargeView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.setOrientation(VERTICAL);

		LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setLayoutParams(param);

		LinearLayout layout_title = new LinearLayout(context);
		layout_title.setBackgroundResource(R.drawable.cs_mymagic_recharge_line);
		layout_title.setOrientation(LinearLayout.HORIZONTAL);
		LinearLayout.LayoutParams param_layout_title = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 4);
		param_layout_title.topMargin = 15;
		// param_layout_title.leftMargin = 15;
		param_layout_title.rightMargin = 15;
		layout_title.setLayoutParams(param_layout_title);
		if(AliPayManager.GetInstance().isAli()) {
			ali_recharge = new RechargeViewTitleView(context);
			ali_recharge.setId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ALI_ID);
			ali_recharge.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showAliView();
				}
			});
			ali_recharge.setFocusListener(new FocusListener() {
				@Override
				public void onFocusChange(boolean hasFocus) {
					if (hasFocus) {
						showAliView();
					}
				}
			});
			ali_recharge.setText("支付宝");
			LinearLayout.LayoutParams param_ali_recharge = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			ali_recharge.setLayoutParams(param_ali_recharge);
			layout_title.addView(ali_recharge);
			
			card_recharge = new RechargeViewTitleView(context);
			card_recharge.setId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ID);
			card_recharge.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showCardView();
				}
			});
			card_recharge.setFocusListener(new FocusListener() {
				
				@Override
				public void onFocusChange(boolean hasFocus) {
					if (hasFocus) {
						showCardView();
					}
				}
			});
			card_recharge.setText("充值卡充值");
			LinearLayout.LayoutParams param_card_recharge = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
//			param_card_recharge.leftMargin = DisplayManager.GetInstance()
//					.changeWidthSize(60);
			card_recharge.setLayoutParams(param_card_recharge);
			layout_title.addView(card_recharge);

			official_recharge = new RechargeViewTitleView(context);
			official_recharge.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showOfficialView();
				}
			});
			official_recharge.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						showOfficialView();
					}
				}
			});

		}else {
			card_recharge = new RechargeViewTitleView(context);
			card_recharge.setId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ID);
			card_recharge.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showCardView();
				}
			});
			card_recharge.setFocusListener(new FocusListener() {
				
				@Override
				public void onFocusChange(boolean hasFocus) {
					if (hasFocus) {
						showCardView();
					}
				}
			});
			card_recharge.setText("充值卡充值");
			LinearLayout.LayoutParams param_card_recharge = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
//			param_card_recharge.leftMargin = DisplayManager.GetInstance()
//					.changeWidthSize(60);
			card_recharge.setLayoutParams(param_card_recharge);
			layout_title.addView(card_recharge);

			official_recharge = new RechargeViewTitleView(context);
			official_recharge.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showOfficialView();
				}
			});
			official_recharge.setOnFocusChangeListener(new OnFocusChangeListener() {

				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					if (hasFocus) {
						showOfficialView();
					}
				}
			});

			ali_recharge = new RechargeViewTitleView(context);
			ali_recharge.setVisibility(INVISIBLE);
			ali_recharge.setId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ALI_ID);
			ali_recharge.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					showAliView();
				}
			});
			ali_recharge.setFocusListener(new FocusListener() {
				@Override
				public void onFocusChange(boolean hasFocus) {
					if (hasFocus) {
						showAliView();
					}
				}
			});
			ali_recharge.setText("支付宝");
			LinearLayout.LayoutParams param_ali_recharge = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
			ali_recharge.setLayoutParams(param_ali_recharge);
			layout_title.addView(ali_recharge);
		}

		official_recharge.setText("官网充值");
		official_recharge.setVisibility(INVISIBLE);
		LinearLayout.LayoutParams param_official_recharge = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		official_recharge.setLayoutParams(param_official_recharge);
		layout_title.addView(official_recharge);

		lianTong_recharge = new RechargeViewTitleView(context);
		lianTong_recharge.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showLianTongView();
			}
		});
		lianTong_recharge.setText("联通电话充值");
		lianTong_recharge.setVisibility(INVISIBLE);
		LinearLayout.LayoutParams param_lianTong_recharge = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		lianTong_recharge.setLayoutParams(param_lianTong_recharge);
		layout_title.addView(lianTong_recharge);

		other_recharge = new RechargeViewTitleView(context);
		other_recharge.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showOtherView();
			}
		});
		other_recharge.setText("数码视讯充值");
		other_recharge.setVisibility(INVISIBLE);
		LinearLayout.LayoutParams param_other_recharge = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		other_recharge.setLayoutParams(param_other_recharge);
		layout_title.addView(other_recharge);

		addView(layout_title);

		/*
		 * ImageView line = new ImageView(context); line.setId(1002);
		 * RelativeLayout.LayoutParams param_line = new
		 * RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
		 * LayoutParams.MATCH_PARENT); param_line.addRule(RelativeLayout.BELOW,
		 * 1001); line.setLayoutParams(param_line); Bitmap bitmap =
		 * BitmapUtil.readBitmap(context, R.drawable.cs_mymagic_recharge_line);
		 * line.setImageBitmap(bitmap); addView(line);
		 */

		RelativeLayout layout_down = new RelativeLayout(context);
		LinearLayout.LayoutParams param_layout_down = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1);
		/*param_layout_down.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(100);
		param_layout_down.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		param_layout_down.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);*/
		layout_down.setLayoutParams(param_layout_down);
		
		cardView = new CardView(context);
		layout_down.addView(cardView);
		// addView(layout_down);

		officialView = new OfficialView(context);
		LinearLayout.LayoutParams param_officialView = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		param_officialView.bottomMargin = DisplayManager.GetInstance()
				.changeHeightSize(100);
		param_officialView.leftMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		param_officialView.rightMargin = DisplayManager.GetInstance()
				.changeWidthSize(45);
		officialView.setLayoutParams(param_officialView);
		layout_down.addView(officialView);

		otherView = new OtherView(context);
		layout_down.addView(otherView);

		lianTongView = new LianTongView(context);
		layout_down.addView(lianTongView);

		aliView = new ALiView(context);
		LinearLayout.LayoutParams param_ali = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		aliView.setLayoutParams(param_ali);
		layout_down.addView(aliView);
		addView(layout_down);
		if(AliPayManager.GetInstance().isAli()) {
			showAliView();
		}else{
			showCardView();
		}
	}

	public void setData(RechargeResult rechargeResult) {
		if (rechargeResult != null) {
			String uid = rechargeResult.getUid();
			if (!"".equals(uid)) {
				cardView.setUID(uid);
				officialView.setUID(uid);
				aliView.setUID(uid);
			} else {
				cardView.setUID(AuthManager.GetInstance().getUser().getUid());
				officialView.setUID(AuthManager.GetInstance().getUser()
						.getUid());
				aliView.setUID(AuthManager.GetInstance().getUser().getUid());
			}
		} else {
			cardView.setUID(AuthManager.GetInstance().getUser().getUid());
			officialView.setUID(AuthManager.GetInstance().getUser().getUid());
			aliView.setUID(AuthManager.GetInstance().getUser().getUid());
		}
	}

	public void setData(String uid) {
		this.uid = uid;
		cardView.setUID(uid);
		cardView.clearPwd();
		officialView.setUID(uid);
		aliView.setUID(uid);
		aliView.clearMoney();
		
	}
	
	public void setMoney(OrderListInfo orderListInfo){
		if (orderListInfo.getBalance() != null) {
			cardView.setMoney(Integer.parseInt(orderListInfo.getBalance())/100+"");
		}else {
			cardView.setMoney("未获取到");
		}
	}

	public String getUid() {
		return uid;
	}

	private void showCardView() {
		cardView.setVisibility(View.VISIBLE);
		officialView.setVisibility(View.INVISIBLE);
		otherView.setVisibility(View.INVISIBLE);
		lianTongView.setVisibility(View.INVISIBLE);

		card_recharge.setSelected(true);
		official_recharge.setSelected(false);
		other_recharge.setSelected(false);
		lianTong_recharge.setSelected(false);

		aliView.setVisibility(View.INVISIBLE);
		ali_recharge.setSelected(false);
	}

	private void showOfficialView() {
		officialView.setVisibility(View.VISIBLE);
		cardView.setVisibility(View.INVISIBLE);
		otherView.setVisibility(View.INVISIBLE);
		lianTongView.setVisibility(View.INVISIBLE);
		aliView.setVisibility(View.INVISIBLE);

		card_recharge.setSelected(false);
		official_recharge.setSelected(true);
		other_recharge.setSelected(false);
		lianTong_recharge.setSelected(false);
		ali_recharge.setSelected(false);
	}

	private void showAliView() {
		aliView.setVisibility(View.VISIBLE);
		officialView.setVisibility(View.INVISIBLE);
		cardView.setVisibility(View.INVISIBLE);
		otherView.setVisibility(View.INVISIBLE);
		lianTongView.setVisibility(View.INVISIBLE);

		ali_recharge.setSelected(true);
		card_recharge.setSelected(false);
		official_recharge.setSelected(false);
		other_recharge.setSelected(false);
		lianTong_recharge.setSelected(false);

	}

	private void showOtherView() {
		otherView.setVisibility(View.VISIBLE);
		cardView.setVisibility(View.INVISIBLE);
		officialView.setVisibility(View.INVISIBLE);
		lianTongView.setVisibility(View.INVISIBLE);

		card_recharge.setSelected(false);
		official_recharge.setSelected(false);
		other_recharge.setSelected(true);
		lianTong_recharge.setSelected(false);
		ali_recharge.setSelected(false);
	}

	private void showLianTongView() {
		lianTongView.setVisibility(View.VISIBLE);
		cardView.setVisibility(View.INVISIBLE);
		officialView.setVisibility(View.INVISIBLE);
		otherView.setVisibility(View.INVISIBLE);

		card_recharge.setSelected(false);
		official_recharge.setSelected(false);
		other_recharge.setSelected(false);
		lianTong_recharge.setSelected(true);

		aliView.setVisibility(View.INVISIBLE);
		ali_recharge.setSelected(false);
	}

	/**
	 * @author Administrator
	 */
	public class RechargeViewTitleView extends BaseView {

		private Paint paint = new Paint();
		private boolean isFocused = false;
		private boolean isSelected = false;
		private String string = "";
		
		private FocusListener focusListener = null;
		private Rect rect;

		// private Bitmap unFocusedBitmap = null;

		public void setFocusListener(FocusListener focusListener) {
			this.focusListener = focusListener;
		}

		public RechargeViewTitleView(Context context) {
			super(context);
			init(context);
		}

		public RechargeViewTitleView(Context context, AttributeSet attrs,
				int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public RechargeViewTitleView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public void setText(String text) {
			string = text;
		}

//		public void setFocused(boolean isFocused) {
//			this.isFocused = isFocused;
//			invalidate();
//		}

		public void setSelected(boolean isSelected) {
			this.isSelected = isSelected;
			invalidate();
		}

		/*
		 * public void setImg(Bitmap unFocusedBitmap,Bitmap focusedBitmap){ //
		 * this.unFocusedBitmap = unFocusedBitmap; this.focusedBitmap =
		 * focusedBitmap; }
		 */

		private void init(Context context) {
			setFocusable(true);
			setFocusableInTouchMode(true);
			setClickable(true);

			paint.setStrokeWidth(0);
			paint.setTextAlign(Align.CENTER);
			paint.setStyle(Paint.Style.FILL);
			paint.setTextSize(DisplayManager.GetInstance().changeWidthSize(28));
		}

		@Override
		protected void onFocusChanged(boolean gainFocus, int direction,
				Rect previouslyFocusedRect) {
			isFocused = gainFocus;
			invalidate();
			if(focusListener != null){
				focusListener.onFocusChange(gainFocus);
			}
		}

		@Override
		protected void onDraw(Canvas canvas) {
			/*super.onDraw(canvas);
			Rect rect = canvas.getClipBounds();
			if (isFocused) {
				paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
				//paint.setColor(Color.WHITE);
				canvas.drawBitmap(focusedBitmap, null, rect, null);
			} else {
				if (isSelected) {
					//paint.setColor(Color.GRAY);
					paint.setColor(Color.WHITE);
					canvas.drawBitmap(focusedBitmap, null, rect, null);
				} else {
					//paint.setColor(Color.GRAY);
					paint.setColor(Color.WHITE);
				}
				// canvas.drawBitmap(unFocusedBitmap, null, rect, null);
			}
			FontMetrics fontMetrics = paint.getFontMetrics();
			float fontHeight = fontMetrics.bottom - fontMetrics.top;
			float textBaseY = rect.height() - (rect.height() - fontHeight) / 2
					- fontMetrics.bottom;
			canvas.drawText(string, rect.width() / 2, textBaseY, paint);*/
			
			super.onDraw(canvas);
			if (rect==null) {
				rect = canvas.getClipBounds();
			}
			if (isFocused) {
				paint.setColor(Color.rgb(0xe4, 0x7b, 0x39));
			} else {
				paint.setColor(Color.WHITE);
			}
			
			if (isSelected) {
				canvas.drawBitmap(focusedBitmap, null, rect, null);
			} 
			FontMetrics fontMetrics = paint.getFontMetrics();
			float fontHeight = fontMetrics.bottom - fontMetrics.top;
			float textBaseY = rect.height() - (rect.height() - fontHeight) / 2
					- fontMetrics.bottom;
			canvas.drawText(string, rect.width() / 2, textBaseY, paint);
		}
		
	}
	
	public interface FocusListener {
		public void onFocusChange(boolean hasFocus);
	}

	/**
	 * 充值卡充值View
	 * 
	 * @author Administrator
	 */
	public static class CardView extends BaseLinearLayout {

		private TextView userId = null;
		private TextView userMoney = null;
		private InputText cardPassword = null;
		private ShadeButton btn_ok = null;
		private ShadeButton btn_cancle = null;
		private final int textSize = DisplayManager.GetInstance()
				.changeTextSize(28);

		private String uid = "";

		private RechargeListener listener = null;

		public void setRechargeListener(RechargeListener listener) {
			this.listener = listener;
		}

		public CardView(Context context) {
			super(context);
			setOrientation(VERTICAL);
			init(context);
		}

		public void setUID(String uid) {
			this.uid = uid;
			userId.setText("用户ID：" + uid);
		}
		
		public void clearPwd(){
			cardPassword.getEditText().setText("");
		}
		
		public void setMoney(String money) {
			userMoney.setText("     用户余额：￥"+money);
		}

		private void init(final Context context) {
			/*
			 * ImageView line = new ImageView(context);
			 * LinearLayout.LayoutParams param_line = new
			 * LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
			 * LayoutParams.WRAP_CONTENT); param_line.leftMargin =
			 * DisplayManager.GetInstance().changeWidthSize(5);
			 * line.setLayoutParams(param_line); addView(line);
			 */
			this.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			setOrientation(HORIZONTAL);
			
			LinearLayout layout_left = new LinearLayout(context);
			layout_left.setOrientation(VERTICAL);
			layout_left.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,4));
			addView(layout_left);
			
			
			
			
			LinearLayout layout_user = new LinearLayout(context);
			layout_user.setOrientation(HORIZONTAL);
			LinearLayout.LayoutParams param_layout_user = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			param_layout_user.leftMargin = DisplayManager.GetInstance()
					.changeWidthSize(30);
			param_layout_user.topMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			layout_user.setLayoutParams(param_layout_user);
			userId = new TextView(context);
			userId.setText("用户ID：");
			userId.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			layout_user.addView(userId);
			
			
			userMoney=new TextView(context);
			userMoney.setText("     用户余额：");
			userMoney.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			layout_user.addView(userMoney);
			
			layout_left.addView(layout_user);
			
			
			LinearLayout layout_password = new LinearLayout(context);
			layout_password.setOrientation(HORIZONTAL);
			LinearLayout.LayoutParams param_layout_password = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
			param_layout_password.leftMargin = DisplayManager.GetInstance()
					.changeWidthSize(30);
			param_layout_password.topMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			layout_password.setLayoutParams(param_layout_password);
			layout_password.setGravity(Gravity.CENTER_VERTICAL);

			TextView password_tv = new TextView(context);
			password_tv.setSingleLine(true);
			password_tv.setText("充值卡密码：");
			password_tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP,textSize);
			LinearLayout.LayoutParams param_password_tv = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			password_tv.setLayoutParams(param_password_tv);
			layout_password.addView(password_tv);

			cardPassword = new InputText(context);
			cardPassword.getEditText().setInputType(InputType.TYPE_NULL);
			cardPassword.getEditText().setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					 NumberDialog dialog=new NumberDialog(context,R.style.alertDialog);
					 dialog.setInputLimit(20);
					 dialog.show();
					 dialog.setNumberListener(new NumberListener() {
						
						@Override
						public void onInput(String str) {
							cardPassword.getEditText().setText(str);
						}
						
						@Override
						public void onGotoPage(int pageNo) {
							
						}
					});
				}
			});
			// cardPassword.setBackgroundResource(R.drawable.cs_search_bar_list_item_focused);
			LinearLayout.LayoutParams param_cardPassword = new LinearLayout.LayoutParams(
					DisplayManager.GetInstance().changeWidthSize(250), LayoutParams.WRAP_CONTENT);
			cardPassword.setLayoutParams(param_cardPassword);
			cardPassword.getEditText().setNextFocusUpId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ID);
			cardPassword.getEditText().setNextFocusDownId(ID.MyMagicActivity.CC_RECHARGEVIEW_CARD_CAHRGE_ID);
			layout_password.addView(cardPassword);

			TextView tv = new TextView(context);
			tv.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 7));
			layout_password.addView(tv);

			layout_left.addView(layout_password);

			LinearLayout layout_btn = new LinearLayout(context);
			layout_password.setOrientation(HORIZONTAL);
			LinearLayout.LayoutParams param_layout_btn = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			param_layout_btn.leftMargin = DisplayManager.GetInstance()
					.changeWidthSize(30);
			param_layout_btn.topMargin = DisplayManager.GetInstance()
					.changeHeightSize(20);
			layout_btn.setLayoutParams(param_layout_btn);

			btn_ok = new ShadeButton(context);
			btn_ok.setText("充值");
			btn_ok.setId(ID.MyMagicActivity.CC_RECHARGEVIEW_CARD_CAHRGE_ID);
			btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (listener != null) {
						listener.recharge(uid, cardPassword.getText()
								.toString());
					}
				}
			});
			LinearLayout.LayoutParams param_btn_ok = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			btn_ok.setLayoutParams(param_btn_ok);
			layout_btn.addView(btn_ok);

			btn_cancle = new ShadeButton(context);
			btn_cancle.setText("重置");
			btn_cancle.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					cardPassword.clear();
				}
			});
			LinearLayout.LayoutParams param_btn_cancle = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			param_btn_cancle.leftMargin = DisplayManager.GetInstance()
					.changeWidthSize(20);
			btn_cancle.setLayoutParams(param_btn_cancle);
			layout_btn.addView(btn_cancle);
			
			layout_left.addView(layout_btn);
			
			TextView card_hint=new TextView(context);
			LinearLayout.LayoutParams param_cardhint = new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			param_cardhint.leftMargin = 30;
			param_cardhint.topMargin = 20;
			card_hint.setLayoutParams(param_cardhint);
			String hint=MessageManager.GetInstance().getAlert(R.string.mymagic_cc_recharge_card_hint, context);
			card_hint.setText(hint);
			card_hint.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(22));
			card_hint.setTextColor(Color.WHITE);
			
			layout_left.addView(card_hint);
			
			LinearLayout layout_right = new LinearLayout(context);
			layout_right.setGravity(Gravity.CENTER);
			layout_right.setPadding(0, 20,0,0);
			layout_right.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT,6));
			layout_right.setOrientation(VERTICAL);
			addView(layout_right);
			
			ImageView imageView=new ImageView(context);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			imageView.setImageResource(R.drawable.cs_mm_cc_recharge_ma);
			layout_right.addView(imageView);
			
			TextView textView=new TextView(context);
			textView.setLayoutParams(new LinearLayout.LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			textView.setTextColor(Color.WHITE);
			textView.setText("手机扫描二维码 ，订购优朋充值卡");
			textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(20) );
			layout_right.addView(textView);
			
			
		}
	}

	/**
	 * 其他充值方式View 如：数码视讯
	 * 
	 * @author Administrator
	 * 
	 */
	public static class OtherView extends BaseLinearLayout {

		public OtherView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {

		}

	}

	public static class ALiView extends BaseLinearLayout {

		private View view = null;
		private TextView uidTV = null;
		private ShadeButton okBtn = null;
		private ShadeButton cancelBtn = null;
		private MoneyView moneyView=null;
		private InputText number_et=null;

		private RechargeListener listener = null;

		public void setRechargeListener(RechargeListener listener) {
			this.listener = listener;
		}
		
		public ALiView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init(context);
		}

		public ALiView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context);
		}

		public ALiView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setOrientation(VERTICAL);
			view = LayoutInflater.from(context).inflate(
					R.layout.cs_magic_cc_ali, null);
			uidTV = (TextView) view.findViewById(R.id.uid_textview);
			moneyView=(MoneyView) view.findViewById(R.id.moneyView);
			moneyView.setNextFocusUpId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ALI_ID);
			moneyView.setMoneyItemSelectListener(new MoneyItemSelectListener() {
				
				@Override
				public void onSelected() {
					number_et.getEditText().setText("");
				}
			});
			number_et=(InputText)view.findViewById(R.id.number_et);
			number_et.getEditText().setGravity(Gravity.CENTER);
			number_et.getEditText().setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
			number_et.getEditText().setNextFocusUpId(ID.MyMagicActivity.CC_RECHARGEVIEW_TITLE_ALI_ID);
			number_et.getEditText().setNextFocusDownId(ID.MyMagicActivity.CC_RECHARGEVIEW_ALI_CAHRGE_ID);
			number_et.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
			
			okBtn = (ShadeButton) view.findViewById(R.id.ok);
			okBtn.setText("充值");
			okBtn.setId(ID.MyMagicActivity.CC_RECHARGEVIEW_ALI_CAHRGE_ID);
			okBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					 if(listener!=null){
						 String price=moneyView.getPrice();
						 if("".equals(price)){
							 price=number_et.getText();
						 }
						 listener.aliPay(price);
					 }
					
				}
			});
			cancelBtn = (ShadeButton) view.findViewById(R.id.cancel);
			cancelBtn.setText("重置");
			cancelBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					((InputText)view.findViewById(R.id.number_et)).clear();
				}
			});
			addView(view);

		}

		public void setUID(String uid) {
			uidTV.setText(uid);
		}
		
		public void clearMoney(){
			number_et.getEditText().setText("");
		}

	}

	/**
	 * 联通电话充值
	 * 
	 * @author Administrator
	 * 
	 */
	public static class LianTongView extends BaseLinearLayout {

		public LianTongView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {

		}
	}

	/**
	 * 官网充值
	 * 
	 * @author Administrator
	 * 
	 */
	public static class OfficialView extends BaseLinearLayout {

		private TextView uidTV = null;
		private TextView contentTV = null;

		public OfficialView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setOrientation(VERTICAL);

			uidTV = new TextView(context);
			LinearLayout.LayoutParams param_uidTV = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			param_uidTV.topMargin = 10;
			uidTV.setLayoutParams(param_uidTV);
			uidTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(28));
			uidTV.setText("用户UID：");
			uidTV.setVisibility(View.INVISIBLE);
			addView(uidTV);

			contentTV = new TextView(context);
			LinearLayout.LayoutParams param_contentTV = new LinearLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			param_contentTV.topMargin = 10;
			contentTV.setLayoutParams(param_contentTV);
			contentTV.setTextSize(TypedValue.COMPLEX_UNIT_DIP,DisplayManager.GetInstance().changeTextSize(
					28));
			contentTV.setText("请登录优朋普乐官方网站www.voole.com进行充值");
			addView(contentTV);
		}

		public void setUID(String uidString) {
			uidTV.setText("用户UID：" + uidString);
		}
	}

}
