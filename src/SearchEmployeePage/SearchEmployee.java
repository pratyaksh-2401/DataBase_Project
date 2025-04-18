package SearchEmployeePage;

import AddProjectPage.AddProjectSQLQueries;
import AdminPage.Admin;
import CustomWidgets.DateLabelFormatter;
import CustomWidgets.TransparentJPanel;
import AssignTask.AssignTask;
import LeavePage.Leave;
import PersonalInfoPage.PersonalInfo;
import PersonalInfoPage.infoSQLQueries;
import UserGlobalData.UserGlobalData;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class SearchEmployee extends JFrame {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public SearchEmployee() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        // MAIN PANEL
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(40, 20, 60, 20));

        // WELCOME TEXT
        JLabel welcomeText = new JLabel("SEARCH/MANAGE EMPLOYEE");
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

        //Date Picker
        JLabel startLabel = new JLabel("Select Date:");
        startLabel.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
        UtilDateModel startChooserModel = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl startChooserPanel = new JDatePanelImpl(startChooserModel, p);
        JDatePickerImpl startChooserPicker = new JDatePickerImpl(startChooserPanel, new DateLabelFormatter());
        leftPanel.add(startLabel);

        leftPanel.add(startChooserPicker);

        // Label for the search bar
        JLabel searchLabel = new JLabel("Employee ID: ");
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

        JButton absOn = new JButton("Present On");
        absOn.setForeground(Color.WHITE); // Set text color
        absOn.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        absOn.setBackground(new Color(47, 45, 82)); // Set background color
        absOn.setPreferredSize(new Dimension(600, 60));
        buttonPanel.add(absOn, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton preIn = new JButton("Present In");
        preIn.setForeground(Color.WHITE); // Set text color
        preIn.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        preIn.setBackground(new Color(47, 45, 82)); // Set background color
        preIn.setPreferredSize(new Dimension(600, 60));
        buttonPanel.add(preIn, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        JButton button2 = new JButton("Assign Task");
        button2.setForeground(Color.WHITE); // Set text color
        button2.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        button2.setBackground(new Color(47, 45, 82)); // Set background color
        button2.setPreferredSize(new Dimension(600, 60));
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open new page logic here
                dispose();
                new AssignTask(searchBar.getText()).setVisible(true);
            }
        });
        buttonPanel.add(button2, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

        // Delete Employee
        JButton delEmp = new JButton("Remove Employee");
        delEmp.setForeground(Color.WHITE); // Set text color
        delEmp.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        delEmp.setBackground(new Color(47, 45, 82)); // Set background color
        delEmp.setPreferredSize(new Dimension(600, 60));
        buttonPanel.add(delEmp, gbc);
        buttonPanel.add(Box.createVerticalStrut(16), gbc);

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
        textArea.setText("**INSTRUCTIONS**\n\n1) To search details of any Employee, write Employee ID and press 'Search'.\n\n2) To list employees who are Present on a particular date, select date and press 'Present On'.\n\n3) To list employees who have attended work on all working days of a specific month, enter date and press 'Present In'.");
        textArea.setFont(new Font(Font.DIALOG, Font.PLAIN, 24));
        textArea.setForeground(new Color(47, 45, 82));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);


        // Calling seach method
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchEmpSQLQueries search = new SearchEmpSQLQueries();
                String information;
                String empId = searchBar.getText();
                try {
                    information = search.searchEmployee(empId);
                    textArea.setText(information);
                } catch (SQLException exp) {
                    throw new RuntimeException(exp);
                } catch (ClassNotFoundException exp) {
                    throw new RuntimeException(exp);
                }
            }
        });

        absOn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startChooserModel.getValue());
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String date = year + "-" + month + "-" + day;
                SearchEmpSQLQueries search = new SearchEmpSQLQueries();
                List<String> information;

                try {
                    information = search.searchPresentOn(date);
                    textArea.setText("Following were present on selected day:\nEmployee ID - Name\n");
                    int size = information.size();
                    int i = 0;
                    while (i < size) {
                        textArea.append(information.get(i++) + "\n");
                    }
                } catch (SQLException exp) {
                    throw new RuntimeException(exp);
                } catch (ClassNotFoundException exp) {
                    throw new RuntimeException(exp);
                }
            }
        });

        preIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startChooserModel.getValue());
                String year = "" + calendar.get(Calendar.YEAR);
                int monthInt = calendar.get(Calendar.MONTH) + 1;
                String month = "" + monthInt;

                SearchEmpSQLQueries search = new SearchEmpSQLQueries();
                List<String> information;

                try {
                    information = search.searchPresentIn(year, month);
                    textArea.setText("Following were present on selected month of year:\nEmployee ID - Name\n");
                    int size = information.size();
                    System.out.println(size);
                    int i = 0;
                    while (i < size) {
                        textArea.append(information.get(i++) + "\n");
                    }
                } catch (SQLException exp) {
                    throw new RuntimeException(exp);
                } catch (ClassNotFoundException exp) {
                    throw new RuntimeException(exp);
                }
            }
        });

        delEmp.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    SearchEmpSQLQueries search = new SearchEmpSQLQueries();
                    String empId = searchBar.getText();
                    search.deleteEmployee(empId);
                }
                catch(Exception ex){
                    ex.printStackTrace();
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
        SwingUtilities.invokeLater(() -> new SearchEmployee().setVisible(true));
    }
}
