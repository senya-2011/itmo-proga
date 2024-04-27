package main.threads;

import main.Managers.CollectionManager;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Console implements Runnable{

    Logger logger;
    ExecutorService executor;
    CollectionManager collectionManager;
    Thread serverThread;

    public Console(Logger logger, ExecutorService executor, CollectionManager collectionManager, Thread serverThread) {
        this.logger = logger;
        this.executor = executor;
        this.collectionManager = collectionManager;
        this.serverThread = serverThread;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command;
        while (true){
            try {
                if (scanner.hasNext()) {
                    command = scanner.nextLine();
                    if (command.equals("exit")) {
                        executor.shutdown();
                        logger.info("Shutdown executor");
                        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                        collectionManager.saveCollection();
                        collectionManager.saveUsers();
                        logger.info("Collection saved");
                        logger.info("Turning off the server");
                        Thread.currentThread().interrupt();
                        System.exit(0);
                    }else if (command.equals("save")){
                        collectionManager.saveCollection();
                        collectionManager.saveUsers();
                        logger.info("Collection saved");
                    }else{
                        logger.info("Это не команда!");
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
