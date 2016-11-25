package my;

import java.util.HashMap;
import java.util.Map;

import utils.utils;

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
	}
}
