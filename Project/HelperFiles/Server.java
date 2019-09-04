/*
Scott Holley
CS470
Project 1: Client/Server update connection

Purpose: 
    Connect with client via IP and Port;
    Display message from clinet as well as clients IP
    Return newest software if client agrees
*/



/*
Server should compare version on client and ask if they want to update
If yes, send the update and overwrite, if no, do nothing
*/

// TODO: 
// Server should start first so client automatically connects
// Client should send software version to server
// Server should display message from client and contain ip address from it
// Server should send new version to client
// Client should choose whether or not to update
// File Name Server.java
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Server extends Thread {
    private ServerSocket serverSocket;

    
    public Server(int port) throws IOException {
    // Initializing the server
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(30000);
    }

    public void run() {
    while(true) {
    try {
        
    System.out.println("Waiting for client on port " +
    serverSocket.getLocalPort() + "...");
    // Waiting for connection
    Socket server = serverSocket.accept();
    // IP Address and client port number
    System.out.println("Connected to IP: " + server.getInetAddress());
    // Reading the stream sent by the client
    DataInputStream in = new DataInputStream(server.getInputStream());
    System.out.println(in.readUTF());
    // Sending back the newer version
    String soft_vers = " 1.2";
    DataOutputStream out = new DataOutputStream(server.getOutputStream());
    out.writeUTF("You are now connected to: " + server.getLocalSocketAddress()
    + "\nSoftware has been updated to version: " + soft_vers);
    server.close();   

    } catch (SocketTimeoutException s) {
    System.out.println("Socket timed out!");
        break;
    } catch (IOException e) {
    e.printStackTrace();
        break;
        }   
    }
}

public static void main(String [] args) {
    int port = 12896;
        try {
    Thread t = new Server(port);
    t.start();
    } catch (IOException e) {
    e.printStackTrace();
        }
    }
}