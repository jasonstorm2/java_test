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
	 * ִ�� ��̬������� �����з���ʱ�����ᱻ�滻��ִ�����µ� invoke ����
	 * ���У�
	 * proxy ����̬�������
	 * method ��������ִ�еķ���
	 * args  �������Ŀ�귽��ʱ�����ʵ��
	 *
	 *
	 * (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("-------����ִ�еķ�����"+method);
		System.out.println("��ǰִ�е��߳����֣�"+Thread.currentThread().getName());
//		atomicInteger.incrementAndGet();
		atomicInteger2.incrementAndGet();

		if(proxy instanceof Person2){
			System.out.println("proxy instanceof Person2");
		}
		if(proxy instanceof Person3){
			System.out.println("proxy instanceof Person3");
		}

		if(args != null){
			System.out.println("������ִ�и÷���ʱ �����ʵ�Σ�");
			for(Object val : args){
				System.out.println(val);
			}
		}else{
			System.out.println("���ø÷���û��ʵ�Σ�");
		}
		if(method.getName()=="walk"){
			System.out.println("*********************��һ�߿�");
		}else if(method.getName()=="sayHello"){
			System.out.println("����𣿣���������������");
		}
		return null;
	}

}
public class ProxyTest2 {
	public static void main(String[] args)  {
		//����һ�� InvocationHandler ����
		MyInvokationHandler handler = new MyInvokationHandler();
		//ʹ�� ָ����InvocationHandler ������һ����̬�������
		Object obj = Proxy.newProxyInstance(Person2.class.getClassLoader(), new Class[]{Person2.class,Person3.class}, handler);
		Person2 p = (Person2)obj;
		Person3 p2 = (Person3)obj;

		//���� ��̬������� �� walk�� sayHello����
		p.walk();
		p.sayHello("��˹��");

		p2.cock("yellow");
//		System.out.println("��ֵ��"+MyInvokationHandler.atomicInteger);
		System.out.println("��ֵ��"+handler.getAtomicInteger2());

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
