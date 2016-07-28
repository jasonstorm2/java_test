package my;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Map遍历测试
 * @author Administrator
 *
 */
public class MapTraverseTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "one");
		map.put("two", "two");
		map.put("3", "three");
		map.put("4", "four");
		// 1.
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
		}
		System.out.println("---------------------------");
		// 2.
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}
		System.out.println("---------------------------");
		// 3
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= "
					+ entry.getValue());
		}
		System.out.println("---------------------------");
		// 4
		System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
		for (String v : map.values()) {
			System.out.println("value= " + v);
		}
	}

}
