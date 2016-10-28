package Chapter18_ClassLoadAndReflection;

public class ClassFieldChageTest {
	public String name = "jason";
	public int value = -1;
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		ClassFieldChageTest cc = new ClassFieldChageTest();
		System.out.println(cc.name);
		ClassFielChageMethod.resetField(cc);
		System.out.println(cc.name);		
	}

}
