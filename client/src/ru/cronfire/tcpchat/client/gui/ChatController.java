package ru.cronfire.tcpchat.client.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ru.cronfire.tcpchat.client.Main;
import ru.cronfire.tcpchat.client.implementations.ChatClient;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable{

    private static ChatClient chatClient = null;

    @FXML private TextArea chatArea;
    @FXML private TextField messageField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.getStage().setOnCloseRequest((event) -> {if(chatClient != null) chatClient.close();});
    }

    public static void setChatClient(final ChatClient chatClient) {
        ChatController.chatClient = chatClient;
    }

    @FXML
    private void sendMessage() {
        chatClient.send(messageField.getText());
        messageField.setText("");
    }

    public void addMessage(final String message) {
        Platform.runLater(() -> chatArea.setText(chatArea.getText() + message + "\n"));
    }

}
