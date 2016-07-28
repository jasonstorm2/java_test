package javaTest;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<>();
		parts.add(new Item("Toaster",1234));
		parts.add(new Item("Widget",4562));
		parts.add(new Item("Modem",9912));
		System.out.println(parts);
		
		SortedSet<Item> sortByDescription = new TreeSet<>(new Comparator<Item>() {

			@Override
			public int compare(Item a, Item b) {
				// TODO Auto-generated method stub
				String desA = a.getDescription();
				String desB = b.getDescription();
				return desA.compareTo(desB);
			}
		});
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
		
	}

}
