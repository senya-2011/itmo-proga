package main.Managers;

import main.ResponseAndRequest.Response;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ResponseManager{

    private ToByteManager toByteManager = new ToByteManager();
    private byte[] bytes;
    private ByteBuffer buffer;

    public void setBuffer(String response) throws IOException {
        bytes = toByteManager.toByte(new Response(response));
        buffer = ByteBuffer.wrap(bytes);
    }
    public ByteBuffer getBuffer(){
        return buffer;
    }
}

