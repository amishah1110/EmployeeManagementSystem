package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class ViewEmployee extends JFrame implements ActionListener {

    JComboBox<String> cemployeeId;
    JTable table;
    JButton search, print, update, back;

    ViewEmployee() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout()); // Using BorderLayout for layout management

        JLabel searchlbl = new JLabel("Search by Employee Id");
        add(searchlbl, BorderLayout.NORTH);

        cemployeeId = new JComboBox<>();
        add(cemployeeId, BorderLayout.NORTH);

        // Load employee ids into the combo box
        loadEmployeeIds();

        table = new JTable();

        // Load all employee data into the table
        loadAllEmployees();

        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        search = new JButton("Search");
        search.addActionListener(this);
        buttonPanel.add(search);

        print = new JButton("Print");
        print.addActionListener(this);
        buttonPanel.add(print);

        update = new JButton("Update");
        update.addActionListener(this);
        buttonPanel.add(update);

        back = new JButton("Back");
        back.addActionListener(this);
        buttonPanel.add(back);

        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    private void loadEmployeeIds() {
        try (Conn c = new Conn(); ResultSet rs = c.s.executeQuery("select empId from employee")) {
            while (rs.next()) {
                cemployeeId.addItem(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAllEmployees() {
        try (Conn c = new Conn(); ResultSet rs = c.s.executeQuery("select * from employee")) {
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
            String selectedEmpId = cemployeeId.getSelectedItem().toString();
            loadEmployeeById(selectedEmpId);
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == update) {
            String selectedEmpId = cemployeeId.getSelectedItem().toString();
            setVisible(false);
            new UpdateEmployee(selectedEmpId);
        } else {
            setVisible(false);
            new Home();
        }
    }

    private void loadEmployeeById(String empId) {
        String query = "select * from employee where empId = ?";
        try (Conn c = new Conn(); PreparedStatement pstmt = c.prepareStatement(query)) {
            pstmt.setString(1, empId);
            ResultSet rs = pstmt.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ViewEmployee());
    }
}
