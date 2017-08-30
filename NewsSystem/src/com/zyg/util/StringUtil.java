package com.zyg.util;
/**
 * 新闻标题缩略显示处理类
 * 当新闻标题过长时，将其缩略显示，只显示部分标题
 * @author Mo
 *
 */
public class StringUtil {
	public  String SubStr(String aString) {
		if(aString.length() < 15)
			return aString;
		else 
			return aString.substring(0, 15) + ".....";
	}
}
