package my;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Arrays;

public class ArrarysSort {
	public static void main(String[] args) {
		int[] test = {2,1,5};
		Arrays.sort(test);
		System.out.println(test);
		System.out.println(System.getProperty("user.dir"));
		String separator =  File.separator;
		System.out.println();
		InputStream s = null;
		Reader r  = null;
		CharBuffer c = null;
	}

}
