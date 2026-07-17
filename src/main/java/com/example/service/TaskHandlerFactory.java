package com.example.service;

public class TaskHandlerFactory {

    public static TaskHandler create(int choice){
        if(choice == 1){
            return createTaskHandlerInMemory();
        }
        return createTaskHandlerDB();
    }

    private static TaskHandler createTaskHandlerInMemory(){
        return new TaskHandlerImpl();
    }

    private static TaskHandler createTaskHandlerDB(){
        return new TaskHandlerBD();
    }

}
