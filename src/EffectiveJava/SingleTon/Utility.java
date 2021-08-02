package EffectiveJava.SingleTon;


import java.io.InputStream;
import java.util.Arrays;
import java.util.Timer;

/**
 * 私有的构造函数让类不可以被实例化
 * 使用情况：单例模式，工具类等
 */
public class Utility {
    /**
     * Suppresses default constructor, ensuring non-instantiability.
     * 这个类不可以被实例化，只做工具类
     */
    private Utility(){

    }

    //获取系统当前时间
    public static long getNowTime(){
        return System.currentTimeMillis();
    }
}
