package ru.cronfire.tcpchat.client.implementations;

/*
 * COPYRIGHT (c) 2016 Haarolean (Roman Zabaluev)
 * This file is part of tcp-chat
 * Package: ru.cronfire.tcpchat.client.implementations
 * Date: 13.05.2016
 * Time: 18:35
 * DO NOT DISTRIBUTE.
 */

import ru.cronfire.tcpchat.client.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ChatClient {

    private Socket socket;
    private OutputStream outputStream;
    private static final String CRLF = "\r\n"; // newline

    public ChatClient(final String server, final int port) throws IOException {
        socket = new Socket(server, port);
        outputStream = socket.getOutputStream();

        new Thread() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line;
                    while((line = reader.readLine()) != null)
                        Main.getChatController().addMessage(line);
                } catch(IOException e) {
                    Main.getChatController().addMessage(e.getClass().getName() + e.getMessage());
                }
            }
        }.start();
    }

    public void send(String text) {
        try {
            outputStream.write((text + CRLF).getBytes());
            outputStream.flush();
        } catch(IOException e) {
            Main.getChatController().addMessage(e.getClass().getName() + e.getMessage());
        }
    }

    public void close() {
        try {
            socket.close();
        } catch(IOException e) {
            Main.getChatController().addMessage(e.getClass().getName() + e.getMessage());
        }
    }

}
