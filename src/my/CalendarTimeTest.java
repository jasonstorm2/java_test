package my;

import java.util.Calendar;

public class CalendarTimeTest {
	public static void main(String[] args) {
    	Calendar calendar = Calendar.getInstance();
    	long ll = System.currentTimeMillis();
    	calendar.setTimeInMillis(ll);
    	System.out.println("当前时间的毫秒数："+ll);
    	
    	if(calendar.get(Calendar.HOUR)<4){
    		calendar.set(Calendar.DAY_OF_MONTH, Calendar.DAY_OF_MONTH-1);
    	}
    	
    	
    	calendar.set(Calendar.HOUR, 4);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
        System.out.println("零点的毫秒数？？："+calendar.getTimeInMillis());
        System.out.println("是否零点？"+calendar.get(Calendar.YEAR)+calendar.get(Calendar.MONTH)+calendar.get(Calendar.DAY_OF_MONTH)+calendar.get(Calendar.HOUR)+calendar.get(Calendar.MINUTE));
	    
	}

}
