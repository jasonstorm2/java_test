package Chapter18_ClassLoadAndReflection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * 获得系统的类加载器 ClassLoader.getSystemClassLoader() 
 * 系统类加载器 的加载路径--通常由CLASSPATH 环境变量 指定，
 * 如果，操作系统 没有指定CLASSPATH环境变量， 则 默认以当前路径作为 系统类加载器 的家在路径
 * @author Administrator
 *
 */
public class ClassLoaderPropTest {
	static{
		System.out.println("加载时，是否初始化数据");
	}
	public static void main(String[] args) throws IOException {
		ClassLoader systemLoader = ClassLoader.getSystemClassLoader();//  get system loader 获得系统的类加载器
	
		System.out.println("系统类加载器:        "+systemLoader);
		/*
		 * 获取 系统类加载器 的加载路径--通常由CLASSPATH 环境变量 指定，
		 * 如果，操作系统 没有指定CLASSPATH环境变量， 则 默认以当前路径作为 系统类加载器 的加载路径
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
		
		System.out.println("------------------------------");
		System.out.println(ClassLoaderPropTest.class.getResource(""));
		System.out.println(ClassLoaderPropTest.class.getResource("/"));
		
		URL url = ClassLoaderPropTest.class.getResource("");	
		String ss = "D:\\jj\\kk";			//绝对路径：D:\jj\kk
//		String ss = "file:///f:/1.swf";		//绝对路径：D:\WorkSpace1\java_test\file:\f:\1.swf
//		String ss = "/long";				//绝对路径：D:\long
//		String ss = "lo/ng";				//绝对路径：D:\WorkSpace1\java_test\lo\ng
//		String ss = "/lo/ng";				//绝对路径：D:\lo\ng
//		String ss = "\\long";				//绝对路径：D:\long
//		String ss = "//long";				// 绝对路径：\\long
//		String ss = "file:///D:/Test/source/xxx.rmvb";
		
		ClassLoaderPropTest.printContent(ss);
	}
	
	public static void printContent(String path) throws IOException{
		
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		URL url               = cl.getResource("");		
		Enumeration<URL> dirs = cl.getResources("");

		System.out.println("路径：："+url);
		while(dirs.hasMoreElements()){				
				System.out.println("URL路径："+ dirs.nextElement());
			}
		System.out.println("----------------");
		File file = new File(path);
		String ab = file.getAbsolutePath();
		System.out.println("user.dir路径："+System.getProperty("user.dir"));
		System.out.println("绝对路径："+ab);
		System.out.println("File是否存在："+file.exists());
		System.out.println("last modify:"+file.lastModified());
		
		
	}
    
}
