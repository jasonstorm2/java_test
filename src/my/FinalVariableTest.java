package my;

/**
 * 测试 final修饰的变量 和 普通变量的区别
 * final的变量赋值后无法改变
 * final的变量在初始化时必须赋值，或者在声明时赋值
 * 
 * @author Administrator
 *
 */
public class FinalVariableTest {
	public final int a;
	public final int[] b;
	public int aa;
	public int[] bb;
	
	public FinalVariableTest(int a,int[] b,int aa,int[] bb){
		this.a = a;
		this.b = b;		
		this.aa = aa;
		this.bb = bb;
	}
	
	public static void main(String[] args) {
		FinalVariableTest fv = new FinalVariableTest(1, new int[]{1,1},4, new int[]{4,4});
//		fv.a = 1;  //错误，final的变量无法改变
		System.out.println("fv.aa的值:"+fv.aa);		
		fv.aa = 5;
//		fv.b = new int[]{5,5}; //错误，final的变量无法改变
		System.out.println("fv.bb的值:"+fv.bb[0]+","+fv.bb[1]);
		fv.bb = new int[]{6,6} ; //对象被修改了
		System.out.println("fv.bb的值:"+fv.bb[0]+","+fv.bb[1]);
		
		System.out.println("fv.aa的值:"+fv.aa);		
		fv.aa = 666;  //基础数值被覆盖
		System.out.println("fv.aa的值:"+fv.aa);
		
		
	}
}
