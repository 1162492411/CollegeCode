package com.qq.main;

/**
 * 主登录界面
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.qq.model.User;
import com.qq.myComponent.myButton;
import com.qq.myComponent.myLabel;
import com.qq.util.JdbcAdapter;

public class login extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private myLabel back, label_id, label_pass;// 标签提示
	private JTextField tf_id;// 账户输入框
	private JPasswordField tf_pass;// 密码输入框
	private JPanel panel;// 面板
	private myButton ensureButton, exitButton, registerButton, forgetPassButton;// 四个功能按钮
	// 构造函数

	public login() {
		
		getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕大小

		back = new myLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/back.png")));// 设置背景
		back.setBounds(0, 0, 400, 90);
		back.setOpaque(false);
		getContentPane().add(back);

		panel = new JPanel(new FlowLayout());// 设置面板
		panel.setBounds(50, 100, 260, 100);

		label_id = new myLabel("账户    ");// 设置账户文字提示
		panel.add(label_id);

		tf_id = new JTextField(10);// 设置账户输入框
		panel.add(tf_id);

		registerButton = new myButton("注册账户");// 设置注册按钮
		registerButton.setBorder(null);
		registerButton.setBackground(Color.white);
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new register().setVisible(true);
			}
		});
		panel.add(registerButton);

		label_pass = new myLabel("密码    ");// 设置密码文字提示
		panel.add(label_pass);

		tf_pass = new JPasswordField(10);// 设置密码输入框
		panel.add(tf_pass);

		forgetPassButton = new myButton("忘记密码");// 设置忘记密码按钮
		forgetPassButton.setBorder(null);
		forgetPassButton.setBackground(Color.white);
		panel.add(forgetPassButton);

		// 设置登录按钮
		ensureButton = new myButton("登录", new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/login.png")));
		ensureButton.addActionListener(this);
		panel.add(ensureButton);

		// 设置退出按钮
		exitButton = new myButton("退出", new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/logout.png")));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(exitButton);
		getContentPane().add(panel);


		// 设置界面其他属性
		setTitle("用户登录界面");
		setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 113, 400, 225);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
	
	/**
	 * 检测输入的数据是否合法
	 */
	private boolean checkData() {
		// 账户长度大于0，密码长度大于6，返回真值
		return tf_id.getText().length() > 0 && String.valueOf(tf_pass.getPassword()).length() >= 6;
	}

	/**
	 * 事件处理
	 */
	public void actionPerformed(ActionEvent e) {// 点击了登录按钮
		User aUser = new User(tf_id.getText(), String.valueOf(tf_pass.getPassword()));
		// 若数据输入合法且成功验证帐号密码
		if (checkData() && new JdbcAdapter().validateLogin(aUser)) {
			this.setVisible(false);// 将此窗体隐藏并退出
			this.dispose();
			new mainUser(tf_id.getText());// 启动用户主界面
		} else {// 否则，提示用户遇到错误
			JOptionPane.showMessageDialog(null, "登录失败！请重新核对账户及密码！");
		}
	}

	// 测试函数，启动程序
	public static void main(String[] args) {
		new login();
	}

}
