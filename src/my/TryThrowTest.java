package my;

public class TryThrowTest {
	public static void main(String[] args) throws Exception {
//		test();
		testCatch();
	}
	
	public static void test() throws myException{
		int i=0;
		int s=5;
		int re=0;
		try {
			re = s/i;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("数据出错");
//			e.printStackTrace();//不要抛出则trycatch之后的程序继续执行
//			throw new Exception("抛出后下面程序不执行");
			throw new myException();
		}
		System.out.println("执行结束");
	}
	
	public static void testCatch(){
		try {
			
			test();
		} catch (myException e) {
			// TODO: handle exception
			System.out.println("捕获到了一个异常");
		}
	}
}

 class myException extends Exception{
	 private static final long serialVersionUID = 1L;
	
}
