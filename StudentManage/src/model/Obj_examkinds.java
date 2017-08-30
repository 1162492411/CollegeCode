package model;
/**
 * 考试类别封装类
 */
public class Obj_examkinds {
    private String kindID;
    private String kindName;

    public Obj_examkinds() {
        super();
    }

    public Obj_examkinds(String kindID, String kindName) {
        super();
        this.kindID = kindID;
        this.kindName = kindName;
    }

    /**
     * 获取考试科目编号
     * @return kindID - 考试类别编号
     */
    public String getKindID() {
        return kindID;
    }

    /**
     * 设置考试科目编号
     * @param kindID  考试类别编号
     */
    public void setKindID(String kindID) {
        this.kindID = kindID;
    }

    /**
     * 设置考试类别名称
     * @return kindName - 考试类别编号
     */
    public String getKindName() {
        return kindName;
    }

    /**
     * 设置考试科目名称
     * @param kindName 考试类别名称
     */
    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

}
