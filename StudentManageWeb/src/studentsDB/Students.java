package studentsDB;

public class Students {
	private String id;
	private String name;
	private String sex;
	private String project;
	private String phone;
	private String remark;
	
	public Students(){
		
	}
	public Students(String id, String name, String sex, String project, String phone, String remark) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.project = project;
		this.phone = phone;
		this.remark = remark;
	}
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
