package JavaCoreLearn;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;
/**
 * 一个java源文件里可以有多个class，但只能由一个是public
 * @author LiZhenhua
 *
 */
public class InnerClassTest {

	public static void main(String[] args) {
		TalkingClock clock = new TalkingClock(1000, true);
		
		//通过外部类对象 构建 内部类对象
		TalkingClock clock2 = new TalkingClock(1000, true);
		TalkingClock.TimePrinter listener = clock2.new TimePrinter();//外部类.内部类
		clock2.init(new ArrayList<String>(){
			{
				add("jx");add("bd");
			}
		});
		
		clock.start();
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);
		
	
		}
}

	class TalkingClock {
		private int interval;
		private boolean beep;
		private ArrayList<String> namelist;

		public TalkingClock(int interval, boolean beep) {
			this.interval = interval;
			this.beep = beep;
		}
		
		public void init(ArrayList<String> ar){
			this.namelist = ar;			
		}
		public void start() {
			//局部内部类
//			class TimePrinter implements ActionListener {
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					// TODO Auto-generated method stub
//					Date now = new Date();
//					System.out.println("pick up:" + now);
//					if (beep)//外围类引用的正式格式outter.this.beep
//						Toolkit.getDefaultToolkit().beep();
//				}
//
//		}
			ActionListener listener = new TimePrinter();
//			ActionListener listener = new TimePrinter(this);内部类，类似这样传递
//			ActionListener listener2 = this.new TimePrinter();//把外围类的引用this设置为创建内部类对象 的方法中的this引用
			Timer t = new Timer(interval, listener);
			t.start();
		}
		
		/************************************************************************************/
		//由外部方法访问final变量
		public void start(int interval,final boolean beep) {
			//局部内部类
			class TimePrinter2 implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					Date now = new Date();
					System.out.println("pick up:" + now);
					if (beep)//外围类引用的正式格式outter.this.beep
						Toolkit.getDefaultToolkit().beep();
				}

		}
			ActionListener listener = new TimePrinter2();
			Timer t = new Timer(interval, listener);
			t.start();
		}
		/************************************************************************************/
		//匿名内部类
		public void start1(int interval,final boolean beep){
			ActionListener listener = new ActionListener() { //重写接口的方法
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
				}
			};
			
			Timer t = new Timer(interval, listener);
			t.start();
		}
		
		
		public class TimePrinter implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date now = new Date();
				System.out.println("pick up:" + now);
				if (beep)//外围类引用的正式格式outter.this.beep
					Toolkit.getDefaultToolkit().beep();
			}

	}
}

