public class GUIMain {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LibraryGUI(); // ✅ Doğru çağrı: parametresiz
        });
    }
}
