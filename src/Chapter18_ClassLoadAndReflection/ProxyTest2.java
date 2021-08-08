package Chapter18_ClassLoadAndReflection;

import sun.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;


interface Person2{
	void walk();
	void sayHello(String name);
}

interface Person3{
	void fuck();
	void cock(String color);
}

class MyInvokationHandler implements InvocationHandler{
	static AtomicInteger atomicInteger = new AtomicInteger(0);
	AtomicInteger atomicInteger2 = new AtomicInteger(0);

	public static AtomicInteger getAtomicInteger() {
		return atomicInteger;
	}

	public AtomicInteger getAtomicInteger2() {
		return atomicInteger2;
	}

	/*
	 * 执行 动态代理对象 的所有方法时，都会被替换成执行如下的 invoke 方法
	 * 其中：
	 * proxy 代表动态代理对象
	 * method 代表正在执行的方法
	 * args  代表调用目标方法时传入的实参
	 *
	 *
	 * (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-------正在执行的方法："+method);
		System.out.println("当前执行的线程名字："+Thread.currentThread().getName());
//		atomicInteger.incrementAndGet();
		atomicInteger2.incrementAndGet();

		if(proxy instanceof Person2){
			System.out.println("proxy instanceof Person2");
		}
		if(proxy instanceof Person3){
			System.out.println("proxy instanceof Person3");
		}

		if(args != null){
			System.out.println("下面是执行该方法时 传入的实参：");
			for(Object val : args){
				System.out.println(val);
			}
		}else{
			System.out.println("调用该方法没有实参！");
		}
		if(method.getName()=="walk"){
			System.out.println("*********************走一走咯");
		}else if(method.getName()=="sayHello"){
			System.out.println("你好吗？？？？？？？？？");
		}
		return null;
	}

}
public class ProxyTest2 {
	public static void main(String[] args)  {
		//创建一个 InvocationHandler 对象
		MyInvokationHandler handler = new MyInvokationHandler();
		//使用 指定的InvocationHandler 来生成一个动态代理对象
		Object obj = Proxy.newProxyInstance(Person2.class.getClassLoader(), new Class[]{Person2.class,Person3.class}, handler);
		Person2 p = (Person2)obj;
		Person3 p2 = (Person3)obj;

		//调用 动态代理对象 的 walk和 sayHello方法
		p.walk();
		p.sayHello("李斯特");

		p2.cock("yellow");
//		System.out.println("数值："+MyInvokationHandler.atomicInteger);
		System.out.println("数值："+handler.getAtomicInteger2());

	}

	class xxx implements Supplier<ProxyTest2> {

		@Override
		public synchronized  ProxyTest2 get() {
			Supplier supplier = new Supplier() {
				@Override
				public Object get() {
					return null;
				}
			};
			if(supplier != this){

			}
			return null;
		}
	}



}
