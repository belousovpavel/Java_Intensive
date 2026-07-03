package com.example;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {

    private Priority priority;
    private LocalDate deadline;
    private String description;
    private final String name;
    private final int id;
    private Status status;

    public Task(int id, String name, String description, LocalDate deadline) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.priority = Priority.LOW;
        this.status = Status.TO_DO;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Priority{
        LOW,
        MIDDLE,
        HIGH
    }

    public enum Status {
        TO_DO("[ ]"),
        DONE("[X]");

        private final String symbol;

        Status(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }


    @Override
    public String toString() {
        return getStatus().getSymbol() +
                " Название - " + getName() +
                ", Описание - " + getDescription() +
                ", Дедлайн: " + getDeadline() +
                ", Приоритет: " + getPriority() +
                ", ID: " + getId();
    }
}
