package my;


public class SpecialMethod {  
    public static void main(String[] args) {  
//        boolean flag = true;  
//        flag &= true;  //与
//        System.out.println("true\t&=\ttrue\t==>\t" + flag);  
//        flag = true;  
//        flag &= false;  
//        System.out.println("true\t&=\tfalse\t==>\t" + flag);   
//        flag = false;  
//        flag &= true;  
//        System.out.println("false\t&=\ttrue\t==>\t" + flag);   
//        flag = false;  
//        flag &= false;  
//        System.out.println("false\t&=\tfalse\t==>\t" + flag+"\n");   
//          
//        flag = true;  
//        flag |= true;  //或
//        System.out.println("true\t|=\ttrue\t==>\t" + flag);  
//        flag = true;  
//        flag |= false;  
//        System.out.println("true\t|=\tfalse\t==>\t" + flag);   
//        flag = false;  
//        flag |= true;  
//        System.out.println("false\t|=\ttrue\t==>\t" + flag);   
//        flag = false;  
//        flag |= false;  
//        System.out.println("false\t|=\tfalse\t==>\t" + flag+"\n");   
//          
//        System.out.println("^=  相同为真，不同为假");  
//        flag = true;  
//        flag ^= true;  //异或 --- 异真
//        System.out.println("true\t^=\ttrue\t==>\t" + flag);  
//        flag = true;  
//        flag ^= false;  
//        System.out.println("true\t^=\tfalse\t==>\t" + flag);   
//        flag = false;  
//        flag ^= true;  
//        System.out.println("false\t^=\ttrue\t==>\t" + flag);   
//        flag = false;  
//        flag ^= false;  
//        System.out.println("false\t^=\tfalse\t==>\t" + flag);   
        int i=0;
        for (;;) {//死循环
        	i++;
			System.out.println("你是谁？"+i);
        	if(i>=1000){
        		break;
        	}
		}
    }  
}  