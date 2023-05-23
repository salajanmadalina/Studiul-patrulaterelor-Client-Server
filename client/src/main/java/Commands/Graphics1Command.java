package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Graphics1Command implements Command{
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;
    private String response;
    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Trimite cererea pentru obținerea tuturor utilizatorilor
            writer.println("graphics1");

            // Așteaptă răspunsul de la server
            response = reader.readLine();

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAllStatistics(){
        return response;
    }
}
