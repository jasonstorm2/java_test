package Chapter17_NetWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * java 提供了 InetAddress类来 代表IP地址 InetAddress 类本身没有提供太多的功能，它代表一个IP地址对象
 * InetAddress 有两个子类 Inet4Address Inet6Address InetAddress 没有构造器，而是提供了两个静态方法 来获取
 * InetAddress实例：getByName getByAddress InetAddress类 还提供了一个获取本机的
 * InetAddress对象的方法 getLocalHost();
 * 
 * isReachable()方法，用于测试 是否能够达到该地址！该方法将尽最大的努力 试图达到主机，但防火墙和服务器配置 可能阻塞请求， 使得它在
 * 访问某些特定的端口时，处于不可达到状态。如果可以获得权限，典型的实现将使用 ICMP ECHO REQUEST;否则它将试图在目标主机
 * 的端口7（ECHO）上建立TCP连接。
 * 
 * HTTP是协议 我们在浏览器的地址栏里输入的网站地址叫做URL (Uniform Resource Locator，统一资源定位符)。
 * 就像每家每户都有一个门牌地址一样，每个网页也都有一个Internet地址。当
 * 你在浏览器的地址框中输入一个URL或是单击一个超级链接时，URL就确定了要浏览的地址。
 * 浏览器通过超文本传输协议(HTTP)，将Web服务器上站点的网页代码提取出来，并翻译成漂亮的网页。
 * 因此，在我们认识HTTP之前，有必要先弄清楚URL的组成,例如： http://www.baidu.com/china/index.htm 。
 * 它的含义如下： 1. http://：代表超文本传输协议，通知baidu.com服务器显示Web页，通常不用输入； 2.
 * www：代表一个Web（万维网）服务器； 3. baidu.com/：这是装有网页的服务器的域名，或站点服务器的名称； 4.
 * China/：为该服务器上的子目录，就好像我们的文件夹； 5. Index.htm：index.htm是文件夹中的一个HTML文件（网页）。
 * 
 * 
 * InetAddress类 本身没有太多的功能，它代表一个IP地址对象，是网络通信的基础。后面的介绍中将大量使用该类
 * 
 * InetAddress.getByName():根据主机名字获取InetAddress对象
 * InetAddress.getByAddress():通过原始的IP地址来获取InetAddress对象
 * getCanonicalHostName()：获取该InetAddress对象的全限定域名
 * getHostAddress()：获取该InetAddress对象的对应第IP地址，字符串
 * getLocal.getHostName():获取该InetAddress对象的主机名
 * isReachable(2000)：测试是否可以到达该地址
 * @author Administrator
 *
 */
public class note2_InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("www.baidu.com");
//		InetAddress ip = InetAddress.getByAddress(new byte[]{115,(byte) 239,(byte) 210,27});
		System.out.println("是否可以到达："+ip.isReachable(2000));
		System.out.println("获取该InetAddress对象的全限定域名："+ip.getCanonicalHostName());
		System.out.println("主机地址："+ip.getHostAddress());
		System.out.println("主机名称"+ip.getHostName());

		
		System.out.println("------------------------------------------");
//		InetAddress getLocal = InetAddress.getByAddress(new byte[]{127,0,0,1});
//		InetAddress getLocal = InetAddress.getByAddress(new byte[]{10,(byte) 163,86,113});

		
		InetAddress getLocal = InetAddress.getLocalHost();
		System.out.println("本机是否可以达到:"+getLocal.isReachable(2000));
		System.out.println("获取该InetAddress对象的全限定域名："+getLocal.getCanonicalHostName());//windows10.microdone.cn
		System.out.println("主机地址："+getLocal.getHostAddress());//10.163.86.113
		System.out.println("主机名称"+getLocal.getHostName());//LiZhenHua
		InetAddress in =InetAddress.getByAddress(new byte[]{(byte)103,(byte)44,(byte)145,(byte)245});

		
	}

}
