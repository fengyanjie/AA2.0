package com.voole.epg.base.common;

import java.util.HashMap;

import android.content.Context;

import com.voole.epg.R;
import com.voole.epg.corelib.model.auth.AuthManager;
import com.voole.epg.corelib.model.utils.LogUtil;
import com.voole.tvutils.NetUtil;

/**
 * 提示信息管理类
 */
public class MessageManager {

	private HashMap<String, String> map = null;
	private HashMap<Integer, String> keyAndId = null;

	private static MessageManager instance = new MessageManager();

	private MessageManager() {
		init();
	}

	public static MessageManager GetInstance() {
		return instance;
	}

	public HashMap<String, String> getMap() {
		return map;
	}

	public String getAlert(int id, Context c) {
		if (map == null) {
			new Thread() {
				public void run() {
					getMessageInfo();
				};
			}.start();
			return c.getString(id);
		}
		String key = keyAndId.get(id);
		String value = map.get(key);
		if (value == null) {
			value = c.getString(id);
		}
		return value;

	}

	private void init() {
		keyAndId = new HashMap<Integer, String>();
		keyAndId.put(R.string.common_get_data_fail, "common_get_data_fail");
		keyAndId.put(R.string.movie_detail_balance_not_enough,
				"movie_detail_balance_not_enough");
		keyAndId.put(R.string.mymagic_cc_recharge_pwd_wrong,
				"mymagic_cc_recharge_pwd_wrong");
		keyAndId.put(R.string.mymagic_cc_recharge_card_disable,
				"mymagic_cc_recharge_card_disable");
		keyAndId.put(R.string.mymagic_cc_recharge_card_mismatching,
				"mymagic_cc_recharge_card_mismatching");
		keyAndId.put(R.string.mymagic_cc_recharge_card_uesed,
				"mymagic_cc_recharge_card_uesed");
		keyAndId.put(R.string.mymagic_cc_recharge_pwd_excess_time,
				"mymagic_cc_recharge_pwd_excess_time");
		keyAndId.put(R.string.mymagic_cc_order_not_enough,
				"mymagic_cc_order_not_enough");
		keyAndId.put(R.string.mymagic_cc_recharge_card_hint,
				"mymagic_cc_recharge_card_hint");
	}

	public void getMessageInfo() {
		String url = AuthManager.GetInstance().getUrlList().getUiHint();
		String httpMessage = "";
		StringBuffer buffer = new StringBuffer();
		LogUtil.d("getMessageInfo-->" + url);
		if (NetUtil.connectServer(url, buffer)) {
			httpMessage = buffer.toString();
			MessageParser parser = new MessageParser(httpMessage);
			parser.parser();
			map = parser.getResult();
		}
	}

}
