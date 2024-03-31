package connect;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class takeRequest {
    public static SocketAddress takeReq(ByteBuffer buffer, DatagramChannel datagramChannel) throws IOException {
        return datagramChannel.receive(buffer);
    }
}
