package Java.Remoting.Server.LoginService;

import Java.Dao.Forget;
import Java.Dao.Login;
import Java.Dao.Register;
import Java.Domain.pojo.Message;
import Java.Remoting.Common.Creat_String;
import Java.Remoting.Common.MessageType;
import Java.Domain.pojo.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static Java.Dao.Forget.Check_UserAccountNumberAndEmail;
import static Java.Dao.Forget.Check_UserAccountNumberAndEmail;
import static javafx.beans.binding.Bindings.select;

public class Server_Login_service {
    private ServerSocket ss = null;

    private boolean Check_User(String AccountNumber,String AccountPassword){
        try {
            return Login.Check_AccountNumberAndAccountPassword(AccountNumber,AccountPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return false;
    }
    private boolean Check_UserEmail(User user){
        try {
            return Register.Inquire_Email(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //该类的一个构造器
    public Server_Login_service(){
        try {
            //服务期正在监听
            ss = new ServerSocket(9999);
            //循环接受客户端发来的消息
            while (true) {
                //如果没有客户端连接,则会阻塞在accept
                System.out.println("服务器正在监听");
                Socket socket = ss.accept();
                System.out.println("监听完毕");
                //得到socket的输入流(等客户端发来消息)
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User)ois.readObject();//读取到客户端发送的User对象
                System.out.println(user.getEmail());
                //拿到该socket的输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                //创建一个Message对象,回复给客户端
                Message message = new Message();
                //处理注册
                if(user.getMessage()!=null)
                if(user.getMessage().equals(MessageType.MESSAGE_REGISTER))
                {
                    //去数据库检查是否重复!
                    if(Check_UserEmail(user)){
                        user.setAccountNumber(Creat_String.getNum(10));
                        user.setMessage(MessageType.MESSAGE_REGISTER_SUCCEED);
                        Register.Creat_NewUser(user);
                        System.out.println("账号:" + user.getAccountNumber() + "已经注册!");
                    }else{
                        user.setAccountNumber(MessageType.MESSAGE_RESETPSW_FAIL);
                        System.out.println("账号:" + user.getAccountNumber() + "尝试注册但是失败了!");
                    }
                    //传给客户端
                    oos.writeObject(user);
                    continue;
                }

                //处理忘记密码
                if(user.getMessage()!=null)
                if(user.getMessage().equals(MessageType.MESSAGE_FORGET))
                {
                    //去数据库查询是否存在此账号数据
                    if(Check_UserAccountNumberAndEmail(user)){
                        //修改此账号在数据库中密码
                        Forget.Reset_AccountPassword(user);
                        System.out.println("账号:" + user.getAccountNumber() + "已经修改密码!");
                        user.setMessage(MessageType.MESSAGE_RESETPSW_SUCCEEDD);
                    }else{
                        user.setMessage(MessageType.MESSAGE_RESETPSW_FAIL);
                        System.out.println("账号:" + user.getAccountNumber() + "尝试修改密码但失败了!(原因:user中账号与email不匹配)");
                    }
                    //传给客户端
                    oos.writeObject(user);
                    continue;
                }
                //处理登录
                //在数据库中查找该账号密码是否匹配
                if(Check_User(user.getAccountNumber(),user.getAccountPassword())){//登陆成功
                    message.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    System.out.println("用户:" + user.getAccountNumber()+"已经登录!");
                    //把登录成功的消息传给客户端.
                    oos.writeObject(message);
                    //线程要持有socket对象(创建一个线程实例,并初始化)
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(user.getAccountNumber(),socket);
                    //将线程放入到一个集合中进行管理
                    ManageClientThreads.addServerConnectClientThread(user.getAccountNumber(),serverConnectClientThread);
                    //启动线程
                    serverConnectClientThread.start();
                }else {//登录失败
                    message.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //如果服务端退出了while循环,说明服务器端不再监听了,需要关闭资源
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
