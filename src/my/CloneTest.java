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
 * 1.为了获取对象的一份拷贝，我们可以利用Object类的clone()方法。
 * <p>
 * 　　2.在派生类中覆盖基类的clone()方法，并声明为public。
 * <p>
 * 　　（Object类中的clone()方法是protected的）。
 * <p>
 * 　　在子类重写的时候，可以扩大访问修饰符的范围。
 * <p>
 * 　　3.在派生类的clone()方法中，调用super.clone()。
 * <p>
 * 　　因为在运行时刻，Object类中的clone()识别出你要复制的是哪一个对象，然后为此对象分配空间，并进行对象的复制，
 * 将原始对象的内容一一复制到新对象的存储空间中。
 * <p>
 * 　　4.在派生类中实现Cloneable接口。
 * <p>
 * 　　这个接口中没有什么方法，只是说明作用。
 * <p>
 * 　　注意：继承自java.lang.Object类的clone()方法是浅复制。
 * <p>
 * java的clone()方法::
 * <p>
 * 　　clone()方法定义在Object类中。
 * <p>
 * 　　clone()方法将对象复制了一份并返回给调用者。拷贝具体的含义将取决于对象所在的类。
 * <p>
 * 　　一般而言，clone()方法满足：
 * <p>
 * 　　1. 克隆对象与原对象不是同一个对象。即对任何的对象x：
 * <p>
 * 　　x.clone() != x
 * <p>
 * 　　2.克隆对象与原对象的类型一样。即对任何的对象x：
 * <p>
 * 　　x.clone().getClass() == x.getClass()
 * <p>
 * 　　3.如果对象x的equals()方法定义恰当，那么下式应该成立：
 * <p>
 * 　　x.clone().equals(x)
 * <p>
 * 　　因为一个定义良好的equals()方法就应该是用来比较内容是否相等的。
 * <p>
 * 利用序列化实现深复制
 * 　　上面例子中的方法实现深复制比较麻烦。
 * <p>
 * 　　下面介绍一种全新的方法：利用序列化来做深复制。
 * <p>
 * 　　把对象写到流里的过程是序列化过程（Serialization），而把对象从流中读出来的过程则叫做反序列化过程（Deserialization）。
 * <p>
 * 　　应当指出的是，写在流里的是对象的一个拷贝，而原对象仍然存在于JVM里面。
 * <p>
 * 　　在Java语言里深复制一个对象，常常可以先使对象实现Serializable接口，然后把对象（实际上只是对象的一个拷贝）写到一个流里，再从流里读出来，便可以重建对象。
 * <p>
 * 　　这样做的前提是对象以及对象内部所有引用到的对象都是可串行化的，否则，就需要仔细考察那些不可串行化的对象可否设成transient，从而将其排除在复制过程之外。
 * <p>
 * 　　注意Cloneable与Serializable接口都是marker Interface，也就是说它们只是标识接口，没有定义任何方法
 *
 * @author LiZhenhua
 */
final class CloneTest {
    public static void main(String[] args) throws Exception {
		CloneTest.clone1();//普通拷贝--拷贝变量
//		CloneTest.clone2();//浅拷贝--只是普通拷贝，引用对象不拷贝
//		CloneTest.clone3();//深拷贝--在类clone方法克隆引用的对象--比较麻烦
//        CloneTest.clone4();//深拷贝--利用序列化拷贝--比较方便
    }

    /**
     * 说明两个引用student1和student2指向的是不同的对象
     *
     * @throws CloneNotSupportedException
     */
    final static void clone1() throws CloneNotSupportedException {
        StudentImpCloneable1 studentSubCloneable1 = new StudentImpCloneable1();
        studentSubCloneable1.setName("ZhangSan");
        studentSubCloneable1.setAge(20);

        System.out.println("当前对象："+studentSubCloneable1);

        StudentImpCloneable1 student2 = new StudentImpCloneable1();
        student2 = (StudentImpCloneable1) studentSubCloneable1.clone();
        System.out.println("克隆对象："+student2.toString());


        // 修改第二个对象的信息
        student2.setName("LiSi");
        student2.setAge(25);
        System.out.println("克隆对象修改后："+student2.toString());
        System.out.println("对象引用是否相同："+(studentSubCloneable1 == student2));

        // 说明两个引用student1和student2指向的是不同的对象
    }

    /**
     * 浅拷贝，拷贝对象中的对象引用并没有改变。对象和拷贝对象 中的对象引用，是同一个引用
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

        // 修改老师的信息
        teacher.setName("Teacher Huang");
        System.out.println(" student3:" + studentSubCloneable3.toString());
        System.out.println(" student4:" + student4.toString());

        System.out.println(studentSubCloneable3 == student4);
        System.out.println(studentSubCloneable3.getTeacher() == student4.getTeacher());

        // 两个引用student1和student2指向不同的两个对象
        // 但是两个引用student1和student2中的两个teacher引用指向的是同一个对象
        // 所以说明是浅拷贝--Object类的clone()方法进行的是浅拷贝。
    }

    /**
     * 深拷贝：重写clone方法，将那些对象的引用拷贝一遍，然后赋值
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
        System.out.println("拷贝得到的信息");
        System.out.println("-------------");

        // 修改老师的信息
        teacher.setName("Teacher Huang");
        System.out.println(student4.toString());
        System.out.println(student2.toString());

        // 两个引用student1和student2指向不同的两个对象
        // 但是两个引用student1和student2中的两个teacher引用指向的是同一个对象
        // 所以说明是浅拷贝

        // 改为深复制之后，对teacher对象的修改只能影响第一个对象
    }

    /**
     * 利用序列化 来深拷贝对象
     * 对象，和对象中的其他对象都实现了Serializable接口
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

        // 将复制后的对象的老师信息修改一下：
        System.out.println("修改拷贝。。。。。。");

        s2.getTeacher().setName("Teacher Huang");
        s2.getTeacher().setAge(28);
        System.out.println(s1.toString());
        System.out.println(s2.toString());
        // 由此证明序列化的方式实现了对象的深拷贝

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
        // 注意此处要把protected改为public,这样才能在非本类调用clone方法

        Object object = super.clone();//Object 的clone()方法是protected的

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
        // 注意此处要把protected改为public

        Object object = super.clone();//Object 的clone()方法是protected的

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
        // 浅复制时：
        // Object object = super.clone();
        // return object;

        // 改为深复制：
        StudentImpCloneable4 student = (StudentImpCloneable4) super.clone();
        // 本来是浅复制，现在将Teacher对象复制一份并重新set进来
        student.setTeacher((TeacherImpClonableExternalizable) student.getTeacher().clone());
        return student;
    }
}


/**
 * 不用重写clone方法，写一个新的方法deepClone()就可以
 *
 * @author LiZhenhua
 * <p>
 * serialVersionUID问题
 * 　　当一个类实现了Serializable接口时，表明该类可被序列化，这个时候Eclipse会给出一个警告，要求你为该类定义一个字段，该字段名字为serialVersionUID，类型为long，提示信息如下：
 * <p>
 * 　　The serializable class Teacher3 does not declare a static final serialVersionUID field of type long。
 * <p>
 * 　　在Eclipse中有两种生成方式：
 * <p>
 * 　　一个是默认的1L；
 * <p>
 * 　　private static final long serialVersionUID = 1L;
 * <p>
 * 　　一个是根据类名、接口名、成员方法及属性等来生成一个64位的哈希字段，比如：
 * <p>
 * 　　private static final long serialVersionUID = -932183802511122207L;
 * <p>
 * 　　如果你没有考虑到兼容性的问题，就把它关掉，不过有这个功能是好的，只要任何类别实现了Serializable接口，如果没有加入serialVersionUID，Eclipse都会给你提示，这个serialVersionUID为了让该类别Serializable向后兼容。
 * <p>
 * 　　如果你的对象序列化后存到硬盘上面后，你却更改了类的field（增加或减少或改名），当你反序列化时，就会出现异常，这样就会造成不兼容性的问题。
 * <p>
 * 　　但当serialVersionUID相同时，它就会将不一样的field以type的缺省值Deserialize，这个可以避开不兼容性的问题
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
     * 对象输出输入流 与 字节数组输出输入流 的结合运用
     * <p>
     * 对象写入内存，就相当于深拷贝一份了
     *
     * @return
     * @throws Exception
     */
    public Object deepClone() throws Exception {

        /**
         * 注意与FileOutputStream(String path) 和 FileInputStream(String path)的区别
         * FileOutputStream需要一个地址来 存放和读取
         * 而ByteArrayOutputStream 是直接写入内存？系统自动分配吗？
         */

        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);//包装成ObjectOutputStream,输入输出Object对象

        oos.writeObject(this);//写整个对象（对象的字节流哦）进入内存

        // 反序列化
        //注意它需要提供一个byte数组作为缓冲区。
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());//从内存中读取放入字节数组，从字节数组读取
        ObjectInputStream ois = new ObjectInputStream(bis);//把字节流转换成（包装成）对象

        return ois.readObject();
    }

}

class tttt implements Serializable{

    private static final long serialVersionUID = 3326839024329530447L;
}
