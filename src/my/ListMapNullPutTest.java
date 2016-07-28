package my;

import java.util.*;

/**
 * list 和 map 都可以放入null的元素，但是，map的key 不能为空
 * @author Administrator
 *
 */
public class ListMapNullPutTest {
	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		String ss = null;
		Map<Integer,String> m = new HashMap<Integer, String>();
		l.add(null);
		l.add("huhu");
		System.out.println("list的大小："+l.size());
		m.put(1, null);
		m.put(2, "sdfs");
		System.out.println("map的大小："+m.size());
		m.put(3, ss);
		System.out.println("map的大小："+m.size());
	}
}
