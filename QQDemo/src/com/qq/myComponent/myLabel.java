package com.qq.myComponent;

import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class myLabel extends JLabel {
	//通过文字构造标签
	public myLabel(String text){
		super(text, null, LEADING);
		setLookAndFeel();
	}

	//通过图标构造标签
	public myLabel(Icon image){
		super(null, image, CENTER);
		setLookAndFeel();
	}
	private void setLookAndFeel() {
		setFont(new Font("微软雅黑",Font.PLAIN,16));
	}
}
