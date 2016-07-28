package my;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPopTest {
	public static void main(String[] args) {
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
		System.out.println(linkList);
		linkList.pop();
		System.out.println(linkList);
		System.out.println("µ¯³öÔªËØ£º"+linkList.pop());
		System.out.println(linkList);
		System.out.println(linkList.get(2));
		linkList.push(6);
		System.out.println(linkList);		
	}

}
