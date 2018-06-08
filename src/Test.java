import java.util.Properties;


class Annoyance extends Exception {}
class Sneeze extends Annoyance {}
 
/**
 * 判处异常的捕获与异常及其继承者的关系
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) throws Exception {
		
		Properties p = new Properties();
		
		try {
			try {
				throw new Sneeze();
			} catch (Annoyance a) {//抛出的其实是Sneeze对象。
				System.out.println("Caught Annoyance");
				throw a;
			}
		} catch (Sneeze s) { //判处的其实是Sneeze对象。
			System.out.println("Caught Sneeze");
			return;
		} finally {
			System.out.println("Hello World!");
		}
	}
}