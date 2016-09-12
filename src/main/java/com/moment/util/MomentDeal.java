package com.moment.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.moment.beans.momentRecommend.Moments;

public class MomentDeal {

	/**
	 * 设置灵感内容，具体内容content制空，是否关注全部设为关注，时间设为多少天：小时：分钟前
	 * @param momentList
	 * @return
	 */
	public static List<Moments> setMoment(List<Moments> momentList,int flag)
	{
		try
		{
			for(Moments moment : momentList)
			{
				//moment.setContent(moment.getContentAbstract());
				//moment.setContentAbstract(null);
//				String times = timeDeal(moment);
//				moment.setPostTime(times);
				moment.setClassifyId(null);
				if(1 == flag)
				    moment.setIsPublic(null);
			}
			return momentList;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 灵感详情获取时
	 * @param moment
	 * @return
	 */
	public static Moments setMoment(Moments moment,int flag)
	{
		try
		{
			moment.setIsFocused(flag);
			moment.setContentAbstract(null);
			//String times = timeDeal(moment);
			//moment.setPostTime(times);
			moment.setClassifyId(null);
			moment.setIsPublic(null);
			return moment;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	/**
	 * 灵感时间处理为多长时间前发布的，形式和微信朋友圈一样
	 * @param moment
	 * @return
	 * @throws ParseException
	 */
	public static String timeDeal(Moments moment) throws ParseException
	{
		Date nowTime = new Date();
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date postTime = dateformat.parse(moment.getPostTime());
		long diff = nowTime.getTime() - postTime.getTime();
		long days = diff/(24*60*60*1000);
		long hours = 0;
		long minutes = 0;
		if(days < 1)
		{
			hours = diff/(60*60*1000);
			if(hours < 0)
			{
			     minutes = diff/(60*1000);
			}
		}
		String times = days + ":" + hours + ":" + minutes;
		return times;	
	}
	
	/**
	 * 根据一个人的生日判断一个人的年龄
	 * @param moment
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	public static Integer getAge(String birthday)
	{
		try
		{
			Date nowTime = new Date();
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd"); 
			Date birthTime = dateformat.parse(birthday);
			int year = nowTime.getYear() - birthTime.getYear();
			return year;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return -1;
		}
	}
}
