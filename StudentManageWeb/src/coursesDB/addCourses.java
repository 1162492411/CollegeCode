package coursesDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/courses/addCourses")
public class addCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		coursesDAO dao = new coursesDAO();//创建DAO对象
		//获取网页传值
		Courses cou = new Courses(request.getParameter("addId"),request.getParameter("addName"),request.getParameter("addTeacher"),Integer.valueOf(request.getParameter("addPeriod")),Integer.valueOf(request.getParameter("addCredit")),request.getParameter("addRemark"));
		//执行DAO中与数据库交互的方法并将其结果存储
		ArrayList<Courses> coursesList = dao.addCourses(cou);
		if(coursesList.size() != 0){//若方法执行成功
			new other.Log("---成功添加课程" + cou.getId());//打印日志
			request.setAttribute("coursesList", coursesList);//绑定方法返回结果并转向下一网页
			request.getRequestDispatcher("showCourses.jsp").forward(request, response);
		}
		else{//若方法执行失败
			new other.Log("===未添加课程" + cou.getId());//打印日志，转向错误页面
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
