package AddProjectPage;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddProjectSQLQueries {
    public static void addProject(String projectID, String projectName, String pstatus, String budget, String startDate, String endDate) throws SQLException, ClassNotFoundException {
        List<String> tasks = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "INSERT INTO\n" +
                "project (projectID, projectName, pstatus, budget, startDate, endDate)\n" +
                "VALUES ("
                + "\'" + projectID + "\'" +", "
                +  "\'" + projectName + "\'" +", "
                + "\'" + pstatus + "\'" +", "
                + budget +", "
                + "\'" + startDate + "\'" +", "
                + "\'" + endDate + "\'" +
                ")";
        PreparedStatement preparedStatement = con.prepareStatement(query);

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        con.close();

        if (rowsAffected > 0)
            JOptionPane.showMessageDialog(null, "Project Added Successfully");
        else
            JOptionPane.showMessageDialog(null, "Failed to add project");

    }
}