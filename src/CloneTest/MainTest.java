package CloneTest;

import java.io.IOException;

/**
 * ��ȿ���
 * ������������ĳ�Ա����Ҳ��Ҫʵ�����л��ӿ�
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

//        System.out.println("���������Ƿ�������ͬ��" + person.getName() == personClone.getName());
    }
}
