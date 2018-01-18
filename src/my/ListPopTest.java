package my;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * 
 * pop() 从顶部弹出，即从index0弹出元素,空时报错
 * poll()从顶部弹出，即从index0弹出元素,空时返回null
 * 
 * pollLast() 从底部弹出元素，即从indexmax 弹出元素，空时返回null
 * pollFirst()从顶部弹出元素，即从index0 弹出元素，空时返回null
 * 
 * push() 从顶部压入元素，即从index0压入元素
 * 
 * LinkedList 弹出 压入测试 pop and push子弹夹
 * linkList.pollLast()，从底部拉出数据，直到null
 * @author Administrator
 *
 */
public class ListPopTest {
	public static void main(String[] args) {
		System.out.println("--------------ConcurrentLinkedQueue--------------------");
		ConcurrentLinkedQueue<Integer> memberLocation = new ConcurrentLinkedQueue<>();
		memberLocation.add(1);
		memberLocation.add(2);
		memberLocation.add(4);
		memberLocation.add(13);
		System.out.println("memberLocation里面的元素："+memberLocation);
		memberLocation.poll();
		System.out.println("memberLocation里面的元素："+memberLocation);
		System.out.println("memberLocation.peek():"+memberLocation.peek());		
		System.out.println("memberLocation里面的元素："+memberLocation);
		
		System.out.println("--------------ConcurrentLinkedQueue--------------------");
		
		//list has no a METHOD call "pop"
		List<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(2);
		list.add(6);
		list.add(7);
		System.out.println(list);
		//LinkedList has a METHOD name "pop"
		LinkedList<Integer> linkList = new LinkedList<Integer>();
		linkList.add(55);
		linkList.add(22);
		linkList.add(1);
		linkList.add(4);
		linkList.add(6);
		System.out.println("linkList里面的元素："+linkList);
		linkList.pop();
		System.out.println("linkList里面的元素："+linkList);
//		System.out.println("弹出元素："+linkList.pop());
		System.out.println("弹出元素："+linkList.pop());
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList里面的元素："+linkList);
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList.pollLast():"+linkList.pollLast());
		System.out.println("linkList里面的元素："+linkList);
		linkList.push(10);
		System.out.println("linkList里面的元素："+linkList);
		linkList.push(111);
		System.out.println("linkList里面的元素："+linkList);

		System.out.println("linkList里面的poll元素："+linkList.poll());	
		System.out.println("linkList里面的pop元素："+linkList.pop());
		System.out.println("linkList里面的poll元素："+linkList.poll());
		System.out.println("linkList里面的pollFirst元素："+linkList.pollFirst());
		linkList.push(11);
		linkList.push(110);
		linkList.push(101);
		System.out.println("linkList里面的元素："+linkList);
		System.out.println("linkList里面的pollFirst元素："+linkList.pollFirst());
		System.out.println("-----------------------------------");
		ConcurrentLinkedDeque<Integer> location = new ConcurrentLinkedDeque<>();	
		location.add(1);
		location.add(2);
		location.add(3);
		popLocation(location, 4);
		
	}
	
	// 弹出方法判断
	public static int popLocation(ConcurrentLinkedDeque<Integer> location ,int position){
		int pos = 0;
		if(position != 0 && location.contains(position)){			
			location.remove(position);
			pos = position;			
		}else{
			pos = location.poll();
		}
		
		return pos;		
	}

}
