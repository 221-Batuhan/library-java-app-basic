import java.util.*;

public class LibraryConsoleApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        initBooks();

        System.out.println("Welcome to the Library Console App!");

        while (true) {
            System.out.println("\nCommands:");
            System.out.println("1 - List all books");
            System.out.println("2 - Search books");
            System.out.println("3 - Borrow a book");
            System.out.println("4 - Return a book");
            System.out.println("5 - Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    listBooks(books);
                    break;
                case "2":
                    searchBooks();
                    break;
                case "3":
                    borrowBook();
                    break;
                case "4":
                    returnBook();
                    break;
                case "5":
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void initBooks() {
        books.add(new Book(1, "The Great Gatsby"));
        books.add(new Book(2, "To Kill a Mockingbird"));
        books.add(new Book(3, "1984"));
        books.add(new Book(4, "Pride and Prejudice"));
        books.add(new Book(5, "The Catcher in the Rye"));
    }

    private static void listBooks(List<Book> list) {
        System.out.println("\nID | Title | Status");
        System.out.println("---------------------");
        for (Book b : list) {
            System.out.println(b);
        }
    }

    private static void searchBooks() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine().toLowerCase();

        List<Book> filtered = new ArrayList<>();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(keyword)) {
                filtered.add(b);
            }
        }

        if (filtered.isEmpty()) {
            System.out.println("No books found with keyword: " + keyword);
        } else {
            listBooks(filtered);
        }
    }

    private static void borrowBook() {
        System.out.print("Enter the ID of the book to borrow: ");
        int id = readIntInput();
        Book b = findBookById(id);
        if (b == null) {
            System.out.println("Book ID not found.");
            return;
        }
        if (b.isBorrowed()) {
            System.out.println("Book is already borrowed.");
        } else {
            b.setBorrowed(true);
            System.out.println("You borrowed: " + b.getTitle());
        }
    }

    private static void returnBook() {
        System.out.print("Enter the ID of the book to return: ");
        int id = readIntInput();
        Book b = findBookById(id);
        if (b == null) {
            System.out.println("Book ID not found.");
            return;
        }
        if (!b.isBorrowed()) {
            System.out.println("This book was not borrowed.");
        } else {
            b.setBorrowed(false);
            System.out.println("You returned: " + b.getTitle());
        }
    }

    private static int readIntInput() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static Book findBookById(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    static class Book {
        private int id;
        private String title;
        private boolean borrowed;

        public Book(int id, String title) {
            this.id = id;
            this.title = title;
            this.borrowed = false;
        }

        public int getId() { return id; }
        public String getTitle() { return title; }
        public boolean isBorrowed() { return borrowed; }
        public void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }

        @Override
        public String toString() {
            return id + " | " + title + " | " + (borrowed ? "Borrowed" : "Available");
        }
    }
}
