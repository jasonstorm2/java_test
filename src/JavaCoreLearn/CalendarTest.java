package JavaCoreLearn;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTest {
	public  String test = "test";
	
	public void setTest(CalendarTest ca,String str){
		ca.test=str;
	}
	
	
	public static void main(String[] args) {
//		int s = Integer.valueOf("s");
		int d = Integer.parseInt("s");
		
		GregorianCalendar ca = new GregorianCalendar();
		

		
		int today = ca.get(Calendar.DAY_OF_MONTH);//记录当日号数
		int month = ca.get(Calendar.MONTH);//记录当月月份
		

		ca.set(Calendar.DAY_OF_MONTH, 1);//把日历设置为当月的1号
		
		int weekday = ca.get(Calendar.DAY_OF_WEEK);
		int firstdayofweek = ca.getFirstDayOfWeek();

		int indent = 0;
		while(weekday!=firstdayofweek){
			indent++;
			ca.add(Calendar.DAY_OF_MONTH, -1);			
			weekday = ca.get(Calendar.DAY_OF_WEEK);
		}
		System.out.println("weekday:"+weekday);
		System.out.println("indent**:"+indent);
		
		String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
		System.out.println("ca是哪一天："+ca.get(Calendar.DAY_OF_MONTH));
		do {
			System.out.printf("%4s",weekdayNames[weekday]);
			ca.add(Calendar.DAY_OF_MONTH, 1);
			weekday = ca.get(Calendar.DAY_OF_WEEK);
		} while (weekday!=firstdayofweek);
		System.out.println();
		
		for(int i=1;i<=indent;i++){//一号前面的空白
			System.out.print("       ");
		}
		
		ca.set(Calendar.DAY_OF_MONTH, 1);
		
		do {
			int day = ca.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d",day);
			//区分普通的日子和日历当天
			if(day == today){
				System.out.print("*   ");
			}else System.out.print("    ");
			
			ca.add(Calendar.DAY_OF_MONTH, 1);//日历前进一天
			weekday = ca.get(Calendar.DAY_OF_WEEK);
			
			if(weekday==firstdayofweek)System.out.println();//如果日期是周的第一天则另起一行
			
		} while (ca.get(Calendar.MONTH)==month);//不是当月则跳出运行
		
		if(weekday!=firstdayofweek){
			System.out.println();
		}
		
		
		
//		int cnt = 1;
//		do{			
//			if(month==ca.get(Calendar.MONTH)){
//				System.out.print(ca.get(Calendar.DAY_OF_MONTH)+"^");	
//				System.out.print(ca.get(Calendar.DAY_OF_WEEK)+"   ");
//			}
//			ca.add(Calendar.DAY_OF_MONTH, 1);
//			cnt++;
//			if(cnt>=7){
//				System.out.println();
//				cnt=0;
//			}
//		}while(month==ca.get(Calendar.MONTH));
		
		CalendarTest cad = new CalendarTest();
		cad.setTest(cad,"metoo");
		System.out.println(cad.test);
	}
		
}
