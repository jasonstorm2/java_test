package awtSwing;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame3 extends JFrame{
    public static void main(String[] args) {
        MyFrame3 f = new MyFrame3();
    }

    JLabel label1;
    JButton jb1;
    JButton jb2;


    public MyFrame3() {
        this.setVisible(true);
        this.setSize(250, 220);
        this.setVisible(true);
        this.setLocation(400, 200);
        
        // 创建内容面板容器
        JPanel panel = new JPanel();
        // 创建分组布局，并关联容器
        GroupLayout layout = new GroupLayout(panel);
        // 设置容器的布局
        panel.setLayout(layout);


        label1 = new JLabel("TRAILING");  
        jb1 = new JButton("按钮1");
        jb1.setSize(20, 20);
        jb2 = new JButton("按钮2");
        jb2.setSize(20, 20);
        
        
        label1.setSize(20, 20);
        //创建GroupLayout的水平连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.SequentialGroup hGroup1 = layout.createSequentialGroup().addComponent(jb1).addGroup(layout.createParallelGroup(Alignment.CENTER).addComponent(label1)).addComponent(jb2);

        layout.setHorizontalGroup(hGroup1);
        
        
        //创建GroupLayout的垂直连续组，，越先加入的ParallelGroup，优先级级别越高。
        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(Alignment.CENTER).addComponent(jb1).addComponent(label1).addComponent(jb2);

        layout.setVerticalGroup(vGroup);
        
        this.setContentPane(panel);

    }

}
