package AddEmployeePage;

import javax.swing.*;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;

import static AddEmployeePage.PasswordEncrypt.generateSalt;
import static AddEmployeePage.PasswordEncrypt.hashPassword;

public class AddEmpSQLQueries {
    public static void addUser(String userName, String hash, String salt, int employeeID) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "INSERT INTO users (userName, hash, salt, employeeID) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, hash);
        preparedStatement.setString(3, salt);
        preparedStatement.setInt(4, employeeID);

        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        con.close();

        if (rowsAffected > 0)
            JOptionPane.showMessageDialog(null, "Employee Added Successfully.");
        else
            JOptionPane.showMessageDialog(null, "Failed to add Employee.");
    }

    public static void addEmployee(String position, String fName, String lName, String gender, Date hiringDate, String email, String salary, Date DOB, String deptID, String branchID) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

        String query = "INSERT INTO employee (position, fName, lName, gender, hiringDate, email, salary, DOB, deptID, branchID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); // Specify RETURN_GENERATED_KEYS option

        preparedStatement.setString(1, position);
        preparedStatement.setString(2, fName);
        preparedStatement.setString(3, lName);
        preparedStatement.setString(4, gender);
        preparedStatement.setDate(5, hiringDate);
        preparedStatement.setString(6, email);
        preparedStatement.setString(7, salary);
        preparedStatement.setDate(8, DOB);
        preparedStatement.setString(9, deptID);
        preparedStatement.setString(10, branchID);

        int rowsAffected = preparedStatement.executeUpdate();

        int generatedEmployeeID = -1;
        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next())
                generatedEmployeeID = generatedKeys.getInt(1);
        }

        preparedStatement.close();
        con.close();

        if (generatedEmployeeID != -1) {
            String password = lName+String.valueOf(generatedEmployeeID);
            byte[] salt;
            String hashedPassword;
            try {
                salt = generateSalt();
                hashedPassword = hashPassword(password, salt);
                AddEmpSQLQueries.addUser(fName+lName+String.valueOf(generatedEmployeeID), hashedPassword, salt.toString(), generatedEmployeeID);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Failed to add Employee.");
    }
}