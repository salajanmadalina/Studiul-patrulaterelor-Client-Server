package View;

import javax.swing.*;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;

@Setter
@Getter

public class UserView extends JFrame {
    private static JFrame frame;
    private JTextArea textArea;
    private JButton btnTest;
    private JButton btnGenereazaPunctaj;
    private JTextArea textAnswers;
    private JTextArea textScore;
    private JButton btnBack;
    private JScrollPane scrollBar;
    private JLabel lblAnswers;
    private JLabel lblQuestions;
    private JLabel lblScore;
    private JComboBox<String> languageComboBox;
    private String[] languages = {"ENGLISH", "ITALIAN", "FRENCH"};
    private JLabel lblLimba;
    private JLabel idTest;
    private JButton btnStatistics;
    private JFreeChart chart;
    private JFreeChart chart2;
    private ChartPanel chartPanel;
    private ChartPanel chartPanel2;

    public UserView() {

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(204, 153, 153));
        frame.getContentPane().setForeground(new Color(210, 180, 140));
        frame.setBounds(100, 100, 1400, 580);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textArea = new JTextArea();
        textArea.setBounds(231, 77, 304, 299);
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        textArea.setEditable(false);
        frame.getContentPane().add(textArea);

        scrollBar = new JScrollPane(textArea);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setBounds(231, 77, 304, 299);
        frame.getContentPane().add(scrollBar);

        lblQuestions = new JLabel("Intrebari test");
        lblQuestions.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblQuestions.setBounds(239, 23, 260, 27);
        frame.getContentPane().add(lblQuestions);

        idTest = new JLabel();
        idTest.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        idTest.setBounds(450, 23, 260, 27);
        frame.getContentPane().add(idTest);

        btnTest = new JButton("Genereaza Test");
        btnTest.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnTest.setBounds(14, 87, 187, 46);
        frame.getContentPane().add(btnTest);

        textAnswers = new JTextArea();
        textAnswers.setEditable(true);
        textAnswers.setBounds(558, 78, 265, 298);
        textAnswers.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        frame.getContentPane().add(textAnswers);

        lblAnswers = new JLabel("Raspunsurile tale");
        lblAnswers.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        lblAnswers.setBounds(558, 23, 260, 27);
        frame.getContentPane().add(lblAnswers);

        textScore = new JTextArea();
        textScore.setEditable(false);
        textScore.setBounds(667, 455, 156, 51);
        textScore.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        frame.getContentPane().add(textScore);

        lblScore = new JLabel("Punctaj");
        lblScore.setFont(new Font("Times New Roman", Font.PLAIN, 27));
        lblScore.setBounds(546, 471, 248, 27);
        frame.getContentPane().add(lblScore);

        btnGenereazaPunctaj = new JButton("Genereaza punctaj");
        btnGenereazaPunctaj.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        btnGenereazaPunctaj.setBounds(16, 152, 185, 46);
        frame.getContentPane().add(btnGenereazaPunctaj);

        btnBack = new JButton("Inapoi");
        btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnBack.setBounds(22, 330, 179, 46);
        frame.getContentPane().add(btnBack);

        lblLimba = new JLabel("Selecteaza limba:");
        lblLimba.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblLimba.setBounds(38, 472, 260, 27);
        frame.getContentPane().add(lblLimba);

        btnStatistics = new JButton("Statistics");
        btnStatistics.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        btnStatistics.setBounds(14, 220, 187, 46);
        frame.getContentPane().add(btnStatistics);

        languageComboBox = new JComboBox<>(languages);
        languageComboBox.setBounds(231, 466, 179, 40);
        frame.getContentPane().add(languageComboBox);

        PiePlot plot = new PiePlot();
        chart  = new JFreeChart(plot);
        chartPanel = new ChartPanel(chart);
        chartPanel.setBounds(900, 0, 380, 270);
        frame.getContentPane().add(chartPanel);

        chart2  = new JFreeChart(plot);
        chartPanel2 = new ChartPanel(chart2);
        chartPanel2.setBounds(900, 270, 380, 270);
        frame.getContentPane().add(chartPanel2);

        frame.setVisible(true);

    }

    public void setTextArea(String textArea) {
        this.textArea.setText(textArea);
    }

    public void setIdTest(String id){
        this.idTest.setText(id);
    }

    public String getIdTest(){
        return this.idTest.getText();
    }

    public void setTextScore(String textScore){
        this.textScore.setText(textScore);
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        UserView.frame = frame;
    }
}
