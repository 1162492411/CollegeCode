package com.qq.test2;
/**
 * 添加好友界面
 */
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.qq.myComponent.myButton;
import com.qq.util.JdbcAdapter;

public class addFriend extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private String mainqq;
	private JPanel topPanel;//顶部面板
	private JTextField tf_search;//搜索条件输入框
	private myButton searchButton,addButton;//搜索及添加按钮
	private JScrollPane scrollPane;//滚动面板
	private JTable table;//结果表格
	
	public addFriend(String mainQQ){
		mainqq = mainQQ;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();//获取屏幕大小
		getContentPane().setLayout(null);
		//设置顶部面板
		topPanel = new JPanel(new FlowLayout());
		topPanel.setBounds(0, 0, 400, 30);
		//设置搜索条件输入框
		tf_search = new JTextField(20);
		topPanel.add(tf_search);
		//设置搜索按钮
		searchButton = new myButton("  查找  ");
		searchButton.addActionListener(this);
		topPanel.add(searchButton);
		//设置添加按钮
		addButton = new myButton("  添加  ");
		addButton.addActionListener(this);
		topPanel.add(addButton);
		getContentPane().add(topPanel);
		//设置滚动面板
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 40, 400, 260);
		//设置搜索结果列表
		table = new JTable();
		buildTable();
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);
		//设置窗体其他属性
		setTitle("添加好友");
		setBounds(screenSize.width/2-200, screenSize.height/2-150, 400, 300);
		setResizable(false);
		setVisible(true);
	}

	//初始化搜索结果
	private void buildTable() {
		String[] header = {"编号","昵称","性别","年龄","简介"};
		table.removeAll();//首先清空结果列表，再生成查询好友sql语句
		String sqlStr = "select userID,userName,userSex,userAge,userInfo from tb_user where userID like '%" + tf_search.getText() + "%'";
		table.setModel(new JdbcAdapter().getTableModel(header,sqlStr));		
	}
	//事件处理器
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(searchButton))//如果点击搜索按钮
			buildTable();//初始化搜索结果
		else{//如果点击添加按钮，执行添加好友方法
			if (new JdbcAdapter().addFriend(mainqq,table.getValueAt(table.getSelectedRow(), 0).toString())) {//如果添加朋友成功
				this.setVisible(false);//隐藏并关闭窗体
				this.dispose();
				new mainUser(mainqq).setVisible(true);//启动主用户界面
			}
			else //如果添加好友失败，提示用户
				JOptionPane.showMessageDialog(null, "添加好友失败！！");
		}	
	}

}
