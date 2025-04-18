package AddEmployeePage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import AdminPage.Admin;
import CustomWidgets.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class AddEmployee extends JFrame {
    public AddEmployee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("Add Employee");
        welcomeText.setBorder(new EmptyBorder(20, 0, 20, 0));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        welcomeText.setForeground(new Color(47, 45, 82));
        mainPanel.add(welcomeText);

        // EMPLOYEE DETAILS PANEL
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Adding Employee Details Fields
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Adding Employee Details Fields
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(firstNameLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(firstNameField,gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField lastNameField = new JTextField();
        lastNameField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(lastNameLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(lastNameField,gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel dobChooserModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl dobChooserPanel = new JDatePanelImpl(dobChooserModel, p);
        JDatePickerImpl dobChooserPicker = new JDatePickerImpl(dobChooserPanel, new DateLabelFormatter());
        detailsPanel.add(dobLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(dobChooserPicker, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel hiringDateLabel = new JLabel("Date of Hiring:");
        hiringDateLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel hiringDateChooserModel = new UtilDateModel();
        JDatePanelImpl hiringDateChooserPanel = new JDatePanelImpl(hiringDateChooserModel, p);
        JDatePickerImpl hiringDateChooserPicker = new JDatePickerImpl(hiringDateChooserPanel, new DateLabelFormatter());
        detailsPanel.add(hiringDateLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(hiringDateChooserPicker, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        String[] genders = {"Male", "Female", "Other"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        detailsPanel.add(genderLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(genderComboBox, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(emailLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(emailField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel contactLabel = new JLabel("Contact Numbers:");
        contactLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextArea contactArea = new JTextArea();
        contactArea.setPreferredSize(new Dimension(600, 30));
        JScrollPane contactScrollPane = new JScrollPane(contactArea);
        detailsPanel.add(contactLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(contactScrollPane, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel deptIdLabel = new JLabel("Department Id:");
        deptIdLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField deptIdField = new JTextField();
        deptIdField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(deptIdLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(deptIdField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField positionField = new JTextField();
        positionField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(positionLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(positionField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField salaryField = new JTextField();
        salaryField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(salaryLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(salaryField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel branchLabel = new JLabel("Branch Id:");
        branchLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField branchField = new JTextField();
        branchField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(branchLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(branchField, gbc);

        // SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0,0,60,0));
        add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(scrollPane);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment
        JButton addButton = new JButton("Add Employee");
        addButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(47, 45, 82));
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    AddEmpSQLQueries addEmpSQLQueries = new AddEmpSQLQueries();
                    addEmpSQLQueries.addEmployee(
                            positionField.getText(),
                            firstNameField.getText(),
                            lastNameField.getText(),
                            (String) genderComboBox.getSelectedItem(),
                            DateLabelFormatter.convertToSQLDate(hiringDateChooserPicker.getModel().getValue()),
                            emailField.getText(),
                            salaryField.getText(),
                            DateLabelFormatter.convertToSQLDate(dobChooserPicker.getModel().getValue()),
                            deptIdField.getText(),
                            branchField.getText());
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        JButton goBackButton = new JButton("Go Back");
        goBackButton.setBackground(new Color(47, 45, 82));
        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        goBackButton.setPreferredSize(new Dimension(200, 40));
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new Admin().setVisible(true);
            }
        });
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createHorizontalStrut(40));
        buttonsPanel.add(goBackButton);

        mainPanel.add(buttonsPanel);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddEmployee().setVisible(true);
            }
        });
    }
}
