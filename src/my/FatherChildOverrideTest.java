package my;


/**
 * 子类覆盖父类的方法，当 子类对象 调用该方法，则实际上是调用子类覆盖的方法
 * 
 * 覆盖的方法 如果调用 super()方法，则会先调用父类被覆盖的方法
 * 
 * super() 调用父类的方法
 * @author LiZhenhua
 *
 */
public class FatherChildOverrideTest extends fatherClass1{
	@Override
	public int c() {	
		int s = super.c();
		System.out.println("子类方法c调用");
		System.out.println("父类的返回值s:"+s);
		return 100000;
	}
	
	public static void main(String[] args) {
		FatherChildOverrideTest ff = new FatherChildOverrideTest();
		int zhi = ff.a();
		System.out.println("子类的返回值zhi:"+zhi);
	}
}

class fatherClass1 {
	public void dd(){
		
	}
	

	public int a() {
		return b();

	}

	public int b() {
		return c();
	}

	public int c() {
		System.out.println("父类方法c调用");
		return 11111;
	}

}
