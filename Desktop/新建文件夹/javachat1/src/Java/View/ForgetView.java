package Java.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ForgetView {
    static Stage loginStage;
    public static void start(Stage primaryStage) throws IOException {
        loginStage = primaryStage;
        Parent root = FXMLLoader.load(ForgetView.class.getResource("fxml/Forget.fxml"));
        primaryStage.setTitle("重置密码");
        primaryStage.setScene(new Scene(root,700,450));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
