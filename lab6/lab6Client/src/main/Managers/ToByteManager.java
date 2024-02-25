package main.Managers;

import main.ResponseAndRequest.Request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ToByteManager {
    public byte[] outRequest(Request arg) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(arg);
        return bos.toByteArray();
    }

}
