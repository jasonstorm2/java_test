package JavaCoreLearn;

/**
 * 静态内部类（实际上正确的说法是 静态嵌套类）和非静态内部类之间到底有什么不同呢？下面是两者间主要的不同。 
 * （1）内部静态类不需要有指向外部类的引用。但非静态内部类需要持有对外部类的引用。
 * 
 * （2）非静态内部类 能够访问 外部类的静态和非静态成员。静态类 不能访问外部类的 非静态成员。
 * 		他只能访问外部类的静态成员。
 * （3）一个非静态内部类不能脱离外部类实体被创建，一个非静态内部类可以访问外部类的数据和方法，
 * 		因为他就在外部类里面。
 * 
 * 
 * 静态内部类的声明： ArrayAlg2.Pair p 即 外部类.静态内部类
 * 
 * 静态内部类的用处：封装数据结构，不必重新建一个class来封装
 * 		如在程序测试的时候，为了避免在各个Java源文件中书写主方法的 代码，
		可以将主方法写入到静态内部类中，以减少代码的书写量，让代码更加的简洁
		
  内部类和静态嵌套类的差异：
  内部类不能声明静态的成员变量和静态方法，而静态嵌套类可以
  内部类持有外围类的引用，而静态嵌套类没有
 * @author LiZhenhua
 *
 */
public class StaticInnerClassTest {
	public static void main(String[] args) {
		// 一个随机double数组
		double [] d = new double[20];
		for(int i=0;i<d.length;i++){
			d[i]=100*Math.random();
		}
		
	   /** 怎么创建静态内部类和非静态内部类的实例 **/
	   ArrayAlg2.Pair p1 = new ArrayAlg2.Pair(1, 2);
	   // 为了创建非静态内部类，我们需要外部类的实例
	   ArrayAlg2.Inner inner = new ArrayAlg2().new Inner();
		
	   // 调用静态方法，生成一个静态内部类对象
	   ArrayAlg2.Pair p = ArrayAlg2.minmax(d);
	   System.out.println("min="+p.getFirst());
	   System.out.println("max="+p.getSecond());
	   
	   // 测试静态方法是否能改变静态变量的值  --  可以改变
	   System.out.println("ArrayAlg2.country的值："+ArrayAlg2.country);
	   ArrayAlg2.changeName();
	   System.out.println("ArrayAlg2.country的值："+ArrayAlg2.country);
		  
	   
	}

}

class ArrayAlg2 {
	
	public String name = "jason";
	
	public static String country = "china";
	
	// 非静态内部类
	public class Inner{
		// 内部类中不允许静态变量存在
//		public static int age = 1; // 报错
//		public static void main(String[] args) { //报错
//			// 非静态内部类 不能有 静态的方法和变量
//		}
		
		public String accessName(){
			return name;
		}
		
		public String accessCountry(){
			return country;
		}
		
	}
	
	// 静态内部类
	public  static class Pair {
		private double first;
		private double second;

		public static void main(String[] args) {
			// 非静态内部类 不能有 静态的方法和变量
		}

		public Pair(double f, double s) {
			first = f;
			second = s;
		}

		public double getFirst() {
			return first;
		}

		public double getSecond() {
			return second;
		}
		// 静态内部类 无法改变外部内的 非静态成员，因为无法访问该成员
//		public void changeName(){
//			this.name =  "Jessica";
//		}
		// 静态内部类 可以改变外部内的 静态成员
		public static void changeName() {
			country = "Jessica";
		}

	}
	// 静态方法
	public static Pair minmax(double[] values) {
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (double v : values) {
			if (min > v)
				min = v;
			if (max < v)
				max = v;
		}
		return new Pair(min, max);
	}
	// 静态方法
	public static void changeName(){
		country = "America";
	}
}
