/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnwithstyle;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Alex
 */
public class TurnWithStyle {

    static String ip;
    static int port;
    java.net.ServerSocket serverSock;
    private Count c = new Count();
    
public void startServer() throws IOException{
        serverSock = new ServerSocket();
        serverSock.bind(new InetSocketAddress(ip,port));
        System.out.println("server started, listening on port: "+port);
        while(true){
            java.net.Socket socket = serverSock.accept();//Remember Blocking Call
            ServThread st = new ServThread(socket, c);
            st.start();

        }
    }
    public static void main(String[] args) throws IOException {
            port = 8088;
        ip = "localhost";
        new TurnWithStyle().startServer();
    
    }
}
