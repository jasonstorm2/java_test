package javaTest;

import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * �����ĵ���˳��
 *
 * ������ʵ����ʱ������Ĺ��캯���ȵ���
 * ����Ĺ��캯�����÷��������ڷ�����������д����������Ĺ��췽�����õ�������ķ���
 */
public class FatherSubClassTest {
    public static void main(String[] args) {
        SubFather subFather = new SubFather();
//        subFather.print();
    }
}

/**
 * ���е��࣬�����ڲ���
 */
class Father{
    Father(){
        say();
        print();
    }
    /**
     * ��������д�����๹������������д����
     */
    void print(){
        System.out.println("world");
        ArrayList list = new ArrayList<>();
    }

    /**
     * û�б�������д����������ʵ����ʱ�����๹�������ø÷���
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
