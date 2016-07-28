package my;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CompareSortTest {
	public static void main(String[] args) {
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();

		user1.setInteger(1);
		user1.setName("one");
		user2.setInteger(2);
		user2.setName("two");

		user3.setInteger(3);
		user3.setName("three");

		List<User> list = new ArrayList<User>();

		list.add(user2);
		list.add(user3);
		list.add(user1);
		System.out.println(list.get(0).getName());
		System.out.println(list.get(1).getName());
		System.out.println(list.get(2).getName());
		System.out.println("------------------------Ö®Ç°µÄÅÅĞò");

		Collections.sort(list);
		for (User u : list) {
			System.out.println(u.getName());
		}
		System.out.println(list.get(0).getName());
		System.out.println(list.get(1).getName());
		System.out.println(list.get(2).getName());

	}
}

class User implements Comparable<User> {

	private Integer integer;
	private String name = null;

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
