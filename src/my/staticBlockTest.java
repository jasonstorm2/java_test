package my;

public class staticBlockTest extends father{
	staticBlockTest(){
		System.out.println("construct staticBlockTest");
	}
	
	staticBlockTest(int i){
		System.out.println("2");
	}
	{
		System.out.println("block staticBlockTest");
	}
	static{
		System.out.println("static staticBlockTest");
	}
	public static void main(String[] args) {
		new staticBlockTest(3);
	}
}

class father{
	father(int i){
		System.out.println("1");
	} 
	father(){
		System.out.println("33");
	}
	{
		System.out.println("block father");
	}
	static{
		System.out.println("static father");
	}
}
