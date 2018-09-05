package MultiThread;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

///**
// * 读取服务器信息线程
// */
//
//class ReadFromServerThread implements Runnable{
//    private Socket client;
//
//    public ReadFromServerThread(Socket client) {
//        this.client = client;
//    }
//
//    @Override
//    public void run() {
//        try {
//            Scanner in = new Scanner(client.getInputStream());
//            in.useDelimiter("\n");
//            while(true){
//                if(in.hasNextLine()){
//                    System.out.println("服务器："+in.next());
//                }
//                if(client.isClosed()){
//                    System.out.println("客户端关闭连接");
//                    break;
//                }
//            }
//            in.close();
//        } catch (IOException e) {
////            e.printStackTrace();
//        }
//    }
//}
//
///**
// * 将信息发送给服务器线程
// */
//
//class WriteToServerThread implements Runnable{
//    private Socket client;
//
//    public WriteToServerThread(Socket client) {
//        this.client = client;
//    }
//
//    @Override
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        scanner.useDelimiter("\n");
//        try {
//            PrintStream out = new PrintStream(client.getOutputStream());
//            while(true){
//                System.out.println("请输入发送的信息：");
//                String strToServer ;
//                if(scanner.hasNextLine()){
//                    strToServer=scanner.nextLine().trim();
//                    out.println(strToServer);
//                    //设置退出标志位
//                    if(strToServer.equals("byebye")){
//                        System.out.println("客户端退出");
//                        out.close();
//                        scanner.close();
//                        client.close();
//                        break;
//                    }
//                }
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}


/**
 * 多线程版客户端
 */
public class Client {
    /**
     * 读取服务器信息线程
     */

     private static class ReadFromServerThread implements Runnable{
        private Socket client;

        public ReadFromServerThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                Scanner in = new Scanner(client.getInputStream());
                in.useDelimiter("\n");
                while(true){
                    if(in.hasNextLine()){
                        System.out.println("服务器："+in.next());
                    }
                    if(client.isClosed()){
                        System.out.println("客户端关闭连接");
                        break;
                    }
                }
                in.close();
            } catch (IOException e) {
//            e.printStackTrace();
            }
        }
    }

    /**
     * 将信息发送给服务器线程
     */

    private static class WriteToServerThread implements Runnable{
        private Socket client;

        public WriteToServerThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            scanner.useDelimiter("\n");
            try {
                PrintStream out = new PrintStream(client.getOutputStream());
                while(true){
                    System.out.println("请输入发送的信息：");
                    String strToServer ;
                    if(scanner.hasNextLine()){
                        strToServer=scanner.nextLine().trim();
                        out.println(strToServer);
                        //设置退出标志位
                        if(strToServer.equals("byebye")){
                            System.out.println("客户端退出");
                            out.close();
                            scanner.close();
                            client.close();
                            break;
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main (String [] args){
        try {
            Socket client = new Socket("127.0.0.1",6666);
            Thread read = new Thread(new ReadFromServerThread(client));
            Thread write = new Thread(new WriteToServerThread(client));
            read.start();
            write.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
