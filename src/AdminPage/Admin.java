package AdminPage;

import AddEmployeePage.AddEmployee;
import AuthPage.Auth;
import CustomWidgets.TransparentJPanel;
import EmployeePage.EmployeeSQLQueries;
import LeavePage.Leave;
import ManageProjectPage.ManageProject;
import PersonalInfoPage.PersonalInfo;
import SearchEmployeePage.SearchEmployee;
import UserGlobalData.UserGlobalData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Admin extends JFrame {
    public Admin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 20, 40, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("WELCOME " + UserGlobalData.getUserFullName().toUpperCase());
        welcomeText.setBorder(new EmptyBorder(0, 0, 40, 0));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        welcomeText.setForeground(new Color(47, 45, 82));
        mainPanel.add(Box.createVerticalStrut(20)); // Add some vertical space
        mainPanel.add(welcomeText);

        // BUTTON SIDE: Buttons
        TransparentJPanel buttonPanel = new TransparentJPanel();
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;

        JButton button1 = new JButton("Personal Information");
        button1.setForeground(Color.WHITE); // Set text color
        button1.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button1.setBackground(new Color(47, 45, 82)); // Set background color
        button1.setPreferredSize(new Dimension(600, 60));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new PersonalInfo().setVisible(true);
            }
        });
        buttonPanel.add(button1, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button2 = new JButton("Add New Employee");
        button2.setForeground(Color.WHITE); // Set text color
        button2.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button2.setBackground(new Color(47, 45, 82)); // Set background color
        button2.setPreferredSize(new Dimension(600, 60));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new AddEmployee().setVisible(true);
            }
        });
        buttonPanel.add(button2, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button5 = new JButton("Search Employee");
        button5.setForeground(Color.WHITE); // Set text color
        button5.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button5.setBackground(new Color(47, 45, 82)); // Set background color
        button5.setPreferredSize(new Dimension(600, 60));
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new SearchEmployee().setVisible(true);
            }
        });
        buttonPanel.add(button5, gbc);
        buttonPanel.setBorder(new EmptyBorder(0, 120, 0, 120));
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button6 = new JButton("Manage Projects");
        button6.setForeground(Color.WHITE); // Set text color
        button6.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button6.setBackground(new Color(47, 45, 82)); // Set background color
        button6.setPreferredSize(new Dimension(600, 60));
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new ManageProject().setVisible(true);
            }
        });
        buttonPanel.add(button6, gbc);
        buttonPanel.setBorder(new EmptyBorder(0, 120, 0, 120));
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button3 = new JButton("Mark Attendance");
        button3.setForeground(Color.WHITE); // Set text color
        button3.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button3.setBackground(new Color(47, 45, 82)); // Set background color
        button3.setPreferredSize(new Dimension(600, 60));
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EmployeeSQLQueries.markAttendance();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buttonPanel.add(button3, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button4 = new JButton("Apply Leave");
        button4.setForeground(Color.WHITE); // Set text color
        button4.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button4.setBackground(new Color(47, 45, 82)); // Set background color
        button4.setPreferredSize(new Dimension(600, 60));
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new Leave().setVisible(true);
            }
        });
        buttonPanel.add(button4, gbc);
        buttonPanel.setBorder(new EmptyBorder(0, 120, 0, 120));
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button7 = new JButton("Logout");
        button7.setForeground(Color.WHITE); // Set text color
        button7.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button7.setBackground(new Color(47, 45, 82)); // Set background color
        button7.setPreferredSize(new Dimension(600, 60));
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserGlobalData.eraseData();
                // Open new page logic here
                dispose();
                new Auth().setVisible(true);
            }
        });
        buttonPanel.add(button7, gbc);
        buttonPanel.setBorder(new EmptyBorder(0, 120, 0, 120));

        mainPanel.add(Box.createVerticalGlue()); // Add vertical glue to push components upwards
        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Admin().setVisible(true);
        });
    }
}
