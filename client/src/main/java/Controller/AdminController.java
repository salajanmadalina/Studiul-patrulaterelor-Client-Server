package Controller;

import Commands.*;
import View.AdminView;

public class AdminController implements Observer {

    private AdminView adminView;
    private Language language;
    private Command deleteUserCommand;
    private Command updateUserCommand;
    private Command addUserCommand;
    private Command allUsersCommand;

    public AdminController(Language language, int index) {
        this.adminView = new AdminView();
        this.language = language;
        this.language.attachObserver(this);
        this.language.setCurrentLanguage(index);
        addActionListeners();
    }

    private void addActionListeners(){
        adminView.getBtnCreeaza().addActionListener(actionListener -> {
            String username = String.valueOf(adminView.getNameField().getText());
            String password = String.valueOf(adminView.getPasswordField().getPassword());
            String role = String.valueOf(adminView.getRolField().getText());

            addUserCommand= new AddUserCommand(username,password,role);
            addUserCommand.execute();

            if(addUserCommand instanceof AddUserCommand){
                String response = ((AddUserCommand) addUserCommand).getResponse();
                adminView.showMessage(response);
            }
        });

        adminView.getBtnVizualizeazaListaUseri().addActionListener(actionListener -> {
            allUsersCommand = new AllUsersCommand();
            allUsersCommand.execute();

            String info = ((AllUsersCommand)allUsersCommand).getAllUsers();

            adminView.setTextArea(info);
        });

        adminView.getBtnBack().addActionListener(action -> {
            System.out.println(language.getLanguage());
            new LogInController(language.getLanguage());
            AdminView.getFrame().dispose();
        });

        adminView.getBtnDelete().addActionListener(action -> {
            int id = Integer.parseInt(adminView.getIdField().getText());
            deleteUserCommand = new DeleteUserCommand(id);
            deleteUserCommand.execute();
            if(deleteUserCommand instanceof DeleteUserCommand){
                String response = ((DeleteUserCommand) deleteUserCommand).getResponse();
                adminView.showMessage(response);
            }
        });

        adminView.getBtnUpdate().addActionListener(action -> {
            String username = String.valueOf(adminView.getNameField().getText());
            String password = String.valueOf(adminView.getPasswordField().getPassword());
            String role = String.valueOf(adminView.getRolField().getText());
            int id = Integer.parseInt(adminView.getIdField().getText());

            updateUserCommand = new UpdateUserCommand(id,username,password,role);
            updateUserCommand.execute();
            if (updateUserCommand instanceof UpdateUserCommand) {
                String response = ((UpdateUserCommand)updateUserCommand).getResponse();
                adminView.showMessage(response);
            }

        });

        adminView.getLanguageComboBox().addActionListener(actionListener -> {
            language.setCurrentLanguage(adminView.getLanguageComboBox().getSelectedIndex());
        });
    }

    @Override
    public void update() {
        adminView.getBtnUpdate().setText(language.getRb().getString("update"));
        adminView.getBtnDelete().setText(language.getRb().getString("delete"));
        adminView.getBtnCreeaza().setText(language.getRb().getString("insert"));
        adminView.getBtnVizualizeazaListaUseri().setText(language.getRb().getString("showUsers"));
        adminView.getBtnBack().setText(language.getRb().getString("back"));
        adminView.getLblName().setText(language.getRb().getString("userName"));
        adminView.getLblPassword().setText(language.getRb().getString("password"));
        adminView.getLblRol().setText(language.getRb().getString("role"));
        adminView.getLblUsersList().setText(language.getRb().getString("userList"));
        adminView.getLblLimba().setText(language.getRb().getString("language"));
    }
}
