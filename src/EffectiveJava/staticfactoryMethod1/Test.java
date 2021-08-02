package EffectiveJava.staticfactoryMethod1;

import java.util.Collections;

/**
 * static factory method
 * Benifit:
 * 1 相比于构造函数，你可以命名静态方法的名字。很容易看出这个类生成的对象包含什么参数
 * 2 当相比于构造函数，不用每次都创建对象。不需要重复创建类的对象时，通过静态工厂方法，你可以只生成一个实例，每次获取相同的实例 -- 单例模式
 * 3 可以返回子类的对象实例（声明还是父类）
 */
public class Test {
    private int age;
    private String name;

    public Test(){

    }
//    private static Test instance = new Test();
//    //1单例模式
//    public static Test getInstance(){
//        return instance;
//    }

//    //2静态工厂方法，根据名字可以看出对象的参数
//    public static Test nameTest(String name){
//        Test test = new Test();
//        test.name = name;
//        return test;
//    }
//    //2静态工厂方法，根据名字可以看出对象的参数
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