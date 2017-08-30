package model;
/*
 * 课程信息封装类
 */
public class Obj_subject {

    private String code;
    private String subject;

    public Obj_subject() {
        super();
    }

    public Obj_subject(String code, String subject) {
        super();
        this.code = code;
        this.subject = subject;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
