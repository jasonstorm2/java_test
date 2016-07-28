package my;

public class SuperTest extends FatherClass{
	int ss = 99;

	public SuperTest(int a, int b,int s) {
		super(a, b);
		// TODO Auto-generated constructor stub
		this.ss = s;
	}
	
	public static void main(String[] args) {
		SuperTest st = new SuperTest(66,77,88);
		System.out.println(st.a+""+st.b+""+st.ss);		
		System.out.println("创建对象时的系统时间："+st.time);
	}
}

class FatherClass {
	int a = 0;
	int b =11;
	long time = 0;
	public FatherClass(int a,int b){
		this.a = a;
		this.b = b;
		System.out.println("父类方法被调用");
		time = System.currentTimeMillis();
	}
}