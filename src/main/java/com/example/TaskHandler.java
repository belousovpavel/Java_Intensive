package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskHandler {

    private List<Task> tasks;
    private int taskId;

    public TaskHandler() {
        this.tasks = new ArrayList<>();
        this.taskId = 1;
    }

    public void addTask(String name, String description, LocalDate deadline){
        Task task = new Task(taskId++, name, description,deadline);
        tasks.add(task);
    }

    public void deleteTask(int number) {
        tasks.remove(number);
    }

    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            return null;
        }
        return tasks.get(index);
    }

    public boolean markAsDone(int index) {
        Task task = tasks.get(index);
        if (task.getStatus() == Task.Status.DONE) {
            return false;
        }
        task.setStatus(Task.Status.DONE);
        return true;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}

