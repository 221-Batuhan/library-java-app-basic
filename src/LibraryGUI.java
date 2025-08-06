import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LibraryModel model;

    public LibraryGUI() {
        model = new LibraryModel();
        setTitle("Library System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ortala

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 1. Login panelini oluştur
        LoginPanel loginPanel = new LoginPanel(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = e.getActionCommand();
                showLibraryPanel(userName);
            }
        });

        mainPanel.add(loginPanel, "login");

        add(mainPanel);
        setVisible(true);
    }

    private void showLibraryPanel(String userName) {
        LibraryPanel libraryPanel = new LibraryPanel(model, userName);
        mainPanel.add(libraryPanel, "library");
        cardLayout.show(mainPanel, "library");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryGUI());
    }
}
