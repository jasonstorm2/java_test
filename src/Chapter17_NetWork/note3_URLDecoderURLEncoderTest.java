package Chapter17_NetWork;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URLDecoder URLEncoder用于完成普通字符串 和 application/x-www-form-urlencoded MIME 字符串之间的相互转换
 * 
 * 仅包含西欧字符的普通字符串和application/x-www-form-urlencoded MIME字符串无需转换
 * 
 * 当URL地址里 包含非西欧字符的字符串时，系统会将这些非西欧字符串转换成特殊字符串，若要用到这些字符串，就需要URLDecoder URLEncoder转换了
 * 
 * URLDecoder：解码，包含 decode静态方法，可以将 看上去是乱码 的字符串转换成 普通字符串
 * URLEncoder :编码，encode静态方法，可以将 普通字符串 转换成 所谓的乱码--application/x-www-form-urlencoded MIME字符串
 * 
 * utf-8字符集中，每个中文占 三个字节（1字节=8位，4位可以用1个十六进制表示），每个字节可以转换才两个十六进制的数字，如：%E4%BD%A0
 * 不同字符集，中文所占字节数不一样
 * @author Administrator
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
