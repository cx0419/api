package Java.MAIN;
import Java.View.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;
//管理客户端连接到服务器的线程的类

public class Main {
    public static class View extends Application {
        public static void main(String[] args){
            launch(args);
        }
        @Override
        public void start(Stage primaryStage) throws Exception{
            LoginView.start(primaryStage);
        }
    }
}
