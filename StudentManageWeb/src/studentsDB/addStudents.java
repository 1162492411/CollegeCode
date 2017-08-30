package studentsDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/students/addStudents")
public class addStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studentsDAO dao = new studentsDAO();
		Students stu = new Students(request.getParameter("addId"),request.getParameter("addName"),request.getParameter("addSex"),request.getParameter("addProject"),request.getParameter("addPhone"),request.getParameter("addRemark"));
		ArrayList<Students> studentsList = dao.addStudents(stu);
		if(studentsList.size() != 0){//若添加学生方法执行成功
			new other.Log("---成功添加学生" + stu.getId());
			request.setAttribute("studentsList", studentsList);
			request.getRequestDispatcher("showStudents.jsp").forward(request, response);
		}
		else{
			new other.Log("===未成功添加学生" + stu.getId());
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
