package com.cloud.web.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日期常用方法<br>
 * 提供对日期型数据的常用处理方法
 * 
 */
public final class DateUtil {
	// 日志对象
	private static Logger log = Logger.getLogger(DateUtil.class);

	// 日期格式化对象,日期型（yyyy-MM-dd）
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	// 日期时间格式化对象,日期时间型（yyyy-MM-dd HH:mm:ss）
	private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 日期格式:yyyy-mm-dd<br>
	 * 例如:2005-11-02
	 */
	public final static String DATE_PATTERN_LINE = "yyyy-MM-dd";

	/**
	 * 日期格式:yyyy/mm/dd<br>
	 * 例如:2005/11/02
	 */
	public final static String DATE_PATTERN_BIAS = "yyyy/MM/dd";
	
	public final static String DATET_DAY_LINE = "yyyyMMdd";

	/**
	 * 日期时间格式(24小时制):yyyy-mm-dd HH:mm:ss<br>
	 * 例如:2005-11-02 23:01:01
	 */
	public final static String DATETIME24_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";
	
	public final static String DATETIME24_PATTERN_STRING = "yyyyMMddHHmmss";

	/**
	 * 日期时间格式(12小时制):yyyy-mm-dd hh:mm:ss<br>
	 * 例如:2005-11-02 11:01:01
	 */
	public final static String DATETIME12_PATTERN_LINE = "yyyy-MM-dd hh:mm:ss";

	/**
	 * 日期时间格式(24小时制):yyyy/mm/dd HH:mm:ss<br>
	 * 例如:2005/11/02 23:01:01
	 */
	public final static String DATETIME24_PATTERN_BIAS = "yyyy/MM/dd HH:mm:ss";

	/**
	 * 日期时间格式(12小时制):yyyy/mm/dd hh:mm:ss<br>
	 * 例如:2005/11/02 11:01:01
	 */
	public final static String DATETIME12_PATTERN_BIAS = "yyyy/MM/dd hh:mm:ss";

//	// 静态初始化时区
//	static {
//		// 中国时区
//		TimeZone tzChina = TimeZone.getTimeZone("Asia/Chongqing");
//		DateUtil.dateTimeFormat.setTimeZone(tzChina);
//		DateUtil.dateFormat.setTimeZone(tzChina);
//	}

	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param now
	 *            给定日期
	 * @return 被格式化后的字符串
	 */
	public static String formatDate(Calendar now) {
		return formatDate(now, DATETIME24_PATTERN_LINE);
	}

	/**
	 * 根据指定的格式化模式,格式化日历数据<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param now
	 *            给定日期
	 * @param formatePattern
	 *            格式化模式
	 * @return 被格式化后的字符串<br>
	 */
	public static String formatDate(Calendar now,
			String formatePattern) {
		if (now == null) {
			return null;
		}
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATETIME24_PATTERN_LINE;
		}
		Date tempDate = now.getTime();
		SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
		return dateFormate.format(tempDate);
	}

	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            java.util.Date类型数据
	 * @param formatePattern
	 *            指定的日期格式化模式
	 * @return 格式化后的日期的字符串形式<br>
	 * 
	 */
	public static String formatDate(Date date, String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATETIME24_PATTERN_LINE;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}

	/**
	 * 将java.sql.Timestamp数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            Timestamp数据
	 * @param formatePattern
	 *            日期格式化模式
	 * @return 格式化后的日期的字符串形式
	 */
	public static String formatDate(Timestamp date,
			String formatePattern) {
		if (formatePattern == null || formatePattern.trim().length() <= 0) {
			formatePattern = DATETIME24_PATTERN_LINE;
		}
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat dateFormate = new SimpleDateFormat(formatePattern);
			return dateFormate.format(date);
		}
	}

	/**
	 * 将java.util.Date数据转换为指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            java.util.Date类型数据
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate(Date date) {
		return formatDate(date, DATETIME24_PATTERN_LINE);
	}
	
	public static String formatDateString(Date date) {
		return formatDate(date, DATETIME24_PATTERN_STRING);
	}

	/**
	 * 将代表日期的长整形数值转换为yyyy-MM-dd HH:mm:ss格式的字符串<br>
	 * 
	 * @param datetime
	 *            需要转换的日期的长整形数值
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long datetime) {
		return formatDate(datetime, DATETIME24_PATTERN_LINE);
	}

	/**
	 * 将代表日期的字符串转换yyyy-MM-dd HH:mm:ss格式的字符串
	 * 
	 * @param datetime
	 *            需要转换的日期
	 * @return 格式化后的日期字符串
	 */
	public static String formate(String datetime) {
		return formatDate(datetime, DATETIME24_PATTERN_LINE);
	}

	/**
	 * 将代表日期的字符串转换未指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 *            需要转换的日期的字符串
	 * @param formatePattern
	 *            指定的日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(String datetime, String formatePattern) {
		if (datetime == null || datetime.trim().length() <= 0) {
			return "";
		}
		try {
			Date date = null;
			if (formatePattern != null
					&& (formatePattern.equals(DATE_PATTERN_BIAS) || formatePattern
							.equals(DATE_PATTERN_LINE))) {
				date = parseDate(datetime);
			} else {
				date = parseDateTime(datetime);
			}

			return formatDate(date, formatePattern);
		} catch (Exception ex) {
			log.error("日期转换失败:", ex);
			throw new RuntimeException("日期转换失败:", ex);
		}
	}

	/**
	 * 将代表日期的长整形数值转换为y指定格式的字符串<br>
	 * 如果格式化模式为null或者为空,则默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param datetime
	 *            需要转换的日期的长整形数值
	 * @param formatePattern
	 *            指定的日期格式
	 * @return 格式化后的日期字符串
	 */
	public static String formatDate(long datetime, String formatePattern) {
		if (datetime <= 0) {
			return "";
		}
		try {
			Date date = new Date(datetime);
			return formatDate(date, formatePattern);
		} catch (Exception ex) {
			log.error("日期转换失败:", ex);
			throw new RuntimeException("日期转换失败:", ex);
		}
	}

	/**
	 * 将java.sql.Date数据转换为指定格式的字符串<br>
	 * 默认使用yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            java.sql.Date类型数据
	 * @return 格式化后的日期的字符串形式<br>
	 */
	public static String formatDate(java.sql.Date date) {
		return formatDate(toUtilDate(date));
	}

	/**
	 * 将java.sql.Date转换为java.util.Date数据类型
	 * 
	 * @param date
	 *            需要转换的java.sql.Date数据
	 * @return 转换后的java.util.Date数据
	 */
	public static Date toUtilDate(java.sql.Date date) {
		if (date == null) {
			return null;
		} else {
			return new Date(date.getTime());
		}
	}

	/**
	 * 得到当前系统日期
	 * 
	 * @return 得到系统当前日期
	 */
	public static Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyy-MM-dd格式
	 * 
	 * @param src
	 *            日期数据字符串
	 * @return java.util.Date型日期类型数据
	 */
	public static Date parseDate(String src) {
		if (src == null || src.trim().length() <= 0) {
			return null;
		}
		try {
			return DateUtil.dateFormat.parse(src);
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}

	/**
	 * 根据日期、小时、分钟、秒组合成日期时间
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @return 组合后的日期时间
	 */
	public static Date parseDate(String date, int hour, int minute,
			int second) {
		Calendar cal = Calendar.getInstance();

		Date dateObj = parseDate(date);
		cal.set(getYear(dateObj), getMonth(dateObj), getDay(dateObj), hour,
				minute, second);
		return cal.getTime();
	}

	/**
	 * 将字符串转化为日期型数据<br>
	 * 字符串必须是yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param src
	 *            日期数据字符串
	 * @return java.util.Date型日期时间型数据
	 */
	public static Date parseDateTime(String src) {
		if (StringUtils.isEmpty(src)) {
			return null;
		}

		try {
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return dateTimeFormat.parse(src);
		} catch (Exception pe) {
			throw new RuntimeException(pe);
		}
	}

	/**
	 * 解析Date，字符串必须是yyyy-MM-dd HH:mm:ss格式
	 * 
	 * @param src
	 *            日期数据字符串
	 * @return
	 */
	public static Date parseAllDate(String src) {
		try {
			if (src == null || src.equals(""))
				return null;
			if (src.length() == 10) {
				return dateFormat.parse(src);
			} else if (src.length() == 19) {

				return dateTimeFormat.parse(src);
			} else if (src.length() > 19) {
				src = src.substring(0, 19);
				return dateTimeFormat.parse(src);
			} else {
				throw new RuntimeException(
						"长度不符。日期格式为:yyyy-mm-dd:,时间格式为:yyyy-mm-dd hh:mi:ss");
			}

		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}

	/**
	 * 将java.util.Date转换为java.sql.Date数据类型
	 * 
	 * @param date
	 *            需要转换的java.util.Date数据
	 * @return 转换后的java.sql.Date数据
	 */
	public static java.sql.Date toSqlDate(Date date) {
		if (date == null) {
			return null;
		} else {
			return new java.sql.Date(date.getTime());
		}
	}

	/**
	 * 将java.util.Date转换为java.sql.Timestamp
	 * 
	 * @param date
	 *            需要转换的java.util.Date数据
	 * @return 转换后的java.sql.Timestamp
	 */
	public static Timestamp toTimestamp(Date date) {
		if (date == null) {
			return null;
		} else {
			return new Timestamp(date.getTime());
		}
	}
	
	public static Timestamp getCurrentTime() {
		return DateUtil.toTimestamp(new Date(System.currentTimeMillis()));
	}
	

	/**
	 * 得到指定年月的最后一天
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @return 指定年月的最后一天
	 */
	public static Date getMonthLastDay(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar lCal = Calendar.getInstance();
			lCal.set(year, month, 1);
			lCal.add(Calendar.DATE, -1);
			return lCal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}

	/**
	 * 得到指定年月的第一天
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @return 指定年月的第一天
	 */
	public static Date getMonthFirstDay(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, 1, 0, 0, 0);
			return cal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}

	/**
	 * 得到指定年月的最后一天的最后小时分秒
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @return 年月的最后一天的最后小时分秒
	 */
	public static Date getMonthLastDatetime(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar lCal = Calendar.getInstance();
			lCal.set(year, month, 1, 23, 59, 59);
			lCal.add(Calendar.DATE, -1);
			return lCal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}

	/**
	 * 得到指定年月的第一天的开始小时分秒
	 * 
	 * @param year
	 *            指定年
	 * @param month
	 *            指定月
	 * @return 年月的第一天的开始小时分秒
	 */
	public static Date getMonthFirstDatetime(int year, int month) {
		if (month >= 1 && month <= 12) {
			Calendar cal = Calendar.getInstance();
			cal.set(year, month - 1, 1, 0, 0, 0);
			return cal.getTime();
		} else {
			throw new RuntimeException("月份传入错误必须介于1和12之间");
		}
	}

	/**
	 * 得到指定日期所在周的指定星期几的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param dayOfWeek
	 *            指定星期几
	 * @return 指定星期几的日期
	 */
	public static Date getDateOfWeek(Date date, int dayOfWeek) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		return c.getTime();
	}

	/**
	 * 得到指定日期为当前年的第几周
	 * 
	 * @param date
	 *            指定日期
	 * @return 当前年的第几周
	 */
	public static int getWeekOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到指定年的第几周的第一天日期
	 * 
	 * @param year
	 *            指定年
	 * @param nWeek
	 *            第几周
	 * @return 第一天日期
	 */
	public static Date getWeekOfFirstDate(int year, int nWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, nWeek);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		return c.getTime();
	}

	/**
	 * 得到指定年的第几周的最后一天日期
	 * 
	 * @param year
	 *            指定年
	 * @param nWeek
	 *            第几周
	 * @return 最后一天日期
	 */
	public static Date getWeekOfLastDate(int year, int nWeek) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, nWeek);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		return c.getTime();
	}

	/**
	 * 得到当前年
	 * 
	 * @return 当前年
	 */
	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到日期中的年份
	 * 
	 * @param date
	 *            日期
	 * @return 年份
	 */
	public static int getYear(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到日期中的月份
	 * 
	 * @param date
	 *            日期
	 * @return 月份
	 */
	public static int getMonth(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH);
	}

	/**
	 * 得到日期中的天
	 * 
	 * @param date
	 *            日期
	 * @return 天
	 */
	public static int getDay(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到日期中的小时
	 * 
	 * @param date
	 *            日期
	 * @return 小时
	 */
	public static int getHour(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到当前月<br>
	 * 0:一月;1:二月;....;11:十二月
	 * 
	 * @return 当前月
	 */
	public static int getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH);
	}
	
	/**
	 * 将指定的日期时间字符串加上秒数返回新的日期时间字符串。如将1999-12-31 23:59:59 加上3600秒，结果是2000-01-01
	 * 00:59:59
	 * 
	 * @param datetime
	 *            日期时间字符串
	 * @param seconds
	 *            秒数
	 * @return java.lang.String 日期时间字符串
	 */
	public static String addDatetimeBySecond(String datetime, int seconds) {
		Date date1 = DateUtil.parseDate(datetime);
		// 使用日历加秒数
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date1);
		calendar.add(Calendar.SECOND, seconds);
		return getDate(calendar.getTime(),getSystemDateLongPattern());
	}

	public static Date addDatetimeBySecond(Date datetime, int seconds) {
		return DateUtil.parseDate(addDatetimeBySecond(DateUtil
				.toString(datetime,getSystemDateLongPattern()), seconds));
	}
	
	/**
	 * 将指定日期转换为格式字符串，例如2002-12-12
	 * 
	 * @param date
	 *            日期对象
	 * @return java.lang.String
	 */
	public static String getDate(Date date,String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
	
	/*
	 * 将日期，时间按系统设置的格式转换成字符串
	 */
	public static String toString(Date date, String pattern) {
		if (date == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat(pattern);
		return dtFmt.format(date);
	}
	
	public static String toString(Timestamp timestamp, String pattern) {
		if (timestamp == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat(pattern);
		return dtFmt.format(timestamp);
	}
	
	public static String getSystemDateLongPattern() {
		return "yyyy-MM-dd HH:mm:ss";
	}
	
	public static boolean isLastDayOfMonth() {
		Date a = new Date();
        Calendar b = Calendar.getInstance();
        b.setTime(a);
        int lastDay = b.getActualMaximum(Calendar.DAY_OF_MONTH);
        int now = b.get(Calendar.DAY_OF_MONTH);
        return now == lastDay;
	}
	
	/**
	 * time 格式：HH:mm:ss
	 * @param timeString
	 * @return
	 */
	public static long getLong(String timeString) {
		long time = 1L;
		String str[] = timeString.split(":");
		time = Long.valueOf(str[0]) * 3600 + Long.valueOf(str[1]) * 60  + Long.valueOf(str[2]);
        return time;
	}
	
	/**
	 * 得到星期日期中的天
	 * 
	 * @param date
	 *            日期
	 * @return 天
	 */
	public static int getWeekDay(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
//	public static Date addDatetimeByYear(Date datetime, int yrs) {
//		return DateUtil.parseDate(addDatetimeByYear(
//				DateUtil.toString(datetime), yrs));
//	}
	
	/*
	 * 将日期，时间按系统设置的格式转换成字符串
	 */
	public static String toString(Date date) {
		if (date == null)
			return null;
		DateFormat dtFmt = new SimpleDateFormat(getSystemDateShortPattern());
		return dtFmt.format(date);
	}
	
//	public static String addDatetimeByYear(String datetime, int yrs) {
//		Date date1 = DateUtil.parseDate(datetime);
//		// 使用日历加天数
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.setTime(date1);
//		calendar.add(Calendar.YEAR, yrs);
//		// 返回结果
//		return getDate(calendar.getTime());
//	}
	
	public static Date addDatetimeByYear(Date date, int yrs) {
//		Date date1 = DateUtil.parseDate(datetime);
		// 使用日历加天数
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, yrs);
		// 返回结果
		return calendar.getTime();
	}
	
	public static Date addDatetimeByMonth(Date date, int month) {
		// 使用日历加天数
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		// 返回结果
		return calendar.getTime();
	}
	
	public static Date addDatetimeByWeek(Date date, int num) {
		// 使用日历加天数
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, num);
		// 返回结果
		return calendar.getTime();
	}
	
	public static Date addDatetimeByDay(Date date, int num) {
		// 使用日历加天数
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, num);
		// 返回结果
		return calendar.getTime();
	}
	
	/**
	 * 将指定日期转换为格式字符串，例如2002-12-12
	 * 
	 * @param date
	 *            日期对象
	 * @return java.lang.String
	 * @roseuid 3F39FE450366
	 */
	public static String getDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				getSystemDateShortPattern());
		return formatter.format(date);
	}
	
	public static String getSystemDateShortPattern() {
//		return ConfigurationHandler.getInstance().getConfig(
//				ConfigConstants.FORMAT_DATE_SHORT);
		return "yyyy-MM-dd";
	}
	
	/**
	 * time 格式：yyyy:MM:dd HH:mm:ss
	 * @param timeString
	 * @return
	 */
	public static long getDateLong(String timeString) {
		long time = 1L;
		String str[] = timeString.split(":");
		time = Long.valueOf(str[0]) * 3600 + Long.valueOf(str[1]) * 60  + Long.valueOf(str[2]);
        return time;
	}
	
	/**
	 * 
	 * @Title: getLong 
	 * @author: stlife
	 * @date: 2015年1月7日 上午11:47:24 
	 * @Description: TODO
	 * @param 
	 * 		@param time	HH:mm:ss
	 * 		@return 
	 * @return long 
	 * @throws 
	 *
	 */
	public static long getTimeLong(String timeStr) {
		long time = 1L;
		String str[] = timeStr.split(":");
		time = Long.valueOf(str[0]) * 3600 + Long.valueOf(str[1]) * 60  + Long.valueOf(str[2]);
        return time;
	}
	
	/**
	 * 
	 * @Title: beforeByDate 
	 * @author: stlife
	 * @date: 2015年4月2日 上午11:20:02 
	 * @Description: 
	 * 		在目标日期之前返回true
	 * @param 
	 * 		@param date	目标日期
	 * 		@return 
	 * @return boolean 
	 * @throws 
	 *
	 */
	public static boolean beforeByDate(Date date) {
		// 使用日历加天数
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		Date curDate = df.parse(date);
//		GregorianCalendar calendar = new GregorianCalendar();
//		calendar.setTime(date);
		Date curDate = new Date();
		return date.getTime() >= curDate.getTime();
	}
	
	public static boolean beforeByTime(Timestamp time) {
		Timestamp curT = new Timestamp(System.currentTimeMillis());
		return curT.before(time);
	}
	
	/**
	 * 获取时间长度  1天23小时50分
	 * @param time
	 * @return
	 */
	public static String getTimeStrLong(Long time) {
		String str = "";
		
		if(time == null){
			time = 0L;
		}
		
		Long day = 0L,hour = 0L,minute = 0L;
		day = time / (24 * 3600);
		if(day > 0){
			str = day + "天";
		}
		
		hour = time % (24 * 3600);
		hour = hour / 3600;
		if(hour > 0){
			str += hour + "时";
		}
		
		minute = time % 3600;
		minute = minute / 60;
		if(minute > 0){
			str += minute + "分";
		}
		return str;
	}
	
	public static Timestamp parseTime(String src) {
		if (StringUtils.isEmpty(src)) {
			return null;
		}

		try {
			Date date = DateUtil.dateTimeFormat.parse(src);
			return new Timestamp(date.getTime());
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		}
	}
	
	public static Date addDatetimeByMinute(Date date, int num) {
		// 使用日历加天数
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, num);
		// 返回结果
		return calendar.getTime();
	}
	
	public static void main(String[] args) {
//		Long cur = Long.valueOf(DateUtil.formatDate(new Date(System.currentTimeMillis()),"yyyyMMddHHmmss"));
//		String s = "2014-04-15 23:07:46";
//		Long end = Long.valueOf(DateUtil.formatDate(DateUtil.parseDateTime(s),"yyyyMMddHHmmss"));
//		System.out.print(cur < end);
//		long time = System.currentTimeMillis();
//		Date d = new Date(time);
//		String t = DateUtil.toString(d, getSystemDateLongPattern());
//		System.out.println(d);
//		System.out.println(new Date(time + 10 * 1000));
//		System.out.println();
//		System.out.println(DateUtil.getMonth(new Date()));
//        System.out.println(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        //
//        long curTimeL = DateUtil.getLong(DateUtil.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
//        String[] s = "20 20:47:17".split(" ");
//        System.out.println(DateUtil.toTimestamp((DateUtil.parseAllDate("2014-05-22 21:47:54"))));
//        Date o = DateUtil.parseAllDate("2014-05-22 21:47:54");
//        System.out.println(new java.sql.Date( ((java.util.Date) o).getTime()));
////        System.out.println(new Date());
//		Date cur = new Date(System.currentTimeMillis());
//		long t = System.currentTimeMillis();
//		for(int i = 0; i < 1000000000; i++){
//			continue;
//		}
//		Date last = new Date(System.currentTimeMillis());
//		long s = System.currentTimeMillis();
//        System.out.println(last.getTime()-cur.getTime());
//        int y = getYear(new Date());
//        int m = getMonth(new Date());
//        int day = getDay(new Date());
//        System.out.println(y + "-" + m + "-" + day);
//        System.out.println(DateUtil.parseDate(DateUtil.getYear(date) + "-06-06 09:26:16"));
//        Date d = DateUtil.parseDate("2015-04-02");
//        System.out.println(beforeByDate(d));
//        System.out.println(DateUtil.formatDate(new Date(System.currentTimeMillis()),"yyyy:MM:dd:HH:mm:ss"));
//		formatDate();
//		Timestamp cur = new Timestamp(System.currentTimeMillis());
////		Timestamp time1 = new Timestamp(time.getTime() - 1);
////		System.out.println(time.before(time1));
//		System.out.println(DateUtil.formatDate(cur,"yyyy-MM-dd HH:mm:ss"));
//		Date t = DateUtil.addDatetimeByMinute(cur, Integer.valueOf(10));
//		Timestamp val =  DateUtil.parseTime(DateUtil.formatDate(t,"yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtil.getWeekDay(new Date()));
//		System.out.println(new Date());
//		System.out.println(DateUtil.getDateOfWeek(new Date(System.currentTimeMillis()),1));
//		System.out.println(DateUtil.getTimeLong(DateUtil.toString(time, "HH:mm:ss")));
		
		Date curDate = DateUtil.getCurrentDate();
		Date preDate = DateUtil.addDatetimeByDay(curDate,2);
		String curMonth = DateUtil.getDate(curDate, "yyyy-MM-dd HH:mm:ss");
		String preMonth = DateUtil.getDate(preDate, "yyyy-MM-dd HH:mm:ss");
		System.out.println(curMonth);
		System.out.println(preMonth);

	}
}
