public class LibraryController {
    private LibraryModel model;
    private LibraryView view;

    public LibraryController(LibraryModel model, LibraryView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        while (true) {
            view.showBooks(model.getBooks());
            int bookId = view.getBookIdFromUser();

            boolean success = model.borrowBook(bookId);
            view.showBorrowResult(success);

            String answer = view.getYesNoAnswer();
            if (!answer.equals("yes")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}
