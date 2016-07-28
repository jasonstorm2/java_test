package my;

import java.lang.reflect.Field;

public class FieldGetTest {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BeCall be = new BeCall();
		Field f = BeCall.class.getDeclaredField("a");
		int a1 = (int)f.get(be);  //非static的field要输入相关的对象实例
		System.out.println(a1);	
		System.out.println("-----------------");		
		
		Field fb = BeCall.class.getDeclaredField("b");
		int b1 = fb.getInt(null);  //static 的 field 随便输入对象，甚至可以是null
		int b2 = fb.getInt(be);
		int b3 = fb.getInt(12);
		int b4 = fb.getInt("");
		
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
		
		Field s = BeCall.class.getField("s");  //public 的域，可以用 getField方法获得，不然会报错
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
