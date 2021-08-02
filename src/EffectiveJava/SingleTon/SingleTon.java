package EffectiveJava.SingleTon;

/**
 * 单例模式
 * 特征：
 *      私有的默认构造函数
 *      静态的本类对象成员变量
 *
 * 本例为第一种单例：私有静态成员变量，共有的静态工厂方法获取该变量
 */
public class SingleTon {
    private static final SingleTon singleTonTest = new SingleTon();
    private int value;
    private String key;

    private SingleTon(){
    }
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static SingleTon getInstance(){
        return singleTonTest;
    }

    @Override
    public String toString() {
        return "SingleTon{" +
                "value=" + value +
                ", key='" + key + '\'' +
                '}';
    }
}
