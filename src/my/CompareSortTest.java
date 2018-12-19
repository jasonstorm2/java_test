package my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareSortTest {
	public static void main(String[] args) {		

		List<Long> longList = new ArrayList<Long>(Arrays.asList(1536101414555l,
				1536142042181l,
				1527004802332l,
				1527004802332l,
				1527005983995l,
				1527005988416l,
				1527005998872l,
				1527006007998l,
				1527076584680l,
				1527117510184l,
				1527132388178l,
				1527137824229l,
				1527202526302l,
				1527341041327l,
				1527341041327l,
				1527345706529l,
				1527345721216l,
				1527859410151l,
				1528711840153l,
				1531265831075l,
				1531265838267l,
				1531265848490l,
				1531265857409l,
				1531265877682l,
				1531265891700l,
				1531265904233l,
				1531265923134l,
				1531265940972l,
				1531265952626l,
				1531527074968l,
				1531527098252l,
				1531527102290l,
				1531527109941l,
				1531527128568l,
				1531540187910l,
				1531542612443l,
				1531650628561l,
				1532242300437l,
				1532364565612l,
				1532694943469l,
				1532694952850l,
				1533430771227l,
				1534993621676l,
				1534993628475l,
				1535105332182l,
				1535677109656l,
				1535677148635l,
				1535677151447l,
				1535677161305l,
				1535677169093l,
				1535677181485l,
				1535677193317l));
		
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		User user4 = new User();
		User user5 = new User();
		User user6 = new User();
		User user7 = new User();
		User user8 = new User();		
		
		user1.setInteger(1);
		user2.setInteger(2);
		user3.setInteger(3);
		user4.setInteger(4);
		user5.setInteger(5);
		user6.setInteger(5);
		user7.setInteger(1);
		user8.setInteger(1);

		List<User> list = new ArrayList<User>();
		list.add(user1);
		list.add(user2);
		list.add(user3);		
		list.add(user4);
		list.add(user5);
		list.add(user6);
		list.add(user7);
		list.add(user8);
		
		utils.utils.PrintLine("sort(数组排序)--");

		Collections.sort(list);
		for (User u : list) {
			System.out.println(u.getInteger());
		}	
		
		utils.utils.PrintLine("lambda表达式排序--");	
		Collections.sort(list, (m1, m2) -> (m2.getInteger()-m1.getInteger()));

		
		utils.utils.PrintLine("重写Comparator方法,没有返回相同值=0");		
		Collections.sort(list,new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				return o1.getInteger() < o2.getInteger() ? 1 : -1;
			}
		});
		
		utils.utils.PrintLine("重写Comparator方法2,没有返回相同值=0");		
		Collections.sort(list,new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				if(o1.getInteger() < o2.getInteger()){
					return 1;
				}else{
					return -1;
				}
			}
		});
		utils.utils.PrintLine("重写Comparator方法,返回相同值=0");		
		Collections.sort(list,new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				// TODO Auto-generated method stub
				if(o1.getInteger() < o2.getInteger()){
					return 1;
				}else if(o1.getInteger() > o2.getInteger()){
					return -1;
				}
				return 0;
			}
		});
		utils.utils.PrintLine("long类型的比较测试");
		Collections.sort(longList, (m1, m2) -> (int)(m1-m2));
		printValue(longList);
		utils.utils.PrintLine("修改lambda语句：");
		Collections.sort(longList, (m1, m2) -> {
			if(m2-m1 >0){
				return 1;
			}else if(m2-m1 <0){
				return -1;
			}else{
				return 0;
			}
		});
		printValue(longList);
		utils.utils.PrintLine("long类型的比较测试，重写Comparator方法,没有返回相同值=0");
		Collections.sort(longList,new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				return (int)(o1 - o2);
			}
		});
		printValue(longList);
		utils.utils.PrintLine("模拟报错的lambda表达式，并不能正确的排列");
		Collections.sort(longList,new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				return (int)(o2 - o1);
			}
		});
		printValue(longList);		
		utils.utils.PrintLine("正确的写法");
		Collections.sort(longList,new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				// TODO Auto-generated method stub
				if(o2 - o1 >0){
					return 1;
				}else if(o2 - o1 <0){
					return -1;
				}else{
					return 0;
				}				
			}
		});
		printValue(longList);
	}
	
	
	public static void printValue(List<Long> list){
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(3));
		System.out.println(list.get(4));
		
	}
}





class User implements Comparable<User> {

	private Integer integer;
	private String name = null;
	public long time;

	public Integer getInteger() {
		return integer;
	}

	public void setInteger(Integer integer) {
		this.integer = integer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(User o) {
		// TODO Auto-generated method stub
		return this.integer.compareTo(o.integer);
	}

}
