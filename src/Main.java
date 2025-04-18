import AuthPage.Auth;
import EmployeePage.Employee;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Auth().setVisible(true); // Create an instance of Auth and display the GUI
        });
    }
}