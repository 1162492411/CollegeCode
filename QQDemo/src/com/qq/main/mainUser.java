package com.qq.main;

/**
 * 用户主界面
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.qq.myComponent.myButton;
import com.qq.util.JdbcAdapter;

public class mainUser extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mainqq;// 用户id
	private JScrollPane scrollPane;// 滚动界面，容纳好友
	private JList<String> list;// 好友列表
	private JPanel panel;// 面板
	private myButton addButton, deleteButton, chatButton, chatAllButton, updateUserInfoButton, updateUserPassButton;// 功能按钮

	/**
	 * 构造函数
	 * 
	 * @throws IOException
	 */
	public mainUser(String mainQQ) {
		mainqq = mainQQ;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕大小
		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();// 设置滚动面板
		scrollPane.setBounds(10, 5, 280, 325);
		list = new JList<String>();// 设置好友列表
		buildList();
		scrollPane.setViewportView(list);
		getContentPane().add(scrollPane);

		panel = new JPanel(new FlowLayout());// 设置面板
		panel.setBounds(10, 330, 280, 80);

		// 设置添加好友按钮
		addButton = new myButton("  添加  ", new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/addFriend.png")));
		addButton.setBackground(Color.WHITE);
		addButton.addActionListener(this);
		panel.add(addButton);

		// 设置删除好友按钮
		deleteButton = new myButton("  删除  ",
				new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/deleteFriend.png")));
		deleteButton.addActionListener(this);
		panel.add(deleteButton);

		// 设置聊天按钮
		chatButton = new myButton("  聊天  ",
				new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/chatToPerson.png")));
		chatButton.addActionListener(this);
		panel.add(chatButton);

		// 设置群聊按钮
		chatAllButton = new myButton("  群聊  ",
				new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/chatToPerson.png")));
		chatAllButton.addActionListener(this);
		panel.add(chatAllButton);

		// 设置修改信息按钮
		updateUserInfoButton = new myButton("  修改  ",
				new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/updateUserInfo.png")));
		updateUserInfoButton.addActionListener(this);
		panel.add(updateUserInfoButton);

		// 设置改密按钮
		updateUserPassButton = new myButton("  改密  ",
				new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/updateUserPass.png")));
		updateUserPassButton.addActionListener(this);
		panel.add(updateUserPassButton);

		getContentPane().add(panel);


		// 设置窗体其他属性
		setTitle("用户" + mainQQ + "界面");
		setBounds(screenSize.width - 350, 100, 300, 450);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * 初始化好友列表
	 */
	void buildList() {
		list.removeAll();// 首先清除所有列表内容
		// 根据用户id查询好友
		String sqlStr = "select friendQQ from tb_friend where mainQQ = '" + mainqq + "'";
		list.setModel(new JdbcAdapter().getListModel(sqlStr));
	}

	// 删除好友
	private void deleteFriend() {
		if (JOptionPane.showConfirmDialog(null, "是否删除此好友") == JOptionPane.YES_NO_OPTION) {
			if (new JdbcAdapter().DeleteObject(mainqq,String.valueOf(list.getSelectedValue())))// 成功删除时
				buildList();// 初始化好友
			else// 未成功删除时，提示用户
				JOptionPane.showMessageDialog(null, "删除好友失败！");
		}
	}

	// 事件监听器
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(addButton))// 点击添加好友按钮
			new addFriend(mainqq);// 启动添加好友窗体
		else if (e.getSource().equals(deleteButton))// 点击删除好友按钮
			deleteFriend();// 执行删除好友方法
		else if (e.getSource().equals(chatButton))// 点击聊天按钮
			new chatToPerson(mainqq, list.getSelectedValue()).setVisible(true);// 启动聊天窗体
		else if (e.getSource().equals(updateUserInfoButton))// 点击修改信息按钮
			new updateUserInfo(mainqq); // 启动修改信息窗体
		else if (e.getSource().equals(updateUserPassButton))// 点击改密按钮
			new updatePass(mainqq);// 启动改密窗体
		// 隐藏并关闭当前窗体
		if (!e.getSource().equals(deleteButton) && ! e.getSource().equals(chatButton)) {// 设置例外情况
			this.setVisible(false);
			this.dispose();
		}
	}
}
