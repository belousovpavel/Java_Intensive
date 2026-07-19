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


    public TaskHandlerBD() {
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

    private int getTaskId(int index) throws SQLException{
        String sql = "SELECT id FROM task.tasks ORDER BY id LIMIT 1 OFFSET ?";
        try(Connection connection = ConnectionBD.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,index);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
            throw new SQLException("Задача не найдена по индексу: " + index);
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
            statement.setString(5, Status.TO_DO.name());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTask(int index) throws SQLException {

        int taskId = getTaskId(index);

        String deleteTask = "DELETE FROM task.tasks WHERE id = ?";

        try(Connection connection = ConnectionBD.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(deleteTask);
            statement.setInt(1,taskId);
            statement.executeUpdate();

            System.out.println("Задача удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean markAsDone(int index) {
        try {
            int number = getTaskId(index);
            String getStatus = "SELECT status FROM task.tasks WHERE id = ?";
            try(Connection connection = ConnectionBD.getConnection()){
                PreparedStatement statement = connection.prepareStatement(getStatus);
                statement.setInt(1,number);
                ResultSet result = statement.executeQuery();
                if(result.next()){
                    String status = result.getString("status");
                    if(status.equals(Status.DONE.name())){
                        return false;
                    }
                }
            }

            String updateStatus = "UPDATE task.tasks SET status = ? WHERE id = ?";
            try(Connection connection = ConnectionBD.getConnection()){
                PreparedStatement statement = connection.prepareStatement(updateStatus);
                statement.setString(1,Status.DONE.name());
                statement.setInt(2,number);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public Task getTask(int index) {
        return null;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String getAllTasks = "SELECT * FROM task.tasks ORDER BY id";
        try(Connection connection = ConnectionBD.getConnection()){
            PreparedStatement statement = connection.prepareStatement(getAllTasks);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String description = result.getString("description");
                LocalDate deadline = result.getDate("deadline").toLocalDate();
                String priority = result.getString("priority");
                String status = result.getString("status");

                Task task = new Task(id,name,description,deadline);
                try {
                    task.setPriority(Priority.valueOf(priority));
                } catch (IllegalArgumentException | NullPointerException e) {
                    task.setPriority(Priority.LOW);
                }

                try {
                    task.setStatus(Status.valueOf(status));
                } catch (IllegalArgumentException | NullPointerException e) {
                    task.setStatus(Status.TO_DO);
                }

                tasks.add(task);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
