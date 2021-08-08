package my;

public class EnumWeekDayTest {
	public static void main(String args[]){
		//����ö�ٶ���.values()��ʹ��
		for(EnumWeekDay we:EnumWeekDay.values()){
			System.out.println(we + "====>" + we.getDay()+"(index:"+we.getIndex()+")");
			System.out.println(we.hashCode());

		}
		EnumWeekDay.printDay(6); 
		EnumWeekDay.valueOf("Fri").printDay(5);
		
		//ö����(enum class)��ʹ�÷�ʽ
		System.out.println("��̬����EnumWeekDay.Mon:"+EnumWeekDay.Mon);//EnumWeekDay.Mon ��һ��EnumWeekDay����
		System.out.println(EnumWeekDay.valueOf("Fri"));
		System.out.println("���÷���:"+EnumWeekDay.Fri.getDay());
		System.out.println(EnumWeekDay.valueOf("Fri").name());		    //EnumWeekDay.valueOf("Fri") Ҳ��һ��EnumWeekDay����
		EnumWeekDay.Mon.putStingIn(EnumWeekDay.valueOf("Fri").getDay());
	}

}