package my;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class AtomicIntegerTest {

    static long randomTime() {
        return (long) (Math.random() * 1000);
    }

    public static void main(String[] args) {
        // 阻塞队列，能容纳100个文件
        final BlockingQueue<File> queue = new LinkedBlockingQueue<File>(100);
        // 线程池
        final ExecutorService exec = Executors.newFixedThreadPool(5);
        final File root = new File("D:\\SVN_XINGXIU\\2016220newserver\\server\\gof");
        // 完成标志
        final File exitFile = new File("");
        // 原子整型，读个数
        // AtomicInteger可以在并发情况下达到原子化更新，避免使用了synchronized，而且性能非常高。
        final AtomicInteger rc = new AtomicInteger();
        // 原子整型，写个数
        final AtomicInteger wc = new AtomicInteger();
        Logger log = Logger.getLogger("AtomicIntegerTest.class");
        
		exec.submit(() -> {
			try {
				System.out.println("***************");
				System.out.println(1 / 0);// 没有异常抛出。。普通线程 跟 线程池的区别之处。。。。需要trycatch包裹
			} catch (Exception e) {
				e.printStackTrace();
//				log.

				System.out.println("有异常抛出");
			}
		});
		
        
        
        // 读线程
        Runnable read = new Runnable() {
            public void run() {
                scanFile(root);
                scanFile(exitFile);
            }

            public void scanFile(File file) {
                if (file.isDirectory()) {
                    File[] files = file.listFiles(new FileFilter() {
                        public boolean accept(File pathname) {
                            return pathname.isDirectory() || pathname.getPath().endsWith(".bat");
                        }
                    });
                    for (File one : files)
                        scanFile(one);
                } else {
                    try {
                        // 原子整型的incrementAndGet方法，以原子方式将当前值加 1，返回更新的值
                        int index = rc.incrementAndGet();
                        System.out.println("Read0: " + index + " " + file.getPath());
                        // 添加到阻塞队列中--每有一个符合条件，计数+1，并写入阻塞队列
                        queue.put(file);
                    } catch (InterruptedException e) {

                    }
                }
            }
        };
        // submit方法提交一个 Runnable 任务用于执行，并返回一个表示该任务的 Future。
        exec.submit(read);

        // 四个写线程
        for (int index = 0; index < 4; index++) {
            // write thread
            final int num = index;
            Runnable write = new Runnable() {
                String threadName = "Write" + num;

                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(randomTime());
                            // 原子整型的incrementAndGet方法，以原子方式将当前值加 1，返回更新的值
                            int index = wc.incrementAndGet();
                            // 获取并移除此队列的头部，在元素变得可用之前一直等待（如果有必要）。
                            //Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.

                            File file = queue.take();
                            // 队列已经无对象
                            if (file == exitFile) {
                                // 再次添加"标志"，以让其他线程正常退出
                                queue.put(exitFile);
                                break;
                            }
                            System.out.println(threadName + ": " + index + " " + file.getPath());
                        } catch (InterruptedException e) {
                        	System.out.println("异常发生。。。。。。。。。");
                        }
//                        System.out.println("执行中。。。。。。。。");
                    }
                }

            };
            exec.submit(write);
        }//for循环结束
        exec.shutdown();
    }
}
