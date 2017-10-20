package Chapter17_NetWork;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Chapter17_NetWork.AIOServer.AcceptHandler;

public class AIOClient {
	static final int PORT = 30000;
	final static String UTF_8 = "utf-8";
	
	AsynchronousSocketChannel clientChannel;
	JFrame mainWin = new JFrame("多人聊天");
	JTextArea jta = new JTextArea(16,48);
	JTextField jtf =  new JTextField(40);	
	JButton sendBn = new JButton("发送");
	
	public void init(){
		mainWin.setLayout(new BorderLayout());
		jta.setEditable(false);
		mainWin.add(new JScrollPane(jta),BorderLayout.CENTER);
		
		JPanel jp = new JPanel();
		jp.add(jtf);
		jp.add(sendBn);
		
		Action sendAction = new AbstractAction() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String content = jtf.getText();
				if(content.trim().length() > 0){
					
					try {
						clientChannel.write(ByteBuffer.wrap(content.trim().getBytes(UTF_8))).get();
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ExecutionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// 清空输入框
					jtf.setText("");					
				}
			}
		};
		
	    sendBn.addActionListener(sendAction);
	    // 将 ctrl+ enter键 和send 关联
	    jtf.getInputMap().put(KeyStroke.getKeyStroke('\n'), "send");
	    // 将send 和 sendAction关联
	    jtf.getActionMap().put("send", sendAction);
	    
	    mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainWin.add(jp, BorderLayout.SOUTH);
	    mainWin.pack();
	    mainWin.setVisible(true);		
	}
	
	public void connect() throws Exception{
		final ByteBuffer buff = ByteBuffer.allocate(1024);
		ExecutorService executor = Executors.newFixedThreadPool(20);
		AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executor);	
		clientChannel = AsynchronousSocketChannel.open(channelGroup);
		clientChannel.connect(new InetSocketAddress("127.0.0.1", PORT)).get();
		
		jta.append("--与服务器连接成功--\n");
		buff.clear();
		
		clientChannel.read(buff, null, new CompletionHandler<Integer, Object>() {

			@Override
			public void completed(Integer result, Object attachment) {
				// TODO Auto-generated method stub
				buff.flip();
				
				String content = StandardCharsets.UTF_8.decode(buff).toString();
				
				jta.append("某人说："+content + "\n");
				buff.clear();
				clientChannel.read(buff, null, this);
			}

			@Override
			public void failed(Throwable exc, Object attachment) {
				// TODO Auto-generated method stub
				System.out.println("读取数据失败："+exc);
				
			}
		});
		
	}
	public static void main(String[] args) throws Exception {
		AIOClient client = new AIOClient();
		client.init();
		client.connect();
	}
	
}
