package main.threads;

import Requests.RequestManager;
import main.Managers.Invoker;
import main.Managers.ToByteManager;
import main.ResponseAndRequest.Request;
import main.ResponseAndRequest.Response;
import main.SpaceMarines.SpaceMarine;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import static Response.sendResponse.send;
import static main.Managers.CollectionManager.correctCollection;
import static main.Managers.CollectionManager.login;

public class ClientThreadRead implements Runnable{

    ByteBuffer takeBuffer;
    SocketAddress clientAddress;
    RequestManager requestManager;
    Invoker invoker;
    ToByteManager toByteManager;
    DatagramChannel datagramChannel;
    Logger logger;
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    Vector<SpaceMarine> collection;

    public ClientThreadRead(ByteBuffer takeBuffer, SocketAddress clientAddress, RequestManager requestManager, Invoker invoker, ToByteManager toByteManager, DatagramChannel datagramChannel, Logger logger, Vector<SpaceMarine> collection){
        this.takeBuffer = takeBuffer;
        this.clientAddress = clientAddress;
        this.requestManager = requestManager;
        this.invoker = invoker;
        this.toByteManager = toByteManager;
        this.datagramChannel = datagramChannel;
        this.logger = logger;
        this.collection = collection;
    }


    private void clientQuerty() throws IOException, ClassNotFoundException {
        logger.info("Received a buffer from the client {}", clientAddress);
        logger.info("Thread: "+Thread.currentThread().getName(), clientAddress);
        //прием запроса
        Request request = (Request) requestManager.doRequest(takeBuffer.array());

        String response = invoker.doCommand(request);
        Response newResponce = new Response(response);

        newResponce.setData(getData(correctCollection));

        Vector<SpaceMarine> myCollection = new Vector<>();
        for(SpaceMarine sp: correctCollection){
            if (sp.getUser().equals(login)){
                myCollection.add(sp);
            }
        }
        newResponce.setMyData(getData(myCollection));
        byte[] responseBytes = toByteManager.toByte(newResponce);

        forkJoinPool.invoke(new SendResponse(responseBytes, datagramChannel, clientAddress));
        send(new byte[]{2, 2, 8, 2}, datagramChannel, clientAddress);
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

    private String[][] getData(Vector<SpaceMarine> collection){
        if(collection==null || collection.size()==0){
            return null;
        }
        String[][] data = new String[collection.size()][10];

        for(int i=0; i<collection.size();i++){
            data[i][0] = String.valueOf(collection.get(i).getId());
            data[i][1] = String.valueOf(collection.get(i).getName());
            data[i][2] = String.valueOf(collection.get(i).getCoordinates());
            data[i][3] = String.valueOf(collection.get(i).getChapter());
            data[i][4] = String.valueOf(collection.get(i).getDate());
            data[i][5] = String.valueOf(collection.get(i).getHealth());
            data[i][6] = String.valueOf(collection.get(i).getHeartCount());
            data[i][7] = String.valueOf(collection.get(i).getWeaponType());
            data[i][8] = String.valueOf(collection.get(i).getMeleeWeapon());
            data[i][9] = String.valueOf(collection.get(i).getUser());
        }
        return data;
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

