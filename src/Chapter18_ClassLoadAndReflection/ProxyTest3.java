package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface Dog{
	void info(String name);
	void run();
}

class GunDog implements Dog{

	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		System.out.println("(代码段一)我是一只狗,我的名字是："+name);//希望代码段 执行 通用方法
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("(代码段二)我在快速奔跑");				 //希望代码段 执行 通用方法
	}	
	
	public void self(){
		System.out.println("这是一个类自己的方法，非接口继承");
	}
}

/*
 * 要执行通用方法的普通方式：增加了代码的 耦合性！！
 */
class GunDog2 implements Dog{
	DogUtil du = new DogUtil();
	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		du.method1();
		System.out.println("(代码段一)我是一只狗,我的名字是："+name);//希望代码段 执行 通用方法
		du.method2();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		du.method1();
		System.out.println("(代码段二)我在快速奔跑");				 //希望代码段 执行 通用方法
		du.method2();
	}	
	public void self(){
		System.out.println("这是一个类自己的方法，非接口继承");
	}
}

/*
 * 通用方法
 */
class DogUtil{
	//第一个拦截器方法
	public void method1(){
		System.out.println("======模拟第一个通用方法======");
	}
	//第二个拦截器方法
	public void method2(){
		System.out.println("======模拟第二个通用方法======");
	}
	
}

class MyInvokationHandler2 implements InvocationHandler{
	//需要被代理的对象
	private Object target;
	public void setTarget(Object target){
		this.target = target;
	}
	
	/*
	 *通用方法 放于 代理的handler的invoke方法内！！！！ 
	 */
	//执行动态代理对象 的所有方法时，都会被替换成执行如下的invoke方法
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxy instanceof GunDog) {			
			System.out.println("对象是GUNDOG实体");
		}
		if(proxy instanceof Dog){
			System.out.println("对象是DOG实体");
		}
		System.out.println("Proxy的类："+proxy.getClass());
		System.out.println("方法名："+method.getName());
		DogUtil du = new DogUtil();//通用方法
		du.method1();
		//以target作为 主调用 来执行 method方法
		Object result = method.invoke(target,args);//反射来实现原来的方法。。原来的方法与通用方法 解耦了。。
		du.method2();
		return result;
	}
	
}
class MyProxyFactory{
	//为指定的 target生成动态代理对象
	public static Object getProxy(Object target) throws Exception{
		MyInvokationHandler2 handler = new MyInvokationHandler2();
		
		handler.setTarget(target);//传入 此对象的 目的是，让代理能够通过反射，完成此对象的 指定方法的调用
		
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
	}
}
public class ProxyTest3 {
	public static void main(String[] args) throws Exception {
		
		System.out.println("-------------------------代理使用方法--------------------------");
		//创建一个 原始的GunDog对象，作为 target
		Dog target = new GunDog();
		//以指定的target 来创建动态代理对象
		Dog dog = (Dog)MyProxyFactory.getProxy(target);
		
		dog.info("旺财");//在代理的invoke方法中，有通过Method的反射，来实现info方法
		dog.run();
		//dog.self()方法不能调用。。。代理制能调用接口方法！！！
		
		
		System.out.println("-------------------------正常的使用方法--------------------------");
		GunDog2 target2 = new GunDog2();
		target2.info("旺财");
		target2.run();
		target2.self();
		
		if (target instanceof GunDog) {			
			System.out.println("1111对象是GUNDOG实体");
		}
		if(target instanceof Dog){
			System.out.println("1111对象是DOG实体");
		}
		System.out.println(target.getClass());
		
	}

}
