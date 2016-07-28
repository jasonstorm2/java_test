package my;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 *1.串行化能保存的元素 
 *  串行化只能保存对象的非静态成员变量，不能保存任何的成员方法和静态的成员变量，而且串行化保存的只是变量的值，对于变量的任何修饰符都不能保存。
 *  
 *  2.transient关键字 

            对于某些类型的对象，其状态是瞬时的，这样的对象是无法保存其状态的。例如一个Thread对象或一个FileInputStream对象 ，
            对于这些字段，我们必须用transient关键字标明，否则编译器将报措。 
            
            另外 ，串行化可能涉及将对象存放到 磁盘上或在网络上发达数据，这时候就会产生安全问题。因为数据位于Java运行环境之外，不在Java安全机制的控制之中。
            对于这些需要保密的字段，不应保存在永久介质中 ，或者不应简单地不加处理地保存下来 ，为了保证安全性。
            应该在这些字段前加上transient关键字。
 */
public class SerializableTest {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(11);
		list.add(22);
		Student stu2 = new Student("jason", 229, "male",list);
		/*
		 * "/"符号等价于"\\"
		 * 如果输出流 FileOutputStream（路径）路径指示的文件不存在，那么，将生成这个文件
		 * 
		 */
		/********写入指定位置文件*********/
//		try {
//			FileOutputStream fo = new FileOutputStream("E:/Serializable.ser");
//			ObjectOutputStream oo = new ObjectOutputStream(fo);
//			
//			oo.writeObject(stu2);
//			oo.close();
//			System.out.println("写入成功");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		/********读出指定位置文件*********/
		

		try {
			System.out.println("读出.....");
			FileInputStream fi = new FileInputStream("E:/Serializable.ser");
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Student s = (Student)oi.readObject();
			System.out.println("名字："+s.name);
			System.out.println("性别："+s.sex);
			System.out.println("年龄："+s.age);
			List<Integer> l = s.list;
			for(Integer i:l){
				System.out.println("list:"+i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}


class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List list;
	
	String name;
	int age ;
	String sex;
	
	public Student(String name,int age,String sex,List<Integer> list) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.list = list;
	}	
}

class LoggingInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Date loggingDate = new Date();
	private String uid;
	private transient String pwd;

	LoggingInfo(String user, String password) {
		uid = user;
		pwd = password;
	}

	public String toString() {
		String password = null;
		if (pwd == null) {
			password = "NOT SET";
		} else {
			password = pwd;
		}
		return "logon info: \n   " + "user: " + uid + "\n   logging date : "
				+ loggingDate.toString() + "\n   password: " + password;
	}

	public static void main(String[] args) {
		LoggingInfo logInfo = new LoggingInfo("MIKE", "MECHANICS");
		System.out.println(logInfo.toString());
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(
					"logInfo.out"));
			o.writeObject(logInfo);
			o.close();
		} catch (Exception e) {// deal with exception
		}
		// To read the object back, we can write
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"logInfo.out"));
			LoggingInfo logInfo1 = (LoggingInfo) in.readObject();
			System.out.println(logInfo1.toString());
		} catch (Exception e) {// deal with exception
		}
	}
}