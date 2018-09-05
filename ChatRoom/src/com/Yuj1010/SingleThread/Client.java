package com.Yuj1010.SingleThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String [] args){
        try {
            //创建客户端连接服务器
            Socket client = new Socket("127.0.0.1",6666);
            System.out.println("连接服务器成功！服务器地址为："+client.getInetAddress());
            //获取输入、输出流
            PrintStream out = new PrintStream(client.getOutputStream(),true,"UTF-8");
            Scanner in = new Scanner(client.getInputStream());
            in.useDelimiter("\n");
            //向服务器发送消息
            out.println("hello,i am client!!!");
            if(in.hasNextLine()){
                System.out.println("服务器消息："+ in.next());
            }
            in.close();
            out.close();
            client.close();
        } catch (IOException e) {
            System.err.println("通信异常："+ e);
        }
    }

}
