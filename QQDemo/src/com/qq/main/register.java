package com.qq.main;
/**
 * 注册界面
 */
import java.awt.Dimension;
/**
 * 用户注册界面
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.qq.model.User;
import com.qq.myComponent.myButton;
import com.qq.myComponent.myLabel;
import com.qq.util.JdbcAdapter;

import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class register extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private myLabel label_id, label_pass, label_ensurepass, label_name, label_sex, label_age, label_info, label_email;
	private JTextField tf_id, tf_name, tf_sex, tf_age, tf_info, tf_email;
	private JPasswordField tf_pass, tf_ensurepass;
	private myButton ensureButton, resetButton;
	/**
	 * 构造函数
	 */
	public register() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕大小
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();// 设置并添加面板
		panel.setBounds(100, 30, 200, 260);
		panel.setLayout(new FlowLayout());
		getContentPane().add(panel);

		myLabel topLabel = new myLabel("用户注册界面");// 添加顶部标签文字
		topLabel.setBounds(150, 5, 150, 25);
		getContentPane().add(topLabel);

		// 添加面板上的元素
		label_id = new myLabel("账户        ");
		panel.add(label_id);

		tf_id = new JTextField(10);
		panel.add(tf_id);

		label_pass = new myLabel("密码        ");
		panel.add(label_pass);

		tf_pass = new JPasswordField(10);
		panel.add(tf_pass);

		label_ensurepass = new myLabel("确认密码  ");
		panel.add(label_ensurepass);

		tf_ensurepass = new JPasswordField(10);
		panel.add(tf_ensurepass);

		label_name = new myLabel("昵称        ");
		panel.add(label_name);

		tf_name = new JTextField(10);
		panel.add(tf_name);

		label_sex = new myLabel("性别        ");
		panel.add(label_sex);

		tf_sex = new JTextField(10);
		panel.add(tf_sex);

		label_age = new myLabel("年龄        ");
		panel.add(label_age);

		tf_age = new JTextField(10);
		panel.add(tf_age);

		label_info = new myLabel("简介        ");
		panel.add(label_info);

		tf_info = new JTextField(10);
		panel.add(tf_info);

		label_email = new myLabel("邮箱        ");
		panel.add(label_email);

		tf_email = new JTextField(10);
		panel.add(tf_email);

		ensureButton = new myButton("  提交  ");
		ensureButton.addActionListener(this);
		panel.add(ensureButton);

		resetButton = new myButton("  重置  ");
		resetButton.addActionListener(this);
		panel.add(resetButton);
		//设置窗体属性
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(screenSize.width/2-200, screenSize.height/2-160, 400, 320);
	}

	/**
	 * 事件监听器
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(resetButton)) {// 若点击重置按钮
			creatEmpty();//重置面板
		} else {// 若点击提交按钮
			if (checkData()) {// 数据合理时,生成对象
				User aUser = new User(tf_id.getText().trim(), String.valueOf(tf_pass.getPassword()),
						tf_name.getText().trim(), tf_sex.getText().trim(), Integer.parseInt(tf_age.getText().trim()),
						tf_info.getText().trim(), tf_email.getText().trim());
				if(new JdbcAdapter().insertRegister(aUser)){//如果注册用户成功存入数据库
				this.setVisible(false);//隐藏并关闭该窗体
				this.dispose();
				}
			} else
				JOptionPane.showMessageDialog(null, "注册失败！！");
		}
	}

	/**
	 * 重置面板
	 */
	private void creatEmpty() {
		tf_id.setText("");
		tf_pass.setText("");
		tf_ensurepass.setText("");
		tf_name.setText("");
		tf_email.setText("");
	}

	/**
	 * 检验输入数据是否合法
	 */
	private boolean checkData() {
		// 账户长度在1-16之内，输入的密码长度在6-16之内，两次输入的密码相同，昵称不为空，性别为男或女，年龄在0-200之间，所填邮箱格式正确，则返回真值
		boolean idFlag = tf_id.getText().trim().length() >= 1 && tf_id.getText().trim().length() <= 16;
		String pass = String.valueOf(tf_pass.getPassword());
		String ensurepass = String.valueOf(tf_ensurepass.getPassword());
		boolean passFlag = pass.length() >= 6 && pass.length() <= 16 && pass.equals(ensurepass);
		boolean nameFlag = tf_name.getText().trim().length() >= 1 && tf_name.getText().trim().length() <= 16 ;
		boolean sexFlag = tf_sex.getText().trim().equals("男") || tf_sex.getText().trim().equals("女");
		boolean ageFlag = false;
		try {
			int age = Integer.parseInt(tf_age.getText().trim());
			if(age > 0 && age < 200)
				ageFlag = true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "年龄输入错误");
		}
		Pattern pattern = Pattern.compile(
				"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher matcher = pattern.matcher(tf_email.getText().trim());
		boolean emailFlag = matcher.matches();
		return idFlag && passFlag && nameFlag && sexFlag && ageFlag && emailFlag;
	}

}
