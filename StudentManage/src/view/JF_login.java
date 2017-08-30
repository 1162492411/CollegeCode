package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import util.CommonJdbc;
import util.JdbcAdapter;

/*
 * 系统登录窗体，用于防止非法用户进入系统内部
 */
@SuppressWarnings("serial")
public class JF_login extends JFrame{
	public Container container;
	public JLabel labelA, labelB,back;
	public JTextField tfA;
	public JPasswordField tfB;
	public JButton btA, btB;
	public Connection con = null;
	public PreparedStatement pstmt=null;
	public ResultSet rs = null;
	//登录窗体的构造函数
	public JF_login(){
		container = this.getContentPane();
		container.setLayout(null);		
		//设置装饰效果
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕大小
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		back = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/loginBack.jpg")));
		back.setBounds(0,0,400,130);
		//设置组件
		labelA = new JLabel("账户");
		labelA.setBounds(80,145,30,30);
		tfA = new JTextField();
		tfA.setBounds(120, 145, 200, 30);
		labelB = new JLabel("密码");
		labelB.setBounds(80,180,30,30);
		tfB = new JPasswordField();
		tfB.setBounds(120, 180, 200, 30);
		btA = new JButton("登录");
		btA.addActionListener(new handleLogin());
		btA.setBounds(140, 220, 60, 30);
		btB = new JButton("退出");
		btB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btB.setBounds(210, 220, 60, 30);
		//添加组件
		container.add(back);
		container.add(labelA);
		container.add(tfA);
		container.add(labelB);
		container.add(tfB);
		container.add(btA);
		container.add(btB);
		//设置窗体
		this.setTitle("学生成绩管理系统");
		this.setBounds(screenWidth / 2 - 200, screenHeight / 2 - 150, 400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}
	//处理点击 登录 按钮 事件
		public class handleLogin implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(new JdbcAdapter().validateUser(tfA.getText(),tfB.getPassword())) 
					new appMain().setVisible(true);
				else 
					JOptionPane.showMessageDialog(null, "帐号或密码输入错误！");
			}	
		}

	public static void main(String[] args) {
		JF_login aJF_login = new JF_login();

	}

}
