package Java.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainCoreView {
    static Stage loginStage;
    static Parent root;
    public static void start(Stage primaryStage) throws IOException{
        loginStage = primaryStage;
        root = FXMLLoader.load(MainCoreView.class.getResource("fxml/MainCore.fxml"));
        primaryStage.setTitle("主界面");
        primaryStage.setScene(new Scene(root,1400,700));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    public static Object $(String id) {
        return (Object) root.lookup("#" + id);
    }
}


