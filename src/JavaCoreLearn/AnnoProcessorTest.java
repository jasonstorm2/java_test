package JavaCoreLearn;

import java.lang.reflect.Method;

public class AnnoProcessorTest {
	public static void process(String clazz)throws ClassNotFoundException{
		int passed = 0;
		int failed = 0;
		for(Method m : Class.forName(clazz).getMethods()){
			if(m.isAnnotationPresent(AnnoTestable.class)){
				try {
					m.invoke(null, null);
					passed ++;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					failed ++;
					e.printStackTrace();
				} 
				
			}
			
		}
		System.out.println("共运行了："+(passed+failed)+"个方法，其中：\n"+"失败了："+failed+"个，\n"+"成功了:"+passed+"个！");
		
	}

}
