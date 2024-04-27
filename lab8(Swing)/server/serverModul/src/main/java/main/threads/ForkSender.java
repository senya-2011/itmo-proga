package main.threads;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

import static Response.sendResponse.send;

public class ForkSender extends RecursiveAction {
    byte[] responseBytes;
    DatagramChannel datagramChannel;
    SocketAddress clientAddress;

    public ForkSender(byte[] responseBytes, DatagramChannel datagramChannel, SocketAddress clientAddress) {
        this.responseBytes = responseBytes;
        this.datagramChannel = datagramChannel;
        this.clientAddress = clientAddress;
    }

    @Override
    protected void compute() {
        send(responseBytes, datagramChannel, clientAddress);
    }
}
