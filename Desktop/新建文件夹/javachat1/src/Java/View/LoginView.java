package Java.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoginView {
    static Stage loginStage;
    public static void start(Stage primaryStage) throws IOException{
        loginStage = primaryStage;
        Parent root = FXMLLoader.load(LoginView.class.getResource("fxml/Login.fxml"));
        primaryStage.setTitle("登录界面");
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

}


