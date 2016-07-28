package my;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalandarTimeSetting {
	static Calendar calendar = Calendar.getInstance(); 
	
	/*
     * 根据输入的时间获取具体的日期
     */
    public static void getTime(long ll){
    	Calendar calendar = Calendar.getInstance(); 
        calendar.setTimeInMillis(ll);
        String str;
	    str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());  
	    System.out.println("根据输入的时间获得的日期是："+str);

    }
    
    public static void getTime2(long ll){
    	Calendar calendar = Calendar.getInstance(); 
        calendar.setTimeInMillis(ll);
        String str;
	    str = (new SimpleDateFormat("HH:mm:ss")).format(calendar.getTime());  
	    System.out.println("根据输入的时间获得的日期是："+str);

    }
    
    public static void getTimeByCalendarInstance(){
    	Calendar ca = Calendar.getInstance();
    	SimpleDateFormat simdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
    	Date date = ca.getTime();
    	String str = simdate.format(date);
    	System.out.println("打印一个calendar实例的时间："+str);
    	
    	System.out.println("周的第一天："+ca.getFirstDayOfWeek());
    	System.out.println("周日："+Calendar.SUNDAY);
    	//SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, Constant Field Values

    	System.out.println("周一："+Calendar.MONDAY);
    	System.out.println(Calendar.TUESDAY);
    	System.out.println(Calendar.WEDNESDAY);
    	System.out.println(Calendar.THURSDAY);
    	
    }
	public static void main(String[] args)  
	  {  
	    // 字符串转换日期格式  
	    DateFormat fmtDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    // 得到日期格式对象  
//	    Date date = fmtDateTime.parse(strDateMake);  
	  
	    // 完整显示日期时间--把一个date对象转换为特定的格式  
	    String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")).format(new Date());  //Formats a Date into a date/time string.
	    System.out.println("DateFormat.format(Date date):"+str);  
	  
	    // 创建 Calendar 对象  
	    Calendar calendar = Calendar.getInstance();  
	    // 初始化 Calendar 对象，但并不必要，除非需要重置时间  
	    long ll = System.currentTimeMillis();
//	    calendar.setTime(new Date());  
	    calendar.setTimeInMillis(ll);
	    System.out.println("当前时间的毫秒数："+ll);
	  
	    // setTime 类似上面一行  
	    // Date date = new Date();  
	    // calendar.setTime(date);  
	  
	    // 显示年份  
	    int year = calendar.get(Calendar.YEAR);  
	    System.out.println("YEAR is = " + String.valueOf(year));  
	  
	    // 显示月份 (从0开始, 实际显示要加一)  
	    int MONTH = calendar.get(Calendar.MONTH);  
	    System.out.println("MONTH is = " + (MONTH + 1));  
	  
	    // 今年的第 N 天  
	    int DAY_OF_YEAR = calendar.get(Calendar.DAY_OF_YEAR);  
	    System.out.println("DAY_OF_YEAR is = " + DAY_OF_YEAR);  
	  
	    // 本月第 N 天  
	    int DAY_OF_MONTH = calendar.get(Calendar.DAY_OF_MONTH);  
	    System.out.println("DAY_OF_MONTH = " + String.valueOf(DAY_OF_MONTH));  
	  
	    // 3小时以后  
	    calendar.add(Calendar.HOUR_OF_DAY, 3);  
	    int HOUR_OF_DAY = calendar.get(Calendar.HOUR_OF_DAY);  
	    System.out.println("HOUR_OF_DAY + 3 = " + HOUR_OF_DAY);  
	  
	    // 当前分钟数  
	    int MINUTE = calendar.get(Calendar.MINUTE);  
	    System.out.println("MINUTE = " + MINUTE);  
	  
	    // 15 分钟以后  
	    calendar.add(Calendar.MINUTE, 15);  
	    MINUTE = calendar.get(Calendar.MINUTE);  
	    System.out.println("MINUTE + 15 = " + MINUTE);  
	  
	    // 30分钟前  
	    calendar.add(Calendar.MINUTE, -30);  
	    MINUTE = calendar.get(Calendar.MINUTE);  
	    System.out.println("MINUTE - 30 = " + MINUTE);  
	  
	    // 格式化显示  
	    str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());  
	    System.out.println(str);  
	  
	    // 重置 Calendar 显示当前时间  
	    calendar.setTime(new Date());  
	    str = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(calendar.getTime());  
	    System.out.println(str);  
	  
	    // 创建一个 Calendar 用于比较时间  
	    Calendar calendarNew = Calendar.getInstance();  
	  
	    // 设定为 5 小时以前，后者大，显示 -1  
	    calendarNew.add(Calendar.HOUR, -5);  
	    System.out.println("时间比较-5：" + calendarNew.compareTo(calendar));  
	  
	    // 设定7小时以后，前者大，显示 1  
	    calendarNew.add(Calendar.HOUR, +7);  
	    System.out.println("时间比较+7：" + calendarNew.compareTo(calendar));  
	  
	    // 退回 2 小时，时间相同，显示 0  
	    calendarNew.add(Calendar.HOUR, -2);  
	    System.out.println("时间比较-2:" + calendarNew.compareTo(calendar)); 
	    
	    
	    calendar.set(Calendar.HOUR, 4);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("零点的毫秒数？？："+calendar.getTimeInMillis());
        System.out.println("是否零点？"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DAY_OF_MONTH)+calendar.get(Calendar.HOUR)+calendar.get(Calendar.MINUTE));
	    
	    //得到当月天数
	    Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE); 
	    System.out.println("当月天数："+maxDate);
	    a.get(Calendar.MONTH);
	    System.out.println("本月份："+a.get(Calendar.MONTH));
	    
	    getTime(1452569051917l);
	    getTime(1452828251917L);
	    getTimeByCalendarInstance();
	    
	  }  

}
