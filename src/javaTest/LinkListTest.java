package javaTest;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class LinkListTest {
	public static long length = 1000000l;
	public static void main(String[] args) {
		List<String> a = new LinkedList<>();
		List<String> b = new LinkedList<>();
		a.add("AMY");
		a.add("CARL");
		a.add("ERICA");
		
		b.add("BOB");
		b.add("DOUG");
		b.add("FRANCES");
		b.add("GLORIA");
		
		ListIterator<String> aIter = a.listIterator();//两个的迭代器不同哦
		Iterator<String> bIter = b.iterator();
		
		while(bIter.hasNext()){
			if(aIter.hasNext())aIter.next();
			aIter.add(bIter.next());
		}
		System.out.println(a);
		
		//remove every second word from b 间隔删除元素
		bIter = b.iterator();
		
		while(bIter.hasNext()){
			bIter.next();//skip one element
			if(bIter.hasNext()){
				bIter.next();//skip new elemet
				bIter.remove();
			}
		}
		System.out.println(b);
		
		//bulk operation; remove all words in b from a
		a.removeAll(b);
		System.out.println(a);
	}

}
