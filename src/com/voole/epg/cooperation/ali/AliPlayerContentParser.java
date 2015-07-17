package com.voole.epg.cooperation.ali;

import org.json.JSONException;
import org.json.JSONObject;


public class AliPlayerContentParser {
	private String httpMessage;
	private String mid = null;
	private String sid = null;

	public AliPlayerContentParser(String httpMessage) {
		this.httpMessage = httpMessage;
	}
	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public void parser() {
		try {
			JSONObject jsonObject = new JSONObject(httpMessage);
			mid = jsonObject.getString("mid");
			sid = jsonObject.getString("sid");
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
