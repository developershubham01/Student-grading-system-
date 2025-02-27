import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentGradingSystem extends JFrame implements ActionListener {
    JLabel lblName, lblSubjects, lblResult;
    JTextField txtName, txtMarks1, txtMarks2, txtMarks3, txtMarks4, txtMarks5;
    JButton btnCalculate, btnClear;
    JTextArea txtResult;

    public StudentGradingSystem() {
        setTitle("Student Grading System");
        setSize(400, 400);
        setLayout(new GridLayout(8, 2, 5, 5));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblName = new JLabel("Student Name:");
        txtName = new JTextField();
        lblSubjects = new JLabel("Enter Marks for 5 Subjects:");
        txtMarks1 = new JTextField();
        txtMarks2 = new JTextField();
        txtMarks3 = new JTextField();
        txtMarks4 = new JTextField();
        txtMarks5 = new JTextField();
        btnCalculate = new JButton("Calculate Grade");
        btnClear = new JButton("Clear");
        lblResult = new JLabel("Result:");
        txtResult = new JTextArea();
        txtResult.setEditable(false);

        btnCalculate.addActionListener(this);
        btnClear.addActionListener(this);

        add(lblName);
        add(txtName);
        add(lblSubjects);
        add(new JLabel());
        add(new JLabel("Subject 1:"));
        add(txtMarks1);
        add(new JLabel("Subject 2:"));
        add(txtMarks2);
        add(new JLabel("Subject 3:"));
        add(txtMarks3);
        add(new JLabel("Subject 4:"));
        add(txtMarks4);
        add(new JLabel("Subject 5:"));
        add(txtMarks5);
        add(btnCalculate);
        add(btnClear);
        add(lblResult);
        add(txtResult);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            try {
                String name = txtName.getText();
                int m1 = Integer.parseInt(txtMarks1.getText());
                int m2 = Integer.parseInt(txtMarks2.getText());
                int m3 = Integer.parseInt(txtMarks3.getText());
                int m4 = Integer.parseInt(txtMarks4.getText());
                int m5 = Integer.parseInt(txtMarks5.getText());
                int total = m1 + m2 + m3 + m4 + m5;
                double percentage = total / 5.0;
                String grade;

                if (percentage >= 90) grade = "A+";
                else if (percentage >= 80) grade = "A";
                else if (percentage >= 70) grade = "B+";
                else if (percentage >= 60) grade = "B";
                else if (percentage >= 50) grade = "C";
                else if (percentage >= 40) grade = "D";
                else grade = "F";

                txtResult.setText("Student: " + name + "\nTotal Marks: " + total +
                        "\nPercentage: " + percentage + "%\nGrade: " + grade);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid marks (0-100)", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnClear) {
            txtName.setText("");
            txtMarks1.setText("");
            txtMarks2.setText("");
            txtMarks3.setText("");
            txtMarks4.setText("");
            txtMarks5.setText("");
            txtResult.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentGradingSystem().setVisible(true);
        });
    }
}
