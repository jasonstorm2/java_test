package my;

public class DebugTest {
	//½×³Ë	
	public static int factorial(int value){	
	
			if(value==1){
				return value;
			}else {	
				return value*factorial(value-1);
			}
		
		
	}	
	public static void main(String[] args) {
		System.out.println(factorial(6));	
		 System.out.println(System.getProperty("java.library.path"));
		 System.err.println("ÏµÍ³´íÎóÅ¶");
	}
}
