package main;

import main.Managers.CommandProvider;
import main.Managers.ToByteManager;
import main.Managers.UserManager;
import main.ResponseAndRequest.Request;
import main.ResponseAndRequest.Response;

import javax.swing.table.DefaultTableModel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.ResourceBundle;

import static main.Swing.CollectionPanel.*;
import static main.Swing.Main.start;
import static main.Swing.My.myModel;
import static main.Swing.Login.serverStatus;


public class Client {
    static String login;
    static String password;
    static UserManager userManager = new UserManager();
    static ToByteManager toByteManager;
    static DatagramChannel datagramChannel;
    static InetSocketAddress serverAddr;
    static byte[] bytes;
    static ByteBuffer buffer;
    static CommandProvider cp;
    public static String[][] data;
    public static String[][] myData;
    public static ResourceBundle messages = ResourceBundle.getBundle("messages");

    public static void main(String[] args) throws Exception {
        updateModels();
        toByteManager = new ToByteManager();
        int port = 8000;
        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        datagramChannel = DatagramChannel.open();
        InetAddress host = InetAddress.getByName("127.0.0.1");
        serverAddr = new InetSocketAddress(host, port);
        cp = new CommandProvider();
        start();
        datagramChannel.configureBlocking(false);
    }

    public static void sendRequest(String command) {
        try {
            bytes = cp.getCommand(command, login, password);
            ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
            if (bytes.length > 1) {
                buffer = ByteBuffer.wrap(bytes);
                datagramChannel.send(buffer, serverAddr);
                buffer.clear();
                buffer = ByteBuffer.allocate(100);
                //получение ответа
                Thread.sleep(500);
                SocketAddress connectWithServer = datagramChannel.receive(buffer);
                if (connectWithServer != null) {
                    while (bytes.length != 1) {
                        if (buffer.array()[0] != 2 || buffer.array()[1] != 2 || buffer.array()[2] != 8) {
                            responseBytes.write(buffer.array());
                            buffer = ByteBuffer.allocate(100);
                            datagramChannel.receive(buffer);
                        } else {
                            ByteArrayInputStream byteStream = new ByteArrayInputStream(responseBytes.toByteArray());
                            ObjectInputStream objStream = new ObjectInputStream(byteStream);
                            Response response = (Response) objStream.readObject();
                            data = response.getData();
                            myData = response.getMyData();
                            myModel.setDataVector(myData, columnNames);
                            model.setDataVector(data, columnNames);
                            if(!(response.getRespose().equals("228328"))){
                                mainLabel.setText(response.getRespose());
                            }
                            responseBytes = new ByteArrayOutputStream();
                            break;
                        }
                    }

                }
            } else if (bytes.length == 1) {
                for (String s : cp.getScriptLines()) {
                    System.out.println(s);
                    bytes = cp.getCommand(s, login, password);
                    if (bytes.length > 1) {
                        buffer = ByteBuffer.wrap(bytes);
                        datagramChannel.send(buffer, serverAddr);
                        Thread.sleep(1000);
                        //получение ответа
                        buffer = ByteBuffer.allocate(100);
                        SocketAddress connectWithServer = datagramChannel.receive(buffer);
                        if (connectWithServer != null) {
                            while (true) {
                                if (buffer.array()[0] != 2 || buffer.array()[1] != 2 || buffer.array()[2] != 8) {
                                    responseBytes.write(buffer.array());
                                    buffer = ByteBuffer.allocate(100);
                                    datagramChannel.receive(buffer);
                                } else {
                                    ByteArrayInputStream byteStream = new ByteArrayInputStream(responseBytes.toByteArray());
                                    ObjectInputStream objStream = new ObjectInputStream(byteStream);
                                    Response response = (Response) objStream.readObject();
                                    data = response.getData();
                                    myData = response.getMyData();
                                    myModel.setDataVector(myData, columnNames);
                                    model.setDataVector(data, columnNames);
                                    if(!(response.getRespose().equals("228328"))){
                                        mainLabel.setText(response.getRespose());
                                    }
                                    System.out.println(response.getRespose());
                                    responseBytes = new ByteArrayOutputStream();
                                    break;
                                }
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public static boolean registration(String login1, String password1) throws IOException, InterruptedException, ClassNotFoundException {
        login = login1;
        password = password1;
        ByteBuffer buffer = ByteBuffer.allocate(100);
        ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
        boolean status = true;
        password = userManager.hashPassword(password);
        Request request = new Request();
        request.setName("login");
        request.setLogin(login);
        request.setPassword(password);


        try {
            while (status) {
                byte[] bytes = toByteManager.outRequest(request);
                datagramChannel.send(ByteBuffer.wrap(bytes), serverAddr);
                Thread.sleep(200);
                SocketAddress connectWithServer = datagramChannel.receive(buffer);
                if (connectWithServer != null) {
                    while (bytes.length != 1) {
                        if (buffer.array()[0] != 2 || buffer.array()[1] != 2 || buffer.array()[2] != 8 || buffer.array()[3] != 2) {
                            responseBytes.write(buffer.array());
                            buffer = ByteBuffer.allocate(100);
                            datagramChannel.receive(buffer);
                        } else {
                            ByteArrayInputStream byteStream = new ByteArrayInputStream(responseBytes.toByteArray());
                            ObjectInputStream objStream = new ObjectInputStream(byteStream);
                            Response response = (Response) objStream.readObject();
                            data = response.getData();
                            myData = response.getMyData();
                            model.setDataVector(getNewData(), columnNames);
                            myModel.setDataVector(myData, columnNames);
                            mainLabel.setText(response.getRespose());
                            return response.getRespose().split(" ")[0].length()==2;
                        }
                    }
                    buffer = ByteBuffer.allocate(100);
                }
                serverStatus=true;
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

    public static String[][] getNewData(){
        return data;
    }

    public static void updateModels(){
        model = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        myModel = new DefaultTableModel(data, columnNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
    }
}

