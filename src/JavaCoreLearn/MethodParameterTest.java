package JavaCoreLearn;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

class Test{
	public void replace(String str,List<String> list,Integer...i){	//可变数量的形参，实际上是数组！！反射调用方法时候可以看出
		
	}
}
public class MethodParameterTest {
	public static void main(String[] args) throws Exception {
		//获取 String 的类
//		Class<Test> clazz = Test.class;
		Class<?> clazz = Class.forName("JavaCoreLearn.Test");

		System.out.println(clazz.getName());
		//获取 String 类的带两个参数的 replace()方法
		Method re = clazz.getMethod("replace", String.class,List.class,Integer[].class);
		System.out.println("方法内，是否有可变形参："+re.isVarArgs());
		
		//获取 指定方法的 参数个数
		System.out.println("replace方法参数个数："+re.getParameterCount()+"\n");
		//获取 指定方法的 修饰符
		System.out.println("replace方法修饰符："+re.getModifiers()+"\n");
		//获取 re的所有参数信息
		Parameter[] parameters = re.getParameters();
		int index = 1;
		for (Parameter parameter : parameters) {
			if(parameter.isNamePresent()){    //编译默认生成的.class文件，并不包括 形参名的信息
				System.out.println("第"+index+"个参数信息：");
				System.out.println("参数名："+parameter.getName());
				System.out.println("参数类型："+parameter.getType());
				System.out.println("泛型类型："+parameter.getParameterizedType());				
			}
			System.out.println("是否是可变形参："+parameter.isVarArgs());
			System.out.println("第"+index+"个参数信息：");
			System.out.println("参数名："+parameter.getName());
			System.out.println("参数类型："+parameter.getType());
			System.out.println("泛型类型："+parameter.getParameterizedType());		
			
		}		
	}
}
