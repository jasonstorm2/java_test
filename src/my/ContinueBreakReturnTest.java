package my;

public class ContinueBreakReturnTest {
	public static void main(String[] args) {
		int a=1;
		int b=2;
		int c=3;
		int[] d={0,1,2,3,4,5,1};
		
		
		if(a==1){
//			System.out.println("a=1");
			for(int i=0;i<7;i++){
				if(d[i]==4){
					System.out.println("i的值："+i);
					break;//终止循环，循环体以后的语句都不执行。。。。跳出循环
//					return;//函数返回，以后的所有语句都不执行。。。。比较霸道
//					continue;
				}
//				else if(d[i]==2){
//					System.out.println("b等于2");
////					break;
////					return;//函数返回，以后的所有语句都不执行。。。。比较霸道
//
//				}
				System.out.println("i=="+i);
			}
			System.out.println("在for循环之外");

//		}else if(b==2){
//			System.out.println("b=2");
//		}else if(c==3){
//			System.out.println("c=3");
//		}
		
//		if(b==2){
//			System.out.println("判断：b==2");
		}
		
		System.out.println("循环体，判断体之外的语句是否执行");
		
	}

}
