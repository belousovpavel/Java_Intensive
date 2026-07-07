package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskHandlerImpl implements TaskHandler{

    private List<Task> tasks;
    private int taskId;

    public TaskHandlerImpl() {
        this.tasks = new ArrayList<>();
        this.taskId = 1;
    }

    @Override
    public void addTask(String name, String description, LocalDate deadline){
        Task task = new Task(taskId++, name, description,deadline);
        tasks.add(task);
    }

    @Override
    public void deleteTask(int number) {
        tasks.remove(number);
    }

    @Override
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }

    @Override
    public boolean markAsDone(int index) {
        Task task = tasks.get(index);
        if (task.getStatus() == Task.Status.DONE) {
            return false;
        }
        task.setStatus(Task.Status.DONE);
        return true;
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}

