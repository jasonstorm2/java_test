package EffectiveJava.SingleTon;


import java.io.InputStream;
import java.util.Arrays;
import java.util.Timer;

/**
 * ˽�еĹ��캯�����಻���Ա�ʵ����
 * ʹ�����������ģʽ���������
 */
public class Utility {
    /**
     * Suppresses default constructor, ensuring non-instantiability.
     * ����಻���Ա�ʵ������ֻ��������
     */
    private Utility(){

    }

    //��ȡϵͳ��ǰʱ��
    public static long getNowTime(){
        return System.currentTimeMillis();
    }
}
