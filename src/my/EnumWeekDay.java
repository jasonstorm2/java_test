package my;

public enum EnumWeekDay {	
	Mon("Monday",1),//---->Mon：一个类，调用了构造函数EnumWeekDay(),并赋值day为：Monday
	Tue("Tuesday",2),//一个EnumWeekDa的元素或者类似于对象
    Wen("Wednesday",3),//可以静态调用EnumWeekDay.Wen ==Wen
    Thu("Thursday",4),//类似于 类的实例化   类名（构造函数要输入的参数）
    Fri("Friday",5),
    Sat("Saturday",6),
    Sun("Sunday",7),
	What("什么都可以",-1);
	
	private final String day;	
	private int index;
	private  EnumWeekDay(String day,int index){//构造方法生成一个枚举(必须定义！)。。如"Sunday" Sun
		this.day = day;
		this.index = index;
	}
	
	public static void printDay(int i){
		switch(i){
		case 1: System.out.println("printDay:"+EnumWeekDay.Mon);break;
		case 2: System.out.println("printDay:"+EnumWeekDay.Tue);break;
		case 3: System.out.println("printDay:"+EnumWeekDay.Wen);break;
		case 4: System.out.println("printDay:"+EnumWeekDay.Thu);break;
		case 5: System.out.println("printDay:"+EnumWeekDay.Fri);break;
		case 6: System.out.println("printDay:"+EnumWeekDay.Sat);break;
		case 7: System.out.println("printDay:"+EnumWeekDay.Sun);break;		
		case 8: System.out.println("printDay:"+EnumWeekDay.What);break;
		 }
		
		EnumWeekDay  week = EnumWeekDay.Fri;//获取一个weekday对象。。每一个enumerate类型相当于一个类。
		}	
		
	public String getDay(){
		
		return day;
		}		
	public int getIndex(){
		return index;
	}
	public void putStingIn(String s){
		System.out.println("the String put in:"+s);
	}
}




