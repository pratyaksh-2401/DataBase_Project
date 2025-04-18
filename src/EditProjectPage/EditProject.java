package EditProjectPage;

import AdminPage.Admin;
import CustomWidgets.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class EditProject extends JFrame {
    public EditProject(String projectID, String projectName, String projectStatus, Date startDate, Date endDate, int budget) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("Edit Project");
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
        JLabel projectNameLabel = new JLabel("Project Name:");
        projectNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField projectNameField = new JTextField(projectName);
        projectNameField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(projectNameLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(projectNameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        String[] status = {"In Progress", "Completed", "Cancelled", "On Hold"};
        JComboBox<String> statusComboBox = new JComboBox<>(status);
        statusComboBox.setSelectedIndex(Arrays.asList(status).indexOf(projectStatus));
        detailsPanel.add(statusLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(statusComboBox, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel budgetLabel = new JLabel("Current Budget:");
        budgetLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField budgetField = new JTextField(budget);
        budgetField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(budgetLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(budgetField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        JLabel startLabel = new JLabel("Start Date:");
        startLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel startChooserModel = new UtilDateModel();
        startChooserModel.setDate(year, month, day);
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

        JDatePickerImpl endChooserPicker;
        if (endDate != null) {
            calendar.setTime(endDate);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DAY_OF_MONTH);

            JLabel endLabel = new JLabel("End Date:");
            endLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
            UtilDateModel endChooserModel = new UtilDateModel();
            endChooserModel.setDate(year, month, day);
            JDatePanelImpl endChooserPanel = new JDatePanelImpl(endChooserModel, p);
            endChooserPicker = new JDatePickerImpl(endChooserPanel, new DateLabelFormatter());
            detailsPanel.add(endLabel, gbc);
            gbc.gridx++;
            detailsPanel.add(endChooserPicker, gbc);
        } else {
            JLabel endLabel = new JLabel("End Date:");
            endLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
            UtilDateModel endChooserModel = new UtilDateModel();
            JDatePanelImpl endChooserPanel = new JDatePanelImpl(endChooserModel, p);
            endChooserPicker = new JDatePickerImpl(endChooserPanel, new DateLabelFormatter());
            detailsPanel.add(endLabel, gbc);
            gbc.gridx++;
            detailsPanel.add(endChooserPicker, gbc);
        }
        gbc.gridy++;
        gbc.gridx = 0;

        // SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(detailsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(new EmptyBorder(0, 0, 60, 0));
        add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(scrollPane);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment
        JButton addButton = new JButton("Edit Project");
        addButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(47, 45, 82));
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EditProjectSQLQueries.editProject(projectID, projectNameField.getText(), (String) statusComboBox.getSelectedItem().toString(), Integer.getInteger(budgetField.getText()), DateLabelFormatter.convertToSQLDate(startChooserPicker.getModel().getValue()), DateLabelFormatter.convertToSQLDate(endChooserPicker.getModel().getValue()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
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
}
