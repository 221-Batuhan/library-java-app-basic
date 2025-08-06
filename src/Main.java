public class Main {
    public static void main(String[] args) {
        LibraryModel model = new LibraryModel();
        LibraryView view = new LibraryView();
        LibraryController controller = new LibraryController(model, view);
        controller.start();
    }
}
