package com.wut.tool;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.jna.platform.win32.OaIdl.DATE;

//工具类
public class tool {
	//String time 转换成 date 格式
	public static Date strDate(String strDate){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	//date格式 转化成 String time
	public static String dateStr(Date date){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);
		return dateString;
	}

	public static Date strDate1(String string1) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
