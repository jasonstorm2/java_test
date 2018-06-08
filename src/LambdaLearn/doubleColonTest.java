package LambdaLearn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 
 * 欲了解lambda，需先知道匿名内部类的局限！ 函数式接口：只拥有一个方法的接口 ---只有实现了函数式接口的方法，才能用lambda？
 * 
 * 我们并不需要额外的工作来声明一个接口是函数式接口：编译器会根据接口的结构自行判断
 * （判断过程并非简单的对接口方法计数：一个接口可能冗余的定义了一个Object已经提供的方法，比如toString()，
 * 或者定义了静态方法或默认方法，这些都不属于函数式接口方法的范畴）。
 * 不过API作者们可以通过@FunctionalInterface注解来显式指定一个接口是函数式接口（以避免无意声明了一个符合函数式标准的接口），
 * 加上这个注解之后，编译器就会验证该接口是否满足函数式接口的要求。
 * 
 * 引入全新的 结构化函数类型，称之为“箭头”类型。例如，一个接受String 和 Object 并返回 int 的 函数类型 可以被表示为：
 * (String,Object) -> int ，很遗憾，由于各种原因，引入新的函数类型，被放弃了，我们选择了使用 已知类型
 * 这条路：现有的函数，有很多函数式接口
 * 
 * lambda 表达式 是 匿名方法，它提供了轻量级的语法，简化了匿名类的语法
 * 
 * lambda表达式的语法由参数列表、箭头符号->和函数体组成。函数体既可以是一个表达式，也可以是一个语句块： 
 * 
 * 	表达式：表达式会被执行然后返回执行结果。
 * 	语句块：语句块中的语句会被依次执行，就像方法中的语句一样—— return语句会把控制权交给匿名方法的调用者 break和continue只能在循环中使用
 * 	如果函数体有返回值，那么函数体内部的每一条路径都必须返回值 表达式函数体适合小型lambda表达式，它消除了return关键字，使得语法更加简洁。
 * 		lambda 表达式： 
 * 			(int x, int y) -> x + y 
 * 			() -> 42 
 * 			(String s) -> { System.out.println(s); }
 * 
 * 第一个 lambda 表达式接收 x 和 y 这两个整形参数并返回它们的和； 第二个 lambda 表达式不接收参数，返回整数 ‘42’； 第三个
 * lambda 表达式接收一个字符串并把它打印到控制台，不返回值。
 * 
 * @author
 * 
 * 		理解Functional Interface（函数式接口，以下简称FI）是学习Java8 Lambda表达式的关键所在，
 *         所以放在最开始讨论。 FI的定义其实很简单：任何接口，如果只包含 唯一 一个抽象方法，那么它就是一个FI。
 *         为了让编译器帮助我们确保一个接口满足FI的要求（也就是说有且仅有一个抽象方法）
 */

public class doubleColonTest implements Functional3{
	
	public static Functional3 testMethodReference(){
		System.out.println("测试::方法引用1");
		return null;
	}
	
	public static Functional3<?, ?> testMethodReference2(){
		System.out.println("测试::方法引用2");
		return (a,b)->12;
	}
	
	public static String 中文(){
		System.out.println("测试::方法引用3");
		return null;
	}
	
	public void testInvodeMethod(Functional3<String,Integer> f3){
		String a = "stringvalue";
		int b = 1;
		f3.sex(a, b);		
	}
	
	public void testInvodeMethod2(Functional<String> f3){	
	}
	
	public int testMethod(String s,Integer i){
		return 1;		
	}
	
	public static void main(String[] args) {
		doubleColonTest thisClass = new doubleColonTest();	
		//类强制转换为函数式接口测试
		doubleTest2<String> d2 = new doubleTest2<String>();
		Functional<String> f = (Functional<String>)(d2::method1);
		f.judge("hahahaha");
		//不用强制类型转化了。编译器自动识别转换
		thisClass.testInvodeMethod2(d2::method1);
		
		/**有空再来修改2017.1**/		
		//::表示一个implement函数式接口的对象调用重写的方法。
//		Functional3 s = thisClass::testMethodReference();
		Functional3 s2 = thisClass.testMethodReference();
		Functional3 s3 = thisClass::sex;
		Functional3 s4 = (a,b)->{
			return a;
		};
		Functional3 s5 = (a,b)->a;
//		doubleColonTest  s6 = thisClass::sex; // 报错
		/*
		 * 需要注意的是，函数式接口的名称并不是 lambda 表达式的一部分。
		 * 那么问题来了，对于给定的 lambda表达式，它的类型是什么？
		 * 答案是：它的类型是由其上下文推导而来 下面代码中的 lambda 表达式类型是 Functional
		 * 
		 * 编译器负责推导 lambda 表达式类型。它利用 lambda 表达式所在上下文所期待的类型 进行推导，这个被期待的类型 被称为 目标类型。
		 * lambda 表达式--只能出现--在目标类型为函数式接口的上下文中。
		 */
		Functional<String> f1 = (a)-> "fuc";//实现一个Functional类，返回的是"fuc"
		Functional2<String> f11 = ()-> "fuc";//实现一个Functional2类，返回的是"fuc"
		
		/*
		 * 由于目标类型（函数式接口）已经“知道 lambda表达式的形式参（Formal parameter类型，
		 * 所以我们没有必要把已知类型再重复一遍。也就是说，lambda 表达式的参数类型可以从 目标类型 中得出：
		 */
		Functional<String> f2 = (a)->a ;//由上下文可知 a 的类型是String
		/*
		 * 当 lambda 的参数只有一个而且它的类型可以被推导得知时，该参数列表外面的括号 可以 被省略：
		 */
		Functional<String> f3 = a->a ;//
		/*
		 * 当 lambda 的参数有两个而且它的类型可以被推导得知时，该参数列表外面的括号 !不可以! 被省略：
		 */
		Functional3<String,Boolean> ft = (a,b)->a ;
		//获得一个Functional对象，重写了函数式接口的方法
		Functional<String> f4 = a -> {
			System.out.println("傻逼");
			return a;
		};
		System.out.println(f4.judge("lala"));
		
		/**
		 * lambda表达式对值封闭，对变量开放：lambda expressions close over values, not variables，
		 */
		int sum = 0 ;
		List<String> list = new ArrayList<String>();
		Functional<String> f5 = (a) -> {
//			sum = sum + 1;       //错误，  对值封闭，不能改变外部类的值
			list.add("moment");  //正确，对变量开放
			return a;			
		};		
	}
	
	public void print(){
		System.out.println("这是一个打印");
	}
	
	public void forBlock(){//局部变量，全局变量，显示参数，隐式参数，匿名类，匿名内部类,类型擦除跟重载，编译类型，运行时类型？？？？？？？？？？？
//		int i = 0;
//		int sum = 0;
//		for (int i = 1; i < 10; i += 1) { //这里会出现编译错误，因为i已经在for循环外部声明过了
//		  sum += i;
//		}
	}

	@Override
	public Object sex(Object t, Object k) {
		System.out.println("sex");
		// TODO Auto-generated method stub
		return null;
	}

}

@FunctionalInterface  //接口有此标志，说明其内部只能有一个方法,变量倒是无所谓,静态方法（类方法）,默认方法，以及冗余方法不属于函数式接口的范畴
interface Functional<T>{
	String ss = "2";
	T judge(T t);  // 默认是抽象的方法
	static boolean  dd() {  //静态方法，在接口内可以有方法体哦。
		return false;
	}
	
	public String toString();//冗余了 Object 的 toString方法 接口内没有方法体哦。
	
}

@FunctionalInterface  //接口有此标志，说明其内部只能有一个方法,变量倒是无所谓,静态方法（类方法）,默认方法，以及冗余方法不属于函数式接口的范畴
interface Functional2<T>{
	String ss = "2";
//	T judge();  //此方法，跟 sex()方法不能同时存在！！
	static boolean  dd() {
		return false;
	}	
	abstract T sex();	
}

@FunctionalInterface  
interface Functional3<T,K>{
	abstract T sex(T t,K k);	
}

/**
 * 类强制转换为函数式接口测试
 * @author Administrator
 *
 * @param <T>
 */
class doubleTest2<T>{
	
	T method1(T t) {
		System.out.println("类中的一个普通方法，跟要强制转换的函数式接口的输入输出参数相同！");
		System.out.println(t);
		return t;
	}  // 默认是抽象的方法
	
}

