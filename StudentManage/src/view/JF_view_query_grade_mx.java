package view;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import util.RetrieveObject;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class JF_view_query_grade_mx extends JInternalFrame {
	private JPanel panel;// 总面板
	private JLabel label_1, label_2, bottomLabel;// 三个标签
	private JComboBox<String> comboBox_1, comboBox_2;// 两个选择框
	private JButton exitButton;// 两个按钮
	private ArrayList<String> kindIDList = null, kindNameList = null, classIDList = null, classNameList = null;// 选择框数据
	private JScrollPane scrollPane;// 滚动面板
	private JTable table;// 数据表格

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new JF_view_query_grade_mx().setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public JF_view_query_grade_mx() {
		setBounds(0, 0, 635, 355);
		setVisible(true);
		getContentPane().setLayout(null);
		this.setTitle("考试成绩班级明细查询");

		panel = new JPanel();
		panel.setBounds(0, 0, 600, 355);
		panel.setLayout(null);
		

		label_1 = new JLabel("考试类别");
		label_1.setBounds(10, 10, 72, 15);
		panel.add(label_1);

		comboBox_1 = new JComboBox<String>();
		buildComboBox_1();
		comboBox_1.addItemListener(new handleComboBox_1());
		comboBox_1.setBounds(83, 7, 92, 21);
		panel.add(comboBox_1);

		label_2 = new JLabel("所属班级");
		label_2.setBounds(180, 10, 72, 15);
		panel.add(label_2);

		comboBox_2 = new JComboBox<String>();
		buildComboBox_2();
		comboBox_2.addItemListener(new handleComboBox_2());
		comboBox_2.setBounds(246, 7, 125, 21);
		panel.add(comboBox_2);

		exitButton = new JButton("退出");
		exitButton.setBounds(380, 6, 72, 23);
		panel.add(exitButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 35, 565, 240);
		panel.add(scrollPane);

		table = new JTable();
		buildTable();
		scrollPane.setViewportView(table);

		bottomLabel = new JLabel("共有数据【0】条。");
		buildBottomLabel();
		bottomLabel.setBounds(10, 280, 200, 25);
		panel.add(bottomLabel);
		getContentPane().add(panel);
	}


	/**
	 * 初始化选择框1
	 */
	private void buildComboBox_1() {
		RetrieveObject aObject = new RetrieveObject();
		kindIDList = aObject.getTableCollection("select KindID from tb_examKinds");
		kindNameList = aObject.getTableCollection("select KindName from tb_examKinds");
		for (String kindName : kindNameList) {
			comboBox_1.addItem(kindName);
		}
	}
	
	/**
	 * 第一个选择框的监听器
	 */
	class handleComboBox_1 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			buildTable();
			buildBottomLabel();
		}		
	}

	/**
	 * 初始化选择框2
	 */
	private void buildComboBox_2() {
		RetrieveObject aObject = new RetrieveObject();
		classIDList = aObject.getTableCollection("select classID from tb_class");
		classNameList = aObject.getTableCollection("select className from tb_class");
		for (String className : classNameList) {
			comboBox_2.addItem(className);
		}
	}
	
	/**
	 * 第二个选择框的监听器
	 */
	class handleComboBox_2 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			buildTable();
			buildBottomLabel();
		}		
	}

	/**
	 * 初始化表格
	 */
	private void buildTable() {
		RetrieveObject aObject = new RetrieveObject();
		// 先获取所有科目
		ArrayList<String> subjectList = aObject.getTableCollection("select subject from tb_subject");
		String[] tableHeadr = new String[subjectList.size() + 2];// 初始化表头
		tableHeadr[0] = "学生编号";
		tableHeadr[1] = "学生姓名";
		for (int i = 2; i < tableHeadr.length; i++) {// 将科目填入表头
			tableHeadr[i] = subjectList.get(i - 2);
		}
//第一种方法
//		// 初始化前两列
//		String sqlStr = "select a.stuID,a.stuName from tb_student AS a LEFT JOIN tb_grade_sub AS b  ON a.stuID = b.stuID where classID = '" + classIDList.get(comboBox_2.getSelectedIndex()) + "' group by a.stuID,a.stuName";
//		DefaultTableModel aModel = aObject.getTableModel(tableHeadr, sqlStr);
//		table.setModel(aModel);
//		//根据前两列获取各科目成绩
//		for (int i = 0; i < table.getRowCount(); i++) {// 对每行表格中的数据进行循环操作
//			ArrayList<String> result = new ArrayList<String>();// 存储成绩
//			for (int j = 0; j < subjectList.size(); j++) {// 将取到的成绩直接填入表格
//				//这样每次调用函数获取数据只在ArrayList里放入了一个值，效率非常低！！！
//				//根本原因在于getTableCollection()只能返回一个字段的所有值，需要改写
//				table.setValueAt(
//						aObject.getTableCollection("select sum(CASE WHEN code='" + (j + 1)
//								+ "' THEN grade ELSE 0 END) from tb_grade_sub where stuID = '" + table.getValueAt(i, 0)
//								+ "' and kindID = '" + kindIDList.get(comboBox_1.getSelectedIndex()) + "'").get(0),
//						i, j + 2);
//			}
//		}
		
//第二种方法，直接返回一整行数据再进行填充
		String sqlStr = "select a.stuID,a.stuName,";
		for (int i = 0; i < subjectList.size() - 1; i++) {//根据科目数量动态设置查询的科目成绩
			sqlStr = sqlStr + "sum(case when b.code = '" + i + "' then b.grade else 0 end),";
		}
		//设置查询最后一个科目的成绩
		sqlStr = sqlStr + "sum(case when b.code = '" +subjectList.size() +"' then b.grade else 0 end) ";
		//设置其他查询限制条件,同等连接，筛选，分组
		sqlStr = sqlStr + "from tb_student AS a LEFT JOIN tb_grade_sub AS b "
				+ "ON a.stuID = b.stuID "
				+ "where a.classID = '" + classIDList.get(comboBox_2.getSelectedIndex()) + "'"
				+ "and kindID = '" + kindIDList.get(comboBox_1.getSelectedIndex()) + "'"
				+ " group by a.stuID,a.stuName";
		table.setModel(new RetrieveObject().getTableModel(tableHeadr, sqlStr));
	}

	/**
	 * 初始化底部标签
	 */
	private void buildBottomLabel() {
		bottomLabel.setText("共有数据【" + table.getRowCount() +"】条。");
	}


}
