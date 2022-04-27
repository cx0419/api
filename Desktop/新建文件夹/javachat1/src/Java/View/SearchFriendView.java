package Java.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SearchFriendView {
    static Stage loginStage;
    public static void start(Stage primaryStage) throws IOException {
        loginStage = primaryStage;
        Parent root = FXMLLoader.load(SearchFriendView.class.getResource("fxml/SearchFriend.fxml"));
        primaryStage.setTitle("添加好友界面界面");
        primaryStage.setScene(new Scene(root,600,350));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
