package Java.View;

import Java.Domain.pojo.User;
import Java.Remoting.Client.LoginService.Client_Login_service;
import Java.Remoting.Common.Creat_String;
import Java.Remoting.Common.SendEmail_Forget;
import Java.Remoting.Common.SendEmail_Register;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class ForgetController {
    @FXML
    private TextField AccountNumber;
    @FXML
    private PasswordField NewAccountPassword;
    @FXML
    private TextField Email;//邮箱输入
    @FXML
    private TextField Email_VerificationCode;//验证码输入
    @FXML
    private Button Reset;
    @FXML
    private Button SendVerificationCode_but;
    @FXML
    private Button Return_but;

    private String VerificationCode;

    public void Return_Login(){
        Stage stage =(Stage) Return_but.getScene().getWindow();
        stage.close();
        try {
            LoginView.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Send_VerificationCode() {
        String emailMatcher="[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        boolean isMatch = Pattern.matches(emailMatcher,Email.getText());
        if(isMatch){
            System.out.println("即将发送...");
        }else{
            System.out.println("邮箱格式不正确!!!");
            return;
        }
        try {
            VerificationCode = Creat_String.getCharAndNumr(6);
            SendEmail_Forget.sendEmail(Email.getText(),VerificationCode);
            System.out.println("发送成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //设计点击事件
    public void ForgetAccount () throws Exception {
        if(AccountNumber.getText().equals("")||NewAccountPassword.getText().equals("")||Email.getText().equals("")||Email_VerificationCode.getText().equals("")){
            System.out.println("请将信息输入完整!");
            return;
        }
        if(VerificationCode==null){
            System.out.println("请先发送验证码!");
            return;
        }
        User user = new User();
        user.setAccountNumber(AccountNumber.getText());
        user.setEmail(Email.getText());
        user.setAccountPassword(NewAccountPassword.getText());
        if(VerificationCode==null){
            System.out.println("请先发送验证码!");
        }
        if(!Email_VerificationCode.getText().equals(VerificationCode)){
            System.out.println("验证码错误!");
            return;
        }
        if (Client_Login_service.Forget_Check(user)){
            System.out.println("重置成功!");
        } else {
            System.out.println("重置失败!");
        }
    }
}
