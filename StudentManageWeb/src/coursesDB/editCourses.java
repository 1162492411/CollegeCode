package coursesDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/courses/editCourses")
public class editCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		coursesDAO dao = new coursesDAO();//创建DAO对象
		//创建临时对象，并将该servlet取得的值存入临时对象
		Courses cou = new Courses(request.getParameter("formerId"),request.getParameter("editName"),request.getParameter("editTeacher"),Integer.parseInt(request.getParameter("editPeriod")),Integer.parseInt(request.getParameter("editCredit")),request.getParameter("editRemark"));
		ArrayList<Courses> coursesList = dao.editCourses(cou);//设置结果
		if(coursesList.size() != 0){//若编辑课程方法执行成功
			new other.Log("成功修改课程" + cou.getId());//打印日志
			request.setAttribute("coursesList", coursesList);//绑定结果并指向下一网页
			request.getRequestDispatcher("showCourses.jsp").forward(request, response);
		}
		else{//若方法执行失败
			new other.Log("===未成功修改课程" + cou.getId());//打印日志并转向错误网页
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
