package main;

import Requests.RequestManager;
import main.Managers.CollectionManager;
import main.Managers.Invoker;
import main.Managers.ToByteManager;
import main.ResponseAndRequest.Request;
import main.ResponseAndRequest.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

import static Response.sendResponse.send;
import static connect.takeRequest.takeReq;

public class Server {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("The Server started");
        //main.Managers
        CollectionManager collectionManager = new CollectionManager();
        RequestManager requestManager = new RequestManager();
        ToByteManager toByteManager = new ToByteManager();
        Invoker invoker = new Invoker(collectionManager);

        int port = 8000;
        byte[] bytes;

        Scanner scanner = new Scanner(System.in);

        DatagramChannel datagramChannel= DatagramChannel.open();
        datagramChannel.configureBlocking(false);

        ByteBuffer buffer= ByteBuffer.allocate(1024 * 1024);
        SocketAddress clientAddress;
        SocketAddress host = new InetSocketAddress(port);

        try{
            logger.info("Bind Address {}", host);
            datagramChannel.bind(host);
        }catch (Exception e){
            logger.error("Address is already in use");
            throw e;
        }

        String command;

        while (true){

            if (System.in.available() > 0) {
                command = scanner.nextLine();
                if (command.equals("exit")) {
                    collectionManager.saveCollection();
                    logger.info("Collection saved");
                    logger.info("Turning off the server");
                    break;
                }else if (command.equals("save")){
                    collectionManager.saveCollection();
                    logger.info("Collection saved");
                }
            }

            clientAddress = takeReq(buffer, datagramChannel);
            if (clientAddress!=null){
                logger.info("Received a buffer from the client {}", clientAddress);
                //прием запроса
                Request request = (Request) requestManager.doRequest(buffer.array());
                buffer.clear();

                //Ответ клиенту
                String response = invoker.doCommand(request);
                send(toByteManager.toByte(new Response(response)), datagramChannel, clientAddress);
                logger.info("Sent a response to the client {}", clientAddress);
                buffer = ByteBuffer.allocate(1024 * 1024);

                if (System.in.available() > 0) {
                    command = scanner.nextLine();
                    if (command.equals("exit")) {
                        logger.info("Collection saved");
                        logger.info("Turning off the server");
                        collectionManager.saveCollection();
                    }else if (command.equals("save")){
                        logger.info("Collection saved");
                        collectionManager.saveCollection();
                    }
                }
            }
        }
        datagramChannel.close();
    }
}



