package PersonalInfoPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import AdminPage.Admin;
import EmployeePage.Employee;
import UserGlobalData.UserGlobalData;

import java.util.List;

public class PersonalInfo extends JFrame {

    public PersonalInfo() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 60, 100, 60));

        // calling method for info
        infoSQLQueries info = new infoSQLQueries();
        String information;
        int empId = UserGlobalData.getUserEmployeeID();
        try {
            information = info.getInfo(empId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("PERSONAL INFORMATION");
        welcomeText.setBorder(new EmptyBorder(0, 0, 40, 0));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        welcomeText.setForeground(new Color(47, 45, 82));
        mainPanel.add(Box.createVerticalStrut(20)); // Add some vertical space
        mainPanel.add(welcomeText);

        JPanel gap = new JPanel();
        gap.setPreferredSize(new Dimension(20, 0));

        JTextArea textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setText(information);
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        textArea.setForeground(new Color(47, 45, 82));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(47, 45, 82));
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        goBackButton.setPreferredSize(new Dimension(600, 40));
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                if (UserGlobalData.isAdmin())
                    new Admin().setVisible(true);
                else
                    new Employee().setVisible(true);

            }
        });

        mainPanel.add(gap);
        mainPanel.add(scrollPane);
        mainPanel.add(goBackButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PersonalInfo().setVisible(true);
        });
    }
}
