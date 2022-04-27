package Java.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterView {
    static Stage loginStage;
    public static void start(Stage primaryStage) throws IOException {
        loginStage = primaryStage;
        Parent root = FXMLLoader.load(RegisterView.class.getResource("fxml/Register.fxml"));
        primaryStage.setTitle("注册界面");
        primaryStage.setScene(new Scene(root,840,530));
        primaryStage.show();
    }
}
