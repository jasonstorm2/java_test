package JavaCoreLearn;

import java.lang.reflect.Array;

public class ArrayTest2 {
	public static void main(String[] args) {
		
		
		String[][][] cc = new String[4][4][4];
		/*
		 * 三维数组：其实也是一个一维数组，其数组元素是二维数组
		 * 因此可以认为：arr 是长度为 3 的一维数组
		 */
		Object arr = Array.newInstance(String.class, 3,4,10);
		//arr数组中 index 为 2 的元素，该元素是二维数组
		Object arrObj = Array.get(arr, 2);
		//为二维数组赋值，二维数组的元素是一维数组
		Array.set(arrObj, 2, new String[]{
				"疯狂金发女啊",
				"劳动纠纷","eee"
		});
		//获取二维数组中，下标为3 的一维数组元素
		Object anAr = Array.get(arrObj, 3);
		
		Array.set(anAr, 8, "阿道夫");
		
		Object anAr0 = Array.get(arrObj, 0);		
		Array.set(anAr0,9,"坐标:2,0,9");
		
		String[][][] cast = (String[][][])arr;      //此处的 强制转换 是不安全的操作 
		System.out.println(cast[2][3][8]);
		System.out.println(cast[2][2][0]);
		System.out.println(cast[2][2][1]);
		System.out.println(cast[2][2][2]);
		System.out.println(cast[2][0][9]);
		
		
		System.out.println(cast[2][3][9]);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <4; j++) {
				for (int j2 = 0; j2 < 4; j2++) {
//					if (cc[i][j][j2]!=null) {
						System.out.println(cc[i][j][j2]);
//					}
				}
				
			}
		}
	}

}
