package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UpdateUserCommand implements  Command{
    private String username;
    private int id;
    private String password;
    private String role;
    private String response;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public String getResponse() {
        return response;
    }

    public UpdateUserCommand(int id,String username, String password, String role){
        this.username= username;
        this.password=password;
        this.role=role;
        this.id=id;
    }
    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String request = "update_user";
            writer.println(request);
            writer.println(id);
            writer.println(username);
            writer.println(password);
            writer.println(role);

            String response = reader.readLine();
            System.out.println(response);
            this.response=response;
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
