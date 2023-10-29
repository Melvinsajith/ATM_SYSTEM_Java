import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class we_atm extends JFrame {
    private Map<String, UserData> userDatabase = new HashMap<>();
    private JTextField balanceField;

    public we_atm() {
        setTitle("ATM Simulation");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add user accounts with account numbers, PINs, and initial balances
        userDatabase.put("123456", new UserData("John Doe", "1234", 1000.0));
        userDatabase.put("987654", new UserData("Alice Smith", "5678", 1500.0));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel accountLabel = new JLabel("Account Number:");
        JTextField accountField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();
        JLabel balanceLabel = new JLabel("Balance:");
        balanceField = new JTextField();
        balanceField.setEditable(false);

        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        panel.add(accountLabel);
        panel.add(accountField);
        panel.add(pinLabel);
        panel.add(pinField);
        panel.add(balanceLabel);
        panel.add(balanceField);
        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = new String(pinField.getPassword());

                UserData userData = userDatabase.get(accountNumber);
                if (userData != null && userData.validatePin(pin)) {
                    balanceField.setText(String.valueOf(userData.getBalance()));
                } else {
                    balanceField.setText("Invalid account number or PIN");
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = new String(pinField.getPassword());

                UserData userData = userDatabase.get(accountNumber);
                if (userData != null && userData.validatePin(pin)) {
                    try {
                        double depositAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter the deposit amount:"));
                        userData.deposit(depositAmount);
                        balanceField.setText(String.valueOf(userData.getBalance()));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid deposit amount. Please enter a valid number.");
                    }
                } else {
                    balanceField.setText("Invalid account number or PIN");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountNumber = accountField.getText();
                String pin = new String(pinField.getPassword());

                UserData userData = userDatabase.get(accountNumber);
                if (userData != null && userData.validatePin(pin)) {
                    try {
                        double withdrawalAmount = Double.parseDouble(JOptionPane.showInputDialog("Enter the withdrawal amount:"));
                        if (userData.withdraw(withdrawalAmount)) {
                            balanceField.setText(String.valueOf(userData.getBalance()));
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient funds.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid withdrawal amount. Please enter a valid number.");
                    }
                } else {
                    balanceField.setText("Invalid account number or PIN");
                }
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new we_atm());
    }

    // Define a class to represent user data
    private class UserData {
        private String name;
        private String pin;
        private double balance;

        public UserData(String name, String pin, double balance) {
            this.name = name;
            this.pin = pin;
            this.balance = balance;
        }

        public boolean validatePin(String enteredPin) {
            return pin.equals(enteredPin);
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (balance >= amount) {
                balance -= amount;
                return true;
            }
            return false;
        }
    }
}

