package view;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;


@SuppressWarnings("serial")
public class appMain extends JFrame {
	public JDesktopPane contentPane;
	public static void main(String[] args) {
		new appMain();
	}

	public appMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 420);
		setPersonalFont();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//初始化菜单栏
		String menuName[] = {"参数设置","基本信息","系统查询","系统管理"};
		JMenu menu[] = new 	JMenu[menuName.length];
		for (int i = 0; i < menuName.length; i++) {
			menu[i] = new JMenu(menuName[i]);
			menuBar.add(menu[i]);
			}
		
		//初始化菜单A
		String menuItemNameA[] = {"年级设置","班级设置","课程信息","考试类别"};
		JMenuItem menuItemA[] = new JMenuItem[menuItemNameA.length];
		for (int i = 0; i < menuItemA.length; i++) {
			menuItemA[i] = new JMenuItem(menuItemNameA[i]);
			menuItemA[i].addActionListener(new handleMenu());
			menu[0].add(menuItemA[i]);
		}
		
		//初始化菜单B
		String menuItemNameB[] = {"学生信息","教师信息","考试成绩"};
		JMenuItem menuItemB[] = new JMenuItem[menuItemNameB.length];
		for (int i = 0; i < menuItemB.length; i++) {
			menuItemB[i] = new JMenuItem(menuItemNameB[i]);
			menuItemB[i].addActionListener(new handleMenu());
			menu[1].add(menuItemB[i]);
		}
		
		//初始化菜单C
		String menuItemNameC[] = {"基本信息","成绩信息","汇总信息"};
		JMenuItem menuItemC[] = new JMenuItem[menuItemNameC.length];
		for (int i = 0; i < menuItemC.length; i++) {
			menuItemC[i] = new JMenuItem(menuItemNameC[i]);
			menuItemC[i].addActionListener(new handleMenu());
			menu[2].add(menuItemC[i]);
		}
		
		//初始化菜单D
		String menuItemNameD[] = {"用户维护","退出系统"};
		JMenuItem menuItemD[] = new JMenuItem[menuItemNameD.length];
		for (int i = 0; i < menuItemD.length; i++) {
			menuItemD[i] = new JMenuItem(menuItemNameD[i]);
			menuItemD[i].addActionListener(new handleMenu());
			menu[3].add(menuItemD[i]);
		}
		
		contentPane = new JDesktopPane();
		setContentPane(contentPane);

		contentPane.add(setBackgroundImage(),new Integer(Integer.MIN_VALUE));
		contentPane.setVisible(true);
		setVisible(true);
	}
	//设置背景图片
	private  JLabel setBackgroundImage(){
	JLabel lblAppback = new JLabel("");
	lblAppback.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("images/appBackTest2.jpg")));
	lblAppback.setBounds(0,0,640,400);
	return lblAppback;
}
	//设置全局字体--来自YunCode
	private void setPersonalFont(){//使用迭代器批量添加字体
		FontUIResource allFont = new FontUIResource(new Font("微软雅黑",Font.PLAIN,16));
		for(Enumeration<Object> keys = UIManager.getDefaults().keys();keys.hasMoreElements();){
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource){
				 UIManager.put(key, allFont);
			}
		}
	}
	//添加按钮处理
	private class handleMenu implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {//根据按钮名进行不同响应
			case "年级设置":
				contentPane.add(new JF_view_sysset_grade());
				break;
			case "班级设置":
				contentPane.add(new JF_view_syssset_class());//完成一次
				break;
			case "课程信息":
				contentPane.add(new JF_view_sysset_subject());
				break;
			case "考试类别":
				contentPane.add(new JF_view_sysset_examkinds());
				break;
			case "学生信息":
				contentPane.add(new JF_view_student());//完成一次
				break;
			case "教师信息":
				contentPane.add(new JF_view_teacher());
				break;
			case "考试成绩":
				contentPane.add(new JF_view_gradesub());//待完成
				break;
			case "基本信息":              
				contentPane.add(new JF_view_query_jbqk());//完成一次
				break;
			case "成绩信息":
				contentPane.add(new JF_view_query_grade_mx());//完成一次
				break;
			case "汇总信息":
				contentPane.add(new JF_view_query_grade_hz());
				break;
			case "用户维护":
				contentPane.add(new JF_view_user_modify());
				break;
			case "退出系统":
				contentPane.add(new JF_view_logout());
				break;
			default:
				System.out.println("未选择按钮，请点击选择");
				break;
			}
		}
		
	}

}

