package my;

/**
 * 
 * @author Administrator
 * 按位运算用于基础类型，不能表示逻辑运算
 */
public class IntEquals {
	
	public boolean isSame(int channelNum,int channel){
//		return channelNum==channel;
		return (channelNum & channel) == channelNum;
	}
	
	public int intand(int channelNum,int channel){
		return (channelNum & channel);
	}
	public static void main(String[] args) {
		IntEquals ii = new IntEquals();
		boolean bo=ii.isSame(6,2);
		System.out.println(bo);
		
		int and=ii.intand(6,2);
		System.out.println(and);
		
	}

}
