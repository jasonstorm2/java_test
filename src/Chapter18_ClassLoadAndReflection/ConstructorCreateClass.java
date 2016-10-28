package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.Constructor;

public class ConstructorCreateClass {
	public static void main(String[] args) throws Exception {
		Class<?> jf = Class.forName("javax.swing.JFrame");
		//通过Class对象,获得一个带 字符串参数 的 构造器对象
		Constructor<?> ct = jf.getConstructor(String.class);
		//调用Constructor对象的newInstance方法创建对象
		Object ob = ct.newInstance("测试");
		            jf.newInstance();      //两个方法对比下
		System.out.println(ob);
		
	}

}
