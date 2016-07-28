package my;

public class EnumWeekDayTest {
	public static void main(String args[]){
		//遍历枚举对象：.values()的使用
		for(EnumWeekDay we:EnumWeekDay.values()){
			System.out.println(we + "====>" + we.getDay()+"(index:"+we.getIndex()+")");		
		}
		EnumWeekDay.printDay(6); 
		EnumWeekDay.valueOf("Fri").printDay(5);
		
		//枚举类(enum class)的使用方式
		System.out.println("静态调用EnumWeekDay.Mon:"+EnumWeekDay.Mon);//EnumWeekDay.Mon 是一个EnumWeekDay对象
		System.out.println(EnumWeekDay.valueOf("Fri"));
		System.out.println("调用方法:"+EnumWeekDay.Fri.getDay());
		System.out.println(EnumWeekDay.valueOf("Fri").name());		    //EnumWeekDay.valueOf("Fri") 也是一个EnumWeekDay对象
		EnumWeekDay.Mon.putStingIn(EnumWeekDay.valueOf("Fri").getDay());
	}

}