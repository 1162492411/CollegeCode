package coursesDB;
import java.io.Serializable;
public class Courses implements Serializable {
	//属性
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String teacher;
	private int period;
	private int credit;
	private String remark;
	
	//构造器
	public Courses(){
		
	}
	
	public Courses(String id, String name, String teacher, int period, int credit, String remark) {
		
		this.id = id;
		this.name = name;
		this.teacher = teacher;
		this.period = period;
		this.credit = credit;
		this.remark = remark;
	}
	//get和set方法
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
