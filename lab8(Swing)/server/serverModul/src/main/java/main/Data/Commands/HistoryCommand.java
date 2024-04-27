package main.Data.Commands;

import interfaces.Command;

import java.util.LinkedList;

public class HistoryCommand implements Command {

    private LinkedList list;
    private String descr;

    public HistoryCommand(LinkedList list){
        this.list = list;
    }

    @Override
    public void execute() {
        StringBuilder descr = new StringBuilder("");

        list.forEach(i -> descr.append(i).append(" "));

        this.descr = String.valueOf(descr);
    }

    @Override
    public String descr() {
        return descr;
    }
}
