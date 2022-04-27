package Java.View;

import Java.Domain.pojo.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.io.IOException;

public class FriendListView {
    public static void Show_friend_list() {
        ListView<User> friend_list = ((javafx.scene.control.ListView<User>) MainCoreView.$("Chat_list"));
        friend_list.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
            @Override
            public ListCell<User> call(ListView<User> param) {
                return new ListCell<User>() {
                    @Override
                    protected void updateItem(User Item,boolean empty){
                        setStyle("-fx-background-color: transparent");
                        super.updateItem(Item,empty);
                        if(!empty){
                            HBox hbox = new HBox();
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("fxml/friendList.fxml"));
                            try {
                                hbox.getChildren().add(fxmlLoader.load());
                            } catch (IOException e) {
                                System.out.println("好友列表加载失败!");
                                e.printStackTrace();
                            }
                            FriendListController friendListController = fxmlLoader.getController();
                            friendListController.setAccountNumber(Item.getAccountNumber());
                            this.setGraphic(hbox);
                            System.out.println("saf");

                        }

                    }
                };
            }
        });
    }
}
