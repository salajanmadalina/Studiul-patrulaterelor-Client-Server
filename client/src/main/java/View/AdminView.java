package View;

import javax.swing.*;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class AdminView extends JFrame {
    private static JFrame frame;
    private JTextArea textArea;
    private JTextField idField;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JTextField rolField;
    private JButton btnBack;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnCreeaza;
    private JButton btnVizualizeazaListaUseri;
    private JLabel lblUsersList;
    private JLabel lblId;
    private JLabel lblName;
    private JLabel lblPassword;
    private JLabel lblRol;
    private JComboBox<String> languageComboBox;
    private String[] languages = {"ENGLISH", "ITALIAN", "FRENCH"};
    private JLabel lblLimba;
    private JScrollPane scrollBar;


    public AdminView(){
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 1287, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(39, 65, 609, 345);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

        scrollBar = new JScrollPane(textArea);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(39, 75, 609, 345);
        frame.getContentPane().add(scrollBar);

        lblUsersList = new JLabel("Lista utilizatori");
        lblUsersList.setFont(new Font("Times New Roman", Font.PLAIN, 33));
        lblUsersList.setBounds(39, 23, 350, 27);
        frame.getContentPane().add(lblUsersList);

        btnBack = new JButton("Inapoi");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnBack.setBounds(50, 452, 149, 46);
        frame.getContentPane().add(btnBack);

        btnVizualizeazaListaUseri = new JButton("Vizualizeaza lista useri");
        btnVizualizeazaListaUseri.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnVizualizeazaListaUseri.setBounds(843, 452, 320, 46);
        frame.getContentPane().add(btnVizualizeazaListaUseri);

        idField = new JTextField();
        idField.setBounds(903, 59, 143, 37);
        frame.getContentPane().add(idField);
        idField.setColumns(10);

        lblId = new JLabel("ID:");
        lblId.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblId.setBounds(721, 65, 150, 27);
        frame.getContentPane().add(lblId);

        nameField = new JTextField();
        nameField.setColumns(10);
        nameField.setBounds(903, 117, 143, 37);
        frame.getContentPane().add(nameField);

        lblName = new JLabel("Nume:");
        lblName.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblName.setBounds(721, 118, 180, 27);
        frame.getContentPane().add(lblName);

        btnDelete = new JButton("Sterge");
        btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnDelete.setBounds(903, 245, 149, 46);
        frame.getContentPane().add(btnDelete);

        passwordField = new JPasswordField();
        passwordField.setColumns(10);
        passwordField.setBounds(903, 175, 143, 37);
        frame.getContentPane().add(passwordField);

        lblPassword = new JLabel("Parola:");
        lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblPassword.setBounds(721, 176, 150, 27);
        frame.getContentPane().add(lblPassword);

        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnUpdate.setBounds(903, 310, 149, 46);
        frame.getContentPane().add(btnUpdate);

        btnCreeaza = new JButton("Creeaza");
        btnCreeaza.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnCreeaza.setBounds(903, 378, 149, 46);
        frame.getContentPane().add(btnCreeaza);

        rolField = new JTextField();
        rolField.setColumns(10);
        rolField.setBounds(903, 10, 143, 37);
        frame.getContentPane().add(rolField);

        lblRol = new JLabel("Rol:");
        lblRol.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblRol.setBounds(721, 23, 150, 27);
        frame.getContentPane().add(lblRol);

        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setBounds(1124, 63, 121, 38);
        frame.getContentPane().add(languageComboBox);

        lblLimba = new JLabel("Limba:");
        lblLimba.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblLimba.setBounds(1124, 26, 121, 27);
        frame.getContentPane().add(lblLimba);

        frame.setVisible(true);

    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        AdminView.frame = frame;
    }

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }
}
