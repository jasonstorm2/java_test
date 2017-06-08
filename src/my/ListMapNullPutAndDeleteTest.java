package my;

import java.util.*;
import java.util.Map.Entry;

/**
 * list 和 map 都可以放入null的元素，但是，map的key 不能为空
 * 
 * Map遍历删除测试
 * 
 * @author Administrator
 *
 */
public class ListMapNullPutAndDeleteTest {
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
		
		/**
		 * Map遍历删除测试
		 */
//		ListMapNullPutAndDeleteTest.delete1(m);
//		ListMapNullPutAndDeleteTest.delete2(m);
		ListMapNullPutAndDeleteTest.delete3(m);
		
	}
	
	private static void delete1(Map<Integer,String> m){
		for(Entry<Integer, String> entry :m.entrySet()){
			if(entry.getKey() == 1){
				m.remove(entry.getKey());  //false
			}			
		}
		System.out.println("map的值："+m.toString());
	}
	
	private static void delete2(Map<Integer, String> m) {
		  Iterator<Map.Entry<Integer, String>> it = m.entrySet().iterator();  
	        while(it.hasNext()){  
	            Map.Entry<Integer, String> entry = it.next();  
	            if(entry.getKey() == 3)  
//	            	m.remove(entry.getKey()); // false
	                it.remove();//使用迭代器的remove()方法删除元素  
	        }
	        
	        System.out.println("map的值："+m.toString());
	}
	
	private static void delete3(Map<Integer, String> m) {
        Iterator<Map.Entry<Integer, String>> it = m.entrySet().iterator();  
        while(it.hasNext()){  
            Map.Entry<Integer, String> entry=it.next();  
            int key=entry.getKey();  
            if(key==1){  
                System.out.println("delete this: "+key+" = "+key);  
                m.put(1, "奇数");   //ConcurrentModificationException  ok??????为什么，可以放入，但不可以删除
//                m.remove(key);      //ConcurrentModificationException
//                it.remove();        //OK   使用Iterator的remove方法移除当前对象  map的remove 方法会报错
            }  
        }
	        
	        System.out.println("map的值："+m.toString());
	}
	
}
