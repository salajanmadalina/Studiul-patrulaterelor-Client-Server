package Controller;

import Commands.AddUserCommand;
import Commands.Command;
import Commands.LoginCommand;
import View.LogInView;

public class LogInController implements Observer{
    private LogInView logInView;
    private Language language;
    private Command loginCommand;
    private Command addUserCommand;

    public LogInController(int index) {
        this.logInView = new LogInView();
        this.language = Language.getInstance();
        language.attachObserver(this);
        language.setCurrentLanguage(index);
        addActionListeners();
    }

    private void addActionListeners(){
        logInView.getLogIn().addActionListener(actionListener -> {
            String username = String.valueOf(logInView.getNameField().getText());
            String password = String.valueOf(logInView.getPasswordField().getPassword());

            loginCommand = new LoginCommand(username, password);
            loginCommand.execute();

            if(loginCommand instanceof LoginCommand){
                String isAuthentificated = ((LoginCommand) loginCommand).getIsAuthenticated();

                if(isAuthentificated.equals("ADMIN")){
                    new AdminController(language, language.getLanguage());
                    logInView.getFrame().dispose();
                }
                else if(isAuthentificated.equals("ELEV")){
                    new UserController(language, language.getLanguage());
                    logInView.getFrame().dispose();
                }
            }

        });

        logInView.getGuestMode().addActionListener(actionListener -> {
            new GuestController(language, logInView.getLanguageComboBox().getSelectedIndex());
            logInView.getFrame().dispose();
        });

        logInView.getBtnRegister().addActionListener(actionListener -> {
            String username = String.valueOf(logInView.getNameField().getText());
            String password = String.valueOf(logInView.getPasswordField().getPassword());
            String role = "ELEV";

            addUserCommand= new AddUserCommand(username,password,role);
            addUserCommand.execute();

            if(addUserCommand instanceof AddUserCommand){
                String response = ((AddUserCommand) addUserCommand).getResponse();
                logInView.showMessage(response);
            }
        });

        logInView.getLanguageComboBox().addActionListener(actionListener -> {
            language.setCurrentLanguage(logInView.getLanguageComboBox().getSelectedIndex());
        });
    }

    @Override
    public void update() {
        logInView.getLblUsername().setText(language.getRb().getString("userName"));
        logInView.getLblPassword().setText(language.getRb().getString("password"));
        logInView.getGuestMode().setText(language.getRb().getString("guestMode"));
        logInView.getLogIn().setText(language.getRb().getString("logIn"));
        logInView.getBtnRegister().setText(language.getRb().getString("register"));
        logInView.getLblStudiulPatrulaterelor().setText(language.getRb().getString("titleLogIn"));

    }

}
