package ManageProjectPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectSQLQueries {
    public static String searchProject(String projID) throws SQLException, ClassNotFoundException {
        String info = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT *\n" +
                "FROM project p\n" +
                "WHERE p.projectID = " + "\"" + projID + "\"";
        PreparedStatement preparedStatement = con.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String pID = resultSet.getString("projectID");
            String projectName = resultSet.getString("projectName");
            String pstatus = resultSet.getString("pstatus");
            String budget = resultSet.getString("budget");
            String startDate = resultSet.getString("startDate");
            String endDate = resultSet.getString("endDate");
            System.out.println(resultSet);
            info = "Project ID: " + pID + "\nProject Name: " + projectName + "\nStatus: " + pstatus + "\nBudget: "+ budget + "\nStart Date: "+ startDate+ "\nEnd Date: " + endDate;
        }

        resultSet.close();
        preparedStatement.close();
        con.close();

        return info;
    }
}
