package model;
/*
 * 教师信息封装类
 */
public class Obj_teacher {
    private String teaid;
    private String classID;
    private String teaname;
    private String sex;
    private String knowledge;
    private String knowlevel;

    public Obj_teacher() {
        super();
    }

    public Obj_teacher(String teaid, String classID, String teaname, String sex, String knowledge, String knowlevel) {
        super();
        this.teaid = teaid;
        this.classID = classID;
        this.teaname = teaname;
        this.sex = sex;
        this.knowledge = knowledge;
        this.knowlevel = knowlevel;
    }

    public String getTeaid() {
        return teaid;
    }

    public void setTeaid(String teaid) {
        this.teaid = teaid;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getTeaname() {
        return teaname;
    }

    public void setTeaname(String teaname) {
        this.teaname = teaname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getKnowlevel() {
        return knowlevel;
    }

    public void setKnowlevel(String knowlevel) {
        this.knowlevel = knowlevel;
    }

}
