package Controller;

import View.UserView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;

public class UserController implements Observer{
    private UserView userView;
    private Language language;
    private int idUser;

    public UserController(Language language, int index) {
        this.userView = new UserView();
        this.language = language;
        this.language.attachObserver(this);
        this.language.setCurrentLanguage(index);
        addActionListeners();
    }
//
//    private ArrayList<Integer> calculateStatistics1(){
//        ArrayList<Integer> statistics = new ArrayList<Integer>(Collections.nCopies(11, 0));
//        ArrayList<Integer> statistics2 = new ArrayList<Integer>();
//
//        ArrayList<Test> tests = (ArrayList<Test>) testDAO.findAll();
//        for(Test test: tests){
//            int punctaj = test.getPunctaj();
//            int value = statistics.get(punctaj);
//            statistics.set(punctaj, value + 1);
//        }
//
//        for (Integer statistic : statistics) {
//            statistics2.add((100 * statistic) / tests.size());
//        }
//
//        return statistics2;
//    }
//
//    private ArrayList<Integer> calculateStatistics2(){
//        ArrayList<Integer> statistics = new ArrayList<Integer>(Collections.nCopies(11, 0));
//
//        ArrayList<Test> tests = (ArrayList<Test>) testDAO.findAll();
//        for(Test test: tests){
//            int punctaj = test.getPunctaj();
//            int value = statistics.get(punctaj);
//            statistics.set(punctaj, value + 1);
//        }
//
//        return statistics;
//    }

    void addActionListeners(){
        userView.getBtnBack().addActionListener(action -> {
            new LogInController(language.getLanguage());
            userView.getFrame().dispose();
        });

//        userView.getBtnTest().addActionListener(action -> {
//            ArrayList<Integer> intrebari = new ArrayList<Integer>();
//            Random rand = new Random();
//            int num;
//            IntrebareDAO intrebareDAO = new IntrebareDAO();
//            TestDAO testDAO = new TestDAO();
//
//            for (int i = 0; i < 10; i++) {
//                while(true){
//                    num = rand.nextInt((10 - 1) + 1) + 1;
//                    if(!intrebari.contains(num)){
//                        intrebari.add(num);
//                        break;
//                    }
//                }
//            }
//
//            String intrebariText = intrebari.stream().map(Object::toString).collect(Collectors.joining(", "));
//
//            String text = "";
//            for(int i = 0; i < 10; i ++){
//                text += intrebareDAO.findById(intrebari.get(i)).getIntrebare() + "\n";
//            }
//
//            int id = testDAO.findAll().size() + 1;
//            testDAO.insert(new Test(id, 0, intrebariText, idUser));
//
//            userView.setTextArea(text);
//            userView.setIdTest(String.valueOf(id));
//        });
//
//        userView.getBtnGenereazaPunctaj().addActionListener(action -> {
//            int id = Integer.parseInt(userView.getIdTest());
//            String answers = userView.getTextAnswers().getText();
//            IntrebareDAO intrebareDAO = new IntrebareDAO();
//            TestDAO testDAO = new TestDAO();
//            Test test = testDAO.findById(id);
//            String questions = test.getIntrebari();
//            ArrayList<Integer> numbers = new ArrayList<Integer>();
//
//            String[] tokens = questions.split(", ");
//            for(String token: tokens){
//                numbers.add(Integer.parseInt(token));
//            }
//
//            String correctAnswers = "";
//            for(int i = 0; i < 10; i ++) {
//                correctAnswers += intrebareDAO.findById(numbers.get(i)).getRaspuns() + "\n";
//            }
//
//            String[] strValues = answers.split("\n");
//            String[] raspunsuriCorecte = correctAnswers.split("\n");
//
//            int score = 0;
//            for(int i = 0; i < strValues.length; i ++){
//                if(strValues[i].equals(raspunsuriCorecte[i])){
//                    score++;
//                }
//            }
//
//            testDAO.update("punctaj", String.valueOf(score), id);
//            userView.setTextScore(String.valueOf(score));
//        });

        userView.getLanguageComboBox().addActionListener(actionListener -> {
            language.setCurrentLanguage(userView.getLanguageComboBox().getSelectedIndex());
        });

//        userView.getBtnStatistics().addActionListener(action -> {
//            ArrayList<Integer> statistics = calculateStatistics1();
//
//            DefaultPieDataset dataset = new DefaultPieDataset();
//            dataset.setValue("Score 0", statistics.get(0));
//            dataset.setValue("Score 1-2", (statistics.get(2) + statistics.get(1)));
//            dataset.setValue("Score 3-4", (statistics.get(3) + statistics.get(4)));
//            dataset.setValue("Score 5-6", (statistics.get(5) + statistics.get(6)));
//            dataset.setValue("Score 7-8", (statistics.get(7) + statistics.get(8)));
//            dataset.setValue("Score 9-10", (statistics.get(9) + statistics.get(10)));
//
//            JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
//            chart.setBackgroundPaint(Color.white);
//            userView.getChartPanel().setChart(chart);
//
//            DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
//            ArrayList<Integer> statistics2 = calculateStatistics2();
//
//            dataset2.addValue(statistics2.get(0), "Series 1", "0");
//            dataset2.addValue(statistics2.get(2) + statistics2.get(1), "Series 1", "1-2");
//            dataset2.addValue(statistics2.get(3) + statistics2.get(5), "Series 1", "3-4");
//            dataset2.addValue(statistics2.get(5) + statistics2.get(6), "Series 1", "5-6");
//            dataset2.addValue(statistics2.get(7) + statistics2.get(8), "Series 1", "7-8");
//            dataset2.addValue(statistics2.get(9) + statistics2.get(10), "Series 1", "9-10");
//            JFreeChart chart2 = ChartFactory.createBarChart("",
//                    "Score",
//                    "Nr. students",
//                    dataset2,
//                    PlotOrientation.VERTICAL,
//                    true,
//                    true,
//                    false);
//            userView.getChartPanel2().setChart(chart2);
//
//        });
    }

    @Override
    public void update() {
        userView.getBtnBack().setText(language.getRb().getString("back"));
        userView.getBtnGenereazaPunctaj().setText(language.getRb().getString("genScore"));
        userView.getBtnTest().setText(language.getRb().getString("test"));
        userView.getLblLimba().setText(language.getRb().getString("language"));
        userView.getLblAnswers().setText(language.getRb().getString("answers"));
        userView.getLblQuestions().setText(language.getRb().getString("questions"));
        userView.getLblScore().setText(language.getRb().getString("score"));

    }
}
