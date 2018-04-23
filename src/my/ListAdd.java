package my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAdd {
	public static void main(String[] args) {
//		StringToListMap();
		ListForEachInForEachTest();
	}
	
	/**
	 * String 转化为 String[],在转化为 List<Map<String,Integer>>
	 */
	public static void StringToListMap(){
		String str = "{\"10006\":5,\"10012\":1,\"10010\":1,\"10008\":1,\"10019\":1,\"10002\":10}";

		List<Map<String,Integer>> list = new ArrayList<Map<String,Integer>>();
		str = str.replace("{", "").replace("}", "").replace("\"", "").replace(":", ",");
		String [] array = str.split(",");
		int j=0;
		for(int i=0;i<array.length;i+=2){
			Map<String,Integer> map = new HashMap<String, Integer>();
			map.put(array[i], Integer.valueOf(array[i+1]));
			list.add(j,map);
			j++;
		}
		System.out.println("list:"+list);
	}
	public static class DATA{
		String key = "";
	    int value = 0;
		
	}
	public static void ListForEachInForEachTest(){
		DATA data = new DATA();
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		
		for (Integer i: list) {
			System.out.println("i="+i);
			for (Integer l: list) {
				System.out.println("l="+l);
			}
		}
		
	}

}
