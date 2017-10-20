package my;

/**
 * 静态方法的锁是指 jvm加载该类产生的Class的对应的锁，即synchronizeTest.class的锁
 * @author Administrator
 *
 */
public class synchronizeTest extends Thread {

    private int threadNo;
    //private String lock;

    public synchronizeTest(int threadNo) {
        this.threadNo = threadNo;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i < 20; i++) {
            new synchronizeTest(i).start();
            Thread.sleep(1);
        }
    }
    // 该锁只有一个，所以多线程下会按顺序打印
    public static synchronized void abc(int threadNo) {
        for (int i = 1; i < 10; i++) {
            System.out.println("No." + threadNo + ":" + i);
        }
    }

    public void run() {
        abc(threadNo);
    }
}