package main;

import main.Managers.CommandProvider;
import main.ResponseAndRequest.Response;

import java.io.ByteArrayInputStream;
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

        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        ByteBuffer buffer;
        InetAddress host  = InetAddress.getByName("127.0.0.1");
        InetSocketAddress serverAddr = new InetSocketAddress(host, port);

        boolean repeat;

        boolean timerIsRunning = false;
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nНет подключения к серверу!");
            }
        };


        byte[] bytes;
        boolean run = true;


        while(run) {
            System.out.print("> ");
            bytes = cp.getCommand(scanner.nextLine(), datagramChannel, serverAddr);
            if (bytes == null) {
                run = false;
            } else if (bytes.length > 1) {
                if (!timerIsRunning || !task.cancel()) {
                    timer = new Timer();
                    task = new TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("\nНет подключения к серверу!");
                        }
                    };
                    timer.schedule(task, 5000);
                    timerIsRunning = true;
                }
                buffer = ByteBuffer.wrap(bytes);
                datagramChannel.send(buffer, serverAddr);
                buffer.clear();
                buffer = ByteBuffer.allocate(1024 * 1024);
                //получение ответа
                Thread.sleep(1000);
                SocketAddress connectWithServer = datagramChannel.receive(buffer);
                if (connectWithServer != null) {
                    timer.cancel();
                    timerIsRunning = false;
                    buffer.clear();
                    ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer.array());
                    ObjectInputStream objStream = new ObjectInputStream(byteStream);
                    Response response = (Response) objStream.readObject();
                    System.out.println(response.getRespose());
                    buffer.clear();
                }
            } else if (bytes.length == 1) {
                for(String s: cp.getScriptLines()){
                    bytes = cp.getCommand(s, datagramChannel, serverAddr);
                    if (bytes == null) {
                        run = false;
                    } else if (bytes.length > 1) {

                        buffer = ByteBuffer.wrap(bytes);
                        datagramChannel.send(buffer, serverAddr);
                        buffer.clear();
                        buffer = ByteBuffer.allocate(1024 * 1024);
                        //получение ответа
                        Thread.sleep(1000);
                        SocketAddress connectWithServer = datagramChannel.receive(buffer);
                        if (connectWithServer != null) {
                            buffer.clear();
                            ByteArrayInputStream byteStream = new ByteArrayInputStream(buffer.array());
                            ObjectInputStream objStream = new ObjectInputStream(byteStream);
                            Response response = (Response) objStream.readObject();
                            System.out.println(response.getRespose());
                            buffer.clear();
                        }
                    }
                }

            }
        }

        System.exit(0);
    }
}
