import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private Scanner scanner = new Scanner(System.in);

    public void showBooks(List<Book> books) {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public int getBookIdFromUser() {
        System.out.print("Enter the ID of the book to borrow: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        int id = scanner.nextInt();
        scanner.nextLine(); // Satır sonunu temizle
        return id;
    }

    public void showBorrowResult(boolean success) {
        if (success) {
            System.out.println("Book borrowed successfully!");
        } else {
            System.out.println("Sorry, this book is not available.");
        }
    }

    public String getYesNoAnswer() {
        System.out.print("Do you want to borrow another book? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        while (!answer.equals("yes") && !answer.equals("no")) {
            System.out.print("Please answer with 'yes' or 'no': ");
            answer = scanner.nextLine().trim().toLowerCase();
        }
        return answer;
    }
}
