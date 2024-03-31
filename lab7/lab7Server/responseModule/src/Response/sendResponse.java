package Response;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class sendResponse {
    public static void send(byte[] bytes, DatagramChannel datagramChannel, SocketAddress clientAddress){

        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        try{
            datagramChannel.send(buffer, clientAddress);
        }catch (Exception e){
            System.err.println("Потеряно соединение с пользователем");
        }

        buffer.clear();
    }
}
