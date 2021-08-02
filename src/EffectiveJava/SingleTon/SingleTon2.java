package EffectiveJava.SingleTon;

/**
 * ����ģʽ
 * ������
 *      ˽�е�Ĭ�Ϲ��캯��
 *      ��̬�ı�������Ա����
 *  �ڶ��ֵ��������еľ�̬final��Ա����
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
