package studentsDB;
import java.sql.*;
import java.util.ArrayList;
public class studentsDAO {
	static Connection con = null;
	static Statement st = null;
	static PreparedStatement ptmt = null;
	
	//初始化时连接数据库
	public studentsDAO(){
		try {//连接数据库
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/school";
			con = DriverManager.getConnection(url, "user1", "20100528");
			new other.Log("成功连接数据库");
			st = con.createStatement();
		} catch (Exception e) {
		} 	
	}
	
	//获取所有学生信息
	public ArrayList<Students> getAllStudents(){
		new other.Log("开始执行获取所有学生方法");
		ArrayList<Students> studentsList = new ArrayList<Students>();
		try{
		String sql = "select * from students";//定义数据库操作语句
		ResultSet rs = st.executeQuery(sql);//执行数据库操作语句
		while(rs.next()){//取出每一个对象的属性并添加到返回值中
			Students stu = new Students(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			studentsList.add(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentsList;
	}
	
	//添加学生信息
	public ArrayList<Students> addStudents(Students stu){
		new other.Log("开始执行添加学生方法");
		ArrayList<Students> studentsList = new ArrayList<Students>();
		String sql = "insert students values('"+stu.getId()+"','"+stu.getName()+"','"+stu.getSex()+"','"+stu.getProject()+"','"+stu.getPhone()+"','"+stu.getRemark()+"')";
		try {
			int rs = st.executeUpdate(sql);
			if(rs == 1){
				studentsList.add(stu);
				new other.Log("添加了学生" + stu.getId());
			}
		} catch (Exception e) {
		}
		return studentsList;
	}
	
	//修改学生信息
	public ArrayList<Students> editStudents(Students stu){
		ArrayList<Students> studentsList = new ArrayList<Students>();
		new other.Log("开始执行修改学生方法");
		String sql = "update students set name='"+stu.getName()+"', sex='"+stu.getSex()+"',project='"+stu.getProject()+"',phone='"+stu.getPhone()+"', remark='"+stu.getRemark()+"' where id='"+stu.getId()+"' ";
	    try {
			int rs = st.executeUpdate(sql);
			if(rs == 1){
				studentsList.add(stu);
				new other.Log("修改了学生" + stu.getId());
			}
		} catch (Exception e) {
		}
		return studentsList;
	}
	
	//删除学生信息
	public ArrayList<Students> deleteStudents(Students stu){
		new other.Log("开始执行删除学生方法");
		ArrayList<Students> studentsList = new ArrayList<Students>();
		String sql = "delete from students where id='"+stu.getId()+"'";
		try {
		int rs = st.executeUpdate(sql);
		if(rs== 1){
				studentsList.add(new Students());
				new other.Log("删除了学生" + stu.getId());	
			}
		} catch (Exception e) {
		}
	return studentsList;
}
	
	//查询学生信息
	public ArrayList<Students> searchStudents(Students stu){
		new other.Log("开始执行查询学生方法");
		ArrayList<Students> studentsList = new ArrayList<Students>();
		String sql = "select * from students where id='"+stu.getId()+"' or name='"+stu.getName()+"' or phone='"+stu.getPhone()+"' ";
		try {
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				Students cou2 = new Students(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				studentsList.add(cou2);
			}
		} catch (Exception e) {
		}
		return studentsList;
	}

	
}
