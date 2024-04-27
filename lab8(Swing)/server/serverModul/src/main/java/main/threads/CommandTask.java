package main.threads;

import interfaces.Command;

import java.util.Map;

public class CommandTask implements Runnable{
    Map<String, Command> commands;

    public CommandTask(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void run() {

    }
}
