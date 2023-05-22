package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class DeleteUserCommand implements Command{
    private int id;
    private String response;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public DeleteUserCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            String request = "delete_user";
            writer.println(request);
            writer.println(id);

            String response = reader.readLine();
            this.response=response;
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }
}
