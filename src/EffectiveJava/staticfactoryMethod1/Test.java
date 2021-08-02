package EffectiveJava.staticfactoryMethod1;

import java.util.Collections;

/**
 * static factory method
 * Benifit:
 * 1 ����ڹ��캯���������������̬���������֡������׿�����������ɵĶ������ʲô����
 * 2 ������ڹ��캯��������ÿ�ζ��������󡣲���Ҫ�ظ�������Ķ���ʱ��ͨ����̬���������������ֻ����һ��ʵ����ÿ�λ�ȡ��ͬ��ʵ�� -- ����ģʽ
 * 3 ���Է�������Ķ���ʵ�����������Ǹ��ࣩ
 */
public class Test {
    private int age;
    private String name;

    public Test(){

    }
//    private static Test instance = new Test();
//    //1����ģʽ
//    public static Test getInstance(){
//        return instance;
//    }

//    //2��̬�����������������ֿ��Կ�������Ĳ���
//    public static Test nameTest(String name){
//        Test test = new Test();
//        test.name = name;
//        return test;
//    }
//    //2��̬�����������������ֿ��Կ�������Ĳ���
//    public static Test ageTest(int age){
//        Test test = new Test();
//        test.age = age;
//        return test;
//    }

    public static Test getSubTest(){
        return new SubTest();
    }

    public static Test getSubTest2(){
        return new SubTest2();
    }

    public static void main(String[] args) {
        Test test = Test.getSubTest2();
        System.out.println(test.age);
    }


}