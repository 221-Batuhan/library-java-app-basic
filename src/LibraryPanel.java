import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryPanel extends JPanel {
    private LibraryModel model;
    private DefaultListModel<String> listModel;
    private JList<String> bookList;
    private JLabel statusLabel;
    private JTextField searchField;

    public LibraryPanel(LibraryModel model, String userName) {
        this.model = model;

        setLayout(new BorderLayout(10, 10));

        // Üst panel: Hoş geldin + Arama
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.LEFT);

        searchField = new JTextField();
        JButton searchButton = new JButton("Search");
        JButton resetButton = new JButton("Reset");

        JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
        searchPanel.add(searchField, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        buttons.add(searchButton);
        buttons.add(resetButton);
        searchPanel.add(buttons, BorderLayout.EAST);

        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Kitap listesi
        listModel = new DefaultListModel<>();
        updateBookList(model.getBooks());

        bookList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(bookList);

        // Borrow ve Return butonları + durum etiketi
        JButton borrowButton = new JButton("Borrow Selected Book");
        JButton returnButton = new JButton("Return Selected Book");
        statusLabel = new JLabel(" ");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

       borrowButton.addActionListener(e -> {
    int index = bookList.getSelectedIndex();
    if (index != -1) {
        String selectedTitle = bookList.getSelectedValue();
        int bookId = Integer.parseInt(selectedTitle.split("\\.")[0].trim());
        Book selectedBook = model.getBooks().stream()
                .filter(b -> b.getId() == bookId)
                .findFirst().orElse(null);
        if (selectedBook != null) {
            boolean success = model.borrowBook(bookId);
            if (success) {
                updateBookList(model.getBooks());
                statusLabel.setText("Book borrowed successfully.");
                JOptionPane.showMessageDialog(this, "You have successfully borrowed the book.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                statusLabel.setText("This book is already borrowed.");
                JOptionPane.showMessageDialog(this, "This book is already borrowed.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    } else {
        statusLabel.setText("Please select a book.");
        JOptionPane.showMessageDialog(this, "Please select a book to borrow.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
});

returnButton.addActionListener(e -> {
    int index = bookList.getSelectedIndex();
    if (index != -1) {
        String selectedTitle = bookList.getSelectedValue();
        int bookId = Integer.parseInt(selectedTitle.split("\\.")[0].trim());
        Book selectedBook = model.getBooks().stream()
                .filter(b -> b.getId() == bookId)
                .findFirst().orElse(null);
        if (selectedBook != null) {
            boolean success = model.returnBook(bookId);
            if (success) {
                updateBookList(model.getBooks());
                statusLabel.setText("Book returned successfully.");
                JOptionPane.showMessageDialog(this, "You have successfully returned the book.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                statusLabel.setText("This book was not borrowed.");
                JOptionPane.showMessageDialog(this, "This book was not borrowed.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    } else {
        statusLabel.setText("Please select a book.");
        JOptionPane.showMessageDialog(this, "Please select a book to return.", "Warning", JOptionPane.WARNING_MESSAGE);
    }
});


        // Arama butonu işlevi
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim().toLowerCase();
            if (!keyword.isEmpty()) {
                List<Book> filtered = model.getBooks().stream()
                        .filter(book -> book.getTitle().toLowerCase().contains(keyword))
                        .collect(Collectors.toList());
                updateBookList(filtered);
                statusLabel.setText(filtered.isEmpty() ? "No results found." : "Filtered results:");
            }
        });

        // Reset butonu
        resetButton.addActionListener(e -> {
            searchField.setText("");
            updateBookList(model.getBooks());
            statusLabel.setText("List reset.");
        });

        // Alt panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(); // Borrow ve Return yanyana
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);

        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateBookList(List<Book> books) {
        listModel.clear();
        for (Book book : books) {
            listModel.addElement(book.toString());
        }
    }
}
