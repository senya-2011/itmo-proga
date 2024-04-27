package main.Managers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserManager {
    public boolean checkString(String s){
        s.trim();
        s.replaceAll(" ", "_");
        if(s.length()<3 || s.length()>30){
            System.out.println("Неправильные размеры пароля/логина");
            return true;
        }
        return false;
    }
    public String hashPassword(String string){
        String peper = "SuperMegaPeper";
        string = peper + string;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(string.getBytes());
            byte[] digest = messageDigest.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b: digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
