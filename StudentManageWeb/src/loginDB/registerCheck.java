package loginDB;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/registerCheck")
public class registerCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {;
		//获取用户发送的信息
		String checkName = request.getParameter("registerName");
		String checkPassword = request.getParameter("registerPassword");
		try {
			Class.forName("com.mysql.jdbc.Driver");//加驱
			String url = "jdbc:mysql://localhost:3306/school";//声明数据库地址
			Connection con = DriverManager.getConnection(url, "user1", "20100528");//连接数据库
			new other.Log("连接数据库成功！");
			Statement st = con.createStatement();
			String sql2 = "insert into user() values('"+checkName+"','"+checkPassword+"')";
			int rt = st.executeUpdate(sql2);//插入到数据库中
			if(rt == 1){//若插入成功，转向登录界面
				new other.Log("---成功注册用户" + checkName);//打印日志，转向错误页面
				RequestDispatcher rd= request.getRequestDispatcher("index.jsp");
				rd.forward(request,response);
			}
			else{
				new other.Log("===未成功注册用户" + checkName);//打印日志，转向错误页面
				request.getRequestDispatcher("../error/404.jsp").forward(request, response);
			}
		} catch (Exception e) {
			new other.Log("===出现错误" + checkName);//打印日志，转向错误页面
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		} 
	}

}
