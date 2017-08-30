package coursesDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/courses/showCourses")
public class showCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		coursesDAO dao = new coursesDAO();//创建DAO对象
		ArrayList<Courses> coursesList = dao.getAllCourses();//进行数据库交互并将返回值存入结果集
		if(coursesList.size() != 0){//若方法执行成功
			new other.Log("---成功显示所有课程");//打印日志
			request.setAttribute("coursesList", coursesList);//绑定结果集并转向下一网页
			request.getRequestDispatcher("showCourses.jsp").forward(request, response);
		}
		else{//若方法执行失败
			new other.Log("===未成功显示所有课程");//打印日志并转向错误页面
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}
}
