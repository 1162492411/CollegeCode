package com.qq.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.JTextArea;

/*
 * 接收消息的线程类
 */
class receiveThread implements Runnable{
	private DatagramSocket receiveSocket;
	private JTextArea messageArea;
	private String friendqq;
	private String mainqq;

	public receiveThread(JTextArea messageArea, DatagramSocket receiveSocket,String friendqq,String mainqq) {
		this.messageArea = messageArea;
		System.out.println("接收信息线程开启");
		this.receiveSocket = receiveSocket;
		this.friendqq = friendqq;
		this.mainqq = mainqq;
	}

	@Override
	public void run() {
		try {
			while(true){
				//一次接收的内容的最大容量
				byte[] buf = new byte[1024];
				DatagramPacket dp = new DatagramPacket(buf, buf.length);
				receiveSocket.receive(dp);//接收数据包
				String data = new String(dp.getData(), 0, dp.getLength());//取得数据包里的内容 
				//分析数据包是否为指定好友向自己发送
				String dpSrc = data.substring(0, data.indexOf("对"));
				String dpDes = data.substring(data.indexOf("对") + 1,data.indexOf("说"));
				if(dpSrc.equals(friendqq) && dpDes.equals(mainqq)){
					System.out.println(data);
					messageArea.setAlignmentX(0);
					messageArea.append(dpSrc + "\n    ");
					messageArea.append(data.substring(data.indexOf("说") + 1));
				}
			}
		} catch (IOException e) {
			System.out.println("receive fail");
		}finally{
			if(receiveSocket != null){
				receiveSocket.close();
			}
		}
		
	}
}
