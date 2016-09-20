package my;

public class ForceConversionTest {
	public static void main(String[] args) {
		BClass1 b = new BClass1(1,2);//类加载机制，先父类实例化，若子类没有super父类构造，则调用父类的无参构造函数，否则调用super的构造方法
		AClass1 a = (AClass1)b;
		
//		BClass1 b2 = new BClass1();//类加载机制，先父类实例化，调用父类的无参构造函数
		
//		AClass1 a1 = new AClass1(1,2);
//		BClass1 b1 = (BClass1)a1;//报错，父类不能强制转换成子类
	}

}


class AClass1{
	public int a;
	public int b;
	public AClass1(){
		System.out.println("A类的无参构造器");
	}
	
	public AClass1(int a,int b){
		this.a = a;
		this.b = b;
		System.out.println("A类 两个参数构造器");
	}
}

class BClass1 extends AClass1{
	public BClass1(){
//		super();
		System.out.println("B类的无参构造器");
	}
	public BClass1(int a,int b){
		super(a, b);
		System.out.println("B类 两个参数构造器");
	}
	
}
