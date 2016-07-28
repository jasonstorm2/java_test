package JavaCoreLearn;

public class ClassVariableTest {
	public static void main(String[] args) {
		int c = Add.a;
		System.out.println(c);
	}
	

}

class Add{
	public static int a = 7;
}
