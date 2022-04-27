package Java.Remoting.Server.main;

import Java.Remoting.Server.LoginService.Server_Login_service;

public class ServerApplication {
    public static void main(String[] args) throws Exception{
        //这里用while,因为我开服务器是通过一个类的构造方法去完成的.
        while(true) {
            Server_Login_service server_login_service = new Server_Login_service();

        }
    }
}
