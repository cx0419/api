package Java.View;

import Java.Domain.pojo.User;
import Java.Remoting.Client.Chat.MessageClientService;
import Java.Remoting.Client.LoginService.Client_Login_service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sun.plugin2.jvm.RemoteJVMLauncher;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class MainCoreController {
    @FXML
    private Button Return_Login;
    @FXML
    private Button Return_onlineUser;
    @FXML
    private Button Send;
    @FXML
    private TextField Content;
    @FXML
    public ListView Chat_list;
    @FXML
    private ListView<User> friend_list;

    private static String AccountNumber;

    //对外暴露
    public static void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    //退出登录
    public void ExitLogin() throws Exception{
        Client_Login_service.LoginOut();
    }

    //返回登录界面
    public void ReturnLogin(){
        Stage stage =(Stage) Return_Login.getScene().getWindow();
        stage.close();
        try {
            ExitLogin();
            LoginView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取在线用户列表
    public void GetonlineUsers() throws Exception{
        Client_Login_service.onlineFriendList();
    }

    //发送消息
    public void SentContent() throws Exception{
//        ObservableList<String> list = FXCollections.observableArrayList();
//        list.add("姓名：杨先生	年龄：18	性别：男");
//        list.add("姓名：杨女生1	年龄：18	性别：女");
//        list.add("姓名：杨女生2	年龄：18	性别：女");
//        list.add("姓名：杨女生3	年龄：18	性别：女");
//        list.add("姓名：杨女生4	年龄：18	性别：女");
//        Chat_list = new ListView<String>();
//        //占位符 当listview没有数据时显示占位符
//        Chat_list.setPlaceholder(new Label("没有数据"));
//        //添加一个可观测的列表显示
//        Chat_list.setItems(list);
//        Chat_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(System.currentTimeMillis()));
        Chat_list.getItems().add(df.format(System.currentTimeMillis())+"\t"+"你对"+"200说:"+Content.getText());
        new MessageClientService().SendMessageToOne(Content.getText(),AccountNumber,"200");
    }
    public void ShowText(String content){
        Chat_list.getItems().add(content);
    }

    public void Add_Friend(){

    }
}

