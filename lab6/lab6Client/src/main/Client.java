package main;

import main.Managers.CommandProvider;
import main.ResponseAndRequest.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class Client {

    public static void main(String[] args) throws Exception{
        CommandProvider cp = new CommandProvider();

        Scanner scanner = new Scanner(System.in);
        int port = 8000;

        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        ByteBuffer buffer;
        InetAddress host  = InetAddress.getByName("127.0.0.1");
        InetSocketAddress serverAddr = new InetSocketAddress(host, port);


        byte[] bytes;
        boolean run = true;


        while(run) {
            System.out.print("> ");
            bytes = cp.getCommand(scanner.nextLine(), datagramChannel, serverAddr);
            if (bytes == null) {
                run = false;
            } else if (bytes.length > 1) {

                buffer = ByteBuffer.wrap(bytes);
                datagramChannel.send(buffer, serverAddr);
                buffer.clear();
                buffer = ByteBuffer.allocate(100);
                //получение ответа
                Thread.sleep(500);
                SocketAddress connectWithServer = datagramChannel.receive(buffer);
                if (connectWithServer != null) {
//                    System.out.println(ByteBuffer.wrap(buffer.array()).getInt());
//                    buffer = ByteBuffer.allocate(ByteBuffer.wrap(buffer.array()).getInt());
//                    timer.cancel();
//                    timerIsRunning = false;
//                    buffer.clear();
//                    datagramChannel.receive(buffer);
//                    ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer.array());
//                    ObjectInputStream objStream = new ObjectInputStream(byteStream);
//                    Response response = (Response) objStream.readObject();
//                    System.out.println(response.getRespose());
//                    buffer.clear();
                    while (bytes.length != 1){
                        if(buffer.array()[0]!=2 && buffer.array()[1]!=2 && buffer.array()[2]!=8){
                            responseBytes.write(buffer.array());
                            buffer = ByteBuffer.allocate(100);
                            datagramChannel.receive(buffer);
                        }else {
                            ByteArrayInputStream byteStream = new ByteArrayInputStream(responseBytes.toByteArray());
                            ObjectInputStream objStream = new ObjectInputStream(byteStream);
                            Response response = (Response) objStream.readObject();
                            System.out.println(response.getRespose());
                            responseBytes = new ByteArrayOutputStream();
                            break;
                        }
                    }

                }
            } else if (bytes.length == 1) {
                for(String s: cp.getScriptLines()){
                    System.out.println(s);
                    bytes = cp.getCommand(s, datagramChannel, serverAddr);
                    if (bytes == null) {
                        run = false;
                    } else if (bytes.length > 1) {
                        buffer = ByteBuffer.wrap(bytes);
                        datagramChannel.send(buffer, serverAddr);
                        Thread.sleep(1000);
                        //получение ответа
                        buffer = ByteBuffer.allocate(100);
                        SocketAddress connectWithServer = datagramChannel.receive(buffer);
                        if(connectWithServer!=null){
                            while (true){
                                if(buffer.array()[0]!=2 && buffer.array()[1]!=2 && buffer.array()[2]!=8){
                                    responseBytes.write(buffer.array());
                                    buffer = ByteBuffer.allocate(100);
                                    datagramChannel.receive(buffer);
                                }else {
                                    ByteArrayInputStream byteStream = new ByteArrayInputStream(responseBytes.toByteArray());
                                    ObjectInputStream objStream = new ObjectInputStream(byteStream);
                                    Response response = (Response) objStream.readObject();
                                    System.out.println(response.getRespose());
                                    responseBytes = new ByteArrayOutputStream();
                                    break;
                                }
                            }
                        }
                    }
                }

            }
        }
        datagramChannel.close();
        responseBytes.close();
    }
}
