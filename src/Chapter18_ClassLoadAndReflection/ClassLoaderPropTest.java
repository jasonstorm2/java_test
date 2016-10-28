package Chapter18_ClassLoadAndReflection;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;


public class ClassLoaderPropTest {
	static{
		System.out.println("加载时，是否初始化数据");
	}
	public static void main(String[] args) throws IOException {
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();//  get system loader 获得系统的类加载器
	
		System.out.println("系统类加载器:        "+systemLoader);
		/*
		 * 获取 系统类加载器 的加载路径--通常由CLASSPATH 环境变量 指定，
		 * 如果，操作系统 没有指定CLASSPATH环境变量， 则 默认以当前路径作为 系统类加载器 的家在路径
		 */
		Enumeration<URL> eml = systemLoader.getResources("");
		while(eml.hasMoreElements()){
	    System.out.println("SYSTEMClassLoader Route:  "+eml.nextElement());
		}
		//获取 系统类加载器 的父类加载器，得到 扩展类加载器
		ClassLoader extenionLader = systemLoader.getParent();
		System.out.println("扩展类加载器:            "+extenionLader);
		System.out.println("扩展类加载器的 路径:      "+System.getProperty("java.ext.dirs"));
		System.out.println("扩展类加载器的 父类："+extenionLader.getParent());//The parent Classload is Bootstrap ClassLoader.BC is not use Java Language...So..
		//根加载器 没有 继承ClassLoader抽象类。所以，返回的是Null
		//但实际上 扩展类加载器 的 父类加载器 是 根类加载器，只是，根类加载器 并不是 java实现的。
		
	}
}
