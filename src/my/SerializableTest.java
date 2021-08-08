package my;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.utils;

/**
 * 
 * @author LiZhenhua
 *1.���л��ܱ����Ԫ�� ---static �������ܱ���
 *  ���л�ֻ�ܱ������ķǾ�̬��Ա���������ܱ����κεĳ�Ա�����;�̬�ĳ�Ա������
 *  ���Ҵ��л������ֻ�Ǳ�����ֵ�����ڱ������κ����η������ܱ��档
 *  
 *  2.transient�ؼ��� 

     ����ĳЩ���͵Ķ�����״̬��˲ʱ�ģ������Ķ������޷�������״̬�ġ�����һ��Thread�����һ��FileInputStream���� ��
     ������Щ�ֶΣ����Ǳ�����transient�ؼ��ֱ�������������������롣 
            
     ���� �����л������漰�������ŵ� �����ϻ��������Ϸ������ݣ���ʱ��ͻ������ȫ���⡣
     ��Ϊ����λ��Java���л���֮�⣬����Java��ȫ���ƵĿ���֮�С�
     ������Щ��Ҫ���ܵ��ֶΣ���Ӧ���������ý����� �����߲�Ӧ�򵥵ز��Ӵ���ر������� ��Ϊ�˱�֤��ȫ�ԡ�
     Ӧ������Щ�ֶ�ǰ����transient�ؼ��֡�
    3.Ĭ�����л����ƣ�
     �������ֻ����ĳ����ʵ��Serializable�ӿڣ���û�������κδ���Ļ��������ʹ��Ĭ�����л����ơ�
     ʹ��Ĭ�ϻ��ƣ������л�����ʱ�����������л���ǰ����������Ըö������õ���������Ҳ�������л���
     ͬ���أ���Щ�����������õ��������Ҳ�������л����Դ����ơ�
     ���ԣ����һ����������ĳ�Ա��������������󣬶���Щ���������е�Ԫ��Ҳ�����������
     ��ô������л��Ĺ��̾ͻ�ϸ��ӣ�����Ҳ�ϴ�
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
		 * "/"���ŵȼ���"\\"
		 * �������� FileOutputStream��·����·��ָʾ���ļ������ڣ���ô������������ļ�
		 * 
		 */
		/********д��ָ��λ���ļ�*********/
		try {
			FileOutputStream fo = new FileOutputStream(serPath);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			
			oo.writeObject(stu2);
			oo.close();
			fo.close();//Ϊʲô�����������
			System.out.println("д��ɹ�");
			System.out.println(stu2);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/********����ָ��λ���ļ�*********/
		

		try {
//			new Student(serPath, 0, serPath, list).name = "�����ǲ��ǴӴ���д����";
			System.out.println("����.....");
			FileInputStream fi = new FileInputStream(serPath);
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			Object s = oi.readObject();
			System.out.println(s);
			System.out.println("�����Ƿ���ȣ�"+(s==stu2));
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

/**
 * �����¶�ȡ�������Person����ʱ����û�е���Person���κι�������������������ֱ��ʹ���ֽڽ�Person����ԭ�����ġ�
   ��Person���󱻱��浽person.out�ļ���֮�����ǿ����������ط�ȥ��ȡ���ļ��Ի�ԭ����
   ������ȷ���ö�ȡ�����CLASSPATH�а�����Person.class(�����ڶ�ȡPerson����ʱ��û����ʾ��ʹ��Person�࣬��������ʾ)��
   ������׳�ClassNotFoundException��
 * @author LiZhenhua
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
		System.out.println("Student�޲���������");
	}
	
	public Student(String name,int age,String sex,List<Integer> list) {
		System.out.println("Student�в���������");
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.list = list;
	}	
	
	/*
	 * ��Student�����������������writeObject()��readObject()
	 * private��������ô��������α����õ��أ��������ʣ���ʹ�÷���
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();//ִ��Ĭ�ϵ����л�����
//        out.writeInt(age);//����writeInt()������ʾ�ؽ�age�ֶ�д��
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();//ִ��Ĭ�ϵ����л�����
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
 * ʹ��Externalizable�������л�������ȡ����ʱ������ñ����л�����޲ι�����ȥ����һ���µĶ���
 * Ȼ���ٽ������������ֶε�ֵ�ֱ���䵽�¶����С������Ϊʲô�ڴ˴����л�������Person����޲ι������ᱻ���á�
 * �������ԭ��ʵ��Externalizable�ӿڵ������Ҫ�ṩһ���޲εĹ������������ķ���Ȩ��Ϊpublic��
 * @author LiZhenhua
 *
 */
class Student2 implements Externalizable{
	
	 private static class InstanceHolder {
	        private static final Student2 instatnce = new Student2("John", 31,"MALE",new ArrayList<Integer>(){{add(1);add(2);}},new TeacherImpClonableExternalizable("ZhangHen",50));
	    }

	    public static Student2 getInstance() {
	        return InstanceHolder.instatnce;
	    }
	
	    
	    public static Student2 getInstance2() {//���ǵ���ģʽ��ÿ�ε��ö�������һ���µĶ���
	        return new Student2("John", 31,"MALE",new ArrayList<Integer>(){{add(1);add(2);}},new TeacherImpClonableExternalizable("ZhangHen",50));
	    }
	    
	    /**
	     * ������JVM���ڴ��з����л���"��װ"һ���¶���ʱ,
	     * �ͻ��Զ�������� readResolve��������������ָ���õĶ�����, 
	     * ��������Ҳ�͵õ��˱�֤.
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
	TeacherImpClonableExternalizable t;
	String name;
	transient int age ;
	String sex;
	
	public Student2(){
		System.out.println("Student�޲���������");
	}
	
	public Student2(String name, int age, String sex, List<Integer> list, TeacherImpClonableExternalizable t) {
		System.out.println("Student�в���������");
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.list = list;
		this.t = t;
	}	
	
	/*
	 * ��Person�����������������writeObject()��readObject()
	 * private��������ô��������α����õ��أ��������ʣ���ʹ�÷���
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();//ִ��Ĭ�ϵ����л�����
//        out.writeInt(age);//����writeInt()������ʾ�ؽ�age�ֶ�д��
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();//ִ��Ĭ�ϵ����л�����
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
		t = (TeacherImpClonableExternalizable)in.readObject();
	}
}

class LoggingInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Date loggingDate = new Date();
	private String uid;
	private transient String pwd;//˲̬�����ᴢ��

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