package my;

public class OuterClass {
	public int value = 10;
	
	/**
	 * 内部类
	 * @author LiZhenhua
	 *
	 */
	public class InnerClass{
		public void getOuterClassArgument(){
			System.out.println(value);
			System.out.println(OuterClass.this.value);			
		}
		
	}
	
	/**
	 * 静态嵌套类
	 * @author LiZhenhua
	 *
	 */
	static class StaticNestedClass{
		public void getOuterClassArgument(){
			System.out.println("静态嵌套类无法调用外围类的参数");
		}
	}
	
	/**
	 * 方法内部的类，只能是abstract or final 修饰符修饰
	 */
	public void InnerMethodClass(){
		class testss{
			int age = 18;
			public void getAge(){
				System.out.println("年龄："+age);
			}
		}
		testss t = new testss();
		t.getAge();
	}
	
	
	
	public static void main(String[] args) {
		OuterClass out = new OuterClass();
		out.InnerMethodClass();// 方法内部的类，只能是abstract or final 修饰符修饰
		InnerClass in = out.new InnerClass();
		in.getOuterClassArgument();
//		InnerClass in2 = new InnerClass();//内部类实例化必须先实例化外围类
		
		
		StaticNestedClass staticN = new StaticNestedClass(); //静态嵌套类的实例化1
		OuterClass.StaticNestedClass staticN2 = new OuterClass.StaticNestedClass();//静态嵌套类的实例化2
		staticN.getOuterClassArgument();
		staticN2.getOuterClassArgument();
	}

}
