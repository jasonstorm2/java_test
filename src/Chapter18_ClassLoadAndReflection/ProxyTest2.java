package Chapter18_ClassLoadAndReflection;

import sun.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


interface Person2{
	void walk();
	void sayHello(String name);
}

interface Person3{
	void fuck();
	void cock(String color);
}

class MyInvokationHandler implements InvocationHandler{
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
	public static void main(String[] args) throws ClassNotFoundException {
		//����һ�� InvocationHandler ����
		InvocationHandler handler = new MyInvokationHandler();
		System.out.println("�����ߵ�classloader:"+ Class.forName("Chapter18_ClassLoadAndReflection.ProxyTest2").getClassLoader());
		System.out.println("Person2.class classLoader�����֣�"+Person2.class.getClassLoader().toString());
		
		//ʹ�� ָ����InvocationHandler ������һ����̬�������
		Object obj = Proxy.newProxyInstance(Person2.class.getClassLoader(), new Class[]{Person2.class,Person3.class}, handler);
		Person2 p = (Person2)obj;
		Person3 p2 = (Person3)obj;
		
		//���� ��̬������� �� walk�� sayHello����
		p.walk();
		p.sayHello("��˹��");		
		
		p2.cock("yellow");
	}

}
