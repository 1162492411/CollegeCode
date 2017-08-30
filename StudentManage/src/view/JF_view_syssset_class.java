package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import model.Obj_class;
import util.JdbcAdapter;
import util.RetrieveObject;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class JF_view_syssset_class extends JInternalFrame{
	private JTable table;
	private JTextField textField_1,textField_2,textField_3;
	private JComboBox<String> comboBox;
 	private ArrayList<String> gradeIdList = null,gradeNameList = null,classIdList = null,classNameList = null;


	public JF_view_syssset_class() {
		setBounds(0, 0, 635, 355);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		setTitle("班级信息设置"); 
		JLabel label = new JLabel("选择年级");
		label.setBounds(10, 10, 100, 20);
		getContentPane().add(label);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(80, 10, 60, 20);
		buildComboBox();
		getContentPane().add(comboBox);
		
		JButton addButton = new JButton("添加");
		addButton.addActionListener(new handleAdd());
		addButton.setBounds(150, 10, 70, 20);
		getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.addActionListener(new handleDelete());
		deleteButton.setBounds(230, 10, 70, 20);
		getContentPane().add(deleteButton);
		
		JButton saveButton = new JButton("存盘");
		saveButton.addActionListener(new handleAdd());
		saveButton.setBounds(310, 10, 70, 20);
		getContentPane().add(saveButton);
		
		JButton exitButton = new JButton("退出");
 		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(390, 10, 70, 20);
		getContentPane().add(exitButton);
		
		table = new JTable();
		buildTable();
		table.addMouseListener(new handleTable());
		getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 50, 400, 200);
		scrollPane.setViewportView(table);
		getContentPane().add(scrollPane);  
		
		JLabel label_1 = new JLabel("班级名称");
		label_1.setBounds(65, 255, 72, 15);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("班级编号");
		label_2.setBounds(65, 275, 72, 15);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("年级名称");
		label_3.setBounds(65, 295, 72, 15);
		getContentPane().add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(140, 255, 106, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(140, 275, 106, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(140, 295, 106, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);

		comboBox.addItemListener(new handleComboBox());
	}



	/**
	 * 初始化表格
	 */
	private void buildTable() {
		String[] name = {"班级编号","年级编号","班级名称"};
		String sqlStr = "select * from tb_class";
		//调用getTableModel获取一个表格模型实例
		DefaultTableModel tableModel = new RetrieveObject().getTableModel(name, sqlStr);
		table.setModel(tableModel);
	}
	
	/**
	 * 初始化选择框
	 */
	private void buildComboBox() {
		gradeIdList = new RetrieveObject().getTableCollection("select gradeID from tb_grade");
		gradeNameList =  new RetrieveObject().getTableCollection("select gradeName from tb_grade");
		for (String k : gradeNameList) {
			comboBox.addItem(k);
		}	
	}
	
	/**
	 * 选项表发生变化的事件处理
	 */
	class handleComboBox implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			table.removeAll();//首先清除信息
			int index = comboBox.getSelectedIndex();
			if(index < 0) return ;
			String gid = gradeIdList.get(index);
			System.out.println(gid);
			String[] name = {"班级编号","年级编号","班级名称"};
			String sqlStr = "select * from tb_class where gradeID = '" + gid + "'";
			DefaultTableModel aModel = new RetrieveObject().getTableModel(name, sqlStr);
			table.setModel(aModel);
		}	
	}
	/**
	 * 选择表格一行记录时的事件处理
	 */
 	class handleTable extends MouseAdapter{
 		public void mouseClicked(java.awt.event.MouseEvent e){
 			System.out.println("选中了一行数据");
 			int selectRow = table.getSelectedRow();
 			if (selectRow < 0) return ;
// 			将数据填入文本框
 			System.out.println("开始填充数据");
 			textField_1.setText(table.getValueAt(selectRow, 0).toString());
 			textField_2.setText(table.getValueAt(selectRow, 1).toString());
 			textField_3.setText(table.getValueAt(selectRow, 2).toString());
 			System.out.println("数据填充完毕！");
 		}
 	}
 	
 	/**
 	 * 刷新界面
 	 */
 	private void refresh() {
 		new handleComboBox().itemStateChanged(new ItemEvent(comboBox, 0, comboBox.getSelectedItem(), ItemEvent.SELECTED));
		comboBox.repaint();
		table.repaint();
		textField_1.repaint();
		textField_2.repaint();
		textField_3.repaint();
	}
 	/**
 	 * 处理增加按钮的事件
 	 */
	class handleAdd implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Obj_class aObj_class = new Obj_class(textField_1.getText(),textField_2.getText(),textField_3.getText());
			if(new JdbcAdapter().InsertOrUpdateClass(aObj_class)){
				creatEmptyPane();
				refresh();
			}
			else
				JOptionPane.showMessageDialog(null, "操作失败！", "系统提示", JOptionPane.INFORMATION_MESSAGE);
				
		}
	}

	/**
	 * 处理删除按钮的事件
	 */
	class handleDelete implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String sqlDel = "delete from tb_class where classID = '" + textField_1.getText().replace(" ", "") + "'";
			if (new JdbcAdapter().DeleteObject(sqlDel)) {
				refresh();
			}
		}
	}

	/**
	 * 初始化下方控件
	 */
	private void creatEmptyPane(){
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
	}
	
	public static void main(String args[]){
		new JF_view_syssset_class().setVisible(true);
	}




}
