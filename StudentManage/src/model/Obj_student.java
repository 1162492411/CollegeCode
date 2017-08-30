package model;
/*
 * 学生信息封装类
 */
public class Obj_student {

    private String stuid;
    private String classID;
    private String stuname;
    private String sex;
    private int age;
    private String addres;
    private String phone;

    public Obj_student() {
        super();
    }

    public Obj_student(String stuid, String classID, String stuname, String sex, int age, String addres,
                       String phone) {
        super();
        this.stuid = stuid;
        this.classID = classID;
        this.stuname = stuname;
        this.sex = sex;
        this.age = age;
        this.addres = addres;
        this.phone = phone;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
