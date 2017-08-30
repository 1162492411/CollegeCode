package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import util.RetrieveObject;


public class JF_view_query_jbqk extends JInternalFrame {
	private JPanel panel;// 总面板
	private JLabel label_1, label_2,label_3,label_4, bottomLabel;// 五个标签
	private JComboBox<String> comboBox_1, comboBox_2,comboBox_3;// 三个选择框
	private JTextField textField;//一个文本框
	private JButton exitButton;// 两个按钮
	private String[] list1 = null,list2 = null,list3 = null;// 选择框数据
	private JScrollPane scrollPane;// 滚动面板
	private JTable table;// 数据表格
	
	
	public static void main(String[] args) {
		
	}
	
	public JF_view_query_jbqk(){
		setBounds(0, 0, 635, 355);
		setVisible(true);
		getContentPane().setLayout(null);
		this.setTitle("基本信息数据查询");

		panel = new JPanel();
		panel.setBounds(0, 0, 600, 355);
		panel.setLayout(null);
		

		label_1 = new JLabel("查询类型");
		label_1.setBounds(10, 5, 72, 25);
		panel.add(label_1);

		comboBox_1 = new JComboBox<String>();
		buildComboBox_1();
		comboBox_1.addItemListener(new handleComboBox_1());
		comboBox_1.setBounds(83, 5, 92, 25);
		panel.add(comboBox_1);

		label_2 = new JLabel("字段");
		label_2.setBounds(180, 5, 72, 25);
		panel.add(label_2);

		comboBox_2 = new JComboBox<String>();
		buildComboBox_2();
		comboBox_2.addItemListener(new handleComboBox_2());
		comboBox_2.setBounds(220, 5, 92, 25);
		panel.add(comboBox_2);
		
		label_3 = new JLabel("运算符");
		label_3.setBounds(318, 5, 52, 25);
		panel.add(label_3);
		
		comboBox_3 = new JComboBox<String>();
		buildComboBox_3();
		comboBox_3.addItemListener(new handleComboBox_2());
		comboBox_3.setBounds(375, 5, 52, 25);
		panel.add(comboBox_3);
		
		label_4 = new JLabel("数值");
		label_4.setBounds(431, 5, 52, 25);
		panel.add(label_4);
		
		textField = new JTextField();
		textField.getDocument().addDocumentListener(new handleTextField());
		textField.setBounds(467, 5, 52, 25);
		panel.add(textField);

		exitButton = new JButton("退出");
		exitButton.setBounds(524, 5, 72, 25);
		panel.add(exitButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 585, 240);
		panel.add(scrollPane);

		table = new JTable();
		buildTable();
		scrollPane.setViewportView(table);

		bottomLabel = new JLabel("共有数据【0】条。");
	//	buildBottomLabel();
		bottomLabel.setBounds(10, 280, 200, 25);
		panel.add(bottomLabel);
		getContentPane().add(panel);
		
	}

	/**
	 * 初始化选择框1
	 */
	private void buildComboBox_1() {
		comboBox_1.removeAllItems();
		list1 = new String[]{"学生信息","教师信息"};
		for (String item : list1) {
			comboBox_1.addItem(item);
		}	
	}
	
	/**
	 * 选择框1的监听器
	 */
	class handleComboBox_1 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			buildComboBox_2();		
		}		
	}
	
	/**
	 * 初始化选择框2
	 */
	private void buildComboBox_2() {
		comboBox_2.removeAllItems();//使用枚举或map作为关键字是否更安全
		switch (String.valueOf(comboBox_1.getSelectedItem())) {
		case "学生信息":
			list2 = new String[]{"学生编号","班级编号"};
			break;
		case "教师信息":
			list2 = new String[]{"教师编号","班级编号"};
			break;
		default:
			list2 = new String[]{};
			break;
		}
		for (String item : list2) {
			comboBox_2.addItem(item);
		}	
	}
	
	/**
	 * 第二个选择框及第三个选择框的监听器
	 */
	class handleComboBox_2 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			buildTable();
		}
	}
	
	/**
	 * 初始化选择框3
	 */
	private void buildComboBox_3() {
		list3 = new String[]{"like",">","<",">=","<="};
		for (String item : list3) {
			comboBox_3.addItem(item);
		}
	}
	
	/**
	 * 文本框的监听器
	 */
	class handleTextField implements DocumentListener{
		public void insertUpdate(DocumentEvent e) {	
			buildTable();
		}
		public void removeUpdate(DocumentEvent e) {
		}
		public void changedUpdate(DocumentEvent e) {
			buildTable();
		}	
	}
	/**
	 * 初始化表格
	 */
	private void buildTable() {
		String sqlStr = null;	//指定的sql'语句
		String[] header = null;//表头
		//根据第1、2选择框的值指定不同的sql语句
		if (comboBox_1.getSelectedItem().equals("学生信息")) {//若选择了学生信息
			header = new String[]{"学生编号","班级名称","学生姓名","性别","年龄","家庭住址","联系电话"};
			sqlStr = "select * from tb_student ";//查询学生信息表
			if (comboBox_2.getSelectedItem().equals("学生编号")) {//根据学生编号
				sqlStr = sqlStr + "where stuID ";
			}
			else if(comboBox_2.getSelectedItem().equals("班级编号")){//根据班级编号
				sqlStr = sqlStr + "where classID ";
			}		
		}
		else if(comboBox_1.getSelectedItem().equals("教师信息")){//若选择了教师信息
			header = new String[]{"教师编号","班级名称","教师姓名","性别","教师职称","教师等级"};
			sqlStr = "select * from tb_teacher ";
			if (comboBox_2.getSelectedItem().equals("教师编号")) {//根据教师编号
				sqlStr = sqlStr + "where teaID ";
			}
			else if (comboBox_2.getSelectedItem().equals("班级编号")) {//根据班级编号
				sqlStr  = sqlStr + "where classID ";
			}
		}
		//开始对运算符和指定值处理
		String ys = String.valueOf(comboBox_3.getSelectedItem());//获取选择的运算符
		String value = textField.getText().replace(" ", "");//获取文本框的值
		if (ys.equals("like")) {
			sqlStr = sqlStr + ys + "'" + value + "'";
		}
		else{
			sqlStr =sqlStr + ys + value ;
		}
		//根据指定的sql语句开始进行查询并填入表格
		table.setModel(new RetrieveObject().getTableModel(header, sqlStr));	
	}
}
