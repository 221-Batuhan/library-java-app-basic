import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
    private List<Book> books;

    public LibraryModel() {
    books = new ArrayList<>();
    books.add(new Book(1, "The Great Gatsby"));
    books.add(new Book(2, "To Kill a Mockingbird"));
    books.add(new Book(3, "1984"));
    books.add(new Book(4, "Pride and Prejudice"));
    books.add(new Book(5, "The Catcher in the Rye"));
}


    public List<Book> getBooks() {
        return books;
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public boolean borrowBook(int id) {
        Book book = findBookById(id);
        if (book != null && !book.isBorrowed()) {
            book.borrow();
            return true;
        }
        return false;
    }
    public boolean returnBook(int id) {
    for (Book book : books) {
        if (book.getId() == id && book.isBorrowed()) {
            book.returnBook();
            return true;
        }
    }
    return false;
}

}
