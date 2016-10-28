package Chapter18_ClassLoadAndReflection;

import java.net.URL;


//当 JVM 启动时，会形成由 三个类加载器组成的 初始类加载器层次结构
// Bootstrap ClassLoader
// Extension ClassLoader
// System ClassLoader  JVM启动时加载 java命令 -classpath选项，java.class.path系统属性，或CLASSPATH环境变量所指定的JAR包和类路径。
public class BootstrapTest {
	public static void main(String[] args) {
		// 获取根类加载器所加载的全部URL数组
//		URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();	//无需理会警告
//		//遍历 输出根类加载器 加载的全部URL
//		for (int i = 0; i < urls.length; i++) {
//			System.out.println(urls[i].toExternalForm());
//		}
		
	}

}
