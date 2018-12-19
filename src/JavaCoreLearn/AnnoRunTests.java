package JavaCoreLearn;

/**
 * 通过反射，查询一个类中加自定义注释的方法
 * 并通过反射执行该方法
 * @author LiZhenhua
 *
 */
public class AnnoRunTests {
	public static void main(String[] args) throws ClassNotFoundException {
		AnnoProcessorTest.process("JavaCoreLearn.AnnoMyTest");
	}

}
