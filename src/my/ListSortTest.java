package my;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List 的重新排序测试
 * 使用Collections.sort(baseList);方法可以由小到大排序
 * @author Administrator
 *
 */
public class ListSortTest {
	public int age;
	public String name;
	
	public ListSortTest(){
		
	}
	
	public ListSortTest(int age){
		this.age = age;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += age;
		return str;	
	};
	
	public static void main(String[] args) {
		List<Integer> baseList =  new ArrayList<Integer>();
		baseList.add(11);
		baseList.add(2);
		baseList.add(33);
		baseList.add(54);
		baseList.add(5);
		Collections.sort(baseList);
		System.out.println(baseList);
		
		sortList();
	}
	
	public static void sortList(){
		List<ListSortTest> guildList =  new ArrayList<ListSortTest>();
		ListSortTest l1 = new ListSortTest(50);
		ListSortTest l2 = new ListSortTest(10);
		ListSortTest l3 = new ListSortTest(55);
		ListSortTest l4 = new ListSortTest(34);
		guildList.add(l1);
		guildList.add(l2);
		guildList.add(l3);
		guildList.add(l4);
		
		// 降序 由大到小
		Collections.sort(guildList, new Comparator<ListSortTest>(){
			@Override
			public int compare(ListSortTest guild1, ListSortTest guild2) {
				// TODO Auto-generated method stub
	              if(guild1.age> guild2.age){  
	                    return -1;  
	                }  
	                if(guild1.age == guild2.age){  
	                    return 0;  
	                }  
	                return 1;  
			}			
		});	
		
		System.out.println(guildList.toString());
	}

}
