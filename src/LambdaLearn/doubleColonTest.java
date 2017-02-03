package LambdaLearn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 
 * ���˽�lambda������֪�������ڲ���ľ��ޣ� ����ʽ�ӿڣ�ֻӵ��һ�������Ľӿ� ---ֻ��ʵ���˺���ʽ�ӿڵķ�����������lambda��
 * 
 * ���ǲ�����Ҫ����Ĺ���������һ���ӿ��Ǻ���ʽ�ӿڣ�����������ݽӿڵĽṹ�����ж�
 * ���жϹ��̲��Ǽ򵥵ĶԽӿڷ���������һ���ӿڿ�������Ķ�����һ��Object�Ѿ��ṩ�ķ���������toString()��
 * ���߶����˾�̬������Ĭ�Ϸ�������Щ�������ں���ʽ�ӿڷ����ķ��룩��
 * ����API�����ǿ���ͨ��@FunctionalInterfaceע������ʽָ��һ���ӿ��Ǻ���ʽ�ӿڣ��Ա�������������һ�����Ϻ���ʽ��׼�Ľӿڣ���
 * �������ע��֮�󣬱������ͻ���֤�ýӿ��Ƿ����㺯��ʽ�ӿڵ�Ҫ��
 * 
 * ����ȫ�µ� �ṹ���������ͣ���֮Ϊ����ͷ�����͡����磬һ������String �� Object ������ int �� �������� ���Ա���ʾΪ��
 * (String,Object) -> int �����ź������ڸ���ԭ�������µĺ������ͣ��������ˣ�����ѡ����ʹ�� ��֪����
 * ����·�����еĺ������кܶຯ��ʽ�ӿ�
 * 
 * lambda ����ʽ �� �������������ṩ�����������﷨��������������﷨
 * 
 * lambda����ʽ���﷨�ɲ����б�����ͷ����->�ͺ�������ɡ�������ȿ�����һ������ʽ��Ҳ������һ�����飺 
 * 
 * 	����ʽ������ʽ�ᱻִ��Ȼ�󷵻�ִ�н����
 * 	���飺�����е����ᱻ����ִ�У����񷽷��е����һ������ return����ѿ���Ȩ�������������ĵ����� break��continueֻ����ѭ����ʹ��
 * 	����������з���ֵ����ô�������ڲ���ÿһ��·�������뷵��ֵ ����ʽ�������ʺ�С��lambda����ʽ����������return�ؼ��֣�ʹ���﷨���Ӽ�ࡣ
 * 		lambda ����ʽ�� 
 * 			(int x, int y) -> x + y 
 * 			() -> 42 
 * 			(String s) -> { System.out.println(s); }
 * 
 * ��һ�� lambda ����ʽ���� x �� y ���������β������������ǵĺͣ� �ڶ��� lambda ����ʽ�����ղ������������� ��42���� ������
 * lambda ����ʽ����һ���ַ�����������ӡ������̨��������ֵ��
 * 
 * @author
 * 
 * 		����Functional Interface������ʽ�ӿڣ����¼��FI����ѧϰJava8 Lambda����ʽ�Ĺؼ����ڣ�
 *         ���Է����ʼ���ۡ� FI�Ķ�����ʵ�ܼ򵥣��κνӿڣ����ֻ���� Ψһ һ�����󷽷�����ô������һ��FI��
 *         Ϊ���ñ�������������ȷ��һ���ӿ�����FI��Ҫ��Ҳ����˵���ҽ���һ�����󷽷���
 */

public class doubleColonTest {
	
	public static Functional3 testMethodReference(){
		System.out.println("����::��������");
		return null;
	}
	
	public static String ����(){
		System.out.println("����::��������");
		return null;
	}
	
	public static void main(String[] args) {
		
		/**�п������޸�2017.1**/
//		Functional3 s = doubleColonTest::testMethodReference;
		doubleColonTest d = new doubleColonTest();		
		/*
		 * ��Ҫע����ǣ�����ʽ�ӿڵ����Ʋ����� lambda ����ʽ��һ���֡�
		 * ��ô�������ˣ����ڸ����� lambda����ʽ������������ʲô��
		 * ���ǣ����������������������Ƶ����� ��������е� lambda ����ʽ������ Functional
		 * 
		 * �����������Ƶ� lambda ����ʽ���͡������� lambda ����ʽ�������������ڴ������� �����Ƶ���������ڴ������� ����Ϊ Ŀ�����͡�
		 * lambda ����ʽ--ֻ�ܳ���--��Ŀ������Ϊ����ʽ�ӿڵ��������С�
		 */
		Functional2<String> f1 = ()-> "fuc";//ʵ��һ��Functional2�࣬���ص���"fuc"
		Functional2<String> f11 = ()-> "fuc";//ʵ��һ��Functional2�࣬���ص���"fuc"
		
		/*
		 * ����Ŀ�����ͣ�����ʽ�ӿڣ��Ѿ���֪�� lambda����ʽ����ʽ�Σ�Formal parameter���ͣ�
		 * ��������û�б�Ҫ����֪�������ظ�һ�顣Ҳ����˵��lambda ����ʽ�Ĳ������Ϳ��Դ� Ŀ������ �еó���
		 */
		Functional<String> f2 = (a)->a ;//�������Ŀ�֪ a ��������String
		/*
		 * �� lambda �Ĳ���ֻ��һ�������������Ϳ��Ա��Ƶ���֪ʱ���ò����б���������� ���� ��ʡ�ԣ�
		 */
		Functional<String> f3 = a->a ;//
		/*
		 * �� lambda �Ĳ��������������������Ϳ��Ա��Ƶ���֪ʱ���ò����б���������� ������ ��ʡ�ԣ�
		 */
		Functional3<String,Boolean> ft = (a,b)->a ;
		Functional<String> f4 = a -> {
			System.out.println("ɵ��");
			return a;
		};
		f4.judge("lala");
		
		/**
		 * lambda����ʽ��ֵ��գ��Ա������ţ�lambda expressions close over values, not variables��
		 */
		int sum = 0 ;
		List<String> list = new ArrayList<String>();
		Functional<String> f5 = (a) -> {
//			sum = sum + 1;       //����  ��ֵ��գ����ܸı��ⲿ���ֵ
			list.add("moment");  //��ȷ���Ա�������
			return a;			
		};
		
		
		

		
	}
	
	public void print(){
		System.out.println("����һ����ӡ");
	}
	
//	public Object usePrint(doubleColonTest d){
//		return doubleColonTest::print;
//	}
	
	public void forBlock(){//�ֲ�������ȫ�ֱ�������ʾ��������ʽ�����������࣬�����ڲ���,���Ͳ��������أ��������ͣ�����ʱ���ͣ���������������������
//		int i = 0;
//		int sum = 0;
//		for (int i = 1; i < 10; i += 1) { //�������ֱ��������Ϊi�Ѿ���forѭ���ⲿ��������
//		  sum += i;
//		}
	}

}

@FunctionalInterface  //�ӿ��д˱�־��˵�����ڲ�ֻ����һ������,������������ν,��̬�������෽����,Ĭ�Ϸ������Լ����෽�������ں���ʽ�ӿڵķ���
interface Functional<T>{
	String ss = "2";
	T judge(T t);
	static boolean  dd() {  //��̬�������ڽӿ��ڿ����з�����Ŷ��
		return false;
	}
	
	public String toString();//������ Object �� toString���� �ӿ���û�з�����Ŷ��
	
}

@FunctionalInterface  //�ӿ��д˱�־��˵�����ڲ�ֻ����һ������,������������ν,��̬�������෽����,Ĭ�Ϸ������Լ����෽�������ں���ʽ�ӿڵķ���
interface Functional2<T>{
	String ss = "2";
//	T judge();  �˷������� sex()��������ͬʱ���ڣ���
	static boolean  dd() {
		return false;
	}
	
	abstract T sex();
	
}

@FunctionalInterface  
interface Functional3<T,K>{	
	abstract T sex(T t,K k);	
	
	
}
