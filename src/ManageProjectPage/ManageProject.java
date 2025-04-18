package ManageProjectPage;

import AddProjectPage.AddProject;
import AdminPage.Admin;
import CustomWidgets.TransparentJPanel;
import EditProjectPage.EditProject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ManageProject extends JFrame {
    public ManageProject() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 20, 60, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("MANAGE PROJECTS");
        welcomeText.setBorder(new EmptyBorder(0, 0, 40, 0));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        welcomeText.setForeground(new Color(47, 45, 82));
        mainPanel.add(Box.createVerticalStrut(20)); // Add some vertical space
        mainPanel.add(welcomeText);

        //CONTENT PANEL
        JPanel contentPanel = new JPanel(new GridLayout(1, 2));

        // LEFT SIDE: Buttons and Search Bar
        JPanel leftPanel = new JPanel();

        //Search Bar
        JTextField searchBar = new JTextField();
        searchBar.setPreferredSize(new Dimension(500, 50));
        searchBar.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        // Label for the search bar
        JLabel searchLabel = new JLabel("Project Name: ");
        searchLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        searchLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Panel to hold the label and search bar
        JPanel searchFieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchFieldPanel.add(searchLabel);
        searchFieldPanel.add(searchBar);
        leftPanel.add(searchFieldPanel);
        leftPanel.add(Box.createVerticalStrut(240));

        // BUTTON SIDE: Buttons
        TransparentJPanel buttonPanel = new TransparentJPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;

        JButton button1 = new JButton("Search");
        button1.setForeground(Color.WHITE); // Set text color
        button1.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button1.setBackground(new Color(47, 45, 82)); // Set background color
        button1.setPreferredSize(new Dimension(600, 60));
        buttonPanel.add(button1, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button2 = new JButton("Edit Project");
        button2.setForeground(Color.WHITE); // Set text color
        button2.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button2.setBackground(new Color(47, 45, 82)); // Set background color
        button2.setPreferredSize(new Dimension(600, 60));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pID = "", projectName = "", pstatus = "";
                int budget = 0;
                Date startDate = null, endDate = null;

                try {
                    String info = null;

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_management", "root", "Dustu@2002");

                    String query = "SELECT *\n" +
                            "FROM project p\n" +
                            "WHERE p.projectID = " + "\"" + searchBar.getText() + "\"";
                    PreparedStatement preparedStatement = con.prepareStatement(query);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        pID = resultSet.getString("projectID");
                        projectName = resultSet.getString("projectName");
                        pstatus = resultSet.getString("pstatus");
                        budget = resultSet.getInt("budget");
                        startDate = resultSet.getDate("startDate");
                        endDate = resultSet.getDate("endDate");
                        System.out.println(resultSet);
                        info = "Project ID: " + pID + "\nProject Name: " + projectName + "\nStatus: " + pstatus + "\nBudget: " + budget + "\nStart Date: " + startDate + "\nEnd Date: " + endDate;
                    }

                    resultSet.close();
                    preparedStatement.close();
                    con.close();
                } catch (Exception exp) {

                }
                // Open new page logic here
                dispose();
                new EditProject(pID, projectName, pstatus, startDate, endDate, budget).setVisible(true);
            }
        });
        buttonPanel.add(button2, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button4 = new JButton("Add New Project");
        button4.setForeground(Color.WHITE); // Set text color
        button4.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button4.setBackground(new Color(47, 45, 82)); // Set background color
        button4.setPreferredSize(new Dimension(600, 60));
        buttonPanel.add(button4, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new AddProject().setVisible(true);
            }
        });

        JButton button3 = new JButton("Back");
        button3.setForeground(Color.WHITE); // Set text color
        button3.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button3.setBackground(new Color(47, 45, 82)); // Set background color
        button3.setPreferredSize(new Dimension(600, 60));
        button3.setPreferredSize(new Dimension(600, 60));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new Admin().setVisible(true);
            }
        });
        buttonPanel.add(button3, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        buttonPanel.setBorder(new EmptyBorder(0, 120, 0, 120));
        leftPanel.add(buttonPanel);


        leftPanel.setBorder(new EmptyBorder(0, 120, 0, 120));

        // RIGHT SIDE: Multiline Text Area
        JTextArea textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setText("Enter Project ID and press Search");
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        textArea.setForeground(new Color(47, 45, 82));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Calling Search method for Projects
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProjectSQLQueries projectSQLQueries = new ProjectSQLQueries();
                String information;
                String pID = searchBar.getText();
                try {
                    information = projectSQLQueries.searchProject(pID);
                    textArea.setText(information);
                } catch (SQLException exp) {
                    throw new RuntimeException(exp);
                } catch (ClassNotFoundException exp) {
                    throw new RuntimeException(exp);
                }
            }
        });

        contentPanel.add(leftPanel);
        contentPanel.add(scrollPane);

        mainPanel.add(Box.createVerticalGlue()); // Add vertical glue to push components upwards
        mainPanel.add(contentPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManageProject().setVisible(true));
    }
}