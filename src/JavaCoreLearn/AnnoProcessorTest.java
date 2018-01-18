package JavaCoreLearn;

import java.lang.reflect.Method;

/**
 * 通过反射，查询一个类中加自定义注释的方法
 * 并通过反射执行该方法
 *  
 * Class.forName(clazz)，路径是本项目中的类的路径：包名.类名
 * 
 * @author Administrator
 *
 */
public class AnnoProcessorTest {
	public static void process(String clazz)throws ClassNotFoundException{
		int passed = 0;
		int failed = 0;
		Class<?> cl = Class.forName(clazz);
		try {
			Object obj = cl.newInstance();
			for(Method m : Class.forName(clazz).getMethods()){
				if(m.isAnnotationPresent(AnnoTestable.class)){
					try {
//						m.invoke(null, null); //FIXME 或者使用下面的方法
						m.invoke(obj);
						
						passed ++;
					} catch (Exception e) {
						failed ++;
						e.printStackTrace();
					} 
					
				}
				
			}
			
			
		} catch (InstantiationException | IllegalAccessException e1) {
			e1.printStackTrace();
		}//通过类对象，实例化
		
		
		System.out.println("共运行了："+(passed+failed)+"个方法，其中：\n"+"失败了："+failed+"个，\n"+"成功了:"+passed+"个！");
		
	}

}
