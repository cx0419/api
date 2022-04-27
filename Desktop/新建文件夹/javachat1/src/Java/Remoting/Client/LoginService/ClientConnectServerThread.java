package Java.Remoting.Client.LoginService;

import Java.Domain.pojo.Message;
import Java.Remoting.Common.MessageType;
import Java.Remoting.Server.LoginService.ManageClientThreads;
import Java.View.MainCoreView;
import javax.swing.text.html.ListView;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.List;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class ClientConnectServerThread extends Thread {
    //持有socket
    private Socket socket;
    //构造器,让这个对象持有一个socket
    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }
    //重写run方法
    @Override
    public void run() {
        while(true){
            System.out.println("客户端线程:等待读取从服务器发来的消息");
            try {
                //拿到socket的输入流
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                //读取流,服务器没有发送message则会卡在这
                Message message = (Message) ois.readObject();
                //得到了message

                //判断message类型,做出相应的业务处理

                //1.在线用户列表
                if(message.getMesType().equals(MessageType.MESSAGE_RET_ONLINEFRIEND)){//判断服务器返回的消息类型
                    //开一个字符串数组,将
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("=====当前在线用户列表如下====:");
                    for(int i = 0 ; i < onlineUsers.length ; i++){
                        System.out.println("用户:" + onlineUsers[i]);
                    }
                }

                //2.退出登录
                else if(message.getMesType().equals(MessageType.MESSAGE_CLIEN_EXIT)){
                    System.out.println(message.getSend_User_Id() + "即将退出登录...");
                    //将客户端在集合中彻底移除
                    ManageClientConnectServerThread.RemoveClientConnectServerThread(message.getSend_User_Id());
                    //关闭这个对象持有的socket流
                    socket.close();
                    System.out.println("退出成功!");
                    break;
                }

                //接受私聊消息
                else if(message.getMesType().equals(MessageType.MESSAGE_COMM_MES)){
                    //显示消息到客户端
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //更新JavaFX的主线程的代码放在此处
                            ((javafx.scene.control.ListView) MainCoreView.$("Chat_list")).getItems().add(df.format(System.currentTimeMillis())+"\t"+message.getSend_User_Id()+"对你说:"+message.getContent());
                        }
                    });
                    System.out.println(message.getSend_User_Id() + " " + message.getContent() + " " + message.getReceive_User_Id());

                }

                else {
                    System.out.println("其他类型的消息,暂不处理");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
