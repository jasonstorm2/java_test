package CASTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 *
 * 实现一个 CAS compare and set 操作，。乐观锁。先进行操作，再判断操作是否成功
 * 非阻塞同步 。CAS是非阻塞同步中的一种。
 * AtomicInteger 等类是CAS 的包装类
 *
 * 需要定义一个volatile标量，一个AtomicIntegerFieldUpdater 原子域更新对象。使用CAS自旋操作，当更新不成功，对旧值进行重新赋值
 *
 * CAS可以 避免同步锁带来的并发访问性能降低问题
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
            System.out.println("重新修改值："+Thread.currentThread().getName());
            oldvalue = value;
            newvalue = value + addvalue;
        }
        System.out.println("value的值："+value);
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
