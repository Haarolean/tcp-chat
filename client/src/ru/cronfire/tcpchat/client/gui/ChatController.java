package ru.cronfire.tcpchat.client.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import ru.cronfire.tcpchat.client.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML private Pane mainPane;
    @FXML private FlowPane chatPane;
    @FXML private FlowPane usersPane;
    @FXML private TextField messageField;
    @FXML private Label serverIP;
    @FXML private Label serverStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void sendMessage() {
        final String message = messageField.getText();

        if(message.startsWith("/")) {
            handleCommand(message.substring(1,message.length()));

            return;
        }
        messageField.setText("");
    }

    private void handleCommand(final String command) {
        if(command.equals("disconnect")) {
            //disconnect
            Client.showLoginScene();
        }
        messageField.setText("");
    }

    private void clearChat() {
        chatPane.getChildren().removeAll();
    }

    private void addMessage(final String message, final String color) {
        Label l = new Label(message);
        l.setPrefWidth(chatPane.getWidth());
        l.setFont(Font.font("Arial"));
        l.getStyleClass().add("message");
        chatPane.getChildren().add(l);
    }

    private void addUser(final String nickname) {
        Label l = new Label(nickname);
        l.setFont(Font.font("Arial"));
        l.setMinWidth(usersPane.getWidth());
        l.setPrefWidth(usersPane.getWidth());
        l.getStyleClass().add("username");
        usersPane.getChildren().add(l);
    }
}
