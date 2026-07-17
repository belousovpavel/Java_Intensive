package com.example.service;

import com.example.BD.ConnectionBD;
import com.example.model.Priority;
import com.example.model.Status;
import com.example.model.Task;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskHandlerBD implements TaskHandler{

    private final List<Task> tasks;

    public TaskHandlerBD() {
        this.tasks = new ArrayList<>();
        createSchema();
        createTable();
    }

    private void createSchema(){
        String createSchema = " CREATE SCHEMA task;";
        try(Connection connection = ConnectionBD.getConnection()){
            PreparedStatement statement = connection.prepareStatement(createSchema);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTable(){
        String createTable = " CREATE TABLE task.tasks(\n" +
                "    id SERIAL NOT NULL PRIMARY KEY,\n" +
                "    name VARCHAR(256) NOT NULL ,\n" +
                "    description VARCHAR(256) NOT NULL ,\n" +
                "    deadline DATE,\n" +
                "    priority VARCHAR(50),\n" +
                "    status VARCHAR(50)\n" +
                ");";
        try(Connection connection = ConnectionBD.getConnection()){
            PreparedStatement statement = connection.prepareStatement(createTable);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addTask(String name, String description, LocalDate deadline) {
        String insertToBD = "INSERT INTO task.tasks (name, description, deadline, priority, status) VALUES (?,?,?,?,?);";
        try(Connection connection = ConnectionBD.getConnection()){
            PreparedStatement statement = connection.prepareStatement(insertToBD,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,name);
            statement.setString(2,description);
            statement.setDate(3, Date.valueOf(deadline));
            statement.setString(4, Priority.LOW.name());
            statement.setString(5, Status.TO_DO.getSymbol());
            statement.executeUpdate();

            ResultSet generateId = statement.getGeneratedKeys();

            if(generateId.next()){
                int newId = generateId.getInt(1);

                Task task = new Task(newId,name,description,deadline);
                task.setPriority(Priority.LOW);
                task.setStatus(Status.TO_DO);

                tasks.add(task);

                System.out.println("Задача добавлена!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTask(int index) {

    }

    @Override
    public boolean markAsDone(int index) {
        return false;
    }

    @Override
    public Task getTask(int index) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
