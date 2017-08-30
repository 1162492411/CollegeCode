package studentsDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/students/deleteStudents")
public class deleteStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studentsDAO dao = new studentsDAO();
		Students stu = new Students();
		stu.setId((String) request.getParameter("deleteId"));
		ArrayList<Students> studentsList = dao.deleteStudents(stu);//调用DAO中的方法进行数据库交互
		if(studentsList.size() != 0){//若方法执行成功
			request.setAttribute("studentsList", studentsList);
			request.getRequestDispatcher("showStudents.jsp").forward(request, response);
		}
		else{
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
