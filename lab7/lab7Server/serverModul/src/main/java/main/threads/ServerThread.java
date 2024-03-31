package main.threads;

import Requests.RequestManager;
import main.Managers.CollectionManager;
import main.Managers.Invoker;
import main.Managers.ToByteManager;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.concurrent.ExecutorService;

import static connect.takeRequest.takeReq;

public class ServerThread implements Runnable{

    Logger logger;
    CollectionManager collectionManager;
    ExecutorService executor;

    public ServerThread(Logger logger, CollectionManager collectionManager, ExecutorService executor) {
        this.logger = logger;
        this.collectionManager = collectionManager;
        this.executor = executor;
    }

    @Override
    public void run() {
        logger.info("The Server started");
        //main.Managers
        RequestManager requestManager = new RequestManager();
        ToByteManager toByteManager = new ToByteManager();
        Invoker invoker = new Invoker(collectionManager);

        int port = 8000;
        byte[] bytes;

        DatagramChannel datagramChannel= null;
        try {
            datagramChannel = DatagramChannel.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            datagramChannel.configureBlocking(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ByteBuffer buffer= ByteBuffer.allocate(1024 * 1024);

        SocketAddress clientAddress;
        SocketAddress host = new InetSocketAddress(port);

        try{
            logger.info("Bind Address {}", host);
            datagramChannel.bind(host);
        }catch (Exception e){
            logger.error("Address is already in use");
            try {
                throw e;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        while(!Thread.currentThread().isInterrupted()){
            try {
                clientAddress = takeReq(buffer, datagramChannel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (clientAddress!=null){
                ClientThreadRead clientThreadRead = new ClientThreadRead(buffer, clientAddress, requestManager, invoker, toByteManager, datagramChannel, logger);
                executor.execute(clientThreadRead);
                buffer= ByteBuffer.allocate(1024 * 1024);
            }
        }
        Thread.currentThread().interrupt();
    }
}
