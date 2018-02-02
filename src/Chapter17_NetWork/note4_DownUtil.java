package Chapter17_NetWork;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


/**
 * URL: uniform resource locator 统一资源定位器，用于指向网络中的资源，有定位作用 URL格式：
 * protocol://host:port/resourceName 协议名，主机，端口，资源名 URI uniform resource
 * identifiers 统一资源标识符，不能定位任何资源，唯一作用就是解析 URL
 * 不仅可以解析，而且包含一个可打开到该资源的输入流。。URL可以理解成一个URI的特例。
 * 
 * 三种URL连接方式：
 * // 方法一 
 * URL url = new URL("http://www.sina.com.cn"); URLConnection urlcon =
 * url.openConnection(); InputStream is = urlcon.getInputStream();
 * 
 * // 方法二 
 * URL url = new URL("http://www.yhfund.com.cn"); HttpURLConnection
 * urlcon = (HttpURLConnection)url.openConnection(); InputStream is =
 * urlcon.getInputStream();
 * 
 * //方法三 
 * URL url = new URL("http://www.yhfund.com.cn"); InputStream is =
 * url.openStream();
 * 
 * URL类提供的方法：
 * 
 * String getFile（） 获取该URL的资源名 String getHost() 获取主机名 String getPate()
 * 获取该URL的路径部分 int getPort() 获取该URL的端口号 String getProtocol() 获取该URL的协议名 String
 * getQuery() 获取该URL的 查询字符串部分 URLConnection openConnection() 返回一个URLConnection
 * 对象，它代表了与 URL 所引用的远程对象的连接。 InputStream openStream() 打开与此URL的连接，并返回一个用于读取该
 * URL资源的InputStream对象--通过该方法可以很方便的读取远程资源， 甚至实现多线程下载
 */
public class note4_DownUtil {
	private String path;
	
	private String targetFile;
	
	private int threadNum;
	
	private DownThread[] threads;
	
	private int fileSize;
	
	/** 
	 * @param path  url资源路径
	 * @param targetFile 下载存放路径
	 * @param threadNum  下载线程总数
	 */
	public note4_DownUtil(String path,String targetFile,int threadNum){
		this.path = path;
		this.targetFile = targetFile;
		this.threadNum = threadNum;
		
		threads = new DownThread[threadNum];
		
	}
	
	public void download() throws Exception {
		
		URL url = new URL(path);
		//返回一个URLConnection对象，它代表了与URL所引用的远程对象的连接
		//为什么不用 openStream()方法呢？打开与此URL的连接，并返回一个用于读取该URL资源的InputStream
		
		//URLConnection 表示 程序与URL之间的通信连接，HttpURLConnection表示 与URL 之间的HTTP连接
//		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		URLConnection conn = url.openConnection();

//		conn.setConnectTimeout(5* 1000);
//		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", 
//				"image/gif,image/jpeg,image/pjpeg,"
//				+"application/x-shockwave-flash,application/xaml+xml,*/*");
//		conn.setRequestProperty("Accept-Language", "zh-CN");
//		conn.setRequestProperty("Charset", "UTF-8");
//		conn.setRequestProperty("Connection", "Keep-Alive");
		//得到 文件大小
		fileSize = conn.getContentLength();
		System.out.println("文件大小："+fileSize);
		System.out.println("线程个数："+threadNum);
//		conn.disconnect();
		
		//此处为何+1：取整运算后，可能还有余数，余数肯定在0~1以内，所以加1，防止溢出
		int currentPartSize = fileSize / threadNum +1;
		System.out.println("每个下载部分的大小:"+currentPartSize);
		
		RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
		//设置本地文件的大小
		file.setLength(fileSize);
		file.close();
		System.out.println("当前线程："+Thread.currentThread().getName());
		System.out.println("下载开始时间："+new Date());
		for (int i = 0; i < threadNum; i++) {
			//计算每个线程下载的开始位置
			int startPos = i * currentPartSize;
			//每个线程使用一个RandomAccessFile 进行下载
			RandomAccessFile currenPart = new RandomAccessFile(targetFile, "rw");
			//定位该线程的下载位置
			currenPart.seek(startPos);
			//创建下载线程
			threads[i] = new DownThread(startPos,currentPartSize,currenPart);
			threads[i].start();
			threads[i].join();
			
		}
		System.out.println("下载结束时间："+new Date());
		
		
		
	}
	
	// 获取下载的完成百分比
	public double getCompleteRate(){
		int sumSize = 0;
		for(int i=0;i<threadNum;i++){
			sumSize += threads[i].length;
		}
		return sumSize * 1.0/fileSize;
	}
	
	private class DownThread extends Thread{
		//当前线程的下载位置
		private int startPos;
		//定义当前线程负责下载的文件大小
		private int currentPartSize;
		//当前线程需要下载的文件块。。。
		private RandomAccessFile currentPart;
		//定义该线程已下载的字节数
		public int length;
		
		public DownThread(int startPos,int currentPartSize,RandomAccessFile currentPart){
			this.startPos = startPos;
			this.currentPart = currentPart;
			this.currentPartSize = currentPartSize;
			
		}
		
		public void run(){
			try {
				URL url = new URL(path);
				//返回一个URLConnection对象，它代表了与URL所引用的远程对象的连接
				//为什么不用 openStream()方法呢？打开与此URL的连接，并返回一个用于读取该URL资源的InputStream
				//url.openStream();This method is a shorthand for:  openConnection().getInputStream()

//				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				URLConnection conn = url.openConnection();



//				conn.setConnectTimeout(5* 1000);
//				conn.setRequestMethod("GET");
//				conn.setRequestProperty("Accept", "image/gif,"
//						+"application/x-shockwave-flash,application/xaml+xml,*/*");
//				conn.setRequestProperty("Accept-Language", "zh-CN");
//				conn.setRequestProperty("Charset", "UTF-8");
				
				InputStream inStream = conn.getInputStream();
				//跳过startPos字节，表名该线程只下载自己负责的那部分文件
				inStream.skip(this.startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				//读取网络数据，并写入本地文件
				while(length < currentPartSize && (hasRead = inStream.read(buffer))!=-1){
					currentPart.write(buffer,0,hasRead);
					// 累计该线程下载的总大小
					length += hasRead;
				}
				currentPart.close();
				inStream.close();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}

}
