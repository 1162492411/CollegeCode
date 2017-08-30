package com.qq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonJdbc {
	public static Connection connection = null;
	public CommonJdbc() {
		getCon();
	}

	private Connection getCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_qqdemo";
			connection = DriverManager.getConnection(url, "user1", "20100528");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
		}
		return connection;
	}

}
