package com.example.app;

import com.example.service.TaskHandler;
import com.example.service.TaskHandlerFactory;
import com.example.service.TaskManager;
import com.example.service.TaskPrinter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskPrinter.printSelectionMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        TaskHandler taskHandler = TaskHandlerFactory.create(choice);

        TaskManager manager = new TaskManager(scanner,taskHandler);
        manager.start();
        scanner.close();

    }

}
