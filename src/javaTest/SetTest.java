package javaTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SetTest {
	public static int COUNT=0;
	public static void main(String[] args) {
		Set<String> words = new HashSet<String>();
		long totalTime = 0;
		Scanner in = new Scanner(System.in);
		
		while(in.hasNext()){
			String word = in.next();
			long callTime = System.currentTimeMillis();
			words.add(word);
			callTime = System.currentTimeMillis() -callTime;
			totalTime += callTime;	
			System.out.println(word);
		}
		
		Iterator<String> iter = words.iterator();
		for(int i=1;i<=20 && iter.hasNext();i++){
			System.out.println(iter.next());
			System.out.println("_____________");
			System.out.println(words.size()+"distinct words."+totalTime+"milliseconds");
			
		}
		System.out.println("lllll");
		
		
	}

}
