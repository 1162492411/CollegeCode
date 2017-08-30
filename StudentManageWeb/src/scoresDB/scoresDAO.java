package scoresDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class scoresDAO {
	//全局变量
	static Connection con = null;
	static Statement st = null;
	static PreparedStatement ptmt = null;
	//构造函数，进行DAO的初始化
	public scoresDAO(){
		try {//连接数据库
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school";
			con = DriverManager.getConnection(url, "user1", "20100528");
			new other.Log("成功连接数据库");
			st = con.createStatement();
		} catch (Exception e) {
		} 
	}
	
	//获取所有成绩
	public ArrayList<Scores> getAllScores(){
		new other.Log("开始执行获取所有成绩方法");
		ArrayList<Scores> scoresList = new ArrayList<Scores>();
		try{
		String sql = "select * from scores order by studentId";//定义数据库操作语句，查询到的结果按学号升序排序
		ResultSet rs = st.executeQuery(sql);//执行数据库操作语句
		while(rs.next()){//取出每一个对象的属性并添加到返回值中
			Scores sco = new Scores(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getInt(4),rs.getString(5));
			scoresList.add(sco);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scoresList;
	} 
	
	//添加成绩信息--逐条添加
	public ArrayList<Scores> addScoresByRow(Scores sco){
		new other.Log("开始执行逐条添加成绩方法");
		ArrayList<Scores> scoresList = new ArrayList<Scores>();
		String sql = "insert scores values('"+sco.getStudentId()+"','"+sco.getCourseId()+"',cast('"+sco.getScore()+"' as decimal),cast('"+sco.getTerm()+"' as signed),'"+sco.getRemark()+"')";
		try {
			int rs = st.executeUpdate(sql);
			if(rs == 1){
				scoresList.add(sco);
			}
		} catch (Exception e) {
		}
		return scoresList;
	}
	
	//查询成绩信息--根据学号和课程号
	public ArrayList<Scores> searchScores(Scores sco){
		new other.Log("开始执行查询成绩方法");
		ArrayList<Scores> scoresList = new ArrayList<Scores>();
		String sql = "select * from scores where studentId='"+sco.getStudentId()+"' or courseId='"+sco.getCourseId()+"' ";
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Scores sco2 = new Scores(rs.getString(1),rs.getString(2),rs.getFloat(3),rs.getInt(4),rs.getString(5));
				scoresList.add(sco2);
			}
		} catch (Exception e) {
		}
		return scoresList;
	}
	
	
	public ArrayList<Scores> searchEverageScores(Scores sco){
		new other.Log("开始执行查询平均成绩方法");
		ArrayList<Scores> scoresList = new ArrayList<Scores>();

		String sql = " select AVG(score) from scores where courseId='"+sco.getCourseId()+"' ";
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Scores sco2 = new Scores();
				sco2.setCourseId(sco.getCourseId());
				sco2.setRemark(rs.getString(1));
				scoresList.add(sco2);
			}
		} catch (Exception e) {
		}
		return scoresList;
	}
	

}
