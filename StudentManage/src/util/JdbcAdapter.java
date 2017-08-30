package util;
import java.sql.*;
import javax.swing.JOptionPane;
import model.Obj_class;
import model.Obj_grade_sub;
import model.Obj_student;

public class JdbcAdapter {
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;//存储结果集
	private String infoStr = null;//待确定

	public JdbcAdapter() {
		new CommonJdbc();
	}

	/**
	 * 验证班级
	 * @param classid 待验证的班级编号
	 * @return 验证班级是否存在的结果
	 */
	public boolean validateClass(String classid) {
		return validateAll("select * from tb_class where classID = '" + classid + "'");
	}

	/**
	 * 验证考试类型
	 * @param kindid 待验证的考试类型
	 * @return 验证考试类型是否存在的结果
	 */
	public boolean validateExamkinds(String kindid) {
		return validateAll("select * from tb_examkinds where kindID = '" + kindid + "'");
	}

	/**
	 *  验证学生成绩
	 * @param stuid 待验证的学生编号
	 * @param kindid 待验证的考试种类编号
	 * @param code 待验证的考试科目编号
	 * @return 验证学生成绩是否存在的结果
	 */
	public boolean validateGrade_sub(String stuid, String kindid, String code) {
		return validateAll("select * from tb_grade_sub where stuID = '" + stuid + "' and kindID = '" + kindid
				+ "' and code = '" + code + "'");
	}

	/**
	 *  验证年级信息
	 * @param gradeid 待验证的年级编号
	 * @return 验证年级编号是否存在的结果
	 */
	public boolean validateGrade(String gradeid) {
		return validateAll("select * from tb_grade where gradeID = '" + gradeid + "'");
	}

	/**
	 *  验证学生信息
	 * @param stuid 待验证的学生编号
	 * @return 验证学生是否存在的结果
	 */
	public boolean validateStudent(String stuid) {
		return validateAll("select * from tb_student where stuID = '" + stuid + "'");
	}

	/**
	 *  验证课程信息
	 * @param code 待验证的课程编号
	 * @return 验证课程是否存在的结果
	 */
	public boolean validateSubject(String code) {
		return validateAll("select * fromo tb_subject where code = '" + code + "'");
	}

	/**
	 *  验证教师信息
	 * @param teaid 待验证的教师编号
	 * @return 验证教师是否存在的结果
	 */
	public boolean validateTeacher(String teaid) {
		return validateAll("select * from tb_teacher where teaID = '" + teaid + "'");
	}

	/**
	 *  验证用户信息
	 * @param id 待验证的用户编号
	 * @param cs 待验证的用户密码
	 * @return 验证用户账户密码是否正确结果
	 */
	public boolean validateUser(String id, char[] cs) {
		return validateAll("select * from tb_user where userID = '" + id + "' and pass = '" + new String(cs) + "'");
	}

	/**
	 *  通用验证函数
	 * @param sqlStr 待验证的数据库语句
	 * @return 验证的数据是否存在的结果
	 */
	private boolean validateAll(String sqlStr) {
		try {
			con = CommonJdbc.connection;// 获取数据库连接
			pstmt = con.prepareStatement(sqlStr);// 执行预编译语句;
			rs = pstmt.executeQuery();// 执行sql语句
			if (rs.next()) {// 结果集不为空时返回true值
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	/**
	 *  通用执行操作函数
	 * @param sqlState 待执行的操作
	 * @return 执行的操作是否成功
	 */
	private boolean AdapterObject(String sqlState) {
		boolean flag = false;
		try {
			con = CommonJdbc.connection;
			pstmt = con.prepareStatement(sqlState);
			pstmt.execute();
			flag = true;
			JOptionPane.showMessageDialog(null, infoStr + "数据成功", "系统提示", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	/**
	 * 通用删除函数
	 */
	public boolean DeleteObject(String deleteSql){
		infoStr = "删除";
		System.out.println(deleteSql);
		return AdapterObject(deleteSql);
	}

	/**
	 *  修改学生信息
	 * @param objstudent 待修改的学生对象
	 * @return 修改学生信息的语句
	 */
	public boolean InsertOrUpdateStudent(Obj_student objstudent) {
		String sqlStatement = null;
		if (validateStudent(objstudent.getStuid())) {
			sqlStatement = "Update tb_student set stuID = '" + objstudent.getStuid() + "',classID = '"
					+ objstudent.getClassID() + "',stuName = '" + objstudent.getStuname() + "',sex = '"
					+ objstudent.getSex() + "',age = '" + objstudent.getAge() + "',addr = '" + objstudent.getAddres()
					+ "',phone = '" + objstudent.getPhone() + "' where stuID = '" + objstudent.getStuid().trim() + "'";
			infoStr = "更新学生信息";
		} else {
			sqlStatement = "Insert tb_student(stuID,classID,stuName,sex,age,addr,phone) values('" + objstudent.getStuid()
					+ "','" + objstudent.getClassID() + "','" + objstudent.getStuname() + "','" + objstudent.getSex()
					+ "'," + objstudent.getAge() + ",'" + objstudent.getAddres() + "','" + objstudent.getPhone()
					+ "')";
			infoStr = "添加学生信息";
		}
		return AdapterObject(sqlStatement);
	}

	/**
	 *  存储学生成绩
	 * @param object 待存储的学生对象
	 * @return 存储学生对象的语句
	 */
	public boolean InsertOrUpdate_Obj_grade_sub(Obj_grade_sub[] object) {
		try {
			con = CommonJdbc.connection;
			stmt = con.createStatement();
			for (int i = 0; i < object.length; i++) {
				String sqlStr = null;
				if (validateGrade_sub(object[i].getStuid(), object[i].getKindID(), object[i].getCode())) {
					sqlStr = "update tb_grade_sub set stuID = '" + object[i].getStuid() + "',stuName = '"
							+ object[i].getStuname() + "',kindID = '" + object[i].getKindID() + "',code = '"
							+ object[i].getCode() + "',grade=" + object[i].getGrade()  + " where stuID = '" + object[i].getStuid() + "'and kindID = '"
							+ object[i].getKindID() + "'and code = '" + object[i].getCode() + "'";// grade需进行类型转换
				} else {
					sqlStr = "insert tb_grade_sub(stuID,stuName,kindID,code,grade) values ("
							+ "'" + object[i].getStuid() + "','" + object[i].getStuname() + "','" 
							+ object[i].getKindID() + "','" + object[i].getCode() + "',"
							+ object[i].getGrade() + ")";
				}
				System.out.println("sqlStr = " + sqlStr);
				stmt.addBatch(sqlStr);
			}
			stmt.executeBatch();
			JOptionPane.showMessageDialog(null, "学生成绩数据存盘成功！","系统提示",JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			new view.JF_view_error("错误信息为:");//该行存在问题
			return false;
		}
		return true;
	}
	
	/**
	 * 删除学生成绩
	 * @param object 待删除的学生对象数组
	 * @return 删除学生对象数组的语句
	 */
	public boolean Delete_Obj_grade_sub(Obj_grade_sub[] object){
		try {
			con = CommonJdbc.connection;
			stmt = con.createStatement();
			for(int i = 0; i < object.length; i++){
				String sqlStr = null;
				sqlStr = "delete from tb_grade_sub where stuID = '" + object[i].getStuid() + "'and kindID = '" + object[i].getKindID()
						+ "'and code = '" + object[i].getCode() + "'";
				System.out.println("sqlStr = " + sqlStr);
				stmt.addBatch(sqlStr);
			}
			stmt.executeBatch();
			JOptionPane.showMessageDialog(null, "学生成绩数据删除成功！","系统提示",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			new view.JF_view_error("错误信息为:");//该行存在问题
			return false;
		}
		return true;		
	}
	
	/**
	 *  修改班级信息
	 * @param obj_class 待修改的班级对象
	 * @return 修改班级信息的语句
	 */
	public boolean InsertOrUpdateClass(Obj_class obj_class) {
		String sqlStatement = null;
		if (validateClass(obj_class.getClassID())) {
			sqlStatement = "Update tb_class set classID = '" + obj_class.getClassID() + "',gradeID = '" + obj_class.getGradeID() + 
					"',className = '" + obj_class.getClassName() + "' where classID = '" + obj_class.getClassID() + "'";
			infoStr = "更新班级信息";
		} else {
			sqlStatement = "Insert tb_class(classID,gradeID,className) values('" + obj_class.getClassID() + "','" + obj_class.getGradeID() + 
					"','" + obj_class.getClassName() + "')";
			infoStr = "添加班级信息";
		}
		return AdapterObject(sqlStatement);
	}

}
