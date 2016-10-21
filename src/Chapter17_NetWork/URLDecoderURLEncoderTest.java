package Chapter17_NetWork;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLDecoder URLEncoder用于完成普通字符串 和 application/x-www-form-urlencoded MIME 字符串之间的相互转换
 * 
 * 仅包含西欧字符的普通字符串和application/x-www-form-urlencoded MIME字符串无需转换
 * @author Administrator
 *
 */
public class URLDecoderURLEncoderTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		//解码--把特殊的字符 转换为 普通字符
		String decode = URLDecoder.decode("%E4%BD%A0%E5%A5%BD%E5%90%97", "utf-8");
		System.out.println(decode);
		
		//编码--把普通字符 转换成 特殊字符
		String encode = URLEncoder.encode("你好吗", "utf-8");
		System.out.println(encode);
	}

}
