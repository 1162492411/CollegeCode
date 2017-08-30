package com.qq.test2;

/**
 * 修改密码窗体
 */
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.qq.model.User;
import com.qq.myComponent.myButton;
import com.qq.myComponent.myLabel;
import com.qq.util.JdbcAdapter;

public class updatePass extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;// 面板
	private myLabel label_id, label_old_pass, label_new_pass, label_ensure_pass;// 提示文字
	private JTextField tf_id;// 账户
	private JPasswordField tf_old_pass, tf_new_pass, tf_ensure_pass;// 新密码和确认密码
	private myButton ensureButton, exitButton;// 确认和放弃按钮
	private String mainqq;// 要修改的账户

	public updatePass(String mainQQ) {
		mainqq = mainQQ;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕大小
		getContentPane().setLayout(null);

		panel = new JPanel();// 初始化面板
		panel.setLayout(new FlowLayout());
		panel.setBounds(20, 20, 260, 210);
		getContentPane().add(panel);

		label_id = new myLabel("  账户        ");
		panel.add(label_id);

		tf_id = new JTextField(12);
		tf_id.setText(mainQQ);
		tf_id.setEditable(false);
		panel.add(tf_id);

		label_old_pass = new myLabel("旧密码       ");
		panel.add(label_old_pass);

		tf_old_pass = new JPasswordField(12);
		panel.add(tf_old_pass);

		label_new_pass = new myLabel("新密码       ");
		panel.add(label_new_pass);

		tf_new_pass = new JPasswordField(12);
		panel.add(tf_new_pass);

		label_ensure_pass = new myLabel("确认密码    ");
		panel.add(label_ensure_pass);

		tf_ensure_pass = new JPasswordField(12);
		panel.add(tf_ensure_pass);

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
		// 设置窗体属性
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("用户密码修改界面");
		setBounds(screenSize.width / 2 - 150, screenSize.height / 2 - 125, 300, 250);
		setResizable(false);
		setVisible(true);
	}

	// 事件监听器
	public void actionPerformed(ActionEvent e) {// 点击确定按钮
		User aUser = new User(mainqq, String.valueOf(tf_old_pass.getPassword()));
		// 若旧密码正确，所填数据有效且修改密码成功
		if (new JdbcAdapter().validateLogin(aUser) && checkData()
				&& new JdbcAdapter().updatePass(String.valueOf(tf_new_pass.getPassword()), mainqq)) {
			this.setVisible(false);// 关闭并停止当前界面
			this.dispose();
			new mainUser(mainqq);// 启动主用户界面
		} else// 修改密码失败时提示用户
			JOptionPane.showMessageDialog(null, "所填数据有误，请重新核对");
	}

	// 检验数据有效性
	private boolean checkData() {
		// 所填的新密码长度大于6且两次新密码输入相同时返回真值
		return String.valueOf(tf_new_pass.getPassword()).length() >= 6
				&& String.valueOf(tf_new_pass.getPassword()).equals(String.valueOf(tf_ensure_pass.getPassword()));
	}

}
