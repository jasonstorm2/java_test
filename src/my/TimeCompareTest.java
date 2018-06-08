package my;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

/**
 * calendar对象的各种用法
 * 以及java8的时间获取方法
 * 
 * 
 * java8引入了一套全新的时间日期API，本篇随笔将说明学习java8的这套API。

   java。time包中的是类是不可变且线程安全的。新的时间及日期API位于java.time中，下面是一些关键类

●Instant——它代表的是时间戳

●LocalDate——不包含具体时间的日期，比如2014-01-14。它可以用来存储生日，周年纪念日，入职日期等。

●LocalTime——它代表的是不含日期的时间

●LocalDateTime——它包含了日期及时间，不过还是没有偏移信息或者说时区。

●ZonedDateTime——这是一个包含时区的完整的日期时间，偏移量是以UTC/格林威治时间为基准的
 * @author Administrator
 *
 */
public class TimeCompareTest {

	
	public static void main(String[] args) {
		
		//**********格式转换:注意 yyyy/MM/dd 等，跟字母的大小写有关系*************
		//当前时间
		long cutime = System.currentTimeMillis();
		//时间格式对象
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm");//时间转换器，yyyyMMdd is the FORMAT to transform
		//时间格式对象 调用format(time) 转换 当前时间 为 String对象
		String timeNow=sim.format(cutime);				
		System.out.println(cutime);		
		System.out.println(timeNow);		
		
		//**********Calendar对象 的一些基本操作*************
		// 获得实例日历实例，该实例可以获取当前的年月日，时间等，还可以设置这些时间
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime(); //获得时间
		long time = ca.getTimeInMillis();
		//set(field，value)设置某个域指定值
		ca.set(Calendar.DAY_OF_MONTH, 1); 		 
		System.out.println("当前日："+ca.get(Calendar.DAY_OF_MONTH));
		System.out.println("当前月："+ca.get(Calendar.MONTH));
		//add(field，value) 增加或者减去指定域值
		ca.add(Calendar.DAY_OF_MONTH, 1);
		System.out.println("增加一日："+ca.get(Calendar.DAY_OF_MONTH));
		
		System.out.println("当前时间gettime："+ca.getTime());
		
		System.out.println("***********************************");
		//**********java8 LocalDateTime对象 的一些基本操作*************
		LocalDateTime localTime = LocalDateTime.now();
		System.out.println("年："+localTime.getYear());		
		System.out.println("月："+localTime.getMonthValue());
		System.out.println("日："+localTime.getDayOfMonth());
		System.out.println("时"+localTime.getHour());		
		System.out.println("分"+localTime.getMinute());
		System.out.println("秒"+localTime.getSecond());
		System.out.println("月的英文："+localTime.getMonth());		
		System.out.println("一年中的第几天："+localTime.getDayOfYear());
		System.out.println("周的第几天英文："+localTime.getDayOfWeek());
		
		System.out.println(localTime.getNano());
		System.out.println("getlong:"+localTime.getLong(ChronoField.MILLI_OF_DAY));
		System.out.println("当前时间："+Clock.systemDefaultZone().millis()); //java8
		
		DateTimeFormatter formate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String mate = localDate.format(formate);
		System.out.println("当前时间java8："+mate);
		LocalDateTime lo = localTime.minusDays(1);
		
		System.out.println("昨天日期："+lo.getDayOfMonth());
		System.out.println("昨天的当前日期："+lo.toLocalDate());
		System.out.println("昨天的当前时刻："+lo.toLocalTime());
		System.out.println("昨天的当前时间："+lo);
		
		
		
		System.out.println("***********************************");
		
		//获得当月天数
		getTotalDaysOfThisMonth(Calendar.getInstance());
		getTotalDaysOfThisMonth2(Calendar.getInstance());
	    //判断日期时间
		int aDayBefore=getDayBefore(cutime);
		System.out.println("一天前是几号:"+aDayBefore);
		//获取指定calendar的5时时间
		long time2=getTimeHourOfToday(cutime, 5);
		String later=sim.format(time2);
		System.out.println("5小时："+later);
		//获取指定calendar的推迟5时的时间
		long time5=getTimeHourPastFive(cutime, 5);
		String later5=sim.format(time5);
		System.out.println("5小时后:"+later5);
	}
	
	
	/**
	 * 获取前一天的日期
	 * @param time 取当天凌晨的话传入 System.currentTimeMillis() 即可
	 * @return
	 */
	public static int getDayBefore(long time) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		ca.add(Calendar.DATE, -1);
        return ca.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 获取当天的指定小时的时间
	 * @param time 取当天凌晨的话传入 System.currentTimeMillis() 即可
	 * @return hour 指定小时
	 * @return
	 */
	public static long getTimeHourOfToday(long time, int hour) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		ca.set(Calendar.HOUR_OF_DAY, hour);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		
		return ca.getTimeInMillis();
	}
	
	/**
	 * 获取当天的指定小时的时间
	 * @param time 取当天凌晨的话传入 System.currentTimeMillis() 即可
	 * @return hour 指定小时
	 * @return
	 */
	public static long getTimeHourPastFive(long time, int hour) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(time);
		ca.add(Calendar.HOUR_OF_DAY, hour);		
		return ca.getTimeInMillis();
	}
	
	/**
	 * 获得本月的最大天数，roll回滚，只在这个月回滚
	 * @param a
	 */
	public static void getTotalDaysOfThisMonth(Calendar a){
		System.out.println("当前时间Date："+a.getTime());
		System.out.println("当前时间："+a.getTimeInMillis());
		//set(域，值),set设置Calendar相关域的值
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    System.out.println("当月最大天数:"+maxDate);
	}
	
	
	/**
	 * 获得本月的最大天数
	 * @param a
	 */
	public static void getTotalDaysOfThisMonth2(Calendar a){
	    System.out.println("当月最大天数:"+a.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
	
	

}
