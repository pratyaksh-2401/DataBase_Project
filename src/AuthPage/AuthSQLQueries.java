package AuthPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthSQLQueries {
    public static List<String> getEmployeeTasks(int employeeID) throws SQLException, ClassNotFoundException {
        List<String> tasks = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT t.taskID, t.taskName, t.deadline\n" +
                "FROM task t\n" +
                "INNER JOIN worksOn w ON t.taskID = w.taskID\n" +
                "INNER JOIN employee e ON w.employeeID = e.employeeID\n" +
                "WHERE e.employeeID = ?\n" +
                "ORDER BY t.deadline ASC";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, employeeID);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String taskID = resultSet.getString("taskID");
            String taskName = resultSet.getString("taskName");
            String deadline = resultSet.getString("deadline");
            System.out.println(taskID);
            tasks.add("Task ID: " + taskID + ", Task Name: " + taskName + ", Deadline: " + deadline);
        }

        resultSet.close();
        preparedStatement.close();
        con.close();

        return tasks;
    }
}
