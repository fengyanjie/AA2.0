package com.voole.epg.cooperation.ali;



public class AliConfig {
	private static String partner          = "1054";
	private static String partnerNotifyUrl = "http://userauth.voole.com/Service.do?action=alipay";
	private static String prikey           = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAK+bGNj6+PNbRJS+KIcbUv5uvkrhsru/fV4h5Fn1z/7xIlbnPiwEOjHFwtG++w30d6TUcpUj/nTASBXr1geVW8eC05ay6iLlhIjZe7s0WMIGDv0bweK6OzZAkIPzR7KRUmNCmERsW3Bf6/58zQyhuteTHYP0jdRPeUyqHlkvDZWZAgMBAAECgYBhgxaBJpwnRn8GubF3lMy5THWOz1WYu/S52MgczEr7npsa9r2nY5GNIevU3OA8F4x4kyAtgRdDbaKZyaPIAkA71bvpSbEaJGRxUIwoPUHB1y5rZ/QXspklnFuSRf1tYUCiNvHmrEr89/s2Qmx7MCGQ6ejL62HeAIRpSjigbVuAAQJBAN74io273gQKfZdNSXb8gS07rOkIOSX2BxPfTInZRdXSOIxwvWnskl7edGToZbxWCDfGWSko6MDl1MuscfUUt5kCQQDJnmOAqzs4ChIWz3HXoJQvbrrL6Y5LYwJXD1HZwrPf7N4t0yFGZyYEQmUi9dU8McKdK+f3IuYGbVtKUBAKco4BAkAcWqLT70YGYYcjDHMBLI8Iv4P3hlIuH6E6x7Sl+vlEfZ0vTs7nf2wFoGhQxLTbRF17KkAAWfFvRkrPM7iyH9ZRAkEAl5+76NndMSmxrOHZFXY3uuwlIpDEaEFPUzW8Mm3QZEh4VjH/RjjwSCe+c2o5MImjeBu62QqvvomjK+HGDnVUAQJAZb0/7vGvmLEYoUbbDGQ+T3pDmjMGBccrrnZs58zDCxTRJ7lpfqWui3InBvcEDVv11LzP1wOqtcbflZXgC+1g/Q==";

	public static String getPartner() {
		return partner;
	}

	public static String getPartnerNotifyUrl() {
		return partnerNotifyUrl;
	}

	public static String getPrikey() {
		return prikey;
	}
}
