package my;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList引起的ConcurrentModificationException 异常原因及解决方法
 * 参考网址：http://www.itnose.net/detail/6214432.html
 * @author Administrator
 *
 */
public class ListDeleteTest {
	public static void main(String[] args) {
		Data1 d1 = new Data1(1, 18);
		Data1 d2 = new Data1(2, 20);
		Data1 d3 = new Data1(3, 41);
		Data1 d4 = new Data1(4, 55);
		List<Data1> list = new ArrayList<Data1>();
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		System.out.println(toString(list));	
		//1.
		List<Data1> list2 = new ArrayList<Data1>();
		for(Data1 d:list){
			if(d.id == 2){
				list2.add(d);
			}
		}
		list.removeAll(list2);
		System.out.println(toString(list));	
		
		//2.
//		for (Iterator<Data> it = list.iterator(); it.hasNext();) {
//		      Data d = it.next();
//		       if (d.id == 3) {
//		           it.remove();  // ok
//		      }
//		}
//		System.out.println(toString(list));	
		
	}
	public static String toString(List<Data1> list) {
		String str = "list 所有的id:  ";
		for(Data1 d: list){
			str+=d.id+",";
		}		
		
		return str;
		
		
	}
}

class Data1{
	int id;
	int age;
	public Data1(int id ,int age){
		this.id = id;
		this.age = age;
	}
}
