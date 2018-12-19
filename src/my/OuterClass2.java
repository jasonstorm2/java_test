package my;

/**
 * 其他类，访问某类的静态嵌套类
 * @author LiZhenhua
 *
 */
public class OuterClass2 {
	
	public static void main(String[] args) {
		OuterClass.StaticNestedClass ss = new OuterClass.StaticNestedClass();
		ss.getOuterClassArgument();
		OuterClass out = new OuterClass();
		OuterClass.InnerClass in = out.new InnerClass();
		
	}

}
