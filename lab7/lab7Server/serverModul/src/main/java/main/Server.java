package main;

import main.Managers.CollectionManager;
import main.threads.Console;
import main.threads.ServerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    static ExecutorService executor = Executors.newCachedThreadPool();
    static CollectionManager collectionManager = new CollectionManager();

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Thread serverThread = new Thread(new ServerThread(logger, collectionManager,executor));
        Thread console = new Thread(new Console(logger, executor, collectionManager, serverThread));
        serverThread.start();
        console.start();
    }
}



