package SearchEmployeePage;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchEmpSQLQueries {
    public static String searchEmployee(String employeeID) throws SQLException, ClassNotFoundException {
        String info = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT *\n" +
                "FROM employee e\n" +
                "WHERE e.employeeID"+ "=" + employeeID;
        PreparedStatement preparedStatement = con.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String empID = resultSet.getString("employeeID");
            String position = resultSet.getString("position");
            String fName = resultSet.getString("fName");
            String lName = resultSet.getString("lName");
            String gender = resultSet.getString("gender");
            String hiringDate = resultSet.getString("hiringDate");
            String email = resultSet.getString("email");
            String salary = resultSet.getString("salary");
            String DOB = resultSet.getString("DOB");
            String deptID = resultSet.getString("deptID");
            String branchID = resultSet.getString("branchID");
            System.out.println(resultSet);
            info = "EmployeeID: " + empID + "\nPosition: " + position + "\nName: " + fName + " "+ lName + "\nGender: "+ gender + "\nHiringDate: "+ hiringDate+ "\nDOB: " + DOB + "\nEmail: "+ email + "\nSalary: " + salary + "\nDeptID: " + deptID + "\nBranchID: " + branchID;
        }

        resultSet.close();
        preparedStatement.close();
        con.close();

        return info;
    }

    public static List<String> searchPresentOn(String day) throws SQLException, ClassNotFoundException {
        List<String> info = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT e.employeeID, e.fName, e.lName\n" +
                "FROM employee e\n" +
                "LEFT JOIN\n" +
                "attendance a ON e.employeeID = a.employeeID " + // Added a space here
                "WHERE a.date = '" + day + "' AND a.employeeID IS NOT NULL;";


        PreparedStatement preparedStatement = con.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String empID = resultSet.getString("employeeID");
            String fName = resultSet.getString("fName");
            String lName = resultSet.getString("lName");
            System.out.println(resultSet);
            info.add(empID + " - " + fName + " "+ lName) ;
        }

        resultSet.close();
        preparedStatement.close();
        con.close();

        return info;
    }

    public static List<String> searchPresentIn(String year, String month) throws SQLException, ClassNotFoundException {
        List<String> info = new ArrayList<>();

        // Pad month with leading zero if necessary
        if (month.length() == 1) {
            month = "0" + month;
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT a.employeeID, e.fName, e.lName, COUNT(DISTINCT date) AS attended_days " +
                "FROM attendance a " +
                "INNER JOIN employee e ON a.employeeID = e.employeeID " +
                "WHERE MONTH(a.date) = '" + month + "' AND YEAR(a.date) = '" + year + "' " +
                "GROUP BY a.employeeID, e.fName, e.lName " +
                "HAVING COUNT(DISTINCT date) = DAY(LAST_DAY('" + year + "-" + month + "-01'))";

        PreparedStatement preparedStatement = con.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String empID = resultSet.getString("employeeID");
            String fName = resultSet.getString("fName");
            String lName = resultSet.getString("lName");
            System.out.println(resultSet);
            info.add(empID + " - " + fName + " "+ lName) ;
        }

        resultSet.close();
        preparedStatement.close();
        con.close();

        return info;
    }

    public static void deleteEmployee(String empID) throws SQLException, ClassNotFoundException {
        List<String> tasks = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "DELETE FROM employee WHERE employeeID = " + empID;
        PreparedStatement preparedStatement = con.prepareStatement(query);

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        con.close();

        if (rowsAffected > 0)
            JOptionPane.showMessageDialog(null, "Employee removed Successfully");
        else
            JOptionPane.showMessageDialog(null, "Failed remove Employee");

    }
}
