package com.Yuj1010.SingleThread;

import java.net.ServerSocket;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static int port;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            //等待客户端连接
            System.out.println("等待客户端连接ing....");
            //服务器线程一直阻塞，直到有客户端连接，返回客户端连接Socket
            Socket client = serverSocket.accept();
            System.out.println("有客户端连接，客户端端口号为："+client.getPort());
            //获取客户端输出流，向客户端输出信息
            PrintStream out = new PrintStream(client.getOutputStream());
            //获取客户端输出流，读取客户端消息
            Scanner in = new Scanner(client.getInputStream());
            if(in.hasNextLine()){
                System.out.println("客户端发来的信息为："+in.nextLine());
            }
            out.println("Hello, I am sever");
            in.close();
            out.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("连接建立失败，异常为："+e);
        }

    }
}
