package scoresDB;

public class Scores {
	private String studentId;
	private String courseId;
	private float score;
	private int term;
	private String remark;
	
	public Scores(){
		
	}
	
	public Scores(String studentId, String courseId, float score, int term, String remark) {
		this.studentId = studentId;
		this.courseId = courseId;
		this.score = score;
		this.term = term;
		this.remark = remark;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
