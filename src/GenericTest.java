import java.util.*;

public class GenericTest {

	public static void main(String[] args) {

		Box<String> name = new Box<String>("corn");
		Box<Integer> age = new Box<Integer>(712);
		Box<Number> number = new Box<Number>(314);

		getData(name);
		getData(age);
		getData(number);
		// getUpperNumberData(name); // 1
		getUpperNumberData(age); // 2
		getUpperNumberData(number); // 3
	}

	// 泛型类型通配符？的运用。？代替具体的类型实参，，注意，是实参而非形参。。。Box<?>是所有Box<具体类型实参>的逻辑上的父类
	public static void getData(Box<?> data) {
		System.out.println("data :" + data.getData());
	}

	// 类型通配符上限。。。（还有下限的设置）
	/**
	 * 类型通配符上限通过形如Box<? extends Number>形式定义，相对应的，类型通配符下限为Box<? super
	 * Number>形式，其含义与类型通配符上限正好相反，在此不作过多阐述了。
	 */
	public static void getUpperNumberData(Box<? extends Number> data) {
		System.out.println("data :" + data.getData());
	}
}

class Box<T> {

	private T data;

	public Box() {

	}

	public Box(T data) {
		setData(data);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}