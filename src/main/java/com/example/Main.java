package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final List<String> tasks = new ArrayList<>();
    private static final List<Boolean> statusTask = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(">>> Меню");
        while (true){
            showMenu();
            int num = getIntNumber();
            command(num);
        }
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
                    markTaskAsDone();
                }
                break;
            default:
                System.out.println("Некорректный выбор. Выберете число от 0 до 4!");
        }
    }

    static void showMenu(){
        System.out.println("1. Добавить задачу\n" +
                "2. Показать все задачи\n" +
                "3. Удалить задачу (по номеру)\n" +
                "4. Отметить задачу как выполненную\n" +
                "0. Выход");
        System.out.print("Выберите пункт меню: ");
    }

    static int getIntNumber(){
        while (true){
            int num = scanner.nextInt();
            scanner.nextLine();
            return num;
        }
    }

    static void addTask(){
        System.out.println("Введите описание задачи: ");
        String task = scanner.nextLine();
        tasks.add(task);
        statusTask.add(false);
        System.out.println(task + " (ввел пользователь)\n" +
                "Задача добавлена!");
    }

    static void showAllTasks(){
        for (int i = 0; i < tasks.size(); i++) {
            boolean status = statusTask.get(i);
            if (status == true){
                System.out.println(i+1 + ". " + "[X] " + tasks.get(i));
            }else{
                System.out.println(i+1 + ". " + "[ ] " + tasks.get(i));
            }
        }
    }

    static void deleteTask(){
        System.out.println("Введите номер задачи для удаления: ");
        int number = getIntNumber()-1;
        if(number > 0 && number < tasks.size()){
            System.out.println("Задача - " + tasks.get(number) + " удалена");
            tasks.remove(number);
            statusTask.remove(number);
        }
    }

    static void markTaskAsDone(){
        System.out.println("Введите номер задачи для отметки: ");
        int number = getIntNumber()-1;
        statusTask.set(number,true);
        showAllTasks();
    }

    static boolean isTaskListEmpty() {
        return tasks.isEmpty();
    }

}
