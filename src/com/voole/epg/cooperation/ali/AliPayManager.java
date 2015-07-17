package com.voole.epg.cooperation.ali;

import android.content.Context;
import android.os.Bundle;

import com.aliyun.pay.client.PayClient;
import com.aliyun.pay.client.YunOSPayResult;
import com.voole.epg.Config;
import com.voole.epg.corelib.model.account.AccountManager;
import com.voole.epg.corelib.model.account.GetOrderResult;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.tvutils.DeviceUtil;


public class AliPayManager {
	private static AliPayManager instance = new AliPayManager();

	private AliPayManager() {
	}

	public static AliPayManager GetInstance() {
		return instance;
	}
	
	
	public boolean isAli(){
		return "1".equals(Config.GetInstance().getAliPay())&&!"false".equals(DeviceUtil.getCloudUUID());
	}
	
	public AliPayResult pay(Context context, String price){
		AliPayResult aliPayResult = new AliPayResult();
		int v = 0;
		try {
			v = Integer.parseInt(price);
			if(v < 5){
				aliPayResult.setResult("1");
				aliPayResult.setResultDis("输入金额不符合要求");
			}
		} catch (Exception e) {
			aliPayResult.setResult("1");
			aliPayResult.setResultDis("输入金额不符合要求");
		}
		GetOrderResult ret = AccountManager.GetInstance().getOrderNo(v);
		if(ret == null){
			aliPayResult.setResult("1");
			aliPayResult.setResultDis("网络连接异常");
			return aliPayResult;
		}
		
		if(!"0".equals(ret.getStatus())){
			aliPayResult.setResult("1");
			aliPayResult.setResultDis(ret.getResultdesc());
			return aliPayResult;
		}
		String uid = AuthManager.GetInstance().getUser().getUid();
		PayClient payer = new PayClient();
		YunOSOrderManager orderManager = new YunOSOrderManager();
		orderManager.GenerateOrder(AliConfig.getPrikey(), AliConfig.getPartner(), uid, uid + "支付宝充值", String.valueOf(v * 100), AliConfig.getPartnerNotifyUrl(), ret.getOrderid());
		String order = orderManager.getOrder();
		String sign = orderManager.getSign();

		YunOSPayResult payResult = null;
		String errorMsg = "";
		String msg = "";
		Bundle bundle = new Bundle();
		bundle.putString("provider", "alipay");
		try {
			if (payer != null) {
				payResult = payer.YunPay(context, order, sign, bundle);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage() + "----" + e.getLocalizedMessage();
			aliPayResult.setResult("1");
			aliPayResult.setResultDis("支付异常，请重试");
			return aliPayResult;
		}
		if (payResult != null) {
//			msg = payResult.getPayResult() ? "付款成功" : "付款失败," + payResult.getPayFeedback();
			if(payResult.getPayResult()){
				aliPayResult.setResult("0");
				aliPayResult.setResultDis("付款成功");
				return aliPayResult;
			}else{
				aliPayResult.setResult("1");
				aliPayResult.setResultDis("付款失败");
				return aliPayResult;
			}
		} else {
			msg = "支付调起失败 :" + errorMsg;
			aliPayResult.setResult("1");
			aliPayResult.setResultDis(msg);
			return aliPayResult;
		}
	}
}
