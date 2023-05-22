package View;

import javax.swing.*;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Locale;
import java.util.ResourceBundle;

@Setter
@Getter
public class LogInView extends JFrame{

    private static JFrame frame;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton guestMode;
    private JButton logIn;
    private JButton btnRegister;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblStudiulPatrulaterelor;
    private JComboBox<String> languageComboBox;
    private String[] languages = {"ENGLISH", "ITALIAN", "FRENCH"};

    public LogInView(){
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 934, 503);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        guestMode = new JButton("Mod invitat");
        guestMode.setFont(new Font("Times New Roman", Font.BOLD, 20));
        guestMode.setBounds(138, 209, 180, 50);
        frame.getContentPane().add(guestMode);

        logIn = new JButton("Conectare");
        logIn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        logIn.setBounds(442, 386, 146, 50);
        frame.getContentPane().add(logIn);

        nameField = new JTextField();
        nameField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        nameField.setBounds(570, 176, 164, 50);
        frame.getContentPane().add(nameField);
        nameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        passwordField.setBounds(570, 243, 164, 50);
        frame.getContentPane().add(passwordField);

        lblUsername = new JLabel("Nume:");
        lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblUsername.setBounds(414, 176, 150, 50);
        frame.getContentPane().add(lblUsername);

        lblPassword = new JLabel("Parola:");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lblPassword.setBounds(414, 241, 150, 50);
        frame.getContentPane().add(lblPassword);

        lblStudiulPatrulaterelor = new JLabel("STUDIUL PATRULATERELOR");
        lblStudiulPatrulaterelor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 26));
        lblStudiulPatrulaterelor.setBounds(281, 31, 396, 80);
        frame.getContentPane().add(lblStudiulPatrulaterelor);

        btnRegister = new JButton("Inregistreaza-te");
        btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnRegister.setBounds(628, 386, 171, 50);
        frame.getContentPane().add(btnRegister);

        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setBounds(138, 300, 80, 30);
        frame.getContentPane().add(languageComboBox);

        frame.setVisible(true);

    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        LogInView.frame = frame;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

}
