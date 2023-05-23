package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AddTestCommand implements Command{
    private String intrebari;
    private int id;
    private int punctaj;
    private int idUser;
    private String response;
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1234;

    public AddTestCommand(int id, int punctaj, String intrebari, int idUser){
        this.id = id;
        this.punctaj = punctaj;
        this.intrebari = intrebari;
        this.idUser = idUser;
    }
    @Override
    public void execute() {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Trimiterea cererii de adăugare a utilizatorului la server
            String request = "add_test";
            writer.println(request);
            writer.println(id);
            writer.println(punctaj);
            writer.println(intrebari);
            writer.println(idUser);

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
