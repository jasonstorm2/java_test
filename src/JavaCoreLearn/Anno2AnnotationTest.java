package JavaCoreLearn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 本例测试 通过注释类，绑定按钮和监听器
 * @author LiZhenhua
 *
 */
public class Anno2AnnotationTest {
	private JFrame mainWin = new JFrame("使用注册绑定事件监听器");
	
	@Anno2Mine(listener = OkListener.class,test = "确定")//必须设置 annotation 为设置默认值的变量
	private JButton ok = new JButton("确定");
	
	@Anno2Mine(listener = CancelListener.class,test = "取消")
	private JButton cancel = new JButton("取消");
	
	public void init(){
		JPanel jp = new JPanel();
		jp.add(ok);
		jp.add(cancel);
		mainWin.add(jp);
		
		Anno2ActionListenerInstaller.processAnnotations(this);//给按钮设置监听	
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.pack();
		mainWin.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Anno2AnnotationTest().init();
	}
}

class OkListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "单击了确认按钮");
	}
	
}

class CancelListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "单击了取消按钮");
	}
	
}
