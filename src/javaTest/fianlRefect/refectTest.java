package javaTest.fianlRefect;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * �������
 * �÷���ķ������޸Ķ����ڵ�ֵ
 */
public class refectTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        People people = People.class.newInstance();
        System.out.println("ԭ����ֵ��"+people.toString());
        for (Field declaredField : People.class.getDeclaredFields()) {
            //��������Է���
            declaredField.setAccessible(true);
            String fildName = declaredField.getName();
            //����ָ�����ֵ
            if(fildName.endsWith("age")){
                declaredField.set(people,34);
            }else if(fildName.endsWith("sex")){
                declaredField.set(people,1);
            }
        }
        System.out.println(people.toString());
        Date date1 = new Date();
        System.out.println(date1.hashCode());
        Date date2 = (Date) date1.clone();
        System.out.println(date2.hashCode());
        if(date1 == date2){
            System.out.println(true);
        }

    }
}

