package AssignTask;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

public class AssignTaskSQLQueries {
    public static void assignTask(int employeeID, Date deadline, String taskName, String projectID) throws ClassNotFoundException {
        boolean success = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002")) {

            String taskID = projectID + employeeID + LocalDateTime.now();

            // Insert task
            String insertTaskQuery = "INSERT INTO task (taskID, taskName, deadline, assignedOn, projectID) " +
                    "VALUES (?, ?, ?, CURRENT_TIMESTAMP(), ?)";
            try (PreparedStatement insertTaskStatement = con.prepareStatement(insertTaskQuery)) {
                insertTaskStatement.setString(1, taskID); // Task ID
                insertTaskStatement.setString(2, taskName); // Task Name
                insertTaskStatement.setDate(3, deadline); // Deadline
                insertTaskStatement.setString(4, projectID); // Project ID
                insertTaskStatement.executeUpdate();
            }

            // Assign task to employee
            String assignTaskQuery = "INSERT INTO worksOn (taskID, employeeID) VALUES (?, ?)";
            try (PreparedStatement assignTaskStatement = con.prepareStatement(assignTaskQuery)) {
                assignTaskStatement.setString(1, taskID); // Task ID
                assignTaskStatement.setInt(2, employeeID); // Employee ID
                assignTaskStatement.executeUpdate();
            }
            success = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to assign task. Error: " + e.getMessage());
        }

        if (success) {
            JOptionPane.showMessageDialog(null, "Task assigned successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to assign task.");
        }
    }
}
