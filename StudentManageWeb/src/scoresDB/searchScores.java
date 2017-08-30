package scoresDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/scores/searchScores")
public class searchScores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Scores sco = new Scores();
		sco.setStudentId((String) request.getParameter("searchStudentId"));
		sco.setCourseId((String) request.getParameter("searchCourseId"));
		scoresDAO dao = new scoresDAO();
		ArrayList<Scores> scoresList = dao.searchScores(sco);
		if(scoresList.size() != 0){
			new other.Log("查找了" +sco.getStudentId() + "的" + sco.getCourseId() + "成绩" );
			request.setAttribute("scoresList", scoresList);
			request.getRequestDispatcher("showScores.jsp").forward(request, response);
		}
		else{
			new other.Log("未查找到" +sco.getStudentId() + "的" + sco.getCourseId() + "成绩" );
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
