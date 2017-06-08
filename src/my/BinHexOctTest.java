package my;

public class BinHexOctTest {
	public static void main(String[] args) {
		int a = 100729088;//0x06010100
		int b = 33619968;//0x02010000
//		long c = 4294901760l;//0Xffff0000;
		System.out.println("取a前16位:"+Integer.toBinaryString(getSubType(a))+",长度："+Integer.toBinaryString(getSubType(a)).length());
		System.out.println("取b前16位:"+Integer.toBinaryString(getSubType(b))+",长度："+Integer.toBinaryString(getSubType(b)).length());
		
		isSameType(a, b);
		
		isSameType(b, a);
		
		System.out.println("取a前24位:"+Integer.toBinaryString(getSubType3(a))+",长度："+Integer.toBinaryString(getSubType3(a)).length());
		System.out.println("取b前24位:"+Integer.toBinaryString(getSubType3(b))+",长度："+Integer.toBinaryString(getSubType3(b)).length());
		
		
		
		
	}
	/**
	 * 道具是否属于该大类的类型
	 * @param type 标杆：是不是该类型
	 * @param itemType 需要比较鉴定的道具类型
	 * @return
	 */
	public static boolean isSameType(int baseType, int itemType) {
		System.out.println("是否通知中类型"+Integer.toBinaryString(baseType & itemType));
		return (baseType & itemType) == baseType;
	}

	/**
	 * 返回类型的前16位 OX1200
	 * @param type
	 * @return
	 */
	public static int getSubType(int type) {
		return type & 0Xffff0000;
	}
	
	/**
	 * 前面24位
	 * @param type
	 * @return
	 */
	public static int getSubType3(int type) {
//		Integer.toBinaryString();
		return type & 0xff000000;
	}
}
