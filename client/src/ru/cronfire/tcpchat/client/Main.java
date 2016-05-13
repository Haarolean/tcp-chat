package ru.cronfire.tcpchat.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.cronfire.tcpchat.client.gui.ChatController;
import ru.cronfire.tcpchat.client.gui.LoginController;
import ru.cronfire.tcpchat.client.resources.Resources;

public class Main extends Application {

    private static Scene loginScene;
    private static Scene chatScene;

    private static Stage stage;
    private static LoginController loginController;
    private static ChatController chatController;
    private static final String VERSION = "1.0.1";

    @Override
    public void start(Stage stage) throws Exception {
        Main.stage = stage;
        FXMLLoader loader = new FXMLLoader(Resources.getResource("fxml/login.fxml"));
        Parent root = loader.load();
        loginScene = new Scene(root);


        stage.setScene(loginScene);

        stage.setTitle("Chat v. " + VERSION);
        stage.getIcons().add(new Image(Resources.getResourceAsStream("images/chat.png")));

        stage.setResizable(false);
        stage.sizeToScene(); // fix for Windows® bug

        stage.show();

        loginController =  loader.getController();
        loginController.start();

        loader = new FXMLLoader(Resources.getResource("fxml/chat.fxml"));
        root = loader.load();
        chatScene = new Scene(root);
        chatController = loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void showLoginScene() {
        stage.setScene(loginScene);
    }

    public static void showChatScene() {
        stage.setScene(chatScene);
    }

    public static Stage getStage() {
        return Main.stage;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static ChatController getChatController() {
        return chatController;
    }

}
