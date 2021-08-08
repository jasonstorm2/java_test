package my;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * 1.Ϊ�˻�ȡ�����һ�ݿ��������ǿ�������Object���clone()������
 * <p>
 * ����2.���������и��ǻ����clone()������������Ϊpublic��
 * <p>
 * ������Object���е�clone()������protected�ģ���
 * <p>
 * ������������д��ʱ�򣬿�������������η��ķ�Χ��
 * <p>
 * ����3.���������clone()�����У�����super.clone()��
 * <p>
 * ������Ϊ������ʱ�̣�Object���е�clone()ʶ�����Ҫ���Ƶ�����һ������Ȼ��Ϊ�˶������ռ䣬�����ж���ĸ��ƣ�
 * ��ԭʼ���������һһ���Ƶ��¶���Ĵ洢�ռ��С�
 * <p>
 * ����4.����������ʵ��Cloneable�ӿڡ�
 * <p>
 * ��������ӿ���û��ʲô������ֻ��˵�����á�
 * <p>
 * ����ע�⣺�̳���java.lang.Object���clone()������ǳ���ơ�
 * <p>
 * java��clone()����::
 * <p>
 * ����clone()����������Object���С�
 * <p>
 * ����clone()��������������һ�ݲ����ظ������ߡ���������ĺ��彫ȡ���ڶ������ڵ��ࡣ
 * <p>
 * ����һ����ԣ�clone()�������㣺
 * <p>
 * ����1. ��¡������ԭ������ͬһ�����󡣼����κεĶ���x��
 * <p>
 * ����x.clone() != x
 * <p>
 * ����2.��¡������ԭ���������һ���������κεĶ���x��
 * <p>
 * ����x.clone().getClass() == x.getClass()
 * <p>
 * ����3.�������x��equals()��������ǡ������ô��ʽӦ�ó�����
 * <p>
 * ����x.clone().equals(x)
 * <p>
 * ������Ϊһ���������õ�equals()������Ӧ���������Ƚ������Ƿ���ȵġ�
 * <p>
 * �������л�ʵ�����
 * �������������еķ���ʵ����ƱȽ��鷳��
 * <p>
 * �����������һ��ȫ�µķ������������л�������ơ�
 * <p>
 * �����Ѷ���д������Ĺ��������л����̣�Serialization�������Ѷ�������ж������Ĺ�������������л����̣�Deserialization����
 * <p>
 * ����Ӧ��ָ�����ǣ�д��������Ƕ����һ����������ԭ������Ȼ������JVM���档
 * <p>
 * ������Java���������һ�����󣬳���������ʹ����ʵ��Serializable�ӿڣ�Ȼ��Ѷ���ʵ����ֻ�Ƕ����һ��������д��һ������ٴ������������������ؽ�����
 * <p>
 * ������������ǰ���Ƕ����Լ������ڲ��������õ��Ķ����ǿɴ��л��ģ����򣬾���Ҫ��ϸ������Щ���ɴ��л��Ķ���ɷ����transient���Ӷ������ų��ڸ��ƹ���֮�⡣
 * <p>
 * ����ע��Cloneable��Serializable�ӿڶ���marker Interface��Ҳ����˵����ֻ�Ǳ�ʶ�ӿڣ�û�ж����κη���
 *
 * @author LiZhenhua
 */
final class CloneTest {
    public static void main(String[] args) throws Exception {
		CloneTest.clone1();//��ͨ����--��������
//		CloneTest.clone2();//ǳ����--ֻ����ͨ���������ö��󲻿���
//		CloneTest.clone3();//���--����clone������¡���õĶ���--�Ƚ��鷳
//        CloneTest.clone4();//���--�������л�����--�ȽϷ���
    }

    /**
     * ˵����������student1��student2ָ����ǲ�ͬ�Ķ���
     *
     * @throws CloneNotSupportedException
     */
    final static void clone1() throws CloneNotSupportedException {
        StudentImpCloneable1 studentSubCloneable1 = new StudentImpCloneable1();
        studentSubCloneable1.setName("ZhangSan");
        studentSubCloneable1.setAge(20);

        System.out.println("��ǰ����"+studentSubCloneable1);

        StudentImpCloneable1 student2 = new StudentImpCloneable1();
        student2 = (StudentImpCloneable1) studentSubCloneable1.clone();
        System.out.println("��¡����"+student2.toString());


        // �޸ĵڶ����������Ϣ
        student2.setName("LiSi");
        student2.setAge(25);
        System.out.println("��¡�����޸ĺ�"+student2.toString());
        System.out.println("���������Ƿ���ͬ��"+(studentSubCloneable1 == student2));

        // ˵����������student1��student2ָ����ǲ�ͬ�Ķ���
    }

    /**
     * ǳ���������������еĶ������ò�û�иı䡣����Ϳ������� �еĶ������ã���ͬһ������
     *
     * @throws CloneNotSupportedException
     */
    final static void clone2() throws CloneNotSupportedException {
        TeacherImpClonable teacher = new TeacherImpClonable();
        teacher.setName("Teacher Zhang");
        teacher.setAge(40);

        StudentImpCloneable3 studentSubCloneable3 = new StudentImpCloneable3();
        studentSubCloneable3.setName("ZhangSan");
        studentSubCloneable3.setAge(20);
        studentSubCloneable3.setTeacher(teacher);
        System.out.println(" student3:" + studentSubCloneable3.toString());

        StudentImpCloneable3 student4 = (StudentImpCloneable3) studentSubCloneable3.clone();
        System.out.println(" student4:" + student4.toString());
        System.out.println("-------------change teacher name-------------");

        // �޸���ʦ����Ϣ
        teacher.setName("Teacher Huang");
        System.out.println(" student3:" + studentSubCloneable3.toString());
        System.out.println(" student4:" + student4.toString());

        System.out.println(studentSubCloneable3 == student4);
        System.out.println(studentSubCloneable3.getTeacher() == student4.getTeacher());

        // ��������student1��student2ָ��ͬ����������
        // ������������student1��student2�е�����teacher����ָ�����ͬһ������
        // ����˵����ǳ����--Object���clone()�������е���ǳ������
    }

    /**
     * �������дclone����������Щ��������ÿ���һ�飬Ȼ��ֵ
     *
     * @throws CloneNotSupportedException
     */
    final static void clone3() throws CloneNotSupportedException {
        TeacherImpClonableExternalizable teacher = new TeacherImpClonableExternalizable();
        teacher.setName("Teacher Zhang");
        teacher.setAge(40);

        StudentImpCloneable4 student4 = new StudentImpCloneable4();
        student4.setName("ZhangSan");
        student4.setAge(20);
        student4.setTeacher(teacher);


        StudentImpCloneable4 student2 = (StudentImpCloneable4) student4.clone();
        System.out.println(student4.toString());
        System.out.println(student2.toString());
        System.out.println("�����õ�����Ϣ");
        System.out.println("-------------");

        // �޸���ʦ����Ϣ
        teacher.setName("Teacher Huang");
        System.out.println(student4.toString());
        System.out.println(student2.toString());

        // ��������student1��student2ָ��ͬ����������
        // ������������student1��student2�е�����teacher����ָ�����ͬһ������
        // ����˵����ǳ����

        // ��Ϊ���֮�󣬶�teacher������޸�ֻ��Ӱ���һ������
    }

    /**
     * �������л� ���������
     * ���󣬺Ͷ����е���������ʵ����Serializable�ӿ�
     *
     * @throws Exception
     */
    final static void clone4() throws Exception {
        TeacherImpSerializable t = new TeacherImpSerializable();
        t.setName("Teacher Wang");
        t.setAge(50);

        StudentSImpSerializable s1 = new StudentSImpSerializable();
        s1.setAge(20);
        s1.setName("ZhangSan");
        s1.setTeacher(t);

        StudentSImpSerializable s2 = (StudentSImpSerializable) s1.deepClone();
        System.out.println(s1.toString());
        System.out.println(s2.toString());


        System.out.println("---------------------------");

        // �����ƺ�Ķ������ʦ��Ϣ�޸�һ�£�
        System.out.println("�޸Ŀ���������������");

        s2.getTeacher().setName("Teacher Huang");
        s2.getTeacher().setAge(28);
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        // �ɴ�֤�����л��ķ�ʽʵ���˶�������

    }

}

class StudentImpCloneable1 implements Cloneable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentImpCloneable1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // ע��˴�Ҫ��protected��Ϊpublic,���������ڷǱ������clone����

        Object object = super.clone();//Object ��clone()������protected��

        return object;
    }

//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
}

class StudentImpCloneable3 implements Cloneable {
    private String name;
    private int age;
    private TeacherImpClonable teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TeacherImpClonable getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherImpClonable teacher) {
        this.teacher = teacher;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // ע��˴�Ҫ��protected��Ϊpublic

        Object object = super.clone();//Object ��clone()������protected��

        return object;
    }

    @Override
    public String toString() {
        return "StudentImpCloneable3{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher.getName() + " " + teacher.getAge() +
                '}';
    }
}

class TeacherImpClonable implements Cloneable {
    private String name;
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TeacherImpClonable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class TeacherImpClonableExternalizable implements Cloneable, Externalizable {
    private String name;
    private int age;

    public TeacherImpClonableExternalizable(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public TeacherImpClonableExternalizable() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // TODO Auto-generated method stub
        out.writeObject(name);
        out.writeObject(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        name = (String) in.readObject();
        age = (int) in.readObject();

    }

}

class TeacherImpSerializable implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class StudentImpCloneable4 implements Cloneable {
    private String name;
    private int age;
    private TeacherImpClonableExternalizable teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TeacherImpClonableExternalizable getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherImpClonableExternalizable teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "StudentImpCloneable4{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher.toString() +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // ǳ����ʱ��
        // Object object = super.clone();
        // return object;

        // ��Ϊ��ƣ�
        StudentImpCloneable4 student = (StudentImpCloneable4) super.clone();
        // ������ǳ���ƣ����ڽ�Teacher������һ�ݲ�����set����
        student.setTeacher((TeacherImpClonableExternalizable) student.getTeacher().clone());
        return student;
    }
}


/**
 * ������дclone������дһ���µķ���deepClone()�Ϳ���
 *
 * @author LiZhenhua
 * <p>
 * serialVersionUID����
 * ������һ����ʵ����Serializable�ӿ�ʱ����������ɱ����л������ʱ��Eclipse�����һ�����棬Ҫ����Ϊ���ඨ��һ���ֶΣ����ֶ�����ΪserialVersionUID������Ϊlong����ʾ��Ϣ���£�
 * <p>
 * ����The serializable class Teacher3 does not declare a static final serialVersionUID field of type long��
 * <p>
 * ������Eclipse�����������ɷ�ʽ��
 * <p>
 * ����һ����Ĭ�ϵ�1L��
 * <p>
 * ����private static final long serialVersionUID = 1L;
 * <p>
 * ����һ���Ǹ����������ӿ�������Ա���������Ե�������һ��64λ�Ĺ�ϣ�ֶΣ����磺
 * <p>
 * ����private static final long serialVersionUID = -932183802511122207L;
 * <p>
 * ���������û�п��ǵ������Ե����⣬�Ͱ����ص�����������������Ǻõģ�ֻҪ�κ����ʵ����Serializable�ӿڣ����û�м���serialVersionUID��Eclipse���������ʾ�����serialVersionUIDΪ���ø����Serializable�����ݡ�
 * <p>
 * ���������Ķ������л���浽Ӳ���������ȴ���������field�����ӻ���ٻ�����������㷴���л�ʱ���ͻ�����쳣�������ͻ���ɲ������Ե����⡣
 * <p>
 * ��������serialVersionUID��ͬʱ�����ͻὫ��һ����field��type��ȱʡֵDeserialize��������Աܿ��������Ե�����
 */
class StudentSImpSerializable implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6257547865699167078L;
    private String name;
    private int age;
    private TeacherImpSerializable teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public TeacherImpSerializable getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherImpSerializable teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "StudentSImpSerializable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher.toString() +
                '}';
    }

    /**
     * ������������� �� �ֽ�������������� �Ľ������
     * <p>
     * ����д���ڴ棬���൱�����һ����
     *
     * @return
     * @throws Exception
     */
    public Object deepClone() throws Exception {

        /**
         * ע����FileOutputStream(String path) �� FileInputStream(String path)������
         * FileOutputStream��Ҫһ����ַ�� ��źͶ�ȡ
         * ��ByteArrayOutputStream ��ֱ��д���ڴ棿ϵͳ�Զ�������
         */

        // ���л�
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);//��װ��ObjectOutputStream,�������Object����

        oos.writeObject(this);//д�������󣨶�����ֽ���Ŷ�������ڴ�

        // �����л�
        //ע������Ҫ�ṩһ��byte������Ϊ��������
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());//���ڴ��ж�ȡ�����ֽ����飬���ֽ������ȡ
        ObjectInputStream ois = new ObjectInputStream(bis);//���ֽ���ת���ɣ���װ�ɣ�����

        return ois.readObject();
    }

}

class tttt implements Serializable{

    private static final long serialVersionUID = 3326839024329530447L;
}
