package EffectiveJava.SingleTon;

/**
 * 单例模式
 * 特征：
 *      私有的默认构造函数
 *      静态的本类对象成员变量
 *  第二种单例：公有的静态final成员变量
 */
public class SingleTon2 {
    public static final SingleTon2 singleTonTest = new SingleTon2();
    private int value;
    private String key;

    private SingleTon2(){
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


    @Override
    public String toString() {
        return "SingleTon{" +
                "value=" + value +
                ", key='" + key + '\'' +
                '}';
    }
}
