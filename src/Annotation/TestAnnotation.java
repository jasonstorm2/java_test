package Annotation;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestAnnotation {
	/**
	 * 　* author lighter 　* 说明:具体关天Annotation的API的用法请参见javaDoc文档 　
	 */
	public static void main(String[] args) throws Exception {
		//为什么不直接实例化
		String CLASS_NAME = "Annotation.JavaEyer";
		Class<?> test = Class.forName(CLASS_NAME);//---->it may throw a EXCEPTION
		//发发的数组集合。。统计收集
		Method[] method = test.getMethods();
		boolean flag = test.isAnnotationPresent(Description.class);//判断是否有用到@Description
		if (flag) {
			Description des = (Description) test
					.getAnnotation(Description.class);
			System.out.println("描述:" + des.value());
			System.out.println("-----------------");
		}
		// 把JavaEyer这一类有利用到@Name的全部方法保存到Set中去
		Set<Method> set = new HashSet<Method>();
		for (int i = 0; i < method.length; i++) {
			//判断方法有用到@Name
			boolean otherFlag = method[i].isAnnotationPresent(Name.class);
			if (otherFlag)
				set.add(method[i]);
		}
		//遍历set
		for (Method m : set) {
			Name name = m.getAnnotation(Name.class);
			System.out.println(name.originate());
			System.out.println("创建的社区:" + name.community());
		}
	}
}
