package com.qq.test2;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
/**
 * 主私聊界面
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.qq.myComponent.myButton;

public class chatToPerson extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField tf1;
	private myButton bt1, bt_save;
	public static JTextArea messageArea;
	private JScrollPane scrollPane;
	private static DatagramSocket sendSocket;
	private static DatagramSocket receiveSocket;
	private static String IP_SEND = "localhost";
	private static  int PORT_SEND = 0;//目标端口
	private static  int PORT_SELF = 0;//自身端口
	private String mainqq,friendqq;
	

	// 构造函数
	public chatToPerson(String mainQQ, String friendQQ) {
		mainqq = mainQQ;
		friendqq = friendQQ;
		PORT_SEND = 9000 + Integer.parseInt(friendqq);//设置目标端口
		PORT_SELF = 9000 + Integer.parseInt(mainqq);//设置自身端口
		getContentPane().setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();// 获取屏幕大小

		messageArea = new JTextArea();//设置消息窗体
	//	messageArea.setBounds(20, 20, 300, 200);
		messageArea.setEditable(false);
		getContentPane().add(messageArea);

		//加载聊天信息
		chatInfoHandle infoHandle = new chatInfoHandle(mainqq, friendqq);
		String infoStr = infoHandle.loadChatInfo();
		messageArea.append(infoStr);
		
		//设置滚动窗体
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 300, 200);
		scrollPane.setViewportView(messageArea);
		getContentPane().add(scrollPane);
		
		tf1 = new JTextField(12);
		tf1.setBounds(20, 255, 300, 50);
		getContentPane().add(tf1);

		bt_save = new myButton("存储");//设置"存储"按钮
		bt_save.setBounds(160, 310, 75, 25);
		bt_save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				infoHandle.saveChatInfo(messageArea.getText());
			}
		});
		getContentPane().add(bt_save);
		
		bt1 = new myButton("  发送  ");//设置“发送”按钮
		bt1.setBounds(245, 310, 75, 25);
		bt1.addActionListener(new handleSend());
		getContentPane().add(bt1);

		
		
		try {
			receiveSocket = new DatagramSocket(PORT_SELF);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		new Thread(new ReceiveThread(messageArea, receiveSocket,friendqq,mainqq)).start();
			
		setFont(new Font("微软雅黑", Font.PLAIN, 16));
		this.setTitle(friendqq);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(screenSize.width / 2 - 200, screenSize.height / 2 - 200, 400, 400);
		this.setVisible(true);
	}

	// 处理点击"发送"按钮的动作事件
	class handleSend implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sendMessage(tf1.getText());
		}
	}

	private void sendMessage(String s) {
		try {
			sendSocket = new DatagramSocket();
			//设置信息前缀,并将其与信息合并到一起
			String prefix = mainqq + "对" + friendqq + "说";
			byte[] pre = prefix.getBytes();
			byte[] data = s.getBytes();
			byte[] buf = new byte[pre.length + data.length];
			System.arraycopy(pre, 0, buf, 0, pre.length);
			System.arraycopy(data, 0, buf, pre.length, data.length);
			// 实例化一个数据包，指定发送的内容，内容长度，发送的地址和端口
			DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName(IP_SEND), PORT_SEND);
			// 发送数据包
			sendSocket.send(dp);
			// 打印发送的内容
			messageArea.append(mainqq + "\n    " + s + "\n");
			tf1.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		sendSocket.close();
	}


}
