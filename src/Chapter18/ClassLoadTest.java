package Chapter18;


class Tester{
	static{
		System.out.println("Test的类的静态初始化。。。");
	}
}
public class ClassLoadTest {
	
	 static String  classPath = "Chapter18.Tester";

	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoader cl = ClassLoader.getSystemClassLoader();
		cl.loadClass(classPath);  //用到了 Tester类   加载一个类，并不会导致一个类的 初始化
		System.out.println("系统加载Tester类");
		
		Class.forName(classPath); //用到了 Tester类   调用forName返回类才 Class 对象才会 初始化
	}

}
