package PersonalInfoPage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class infoSQLQueries {
    public static String getInfo(int employeeID) throws SQLException, ClassNotFoundException {
        String info = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "SELECT *\n" +
                "FROM employee e\n" +
                "WHERE e.employeeID"+ "=" + employeeID;
        PreparedStatement preparedStatement = con.prepareStatement(query);
//        preparedStatement.setInt(1, employeeID);

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
}
