import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    private double balance = 1000.0;
    private JTextField balanceField;

    public App() {
        setTitle("ATM Simulation");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel accountLabel = new JLabel("Account Number:");
        JTextField accountField = new JTextField();

        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();

        JLabel balanceLabel = new JLabel("Balance:");
        balanceField = new JTextField(String.valueOf(balance));
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
                // You can add account and PIN validation here
                // For simplicity, we skip that part in this example
                balanceField.setText(String.valueOf(balance));
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can add deposit functionality here
                // For simplicity, we skip that part in this example
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // You can add withdrawal functionality here
                // For simplicity, we skip that part in this example
            }
        });

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
