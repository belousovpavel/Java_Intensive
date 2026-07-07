package com.example;

import java.util.List;

public interface TaskHandler {

    void addTask(String name, String description, java.time.LocalDate deadline);

    void deleteTask(int index);

    boolean markAsDone(int index);

    Task getTask(int index);

    List<Task> getAllTasks();

    boolean isEmpty();

}
