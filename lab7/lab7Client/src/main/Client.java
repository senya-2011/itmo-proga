package main;

import main.Managers.CommandProvider;
import main.Managers.ToByteManager;
import main.Managers.UserManager;
import main.ResponseAndRequest.Request;
import main.ResponseAndRequest.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;


public class Client {
    static String login;
    static String password;
    static UserManager userManager = new UserManager();

    public static void main(String[] args) throws Exception{
        ToByteManager toByteManager = new ToByteManager();
        Scanner scanner = new Scanner(System.in);
        int port = 8000;

        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        DatagramChannel datagramChannel = DatagramChannel.open();

        ByteBuffer buffer;
        InetAddress host  = InetAddress.getByName("127.0.0.1");
        InetSocketAddress serverAddr = new InetSocketAddress(host, port);
        registration(scanner, toByteManager, datagramChannel, serverAddr);
        datagramChannel.configureBlocking(false);
        CommandProvider cp = new CommandProvider();

        byte[] bytes;
        boolean run = true;


        while(run) {
            System.out.print("> ");
            bytes = cp.getCommand(scanner.nextLine(), datagramChannel, serverAddr, login, password);
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
                    bytes = cp.getCommand(s, datagramChannel, serverAddr, login, password);
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
    private static void registration(Scanner scanner, ToByteManager toByteManager, DatagramChannel datagramChannel, SocketAddress serverAddress) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        boolean status=true;
        while(status){
            System.out.println("Логин и пароль должны быть от 3 до 30 символов");
            System.out.println("Если вы новый пользователь придумайте новый логин");
            System.out.println("Введите логин: ");
            login = scanner.nextLine();
            if(userManager.checkString(login)){
                continue;
            }
            login.replaceAll(" ", "_");
            System.out.println("Введите пароль: ");
            password = scanner.nextLine();
            if(userManager.checkString(password)){
                continue;
            }
            password = userManager.hashPassword(password);
            Request request = new Request();
            request.setName("login");
            request.setLogin(login);
            request.setPassword(password);
            try {
                byte[] bytes = toByteManager.outRequest(request);
                datagramChannel.send(ByteBuffer.wrap(bytes), serverAddress);
                SocketAddress connectWithServer = datagramChannel.receive(buffer);
                if (connectWithServer != null) {
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
                            if(response.getRespose().split(" ")[0].equals("Вы")){
                                status = false;
                            }
                            responseBytes = new ByteArrayOutputStream();
                            break;
                        }
                    }
                    buffer = ByteBuffer.allocate(100);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
