import Controller.LogInController;

import javax.swing.*;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LogInController controller = new LogInController(0);
            }
        });
    }
}
