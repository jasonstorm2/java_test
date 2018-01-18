package javaTest;


/**
 * int[] blankArray = new int[]{} 	定义了一个长度为0的数组对象
 * iint[] blankArray = new int[0]; 也是定义了一个长度为0的数组对象
 * 
 * int[] res数组长度为0的，里面没有元素，res[0]报空，因为没有下标为0的元素存在
 * @author Administrator
 *
 */
public class intArrayEmptyLengthTest {
	public static void main(String[] args) {
		//实际上使用了匿名内部类来定义blankArray了，所以是不为null，但是长度为零
		int[] blankArray = new int[]{};
//		int[] blankArray = new int[12];
		if (blankArray != null) {
			System.out.println("非空");
		}
		if(blankArray!=null&&blankArray.length>0){
			System.out.println("不为空,有元素");
		}else{
			System.out.println("不为空，没有元素");
		}
		System.out.println("blankArray长度："+blankArray.length);
		for(int i=0;i<blankArray.length;i++){
			System.out.println("第"+i+"个元素:"+blankArray[i]);
		}
		
		int[] result = new int[0];
		System.out.println("result的长度："+result.length);		
//		System.out.println("result值："+result[0]); //报错
		
	}
}
