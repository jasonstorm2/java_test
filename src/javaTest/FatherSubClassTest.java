package javaTest;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * 方法的调用顺序
 *
 * 当子类实例化时，父类的构造函数先调用
 * 父类的构造函数调用方法，由于方法被子类重写，所欲父类的构造方法调用的是子类的方法
 */
public class FatherSubClassTest {
    public static void main(String[] args) {
        SubFather subFather = new SubFather();
//        subFather.print();
    }
}

/**
 * 类中的类，不是内部类
 */
class Father{
    Father(){
        say();
        print();
    }
    /**
     * 被子类重写，父类构造调用子类的重写方法
     */
    void print(){
        System.out.println("world");
        ArrayList list = new ArrayList<>();
    }

    /**
     * 没有被子类重写，所以子类实例化时，父类构造器调用该方法
     */
    void say(){
        System.out.println("father say");
    }
}

class SubFather extends Father{
    SubFather(){
    }
    @Override
    void print() {
        System.out.println("hello");
    }
}
