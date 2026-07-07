package com.example;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final TaskHandlerImpl taskHandler = new TaskHandlerImpl();


    public static void main(String[] args) {

        TaskManager manager = new TaskManager();
        manager.start();

    }
//        while(true){
//            TaskPrinter.printMenu();
//            int num = getIntNumber();
//            command(num);
//        }
//    }
//
//    public static int getIntNumber(){
//        while (true){
//            if(scanner.hasNextInt()){
//                int num = scanner.nextInt();
//                scanner.nextLine();
//                return num;
//            }else{
//                System.out.print("Ошибка. Введите число: ");
//                scanner.next();
//            }
//        }
//    }
//
//    static void addTask(){
//        TaskPrinter.printEnterTaskName();
//        String name;
//        while(true){
//            name = scanner.nextLine();
//            if(!name.isEmpty()){
//                break;
//            }else{
//                System.out.println("Название не должно быть пустым!");
//            }
//        }
//
//        System.out.print("Введите описание: ");
//        String description = scanner.nextLine();
//
//        System.out.print("Укажите deadline для задачи: ");
//        String text = scanner.nextLine();
//        LocalDate deadline = LocalDate.parse(text,dateFormatter);
//
//        taskHandler.addTask(name,description,deadline);
//
//        TaskPrinter.printTaskAdded();
//
//    }
//
//    public static void showAllTasks() {
//        if (taskHandler.isEmpty()) {
//            System.out.println("Нет задач");
//        } else {
//            TaskPrinter.printAllTasks(taskHandler.getAllTasks());
//
//            System.out.println("___________");
//        }
//    }
//
//    public static void deleteTask(){
//        System.out.print("Введите номер для удаления: ");
//        int number = getIntNumber() - 1;
////        if(number > 0 && number < tasks.size()){
////            System.out.println("Задача - " + tasks.get(number) + " удалена");
////            tasks.remove(number);
////        }
//        Task taskToDelete = taskHandler.getTask(number);
//        TaskPrinter.printTaskDeleted(taskToDelete);
//        taskHandler.deleteTask(number);
//    }
//
//    public static void markAsDone(){
//        System.out.println("Введите номер задачи для отметки: ");
//        int number = getIntNumber()-1;
//        if(taskHandler.markAsDone(number)){
//            System.out.println("Задача отмечена как выполненная!");
//        }
//    }
//
//    private static boolean isTaskListEmpty() {
//        return taskHandler.isEmpty();
//    }
//
//
//    static void command(int num){
//        switch (num){
//            case 0:
//                System.out.println("Выход из программы!");
//                System.exit(0);
//                break;
//            case 1:
//                addTask();
//                break;
//            case 2:
//                if(isTaskListEmpty()){
//                    System.out.println("Список пуст");
//                }else{
//                    showAllTasks();
//                }
//                break;
//            case 3:
//                if(isTaskListEmpty()){
//                    System.out.println("Нет задачи для удаления");
//                }else{
//                    deleteTask();
//                }
//                break;
//            case 4:
//                if(isTaskListEmpty()){
//                    System.out.println("Нет задачи для отметки");
//                }else{
//                    markAsDone();
//                }
//                break;
//            default:
//                System.out.println("Некорректный выбор. Выберете число от 0 до 4!");
//        }
//    }

}
