package util;

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
			System.out.println("Strat to connect...");
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/db_student";
			System.out.println("Connecting...");
			connection = DriverManager.getConnection(url, "user1", "20100528");
			System.out.println("Connect succeed.");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e2) {
			new view.JF_view_error(e2.getMessage());
		}
		return connection;
	}

}
