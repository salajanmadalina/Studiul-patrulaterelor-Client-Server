package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class GuestView extends JFrame {
    private Color roz = new Color(204, 153, 153);
    private Color albastru = new Color(63, 127, 190);
    private Color verde = new Color(52, 136, 56);
    private static JFrame frame;
    private JTextArea textArea;
    private JButton btnSolicitareCont;
    private JButton btnAfisareDate;
    private JPanel panel;
    private JButton btnBack;
    private JComboBox comboBoxColor;
    private JScrollPane scrollBar;
    private JButton btnSavePatrulater;
    private JButton btnLoadPatrulater;
    private JTextArea myX = new JTextArea();
    private JTextArea myY = new JTextArea();
    private Graphics g;
    private MouseListener m2;
    private JLabel lblDraw;
    private JLabel lblCharacteristics;
    private JLabel lblCont;
    private JLabel lblSelecteazaCuloareaPentru;
    private JComboBox<String> languageComboBox;
    private String[] languages = {"ENGLISH", "ITALIAN", "FRENCH"};
    private JLabel lblSelecteazaLimba;

    public GuestView(){
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 1287, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBounds(518, 65, 481, 319);
        frame.getContentPane().add(panel);

        lblDraw = new JLabel("Deseneaza un patrulater!");
        lblDraw.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblDraw.setBounds(538, 23, 260, 32);
        frame.getContentPane().add(lblDraw);

        textArea = new JTextArea();
        textArea.setBounds(29, 65, 479, 319);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

        scrollBar = new JScrollPane(textArea);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(29, 65, 479, 319);
        frame.getContentPane().add(scrollBar);

        lblCharacteristics = new JLabel("Caracteristici patrulater");
        lblCharacteristics.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblCharacteristics.setBounds(39, 23, 350, 27);
        frame.getContentPane().add(lblCharacteristics);

        btnSolicitareCont = new JButton("Inregistreaza-te!");
        btnSolicitareCont.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnSolicitareCont.setBounds(1040, 464, 213, 46);
        frame.getContentPane().add(btnSolicitareCont);

        btnAfisareDate = new JButton("Caracteristici patrulater");
        btnAfisareDate.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnAfisareDate.setBounds(39, 402, 350, 46);
        frame.getContentPane().add(btnAfisareDate);

        lblCont = new JLabel("Nu ai un cont?");
        lblCont.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblCont.setBounds(990, 427, 300, 27);
        frame.getContentPane().add(lblCont);

        btnBack = new JButton("Inapoi");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnBack.setBounds(39, 476, 149, 46);
        frame.getContentPane().add(btnBack);

        comboBoxColor = new JComboBox();
        comboBoxColor.setModel(new DefaultComboBoxModel(new String[] {"ROZ", "ALBASTRU", "VERDE"}));
        comboBoxColor.setSelectedIndex(1);
        comboBoxColor.setFont(new Font("Tahoma", Font.PLAIN, 20));
        comboBoxColor.setBounds(762, 394, 162, 32);
        frame.getContentPane().add(comboBoxColor);

        lblSelecteazaCuloareaPentru = new JLabel("Selecteaza culoarea:");
        lblSelecteazaCuloareaPentru.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblSelecteazaCuloareaPentru.setBounds(528, 394, 260, 32);
        frame.getContentPane().add(lblSelecteazaCuloareaPentru);

        btnSavePatrulater = new JButton("Salveaza patrulaterul");
        btnSavePatrulater.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnSavePatrulater.setBounds(1009, 234, 244, 46);
        frame.getContentPane().add(btnSavePatrulater);

        btnLoadPatrulater = new JButton("Incarca patrulaterul");
        btnLoadPatrulater.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnLoadPatrulater.setBounds(1013, 174, 240, 46);
        frame.getContentPane().add(btnLoadPatrulater);

        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
        languageComboBox.setBounds(1009, 65, 244, 46);
        frame.getContentPane().add(languageComboBox);

        lblSelecteazaLimba = new JLabel("Selecteaza limba:");
        lblSelecteazaLimba.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblSelecteazaLimba.setBounds(1046, 23, 260, 32);
        frame.getContentPane().add(lblSelecteazaLimba);

        frame.setVisible(true);

        m2 = new MouseListener() {
            int x, y;
            ArrayList<Integer> alX = new ArrayList<>();
            ArrayList<Integer> alY = new ArrayList<>();
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();

                alX.add(x);
                alY.add(y);

                g = panel.getGraphics();
                if (comboBoxColor.getSelectedItem().toString().equals("ROZ"))
                    g.setColor(roz);
                if (comboBoxColor.getSelectedItem().toString().equals("ALBASTRU"))
                    g.setColor(albastru);
                if (comboBoxColor.getSelectedItem().toString().equals("VERDE"))
                    g.setColor(verde);

                g.fillRect(x-3, y-3, 6, 6);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                alX.clear();
                alY.clear();
                panel.getGraphics().clearRect(0, 0, panel.getWidth(), panel.getHeight());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int[] xArray = new int[5];
                int[] yArray = new int[5];
                int i;

                for(i = 0; i < alX.size(); i ++){
                    xArray[i] = alX.get(i);
                    yArray[i] = alY.get(i);
                }

                myX.setText(alX.toString());
                myY.setText(alY.toString());

                xArray[i] = xArray[0];
                yArray[i] = yArray[0];

                g = panel.getGraphics();
                if (comboBoxColor.getSelectedItem().toString().equals("ROZ"))
                    g.setColor(roz);
                if (comboBoxColor.getSelectedItem().toString().equals("ALBASTRU"))
                    g.setColor(albastru);
                if (comboBoxColor.getSelectedItem().toString().equals("VERDE"))
                    g.setColor(verde);

                g.drawPolyline(xArray, yArray, alX.size()+1 );
            }
        };
        panel.addMouseListener(m2);

        frame.setVisible(true);

    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        GuestView.frame = frame;
    }

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void setMyX(String textArea) {
        this.myX.setText(textArea);
    }

    public void setMyY(String textArea) {
        this.myY.setText(textArea);
    }
}
