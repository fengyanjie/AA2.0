package com.voole.epg.view.mymagic;

import java.util.List;

import com.voole.epg.corelib.model.account.MessageInfo;

public interface MessageNotificationItemListener {
	public void onKey(List<MessageInfo> list,MessageInfo info);
}
