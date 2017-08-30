package model;
/*
 * 年级信息封装类
 */
public class Obj_grade {

    private String gradeID;
    private String gradeName;

    public Obj_grade() {
        super();
    }

    public Obj_grade(String gradeID, String gradeName) {
        super();
        this.gradeID = gradeID;
        this.gradeName = gradeName;
    }

    public String getGradeID() {
        return gradeID;
    }

    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

}
