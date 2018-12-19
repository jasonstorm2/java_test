package JavaCoreLearn;

/**
 * 一个类，在方法上加上自定义的注释
 * @author LiZhenhua
 *
 */
public class AnnoMyTest {
	
	@AnnoTestable	
	public static void m1(){
		System.out.println("m1方法被调用");		
	}
	
	public static void m2(){
		
	}
	@AnnoTestable	
	public static void m3(){
		throw new IllegalArgumentException("参数出错了");
		
	}
	public static void m4(){
		
	}
	@AnnoTestable	
	public static void m5(){
		System.out.println("m5方法被调用");		
	}
	public static void m6(){
		
	}
	@AnnoTestable	
	public static void m7(){
		throw new RuntimeException("程序业务出现异常");
		
	}
	public static void m8(){
		
	}



}
