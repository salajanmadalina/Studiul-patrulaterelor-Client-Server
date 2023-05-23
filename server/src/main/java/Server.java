import Service.IntrebareService;
import Service.TestService;
import Service.UserService;
import Model.Intrebare;
import Model.Test;
import Model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                writer.println(isAuthenticated);
                writer.println(id);
                writer.println();

            } else if (request.trim().equals("get_all_users")) {
                List<User> userList = getAllUsers();
                String response = userListToString(userList);
                writer.println(response);

            }  else if (request.trim().equals("get_all_tests")) {
                List<Test> testList = getAllTests();
                String response = testListToString(testList);
                writer.println(response);

              } else if(request.trim().equals("get_all_questions")){
                List<Intrebare> questions = getAllQuestions();
                String response = questionsListToString(questions);
                writer.println(response);

            } else if(request.trim().equals("get_all_responses")){
                List<String> responses = getAllResponses();
                String response = responsesListToString(responses);
                writer.println(response);

            } else if (request.trim().equals("add_user")) {
                // Cererea de adăugare a unui utilizator
                String username = reader.readLine();
                String password = reader.readLine();
                String role = reader.readLine();
                int id = getAllUsers().size() + 2;

                User newUser = new User.UserBuilder(username, password, role, id).build();
                addUser(newUser);
                writer.println("User added successfully.");

            } else if (request.trim().equals("add_test")) {
                // Cererea de adăugare a unui test
                String id = reader.readLine();
                String punctaj = reader.readLine();
                String intrebari = reader.readLine();
                String idUser = reader.readLine();

                Test newTest = new Test.TestBuilder(Integer.parseInt(id), Integer.parseInt(punctaj), intrebari, Integer.parseInt(idUser)).build();
                addTest(newTest);
                writer.println("Test added successfully.");

            }else if (request.trim().equals("delete_user")) {
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

            } else if (request.trim().equals("update_test")) {
                // Cererea de actualizare a unui test
                String id = reader.readLine();
                String punctaj = reader.readLine();

                updateTest(Integer.parseInt(id), Integer.parseInt(punctaj));
                writer.println("Test updated successfully.");

            } else if(request.trim().equals("graphics1")){
                ArrayList<Integer> statistics = new ArrayList<Integer>(Collections.nCopies(11, 0));

                TestService testService = new TestService();
                ArrayList<Test> tests = (ArrayList<Test>)testService.findAll();
                for(Test test: tests){
                    int punctaj = test.getPunctaj();
                    int value = statistics.get(punctaj);
                    statistics.set(punctaj, value + 1);
                }

                String response = "";
                for (Integer statistic : statistics) {
                    response += (100 * statistic) / tests.size() + ",";
                }

                writer.println(response);
            }
            else if(request.trim().equals("graphics2")){
                ArrayList<Integer> statistics = new ArrayList<Integer>(Collections.nCopies(11, 0));

                TestService testService = new TestService();
                ArrayList<Test> tests = (ArrayList<Test>) testService.findAll();
                String response = "";

                for(Test test: tests){
                    int punctaj = test.getPunctaj();
                    int value = statistics.get(punctaj);
                    statistics.set(punctaj, value + 1);
                }

                for (Integer statistic : statistics) {
                    response += statistic + ",";
                }

                writer.println(response);
            } else if (request.trim().equals("get_filtered_users")) {
                String condition = reader.readLine();

                List<User> userList = getFilteredUsers(Integer.parseInt(condition));
                String response = userListToString(userList);
                writer.println(response);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String authentificateUser(String username, String password) {
        UserService userService = new UserService();
        ArrayList<User> users = (ArrayList<User>) userService.findAll();

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
        UserService userService = new UserService();
        ArrayList<User> users = (ArrayList<User>) userService.findAll();

        User user = getRegisteredUser(users, username, password);

        return String.valueOf(user.getId());
    }

    private static List<User> getAllUsers() {
        // Obțineți lista de utilizatori din baza de date sau din sursa de date corespunzătoare
        UserService userService = new UserService();
        List<User> userList = userService.findAll();

        return userList;
    }

    private static List<User> getFilteredUsers(int filtru) {
        // Obțineți lista de utilizatori din baza de date sau din sursa de date corespunzătoare
        UserService userService = new UserService();
        String targetRol;

        if(filtru == 0){
            targetRol = "ELEV";
        }
        else{
            targetRol = "ADMIN";
        }

        List<User> filteredList = userService.findAll().stream()
                .filter(user -> user.getRol().equals(targetRol))
                .collect(Collectors.toList());

        return filteredList;
    }

    private static List<Test> getAllTests() {
        // Obțineți lista de teste din baza de date sau din sursa de date corespunzătoare
        TestService testService = new TestService();
        List<Test> testList = testService.findAll();

        return testList;
    }

    private static List<Intrebare> getAllQuestions() {
        // Obțineți lista de intrebari din baza de date sau din sursa de date corespunzătoare
        IntrebareService intrebareService = new IntrebareService();
        List<Intrebare> questionList = intrebareService.findAll();

        return questionList;
    }

    private static List<String> getAllResponses() {
        // Obțineți lista de intrebari din baza de date sau din sursa de date corespunzătoare
        IntrebareService intrebareService = new IntrebareService();
        List<Intrebare> questionList = intrebareService.findAll();
        List<String> responses = new ArrayList<>();

        for(Intrebare intrebare: questionList){
            responses.add(intrebare.getRaspuns() + "\n");
        }

        return responses;
    }

    private static String userListToString(List<User> userList) {
        StringBuilder sb = new StringBuilder();
        for (User user : userList) {
            sb.append(user.toString());
        }
        return sb.toString();
    }

    private static String testListToString(List<Test> testList) {
        StringBuilder sb = new StringBuilder();
        for (Test test: testList) {
            sb.append(test.toString());
        }
        return sb.toString();
    }

    private static String questionsListToString(List<Intrebare> questionList){
        StringBuilder sb = new StringBuilder();
        for (Intrebare intrebare: questionList) {
            sb.append(intrebare.toString());
        }
        return sb.toString();
    }

    private static String responsesListToString(List<String> responses){
        StringBuilder sb = new StringBuilder();
        for (String str: responses) {
            sb.append(str);
        }
        return sb.toString();
    }

    private static void addUser(User user) {
        // Adăugați utilizatorul în baza de date sau în sursa de date corespunzătoare
        UserService userService = new UserService();
        userService.insert(user);
    }

    private static void addTest(Test test) {
        // Adăugați testul în baza de date sau în sursa de date corespunzătoare
        TestService testService = new TestService();
        testService.insert(test);
    }

    private static void deleteUser(int id) {
        UserService userService = new UserService();
        User user =  userService.findById(id);

        if(user != null){
            userService.delete(id);
        }
    }

    private static void updateUser(int id, String username, String password, String role) {
        UserService userService = new UserService();

        if(!username.isEmpty()){
            userService.update("nume", username, id);
        }
        if(!password.isEmpty()){
            userService.update("password", password, id);
        }
        if(!role.isEmpty()){
            userService.update("rol", role, id);
        }
    }

    private static void updateTest(int id, int punctaj){
        TestService testService = new TestService();

        testService.update("punctaj", String.valueOf(punctaj), id);
    }

}
