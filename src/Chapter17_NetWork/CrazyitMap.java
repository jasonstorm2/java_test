package Chapter17_NetWork;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CrazyitMap<K,V> {
	
	public Map<K,V> map = Collections.synchronizedMap(new HashMap<K, V>());
	
	//通过值 删除该map指定键值对
	public synchronized void removeByValue(Object value){
		for (Object key : map.keySet()) {
			if(map.get(key) ==  value){
				map.remove(key);
				break;
			}			
		}
	}
	
	// 获取所有value 组成的集合set
	public synchronized Set<V> valueSet(){
		Set<V> result = new HashSet<V>();
		for (V value : map.values()) {
			result.add(value);
		}
		return result;
	}
	
	// 通过值 获取对应的可以
	public synchronized K getKeyByValue(V value){
		for (K key : map.keySet()) {
			if(map.get(key) == value || map.get(key).equals(value)){
				return key;
			}
		}
		return null;		
	}
	
	// 实现 put方法，该方法 不予许value重复
	public synchronized V put(K key , V value){
		for(V val:valueSet()){
			if(val.equals(value) && val.hashCode() == value.hashCode()){
				throw new RuntimeException("map实例中，不允许有重复的value!");
			}
		}
		
		return map.put(key, value);
	}

}
