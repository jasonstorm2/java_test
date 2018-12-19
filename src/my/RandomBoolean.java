package my;

import java.util.Random;

/**
 * Random().nextBoolean()方法，返回的是一个伪随机布尔值
 * @author LiZhenhua
 *
 */
public class RandomBoolean {
	public static void main(String[] args) {
		for(int i=0;i< 100;i++){
			System.out.println("随机的布尔值:"+i+new Random().nextBoolean());
		}		
	}
}
