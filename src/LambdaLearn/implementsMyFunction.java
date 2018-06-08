package LambdaLearn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.poi.hssf.record.formula.functions.T;


public class implementsMyFunction implements MyFunction<String>{
	
    static Map<Integer, Integer> cache = new ConcurrentHashMap<>();      


	public static void main(String[] args) {
		implementsMyFunction thisClass =  new implementsMyFunction();
		myfun fun = new myfun();

        cache.put(0, 0);  
        cache.put(1, 1);  
        implementsMyFunction.fibonacciJava8(8);
        System.out.println("获得缓存中8的值："+cache.get(8));
        
        //实例化一个函数式对象，并且定义了该对象的抽象方法--lamda表达式：(Integer a)->a+1 就代表了一个对象了
        MyFunction<Integer> myFuc = (Integer a)->a+1; 
        int rest = myFuc.oneMethod(4);
        System.out.println("使用自定义的函数式接口："+rest);
        
		// 开始一个线程，使用lambda方法  java.lang.Thread.Thread(Runnable target, String name)
        //实例化一个Runnable函数式对象，并且定义了该对象的抽象方法
		new Thread(()->{System.out.println("开始一个线程，使用lambda方法");}, "lambda线程").start();
		
		// 本类的对象（是一个myFunction对象）调动本类的方法，该方法需要一个myFunction接口的实现对象
		//--此处类似匿名内部类的实例
		System.out.println("------------------------------");
		thisClass.useFunc(a->{
			System.out.println("***调用对象中含有函数式接口参数的方法");
			return a;
		});	
		//参数 thisClass::oneMethod 和 thisClass 没有什么区别吗
		System.out.println("------------------------------");
		thisClass.useFunc(thisClass::oneMethod);
		System.out.println("------------------------------");
		thisClass.useFunc(thisClass);
		System.out.println("------------------------------");
		thisClass.useFunc(a->"mess");
		System.out.println("------------------------------");
		thisClass.useFunc(a->{return "mess";});
		System.out.println("------------------------------");
		useFunc2(thisClass);
//		useFunc2(fun); //报错，虽然lambda结构一样
		List<String> list = Arrays.asList("ab","lady","amine","count");
		List<String> list2 =  Stream.of("ab","lady","amine","count").collect(Collectors.toList());
		
		List<String> collected = new ArrayList<>();
		for (String string : Arrays.asList("a", "b", "hello")) {
		String uppercaseString = string.toUpperCase();
		collected.add(uppercaseString);
		}
		
		
		List<String> collected2 = Stream.of("a", "b", "hello")
				.map(string -> string.toUpperCase()).collect(Collectors.toList());
		
		List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
				.filter(value -> true).collect(Collectors.toList());
		
		long res = list.stream().filter((a)->true).count();
		
//		// forEach方法，需要一个参数对象Consumer<? super String> action，Consumer是一个函数式接口，有个抽象方法accept(T t)
//		list.forEach((l)->{
//			System.out.println(l);
//		});
		// 此处有点怪异，System.out::println是一个Consumer对象吗？
		list.forEach(System.out::println);
		list.forEach((a)->{
			System.out.println("打印："+a);
		});
//		thisClass.useFunc({implementsMyFunction::testMethod});
//		implementsMyFunction ss = implementsMyFunction::thisMethod;
		
	}
	
	public void useFunc(MyFunction<String> mm){
		//这是一个空的方法，方法体没有任何操作		
	}
	public static void useFunc2(MyFunction<String> mm){
		String str = mm.oneMethod("：：使用参数的方法");
		System.out.println(str+"**");
	}

	@Override
	public String oneMethod(String t) {
		// TODO Auto-generated method stub
		System.out.println("重写函数式接口的抽象方法oneMethod,并返回" + t);
		return t;
	}
	
	public  static void filter(List<String> str,Predicate pr){
		for(String s : str){
//			if(){
//				
//			}
		}
		
	}
	
	public static implementsMyFunction thisMethod(String str){
		implementsMyFunction ss = new implementsMyFunction();
		return ss;
		
	}
	
    /**  
     * 采用java8的本地缓存方式 如果缓存MAP中不存在指定key的值，会自动调用mappingFunction(key)计算key的value  
     * 然后将key = value放入到缓存Map,java8会使用thread-safe的方式从cache中存取记录  
     *   
     * @param n  
     * @return  
     */ 
	static int fibonacciJava8(int n) {
		return cache.computeIfAbsent(n, (key) -> {
			System.out.println("calculating FibonacciJava8 " + n);
			return fibonacciJava8(n - 2) + fibonacciJava8(n - 1);
		});
	}
    /**  
     * 在java7中的实现方式  
     * 在java7中，通过synchronized进行线程同步，检查缓存是否存在key对应的值，如果不存在才进行计算并放入缓存中  
     * 为了更好的性能，需要使用 double-checked locking，那样代码会更复杂  
     *   
     * @param n  
     * @return  
     */ 
    static int fibonacciJava7(int n) {  
        if (n == 0 || n == 1)  
            return n;  
 
        Integer result = cache.get(n);  
 
        if (result == null) {  
            synchronized (cache) {  
                result = cache.get(n);  
 
                if (result == null) {  
                    System.out.println("calculating FibonacciJava7(" + n + ")");  
                    result = fibonacciJava7(n - 2) + fibonacciJava7(n - 1);  
                    cache.put(n, result);  
                }  
            }  
        }  
        return result;  
    }
    
    /**  
     * 普通的实现方式 普通方式使用大量的计算，存在性能问题. 并且计算量随着n的增加呈指数级增加，需要用到一些缓存策略，并且是线程安全的.  
     *   
     * @param n  
     * @return  
     */ 
    static int fibonacci(int n) {  
        if (n == 0 || n == 1)  
            return n;  
 
        System.out.println("calculating Fibonacci(" + n + ")");  
        return fibonacci(n - 2) + fibonacci(n - 1);  
    }  
    
    public <R> implementsMyFunction change(Function<? super String, ? extends String> mapper){
		return null;    	
    }
}

@FunctionalInterface  
interface MyFunction<T> {
	T oneMethod(T t);	
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

@FunctionalInterface  
interface MyFunction2<T> {
	 T accept(T t);
}

class myfun implements MyFunction2<String>{

	@Override
	public String accept(String t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}


class  chan<R> implements Function<T,R>{
	@Override
	public R apply(T t) {
		R r = null;
		// TODO Auto-generated method stub
		return r;
	}
	
}
