package ru.cronfire.tcpchat.client.gui;

/*
 * COPYRIGHT (c) 2016 Haarolean (Roman Zabaluev)
 * This file is part of tcp-chat
 * Package: ru.cronfire.tcpchat.client.gui
 * Date: 12.05.2016
 * Time: 19:37
 * DO NOT DISTRIBUTE.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.cronfire.tcpchat.client.Main;
import ru.cronfire.tcpchat.client.Utils;
import ru.cronfire.tcpchat.client.implementations.ChatClient;

import java.io.IOException;

public class LoginController {

    private static final int DEFAULT_PORT = 666; // Sorry DOOM

    @FXML private TextField serverField;
    @FXML private Button connectButton;

    public void start() {
        connectButton.requestFocus();
    }

    @FXML
    private void connect() {
        try {
            String ip = serverField.getText();
            final int port = ip.contains(":") ? Integer.parseInt(ip.split(":")[1]) : DEFAULT_PORT;
            ip = ip.contains(":") ? ip.split(":")[0] : ip;
            ChatController.setChatClient(new ChatClient(ip, port));
            Main.showChatScene();
        } catch(IOException e) {
            Utils.showAlertWindow("Ошибка подключения",
                    "Подключение не удалось: " + e.getClass().getName() + ": " + e.getMessage());
        } catch(Exception e) {
            Utils.showExceptionDialog(e);
        }
    }

}
