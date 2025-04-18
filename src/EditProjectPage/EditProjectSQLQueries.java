package EditProjectPage;

import javax.swing.*;
import java.sql.*;

public class EditProjectSQLQueries {
    public static void editProject(String projectID, String newProjectName, String newStatus, int newBudget, Date newStartDate, Date newEndDate) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String updateQuery = "UPDATE project " +
                "SET projectName = ?, " +
                "pstatus = ?, " +
                "budget = ?, " +
                "startDate = ?, " +
                "endDate = ? " +
                "WHERE projectID = ?";

        try (PreparedStatement statement = con.prepareStatement(updateQuery)) {
            statement.setString(1, newProjectName);
            statement.setString(2, newStatus);
            statement.setDouble(3, newBudget);
            statement.setDate(4, newStartDate);
            statement.setDate(5, newEndDate);
            statement.setString(6, projectID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0)
                JOptionPane.showMessageDialog(null, "Project updated successfully.");
            else
                JOptionPane.showMessageDialog(null, "No project found with the specified ID.");
        }
    }
}
