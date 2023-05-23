package Controller;

import Commands.*;
import View.UserView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class UserController implements Observer{
    private UserView userView;
    private Language language;
    private Command graphics1Command;
    private Command graphics2Command;
    private Command allQuestionsCommand;
    private Command allTestsCommand;
    private Command addTestCommand;
    private Command allResponsesCommand;
    private Command updateTestCommand;

    public UserController(Language language, int index) {
        this.userView = new UserView();
        this.language = language;
        this.language.attachObserver(this);
        this.language.setCurrentLanguage(index);
        addActionListeners();
    }


    void addActionListeners(){
        userView.getBtnBack().addActionListener(action -> {
            new LogInController(language.getLanguage());
            userView.getFrame().dispose();
        });

        userView.getBtnTest().addActionListener(action -> {
            allQuestionsCommand = new AllQuestionsCommand();
            allQuestionsCommand.execute();

            if(allQuestionsCommand instanceof AllQuestionsCommand){
               ArrayList<String> questions = (ArrayList<String>) ((AllQuestionsCommand)allQuestionsCommand).getAllQuestions();

                ArrayList<Integer> intrebari = new ArrayList<Integer>();
                Random rand = new Random();

                int num;
                for (int i = 0; i < 10; i++) {
                    while(true){
                        num = rand.nextInt((19 - 1) + 1) + 1;
                        if(!intrebari.contains(num)){
                            intrebari.add(num);
                            break;
                        }
                    }
                }

                String intrebariText = intrebari.stream().map(Object::toString).collect(Collectors.joining(", "));

                String text = "";
                for(int i = 0; i < 10; i ++){
                    text += questions.get(intrebari.get(i)) + "\n";
                }

                userView.setTextArea(text);

                allTestsCommand = new AllTestsCommand();
                allTestsCommand.execute();

                if(allTestsCommand instanceof AllTestsCommand){
                    List<String> teste = ((AllTestsCommand) allTestsCommand).getAllTests();
                    int id = teste.size() + 1;
                    userView.setIdTest(String.valueOf(id));

                    addTestCommand = new AddTestCommand(id, 0, intrebariText, 1);
                    addTestCommand.execute();

                    userView.setRaspunsuri(intrebariText);
                }
            }
        });

        userView.getBtnGenereazaPunctaj().addActionListener(action -> {
            int id = Integer.parseInt(userView.getIdTest());
            String answers = userView.getTextAnswers().getText();
            String correctAnswers = "";

            allResponsesCommand = new AllResponsesCommand();
            allResponsesCommand.execute();

            List<String> responses = ((AllResponsesCommand)allResponsesCommand).getAllResponses();
            String intrebari = userView.getRaspunsuri();
            String[] intrebare = intrebari.split(", ");

            for(int i = 0; i < intrebare.length; i ++){
                correctAnswers += responses.get(Integer.parseInt(intrebare[i])) + "\n";
            }

            String[] answersSplit = answers.split("\n");
            String[] correctAnswersSplit = correctAnswers.split("\n");

            int score = 0;
            for(int i = 0; i < answersSplit.length; i ++){
                if(correctAnswersSplit[i].equals(answersSplit[i])){
                    score++;
                }
            }

            updateTestCommand = new UpdateTestCommand(score, id);
            updateTestCommand.execute();
            userView.setTextScore(String.valueOf(score));

        });

        userView.getLanguageComboBox().addActionListener(actionListener -> {
            language.setCurrentLanguage(userView.getLanguageComboBox().getSelectedIndex());
        });

        userView.getBtnStatistics().addActionListener(action -> {

            graphics1Command = new Graphics1Command();
            graphics1Command.execute();

            String info = ((Graphics1Command)graphics1Command).getAllStatistics();
            ArrayList<Integer> statistics =new ArrayList<>();

            String info1[] = info.split(",");
            for(int i = 0; i < info1.length; i ++){
                statistics.add(Integer.parseInt(info1[i]));
            }

            DefaultPieDataset dataset = new DefaultPieDataset();
            dataset.setValue("Score 0", statistics.get(0));
            dataset.setValue("Score 1-2", (statistics.get(2) + statistics.get(1)));
            dataset.setValue("Score 3-4", (statistics.get(3) + statistics.get(4)));
            dataset.setValue("Score 5-6", (statistics.get(5) + statistics.get(6)));
            dataset.setValue("Score 7-8", (statistics.get(7) + statistics.get(8)));
            dataset.setValue("Score 9-10", (statistics.get(9) + statistics.get(10)));

            JFreeChart chart = ChartFactory.createPieChart("", dataset, true, true, false);
            chart.setBackgroundPaint(Color.white);
            userView.getChartPanel().setChart(chart);

            graphics2Command = new Graphics2Command();
            graphics2Command.execute();

            info = ((Graphics2Command)graphics2Command).getAllStatistics();
            ArrayList<Integer> statistics2 =new ArrayList<>();

            String info2[] = info.split(",");
            for(int i = 0; i < info2.length; i ++){
                statistics2.add(Integer.parseInt(info2[i]));
            }

            DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

            dataset2.addValue(statistics2.get(0), "Series 1", "0");
            dataset2.addValue(statistics2.get(2) + statistics2.get(1), "Series 1", "1-2");
            dataset2.addValue(statistics2.get(3) + statistics2.get(5), "Series 1", "3-4");
            dataset2.addValue(statistics2.get(5) + statistics2.get(6), "Series 1", "5-6");
            dataset2.addValue(statistics2.get(7) + statistics2.get(8), "Series 1", "7-8");
            dataset2.addValue(statistics2.get(9) + statistics2.get(10), "Series 1", "9-10");
            JFreeChart chart2 = ChartFactory.createBarChart("",
                    "Score",
                    "Nr. students",
                    dataset2,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false);
            userView.getChartPanel2().setChart(chart2);

        });
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
