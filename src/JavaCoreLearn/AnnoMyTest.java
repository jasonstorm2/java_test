package JavaCoreLearn;

public class AnnoMyTest {
	
	@AnnoTestable	
	public static void m1(){
		
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
