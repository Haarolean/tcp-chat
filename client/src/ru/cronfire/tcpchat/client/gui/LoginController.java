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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ru.cronfire.tcpchat.client.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private TextField serverField;
    @FXML private TextField nicknameField;
    @FXML private Button connectButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void start() {
        connectButton.requestFocus();
    }

    @FXML
    private void connect() {
        Client.showChatScene();
    }




}
