package my;

import java.util.Arrays;

/**
 * 二分法搜索测试
 * 
 * 此法为二分搜索法，故查询前需要用sort()方法将数组排序，如果数组没有排序，则结果是不确定的，
 * 另外,如果数组中含有多个指定值的元素，则无法保证找到的是哪一个
 * 
 * @author LiZhenhua
 *
 */
public class BinaryArraySearchTest {
	public static void main(String[] args) {
	     int a[] = new int[] {1, 3, 4, 6, 8, 9};
	        int x1 = Arrays.binarySearch(a, 5);
	        int x2 = Arrays.binarySearch(a, 4);
	        int x3 = Arrays.binarySearch(a, 0);
	        int x4 = Arrays.binarySearch(a, 10);
	        System.out.println("x1:" + x1 + ", x2:" + x2);
	        System.out.println("x3:" + x3 + ", x4:" + x4);
	}
}
