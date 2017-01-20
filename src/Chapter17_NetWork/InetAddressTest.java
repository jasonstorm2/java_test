package Chapter17_NetWork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * java 提供了 InetAddress类来 代表IP地址
 * InetAddress 类本身没有提供太多的功能，它代表一个IP地址对象
 * InetAddress 有两个子类 Inet4Address Inet6Address
 * InetAddress 没有构造器，而是提供了两个静态方法 来获取 InetAddress实例：getByName  getByAddress
 * @author Administrator
 *
 */
public class InetAddressTest {
	public static void main(String[] args) throws Exception {
		InetAddress ip = InetAddress.getByName("www.baidu.com");
//		InetAddress ip = InetAddress.getByAddress(new byte[]{115,(byte) 239,(byte) 210,27});
		System.out.println("是否可以到达："+ip.isReachable(5000));
		System.out.println("获取该InetAddress对象的全限定域名："+ip.getCanonicalHostName());
		System.out.println("主机地址："+ip.getHostAddress());
		System.out.println("主机名称"+ip.getHostName());

		
		System.out.println("------------------------------------------");
//		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		InetAddress local = InetAddress.getByAddress(new byte[]{10,(byte) 163,86,113});

		
		InetAddress getLocal = InetAddress.getLocalHost();
		System.out.println("本机是否可以达到:"+getLocal.isReachable(5000));
		System.out.println("获取该InetAddress对象的全限定域名："+getLocal.getCanonicalHostName());//windows10.microdone.cn
		System.out.println("主机地址："+getLocal.getHostAddress());//10.163.86.113
		System.out.println("主机名称"+getLocal.getHostName());//LiZhenHua

		
	}

}
