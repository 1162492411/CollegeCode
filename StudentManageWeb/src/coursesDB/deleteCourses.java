package coursesDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/courses/deleteCourses")
public class deleteCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		coursesDAO dao = new coursesDAO();//创建DAO对象
		Courses cou = new Courses();//创建临时对象
		cou.setId((String) request.getParameter("deleteId"));//取值并存入临时对象
		ArrayList<Courses> coursesList = dao.deleteCourses(cou);//调用DAO中的方法进行数据库交互
		if(coursesList.size() != 0){//若方法执行成功
			new other.Log("---成功删除课程" + cou.getId());//打印日志
			request.setAttribute("coursesList", coursesList);//绑定结果并转向下一网页
			request.getRequestDispatcher("showCourses.jsp").forward(request, response);
		}
		else{//若方法执行失败
			new other.Log("===未删除课程" + cou.getId());//打印日志并跳转到错误网页
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
