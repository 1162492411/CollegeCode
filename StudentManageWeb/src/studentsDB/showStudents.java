package studentsDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/students/showStudents")
public class showStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		studentsDAO dao = new studentsDAO();
		ArrayList<Students> studentsList = dao.getAllStudents();
		if(studentsList.size() != 0){
			request.setAttribute("studentsList", studentsList);
		}
		request.getRequestDispatcher("showStudents.jsp").forward(request, response);
	}

}
