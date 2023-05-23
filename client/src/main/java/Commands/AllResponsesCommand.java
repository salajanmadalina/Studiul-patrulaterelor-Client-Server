package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class AllResponsesCommand implements Command{
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;
    private List<String> response;

    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Trimite cererea pentru obținerea tuturor utilizatorilor
            writer.println("get_all_responses");

            // Așteaptă răspunsul de la server
            response = new ArrayList<>();
            String line;
            while((line = reader.readLine()) != null){
                response.add(line);
            }

            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllResponses(){
        return response;
    }
}
