package my;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.slf4j.LoggerFactory;

/**
 * apache log4j的使用
 * 
 * slf4j 的使用：SLF4J或者说是Java的简单记录日志设计没有真正地实现日志记录，
 * 相反它只是一个允许你使用任何处于后端的日志记录库的抽象层 
 * 
 * slf4j的优点：
 * 1.让你的程序独立于任何特定的日志记录库
 * 2.占位符功能，在代码中用{}表示。。它在运行时刻才提取所提供的真正的字符串。
 *   这不仅缩减了代码中的许多字符串连接，而且减少了创建String对象所需要的资源
 *   
 * ①首先系统包含slf4j-api作为日志接入的接口。
 *   compile时slf4j-api中public final class LoggerFactor类中private final static void bind()方法会寻找具体的日志实现类绑定，
 *   主要通过StaticLoggerBinder.getSingleton()的语句调用。
 *   
   ②slf4j-log4j12是链接slf4j-api和log4j中间的适配器。
   它实现了slf4j-apiz中StaticLoggerBinder接口，从而使得在编译时绑定的是slf4j-log4j12的getSingleton()方法。
   
   ③log4j是具体的日志系统。通过slf4j-log4j12初始化Log4j，达到最终日志的输出。
   原帖地址：http://blog.csdn.net/tengdazhang770960436/article/details/18006127
   
 * log4j2和log4j1是不一样的，它的配置文件有多种。Log4j的配置是写在log4j.properties文件里面，但是Log4j2就可以写在XML和JSON文件里了。
   
 * log4j2的配置文件可以重新定位：
   配置将自动加载。automatically reload its configuration upon modification!!
   如果没有设置"log4j.configurationFile" system property的话，application将在classpath中按照如下查找顺序来找配置文件：
　　log4j2-test.json 或log4j2-test.jsn文件
　　log4j2-test.xml文件
　　log4j2.json 或log4j2.jsn文件
　　log4j2.xml文件
   
   1，是网络上的方法，放于System property中
   2，是重新配置.classpath文件，添加语句：<classpathentry kind="lib" path="../config"/>
      ---新增加了classpath读取的路径，必须重新开启Eclipse，不然没有办法加载如路径
   
   
 * lo4j2 配置内容：
   在LOG4J的配置文件中,log4j.appender.CATNAME.layout.ConversionPattern项中可配置日志输出格式.其中格式符代表的意义如下. 

  %n - 换行   
  %m - 日志内容      
  %p - 日志级别(FATAL,   ERROR,   WARN,   INFO,   DEBUG   or   custom)       
  %r - 程序启动到现在的毫秒数    
  %% - percent   sign   in   output   
  %t - 当前线程名   
  %d   -  日期和时间,    
  
  常用的格式有 %d{DATE}, %d{ABSOLUTE}, %d{HH:mm:ss,SSS}, %d{ddMMyyyy HH:mm:ss,SSS}。。。 
    
  %l - 同 %F%L%C%M   
  %F - java源文件名   
  %L - java源码行数   
  %C - java类名,%C{1} 输出最后一个元素   特指配置中Logger的名称name
  %M-java方法名   
  %n - 换行 
  %m - 日志内容    
  %p - 日志级别(FATAL,   ERROR,   WARN,   INFO,   DEBUG   or   custom)     
  %r - 程序启动到现在的毫秒数  
  %% - percent   sign   in   output 
  %t - 当前线程名 
  %d   -  日期和时间,  



  示例： 
  [%d{HH\:mm\:ss\:SSS}][%p] (%c\:%L) - %m%n   
  [%d{HH\:mm\:ss\:SSS}][%p] (%c\:%L) - %m%n 
  输出格式为：[08:58:59:412][INFO] (com.soon.action:35) - 服务器启动 
   
   
 * @author Jason
 *
 *
 * 本类采用 slf4j-api-1.7.12.jar  是最近的版本
 * 
 *  log4j-api-2.7.jar , log4j-core-2.7.jar log4j-slf4j-imp-2.7 是一起的，在同一个下载压缩包内
 *  使用 log4j-slf4j-imp 而非slf4j-log4j12-1.7.21  这是为什么呢？？
 */
public class LoggerTest {
	static Logger logger = LogManager.getLogger(Logger.class.getName());

	
	public LoggerTest(){
//		logger.entry();
		logger.fatal("我是fatal信息");
		logger.error("我是error信息");
		logger.warn("我是warn信息");
		logger.info("我是info信息");
		logger.debug("我是debug信息");
		logger.trace("我是trace信息");
		

		
		logger.log(Level.DEBUG,"我是debug信息");//这个就是定制level类型的调用
//		logger.exit();//和entry对应的结束方法
	}
	public static void main(String[] args) {		
		LoggerTest logt= new LoggerTest();
		logger.info("this is a message");
//		
		org.slf4j.Logger  logger2 =  LoggerFactory.getLogger("CORE");
		String d = "******";
		logger2.error("error");
		logger2.warn("warn");		
		logger2.info("info{}",d);
		logger2.debug("debug");
		logger2.trace("trace");
		
		/********************************/
		String str = "{}个大傻逼";		
		System.out.println(LoggerTest.StringMax(str, 1));
	}
	
	/**
	 * log4j的字符串组装方法
	 * @param str
	 * @param objs
	 */
	public static String StringMax(String str,Object... objs){
		return ParameterizedMessage.format(str, objs);
	}

}
