package JavaCoreLearn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

public class ProxyTest {
	public static void main(String[] args) {
		
//		Object[] elements2 = new Object[3];
//		elements2[0] = 1; 
//		elements2[1] = 5; 
//		elements2[2] = 2; 
//		Arrays.sort(elements2);
//		int result2 = Arrays.binarySearch(elements2, 34343);
//		System.out.println("binarySearch测试："+result2);
		
		Object[] elements = new Object[1000];
		for (int i = 0; i < elements.length; i++) {
			Integer value = i+1;
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class,Cloneable.class}, handler);
			Class proxyClass = Proxy.getProxyClass(null, Comparable.class);
			System.out.println("代理类的名字："+proxyClass.getName());
			elements[i] = proxy;
		}
		
		Integer key = new Random().nextInt(elements.length)+1;
		
		int result = Arrays.binarySearch(elements, key);
		
		if(result >=0) System.out.println("re:"+elements[result]);		
	}

}
class TraceHandler implements InvocationHandler{
	private Object target;
	
	public TraceHandler(Object t) {
		target = t;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		System.out.print("目标："+target);
		
		System.out.print(".方法名："+m.getName()+"(");
		
		if(args!=null){
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i]);
				if(i<args.length){
					System.out.print("，");
				}
			}
		}
		System.out.println(")");
		return m.invoke(target, args);
	}	
}
