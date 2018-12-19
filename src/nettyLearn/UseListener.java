package nettyLearn;

public class UseListener {
	
	private MyListener listener;
	
	public int value = 100;

	public static void main(String[] args) {
		UseListener thisClass = new UseListener();
		thisClass.addListener(new MyListener() {
			@Override
			public void operate(UseListener invoke) {
				// TODO Auto-generated method stub
				//super.operate(invoke);//调用了父类的方法，注释掉后，就不会调用，纯粹重写
				if(invoke.value>100){
					System.out.println("value的值>100");
				}else{
					System.out.println("value的<=100");
				}
			}
		});

	}

	public void addListener(MyListener method) {
		listener = method;
		method.operate(this);

	}

	public boolean isSucess() {
		return false;
	}

}

/**
 * 监听器，只有对监听对象的处理
 * @author LiZhenhua
 *
 */
class MyListener {
	/**
	 * 对监听对象的处理
	 * @param invoke
	 */
	public void operate(UseListener invoke) {	
		System.out.println("打印值："+invoke.value);
	}

}
