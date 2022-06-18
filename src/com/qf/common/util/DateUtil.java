package com.qf.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author yup
 *
 * 2021年9月8日
 */
public class DateUtil {
	
	public static final String DATE_LONG = "yyyy-MM-dd";
	public static final String TIME_LONG = "yyyy-MM-dd HH:mm:ss";
	public static final String TIMESTAMP_LONG = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static final String DATE_SHORT = "yyyyMMdd";
	public static final String TIME_SHORT = "yyyyMMddHHmmss";
	public static final String TIMESTAMP_SHORT = "yyyyMMddHHmmssSSS";
	
	
	/**
	 * 获取格式化后的当前时间
	 *
	 * @param format  日期格式
	 * @return
	 * 2021年3月15日
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
	
	/**
	 * 字符串转Date
	 *
	 * @param strDate   字符串时间
	 * @param format    时间格式
	 * @return
	 * 2020年9月1日
	 */
	public static Date stringToDate(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Date转字符串
	 *
	 * @param date      日期
	 * @param format   日期格式
	 * @return
	 * 2020年9月1日
	 */
	public static String dateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转SqlDate
	 *
	 * @param strDate     字符串时间
	 * @param format      日期格式
	 * @return
	 * 2020年9月1日
	 */
	public static java.sql.Date stringtoSqlDate(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(strDate);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * SqlDate转字符串
	 *
	 * @param date       日期
	 * @param format    日期格式
	 * @return
	 * 2020年9月1日
	 */
	public static String sqlDateToString(java.sql.Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 字符串转Timestamp
	 *
	 * @param strDate     字符串时间    时间格式：yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 * 2020年9月1日
	 */
	public static Timestamp stringToTimestamp(String strDate) {
		return Timestamp.valueOf(strDate);
	}
	
	/**
	 * 字符串转Timestamp
	 *
	 * @param strDate   字符串时间
	 * @param format    时间格式
	 * @return
	 * 2020年9月1日
	 */
	public static Timestamp stringToTimestamp(String strDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			Date date = sdf.parse(strDate);
			return new Timestamp(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Timestamp转字符串
	 * @author yup
	 *
	 * @param timestamp     时间
	 * @param format           时间格式
	 * @return
	 * 2020年9月1日
	 */
	public static String timestampToString(Timestamp timestamp, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(timestamp);
	}

	/**
	 * 日期的加减
	 * 2018年3月18日
	 * @param date           时间
	 * @param timeUnit    时间单位：年、月、日、小时、分钟、秒
	 * @param amount     日期加减量
	 * @return
	 */
	public static Date getAddDate(Date date, int timeUnit, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(timeUnit, amount);
		return c.getTime();
	}

}
