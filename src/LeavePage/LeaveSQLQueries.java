package LeavePage;

import UserGlobalData.UserGlobalData;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDateTime;

public class LeaveSQLQueries {
    public static void applyLeave(Date startDate, Date endDate) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "INSERT INTO leaves (leaveID, employeeID, startDate, endDate, status) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, LocalDateTime.now().toString() + UserGlobalData.getUserEmployeeID());
        preparedStatement.setInt(2, UserGlobalData.getUserEmployeeID());
        preparedStatement.setDate(3, new java.sql.Date(startDate.getTime()));
        preparedStatement.setDate(4, new java.sql.Date(endDate.getTime()));
        preparedStatement.setString(5, "Pending");

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        con.close();

        if (rowsAffected > 0)
            JOptionPane.showMessageDialog(null, "Leave Applied");
        else
            JOptionPane.showMessageDialog(null, "Failed to apply leave");
    }
}
