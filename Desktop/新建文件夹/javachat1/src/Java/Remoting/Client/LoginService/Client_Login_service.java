package Java.Remoting.Client.LoginService;

import Java.Domain.pojo.Message;
import Java.Remoting.Common.MessageType;
import Java.Domain.pojo.User;

import javax.jws.soap.SOAPBinding;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 该类提供登录注册相关的服务方法
 */
public final class Client_Login_service {
    //在其他地方可能使用到user(拉取用户什么的)
    private static User user = new User();
    private static Socket socket;

    public Client_Login_service(){};

    public Client_Login_service(User user, Socket socket) {
        this.user = user;
        this.socket = socket;
    }

    //验证登录
    public static boolean Login_Check(String AccountNumber, String AccountPassword) throws Exception {
        boolean flag = false;
        user.setAccountNumber(AccountNumber);
        user.setAccountPassword(AccountPassword);
        //连接到服务端
        socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
        //得到objecoutputstream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        //发送对象,请求登录
        oos.writeObject(user);

        //读取从服务端回复的Message对象等待服务器发来消息)
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message)ois.readObject();
        if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){//登录成功
            //创建一个和服务器保持通讯的线程
            ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
            //启动线程
            ccst.start();
            //放入到线程集合方便管理
            ManageClientConnectServerThread.addClientConnectServerThread(AccountNumber,ccst);

            flag = true;
        }else{//登录失败
            //关闭
            socket.close();
            flag = false;
        }
    return flag;
    }

    //拉取用户列表,向服务器发送请求
    public static void onlineFriendList() throws Exception{
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINEFRIEND);
        message.setSend_User_Id(user.getAccountNumber());
        //发送给服务器
        //应该得到当前(由AccountNumber对应)线程的Socket 对应的 objectOutputSteam
        ObjectOutputStream oos = new ObjectOutputStream
                (ManageClientConnectServerThread.getClientConnectServerThread(user.getAccountNumber()).getSocket().getOutputStream());
        oos.writeObject(message);
    }

    //退出请求
    public static void LoginOut() throws Exception{
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_CLIEN_EXIT);
        message.setSend_User_Id(user.getAccountNumber());
        //应该得到当前(由AccountNumber对应)线程的Socket 对应的 objectOutputSteam
        ObjectOutputStream oos = new ObjectOutputStream
                (ManageClientConnectServerThread.getClientConnectServerThread(user.getAccountNumber()).getSocket().getOutputStream());
        //发送给服务器,请求下线
        oos.writeObject(message);
        //在集合中移除
        ManageClientConnectServerThread.RemoveClientConnectServerThread(user.getAccountNumber());
        //System.exit(0);
    }
    //注册
    public static boolean Register_Check(User user) throws Exception{
        boolean flag = false;
        user.setMessage(MessageType.MESSAGE_REGISTER);
        //send
        socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(user);

        //read
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        User user1 = (User) ois.readObject();
        if(user1.getMessage().equals(MessageType.MESSAGE_REGISTER_FAIL)){
            flag = false;
        }else if(user1.getMessage().equals(MessageType.MESSAGE_REGISTER_SUCCEED)){
            flag = true;
        }
        oos.close();
        ois.close();
        socket.close();
        return flag;
    }
    //修改密码
    public static boolean Forget_Check(User user) throws Exception{
        boolean flag = false;
        user.setMessage(MessageType.MESSAGE_FORGET);
        //send
        socket = new Socket(InetAddress.getByName("127.0.0.1"),9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(user);

        //read
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        User user1 = (User) ois.readObject();
        if(user1.getMessage().equals(MessageType.MESSAGE_RESETPSW_FAIL)){
            //接收到服务器发来的重置失败的消息,说明账号与邮箱不匹配
            flag = false;
        }else if(user1.getMessage().equals(MessageType.MESSAGE_RESETPSW_SUCCEEDD)){
            flag = true;
        }
        //关闭资源
        oos.close();
        ois.close();
        socket.close();
        return flag;
    }
}
