package Chapter18_ClassLoadAndReflection;


/**
 * 测试类，调用反射方法，动态改变类的域
 * @author LiZhenhua
 *
 */
public class ClassFieldChageTest {
	public String name = "jason";
	public String school = "Harvard";
	public int value = -1;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		ClassFieldChageTest cc = new ClassFieldChageTest();
		System.out.println(cc.name);
		System.out.println(cc.school);
		
		ClassFielChageMethod.resetField(cc);
		System.out.println(cc.name);
		System.out.println(cc.school);
		
	}

}
