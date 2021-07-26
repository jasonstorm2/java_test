package CASTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *
 * ʵ��һ�� CAS compare and set ���������ֹ������Ƚ��в��������жϲ����Ƿ�ɹ�
 * ������ͬ�� ��CAS�Ƿ�����ͬ���е�һ�֡�
 * AtomicInteger ������CAS �İ�װ��
 *
 * ��Ҫ����һ��volatile������һ��AtomicIntegerFieldUpdater ԭ������¶���ʹ��CAS���������������²��ɹ����Ծ�ֵ�������¸�ֵ
 *
 * CAS���� ����ͬ���������Ĳ����������ܽ�������
 *
 */
public class CAS {
    private volatile int value = 5;
    private AtomicIntegerFieldUpdater<CAS> atomicInteger = AtomicIntegerFieldUpdater.newUpdater(CAS.class,"value");
    private AtomicInteger atomicint = new AtomicInteger();

    private void updateValue(CAS cas,int addvalue){
        int oldvalue = value;
        int newvalue = value + addvalue;
        while (!atomicInteger.compareAndSet(cas,oldvalue,newvalue)){
            System.out.println("�����޸�ֵ��"+Thread.currentThread().getName());
            oldvalue = value;
            newvalue = value + addvalue;
        }
        System.out.println("value��ֵ��"+value);
    }

    public static void main(String[] args) {
        CAS cas = new CAS();
        for (int i = 0; i <10; i++) {
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    cas.updateValue(cas,1);
                }
            }.start();
        }
        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();        new Thread(){
            @Override
            public void run() {
                super.run();
                cas.updateValue(cas,1);
            }
        }.start();


    }

}
