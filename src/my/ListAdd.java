package my;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListAdd {
	public static void main(String[] args) {
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
		int s =0;
		System.out.println(s);
		System.out.println("list:"+list);
		
	}

}
