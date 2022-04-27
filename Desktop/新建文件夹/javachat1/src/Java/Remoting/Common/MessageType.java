package Java.Remoting.Common;

public interface MessageType {
    //不同的常量的值代表不同的消息类型

    String MESSAGE_LOGIN_SUCCEED = "1";
    String MESSAGE_LOGIN_FAIL = "2";

    String MESSAGE_COMM_MES = "3";//普通信息包

    String MESSAGE_GET_ONLINEFRIEND = "4";//(客户端)要求返回在线用户列表
    String MESSAGE_RET_ONLINEFRIEND = "5";//(服务端)返回在线用户列表

    String MESSAGE_CLIEN_EXIT = "6"; //客户端请求退出

    String MESSAGE_FORGET = "7";//忘记密码

    String MESSAGE_RESETPSW_FAIL = "8";//重置密码失败
    String MESSAGE_RESETPSW_SUCCEEDD = "9";//重置密码成功

    String MESSAGE_REGISTER = "10"; // 注册申请

    String MESSAGE_REGISTER_SUCCEED = "11";
    String MESSAGE_REGISTER_FAIL = "12";


}

