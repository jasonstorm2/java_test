package my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class SetListMapTest {
	/*
	 * 遍历map的方法1
	 */
//	private static void visitMapByEntry(Map map) {
//		Iterator ite = map.entrySet().iterator();
//		
//		while(ite.hasNext()){
//			Map.Entry<Object, Object> entry = (Entry<Object,Object>) ite.next();
//			Object key = entry.getKey();//map中的key
//			Object value = entry.getValue();//上面key对应的value
//		}
//	}
	/*
	 * 遍历map的方法2
	 */
	String ss = "";
	private static void visitMapByKey(Map<String,Object> map) {
		Iterator<String> keys = map.keySet().iterator();
		
		while(keys.hasNext()){
			Object key = keys.next();//key
			Object value = map.get(key);//上面key对应的value
			System.out.println(value);
		}
	}
	public static void main(String[] args) {
		//此例子说明了set中不能有相同的元素
		Set<Object>  set=new HashSet<>();
		   set.add("abc");
		   set.add("cde");
		   set.add("efg");
		   set.add("fgh");  
		   set.add("包");    
		   set.add("快");
		   
		   set.add("abc"); //重复的abc,set会自动将其去掉   
		   System.out.println("size="+ set.size() );
		 List<Object> list = new ArrayList<>();
		    list.add("abc");
		    list.add("aaa");
		    list.add("去");
		    list.add("我");
		    list.add("额");
		    list.add("请");
		 Map<String,Object> map = new HashMap<>();
		    map.put("first", 1);
		    map.put("second", 2);
		    map.put("third", 3);
		    map.put("forth", 4);
		    map.put("fifth", 5);
		 Map<String,Object> linkmap = new LinkedHashMap<>();
		 linkmap.put("first", 11);
		 linkmap.put("second", 22);
		 linkmap.put("third", 33);
		 linkmap.put("forth", 44);
		 linkmap.put("fifth", 55);

		    
		    //list 输出有序
		    for( Iterator<Object> it = list.iterator();  it.hasNext(); )
	        {             
	            System.out.println(it.next());            
	        }
		    System.out.println("set--------------------------------------------"); 
            //set输出无序
		    for( Iterator<Object> it = set.iterator();  it.hasNext(); )
	        {             
	            System.out.println(it.next());            
	        }
		    System.out.println("map--------------------------------------------"); 
		    //map输出
		    visitMapByKey(map);
		    System.out.println("map--------------------------------------------"); 
		    
		  /*
		   * 遍历map的方法3
		   */
		    for(Object obj : map.values()) {
		        //blabla
		    	System.out.println(obj);
		    }
		    System.out.println("linkmap--------------------------------------------"); 
		    for(Object obj:linkmap.values()){
		    	System.out.println(obj);
		    	
		    }

		    
		    
		    
		    
		    
		    set.addAll(list); //将list中的值加入set,并去掉重复的abc
		         System.out.println("size="+ set.size() );
		        for( Iterator<Object> it = set.iterator();  it.hasNext(); )
		        {             
		            System.out.println("value="+it.next().toString());            
		        } 
		    //treeSet	
		    Set<Object> st = new TreeSet<>();
		    
		 }  

}
