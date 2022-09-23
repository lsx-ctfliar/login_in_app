package com.example.util;

public class CodeMsg {
    private int code;
    private String msg;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(200, "success");
    public static CodeMsg Login_ERROR = new CodeMsg(500, "当前登录用户不存在");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问高峰期，请稍等！");
    //登录模块 5002XX
    public static CodeMsg LOGIN_ERROR = new CodeMsg(5002001, "账号或者密码错误");
    public static CodeMsg ACCOUNT_LOGOUT = new CodeMsg(5002002, "账号已经注销");
    public static CodeMsg REGISTER_ERROR = new CodeMsg(5002003, "注册账号失败");
    public static CodeMsg LOGIN_ID_ERROR = new CodeMsg(5002004, "此id不存在失败");
    public static CodeMsg UPDATE_INFO_ERROR = new CodeMsg(5002005, "修改用户信息失败");

    //工人模块 5003XX
    public static CodeMsg ADD_MANAGE_ERROR = new CodeMsg(5002000, "加入仓库工作失败");
    public static CodeMsg UPDATE_MANAGE_ERROR = new CodeMsg(5002001, "辞职工作失败");
//    public static CodeMsg UPDATE_ORDER_ERROR = new CodeMsg(5002000, "修改订单信息失败");


    //电商订单模块 5004XX
    public static CodeMsg ADD_ORDER_ERROR = new CodeMsg(5004000, "电商订单添加失败");
    public static CodeMsg UPDATE_ORDER_ERROR = new CodeMsg(5004001, "电商订单修改失败");
    public static CodeMsg SELECT_ORDER_ERROR = new CodeMsg(5004001, "电商订单查询失败");
    public static CodeMsg SELECT_WORKER_ERROR = new CodeMsg(5004001, "电商订单工人失败");
    public static CodeMsg SELECT_HOUSE_ERROR = new CodeMsg(5004001, "电商仓库查询失败");

    //仓库模块5005xx
    public static CodeMsg HOUSE_ADD_ERROR = new CodeMsg(500500, "仓库添加失败");
    public static CodeMsg HOUSE_ElECT_EXIST = new CodeMsg(500501, "仓库查询失败");
    public static CodeMsg HOUSE_UPDATE_ERROR = new CodeMsg(500502, "仓库更新失败");
    public static CodeMsg HOUSE_DELETE_ERROR = new CodeMsg(500503, "仓库删除失败");
    public static CodeMsg WORKER_ADD_ERROR = new CodeMsg(500505, "仓库员工添加失败");
    public static CodeMsg WORKER_SELECT_ERROR = new CodeMsg(500505, "仓库员工查看失败");
    public static CodeMsg TRADER_SELECT_ERROR = new CodeMsg(500506, "仓库电商租客查看失败");
    public static CodeMsg HOUSE_RENT_ERROR = new CodeMsg(500507, "仓库租用失败");
    public static CodeMsg UPDATE_WORKER_ERROR = new CodeMsg(500508, "修改工人信息失败");


    //管理员模块 5006XX
    public static CodeMsg HOUSE_ALL_ERROR = new CodeMsg(500600, "仓库查询失败");
    public static CodeMsg HOUSE_STATUS_ERROR = new CodeMsg(500601, "仓库状态修改失败");







    private CodeMsg() {
    }

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回带参数的错误码
     * @param args
     * @return
     */
    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }

}
