package scoresDB;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/scores/showScores")
public class showScores extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		scoresDAO dao = new scoresDAO();
		ArrayList<Scores> scoresList = dao.getAllScores();
		if(scoresList.size() != 0){
			new other.Log("---成功显示所有成绩");
			request.setAttribute("scoresList", scoresList);
			request.getRequestDispatcher("showScores.jsp").forward(request, response);
		}
		else{
			new other.Log("===未成功显示所有成绩");
			request.getRequestDispatcher("../error/404.jsp").forward(request, response);
		}
	}

}
