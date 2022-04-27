package Java.View;
import java.util.regex.Pattern;

import Java.Domain.pojo.User;
import Java.Remoting.Client.LoginService.Client_Login_service;
import Java.Remoting.Common.Creat_String;
import Java.Remoting.Common.SendEmail_Register;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    private TextField anonymous_Name;
    @FXML
    private PasswordField AccountPassword;
    @FXML
    private TextField Age;
    @FXML
    private TextField Email;//邮箱输入
    @FXML
    private TextField Email_VerificationCode;//验证码输入
    @FXML
    private Button FinshedRegister;
    @FXML
    private Button SendVerificationCode_but;
    @FXML
    private Button Return_but;

    public static String VerificationCode;
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
                SendEmail_Register.sendEmail(Email.getText(),VerificationCode);
                System.out.println("发送成功!");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

        public void RegisterAccount () throws Exception {
            if(AccountPassword.getText().equals("")||Email_VerificationCode.getText().equals("")||Email_VerificationCode.getText().equals("")){
                System.out.println("请将信息输入完整!");
                return;
            }
            if(VerificationCode==null){
                System.out.println("请先发送验证码!");
                return;
            }
            User user = new User();
            user.setName(anonymous_Name.getText());
            user.setAccountNumber("");
            user.setAccountPassword(AccountPassword.getText());
            user.setAge(Age.getText());
            user.setEmail(Email.getText());
            if(!Email_VerificationCode.getText().equals(VerificationCode)){
                System.out.println("验证码错误!");
                return;
            }
            if (Client_Login_service.Register_Check(user)){
                System.out.println("注册成功!");
            } else {
                System.out.println("注册失败!");
            }
        }


}
