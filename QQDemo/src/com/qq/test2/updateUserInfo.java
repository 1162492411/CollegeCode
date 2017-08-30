package com.qq.test2;
/**
 * 修改用户信息窗体
 */
import java.awt.Dimension;
/**
 * 修改信息界面
 */
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.qq.model.User;
import com.qq.myComponent.myButton;
import com.qq.myComponent.myLabel;
import com.qq.util.JdbcAdapter;

@SuppressWarnings("serial")
public class updateUserInfo extends JFrame implements ActionListener {
	private JPanel panel;
	private myLabel label_id, label_name, label_sex, label_age, label_info, label_email;
	private JTextField tf_id, tf_name, tf_sex, tf_age, tf_info, tf_email;
	private myButton ensureButton, exitButton;
	private String mainqq;

	public updateUserInfo(String mainQQ) {
		mainqq = mainQQ;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕大小
		getContentPane().setLayout(null);

		panel = new JPanel();// 初始化面板
		panel.setLayout(new FlowLayout());
		panel.setBounds(20, 20, 260, 210);
		getContentPane().add(panel);

		label_id = new myLabel("账户    ");
		panel.add(label_id);

		tf_id = new JTextField(16);
		tf_id.setText(mainQQ);
		tf_id.setEditable(false);
		panel.add(tf_id);

		label_name = new myLabel("昵称    ");
		panel.add(label_name);

		tf_name = new JTextField(16);
		panel.add(tf_name);

		label_sex = new myLabel("性别    ");
		panel.add(label_sex);

		tf_sex = new JTextField(16);
		panel.add(tf_sex);

		label_age = new myLabel("年龄   ");
		panel.add(label_age);

		tf_age = new JTextField(16);
		panel.add(tf_age);

		label_info = new myLabel("简介    ");
		panel.add(label_info);

		tf_info = new JTextField(16);
		panel.add(tf_info);

		label_email = new myLabel("邮箱    ");
		panel.add(label_email);

		tf_email = new JTextField(16);
		panel.add(tf_email);

		ensureButton = new myButton("  提交  ");
		ensureButton.addActionListener(this);
		panel.add(ensureButton);

		exitButton = new myButton("  退出  ");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(exitButton);
		
		builtUserInfo();
		//设置窗体属性
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("用户信息修改界面");
		setBounds(screenSize.width/2-150, screenSize.height/2-125, 300, 250);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 初始化用户信息
	 */
	private void builtUserInfo(){
		String[] info = new JdbcAdapter().loadUserInfo(mainqq);
		tf_name.setText(info[0]);
		tf_sex.setText(info[1]);
		tf_age.setText(info[2]);
		tf_info.setText(info[3]);
		tf_email.setText(info[4]);
	}
	/**
	 * 检查所填数据有效性
	 */
	private boolean checkData() {
		// 账户长度在1-16之内，昵称不为空，性别为男或女，年龄在0-200之间，所填邮箱格式正确，则返回真值
		boolean idFlag = tf_id.getText().trim().length() >= 1 && tf_id.getText().trim().length() <= 16;
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
		return idFlag  && nameFlag && sexFlag && ageFlag && emailFlag;
	}

	//事件处理器
	public void actionPerformed(ActionEvent e) {//点击了确认按钮,生成用户对象
		User aUser = new User(tf_id.getText().trim(), "",
				tf_name.getText().trim(), tf_sex.getText().trim(), Integer.parseInt(tf_age.getText().trim()),
				tf_info.getText().trim(), tf_email.getText().trim());
		if (checkData() && new JdbcAdapter().updateUserInfo(aUser)) {//若所填数据正确且更改信息成功
			this.setVisible(false);//隐藏并关闭窗体
			this.dispose();
			new mainUser(mainqq).setVisible(true);;//启动用户主界面
		} else//否则提示用户
			JOptionPane.showMessageDialog(null, "所填的数据存在错误，请认真填写！");
	}

}
