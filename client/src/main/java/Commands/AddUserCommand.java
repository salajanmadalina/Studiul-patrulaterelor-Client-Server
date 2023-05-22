package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AddUserCommand implements Command{
    private String username;
    private String password;
    private String role;
    private String response;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public AddUserCommand(String username,String password,String role){
        this.username = username;
        this.password=password;
        this.role=role;
    }
    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Trimiterea cererii de adăugare a utilizatorului la server
            String request = "add_user";
            writer.println(request);
            writer.println(username);
            writer.println(password);
            writer.println(role);

            // Așteptarea răspunsului de la server
            String response = reader.readLine();
            this.response = response;
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }
}
