package com.example;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final TaskHandler taskHandler = new TaskHandler();


    public static void main(String[] args) {
        System.out.println(">>> Меню:");
        while(true){
            showMenu();
            int num = getIntNumber();
            command(num);
        }
    }

    public static void showMenu(){
        System.out.println("1. Добавить задачу\n" +
                "2. Показать все задачи\n" +
                "3. Удалить задачу (по номеру)\n" +
                "4. Отметить задачу как выполненную\n" +
                "0. Выход");
    }



    public static int getIntNumber(){
        while (true){
            if(scanner.hasNextInt()){
                int num = scanner.nextInt();
                scanner.nextLine();
                return num;
            }else{
                System.out.print("Ошибка. Введите число: ");
                scanner.next();
            }
        }
    }

    static void addTask(){
        System.out.print("Введите название задачи: ");
        String name;
        while(true){
            name = scanner.nextLine();
            if(!name.isEmpty()){
                break;
            }else{
                System.out.println("Название не должно быть пустым!");
            }
        }

        System.out.print("Введите описание: ");
        String description = scanner.nextLine();

        System.out.print("Укажите deadline для задачи: ");
        String text = scanner.nextLine();
        LocalDate deadline = LocalDate.parse(text,dateFormatter);

        taskHandler.addTask(name,description,deadline);

    }

    public static void showAllTasks(){
        if(taskHandler.isEmpty()){
            System.out.println("Нет задач");
        }else{
            System.out.println("Список задач");
            int number = 1;
//            for (Task task : tasks) {
//                System.out.println(number + ". " + task);
//                number++;
//            }
            List<Task> tasks = taskHandler.getAllTasks();
            for (Task task : tasks) {
                System.out.println(number + ". " + task);
                number++;
            }
        }
        System.out.println("___________");
    }

    public static void deleteTask(){
        System.out.print("Введите номер для удаления: ");
        int number = getIntNumber() - 1;
//        if(number > 0 && number < tasks.size()){
//            System.out.println("Задача - " + tasks.get(number) + " удалена");
//            tasks.remove(number);
//        }
        System.out.println("Задача - " + taskHandler.getTask(number).getName() + " удалена");
        taskHandler.deleteTask(number);
    }

    public static void markAsDone(){
        System.out.println("Введите номер задачи для отметки: ");
        int number = getIntNumber()-1;
        if(taskHandler.markAsDone(number)){
            System.out.println("Задача отмечена как выполненная!");
        }
    }

    private static boolean isTaskListEmpty() {
        return taskHandler.isEmpty();
    }


    static void command(int num){
        switch (num){
            case 0:
                System.out.println("Выход из программы!");
                System.exit(0);
                break;
            case 1:
                addTask();
                break;
            case 2:
                if(isTaskListEmpty()){
                    System.out.println("Список пуст");
                }else{
                    showAllTasks();
                }
                break;
            case 3:
                if(isTaskListEmpty()){
                    System.out.println("Нет задачи для удаления");
                }else{
                    deleteTask();
                }
                break;
            case 4:
                if(isTaskListEmpty()){
                    System.out.println("Нет задачи для отметки");
                }else{
                    markAsDone();
                }
                break;
            default:
                System.out.println("Некорректный выбор. Выберете число от 0 до 4!");
        }
    }

}
