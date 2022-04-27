package Java.Domain.pojo;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String Id;
    private String Send_User_Id;
    private String Receive_User_Id;
    private String Content;
    private String Send_Time;
    private String MesType;//消息类型

    public Message() {

    }

    public Message(String id, String receive_User_Id, String content, String send_Time, String mesType) {
        Id = id;
        Receive_User_Id = receive_User_Id;
        Content = content;
        Send_Time = send_Time;
        MesType = mesType;
    }

    public String getSend_User_Id() {
        return Send_User_Id;
    }

    public void setSend_User_Id(String send_User_Id) {
        Send_User_Id = send_User_Id;
    }

    public String getReceive_User_Id() {
        return Receive_User_Id;
    }

    public void setReceive_User_Id(String receive_User_Id) {
        Receive_User_Id = receive_User_Id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSend_Time() {
        return Send_Time;
    }

    public void setSend_Time(String send_Time) {
        Send_Time = send_Time;
    }

    public String getMesType() {
        return MesType;
    }

    public void setMesType(String mesType) {
        MesType = mesType;
    }
}
