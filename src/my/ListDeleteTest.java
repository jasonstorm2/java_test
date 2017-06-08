package my;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList引起的ConcurrentModificationException 异常原因及解决方法
 * 
 * Iterator是工作在一个独立的线程中，并且拥有一个mutex锁，就是说Iterator在工作的时候，是不允许被迭代的对象被改变的。
 * Iterator被创建的时候，建立了一个内存索引表（单链表）
 * 这个索引表指向原来的对象，当原来的对象数量改变的时候，这个索引表的内容没有同步改变，
 * 所以当索引指针往下移动的时候 ，便找不到要迭代的对象，于是产生错误。
 * List、Set等是动态的，可变对象数量的数据结构，但是Iterator则是单向不可变，
 * 只能顺序读取，不能逆序操作的数据结构 ，当Iterator指向的原始数据发生变化时，Iterator自己就迷失了方向。
 * 
 * List、Set、Map 都可以通过Iterator进行遍历，这里仅仅是通过List举例，
 * 在使用其他集合遍历时进行增删操作都需要留意是否会触发ConcurrentModificationException异常。
 * 
 * 参考网址：	http://www.itnose.net/detail/6214432.html
 *     			http://swiftlet.net/archives/743
 * 
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
		for (Iterator<Data1> it = list.iterator(); it.hasNext();) {
			Data1 d = it.next();
		       if (d.id == 4) {
		           it.remove();  // 用Iterator的remove方法才不会出错
		      }
		}
		System.out.println(toString(list));	
		//3.
//		for (Iterator<Data1> it = list.iterator(); it.hasNext();) {
//			Data1 d = it.next();
//		       if (d.id == 3) {
//		    	   list.remove(d); //遍历时，不能用list的remove方法 此处会出现ConcurrentModificationException异常
//		      }
//		}
//		System.out.println(toString(list));	
		//4.
		for (Data1 data:list)  {
			if(data.id == 3){
				list.remove(data);  // foreach 遍历时删除也会出错
			}
		}
		System.out.println(toString(list));	
		
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
