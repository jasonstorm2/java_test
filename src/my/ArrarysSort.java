package my;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Arrays;

/**
 * Arrays.sort  对数组进行排序
 * @author LiZhenhua
 *
 */
public class ArrarysSort {
	public static void main(String[] args) {
		int[] test = {2,1,5};
		Arrays.sort(test);
		for (int i : test) {
			System.out.println(i);
		}
		
		System.out.println(System.getProperty("user.dir"));
		String separator =  File.separator;
		System.out.println();
	}

}
