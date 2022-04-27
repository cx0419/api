package Java.Remoting.Client.Chat;

import Java.Remoting.Client.LoginService.ManageClientConnectServerThread;
import Java.Domain.pojo.Message;
import Java.Remoting.Common.MessageType;

import java.io.ObjectOutputStream;
import java.util.Date;

/**
 *该类提供和消息相关的服务方法
 *
 */
public class MessageClientService {
    public void SendMessageToOne(String Content,String Send_User_Id,String Receive_User_Id){
        //构件message
        Message message = new Message();
        message.setSend_User_Id(Send_User_Id);
        message.setReceive_User_Id(Receive_User_Id);
        message.setContent(Content);
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        message.setSend_Time(new Date().toString());
        //封装的消息
        try {
            ObjectOutputStream oos =
                    new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(Send_User_Id).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }


    }
}
