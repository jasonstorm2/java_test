package my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List 的重新排序测试
 * 使用Collections.sort(baseList);方法可以由小到大排序
 * @author LiZhenhua
 *
 */
public class ListSortTest {
	public int age;
	public String name;
	public int high;
	
	public ListSortTest(){
		
	}
	
	public ListSortTest(int age,int high,String name){
		this.age = age;
		this.high = high;
		this.name = name;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += age;
		return str;	
	};
	
	public static void main(String[] args) {
		utils.utils.PrintLine("普通排序方法Collections.sort(baseList)");
		List<Integer> baseList =  new ArrayList<Integer>();
		baseList.add(11);
		baseList.add(2);
		baseList.add(33);
		baseList.add(54);
		baseList.add(5);
		Collections.sort(baseList);
		System.out.println(baseList);
		utils.utils.PrintLine("自定义排序方法sortList()");
		sortList();
	}
	
	public static void sortList(){
		List<ListSortTest> guildList =  new ArrayList<ListSortTest>();
		ListSortTest l1 = new ListSortTest(50,165,"百川");
		ListSortTest l2 = new ListSortTest(20,170,"吉星");
		ListSortTest l3 = new ListSortTest(55,166,"华丰");
		ListSortTest l4 = new ListSortTest(20,180,"东南");
		guildList.add(l1);
		guildList.add(l2);
		guildList.add(l3);
		guildList.add(l4);
		
		// 左边A1比右边B1大，返回正值1，则右边靠前： B1,A1
		// 排序规则，首先，按年龄排序，年轻越小拍越前
		// 其次,按身高排序，身高约高越拍前
		// 降序 由大到小
		Collections.sort(guildList, new Comparator<ListSortTest>(){
			@Override
			public int compare(ListSortTest guild1, ListSortTest guild2) {				
	              if(guild1.age> guild2.age){
	                    return 1;  
	                } else if(guild1.age == guild2.age){  
	                   if(guild1.high >= guild2.high){
	                	   return -1;  
	                   }else{
	                	   return 1;  
	                   }
	                } else {
	                	 return -1;  
	                } 
	               
			}			
		});			
		ListtoString(guildList);
	}
	
	public static void ListtoString(List<ListSortTest> list){
		List<String> nameList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			nameList.add(list.get(i).name);
		}	
		System.out.println(nameList.toString());
	}

}
