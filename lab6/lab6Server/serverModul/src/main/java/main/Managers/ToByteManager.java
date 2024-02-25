package main.Managers;

import main.ResponseAndRequest.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ToByteManager {
    public byte[] toByte(Response arg) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(arg);
        return bos.toByteArray();
    }
}
