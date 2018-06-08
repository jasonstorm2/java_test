package my;

public class enumTest {
	public static void main(String[] args) {
		System.out.println(enumT.first);
		System.out.println(enumT.values());
		System.out.println(enumT.valueOf("first"));		
		
	}
}


enum enumT {
	first,
	second,
	third;
}