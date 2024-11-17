package com.example.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AplikasiCekGenapGanjil extends JFrame {
    private JTextField inputField;
    private JButton checkButton;
    private JLabel resultLabel;

    public AplikasiCekGenapGanjil() {
        setTitle("Cek Genap atau Ganjil");
        setSize(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Masukkan Angka:", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        inputField = new JTextField();
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 16));
        centerPanel.add(inputField);

        checkButton = new JButton("Periksa");
        checkButton.setFont(new Font("SansSerif", Font.PLAIN, 16));
        centerPanel.add(checkButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        resultLabel = new JLabel("Hasil akan ditampilkan di sini.", JLabel.CENTER);
        resultLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        resultLabel.setForeground(Color.BLUE);
        mainPanel.add(resultLabel, BorderLayout.SOUTH);

        add(mainPanel);

        checkButton.addActionListener(e -> checkNumber());
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                    JOptionPane.showMessageDialog(null, "Masukkan hanya angka!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                inputField.setText("");
                resultLabel.setText("");
            }
        });
    }

    private void checkNumber() {
        try {
            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Input tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int number = Integer.parseInt(input);
            String type = (number % 2 == 0) ? "Genap" : "Ganjil";
            boolean isPrime = checkPrime(number);
            String result = number + " adalah bilangan " + type + (isPrime ? " dan bilangan prima." : ".");
            resultLabel.setText(result);
            JOptionPane.showMessageDialog(this, result, "Hasil", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiCekGenapGanjil app = new AplikasiCekGenapGanjil();
            app.setVisible(true);
        });
    }
}
