package Chapter18;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


@Repeatable(Annos.class)
@interface Anno{}
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
	Anno[] value();
}

//使用人个注释修饰该类
@SuppressWarnings(value="unchecked")
@Deprecated
//使用重复注释修饰该类
@Anno
@Anno
public class ClassForNameTest {
	static{
		System.out.println("////类被初始化啦。");
	}
	//定义一个私有构造器
	private ClassForNameTest(){
		
	}
	//定义一个有参数的构造器
	public ClassForNameTest(String name){
		System.out.println("执行有参数的构造器");
	}
	
	public void info(){
		System.out.println("执行无参的info方法");
	}
	
	public void info(String str){
		System.out.println("执行有参数的info方法"+",其Str参数值："+str);
	}
	
	//定义一个测试用的内部类
	class Inner{

	}
    class Toto{
		
	}
	public static void main(String[] args) throws Exception {
		//下面的代码可以获取ClassTest对应的Class
		Class<ClassForNameTest> clazz = ClassForNameTest.class;
		//获取 全部构造器
		Constructor[] ctors = clazz.getDeclaredConstructors();
		
		System.out.println("ClassForNameTest的全部构造器如下：");
		for (Constructor c : ctors) {
			System.out.println(c);
		}
		//获取 全部 public构造器
		Constructor[] publicCtors = clazz.getConstructors();
		System.out.println("ClassForNameTest的全部 PUBLIC构造器如下：");
		for (Constructor c : publicCtors) {
			System.out.println(c);
		}
		//获取 全部public方法
		Method[] mds = clazz.getMethods();
		System.out.println("全部的public方法：");
		for (Method method : mds) {
			System.out.println(method);
		}
		//获取该 Class对象所对应的类 的指定方法
		System.out.println("带有字符串参数的info方法："+clazz.getMethod("info", String.class));
		//获取 全部注解
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("Class对象所对应的类的 全部注解如下：");
		for (Annotation annotation : anns) {
			System.out.println(annotation);
		}
		System.out.println("该Class元素上的@SuppressWarnings注解为："+Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)));
		
		System.out.println("该Class元素上的@Anno注解为："+Arrays.toString(clazz.getAnnotationsByType(Anno.class)));
		//获取 全部内部类
		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("全部内部类如下：");
		for (Class<?> class1 : inners) {
			System.out.println(class1);
		}
		
		//使用Class.forName加载ClassForNameTest的 Inner 内部类
		Class<?> inClazz = Class.forName("JavaCoreLearn.ClassForNameTest$Inner");
		//访问内部类 的外部类
		System.out.println("inClazz对应类的 外部类 为:"+inClazz.getDeclaringClass());
		
		System.out.println("父类所在的包："+clazz.getPackage());
		System.out.println("内部类类所在的包："+inClazz.getPackage()+"\n");
		System.out.println("ClassForNameTest的父类："+clazz.getSuperclass());
	}
}
