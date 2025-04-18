package AuthPage;

import AdminPage.Admin;
import CustomWidgets.GradientJPanel;
import CustomWidgets.RoundBorder;
import CustomWidgets.TransparentJPanel;
import EmployeePage.Employee;
import PersonalInfoPage.PersonalInfo;
import UserGlobalData.UserGlobalData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.List;

public class Auth extends JFrame {
    private JTextField usernameField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private button.RoundButton loginButton = new button.RoundButton();

    public Auth() {
        setTitle("Employee Portal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new GridLayout(1, 2));

        GradientJPanel leftPanel = new GradientJPanel();
        leftPanel.setLayout(new GridLayout(3, 1));
        leftPanel.setBorder(new EmptyBorder(60, 20, 20, 20));

        // TITLE
        TransparentJPanel titlePanel = new TransparentJPanel();
        ImageIcon ct1 = new ImageIcon("src/AuthPage/face.png");
        Image ct2 = ct1.getImage().getScaledInstance(240, 240, Image.SCALE_DEFAULT);
        ImageIcon ct3 = new ImageIcon(ct2);
        titlePanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("LOGIN PORTAL");
        title.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.BOTTOM);
        title.setIcon(ct3);
        titlePanel.add(title);
        titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        // TEXT FIELDS
        TransparentJPanel usernameRow = new TransparentJPanel();
        TransparentJPanel usernameFieldPanel = new TransparentJPanel();
        usernameFieldPanel.setLayout(new BorderLayout());
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        usernameLabel.setForeground(Color.WHITE);
        usernameField.setPreferredSize(new Dimension(500, 36));
        usernameField.setOpaque(true);
        usernameRow.add(usernameLabel);
        usernameRow.add(usernameField);
        usernameFieldPanel.add(usernameRow, BorderLayout.SOUTH);
        usernameFieldPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameFieldPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        TransparentJPanel passwordFieldPanel = new TransparentJPanel();
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        passwordLabel.setForeground(Color.WHITE);
        passwordField.setPreferredSize(new Dimension(500, 36));
        passwordFieldPanel.setPreferredSize(new Dimension(550, 36));
        passwordFieldPanel.add(passwordLabel);
        passwordFieldPanel.add(passwordField);

        TransparentJPanel textFieldPanel = new TransparentJPanel();
        textFieldPanel.setLayout(new GridLayout(2, 1));
        textFieldPanel.add(usernameFieldPanel);
        textFieldPanel.add(passwordFieldPanel);

        // LOGIN BUTTON
        TransparentJPanel buttonPanel = new TransparentJPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        loginButton.setRadius(12);
        loginButton.setPreferredSize(new Dimension(250, 40));
        loginButton.setColor(new Color(133, 189, 118));
        loginButton.setBorder(new RoundBorder(12, new Color(133, 189, 118)));
        loginButton.setText("LOGIN");
        loginButton.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
        loginButton.setBackground(new Color(188, 255, 209, 255));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

                    String query = "SELECT employee.employeeID, users.hash, users.salt, employee.position, employee.fName, employee.lName, branch.location \n" +
                            "FROM users \n" +
                            "INNER JOIN employee ON users.employeeID = employee.employeeID \n" +
                            "INNER JOIN branch ON employee.branchID = branch.branchID \n" +
                            "WHERE userName = '" + usernameField.getText() + "'";
                    PreparedStatement preparedStatement = con.prepareStatement(query);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Invalid username. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        throw new SQLException("invalid username");
                    } else {
                        do {
                            int employeeID = resultSet.getInt("employeeID");
                            String storedHash = resultSet.getString("hash");
                            String storedSalt = resultSet.getString("salt");
                            String storedPosition = resultSet.getString("position");
                            String name = resultSet.getString("fName") + " " + resultSet.getString("lName");
                            String branch = resultSet.getString("location");

                            // Checking validate password
                            if (PasswordDecrypt.checkPassword(storedHash, storedSalt, new String(passwordField.getPassword()))) {
                                // Open new page logic
                                dispose();
                                if (storedPosition.equals("Admin") || storedPosition.equals("Branch Manager") || storedPosition.equals("Project Manager")) {
                                    UserGlobalData.setUserEmployeeID(employeeID);
                                    UserGlobalData.setUserName(usernameField.getText());
                                    UserGlobalData.setUserFullName(name);
                                    UserGlobalData.setUserBranch(branch);
                                    UserGlobalData.setIsAdmin(true);
                                    new Admin().setVisible(true);
                                } else {
                                    UserGlobalData.setUserEmployeeID(employeeID);
                                    UserGlobalData.setUserName(usernameField.getText());
                                    UserGlobalData.setUserFullName(name);
                                    UserGlobalData.setUserBranch(branch);
                                    UserGlobalData.setIsAdmin(false);
                                    UserGlobalData.setUserTasks(AuthSQLQueries.getEmployeeTasks(employeeID));

                                    new Employee().setVisible(true);
                                }
                            } else {
                                // Password is incorrect, show an error message to the user
                                JOptionPane.showMessageDialog(null, "Invalid password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } while (resultSet.next());
                    }

                    // Close resources
                    resultSet.close();
                    preparedStatement.close();
                    con.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        buttonPanel.add(loginButton, gbc);

        leftPanel.add(titlePanel);
        leftPanel.add(textFieldPanel);
        leftPanel.add(buttonPanel);

        add(leftPanel);
        add(new JPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Auth().setVisible(true); // Create an instance of Auth and display the GUI to be removed after completion of project
        });
    }
}