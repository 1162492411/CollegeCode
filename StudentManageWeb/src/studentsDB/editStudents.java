package studentsDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/students/editStudents")
public class editStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studentsDAO dao = new studentsDAO();
		Students stu = new Students(request.getParameter("formerId"),request.getParameter("editName"),request.getParameter("editSex"),request.getParameter("editProject"),request.getParameter("editPhone"),request.getParameter("editRemark"));
		ArrayList<Students> studentsList = dao.editStudents(stu);
		if(studentsList.size() != 0){//若添加课程方法执行成功
			request.setAttribute("studentsList", studentsList);
			request.getRequestDispatcher("showStudents.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
