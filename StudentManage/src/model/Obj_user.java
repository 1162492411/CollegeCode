package model;
/*
 * 用户信息封装类
 */
public class Obj_user {
    private String userid;
    private String username;
    private String pass;

    public Obj_user() {
        super();
    }

    public Obj_user(String userid, String username, String pass) {
        super();
        this.userid = userid;
        this.username = username;
        this.pass = pass;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
