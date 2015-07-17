package com.voole.epg.base.common;

import java.io.StringReader;
import java.util.HashMap;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class MessageParser {
	private String httpMessage;
	private HashMap<String, String> map = null;

	public HashMap<String, String> getResult() {
		return map;
	}

	public MessageParser(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	public void parser() {
		XmlPullParserFactory xmlPullParserFactory = null;
		try {
			xmlPullParserFactory = XmlPullParserFactory.newInstance();
			xmlPullParserFactory.setNamespaceAware(true);
			XmlPullParser xpp = xmlPullParserFactory.newPullParser();
			xpp.setInput(new StringReader(httpMessage));
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					map = new HashMap<String, String>();
					break;
				case XmlPullParser.START_TAG:
					if ("List".equalsIgnoreCase(xpp.getName())) {
						String key = xpp.getAttributeValue(0);
						String value = xpp.nextText();
						map.put(key, value);
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				eventType = xpp.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
