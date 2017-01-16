package my;

import java.lang.reflect.Field;

/**
 * 反射，获得类的 实例变量，类变量
 * getDeclaredField 得到指定名字 的域对象
 * getField 得到 public 修饰的 指定名字的 域对象
 * 
 * 域如果是一个对象，则需要对这个对象的类型进行判断，才能进行相应的逻辑
 * @author Administrator
 *
 */
public class FieldGetTest {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BeCall be = new BeCall();
		Field f = BeCall.class.getDeclaredField("a");
		
		/**获得 实例变量，须指定对象**/
		int a1 = (int)f.get(be);  //非static的field要输入相关的对象实例
		System.out.println(a1);	
		System.out.println("-----------------");		
		
		/**获得 类变量，无须指定对象**/
		Field fb = BeCall.class.getDeclaredField("b");
		int b1 = fb.getInt(null);  //static 的 field 随便输入对象，甚至可以是null
		int b2 = fb.getInt(be);
		int b3 = fb.getInt(12);
		int b4 = fb.getInt("");
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		System.out.println("-----------------");		
		
		Field s = BeCall.class.getField("s");  //public 的域，可以用 getField方法获得，不然会报错
		Field s1 = BeCall.class.getDeclaredField("s");  //获得声明名字的域
		System.out.println("String 实例变量反射getField获得的值："+s1.get(be));//没有判断类型，直接输出
		
		/*判断域对象的Class类型,并以此进行相应逻辑处理*/
		Class<?> type = s.getType();
		System.out.println("s Field的类型是："+type);
		Object obj = s.get(be);
		
		if(type.isAssignableFrom(String.class)){
			if(obj==null){
				System.out.println("所要查询的值是空的");
			}else{
				System.out.println("所要查询的值:"+obj);
			}
		}else{
			System.out.println(type);
		}
		
	}
}

class BeCall{
	int a = 1;
	static int b = 2;
	public String s = "fuck";
}
