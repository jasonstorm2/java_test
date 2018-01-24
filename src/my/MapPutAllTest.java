package my;

import java.util.HashMap;
import java.util.Map;

/**
 * putAll测试：putAll可以合并两个map,如果两个map有相同的key,那么取被putAll的map的值(后面的覆盖前面的)
 * 
 * putAll()方法并不会改变被put的map的引用
 * @author Administrator
 *
 */
public class MapPutAllTest {
	public static void main(String[] args) {
		test obj = new test();
		 //两个map具有不同的key
		Map<Object, Integer> map1 = new HashMap<Object, Integer>();
		map1.put(1, 1);
		map1.put(obj.<String> ll(), 12);
		
		System.out.println("map1:"+map1);
		
		Map<Integer, Integer> map2 = new HashMap<Integer, Integer>();
		map2.put(22, 22);
		map2.put(23, 23);
		
		map1.putAll(map2);
		System.out.println("map1:"+map1);
		System.out.println("map2:"+map2);
		
		//两个map具有重复的key
		Map<Integer, Integer> map3 = new HashMap<Integer, Integer>();
		map3.put(22, 1);
		System.out.println("map3:"+map3);
		
		Map<Integer, Integer> map4 = new HashMap<Integer, Integer>();
		map4.put(22, 22);
		map4.put(23, 23);
		System.out.println("map4:"+map4);
		
		map3.putAll(map4);
		System.out.println("map3:"+map3);
		System.out.println("map4:"+map4);		
	}
}

class test{
	public Integer ll(){
		System.out.println("go away");

		return 55;
	}
}
