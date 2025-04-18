package AssignTask;

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
import java.util.Properties;

public class AssignTask extends JFrame {
    public AssignTask(String employeeID) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("Assign Task");
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
        JLabel idNameLabel = new JLabel("Employee ID:");
        idNameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField idNameField = new JTextField(employeeID);
        idNameField.setEditable(false);
        idNameField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(idNameLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(idNameField,gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel nameLabel = new JLabel("Task Name:");
        nameLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(nameLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(nameField, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel deadlineDateLabel = new JLabel("Date of Deadline:");
        deadlineDateLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel deadlineDateChooserModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl deadlineDateChooserPanel = new JDatePanelImpl(deadlineDateChooserModel, p);
        JDatePickerImpl deadlineDateChooserPicker = new JDatePickerImpl(deadlineDateChooserPanel, new DateLabelFormatter());
        detailsPanel.add(deadlineDateLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(deadlineDateChooserPicker, gbc);
        gbc.gridy++;
        gbc.gridx = 0;

        JLabel projectIDLabel = new JLabel("Project ID:");
        projectIDLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField projectIDField = new JTextField();
        projectIDField.setPreferredSize(new Dimension(600, 30));
        detailsPanel.add(projectIDLabel, gbc);
        gbc.gridx++;
        detailsPanel.add(projectIDField, gbc);
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
        JButton addButton = new JButton("Add Employee");
        addButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(new Color(47, 45, 82));
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    AssignTaskSQLQueries.assignTask(Integer.parseInt(employeeID), DateLabelFormatter.convertToSQLDate(deadlineDateChooserPicker.getModel().getValue()), nameField.getText(), projectIDField.getText());
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
}
