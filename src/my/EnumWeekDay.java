package my;

public enum EnumWeekDay {	
	Mon("Monday",1),//---->Mon��һ���࣬�����˹��캯��EnumWeekDay(),����ֵdayΪ��Monday
	Tue("Tuesday",2),//һ��EnumWeekDa��Ԫ�ػ��������ڶ���
    Wen("Wednesday",3),//���Ծ�̬����EnumWeekDay.Wen ==Wen
    Thu("Thursday",4),//������ ���ʵ����   ���������캯��Ҫ����Ĳ�����
    Fri("Friday",5),
    Sat("Saturday",6),
    Sun("Sunday",7),
	What("ʲô������",-1);
	
	private final String day;	
	private int index;
	private  EnumWeekDay(String day,int index){//���췽������һ��ö��(���붨�壡)������"Sunday" Sun
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
		
		EnumWeekDay  week = EnumWeekDay.Fri;//��ȡһ��weekday���󡣡�ÿһ��enumerate�����൱��һ���ࡣ
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

	@Override
	public String toString() {
		return "EnumWeekDay{" +
				"day='" + day + '\'' +
				", index=" + index +
				'}';
	}
}




