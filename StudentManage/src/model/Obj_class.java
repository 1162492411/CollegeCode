package model;
/**
 * 班级信息封装类
 * @author Mo
 */
public class Obj_class {
    private String classID;
    private String gradeID;
    private String className;

    public Obj_class() {
        super();
    }

    public Obj_class(String classID, String gradeID, String className) {
        super();
        this.classID = classID;
        this.gradeID = gradeID;
        this.className = className;
    }

    /**
     * 获取班级编号
     * @return classID - 班级编号
     */
    public String getClassID() {
        return classID;
    }

    /**
     * 设置班级编号
     * @param classID 班级编号
     */
    public void setClassID(String classID) {
        this.classID = classID;
    }

    /**
     * 获取年级编号
     * @return gradeID - 年级编号
     */
    public String getGradeID() {
        return gradeID;
    }

    /**
     * 设置年级编号
     * @param gradeID 年级编号
     */
    public void setGradeID(String gradeID) {
        this.gradeID = gradeID;
    }

    /**
     * 获取班级名称
     * @return className - 班级名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置班级名称
     * @param className 班级名称
     */
    public void setClassName(String className) {
        this.className = className;
    }

}
