package Annotation;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestAnn2 {
	/**
	 * 　*author lighter 　* 说明:具体关天Annotation的API的用法请参见javaDoc文档 　
	 */
	public static void main(String[] args) throws Exception {
		JavaEyer java = new JavaEyer();
		String sss=java.ss;
		//为什么不直接实例化,,,,实例化后无法使用
		String CLASS_NAME = "Annotation.JavaEyer";
		Class<?> test = Class.forName(CLASS_NAME);//---->it may throw a EXCEPTION
		//发发的数组集合。。统计收集
		Method[] method = test.getMethods();
		boolean flag = test.isAnnotationPresent(Description.class);//判断是否有用到@Description
		boolean FieldFlag = test.isAnnotationPresent(FieldUse.class);
		if (flag) {
			Description des = (Description) test
					.getAnnotation(Description.class);
			System.out.println("描述:" + des.value());
			System.out.println("-----------------");		
		}
		
		if(FieldFlag){
			FieldUse field = (FieldUse)test.getAnnotation(FieldUse.class);
			System.out.println("第一个参数："+field.value());
			System.out.println("另外个参数："+field.theOther());		
			
		}else{
			System.out.println("没有包含这个方法_nananana");
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
