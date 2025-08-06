import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField nameField;
    private JButton loginButton;

    public LoginPanel(ActionListener loginListener) {
        setLayout(new BorderLayout(10, 10));

        JLabel label = new JLabel("Enter your name:");
        nameField = new JTextField(20);
        loginButton = new JButton("Login");

        // Giriş butonuna basıldığında dışarıya haber ver
        loginButton.addActionListener(e -> {
            ActionEvent event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, getUserName());
            loginListener.actionPerformed(event);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(label);
        inputPanel.add(nameField);
        inputPanel.add(loginButton);

        add(inputPanel, BorderLayout.CENTER);
    }

    public String getUserName() {
        return nameField.getText().trim();
    }
}
