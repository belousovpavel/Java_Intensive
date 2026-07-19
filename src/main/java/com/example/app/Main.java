package com.example.app;

import com.example.service.TaskHandler;
import com.example.service.TaskHandlerFactory;
import com.example.service.TaskManager;
import com.example.service.TaskPrinter;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
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
