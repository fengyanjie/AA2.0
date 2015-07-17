package com.voole.epg.cooperation;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class ControllerParser {
	private String httpMessage;
	private String type = null;
	private String mid = null;
	private String sid = null;
	private String message = null;

	public ControllerParser(String httpMessage) {
		this.httpMessage = httpMessage;
	}

	public String getMid() {
		return mid;
	}

	public String getSid() {
		return sid;
	}

	public String getType() {
		return type;
	}
	
	public String getMessage() {
		return message;
	}

	public void parser() {
		XmlPullParserFactory xmlPullParserFactory;
		try {
			xmlPullParserFactory = XmlPullParserFactory.newInstance();
			xmlPullParserFactory.setNamespaceAware(true);
			XmlPullParser xpp = xmlPullParserFactory.newPullParser();
			xpp.setInput(new StringReader(httpMessage));
			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if ("Message".equals(xpp.getName())) {
						int size = xpp.getAttributeCount();
						for (int i = 0; i < size; i++) {
							if ("type".equals(xpp.getAttributeName(i)))
								type = xpp.getAttributeValue(i);
						}
					} 
					
					if ("Body".equals(xpp.getName())) {
						message = xpp.nextText();
					}
					
					if ("Content".equals(xpp.getName())) {
						int size = xpp.getAttributeCount();
						for (int i = 0; i < size; i++) {
							if ("sid".equals(xpp.getAttributeName(i)))
								sid = xpp.getAttributeValue(i);
							else if ("filmmid".equals(xpp.getAttributeName(i)))
								mid = xpp.getAttributeValue(i);
						}
					} 

					break;
				case XmlPullParser.END_TAG:
					
					break;
				}
				eventType = xpp.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
