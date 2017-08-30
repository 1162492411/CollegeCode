package view;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Obj_student;
import util.JdbcAdapter;
import util.RetrieveObject;

 @SuppressWarnings("serial")
public class JF_view_student extends JInternalFrame{
	private JPanel panel,panel_1;
 	private JComboBox<String> comboBox_1,comboBox_2,comboBox_3;
 	private JButton button_1,button_2,button_3,button_4,button_5;
 	private JSplitPane splitPane;
 	private JScrollPane scrollPane;
 	private JTable table;
 	private JLabel label_A,label_B,label_1,label_2,label_3,label_4,label_5,label_6,label_7;
 	private JTextField textField_1,textField_2,textField_3,textField_5,textField_6,textField_7;
 	private ArrayList<String> gradeIdList = null,gradeNameList = null,classIdList = null,classNameList = null;
 	/**
 	 * Launch the application.
 	 */
 	public static void main(String[] args) {
 		new JF_view_student().setVisible(true);;
 	}

 	/**
 	 * Create the frame.
 	 */
 	public JF_view_student() {
 		setBounds(0, 0, 635, 355);
 		getContentPane().setLayout(null);
 		setVisible(true);
 		this.setTitle("学生基本信息管理");
 		
 		panel = new JPanel();
 		panel.setBounds(0, 0, 600, 35);
 		panel.setLayout(null);
 		getContentPane().add(panel);
 		//设置上半部分
 		label_A = new JLabel("年级");
 		label_A.setBounds(5, 10, 50, 15);
 		panel.add(label_A);
 		
 		comboBox_1 = new JComboBox<String>();
 		comboBox_1.setBounds(40, 7, 80, 21);
 		comboBox_1.addItemListener(new handleComboBox_1());
 		panel.add(comboBox_1);
 		
 		label_B = new JLabel("班级");
 		label_B.setBounds(130, 10, 50, 15);
 		panel.add(label_B);
 		
 		comboBox_2 = new JComboBox<String>();
 		comboBox_2.setBounds(165, 7, 130, 21);
 		comboBox_2.addItemListener(new handleComboBox_2());
 		panel.add(comboBox_2);
 		
 		button_1 = new JButton("刷");
 		button_1.addActionListener(new refresh());
 		button_1.setBounds(310, 6, 50, 23);
 		panel.add(button_1);
 		
 		button_2 = new JButton("增");
 		button_2.addActionListener(new handleButton_2());
 		button_2.setBounds(370, 6, 50, 23);
 		panel.add(button_2);
 		
 		button_3 = new JButton("删");
 		button_3.addActionListener(new handleButton_3());
 		button_3.setBounds(430, 6, 50, 23);
 		panel.add(button_3);
 		
 		button_4 = new JButton("存");
 		button_4.addActionListener(new handleButton_2());
 		button_4.setBounds(490, 6, 50, 23);
 		panel.add(button_4);
 		
 		button_5 = new JButton("退");
 		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
 		button_5.setBounds(550, 6, 50, 23);
 		panel.add(button_5);
 		//设置切割面板
 		splitPane = new JSplitPane();
 		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
 		splitPane.setBounds(0, 35, 600, 330);
 		splitPane.setDividerLocation(0.5);//设置切割面板分割线位置
 		getContentPane().add(splitPane);
 		
 		panel_1 = new JPanel();
 		panel_1.setLayout(null);
 		splitPane.setRightComponent(panel_1);
 		
 		//设置切割面板的下半部分
 		label_1 = new JLabel("学生编号");
 		label_1.setBounds(10, 5, 72, 20);
 		panel_1.add(label_1);
 		
 		textField_1 = new JTextField();
 		textField_1.setBounds(82, 5, 72, 20);
 		panel_1.add(textField_1);
 		textField_1.setColumns(10);
 		
 		label_3 = new JLabel("学生姓名");
 		label_3.setBounds(10, 32, 72, 20);
 		panel_1.add(label_3);
 		
 		textField_3 = new JTextField();
 		textField_3.setBounds(82, 32, 72, 20);
 		panel_1.add(textField_3);
 		textField_3.setColumns(10);
 		
 		label_5 = new JLabel("  年 龄  ");
 		label_5.setBounds(10, 60, 72, 15);
 		panel_1.add(label_5);
 		
 		textField_5 = new JTextField();
 		textField_5.setBounds(82, 57, 72, 20);
 		panel_1.add(textField_5);
 		textField_5.setColumns(10);

 		label_7 = new JLabel("联系电话");
 		label_7.setBounds(10, 85, 72, 20);
 		panel_1.add(label_7);
 		
 		textField_7 = new JTextField();
 		textField_7.setBounds(82, 85, 72, 20);
 		panel_1.add(textField_7);
 		textField_7.setColumns(10);
 		
 		label_2 = new JLabel("班级编号");
 		label_2.setBounds(245, 5, 72, 20);
 		panel_1.add(label_2);
 		
 		textField_2 = new JTextField();
 		textField_2.setBounds(315, 5, 72, 20);
 		panel_1.add(textField_2);
 		textField_2.setColumns(10);
 		
 		label_4 = new JLabel("  性 别 ");
 		label_4.setBounds(245, 35, 72, 20);
 		panel_1.add(label_4);
 		
 		comboBox_3 = new JComboBox<String>();
 		comboBox_3.addItem("男");
 		comboBox_3.addItem("女");
 		comboBox_3.setBounds(315, 35, 72, 20);
 		panel_1.add(comboBox_3);
 		
 		label_6 = new JLabel("家庭住址");
 		label_6.setBounds(245, 60, 72, 20);
 		panel_1.add(label_6);
 		
 		textField_6 = new JTextField();
 		textField_6.setBounds(315, 60, 72, 20);
 		panel_1.add(textField_6);
 		textField_6.setColumns(10);
 		//设置切割面板的上半部分
 		scrollPane = new JScrollPane();
 		
 		table = new JTable();
 		table.addMouseListener(new handleTable());

 		
 		scrollPane.setViewportView(table);
 		splitPane.setLeftComponent(scrollPane);
 		
 		initGrade();

 	}

	/**
 	 * 检索年级信息
 	 */
 	public void initGrade(){
 		//检索年级编号
 		String sqlStr = "select gradeID from tb_grade";
 		gradeIdList = new RetrieveObject().getTableCollection(sqlStr);
 		//检索年级名称
 		String sqlStr2 = "select gradeName from tb_grade";
 		gradeNameList = new RetrieveObject().getTableCollection(sqlStr2);
 		for (String gradename : gradeNameList) {
			comboBox_1.addItem(gradename);
		}	
 }
 	/**
 	 * 第一个列表的事件处理
 	 */
 	class handleComboBox_1 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
	 		comboBox_2.removeAllItems();//首先清空班级信息
	 		int index = comboBox_1.getSelectedIndex();
	 		String sqlStr = "select classID from tb_class where gradeID = '" + gradeIdList.get(index) + "'";
	 		classIdList = new RetrieveObject().getTableCollection(sqlStr);
	 		String sqlStr2 = "select className from tb_class where gradeID = '" + gradeIdList.get(index) + "'";
	 		classNameList = new RetrieveObject().getTableCollection(sqlStr2);
	 		for (String className : classNameList) {
				comboBox_2.addItem(className);
			}
		}	
 	}
 	/**
 	 * 第二个列表的事件处理
 	 */
 	class handleComboBox_2 implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			table.removeAll();//首先清除信息
			String cid = classIdList.get(comboBox_2.getSelectedIndex());
			String[] name ={"学生编号","班级编号","学生姓名","性别","年龄","家庭住址","联系电话"};
			String sqlStr = "select * from tb_student where classID = '" + cid + "'";
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
 			textField_5.setText(table.getValueAt(selectRow, 4).toString());
 			textField_6.setText(table.getValueAt(selectRow, 5).toString());
 			textField_7.setText(table.getValueAt(selectRow, 6).toString());
 			comboBox_3.removeAllItems();
 			comboBox_3.addItem(table.getValueAt(selectRow, 3).toString());
 			System.out.println("填充完数据");
 		}
 	}
 	/**
 	 * 对第一个按钮的事件处理
 	 */
	class refresh implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("开始进行刷新");
			new handleComboBox_2().itemStateChanged(new ItemEvent(comboBox_2, 0, comboBox_2.getSelectedItem(),ItemEvent.SELECTED));
			comboBox_1.repaint();
			comboBox_2.repaint();
			splitPane.repaint();//刷新一个父面板是否面板所有元素都被刷新？repaint还是updateui能起作用？repaint是否立即
			scrollPane.repaint();
			table.repaint();
			textField_1.repaint();
			textField_2.repaint();
			textField_3.repaint();
			comboBox_3.repaint();
			textField_5.repaint();
			textField_6.repaint();
			textField_7.repaint();
			System.out.println("刷新完毕！");
		}	
	}
	/**
	 * 对第二个按钮的事件处理
	 */
	class handleButton_2 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Obj_student aObj_student = new Obj_student(textField_1.getText(),textField_2.getText(),textField_3.getText(),comboBox_3.getItemAt(0),Integer.parseInt(textField_5.getText()),textField_6.getText(),textField_7.getText());
			if(new JdbcAdapter().InsertOrUpdateStudent(aObj_student)){
				creatEmptyPanel();
				new refresh().actionPerformed(e);
			}
			else
				JOptionPane.showMessageDialog(null, "操作失败！", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	/**
	 * 对第三个按钮的事件处理
	 */
	class handleButton_3 implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String sqlDel = "delete from tb_student where stuID = '" + textField_1.getText().replace(" ", "") + "'";
			if(new JdbcAdapter().DeleteObject(sqlDel)){
				creatEmptyPanel();
				new refresh().actionPerformed(e);
			}		
		}
	}

	/**
	 * 初始化下方控件
	 */
	private void creatEmptyPanel(){
		textField_1.setText("");
		textField_2.setText("");
		textField_3.setText("");
		textField_5.setText("");
		textField_6.setText("");
		textField_7.setText("");
		comboBox_3.removeAllItems();
		comboBox_3.addItem("男");
		comboBox_3.addItem("女");
	}

 }
 

