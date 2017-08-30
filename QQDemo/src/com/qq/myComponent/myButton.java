package com.qq.myComponent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.Icon;
/**
 * 自定义按钮
 */
import javax.swing.JButton;
import javax.swing.border.LineBorder;
@SuppressWarnings("serial")
public class myButton extends JButton{
	//根据文字的构造函数
	public myButton(String text){
		super(text, null);
		setLookAndFeel();
	}
	//同时设置图标和文字的构造函数
	public myButton(String text, Icon icon){
		super(text, icon);
		setLookAndFeel();
	}
	
	
	private void setLookAndFeel(){
		setBorder(new LineBorder(Color.BLACK));//设置边框
		setBackground(Color.WHITE);//设置背景色
		setFont(new Font("微软雅黑",Font.PLAIN,16));//设置字体
		//setContentAreaFilled(false);//设置透明效果
	}
}
