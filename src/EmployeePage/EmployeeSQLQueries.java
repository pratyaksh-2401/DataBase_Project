package EmployeePage;

import UserGlobalData.UserGlobalData;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class EmployeeSQLQueries {
    public static void markAttendance() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "INSERT INTO attendance (employeeID, date, intime) VALUES (?, CURDATE(), CURTIME())";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, UserGlobalData.getUserEmployeeID());

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        con.close();

        if (rowsAffected > 0)
            JOptionPane.showMessageDialog(null, "Attendance marked successfully.");
        else
            JOptionPane.showMessageDialog(null, "Failed to mark attendance.");
    }

    public static void generateSalaryReport() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT e.employeeID, e.fName, e.lName, s.amount, s.date\n" +
                "FROM employee e\n" +
                "JOIN salary s ON e.employeeID = s.employeeID\n" +
                "WHERE e.employeeID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, UserGlobalData.getUserEmployeeID());

        ResultSet resultSet = preparedStatement.executeQuery();

        String filename = "report.csv";
        FileWriter csvWriter = new FileWriter(filename);
        csvWriter.append("Employee ID,First Name,Last Name,Salary Amount,Salary Date\n");

        boolean hasData = false;
        while (resultSet.next()) {
            hasData = true;
            int empID = resultSet.getInt("employeeID");
            String fName = resultSet.getString("fName");
            String lName = resultSet.getString("lName");
            double amount = resultSet.getDouble("amount");
            Date date = resultSet.getDate("date");

            csvWriter.append(String.valueOf(empID)).append(",");
            csvWriter.append(fName).append(",");
            csvWriter.append(lName).append(",");
            csvWriter.append(String.valueOf(amount)).append(",");
            csvWriter.append(date.toString()).append("\n");
        }

        csvWriter.flush();
        csvWriter.close();

        // Close the connections
        preparedStatement.close();
        con.close();

        if (hasData)
            JOptionPane.showMessageDialog(null, "CSV report generated successfully. Local link: " + System.getProperty("user.dir") + "\\" + filename);
        else
            JOptionPane.showMessageDialog(null, "No data found for employee ID: " + UserGlobalData.getUserEmployeeID());
    }
}
