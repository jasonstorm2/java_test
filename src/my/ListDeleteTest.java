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
		Data d1 = new Data(1, 18);
		Data d2 = new Data(2, 20);
		Data d3 = new Data(3, 41);
		Data d4 = new Data(4, 55);
		List<Data> list = new ArrayList<Data>();
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		System.out.println(toString(list));	
		//1.
		List<Data> list2 = new ArrayList<Data>();
		for(Data d:list){
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
	public static String toString(List<Data> list) {
		String str = "list 所有的id:  ";
		for(Data d: list){
			str+=d.id+",";
		}		
		
		return str;
		
		
	}
}

class Data{
	int id;
	int age;
	public Data(int id ,int age){
		this.id = id;
		this.age = age;
	}
}
