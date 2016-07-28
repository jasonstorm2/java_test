package my;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeCompareTest {
	
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
	
	public static void main(String[] args) {
		//Current Time
		long cutime = System.currentTimeMillis();
		String nana ="1421154699843";
		//Time format
		SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd HH:mm");//时间转换器，yyyyMMdd is the FORMAT to transform
		String timeNow=sim.format(cutime);
				
		System.out.println(cutime);		
		System.out.println(timeNow);		
		
		
		//当月天数
		Calendar a = Calendar.getInstance();  
	    a.set(Calendar.DATE, 1);//把日期设置为当月第一天  
	    a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = a.get(Calendar.DATE);  
	    System.out.println(maxDate);
	    
	    //空的数组
	    int moonlen[] =new int[maxDate];
//	    for(int i=0;i<maxDate;i++){
//	    	moonlen[i]=0;
//	    	System.out.println("i:="+i+"数组值："+moonlen[i]);
//	    }
	    //数组转换字符串
	    String str="0";
//	    for(int i=0;i<maxDate;i++){
//	    	str+=String.valueOf(moonlen[i])+",";
//	    }
	    for(int i=1;i<maxDate;i++){
	    	str+=",0";
	    }
	    System.out.println(str);
	    
	    //判断日期时间
		int aDayBefore=getDayBefore(cutime);
		System.out.println("一天前天数:"+aDayBefore);
		//获取5小时
		long time2=getTimeHourOfToday(cutime, 5);
		String later=sim.format(time2);
		System.out.println("5小时"+later);
		//推后5小时
		long time5=getTimeHourPastFive(cutime, 25);
		String later5=sim.format(time5);
		System.out.println("5小时后:"+later5);
		
//		Calendar ca = Calendar.getInstance();		
//		ca.setTimeInMillis(time2);
		
		int [] getMoon={1,1,1,1,1,1,1,1,1,1,1};
		
		String str2="";
		for(int i=0;i<getMoon.length;i++){
			str2+=getMoon[i]+",";
		}
		
		System.out.println(str2);
		
		System.out.println(str2.substring(0,str2.length()-1));
		

		
	}

}
