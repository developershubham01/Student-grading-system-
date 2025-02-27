import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TC extends JFrame {
    JLabel l1, l2, l3, l4;
    JComboBox<String> tc1, tc2;
    JTextField t1, t2;
    JButton b, dot, ac, bs, pm;
    JButton n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
    Timer timer;
    Color originalColor;

    public TC(String s) {
        super(s);
        setComp();
        setSize(400, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setComp() {
        String[] arr = { "Celsius", "Fahrenheit", "Kelvin", "Rankine", "Reaumur" };
        l1 = new JLabel("To");
        l2 = new JLabel("Enter the Value:");
        l3 = new JLabel("Converted Value:");
        l4 = new JLabel("From");
        tc1 = new JComboBox<>(arr);
        tc2 = new JComboBox<>(arr);
        t1 = new JTextField();
        t2 = new JTextField();
        t2.setEditable(false);
        originalColor = t2.getBackground();

        b = new JButton("Convert");
        b.addActionListener(new Handler());

        setLayout(null);
        tc1.setBounds(75, 50, 100, 20);
        t1.setBounds(200, 50, 100, 20);
        l1.setBounds(100, 75, 50, 20);
        l4.setBounds(90, 25, 50, 20);
        l2.setBounds(200, 35, 100, 20);
        l3.setBounds(200, 85, 100, 20);
        tc2.setBounds(75, 100, 100, 20);
        t2.setBounds(200, 100, 100, 20);
        b.setBounds(138, 150, 100, 20);

        add(tc1);
        add(tc2);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(t1);
        add(t2);
        add(b);
    }

    class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String from = (String) tc1.getSelectedItem();
            String to = (String) tc2.getSelectedItem();
            try {
                float input = Float.parseFloat(t1.getText());
                float result = convertTemperature(input, from, to);
                t2.setText(String.format("%.2f", result));
                animateResultField();
            } catch (NumberFormatException ex) {
                t2.setText("Invalid Input");
            }
        }
    }

    private float convertTemperature(float value, String from, String to) {
        if (from.equals(to)) return value;
        if (from.equals("Celsius")) {
            if (to.equals("Fahrenheit")) return (value * 9 / 5) + 32;
            if (to.equals("Kelvin")) return value + 273.15f;
            if (to.equals("Rankine")) return (value + 273.15f) * 9 / 5;
            if (to.equals("Reaumur")) return value * 0.8f;
        }
        if (from.equals("Fahrenheit")) {
            if (to.equals("Celsius")) return (value - 32) * 5 / 9;
            if (to.equals("Kelvin")) return (value - 32) * 5 / 9 + 273.15f;
            if (to.equals("Rankine")) return value + 459.67f;
            if (to.equals("Reaumur")) return (value - 32) / 2.25f;
        }
        if (from.equals("Kelvin")) {
            if (to.equals("Celsius")) return value - 273.15f;
            if (to.equals("Fahrenheit")) return (value - 273.15f) * 9 / 5 + 32;
            if (to.equals("Rankine")) return value * 9 / 5;
            if (to.equals("Reaumur")) return (value - 273.15f) * 0.8f;
        }
        if (from.equals("Rankine")) {
            if (to.equals("Celsius")) return (value - 491.67f) * 5 / 9;
            if (to.equals("Fahrenheit")) return value - 459.67f;
            if (to.equals("Kelvin")) return value * 5 / 9;
            if (to.equals("Reaumur")) return (value - 491.67f) * 4 / 9;
        }
        if (from.equals("Reaumur")) {
            if (to.equals("Celsius")) return value * 1.25f;
            if (to.equals("Fahrenheit")) return value * 2.25f + 32;
            if (to.equals("Kelvin")) return value * 1.25f + 273.15f;
            if (to.equals("Rankine")) return value * 2.25f + 32 + 459.67f;
        }
        return 0;
    }

    private void animateResultField() {
        timer = new Timer(50, new ActionListener() {
            int count = 0;
            boolean isOriginal = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count < 10) {
                    t2.setBackground(isOriginal ? Color.YELLOW : originalColor);
                    isOriginal = !isOriginal;
                    count++;
                } else {
                    timer.stop();
                    t2.setBackground(originalColor);
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        new TC("Temperature Converter");
    }
}
