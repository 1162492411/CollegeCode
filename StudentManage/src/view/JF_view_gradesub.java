package view;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Obj_grade_sub;
import util.JdbcAdapter;
import util.RetrieveObject;

@SuppressWarnings("serial")
public class JF_view_gradesub extends JInternalFrame {
	private JPanel panel;// 上面板
	private JLabel  label_2, label_3;// 上面板标签
	private JComboBox<String> comboBox_1, comboBox_2;// 上面板选择框
	private JButton addButton, deleteButton, exitButton;// 上面板退出按钮
	private JSplitPane splitPane;// 下面版
	private JScrollPane scrollPane_1, scrollPane_2;// 下面版滚动面板
	private JTable table_1, table_2;// 下面版表格
	private ArrayList<String> codeList = null,subjectList=null,examKindsIdList = null, examKindsNameList = null, classIdList = null,
			classNameList = null, gradeSubList = null;// 班级编号和班级名称

	public static void main(String[] args) {
		new JF_view_gradesub().setVisible(true);
	}

	public JF_view_gradesub() {
		setBounds(0, 0, 635, 355);
		getContentPane().setLayout(null);
		setVisible(true);
		setTitle("学生考试成绩信息管理");

		panel = new JPanel();
		panel.setBounds(0, 0, 600, 35);
		panel.setLayout(null);
		getContentPane().add(panel);
		// 设置上面板


		label_2 = new JLabel("种类");
		label_2.setBounds(122, 5, 72, 25);
		panel.add(label_2);

		comboBox_1 = new JComboBox<>();
		buildComboBox_1();
		comboBox_1.addItemListener(new handleComboBox_1());
		comboBox_1.setBounds(158, 5, 58, 25);
		panel.add(comboBox_1);

		label_3 = new JLabel("班级");
		label_3.setBounds(221, 5, 72, 25);
		panel.add(label_3);

		comboBox_2 = new JComboBox<>();
		buildComboBox_2();
		comboBox_2.addItemListener(new handleComboBox_2());
		comboBox_2.setBounds(253, 5, 125, 25);
		panel.add(comboBox_2);

		addButton = new JButton("增");
		addButton.addActionListener(new handleAdd());
		addButton.setBounds(383, 5, 54, 25);
		panel.add(addButton);

		deleteButton = new JButton("删");
		deleteButton.addActionListener(new handleDelete());
		deleteButton.setBounds(442, 5, 54, 25);
		panel.add(deleteButton);

		exitButton = new JButton("退");
 		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(500, 5, 54, 25);
		panel.add(exitButton);

		// 设置下面版
		table_1 = new JTable();
		buildTable_1();
		table_1.addMouseListener(new handleTable_1());
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(table_1);

		table_2 = new JTable();
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportView(table_2);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, false, scrollPane_1, scrollPane_2);
		splitPane.setBounds(15, 35, 600, 290);
		getContentPane().add(splitPane);
		splitPane.setDividerLocation(0.5);// 设置分割比例
	}

	/**
	 * 初始化第一个选择框
	 */
	private void buildComboBox_1() {
		examKindsIdList = new RetrieveObject().getTableCollection("select kindID from tb_examkinds");
		examKindsNameList = new RetrieveObject().getTableCollection("select kindName from tb_examkinds");
		for (String examKindsName : examKindsNameList) {
			comboBox_1.addItem(examKindsName);
		}
	}
	
	/**
	 * 第一个选择框的监听器--初始化表格2
	 */
	class handleComboBox_1 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			buildTable_2();
		}		
	}
	

	/**
	 * 初始化第二个选择框
	 */
	private void buildComboBox_2() {
		classIdList = new RetrieveObject().getTableCollection("select classID from tb_class");
		classNameList = new RetrieveObject().getTableCollection("select className from tb_class");
		for (String className : classNameList) {
			comboBox_2.addItem(className);
		}
	}
	
	/**
	 * 第二个选择框的监听器--初始化表格1
	 */
	class handleComboBox_2 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			buildTable_1();
		}		
	}
	
	/**
	 * 初始化表格1
	 */
	private void buildTable_1() {
		table_1.removeAll();// 首先清除信息
		classIdList = new RetrieveObject().getTableCollection("select classID from tb_class");//获取班级编号列表
		String cid = classIdList.get(comboBox_2.getSelectedIndex());
		String[] name = { "学生编号", "班级编号", "学生姓名", "性别", "年龄", "家庭住址", "联系电话" };
		String sqlStr = "select * from tb_student where classID = '" + cid + "'";
		DefaultTableModel aModel = new RetrieveObject().getTableModel(name, sqlStr);
		table_1.setModel(aModel);
	}

	/**
	 * 表格1的监听器--初始化表格2
	 */
	class handleTable_1 extends MouseAdapter {
		public void mouseClicked(java.awt.event.MouseEvent e) {
			buildTable_2();
		}
	}
	/**
	 * 初始化表格2
	 */
	private void buildTable_2(){
		//设置表头
		String[] name = { "学生编号", "学生姓名", "考试类别", "考试科目", "考试成绩"};
//第一种方法，比较麻烦,查询次数多
		//根据指定条件查询成绩表，并将其填充进表格2
//		String sqlStr = "select * from tb_grade_sub where stuID = '"
//				+ table_1.getValueAt(table_1.getSelectedRow(), 0) + "' and kindID = '"
//				+ examKindsIdList.get(comboBox_1.getSelectedIndex()) + "'";
//		DefaultTableModel aModel = new RetrieveObject().getTableModel(name, sqlStr);
//		table_2.setModel(aModel);
//		//将表格2考试类型替换为选择框1中的文字
//		table_2.setValueAt(comboBox_1.getSelectedItem(), 0, 2);
//		//根据表格2获取的考试科目编号获取考试科目名称
//		String sqlStr2 = "select a.subject from tb_subject AS a,tb_grade_sub AS b where a.code = b.code and b.stuID = '"
//				 + table_1.getValueAt(table_1.getSelectedRow(), 0) + "'and b.kindID = '"  + examKindsIdList.get(comboBox_1.getSelectedIndex()) + "'";
//		subjectList = new RetrieveObject().getTableCollection(sqlStr2);
//	//对表格个别地方进行处理		
//			
//			//将表格2第一行考试科目的编号替换为考试科目名称
//			table_2.setValueAt(subjectList.get(0), 0, 3);
//			//将表格2除第一行的前三列均置空，将表格2除第一行的考试科目的编号替换为考试科目名称
//			for (int i = 1; i < table_2.getRowCount(); i++) {
//				table_2.setValueAt("", i, 0);
//				table_2.setValueAt("", i, 1);
//				table_2.setValueAt("", i, 2);
//				table_2.setValueAt(subjectList.get(i), i, 3);
//			}				
//第二种方法
		codeList = new RetrieveObject().getTableCollection("select code from tb_subject");
		subjectList = new RetrieveObject().getTableCollection("select subject from tb_subject");
		DefaultTableModel aModel = new DefaultTableModel(name, subjectList.size());
		//先设置第一行第1、2、3、4列的数据
		table_2.setModel(aModel);
		String stuid = String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 0));
		String stuname = String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2));
		String kindName = String.valueOf(comboBox_1.getSelectedItem());
		table_2.setValueAt(stuid, 0, 0);
		table_2.setValueAt(stuname, 0, 1);
		table_2.setValueAt(kindName, 0, 2);
		for (int i = 0; i < subjectList.size(); i++) {
			table_2.setValueAt(subjectList.get(i), i, 3);
		}
		//获取所有科目的成绩
		String sqlStr = "select ";
		for (int i = 0; i < subjectList.size() - 1; i++) {
			sqlStr += "sum(case when b.code = '" + (i + 1) + "' then b.grade else 0 end),";
		}
			sqlStr += "sum(case when b.code = '" +subjectList.size() +"' then b.grade else 0 end) ";
			sqlStr += "from tb_student AS a LEFT JOIN tb_grade_sub AS b "
					+ "ON a.stuID = b.stuID "
					+ "where b.stuID = '" + stuid + "' and b.kindId = '" + examKindsIdList.get(comboBox_1.getSelectedIndex()) + "'";
			ArrayList<Object> result = new RetrieveObject().getResultCollection(sqlStr);
		
		for (int i = 0; i < subjectList.size(); i++) {
			table_2.setValueAt(result.get(i), i, 4);
		}
	}

	/**
	 * 处理添加按钮事件--存在问题：在表格中存在但数据库中不存在的记录，在第一次添加时用户界面显示成功但实际失败
	 */
	class handleAdd implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String stuid = String.valueOf(table_2.getValueAt(0, 0));
			String stuname = String.valueOf(table_2.getValueAt(0, 1));
			String kindid = examKindsIdList.get(comboBox_1.getSelectedIndex());
			int count = table_2.getRowCount();
			Obj_grade_sub[] obj_grade_subs = new Obj_grade_sub[count];
			for (int i = 0; i < obj_grade_subs.length; i++) {//将表格2的数据封装到对象数组中
				obj_grade_subs[i] = new Obj_grade_sub(stuid, stuname, kindid, codeList.get(i),Float.parseFloat(String.valueOf(table_2.getValueAt(i, 4))));
			}
			//批量执行操作
			if(new JdbcAdapter().InsertOrUpdate_Obj_grade_sub(obj_grade_subs)){
				System.out.println("操作成功！！！");
			}
			else
				System.out.println("操作失败");
		}
	}
	
	/**
	 * 处理删除按钮事件
	 */
	class handleDelete implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println(table_1.getValueAt(table_1.getSelectedRow(), 0));
			String sqlDel = "delete from tb_grade_sub where stuID = '" 
					+ table_2.getValueAt(0, 0) + "' and kindID = '"
					 + (comboBox_1.getSelectedIndex() + 1) + "' and code = '" + (table_2.getSelectedRow() + 1) + "'";
			if(new JdbcAdapter().DeleteObject(sqlDel)){
				System.out.println("删除成功！！");
			}
			else
				System.out.println("删除失败！！");
		}
	}


}
