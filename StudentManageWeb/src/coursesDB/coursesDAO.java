package coursesDB;

import java.sql.*;
import java.util.ArrayList;

import other.Log;

public class coursesDAO {
	static Connection con = null;
	static Statement st = null;
	static PreparedStatement ptmt = null;
	
	public coursesDAO(){
//		new other.ConnectSql();
		try {//连接数据库
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school";
			con = DriverManager.getConnection(url, "user1", "20100528");
			new other.Log("成功连接数据库");
			st = con.createStatement();
		} catch (Exception e) {
			
		} 
		}

	//获取所有课程
	public ArrayList<Courses> getAllCourses(){
		new other.Log("开始执行获取所有课程方法");//打印日志
		
		ArrayList<Courses> coursesList = new ArrayList<Courses>();
		try{
		String sql = "select * from courses";//定义数据库操作语句
		ResultSet rs = st.executeQuery(sql);//执行数据库操作语句
		while(rs.next()){//取出每一个对象的属性并添加到返回值中
			Courses cou = new Courses(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
			coursesList.add(cou);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return coursesList;
	}
	
	//添加课程信息
	public ArrayList<Courses> addCourses(Courses cou){
		new other.Log("开始执行添加课程方法");//打印日志
		ArrayList<Courses> coursesList = new ArrayList<Courses>();//设置返回值
		String sql = "insert courses values('"+cou.getId()+"','"+cou.getName()+"','"+cou.getTeacher()+"','"+cou.getPeriod()+"','"+cou.getCredit()+"','"+cou.getRemark()+"')";
		try {
			int rs = st.executeUpdate(sql);//执行语句
			if(rs == 1){//若语句执行成功
				coursesList.add(cou);//添加到返回值中
				new other.Log("添加了课程" + cou.getId());//打印日志
			}
		} catch (Exception e) {
		}
		return coursesList;
	}
	
	//修改课程信息
	public ArrayList<Courses> editCourses(Courses cou){
		ArrayList<Courses> coursesList = new ArrayList<Courses>();
		new other.Log("开始执行修改课程方法");//打印日志
		String sql = "update courses set name='"+cou.getName()+"', teacher='"+cou.getTeacher()+"',period=cast('"+cou.getPeriod()+"' as signed),credit=cast('"+cou.getCredit()+"' as signed), remark='"+cou.getRemark()+"' where id='"+cou.getId()+"' ";
	    try {
			int rs = st.executeUpdate(sql);//执行数据库语句
			if(rs == 1){//若执行成功
				coursesList.add(cou);//添加到返回值中
				new other.Log("修改了课程" + cou.getId());//打印日志
			}
		} catch (Exception e) {
		}
		return coursesList;
	}
	
	//删除课程信息
	public ArrayList<Courses> deleteCourses(Courses cou){
		new other.Log("开始执行删除课程方法");//打印日志
		ArrayList<Courses> coursesList = new ArrayList<Courses>();//设置返回值
		String sql = "delete from courses where id='"+cou.getId()+"'";//定义语句
		try {
		int rs = st.executeUpdate(sql);//执行数据库语句
		if(rs== 1){//若数据库语句执行成功
				coursesList.add(new Courses());//添加到返回值中
				new other.Log("删除了课程" + cou.getId());//打印日志	
			}
		} catch (Exception e) {
		}
	return coursesList;
}	
	
	//查询课程信息--根据课程号
	public ArrayList<Courses> searchCoursesById(Courses cou){
		new other.Log("开始执行查询课程方法");//打印日志
		ArrayList<Courses> coursesList = new ArrayList<Courses>();//设置返回值
		String sql = "select * from courses where id='"+cou.getId()+"'";//定义数据库语句
		try {
			ResultSet rs = st.executeQuery(sql);//取得结果集
			while(rs.next()){//从结果集中取出字段值
				Courses cou2 = new Courses(rs.getString(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getString(6));
				coursesList.add(cou2);//添加到返回值中
			}
		} catch (Exception e) {
		}
		return coursesList;
	}

}
