package my;

public class ParseIntTest {
	public static void main(String[] args) {
		System.out.println(isInteger("s"));
		System.out.println(isInteger("1"));

	}

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
