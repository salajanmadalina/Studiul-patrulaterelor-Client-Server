package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class FilterUsersCommand implements Command{
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;
    private List<String> response;
    private int filtru;

    public FilterUsersCommand(int filtru){
        this.filtru = filtru;
    }

    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Trimite cererea pentru obținerea tuturor utilizatorilor
            writer.println("get_filtered_users");
            writer.println(filtru);

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

    public String getAllUsers(){
        String response1 = "";
        for(int i  =0; i < response.size(); i ++){
            response1 += response.get(i) + "\n";
        }
        return response1;
    }
}
