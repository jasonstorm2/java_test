package JavaCoreLearn;

import java.lang.annotation.Annotation;

/**
 * 判断一个类是否有某个注释：**.class.isAnnotationPresent(**.class）
 * 通过反射获得一个类上的所有注释类的对象： Class.forName("JavaCoreLearn.second").getAnnotations()
 * 获得注释类对象的一些信息。。。强制转化为某注释类：((AnnotationTest) a).name())
 * 
 * 类在添加注释时，可以给该注释的变量赋新值：@AnnotationTest(name="ji")
 * 
 * @author Administrator
 *
 */
public class AnnotationUse {
	public static void main(String args[]){
		System.out.println(AnnotationUse.class.isAnnotationPresent(AnnotationTest.class));
		System.out.println(first.class.isAnnotationPresent(AnnotationTest.class));
		System.out.println(second.class.isAnnotationPresent(AnnotationTest.class));
		
		try {
			Annotation[] arr = Class.forName("JavaCoreLearn.second").getAnnotations();
			for(Annotation a : arr){
				System.out.println("annotation:"+a);
				if(a instanceof AnnotationTest){
					System.out.println("a is:"+a);
					System.out.println("注释类上对象的信息name："+((AnnotationTest) a).name());
					int age = ((AnnotationTest)a).age();
					System.out.println("注释类上对象的信息age："+age);
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

@AnnotationTest(name="ji",age = 222)
class first{
	
}

class second extends first{
	
	
}
