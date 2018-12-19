package Chapter18_ClassLoadAndReflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import utils.utils;


/**
 * JAVA 三种代理
 * 1.静态代理
 * 2.动态代理
 * 3.Cglib动态代理
 * 
 * https://blog.csdn.net/yangsnow_rain_wind/article/details/79291256
 * 
 * @author LiZhenhua
 * 
 * JDK动态代理局限性 
 * 通过反射类Proxy和InvocationHandler回调接口实现的jdk动态代理，要求 委托类 必须实现一个接口，
 * 但事实上并不是所有类都有接口，对于没有实现接口的类，便无法使用该方方式实现动态代理
 * 
 * 动态代理中，代理类并不是在Java代码中实现，而是在运行时期生成，相比静态代理，动态代理可以很方便的对委托类的方法进行统一处理，
 * 如添加方法调用次数、添加日志功能等等，动态代理分为jdk动态代理和cglib动态代理，
 *
 */


/**
 * 被代理的对象实现的接口
 * @author LiZhenhua
 *
 */
interface Dog{
	void info(String name);
	void run();
}

interface Cat{
	void miao(String str);
}

/**
 * 被代理的对象
 * @author LiZhenhua
 *
 */
class GunDog implements Dog{

	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		System.out.println("GunDog.info (代码段一)我是一只狗,我的名字是："+name);//希望代码段 执行 通用方法
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("GunDog.run (代码段二)我在快速奔跑");				 //希望代码段 执行 通用方法
	}	
	
	public void self(){
		System.out.println("GunDog.self 这是一个类自己的方法，非接口继承");
	}
}

class MyCat implements Cat,Dog{

	@Override
	public void miao(String str) {
		// TODO Auto-generated method stub
		System.out.println("猫叫："+str);
	}

	@Override
	public void info(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("猫学狗在跑");
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
		System.out.println("GunDog2.info (代码段一)我是一只狗,我的名字是："+name);//希望代码段 执行 通用方法
		du.method2();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		du.method1();
		System.out.println("GunDog2.run (代码段二)我在快速奔跑");				 //希望代码段 执行 通用方法
		du.method2();
	}	
	public void self(){
		System.out.println("GunDog2.self 这是一个类自己的方法，非接口继承");
	}
}

/**
 * 通用方法，为了方便实现打印
 * @author LiZhenhua
 *
 */
class DogUtil{
	//第一个拦截器方法
	public void method1(){
		System.out.println("DogUtil.method1 ======模拟第一个通用方法======");
	}
	//第二个拦截器方法
	public void method2(){
		System.out.println("DogUtil.method2 ======模拟第二个通用方法======");
	}
	
}

/**
 * 调用处理类，继承了InvocationHandler，重写了 invoke方法
 * 每次代理对象调用代理方法时，都会调用invoke方法，可以写一些过滤的逻辑
 * @author LiZhenhua
 *
 */
/**
 * 代理工厂，为对象生成一个 代理对象
 * 
 * 代理对象生成的两个步骤：
 * 1.把被代理的对象传入一个InvokationHandler类里，并重写 invoke方法
 * 2.调用Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler)
 * @author LiZhenhua
 *
 */
class ProxyFactory implements InvocationHandler{
	//被代理的对象
	private Object target;
	public ProxyFactory(Object target){
		this.target = target;
	}	
	/*
	 *通用方法 放于 代理的handler的invoke方法内！！！！ 
	 */
	//执行动态代理对象 的所有方法时，都会被替换成执行如下的invoke方法
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if (proxy instanceof GunDog) {			
			System.out.println("MyInvokationHandler2.invoke 对象是GUNDOG实体");
		}
		if(proxy instanceof Dog){
			System.out.println("MyInvokationHandler2.invoke 对象是DOG实体");
		}
		System.out.println("MyInvokationHandler2.invoke Proxy的类："+proxy.getClass());
		System.out.println("MyInvokationHandler2.invoke 方法名："+method.getName());
		DogUtil du = new DogUtil();//通用方法
		du.method1();
		//反射调用方法
		Object result = method.invoke(target,args);//反射来实现原来的方法。。原来的方法与通用方法 解耦了。。
		du.method2();
		return result;
	}	
	
	public static Object getProxy(Object target) throws Exception{	
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new ProxyFactory(target));
	}
	
}



public class ProxyTest3 {
	public static void main(String[] args) throws Exception {
		utils.PrintLine("代理使用方法");
		//被代理对象，作为 target
		Dog target = new GunDog();
		//生成的动态代理对象--注意,只能返回接口而不是具体实现类,否则报错误
		Dog dog = (Dog)ProxyFactory.getProxy(target);
		//使用代理对象调用方法
		dog.info("dog.info 旺财");//在代理的invoke方法中，有通过Method的反射，来实现info方法
		dog.run();
		//dog.self()方法不能调用。。。代理制能调用接口方法！！！
		utils.PrintLine("猫的代理方法");
		Cat cat = (Cat)ProxyFactory.getProxy(new MyCat());
		cat.miao("miao~miao!");
		utils.PrintLine("猫的代理方法,实现了狗接口");
		Dog catDog = (Dog)ProxyFactory.getProxy(new MyCat());
		catDog.run();
		utils.PrintLine("正常的使用方法");
		GunDog2 target2 = new GunDog2();
		target2.info(" target2.info 旺财");
		target2.run();
		target2.self();
		
		if (target instanceof GunDog) {			
			System.out.println("ProxyTest3.main  1111对象是GUNDOG实体");
		}
		if(target instanceof Dog){
			System.out.println("ProxyTest3.main 1111对象是DOG实体");
		}
		System.out.println(target.getClass());		
	}

}
