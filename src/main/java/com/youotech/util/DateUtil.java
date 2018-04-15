package com.youotech.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间处理的公共类
 * 
 * @author sjc
 */
public class DateUtil {

	private static String format1 = "yyyy-MM-dd HH:mm:ss";
	private static String format2 = "yyyy年MM月dd日 HH时mm分ss秒";
	private static String format3 = "yyyy-MM-dd";
	private static String format4 = "yyyy-MM";
	private static String format5 = "MM-dd";

	/**
	 * 将各种格式的字符串转成Date类型的时间格式
	 * 
	 * @param stringTime
	 * @return
	 */
	public static Date stringToDate(String stringTime) {
		Date date = null;
		try {
			if (stringTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}}")) {
				date = new SimpleDateFormat(format1).parse(stringTime);
			} else if (stringTime.matches("\\d{4}年\\d{2}月\\d{2}日 \\d{2}时\\d{2}分\\d{2}秒}")) {
				date = new SimpleDateFormat(format2).parse(stringTime);
			} else if (stringTime.matches("\\d{4}-\\d{2}-\\d{2}")) {
				date = new SimpleDateFormat(format3).parse(stringTime);
			} else if (stringTime.matches("\\d{4}-\\d{2}")) {
				date = new SimpleDateFormat(format4).parse(stringTime);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回date当月的第一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthFristDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String time = new SimpleDateFormat(format3).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回date当月的最后一天时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getMonthLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		String time = new SimpleDateFormat(format3).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回date去年同一天时间
	 * 
	 * @param stringDate(String类型)
	 */
	public static String getLastYearTime(String stringDate) {
		Date date = stringToDate(stringDate);
		return getLastYearTime(date);
	}

	/**
	 * 返回date去年同一天时间
	 * 
	 * @param date(Date类型)
	 */
	public static String getLastYearTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
		String time = new SimpleDateFormat(format3).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回date今年1月1号
	 * 
	 * @param date(String类型)
	 */
	public static String getYearStartTime(String stringDate) {
		return getYearStartTime(stringToDate(stringDate));
	}

	/**
	 * 返回date今年1月1号
	 * 
	 * @param date(Date类型)
	 */
	public static String getYearStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);// 设置月份为1月
		return getMonthFristDay(calendar.getTime());
	}

	/**
	 * 返回date去年1月1号
	 * 
	 * @param date(String类型)
	 */
	public static String getLastYearStartTime(String stringDate) {
		return getLastYearStartTime(stringToDate(stringDate));
	}

	/**
	 * 返回date去年1月1号
	 * 
	 * @param date(Date类型)
	 */
	public static String getLastYearStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);// 年减去一年
		calendar.set(Calendar.MONTH, 0);// 设置月份为1月
		return getMonthFristDay(calendar.getTime());
	}

	/**
	 * 返回date明年1月1号
	 * 
	 * @param date(String类型)
	 */
	public static String getNextYearStartTime(String stringDate) {
		return getLastYearStartTime(stringToDate(stringDate));
	}

	/**
	 * 返回date明年1月1号
	 * 
	 * @param date(Date类型)
	 */
	public static String getNextYearStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);// 年加上一年
		calendar.set(Calendar.MONTH, 0);// 设置月份为1月
		return getMonthFristDay(calendar.getTime());
	}

	/**
	 * 返回date上个月同一天时间
	 * 
	 * @param date(String类型)
	 */
	public static String getLastMonthTime(String stringDate) {
		Date date = stringToDate(stringDate);
		return getLastMonthTime(date);
	}

	/**
	 * 返回date上个月同一天时间
	 * 
	 * @param date(Date类型)
	 */
	public static String getLastMonthTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String time = new SimpleDateFormat(format3).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回date上个月时间(格式：yyyy-MM)
	 * 
	 * @param date(Date类型)
	 */
	public static String getLastMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		String time = new SimpleDateFormat(format4).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回上一周时间（当天减去七）
	 */
	public static Date getLastWeek() {
		String time = getLastWeek(new Date());
		Date date = null;
		try {
			date = new SimpleDateFormat(format1).parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 返回上一周时间（当天减去七）
	 */
	public static String getLastWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
		String time = new SimpleDateFormat(format1).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回本周周一（yyyy-MM-dd）
	 */
	public static String getFristDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_WEEK, 2);// 周日是1
		String time = new SimpleDateFormat(format3).format(calendar.getTime());
		return time;
	}

	/**
	 * 返回本周周一（MM-dd）
	 */
	public static String getNewDate() {
		String time = new SimpleDateFormat(format5).format(new Date());
		return time;
	}

	public static void main(String[] args) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) + 1);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		String time = new SimpleDateFormat(format1).format(calendar.getTime());
		System.out.println(new SimpleDateFormat(format5).format(new Date()));
	}
}
