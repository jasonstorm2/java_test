package my;

import java.util.HashMap;
import java.util.Map;

import utils.utils;

/**
 * 1. map 值的排序测试
 * 
 * 2. map 是否能放空值，是否空值能代替赋值
 * @author LiZhenhua
 *
 */
public class MapSortTest {
	public static void main(String[] args) {
		Map<String,Integer> map  = new HashMap<String,Integer>();
		map.put("ss", 4);
		map.put("sd", 2);
		map.put("sddds", 1);
		map.put("sasds", 8);
		map.put("qqs", 89);
		utils.printMap(map);
		Map<String,Integer> map2  = new HashMap<String,Integer>();
		map2 = utils.sortMap(map);
		utils.printMap(map2);
		
		putNullToMap(map2);
	}
	
	
	/**
	 * 向 map 放入 key - null键值对，是否存放空值
	 * 向 key - null键值对 再存入键值 看看结果
	 * @param map
	 */
	public static void putNullToMap(Map<String,Integer> map){
		map.put("empty", null);
		System.out.println("*******放入null值：");
		utils.printMap(map);
		System.out.println("*******覆盖null值：");
		if(map.containsKey("empty")){
			map.put("empty", 000);
		}
		utils.printMap(map);
	}
}
