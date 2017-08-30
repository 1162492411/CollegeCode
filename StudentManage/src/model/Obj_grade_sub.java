package model;

/**
 * 学生成绩封装类
 * @author Mo
 *
 */
public class Obj_grade_sub {


    private String stuid;
    private String stuname;
    private String kindID;
    private String code;
    private float grade;

    public Obj_grade_sub() {
        super();
    }

    public Obj_grade_sub(String stuid, String stuname, String kindID, String code, float grade) {
        super();
        this.stuid = stuid;
        this.stuname = stuname;
        this.kindID = kindID;
        this.code = code;
        this.grade = grade;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getKindID() {
        return kindID;
    }

    public void setKindID(String kindID) {
        this.kindID = kindID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

}
