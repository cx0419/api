package Java.Remoting.Server.LoginService;

import Java.Domain.pojo.Message;
import Java.Remoting.Common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*
 *该类对应的对象和某个客户端保持连接,
 */
public class ServerConnectClientThread extends Thread{
    //该线程持有socket(账号:accounumber)
    private String accountNumber;
    private Socket socket;

    //构造器
    public ServerConnectClientThread(String AccountNumber, Socket socket) {
        this.accountNumber = AccountNumber;
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    @Override
    public void run(){
        while(true){
            try {
                System.out.println("服务端和客户端 "+accountNumber+" 保持通讯,读取数据...");
                //拿到输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //拿到消息
                Message message = (Message) ois.readObject();
                //在服务器中显示收到了何种消息
                System.out.println("服务器获得指令" + message.getMesType());
                //后面会用到message

                //1.客户端要求拉取在线用户
                if(message.getMesType().equals(MessageType.MESSAGE_GET_ONLINEFRIEND)){
                    //制表
                    System.out.println(message.getSend_User_Id() + " 要在线用户列表");
                    //通过该类获取到用户列表
                    String onlineUser = ManageClientThreads.getOnlineUser();
                    //返回messsage给客户端
                    Message message2 = new Message();
                    message2.setMesType(MessageType.MESSAGE_RET_ONLINEFRIEND);
                    message2.setContent(onlineUser);
                    message2.setReceive_User_Id(message.getSend_User_Id());//即是发送者又是接受者
                    //返回给客户端
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message2);
                }

                //2.退出登录
                else if(message.getMesType().equals(MessageType.MESSAGE_CLIEN_EXIT)){
                    //删除服务器中这条线程的记录
                    ManageClientThreads.RemoveServerConnectionThread(message.getSend_User_Id());
                    //先删除
                    ObjectOutputStream oos1 = new ObjectOutputStream(socket.getOutputStream());
                    //再发送回给客户端,在客户端线程中关掉socket
                    oos1.writeObject(message);
                    //退出循环,断掉run方法,删除这条线程,等待下次从登录界面再次建立连接
                    break;
                }

                //3.私发消息
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    //先拿到接收者的线程
                    ServerConnectClientThread serverConnectClientThread =
                            ManageClientThreads.getServerConnectClientThread(message.getReceive_User_Id());
                    //再来拿oos
                    ObjectOutputStream oos =
                            new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    //发给接收者客户端,如果客户不在线,保存到数据库.
                    oos.writeObject(message);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
