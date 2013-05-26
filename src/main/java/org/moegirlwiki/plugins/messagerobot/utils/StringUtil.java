package org.moegirlwiki.plugins.messagerobot.utils;

public class StringUtil {
	
	private StringUtil(){}
	
	public static boolean isNotBlank(String str){
		return str!=null&&!str.trim().isEmpty();
	}
}
