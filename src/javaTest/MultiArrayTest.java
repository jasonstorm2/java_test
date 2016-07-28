package javaTest;

public class MultiArrayTest {
	private static int length = 9999;
	public static void main(String[] args) {
		int[][] test = new int[length][length];
		long curTime = System.currentTimeMillis();
		row(test);
		System.out.println("row遍历时间："+(System.currentTimeMillis()-curTime));
		curTime = System.currentTimeMillis();
		column(test);		
		System.out.println("col遍历时间："+(System.currentTimeMillis()-curTime));
		
	}
	private static void row(int[][] ss){
		for(int i=0;i<ss.length;i++){
			for(int j=0;j<ss.length;j++){
				ss[i][j] = i+j;
			}			
		}
		
	}
	private static void column(int[][] ss){
		for(int i=0;i<ss.length;i++){
			for(int j=0;j<ss.length;j++){
				ss[j][i] = i+j;
			}			
		}
	}

}
