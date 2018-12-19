package javassistTest;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

/**
 * Javassist是一个动态类库，可以用来检查、”动态”修改以及创建 Java类。其功能与jdk自带的反射功能类似，但比反射功能更强大。
 * 
 * @author LiZhenhua
 * 
 *         重要的类 ClassPool：javassist的类池，使用ClassPool 类可以跟踪和控制所操作的类,它的工作方式与 JVM
 *         类装载器非常相似， CtClass：
 *         CtClass提供了检查类数据（如字段和方法）以及在类中添加新字段、方法和构造函数、以及改变类、父类和接口的方法。
 *         不过，Javassist 并未提供删除类中字段、方法或者构造函数的任何方法。 CtField：用来访问域 CtMethod ：用来访问方法
 *         CtConstructor：用来访问构造器
 * 
 * 
 *         限制与局限性
 *         1.需要注意的是，在调用ctClass.toClass()时，会加载此类，如果此类在之前已经被加载过，则会报一个duplicate
 *         load的错误，表示不能重复加载一个类。所以，修改方法的实现必须在修改的类加载之前进行。
 *         2.不能访问块之外的局部变量。如果在一个方法的开始和结尾都增加了代码段
 *         ，那么在方法的结尾块中无法访问方法开始中的代码段中的变量
 *         （不太完美的解决方法是将原方法改名，然后再增加与原方法同名的方法）。
 *
 */
public class classOne {
	
	public  void method1(){
		System.out.println("this is method1");
		
	}

	public static void main(String[] args) throws Exception {	
		replaceMethodBody("javassistTest.classTwo", "method1", "System.out.println(\"this method is changed dynamically!\");");
		new classTwo().method1();		
		createNewClass("classThree", "method1");
	}
	
	/**
	 * 动态修改类的方法体
	 * @param clazzName
	 * @param methodName
	 * @param newMethodBody
	 * @throws IOException 
	 */
	public static void replaceMethodBody(String clazzName, String methodName, String newMethodBody) throws IOException {
	    try {
	    	//会从classpath中查询该类
	        CtClass clazz = ClassPool.getDefault().get(clazzName);
	        CtMethod method = clazz.getDeclaredMethod(methodName);
	        method.setBody(newMethodBody);
	        //输出并加载class 类，默认加载到当前线程的ClassLoader中，也可以选择输出的ClassLoader
	        clazz.toClass();
	        //输出clazzName.class文件到该目录中
	        clazz.writeFile("d://javassist");
	        //输出成二进制格式
	        //byte[] b=clazz.toBytecode();
	    } catch (NotFoundException | CannotCompileException e) {
	       throw new RuntimeException(e);
	    }
	}
	
	public static void createNewClass(String className,String methodName) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.makeClass(className);
		// 新增方法
		CtMethod m = CtNewMethod.make("public int xmove(int dx) { x += dx; }",cc);
		cc.addMethod(m);
		// 新增Field
//		cc.addField(f);

	}
	
	
	
	
	
	
	
	
	

}
