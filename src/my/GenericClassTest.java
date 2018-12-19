package my;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 泛型支持 在很大程度上都是为了让集合能记住其元素的数据类型。
 * 可以在编译时检查集合中的元素类型，如果试图添加不满足的类型，编译器会提示错误。
 * 
 * 泛型类，不管传入何种类型的参数，对JAVA来说 它们依然被当做同一个类处理，在内存中也只占用一块内存空间
 * 所以，在静态方法，静态变量，静态初始化块中，不允许有类型参数
 * 
 * 由于系统并不会真的产生泛型类，所以 用 instanceof 来判断 泛型类是错的
 * @author LiZhenhua
 *
 */
public class GenericClassTest {
	
	public static void getDate(myGeneric<?> s){
		System.out.println(s.get());
	}
	
	//泛型下限设置，？必须是String 及其子类
	public static void getLimitDate(myGeneric<? extends String> ss){
		System.out.println("泛型设置限制哦："+ss.get());
		
	}
	
	public static void main(String[] args) {
		new GeMethod().doMethod();	
		
		
//		List<String>[] lsa = new List<String>[10]; // Not really allowed.
//		Object o = lsa;
//		Object[] oa = (Object[]) o;
//		List<Integer> li = new ArrayList<Integer>();
//		li.add(new Integer(3));
//		oa[1] = li; // Unsound, but passes run time store check
//		String s = lsa[1].get(0); // Run-time error: ClassCastException.
		
		List<?>[] lsa = new List<?>[10]; // OK, array of unbounded wildcard type.
		Object o = lsa;
		Object[] oa = (Object[]) o;
		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(3));
		oa[1] = li; // Correct.
		String s = (String) lsa[1].get(0); // Run time error, but cast is explicit.  java.lang.Integer cannot be cast to java.lang.String
		System.out.println("泛型数组测试:s:"+lsa[1].get(0));


		
		
		myGeneric<String> ss = new myGeneric<String>("what");
		myGeneric<String> ss2 = new myGeneric<String>("what the fuck");
		myGeneric<Integer> integer = new myGeneric<Integer>(123);		
		
		System.out.println("ss:"+ss.get());
		System.out.println("ss2:"+ss2.get());
		System.out.println("integer:"+integer.get());
	
		getDate(ss);
		getDate(ss2);
		getDate(integer);
		getLimitDate(ss2);
		
	}

}

/**
 * 自定义的泛型类---在定义接口，类时声明类型参数，类型参数在整个接口，类体内可以当成类型使用K（类型有很多种，如String，Integer）
 * 几乎所有可使用 普通类型 的地方都可以使用这种 类型参数
 * @author LiZhenhua
 *
 * @param <K>
 */
//可以为任何类，接口增加泛型声明
// class 类名<泛型声明>
class myGeneric<K> {
	
//	static K s;静态的变量不允许使用类型参数！！
//	public static void bar(K m){} 静态的方法也不允许使用类型参数----是因为不管传入那种实参，对于java来说他们都是同一个类，只占同一个内存空间？

	private K date;   
	
	private List<K> s;// List<K> 是一种新的  数据类型 ,可以认为是List类型的子类型
	//包含泛型声明的类型 可以在定义变量，创建对象时传入一个类型实参，从而可以动态地生成无数多个逻辑上的子类，但这种子类在物理上并不存在

	public myGeneric(K date){
		set(date);
	}
	
	//构造器名还是原来的类名，不要增加泛型声明。。是 myGeneric 而非 myGeneric<K>
	public myGeneric(){
		
	}
	public K get() {
		return date;
	}

	public void set(K date) {
		this.date = date;
	}
}
/****泛型类，定义类型参数时 也可以设置上限（比较通配符上限）*****/
class myG<T extends Object>{
	private T k;
	public T getT(){
		return k;
	}
}
/**
 * 设置多个上限：至多一个父类上限，可以有多个接口上限，且 类上限必须在第一个位置
 * @author LiZhenhua
 *
 * @param <T>
 */
class myGe<T extends Object & Serializable>{
	private T k;
	public T getT(){
		return k;
	}
}

/**
 * 从泛型类派生的子类  被派生的类或接口不能带类型参数
 * class mg extends myGeneric<K>是错的，必须带入实际的类型，跟使用方法类似class mg extends myGeneric<String>
 * 或者class mg extends myGeneric 也是正确的，可以不用传入实际的类型参数
 * @author LiZhenhua
 *
 * 泛型派生后的类 已经不是泛型了
 */
class mg extends myGeneric<String>{
	
	
	//如果已经确定 派生后带入的类型参数，那么重写方法就必须使用该类型参数String
	@Override
	public String get() {
		// TODO Auto-generated method stub
		return super.get();
	}
	
	@Override
	public void set(String date) {
		// TODO Auto-generated method stub
		super.set(date);
	}
	
	/**
	 * 类型通配符：是一个问号？
	 * 将一个问号作为类型实参，传给myGeneric集合，表示各种泛型myGeneric的父类
	 * @param my
	 */
	public void test(myGeneric<?> my){
		List<?> c = new ArrayList<String>();
//		c.add("s");//c 是通配符类型的list ,无法知道 元素的类型，所以不能添加任何类型的元素
		c.add(null);//null 除外，null是所有引用该类型的 实例
	}
	
}

/**
 * 泛型派生后的类 已经不是泛型了
 * @author LiZhenhua
 *
 */
class mg2 extends myGeneric{
	//没有确定 派生后带入的类型参数，重写方法返回的是Object
	@Override
	public Object get() {
		// TODO Auto-generated method stub
		return super.get();
	}
}




/*
 * 限制型通配符的意义
 */

abstract class Shape{
	public abstract void draw(Canvas c);
}

class Circle extends Shape{

	@Override
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		System.out.println("画了一个圆");
		
	}	
}

class Rectangle extends Shape{

	@Override
	public void draw(Canvas c) {
		// TODO Auto-generated method stub
		System.out.println("画了一个矩形");
		
	}	
}

/**
 * 限制的泛型通配符 的意义，为何要限制？(此外，也可以在定义类型参数是设置上限)
 * @author LiZhenhua
 *
 */
class Canvas{
	/**
	 * 此画布意图画多个图形，圆和矩形
	 * 但是List<Shape> 并非 List<Circle> 和 List<Rectangle> 的父类
	 * List<Circle>对象带入将会报错
	 * @param shapes
	 */
	public void drawAll(List<Shape> shapes){
		for(Shape s:shapes){
			s.draw(this);
		}		
	}
	
	/**
	 * 可以用通配符来表示任何类型的父类型，但即用到了泛型，又进行了强制转化
	 * 臃肿而烦琐
	 * @param shapes
	 */
	public void drawAll2(List<?> shapes){
		for(Object o:shapes){
			Shape s = (Shape)o;
			s.draw(this);
		}		
	}
	
	/**
	 * 被限制的泛型通配符：<? extends Shape>，表示所有Shape泛型List的父类，即可以表示Cirle Rectangle的父类
	 * @param shapes
	 */
	public void drawAll3(List<? extends Shape> shapes){
		for(Shape s:shapes){
			s.draw(this);
		}		
		
//		shapes.add(new Circle());//由于无法知道List的类型（虽然是受限制的通配符），此处添加具体类型也是错的
	}
}

/****泛型方法***/

class GeMethod{	//类不是泛型哦。可以单单定义方法为泛型
	
	
	public void doMethod(){
		Object[] obs = new Object[10];
		obs[0] = "1";
		obs[1] = 2;
		Collection<Object> c = new ArrayList<Object>();
		addItem(obs, c);
		String[] str = {"a","b"};
		List<String> l = new ArrayList<String>();
//		addItem(str, l); 报错
	}
	
	/**
	 * 功能有限，只能将Object类型的元素放入Collection<Object>中
	 * @param objects
	 * @param c
	 */
	static void addItem(Object[] objects,Collection<Object> c){
		for(Object o : objects){
			c.add(o);
		}
	}
	
	static void addItem2(Object[] objects,Collection<?> c){
		for(Object o : objects){
//			c.add(o); 不能添加具体类型进入未知类型的泛型
		}
	}
	
	/**
	 * 泛型方法： 比普通方法多了一个类型形参声明，类型形参声明在 修饰符和返回值 之间，可以有多个类型形参，用逗号隔开
	 * 与 泛型类等不同的是，调用泛型方法时，无需显示传入实际类型参数，编译器会根据参数推断出类型参数的类型。
	 * @param t
	 * @param c
	 */
	static <T> void addItem3(T[] t,Collection<T> c){
		for(T o : c){
			c.add(o);
		}
	}
	
	/**
	 * 泛型方法 使用 类型通配符
	 * @param f
	 * @param t
	 */
	static <T> void addItem4(Collection<? extends T> f,Collection<T> t){
		for(T o : f){
			t.add(o);
		}
	}
}

