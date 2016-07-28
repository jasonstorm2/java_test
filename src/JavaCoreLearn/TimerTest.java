package JavaCoreLearn;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

public class TimerTest {
	public static void main(String[] args) {
		ActionListener listener = new TimePrinter();
		Timer t = new Timer(3000, listener);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit?");
		System.exit(0);		
		System.out.println(t.isRunning());
		
	}

}
class TimePrinter implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.out.println("pick up");
		Toolkit.getDefaultToolkit().beep();		
		Toolkit.getDefaultToolkit().beep();
		
	}
	
	@SuppressWarnings(value =  { "unchecked", "rawtypes" })
	public void suppressTest(){
		List<String> ls = new ArrayList();
	}
	
	@SafeVarargs //必须静态才可以
	public static void heapPollutionTest(List<String>... param){
		
	}
	
}