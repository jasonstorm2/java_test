package my;

import java.util.Scanner;

public class scannerTest {
	public static void main (String[] args) 
        {
	       double radius;
           double volume;
           double high;
           Scanner input = new Scanner(System.in);
           System.out.print("请输入半径和高,以空格为分隔符:");
           radius = input.nextDouble(); 
           high = input.nextDouble();
           
           input.close();//scanner要关闭

           volume = Math.PI * radius * radius * high;
           System.out.println("圆柱体的体积:" + volume);	
				
	}
}