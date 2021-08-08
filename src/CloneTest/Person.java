package CloneTest;

import java.io.Serializable;

public class Person implements Serializable {
    private int age = 0;
    private int high =0;
    private String name;
    private Girl girl;
    public Person(int age,int high,String name,Girl girl){
        this.age = age;
        this.high = high;
        this.name = name;
        this.girl = girl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }
}
