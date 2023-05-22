import Model.Dao.UserDAO;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress());

                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket){
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String request = reader.readLine();
            if (request.trim().equals("login")) {

                String username = reader.readLine();
                String password = reader.readLine();

                // Verificarea autentificării utilizatorului
                String isAuthenticated = authentificateUser(username, password);
                String id = getUserId(username, password);
                System.out.println(isAuthenticated);
                writer.println(isAuthenticated);
                writer.println(id);
                writer.println();

            } else if (request.trim().equals("get_all_users")) {
                List<User> userList = getAllUsers();
                String response = userListToString(userList);
                writer.println(response);

            } else if (request.trim().equals("add_user")) {
                // Cererea de adăugare a unui utilizator
                String username = reader.readLine();
                String password = reader.readLine();
                String role = reader.readLine();
                int id = getAllUsers().size() + 2;

                User newUser = new User(username, password, role, id);
                addUser(newUser);
                writer.println("User added successfully.");

            } else if (request.trim().equals("delete_user")) {
                // Cererea de ștergere a unui utilizator
                int userId = Integer.parseInt(reader.readLine());
                deleteUser(userId);
                writer.println("User deleted successfully.");

            } else if (request.trim().equals("update_user")) {
                // Cererea de actualizare a unui utilizator
                String id = reader.readLine();
                String newUsername = reader.readLine();
                String newPassword = reader.readLine();
                String newRole = reader.readLine();

                updateUser(Integer.parseInt(id), newUsername, newPassword, newRole);
                writer.println("User updated successfully.");
//            } else if (request.trim().equals("get_all_filtered_products")) {
//                String condition = reader.readLine();
//                String criteria = reader.readLine();
//
//                List<Product> productsList = getAllFilteredProducts(criteria, condition);
//                String response = productListToString(productsList);
//                writer.println(response);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String authentificateUser(String username, String password) {
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = (ArrayList<User>)userDAO.findAll();

        User user = getRegisteredUser(users, username, password);

        if (user != null) {
            if (user.getRol().equals("ADMIN")) {
                return "ADMIN";
            } else if (user.getRol().equals("ELEV")) {
                return "ELEV";
            }
        } else return "NU EXISTA";

        return null;
    }

    private static User getRegisteredUser(ArrayList<User> users, String userName, String password){
        for(User user: users){
            if(user.getNume().equals(userName) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    private static String getUserId(String username, String password){
        UserDAO userDAO = new UserDAO();
        ArrayList<User> users = (ArrayList<User>)userDAO.findAll();

        User user = getRegisteredUser(users, username, password);

        return String.valueOf(user.getId());
    }

    private static List<User> getAllUsers() {
        // Obțineți lista de utilizatori din baza de date sau din sursa de date corespunzătoare
        UserDAO userDAO = new UserDAO();
        List<User> userList = userDAO.findAll();

        return userList;
    }

    private static String userListToString(List<User> userList) {
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user.toString());
        }
        return sb.toString();
    }

    private static void addUser(User user) {
        // Adăugați utilizatorul în baza de date sau în sursa de date corespunzătoare
        UserDAO userDAO = new UserDAO();
        userDAO.insert(user);
    }

    private static void deleteUser(int id) {
        UserDAO userDAO = new UserDAO();
        User user =  userDAO.findById(id);

        if(user != null){
            userDAO.delete(id);
        }
    }

    private static void updateUser(int id, String username, String password, String role) {
        UserDAO userDAO = new UserDAO();

        if(!username.isEmpty()){
            userDAO.update("nume", username, id);
        }
        if(!password.isEmpty()){
            userDAO.update("password", password, id);
        }
        if(!role.isEmpty()){
            userDAO.update("rol", role, id);
        }
    }



}
