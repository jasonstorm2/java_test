package Chapter17_NetWork;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * Socket与URL通信比较
 * 
 * 利用URL进行通信与利用socket进行通信有许多相似之处。它们都是利用建立连接、获取流来进行通信。那么，它们的区别在何处呢？
 * 利用socket进行通信时， 在服务器端运行一个socket通信程序。服务器端不停地监听某个端口，等待客户的连接申请，
 * 接到申请后建立连接并进行通信，所以，在socket通信方式中，服务器是主动等待连接通信的到来。
 * 
 * 利用URL进行通信时，在服务器端常驻一个CGI程序，但它一直处于休眠状态。只有在客户端要求建立连接时才被激活，然后与用户进行通信
 * 。所以，在URL通信方式中，服务器是被动等待连接通信的到来。
 * 由于URL通信和socket通信的方式不同，所以，它们有各自的特点。利用socket进行通信时
 * ，服务器端的程序可以打开多个线程与多个客户进行通信，还可以通过服务器使各个客户之间进行通信
 * 。这种方式比较灵活，适用于一些较复杂的通信，但是服务器端的程序必须始终处于运行状态以监听端口
 * 。利用URL进行通信时，服务器端的程序只能与一个客户进行通信，形式比较单一
 * 。但是它不需要服务器端的CGI程序一直处于运行状态，只是在有客户申请时才被激活。所以，这种方式比较适用于客户机的浏览器与服务器之间的通信。！
 * 
 * 
 * URLDecoder URLEncoder用于完成普通字符串 和 application/x-www-form-urlencoded MIME
 * 字符串之间的相互转换
 * 
 * 仅包含西欧字符的普通字符串和application/x-www-form-urlencoded MIME字符串无需转换
 * 
 * 当URL地址里 包含非西欧字符的字符串时，系统会将这些非西欧字符串转换成特殊字符串，若要用到这些字符串，就需要URLDecoder
 * URLEncoder转换了
 * 
 * URLDecoder：解码，包含 decode静态方法，可以将 看上去是乱码 的字符串转换成 普通字符串 URLEncoder
 * :编码，encode静态方法，可以将 普通字符串 转换成 所谓的乱码--application/x-www-form-urlencoded MIME字符串
 * 
 * utf-8字符集中，每个中文占 三个字节（1字节=8位，4位可以用1个十六进制表示），每个字节可以转换才两个十六进制的数字，如：%E4%BD%A0
 * 不同字符集，中文所占字节数不一样
 * 
 * @author LiZhenhua
 *
 */
public class note3_URLDecoderURLEncoderTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		//解码--把特殊的字符 转换为 普通字符
		String decode = URLDecoder.decode("%E4%BD%A0%E5%A5%BD%E5%90%97", "utf-8");
		System.out.println(decode);
		
		//编码--把普通字符 转换成 特殊字符
		String encode = URLEncoder.encode("你好吗", "utf-8");
		System.out.println(encode);
	}

}
