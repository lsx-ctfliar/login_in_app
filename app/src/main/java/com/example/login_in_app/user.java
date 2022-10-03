package com.example.login_in_app;
public class user{
    //个人用户信息加载界面的信息，用来获取信息，在别的java文件上面使用
    private static String id;
    private static String userName;
    private String roleid;
    private  String realName;
    private  String idNumber;
    private  String collegeName;
    private  String gender;
    private  String phone;
    private  String avatar;
    private  String email;
    private  String inSchooltime;

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        user.id = id;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        user.userName = userName;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInSchooltime() {
        return inSchooltime;
    }

    public void setInSchooltime(String inSchooltime) {
        this.inSchooltime = inSchooltime;
    }
}
