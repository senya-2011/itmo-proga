package main.Managers;

import jdk.swing.interop.SwingInterOpUtils;
import main.ResponseAndRequest.Request;
import main.ResponseAndRequest.Response;
import main.Scripts.ScriptManager;
import main.SpaceMarines.SpaceMarine;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Vector;

public class CommandProvider {
    ScriptManager sm = new ScriptManager();
    private Vector<String> scriptLines;

    public byte[] getCommand(String userString, DatagramChannel dc, SocketAddress server) throws Exception{

        ToByteManager tbm = new ToByteManager();

        String[] commandToWords = userString.split(" ");
        String command = commandToWords[0]; // получаем первое слово, что и является коммандой
        Request request = new Request();


        if (command.equals("help") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if (command.equals("add") && commandToWords.length==1){
            request.setName(command);
            request.setSp(new SpaceMarine());
            return tbm.outRequest(request);
        }else if (command.equals("info") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if (command.equals("show") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if (command.equals("update") && commandToWords.length==2){
            request.setName(command);
            if(isInt(commandToWords[1])){
                request.setId(commandToWords[1]);
                return tbm.outRequest(request);
            }else{
                return new byte[0];
            }
        }else if (command.equals("remove_by_id") && commandToWords.length==2){
            request.setName(command);
            if(isInt(commandToWords[1])){
                request.setId(commandToWords[1]);
                return tbm.outRequest(request);
            }else{
                return new byte[0];
            }
        }else if (command.equals("exit") && commandToWords.length==1){
            return null;
        }else if (command.equals("sort") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if (command.equals("history") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if(command.equals("group_counting_by_chapter") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if(command.equals("count_less_than_chapter") && commandToWords.length==2){
            request.setName(command);
            if(isInt(commandToWords[1])){
                request.setId(commandToWords[1]);
                return tbm.outRequest(request);
            }else{
                return new byte[0];
            }
        }else if(command.equals("count_greater_than_weapon_type") && commandToWords.length==2){
            request.setName(command);
            if(isInt(commandToWords[1])){
                request.setId(commandToWords[1]);
                return tbm.outRequest(request);
            }else{
                return new byte[0];
            }
        }else if(command.equals("add_if_max") && commandToWords.length==1){
            request.setName(command);
            request.setSp(new SpaceMarine());
            return tbm.outRequest(request);
        }else if(command.equals("clear") && commandToWords.length==1){
            request.setName(command);
            return tbm.outRequest(request);
        }else if(userString.replaceAll(" ", "")==""){
            System.out.print("");//не выдаем ошибку на пустую строку
        }else if(command.equals("execute_script") && commandToWords.length==2){
            Vector<String> lines = sm.Script(commandToWords[1]);
            if(lines!=null){
                scriptLines = lines;
                return new byte[1];
            }
        }else{
            System.out.println("Такой команды нет. Введите help для списка команд");
        }
        return new byte[0];
    }
    private boolean isInt(String id){
        try{
            Integer.parseInt(id);
            return true;
        }catch (Exception e){
            System.out.println("аргумент должен быть числом!");
            return false;
        }
    }

    public Vector<String> getScriptLines() {
        return scriptLines;
    }
}
