package CloneTest;

import java.io.IOException;

/**
 * 深度拷贝
 * 被拷贝类里面的成员变量也需要实现序列话接口
 */
public class MainTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person(1, 2, "fff",new Girl(234));
        Person personClone = DeepCloneProxy.deepClone(person,null);
        System.out.println(personClone.getName());

//        Girl girl = new Girl(44);
//        Girl girlClone = CloneProxy.deepClone(girl);
        System.out.println(person ==  personClone);
        System.out.println(personClone.getGirl().getLegNum());

//        System.out.println("两个对象是否引用相同：" + person.getName() == personClone.getName());
    }
}
