package my;

/**
 * 如果自定义了构造器，那么不能再使用默认的构造器了。必须再自定义默认构造器
 * @author Administrator
 *
 */
public class ConstructorDefaultTest {
	int s;
	
	public ConstructorDefaultTest(int s){
		this.s = s;
	}
	public static void main(String[] args) {
		ConstructorDefaultTest con = new ConstructorDefaultTest(3);
		
		System.out.println("实例变量的值s:"+con.s);
	}

}
