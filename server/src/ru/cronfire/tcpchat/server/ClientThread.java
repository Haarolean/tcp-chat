package ru.cronfire.tcpchat.server;

/*
 * COPYRIGHT (c) 2016 Haarolean (Roman Zabaluev)
 * This file is part of tcp-chat
 * Package: ru.cronfire.tcpchat.server
 * Date: 14.05.2016
 * Time: 1:02
 * DO NOT DISTRIBUTE.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread extends Thread {

    private String clientName = null;
    private PrintStream os = null;
    private final Socket clientSocket;
    private final String address;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.address = clientSocket.getRemoteSocketAddress().toString();
    }

    public void run() {
        try {
            // streams for the client
            final BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            os = new PrintStream(clientSocket.getOutputStream());

            // ask to enter the nickname
            os.println("Welcome! Please, enter your name");
            clientName = is.readLine().trim();

            // say hullo
            os.println("Nice to meet you, " + clientName + ".\nTo leave enter /quit in a new line.");
            synchronized(this) {
                Server.clientThreads.forEach((t -> {
                    if(!t.equals(this)) t.os.println("***" + clientName + " has entered the chat");
                }));
            }

            // start the chat
            while(true) {
                String line = is.readLine();
                if(line.startsWith("/quit")) {
                    break;
                }
                synchronized(this) {
                    Server.clientThreads.forEach((t -> {
                        if(t.clientName != null) t.os.println(clientName + ": " + line); // send message
                    }));
                }
            }

            //bye-bye
            synchronized(this) {
                Server.clientThreads.forEach((t -> {
                    if(!t.equals(this) && t.clientName != null) t.os.println("*** User " + clientName + " has left");
                }));
            }
            os.println("Goodbye, " + clientName + "!"); // say him goodbye
            System.out.println("Client disconnected: " + address);

            // clean up the list
            synchronized(this) {
                Server.clientThreads.remove(this);
            }

            // close all the stuff
            is.close();
            os.close();
            clientSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        ClientThread that = (ClientThread) o;

        if(clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        return !(address != null ? !address.equals(that.address) : that.address != null);

    }

    @Override
    public int hashCode() {
        int result = clientName != null ? clientName.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

}
