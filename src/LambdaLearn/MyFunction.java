package LambdaLearn;

/**
 * 
 * 通过@FunctionalInterface注解来显式指定一个接口是函数式接口（以避免无意声明了一个符合函数式标准的接口）
 * FI的定义其实很简单：任何接口，如果只包含 唯一 一个抽象方法，那么它就是一个FI。
 * 
 * 接口中每一个方法也是隐式抽象的,接口中的方法会被隐式的指定为 public abstract（只能是 public abstract，其他修饰符都会报错）。
 * 注意java1.8的版本接口中可以存在静态方法static，也有默认方法default的出现
 * @author Administrator
 *
 * @param <T>
 */
@FunctionalInterface  
interface MyFunction<T> {
	abstract T oneMethod(T t);
	static void testMethod(){System.out.println("接口里的静态方法testMethod");};
	
	default void defaultTest1(){ //默认方法可以被继承的类重写，也可以直接拿来用
		System.out.println("This is a default method1");
	};
	default void defaultTest2(){
		System.out.println("This is a default method2");
	};
	default void defaultTest3(){
		System.out.println("This is a default method3");
	};	
}
