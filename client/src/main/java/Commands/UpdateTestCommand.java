package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UpdateTestCommand implements Command {
    private int id;
    private int punctaj;
    private String response;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public UpdateTestCommand(int punctaj, int id){
        this.punctaj = punctaj;
        this.id = id;
    }

    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String request = "update_test";
            writer.println(request);
            writer.println(id);
            writer.println(punctaj);

            String response = reader.readLine();
            this.response = response;
            socket.close();

        } catch (
        IOException e) {
            e.printStackTrace();

        }
    }
}
