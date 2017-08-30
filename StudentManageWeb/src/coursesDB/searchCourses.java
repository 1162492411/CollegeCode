package coursesDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/courses/searchCourses")
public class searchCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Courses cou = new Courses();//创建临时对象
		cou.setId((String) request.getParameter("searchId"));//将传的值存入临时对象中
		
		coursesDAO dao = new coursesDAO();//创建DAO对象
		ArrayList<Courses> coursesList = dao.searchCoursesById(cou);//执行数据库交互方法并返回结果
		
		if(coursesList.size() != 0){//若方法执行成功
			new other.Log("---成功查找课程" + cou.getId());//打印日志
			request.setAttribute("coursesList", coursesList);//绑定结果并转向下一网页
			request.getRequestDispatcher("showCourses.jsp").forward(request, response);
		}
		else{//若方法执行失败
			new other.Log("===未成功查找课程" + cou.getId());//打印日志并转向错误网页
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
