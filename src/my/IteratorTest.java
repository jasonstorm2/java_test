package my;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {
	public static void main(String[] args) {
		listIterator(1, 5);

	}
	
	public static void normaoTest(){
		List<Object> l = new ArrayList<>();
		String two = new String("bb");
		Integer d = new Integer(5);
		
		 l.add("aa");
		 l.add("aa");
		 l.add(two);
		 l.add("cc");
		 l.add(d);
		 l.add(d);
		 
		 for (Iterator iter = l.iterator(); iter.hasNext();) {
			  Object str =iter.next();
			  System.out.println(str);
			 }
		 
		 
		 Iterator iter = l.iterator();
		 while(iter.hasNext()){
			 Object str = iter.next();
		  if(str=="bb"){
			  iter.remove();
			  continue;
		  }
		  System.out.println("使用迭代器Iterator:"+str);
		 }
		 for(int i=0;i<l.size();i++){
			 System.out.println("使用list的下标index："+l.get(i));
			 
		 }
	}
	
	public static void listIterator(int from,int to){
		List<String> l = new ArrayList<>();		
		 l.add("aa");
		 l.add("bb");
		 l.add("cc");
		 l.add("dd");
		 l.add("ee");
		 l.add("ff");
		 l.add("gg");
		 l.add("hh");
		 l.add("ii");
		 l.add("jj");
		 l.add("kk");
		 l.add("ll");
		 boolean flag = from==1;
		 for (ListIterator<String> iter=l.listIterator(from-1); iter.hasNext(); from++) {
				if ((!flag&&from>to) || from>100)
					break;
				String info = iter.next();
				System.out.println(info);

			}	
	}
	
}
