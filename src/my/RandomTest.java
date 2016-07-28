package my;

import java.util.Random;

public class RandomTest {
	public Random ran2=new Random();
	
	private static void checkNumber(){
		int arr[] = new int[201];
		int i;
		for(i=1;i<=100;i++){
			arr[i]=i+1;
			arr[i+100]=0-(i+1);
		}
		arr[0]=0;
		int plus=0; int negative=0 ;
		//生成十个随机数
		for(i=0;i<10;i++){
			Random r = new Random();
			int a = arr[r.nextInt(201)];
			System.out.print(a+" ");
			if(a>0){
				plus++;
			}else if(a<0){
				negative++;
			}
		}
		System.out.println("");
		System.out.println("正数有"+plus+"个");
		System.out.println("负数有"+negative+"个");
	}
	
	public static void getMinusRandom(){
		double s=(Math.random()*10);
		int ss =(int)s;
		System.out.println("s="+s);
		System.out.println("ss="+ss);
	}
	public static void main(String[] args) {
		Random ran = new Random();//默认以当前时间作为种子，那么生成的序列当然不同
		Random ran1 = new Random(10);//固定的种子，生成固定的序列的随机数
		
		for (int i = 0; i < 5; i++) {
			int a=ran.nextInt(10);
			System.out.println("a="+a);
		}
		
		for (int i = 0; i < 5; i++) {
			int b=ran1.nextInt(10);
			System.out.println("b="+b);
		}
		for (int i = 0; i < 5; i++) {
			double b2=ran1.nextDouble();
			System.out.println("b2="+b2);
		}
		
		RandomTest.getMinusRandom();
		Random rr = new Random();
		int r=rr.nextInt();
		int r2=r%1000;
		
		
		System.out.println("%100="+r);
		System.out.println("r2="+r2);

		
		
		
	}

}
