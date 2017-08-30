package com.zyg.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoFactory {
	public static Connection connection = null;

	// 获取数据库连接
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_news";
			connection = DriverManager.getConnection(url, "user1", "20100528");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return connection;
	}

	// 关闭数据库连接
	public static void closeAll(Connection con, PreparedStatement st, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
