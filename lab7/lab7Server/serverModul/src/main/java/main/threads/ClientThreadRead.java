package main.threads;

import Requests.RequestManager;
import main.Managers.Invoker;
import main.Managers.ToByteManager;
import main.ResponseAndRequest.Request;
import main.ResponseAndRequest.Response;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static Response.sendResponse.send;

public class ClientThreadRead implements Runnable{

    ByteBuffer takeBuffer;
    SocketAddress clientAddress;
    RequestManager requestManager;
    Invoker invoker;
    ToByteManager toByteManager;
    DatagramChannel datagramChannel;
    Logger logger;
    ForkJoinPool forkJoinPool = new ForkJoinPool();

    public ClientThreadRead(ByteBuffer takeBuffer, SocketAddress clientAddress, RequestManager requestManager, Invoker invoker, ToByteManager toByteManager, DatagramChannel datagramChannel, Logger logger){
        this.takeBuffer = takeBuffer;
        this.clientAddress = clientAddress;
        this.requestManager = requestManager;
        this.invoker = invoker;
        this.toByteManager = toByteManager;
        this.datagramChannel = datagramChannel;
        this.logger = logger;
    }


    private void clientQuerty() throws IOException, ClassNotFoundException {
        logger.info("Received a buffer from the client {}", clientAddress);
        logger.info("Thread: "+Thread.currentThread().getName(), clientAddress);
        //прием запроса
        Request request = (Request) requestManager.doRequest(takeBuffer.array());

        String response = invoker.doCommand(request);
        byte[] responseBytes = toByteManager.toByte(new Response(response));

        forkJoinPool.invoke(new SendResponse(responseBytes, datagramChannel, clientAddress));
        send(new byte[]{2, 2, 8}, datagramChannel, clientAddress);
//        while(true){
//            if(responseBytes.length>100){
//                //forkJoinPool.execute(new ForkSender(Arrays.copyOfRange(responseBytes, 0, 100), datagramChannel, clientAddress));
//                send(Arrays.copyOfRange(responseBytes, 0, 100), datagramChannel, clientAddress);
//                responseBytes = Arrays.copyOfRange(responseBytes, 100, responseBytes.length);
//            }else{
//                send(responseBytes, datagramChannel, clientAddress);
//                send(new byte[]{2, 2, 8}, datagramChannel, clientAddress);
//                break;
//            }
//        }

        logger.info("Sent a response to the client {}", clientAddress);
    }

    @Override
    public void run() {
        try {
            clientQuerty();
            Thread.interrupted();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

class SendResponse extends RecursiveAction{
    byte[] bytes;
    int maxSize = 100;
    DatagramChannel datagramChannel;
    SocketAddress clientAddress;
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    public SendResponse(byte[] bytes, DatagramChannel datagramChannel, SocketAddress clientAddress){
        this.bytes = bytes;
        this.datagramChannel = datagramChannel;
        this.clientAddress = clientAddress;
    }

    @Override
    protected void compute() {
        if (bytes.length>maxSize){
            forkJoinPool.execute(new SendResponse(Arrays.copyOfRange(bytes, 0, maxSize), datagramChannel, clientAddress));
            forkJoinPool.invoke(new SendResponse(Arrays.copyOfRange(bytes, maxSize, bytes.length), datagramChannel, clientAddress));
        } else {
            send(bytes, datagramChannel, clientAddress);
        }
    }
}

