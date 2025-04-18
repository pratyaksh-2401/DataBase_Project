package AddProjectPage;

import AddEmployeePage.AddEmpSQLQueries;
import AdminPage.Admin;
import CustomWidgets.DateLabelFormatter;
import ManageProjectPage.ManageProject;
import SearchEmployeePage.SearchEmployee;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;

public class AddProject extends JFrame {
    public AddProject() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("Add Project");
        welcomeText.setBorder(new EmptyBorder(20, 0, 20, 0));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        welcomeText.setForeground(new Color(47, 45, 82));
        mainPanel.add(welcomeText);

        // EMPLOYEE DETAILS PANEL
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Grid Setting
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Add some padding

        // Adding Employee Details Fields
        JLabel projectIDLabel = new JLabel("Project ID:");
        projectIDLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField projectIDField = new JTextField();
        projectIDField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(projectIDLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(projectIDField,gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel projectNameLabel = new JLabel("Project Name:");
        projectNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField projectNameField = new JTextField();
        projectNameField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(projectNameLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(projectNameField,gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        String[] status = {"In Progress", "Completed", "Cancelled", "On Hold"};
        JComboBox<String> statusComboBox = new JComboBox<>(status);
        detailsPanel.add(statusLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(statusComboBox, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel budgetLabel = new JLabel("Current Budget:");
        budgetLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField budgetField = new JTextField();
        budgetField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(budgetLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(budgetField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel startLabel = new JLabel("Start Date:");
        startLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel startChooserModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl startChooserPanel = new JDatePanelImpl(startChooserModel, p);
        JDatePickerImpl startChooserPicker = new JDatePickerImpl(startChooserPanel, new DateLabelFormatter());
        detailsPanel.add(startLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(startChooserPicker, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel endLabel = new JLabel("End Date:");
        endLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel endChooserModel = new UtilDateModel();
        JDatePanelImpl endChooserPanel = new JDatePanelImpl(endChooserModel, p);
        JDatePickerImpl endChooserPicker = new JDatePickerImpl(endChooserPanel, new DateLabelFormatter());
        detailsPanel.add(endLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(endChooserPicker, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        // SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0,0,60,0));
        add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(scrollPane);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment
        JButton addButton = new JButton("Add Project");
        addButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(47, 45, 82));
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startChooserModel.getValue());
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    String startDate = year + "-" + month + "-" + day;

                    calendar.setTime(endChooserModel.getValue());
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH) + 1;
                    day = calendar.get(Calendar.DAY_OF_MONTH);
                    String endDate = year + "-" + month + "-" + day;


                    AddProjectSQLQueries addProjectSQLQueries = new AddProjectSQLQueries();
                    addProjectSQLQueries.addProject(
                            projectIDField.getText(),
                            projectNameField.getText(),
                            (String) statusComboBox.getSelectedItem(),
                            budgetField.getText(),
                            startDate,
                            endDate);
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
                new ManageProject().setVisible(true);
            }
        });
        buttonsPanel.add(addButton);
        buttonsPanel.add(Box.createHorizontalStrut(40));
        buttonsPanel.add(goBackButton);

        mainPanel.add(buttonsPanel);

        add(mainPanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddProject().setVisible(true));
    }
}
