package MultiThread;


import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.PrimitiveIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Server{
    //存储注册的用户
    private static Map<String , Socket> clientMap = new ConcurrentHashMap<>();

    //处理客户端通信
    private static class ExecuteClient implements Runnable{
        private Socket client;

        public ExecuteClient(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                Scanner in = new Scanner(client.getInputStream());
                String strFromClient;
                while(true){
                    if(in.hasNextLine()){
                        strFromClient=in.nextLine();
                        //将windows下换行符\r\n中的\r换为空字符串
                        Pattern pattern = Pattern.compile("\r");
                        Matcher matcher = pattern.matcher(strFromClient);
                        strFromClient = matcher.replaceAll("");

                        //注册
                        //userName:Name
                        if(strFromClient.startsWith("userName")){
                            String userName=strFromClient.split("\\:")[1];
                            registerUser(userName,client);
                            continue;
                        }
                        //群聊
                        //G:msg
                        if(strFromClient.startsWith("G")){
                            String msg=strFromClient.split("\\:")[1];
                            groupChat(msg);
                            continue;
                        }
                        //私聊
                        //P:Name-msg
                        if(strFromClient.startsWith("P")){
                            String userName=strFromClient.split("\\:")[1].split("-")[0];
                            String msg=strFromClient.split("\\:")[1].split("-")[1];
                            privateChat(userName,msg,client);
                        }
                        //下线
                        if(strFromClient.startsWith("byebye")){
                            String userName=null;
                            for (String keyName:clientMap.keySet()) {
                                if(clientMap.get(keyName).equals(client)){
                                    userName=keyName;
                                }
                            }
                            System.out.println("用户"+userName+"已下线！！！");
//                            clientMap.remove(userName);
                            continue;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //注册方法
    private static void registerUser(String userName,Socket client){
        //将信息保存到clientMap中
        clientMap.put(userName,client);
        System.out.println("用户"+userName+"上线了");
        System.out.println("当前聊天室人数："+clientMap.size());
        try {
            PrintStream out = new PrintStream(client.getOutputStream(),true,"UTF-8");
            out.println("注册成功:)！！！");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("注册成功:)！！！");
    }

    //群聊方法
    private static void groupChat(String msg){
        //首先遍历Map，取出中的所有用户的socket，
        Set<Map.Entry<String ,Socket>> clientset = clientMap.entrySet();
        for (Map.Entry<String , Socket> entry: clientset) {
            Socket socket = entry.getValue();
            try {
                PrintStream out = new PrintStream(socket.getOutputStream(),true,"UTF-8");
                out.println("群消息为："+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //私聊方法
    private static void privateChat(String userName,String msg,Socket socket){
        Socket privatesocket=clientMap.get(userName);
        String FriendName=null;
        for (String keyName:clientMap.keySet()) {
            if(clientMap.get(keyName).equals(socket)){
                FriendName=keyName;
            }
        }
        try {
            PrintStream out = new PrintStream(privatesocket.getOutputStream(),true,"UTF-8");
            out.println("好友-"+FriendName+"发来消息："+msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args){
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            for(int i=0;i<8;i++){
                System.out.println("等待客户端连接.....");
                Socket client = serverSocket.accept();
                System.out.println("客户端连接，端口号为："+client.getPort());
                executorService.submit(new ExecuteClient(client));
            }
            serverSocket.close();
            executorService.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

