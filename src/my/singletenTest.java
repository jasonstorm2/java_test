package my;

public class singletenTest {
	public static void main(String[] args) {
		int s = person.s;
		person a1 = person.getInstance();
		person a2 = person.getInstance();
		person a3 = person.getInstance();
		
		person d1 = person.getInstance2();
		person d2 = person.getInstance2();
		person d3 = person.getInstance2();
		person d4 = person.getInstance2();
		System.out.println(d1==d2&d2==d3&d2==d4);
		System.out.println(a1==a2|a2==a3);
		
		
	}

}

class person{
	
	static int s = 0;
	
	public person(){
		System.out.println("person的无参构造函数");
	}
	
	public person(String s){
		System.out.println("person的有参构造函数");
	}
	
	public static person getInstance(){
		return new person("d");
	}
	
	public static person getInstance2(){
		return instanceClass.p;
	}
	
	public static class instanceClass{
		private static person p = new person("s");
	}
	
}
