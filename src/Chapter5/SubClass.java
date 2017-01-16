package Chapter5;
/**
 * 类的 多态
 * @author Administrator
 *
 */
class BaseClass{
	public int book = 6;
	public void base(){
		System.out.println("1");
	}
	public void test(){
		System.out.println("2");
	}
}
public class SubClass extends BaseClass{
	public String book = "sa";
	public String bo = "bobo";
	public void test(){
		System.out.println("cover 2");
	}
	
	public void sub(){
		System.out.println("a");
	}
	
	public static void main(String[] args) {
		BaseClass bc = new BaseClass();
		
		System.out.println(bc.book);
		bc.base();
		bc.test();
		System.out.println("*********");
		SubClass sc = new SubClass();
		System.out.println(sc.book);
		sc.base();
		sc.test();
		System.out.println("*********");
		BaseClass ploybc = new SubClass();
		//实例变量 不具备多态。方法 具备多态
		System.out.println(ploybc.book);
		ploybc.base();
		ploybc.test();
		//ploybc编译时类型是 BaseClass,而BaseClass类没有提供sub()方法，所以这句代码编译时会出错
		//虽然ploybc引用变量 实际上包含sub()方法（可以用反射来执行该方法）,但因为它的编译时类型为BaseClass所以，编译时无法调用sub()方法。
//		ploybc.sub(); 		
		
	}

}
