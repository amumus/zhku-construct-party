package com.mumu.zhkuconstructparty.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * 说明：日期处理
 * 创建人：FH Q313596790
 * 修改时间：2015年11月24日
 * @version
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfMonthYear = new SimpleDateFormat("yyyy-MM");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static String patton = "yyyy-MM-dd HH:mm:ss";
	private final static SimpleDateFormat sdfLongTime = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}
	/**
	 * 获取YYYY-MM格式
	 * @return
	 */
	public static String getYearMonth() {
		return sdfMonthYear.format(new Date());
	}
	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean
	* @throws
	* @author fh
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}
	/**
	 * Descrption:取得当前日期时间,格式为:YYYYMMDDHHMISS
	 *
	 * @return String
	 * @throws Exception
	 */
	public static String getNowLongTime() throws Exception {
		String nowTime = "";
		try {
			java.sql.Date date = null;
			date = new java.sql.Date(new Date().getTime());
			nowTime = sdfLongTime.format(date);
			return nowTime;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date,String patton) {
		DateFormat fmt = new SimpleDateFormat(patton);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String stringtodate(Date date){
		String format = sdfDay.format(date);
		return format;
	}


	/**
	 *
	 * 字符串形式转化为Date类型 String类型按照format格式转为Date类型
	 **/
	public static Date fromStringToDate(Date date) throws ParseException {
		return sdfDay.parse(sdfTime.format(date));
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    
    /** 
     * 获取给定日期N天后的日期 
     *  
     * @author GaoHuanjie 
     */  
    public static String getDateAfterNDays(String dateTime, int days) {  
        Calendar calendar = Calendar.getInstance();  
        String[] dateTimeArray = dateTime.split("-");  
        int year = Integer.parseInt(dateTimeArray[0]);  
        int month = Integer.parseInt(dateTimeArray[1]);  
        int day = Integer.parseInt(dateTimeArray[2]);  
        calendar.set(year, month - 1, day);  
        long time = calendar.getTimeInMillis();// 给定时间与1970 年 1 月 1 日的00:00:00.000的差，以毫秒显示  
        calendar.setTimeInMillis(time + days * 1000 * 60 * 60 * 24);// 用给定的 long值设置此Calendar的当前时间值  
        return calendar.get(Calendar.YEAR)// 应还书籍时间——年  
                + "-" + (calendar.get(Calendar.MONTH) + 1)// 应还书籍时间——月  
                + "-" + calendar.get(Calendar.DAY_OF_MONTH)// 应还书籍时间——日  
        ;  
    }  
    
    
    
    /**
  	 * 
  	 * 字符串形式转化为Date类型 String类型按照format格式转为Date类型
  	 **/
  	public static Date fromStringToDate(String format, String dateTime) throws ParseException {
  		Date date = null;
  		SimpleDateFormat sdf = new SimpleDateFormat(format);
  		date = sdf.parse(dateTime);
  		return date;
  	}
  	
  	/**
	 * 
	 * @param fromDate   小时间
	 * @param toDate   大时间
	 * 比如：2011-02-02 到  2017-03-02
	 *                                以年为单位相差为：6年
	 *                                以月为单位相差为：73个月
	 *                                以日为单位相差为：2220天
	 * @return
	 */
	public static int dayCompare(Date fromDate,Date toDate){
	    Calendar  from  =  Calendar.getInstance();
	    from.setTime(fromDate);
	    Calendar  to  =  Calendar.getInstance();
	    to.setTime(toDate);
	    //只要年月
	    int fromYear = from.get(Calendar.YEAR);
	    int fromMonth = from.get(Calendar.MONTH);
	    int toYear = to.get(Calendar.YEAR);
	    int toMonth = to.get(Calendar.MONTH);
	    int month = toYear *  12  + toMonth  -  (fromYear  *  12  +  fromMonth);
	    return month;
	}
	
	/** 
	    * 获取任意时间的下一个月 
	    * 描述:<描述函数实现的功能>. 
	    * @param repeatDate 
	    * @return 
	    */  
	   public static String getPreMonth(String repeatDate) {  
	       String lastMonth = "";  
	       Calendar cal = Calendar.getInstance();  
	       SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");  
	       int year = Integer.parseInt(repeatDate.substring(0, 4));  
	       String monthsString = repeatDate.substring(4, 6);  
	       int month;  
	       if ("0".equals(monthsString.substring(0, 1))) {  
	           month = Integer.parseInt(monthsString.substring(1, 2));  
	       } else {  
	           month = Integer.parseInt(monthsString.substring(0, 2));  
	       }  
	       cal.set(year,month,Calendar.DATE);  
	       lastMonth = dft.format(cal.getTime());  
	       return lastMonth;  
	   }  
    
	   
	   
	   /**
	    * 获取前一个月第一天
	    */
	   public static String getBeforeMonthFirstDay(){
		   
	   //获取前一个月第一天
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH,1);
        String firstDay = sdfDay.format(calendar1.getTime());
	    return firstDay;
	   }
	   
	   /**
	    * 获取前一个月最后一天
	    */
	   public static Date getBeforeMonthLastDay(){
		 //获取前一个月最后一天
		 Calendar calendar2 = Calendar.getInstance();
         calendar2.set(Calendar.DAY_OF_MONTH, 0);
         String lastDay = sdfDay.format(calendar2.getTime());
         Date fomatDate = fomatDate(lastDay);
         return fomatDate;
	   }
	   
	   /**
	    * 获取当前月第一天
	    * @return
	    */
	   public static String getNoWMonthFirsttDay(){
		 //获取当前月第一天：
        Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = sdfDay.format(c.getTime());
        return first;
	   }
	   /**
	    * 获取上个月的年月
	    * @return
	    */
	   public static String lastmonth(){
		    Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date()); // 设置为当前时间
	        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
	        Date date = calendar.getTime();
	        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
	        String lastMonth = dft.format(date);
			return lastMonth;
	   }
	   public static String nowmonth(){
		   Calendar calendar = Calendar.getInstance();
	        calendar.setTime(new Date()); // 设置为当前时间
	        Date date = calendar.getTime();
	        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
	        String lastMonth = dft.format(date);
			return lastMonth;
	   }
	   
    public static void main(String[] args) throws ParseException {
//    	System.out.println(getDays());
//    	System.out.println(getAfterDayWeek("3"));
//    	System.out.println(getBeforeMonthFirstDay());
//    	System.out.println(getNoWMonthFirsttDay());
//    	System.out.println(getBeforeMonthLastDay());
    	/*System.out.println();
    	String dateAfterNDays = getDateAfterNDays("2015-08-12",7);
    	SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");*/
		System.out.println(format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
    public static String format(final Date date, final String pattern) {
		if (null == date)
			return "";
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}


}
