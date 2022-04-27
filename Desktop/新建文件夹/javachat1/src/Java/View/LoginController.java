package Java.View;

import Java.Remoting.Client.LoginService.Client_Login_service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button RetrievePassword_but;
    @FXML
    private Button LoginClose_but;
    @FXML
    private Button Register_but;
    @FXML
    private Button Login_but;
    @FXML
    private TextField AccountNumber;
    @FXML
    private TextField AccountPassword;
    //进入注册界面
    public  void  CreatRegister (ActionEvent event){
        Stage stage =(Stage) LoginClose_but.getScene().getWindow();
        stage.close();
        try {
            RegisterView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //关闭界面
    public  void closely(ActionEvent event){
        Stage stage =(Stage) LoginClose_but.getScene().getWindow();
        stage.close();

    }
    //找回密码界面
    public  void  CreatRetrievePassword (ActionEvent event){
        Stage stage =(Stage) Login_but.getScene().getWindow();
        stage.close();
        try {
            ForgetView.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //登录界面
    public  void  CreatMainCore (ActionEvent event) throws Exception{
        //验证登录
        if(Client_Login_service.Login_Check(AccountNumber.getText(), AccountPassword.getText() )){
            //欢迎
            System.out.println("欢迎用户:" + AccountNumber.getText()+"!");
            //为Maincore中的静态变量传参数
            MainCoreController.setAccountNumber(AccountNumber.getText());
            //打开该客户端界面
            Stage stage =(Stage) Login_but.getScene().getWindow();
            stage.close();
            try {

                MainCoreView.start(new Stage());
                FriendListView.Show_friend_list();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("登录成功!");
        }else{
            //显示登录失败
            System.out.println("登录失败!");
        }
    }

}
