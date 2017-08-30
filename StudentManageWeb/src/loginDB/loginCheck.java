package loginDB;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/login/loginCheck")
public class loginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户发送的信息
		String checkName = request.getParameter("loginName");
		String checkPassword = request.getParameter("loginPassword");
		boolean flag = false;
		//在数据库中检查是否存在，若帐号密码均存在且正确，则跳转到默认管理页面
				try {
					Class.forName("com.mysql.jdbc.Driver");//加驱
					String url = "jdbc:mysql://localhost:3306/school";//声明数据库地址
					Connection con = DriverManager.getConnection(url, "user1", "20100528");//连接数据库
					new other.Log("成功连接数据库");//打印日志
					Statement st = con.createStatement();//创建数据库执行语句
					String sql1 = "select *  from user";//执行数据库语句
					ResultSet rt = st.executeQuery(sql1);//取得结果集
					while(rt.next()){//遍历是否帐号密码正确
						if(checkName.equals(rt.getString(1)) && checkPassword.equals(rt.getString(2))){
							flag = true;//如果正确，设置标志变量为true
						}
					}
				} catch (Exception e) {
				}
				if(flag){//如果帐号密码均正确，跳转向默认管理页面
					request.getRequestDispatcher("../manage/main.jsp").forward(request,response);	
				}
				else{//如果出错，跳转向错误页面
					request.getRequestDispatcher("/index.jsp").forward(request,response);
				}
}
}