package Chapter17_NetWork;

/**
 * 多线程下载测试
 * @author Administrator
 *
 */
public class note5_MultiThreadDown {
	public static void main(String[] args) throws Exception {
		final note4_DownUtil downUtil = new note4_DownUtil("file:///D:/Test/source/xxx.rmvb", "D:\\Test\\destination\\xxx.rmvb", 4);
		System.out.println("main当前线程："+Thread.currentThread().getName());
		downUtil.download();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long startTime = System.currentTimeMillis();
				System.out.println("开始时间："+startTime);
				// TODO Auto-generated method stub
				while (downUtil.getCompleteRate()<1) {
					//每隔0.1秒查询一次任务的完成进度
					System.out.println("已完成："+downUtil.getCompleteRate());
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				System.out.println("结束时间："+System.currentTimeMillis());
				System.out.println("间隔时间："+(System.currentTimeMillis()-startTime));
				
				
			}
		}).start();
	}

}
