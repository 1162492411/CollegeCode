package scoresDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/scores/addScores")
public class addScores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		scoresDAO dao = new scoresDAO();
		Scores sco = new Scores(request.getParameter("addStudentId"),request.getParameter("addCourseId"),Float.parseFloat(request.getParameter("addScore")),Integer.parseInt(request.getParameter("addTerm")),request.getParameter("addRemark"));
		ArrayList<Scores> scoresList = dao.addScoresByRow(sco);
		if(scoresList.size() != 0){//若添加课程方法执行成功
			new other.Log("添加了" +sco.getStudentId() + "的" + sco.getCourseId() + "成绩" );
			request.setAttribute("scoresList", scoresList);
			request.getRequestDispatcher("showScores.jsp").forward(request, response);
		}
		else{
			new other.Log("未添加" +sco.getStudentId() + "的" + sco.getCourseId() + "成绩" );
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
