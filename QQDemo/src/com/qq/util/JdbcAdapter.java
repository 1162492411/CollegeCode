package com.qq.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import com.qq.model.User;

public class JdbcAdapter {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;//存储结果集
	private ResultSetMetaData rsmd =null;
	private String infoStr = null;
	private String sqlStr = null;
	
	public JdbcAdapter() {
		new CommonJdbc();
	}
	
	/**
	 *  通用验证函数
	 * @param sqlStr 待验证的数据库语句
	 * @return 验证的数据是否存在的结果
	 */
	private boolean validateAll(String sqlStr) {
		try {
			con = CommonJdbc.connection;// 获取数据库连接
			pstmt = con.prepareStatement(sqlStr);// 执行预编译语句;
			rs = pstmt.executeQuery();// 执行sql语句
			if (rs.next()) {// 结果集不为空时返回true值
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}
	/**
	 *  通用执行操作函数
	 * @param sqlState 待执行的操作
	 * @return 执行的操作是否成功
	 */
	private boolean AdapterObject(String sqlState) {
		boolean flag = false;
		try {
			con = CommonJdbc.connection;
			pstmt = con.prepareStatement(sqlState);
			pstmt.execute();
			flag = true;
			JOptionPane.showMessageDialog(null, infoStr + "成功", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 通用删除函数
	 */
	public boolean DeleteObject(String mainqq, String friendqq){
		sqlStr = "delete  from tb_friend where mainQQ = " + mainqq + " and friendQQ = "
				+ friendqq;
		infoStr = "删除";
		return AdapterObject(sqlStr);
	}
	
	/**
	 * 将注册用户信息加入数据库
	 */
	public boolean insertRegister(User aUser){
		sqlStr = "insert tb_user values('" + aUser.getUserID() + "','" + aUser.getUserPassword() + "','"
				+ aUser.getUserName() + "','" + aUser.getUserSex()  + "'," + aUser.getUserAge() + ",'"
				+ aUser.getUserInfo() + "','" + aUser.getUserEmail() + "')";
		infoStr = "注册用户";
		return AdapterObject(sqlStr);
	}

	/**
	 * 验证账户密码是否正确
	 */
	public boolean validateLogin(User aUser){
		sqlStr = "select * from tb_user where userID = '" + aUser.getUserID() + "' and userPassword = '" + aUser.getUserPassword() + "'";
		return validateAll(sqlStr);
	}

	/**
	 * 生成列表模型
	 */
	public ListModel<String> getListModel(String sqlStr){
		DefaultListModel<String> aListModel = new DefaultListModel<String>();
		con = CommonJdbc.connection;
		try {
			rs = con.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
				for(int i = 1;i<rsmd.getColumnCount() + 1;i++){
					aListModel.addElement((String) rs.getObject(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aListModel;
	}
	/**
	 * 生成表格模型
	 */
	public DefaultTableModel getTableModel(String[] header,String sql){
		DefaultTableModel aModel = new DefaultTableModel(header,0);
		System.out.println("开始生成表格模型");
		con = CommonJdbc.connection;
		try {
			rs = con.prepareStatement(sql).executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
				Vector<Object> vdata = new Vector<Object>();
				for(int i = 1;i<rsmd.getColumnCount() + 1;i++){
					vdata.addElement(rs.getObject(i));
				}
				aModel.addRow(vdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return aModel;	
	}
	/**
	 * 添加好友
	 */
	public boolean addFriend(String mainqq, String addqq){
		String sqlStr = "insert tb_friend values (" + mainqq + "," + addqq + ")"; 
		infoStr = "添加好友";
		return AdapterObject(sqlStr);
	}
	
	/**
	 * 修改密码
	 */
	public boolean updatePass(String newpass,String qq){
		String sql = "update tb_user set userPassword = '" + newpass + "' where userID = '" + qq + "'";
		infoStr = "修改密码";
		return AdapterObject(sql);
	}
	/**
	 * 修改用户信息
	 */
	public boolean updateUserInfo(User aUser){
		String sql = "update tb_user set userName = '" + aUser.getUserName() + "',userSex = '" + aUser.getUserSex()
		 + "',userAge = " + aUser.getUserAge() + ",userInfo = '" + aUser.getUserInfo() + "',userEmail = '"
		 + aUser.getUserEmail() + "' where userID = '" + aUser.getUserID() + "'";
		infoStr = "修改用户信息";
		return AdapterObject(sql);
	}
	/**
	 * 获取用户信息
	 */
	public String[] loadUserInfo(String qq){
		String sqlStr = "select userName,userSex,userAge,userInfo,userEmail from tb_user where userID = '" + qq + "'";
		con = CommonJdbc.connection;
		String[] strings = new String[5];
		try {
			rs = con.prepareStatement(sqlStr).executeQuery();
			while(rs.next()){
					strings[0] = rs.getString(1);
					strings[1] = rs.getString(2);
					strings[2] = rs.getString(3);
					strings[3] = rs.getString(4);
					strings[4] = rs.getString(5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return strings;	
	}
}
