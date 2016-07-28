package my;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerTest {
	//1,get logger
	//通过指定的名字获得记录器对象，如果必要的话，则为这个名字创建一个新的记录器。Name一般取本类的名字
	static Logger logger = Logger.getLogger(LoggerTest.class);
	static{
		 PropertyConfigurator.configure("log4j.properties");
	}
	
	public LoggerTest(){
		logger.debug("wtf");
		logger.warn("destny");		
	}
	public static void main(String[] args) {
    //2.读取配置文件
        //BasicConfigurator.configure ()： 自动快速地使用缺省Log4j环境。
		//DOMConfigurator.configure ( String filename ) ：读取XML形式的配置文件。
		
		
		LoggerTest logt= new LoggerTest();
		logger.info("this is a message");
	}

}
