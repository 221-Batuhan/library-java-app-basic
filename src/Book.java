public class Book {
    private int id;
    private String title;
    private boolean isBorrowed;

    public Book(int id, String title) {
        this.id = id;
        this.title = title;
        this.isBorrowed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }
    public void returnBook() {
    this.isBorrowed = false;
    }

    public void borrow() {
        isBorrowed = true;
    }

    @Override
    public String toString() {
        return id + ". " + title + (isBorrowed ? " [Borrowed]" : " [Available]");
    }
}
