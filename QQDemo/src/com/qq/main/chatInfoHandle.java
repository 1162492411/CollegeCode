package com.qq.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class chatInfoHandle {
	private File chatFile;//聊天文件
	private InputStreamReader is;//流
	private OutputStreamWriter os;//流
	private StringBuilder sBuilder;//消息字符串
	
	//构造器
	public chatInfoHandle(String mainqq, String friendqq) {
		chatFile = new File(mainqq + "To" + friendqq + ".txt");
		//建立指定的聊天记录文件
		if(!chatFile.exists()){
			try {
				chatFile.createNewFile();
			} catch (IOException e) {
				System.out.println("未能建立文件");
			}
		}
	}
	
	//加载指定的聊天记录
	public String loadChatInfo(){
		try {
			sBuilder = new StringBuilder();
			is = new InputStreamReader(new FileInputStream(chatFile));
			char[] info = new char[1024];
			String infos;
			while(is.read(info) != -1){
				infos = new String(info);
				sBuilder.append(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();//释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new String(sBuilder);//返回聊天信息字符串
	}

	//存储指定的聊天记录
	public void saveChatInfo(String infoStr){
		try {
			sBuilder = new StringBuilder();
			os = new OutputStreamWriter(new FileOutputStream(chatFile));
			os.write(infoStr);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				os.close();//释放资源
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
