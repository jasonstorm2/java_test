package EffectiveJava.SingleTon;

/**
 * ����ģʽ
 * ������
 *      ˽�е�Ĭ�Ϲ��캯��
 *      ��̬�ı�������Ա����
 *
 * ����Ϊ��һ�ֵ�����˽�о�̬��Ա���������еľ�̬����������ȡ�ñ���
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
