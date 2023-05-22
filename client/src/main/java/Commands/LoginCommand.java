package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginCommand implements Command{
    private String username;
    private String parola;
    private String isAuthenticated;
    private String id;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public LoginCommand(String username,String parola){
        this.username = username;
        this.parola = parola;
    }

    public String getIsAuthenticated(){
        return isAuthenticated;
    }

    public String getId(){
        return  id;
    }

    @Override
    public void execute() {

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Trimiterea username-ului și parolei la server
            String request = "login ";
            writer.println(request);
            writer.println(username);
            writer.println(parola);


            // Așteptarea răspunsului de la server
            String isAuthenticated = reader.readLine();
            String id = reader.readLine();
            System.out.println(id);
            this.id=id;
            System.out.println(isAuthenticated);
            this.isAuthenticated=isAuthenticated;

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
