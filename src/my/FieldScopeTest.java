package my;

public class FieldScopeTest {
	int x;
	public void ss(){
		int y=1;
		System.out.println("方法变量Y："+y);
	}
	public static void main(String[] args) {
		FieldScopeTest fs = new FieldScopeTest();
		System.out.println("数据成员x："+fs.x);
		fs.ss();
	}

}
