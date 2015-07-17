package com.voole.epg.base.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtil {

	public static boolean isEmail(String email) {
		String check = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		return matcher.matches();
	}

	public static boolean isMobile(String mobile) {
		String check = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(mobile);
		return matcher.matches();
	}

	public static boolean isContainsChinese(String context) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pat = Pattern.compile(regEx);
		Matcher matcher = pat.matcher(context);
		return matcher.find();
	}

	public static boolean isAccountName(String name) {
		String check = "^[\\w\\*\\-\\.]{6,18}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(name);
		return matcher.matches();
	}

	// ~, {, }, \, |, #
	public static boolean isSpecial(String str) {
		String regex = "[^~{}|\"#]{1,}";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// 中文 空格
	public static boolean isChineseBlank(String str) {
		String regex = "^[^\\s\u4e00-\u9fa5]{6,16}$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	// 是否都为零
	public static boolean isNotAllZero(String str) {
		char[] chars = str.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if ('0' != chars[i])
				return true;
		}
		return false;
	}

}
