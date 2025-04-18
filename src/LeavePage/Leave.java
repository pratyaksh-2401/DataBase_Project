package LeavePage;

import AdminPage.Admin;
import CustomWidgets.DateLabelFormatter;
import EmployeePage.Employee;
import UserGlobalData.UserGlobalData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Properties;

public class Leave extends JFrame {
    public Leave() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 20, 160, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("LEAVE APPLICATION PORTAL");
        welcomeText.setBorder(new EmptyBorder(0, 0, 40, 0));
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        welcomeText.setFont(new Font(Font.SERIF, Font.BOLD, 42));
        welcomeText.setForeground(new Color(47, 45, 82));
        mainPanel.add(Box.createVerticalStrut(20)); // Add some vertical space
        mainPanel.add(welcomeText);

        // DATE PICKERS AND BUTTONS PANEL
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

// Date pickers and buttons panel
        JPanel datePickersAndButtonsPanel = new JPanel();
        datePickersAndButtonsPanel.setLayout(new BoxLayout(datePickersAndButtonsPanel, BoxLayout.Y_AXIS));

// Date pickers panel
        JPanel datePickersPanel = new JPanel();
        datePickersPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment

// From Date Picker
        JPanel fromDatePanel = new JPanel();
        fromDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment
        JLabel fromLabel = new JLabel("From: ");
        fromLabel.setForeground(new Color(47, 45, 82));
        fromLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        fromDatePanel.add(fromLabel);
        fromDatePanel.add(datePicker);

// To Date Picker
        JPanel toDatePanel = new JPanel();
        toDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment
        JLabel toLabel = new JLabel("To: ");
        toLabel.setForeground(new Color(47, 45, 82));
        toLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel modelTo = new UtilDateModel();
        JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);
        JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
        toDatePanel.add(toLabel);
        toDatePanel.add(datePickerTo);

// Add From and To panels to the Date pickers panel
        datePickersPanel.add(fromDatePanel);
        datePickersPanel.add(Box.createHorizontalStrut(20)); // Add space between From and To
        datePickersPanel.add(toDatePanel);

// Leave Reason Text Field
        JPanel reasonPanel = new JPanel();
        reasonPanel.setLayout(new BoxLayout(reasonPanel, BoxLayout.Y_AXIS)); // Vertical alignment
        JLabel reasonLabel = new JLabel("Leave Reason:");
        reasonLabel.setForeground(new Color(47, 45, 82));
        reasonLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        JTextField reasonTextArea = new JTextField();
        reasonPanel.add(reasonLabel);
        reasonPanel.add(reasonTextArea);

// Buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Horizontal alignment
        JButton applyLeaveButton = new JButton("Apply Leave");
        applyLeaveButton.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        applyLeaveButton.setForeground(Color.WHITE);
        applyLeaveButton.setBackground(new Color(47, 45, 82));
        applyLeaveButton.setPreferredSize(new Dimension(200, 40));
        applyLeaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LeaveSQLQueries.applyLeave(DateLabelFormatter.convertToSQLDate(datePicker.getModel().getValue()), DateLabelFormatter.convertToSQLDate(datePickerTo.getModel().getValue()));
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                // Open new page logic here
                dispose();
                new Employee().setVisible(true);
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
                if (UserGlobalData.isAdmin())
                    new Admin().setVisible(true);
                else
                    new Employee().setVisible(true);

            }
        });
        buttonsPanel.add(applyLeaveButton);
        buttonsPanel.add(Box.createHorizontalStrut(40));
        buttonsPanel.add(goBackButton);

// Add all panels to the datePickersAndButtonsPanel
        datePickersAndButtonsPanel.add(datePickersPanel);
        datePickersAndButtonsPanel.add(Box.createVerticalStrut(10)); // Add some vertical spacing
        datePickersAndButtonsPanel.add(reasonPanel);
        datePickersAndButtonsPanel.add(Box.createVerticalStrut(60)); // Add some vertical spacing
        datePickersAndButtonsPanel.add(buttonsPanel);

        inputPanel.add(datePickersAndButtonsPanel);

        mainPanel.add(inputPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Leave().setVisible(true);
        });
    }
}
