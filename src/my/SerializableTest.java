package my;

import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import utils.utils;

/**
 * 
 * @author Administrator
 *1.串行化能保存的元素 ---static 变量不能保存
 *  串行化只能保存对象的非静态成员变量，不能保存任何的成员方法和静态的成员变量，
 *  而且串行化保存的只是变量的值，对于变量的任何修饰符都不能保存。
 *  
 *  2.transient关键字 

     对于某些类型的对象，其状态是瞬时的，这样的对象是无法保存其状态的。例如一个Thread对象或一个FileInputStream对象 ，
     对于这些字段，我们必须用transient关键字标明，否则编译器将报措。 
            
     另外 ，串行化可能涉及将对象存放到 磁盘上或在网络上发达数据，这时候就会产生安全问题。
     因为数据位于Java运行环境之外，不在Java安全机制的控制之中。
     对于这些需要保密的字段，不应保存在永久介质中 ，或者不应简单地不加处理地保存下来 ，为了保证安全性。
     应该在这些字段前加上transient关键字。
    3.默认序列化机制：
     如果仅仅只是让某个类实现Serializable接口，而没有其它任何处理的话，则就是使用默认序列化机制。
     使用默认机制，在序列化对象时，不仅会序列化当前对象本身，还会对该对象引用的其它对象也进行序列化，
     同样地，这些其它对象引用的另外对象也将被序列化，以此类推。
     所以，如果一个对象包含的成员变量是容器类对象，而这些容器所含有的元素也是容器类对象，
     那么这个序列化的过程就会较复杂，开销也较大。
 */
public class SerializableTest {
	
	public static void main(String[] args) {		
		utils.cleanFile("E:/", ".ser");		
		String serPath = utils.getProperty("serPath");
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(11);
		list.add(22);		
//		Student stu2 = new Student("jason", 229, "male",list);
//		Student2 stu2 = new Student2("jason", 229, "male",list);
		Student2 stu2 = Student2.getInstance();


		/*
		 * "/"符号等价于"\\"
		 * 如果输出流 FileOutputStream（路径）路径指示的文件不存在，那么，将生成这个文件
		 * 
		 */
		/********写入指定位置文件*********/
		try {
			FileOutputStream fo = new FileOutputStream(serPath);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			
			oo.writeObject(stu2);
			oo.close();
			fo.close();//为什么不加上这句呢
			System.out.println("写入成功");
			System.out.println(stu2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/********读出指定位置文件*********/
		

		try {
//			new Student(serPath, 0, serPath, list).name = "测试是不是从磁盘写出的";
			System.out.println("读出.....");
			FileInputStream fi = new FileInputStream(serPath);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Object s = oi.readObject();
			System.out.println(s);
			System.out.println("对象是否相等："+(s==stu2));
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

/**
 * 当重新读取被保存的Person对象时，并没有调用Person的任何构造器，看起来就像是直接使用字节将Person对象还原出来的。
   当Person对象被保存到person.out文件中之后，我们可以在其它地方去读取该文件以还原对象，
   但必须确保该读取程序的CLASSPATH中包含有Person.class(哪怕在读取Person对象时并没有显示地使用Person类，如上例所示)，
   否则会抛出ClassNotFoundException。
 * @author Administrator
 *
 */
class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Integer> list;
	
	String name;
	transient int age ;
	String sex;
	
	public Student(){
		System.out.println("Student无参数构造器");
	}
	
	public Student(String name,int age,String sex,List<Integer> list) {
		System.out.println("Student有参数构造器");
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.list = list;
	}	
	
	/*
	 * 在Student类中添加两个方法：writeObject()与readObject()
	 * private方法，那么它们是如何被调用的呢？毫无疑问，是使用反射
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();//执行默认的序列化机制
//        out.writeInt(age);//调用writeInt()方法显示地将age字段写入
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();//执行默认的序列化机制
//        age = in.readInt();
    }
	
	public String toString(){
		String l = "";
		for (Integer i: list) {
			l+=i+",";
		}
		return "name:"+name+"\nage:"+age+"\nsex:"+sex+"\nlist:"+l;		
	}
}

/**
 * 使用Externalizable进行序列化，当读取对象时，会调用被序列化类的无参构造器去创建一个新的对象，
 * 然后再将被保存对象的字段的值分别填充到新对象中。这就是为什么在此次序列化过程中Person类的无参构造器会被调用。
 * 由于这个原因，实现Externalizable接口的类必须要提供一个无参的构造器，且它的访问权限为public。
 * @author Administrator
 *
 */
class Student2 implements Externalizable{
	
	 private static class InstanceHolder {
	        private static final Student2 instatnce = new Student2("John", 31,"MALE",new ArrayList<Integer>(){{add(1);add(2);}},new Teacher1("ZhangHen",50));
	    }

	    public static Student2 getInstance() {
	        return InstanceHolder.instatnce;
	    }
	
	    
	    public static Student2 getInstance2() {//并非单例模式，每次调用都会生成一个新的对象
	        return new Student2("John", 31,"MALE",new ArrayList<Integer>(){{add(1);add(2);}},new Teacher1("ZhangHen",50));
	    }
	    
	    /**
	     * 这样当JVM从内存中反序列化地"组装"一个新对象时,
	     * 就会自动调用这个 readResolve方法来返回我们指定好的对象了, 
	     * 单例规则也就得到了保证.
	     * @return
	     * @throws ObjectStreamException
	     */
	    private Object readResolve() throws ObjectStreamException {
	        return InstanceHolder.instatnce;
	    }
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Integer> list;
	Teacher1 t;
	String name;
	transient int age ;
	String sex;
	
	public Student2(){
		System.out.println("Student无参数构造器");
	}
	
	public Student2(String name,int age,String sex,List<Integer> list,Teacher1 t) {
		System.out.println("Student有参数构造器");
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.list = list;
		this.t = t;
	}	
	
	/*
	 * 在Person类中添加两个方法：writeObject()与readObject()
	 * private方法，那么它们是如何被调用的呢？毫无疑问，是使用反射
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();//执行默认的序列化机制
//        out.writeInt(age);//调用writeInt()方法显示地将age字段写入
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();//执行默认的序列化机制
//        age = in.readInt();
    }
	
	public String toString(){
		String l = "";
		if(list!=null){
			for (Integer i: list) {
				l+=i+",";
			}
		}
		
		return "name:"+name+" age:"+age+" sex:"+sex+" list:"+l+" teacher:"+t.getName()+"age:"+t.getAge();		
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeObject(name);
		out.writeObject(sex);
		out.writeInt(age);
//		out.writeObject(list);
		out.writeObject(t);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		name = (String) in.readObject();
		sex = (String) in.readObject();
		age = in.readInt();
//		list = (List<Integer>) in.readObject();
		t = (Teacher1)in.readObject();
	}
}

class LoggingInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Date loggingDate = new Date();
	private String uid;
	private transient String pwd;//瞬态，不会储存

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
		utils.cleanFile("E:/", ".ser");
		String serPath = utils.getProperty("serPath2");		 
		
		LoggingInfo logInfo = new LoggingInfo("MIKE", "MECHANICS");
		System.out.println(logInfo.toString());
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(serPath));
			o.writeObject(logInfo);
			o.close();
		} catch (Exception e) {// deal with exception
		}
		// To read the object back, we can write
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(serPath));
			LoggingInfo logInfo1 = (LoggingInfo) in.readObject();
			System.out.println(logInfo1.toString());
		} catch (Exception e) {// deal with exception
		}
	}
}