package com.example;

import java.util.List;

public final class TaskPrinter {

    private TaskPrinter() {
        throw new UnsupportedOperationException("Это утилитный класс, нельзя создавать экземпляры");
    }

    public static void printMenu(){
        System.out.println("\n>>> Меню:");
        System.out.println("1. Добавить задачу");
        System.out.println("2. Показать все задачи");
        System.out.println("3. Удалить задачу (по номеру)");
        System.out.println("4. Отметить задачу как выполненную");
        System.out.println("0. Выход");
        System.out.print("Выберите пункт меню: ");
    }

    public static void printAllTasks(List<Task> tasks) {
        System.out.println("Список задач:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void printTaskAdded() {
        System.out.println("Задача добавлена!");
    }

    public static void printTaskDeleted(Task task) {
        if (task != null) {
            System.out.println("Задача - " + task + " удалена");
        } else {
            System.out.println("Задача не найдена.");
        }
    }

    public static void printEnterTaskName() {
        System.out.print("Введите название задачи: ");
    }

}
