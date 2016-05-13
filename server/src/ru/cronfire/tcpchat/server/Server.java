package ru.cronfire.tcpchat.server;

/*
 * COPYRIGHT (c) 2016 Haarolean (Roman Zabaluev)
 * This file is part of tcp-chat
 * Package: ru.cronfire.tcpchat.server
 * Date: 12.05.2016
 * Time: 17:40
 * DO NOT DISTRIBUTE.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static ServerSocket serverSocket = null;
    public static final List<ClientThread> clientThreads = new ArrayList<>();
    private static final int DEFAULT_PORT = 666; // Sorry DOOM
    private static boolean SERVER_IS_RUNNING = true;
    private static final String VERSION = "1.0.0";

    public static void main(String args[]) {

        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("Server v. " + VERSION +" started on port " + DEFAULT_PORT);
        } catch(IOException e) {
            e.printStackTrace();
        }

        while(SERVER_IS_RUNNING) { // wait for connections
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has connected from: " + clientSocket.getRemoteSocketAddress().toString());
                ClientThread t = new ClientThread(clientSocket);
                clientThreads.add(t);
                t.start();
            } catch(IOException e) {
                e.printStackTrace();
                SERVER_IS_RUNNING = false;
            }
        }
    }


}
