package util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;



public class RetrieveObject {
	private Connection con = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd =null;
	private ArrayList<String> vdata =null;
	private ArrayList<Object> data = null;
	
	public RetrieveObject(){
		new CommonJdbc();
	}

	
	//检索满足指定条件的数据集合//取出结果后只能整条数据直接使用...
	public ArrayList<Object> getResultCollection(String sqlStr){
		con = CommonJdbc.connection;
		data = new ArrayList<Object>();
			try {
				rs = con.prepareStatement(sqlStr).executeQuery();
				rsmd = rs.getMetaData();
				while(rs.next()){
					Vector<Object> d = new Vector<Object>();
					for(int i = 1;i<rsmd.getColumnCount() + 1;i++){
						data.add(rs.getObject(i));
					}			
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return data;	
	}
	/**
	 * 检索满足一个条件的数据集合
	 * @param sqlStr sql语句
	 * @return 满足条件的数据集合
	 */
	public ArrayList<String> getTableCollection(String sqlStr){
		con = CommonJdbc.connection;
		vdata = new ArrayList<String>();
		try {
			rs = con.prepareStatement(sqlStr).executeQuery();
			while(rs.next()){
					vdata.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		return vdata;	
	}
	
	
	/**
	 * 生成表格数据模型
	 * @param name 表头
	 * @param sqlStr sql语句
	 * @return 表格模型
	 */
	public DefaultTableModel getTableModel(String[] name,String sqlStr){
		DefaultTableModel tableModel = new DefaultTableModel(name, 0);
		System.out.println("开始生成表格模型");
		con = CommonJdbc.connection;
		try {
			rs = con.prepareStatement(sqlStr).executeQuery();
			rsmd = rs.getMetaData();
			while(rs.next()){
				Vector<Object> vdata = new Vector<Object>();
				for(int i = 1;i<rsmd.getColumnCount() + 1;i++){
					vdata.addElement(rs.getObject(i));
				}
				tableModel.addRow(vdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tableModel;	
	}
}
