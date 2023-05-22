package Controller;

import Model.Cerc;
import Model.Dreapta;
import Model.Patrulater;
import Model.Punct;
import View.GuestView;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import com.thoughtworks.xstream.security.ExplicitTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GuestController implements Observer{
    private GuestView guestView;
    private Language language;
    private Patrulater patrulater;

    public GuestController(Language language, int index) {
        guestView = new GuestView();
        this.language = language;
        this.patrulater = new Patrulater();
        this.language.attachObserver(this);
        this.language.setCurrentLanguage(index);
        addActionListeners();
    }

    private void addActionListeners(){
        guestView.getBtnBack().addActionListener(action -> {
            new LogInController(language.getLanguage());
            guestView.getFrame().dispose();;
        });

        guestView.getBtnSolicitareCont().addActionListener(action -> {
            new LogInController(language.getLanguage());
            guestView.getFrame().dispose();;
        });

        guestView.getBtnLoadPatrulater().addActionListener(action -> {
            patrulater = new Patrulater();

            XStream xstream = new XStream();
            xstream.alias("patrulater", Patrulater.class);

            XStream.setupDefaultSecurity(xstream);
            xstream.addPermission(AnyTypePermission.ANY);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.addPermission(new ExplicitTypePermission(new Class[] { Patrulater.class }));

            try {
                FileReader reader = new FileReader("D:\\UTCN\\ANUL 3\\SEM 2\\PS\\proiect3\\src\\main\\java\\patrulater.xml");
                patrulater = (Patrulater) xstream.fromXML(reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            String textX = patrulater.getP1().getX() + " " + patrulater.getP2().getX() + " " + patrulater.getP3().getX() + " " + patrulater.getP4().getX() + " " + patrulater.getP1().getX();
            String textY = patrulater.getP1().getY() + " " + patrulater.getP2().getY() + " " + patrulater.getP3().getY() + " " + patrulater.getP4().getY() + " " + patrulater.getP1().getY();

            String[] coordsX = textX.split(" ");
            String[] coordsY = textY.split(" ");
            int[] xArray = new int[5];
            int[] yArray = new int[5];

            for(int i = 0; i < 5; i ++){
                xArray[i] = Integer.parseInt(coordsX[i]);
                yArray[i] = Integer.parseInt(coordsY[i]);
                guestView.getG().fillRect(xArray[i]-3, yArray[i]-3, 6, 6);
            }
            guestView.getG().drawPolyline(xArray, yArray, 5 );


            guestView.setMyX(textX);
            guestView.setMyY(textY);
        });

        guestView.getBtnSavePatrulater().addActionListener(action -> {
            String alX = String.valueOf(guestView.getMyX().getText());
            String alY = String.valueOf(guestView.getMyY().getText());
            String[] numbersX = alX.replaceAll("\\D+", " ").trim().split(" ");
            String[] numbersY = alY.replaceAll("\\D+", " ").trim().split(" ");
            ArrayList<Integer> coords = new ArrayList<>();

            for(int i = 0; i < 4; i ++){
                coords.add(Integer.valueOf(numbersX[i]));
                coords.add(Integer.valueOf(numbersY[i]));
            }

            patrulater = new Patrulater(coords);

            XStream xstream = new XStream();
            xstream.alias("patrulater", Patrulater.class);

            String xml = xstream.toXML(patrulater);
            try {
                FileWriter writer = new FileWriter("D:\\UTCN\\ANUL 3\\SEM 2\\PS\\proiect3\\src\\main\\java\\patrulater.xml");

                writer.write(xml + "\n");

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        guestView.getBtnAfisareDate().addActionListener(action -> {
            String alX = String.valueOf(guestView.getMyX().getText());
            String alY = String.valueOf(guestView.getMyY().getText());
            String[] numbersX = alX.replaceAll("\\D+", " ").trim().split(" ");
            String[] numbersY = alY.replaceAll("\\D+", " ").trim().split(" ");
            ArrayList<Integer> coords = new ArrayList<>();

            for(int i = 0; i < 4; i ++){
                coords.add(Integer.valueOf(numbersX[i]));
                coords.add(Integer.valueOf(numbersY[i]));
            }

            //Informatii patrulater
            patrulater = new Patrulater(coords);
            guestView.setTextArea(patrulater.toString());

            //Cercuri
            Cerc cercInscris = patrulater.cercInscris();
            Cerc cercCircumscris = patrulater.cercCircumscris();
            guestView.getG().drawOval(Math.round(cercInscris.getCentru().getX() - cercInscris.getRaza()), Math.round(cercInscris.getCentru().getY() - cercInscris.getRaza()), Math.round(2 * cercInscris.getRaza()), Math.round(2 * cercInscris.getRaza()));
            guestView.getG().drawOval(Math.round(cercCircumscris.getCentru().getX() - cercCircumscris.getRaza()), Math.round(cercCircumscris.getCentru().getY() - cercCircumscris.getRaza()), Math.round(2 * cercCircumscris.getRaza()), Math.round(2 * cercCircumscris.getRaza()));

            //Diagonale
            ArrayList<Dreapta> drepte = patrulater.diagonale();
            guestView.getG().drawLine(drepte.get(0).getPunct1().getX(), drepte.get(0).getPunct1().getY(), drepte.get(0).getPunct2().getX(), drepte.get(0).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(1).getPunct1().getX(), drepte.get(1).getPunct1().getY(), drepte.get(1).getPunct2().getX(), drepte.get(1).getPunct2().getY());

            //Bimediane
            drepte = patrulater.bimediane();
            guestView.getG().drawLine(drepte.get(0).getPunct1().getX(), drepte.get(0).getPunct1().getY(), drepte.get(0).getPunct2().getX(), drepte.get(0).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(1).getPunct1().getX(), drepte.get(1).getPunct1().getY(), drepte.get(1).getPunct2().getX(), drepte.get(1).getPunct2().getY());

            //Bisectoare
            drepte = patrulater.bisectoare();
            guestView.getG().drawLine(drepte.get(0).getPunct1().getX(), drepte.get(0).getPunct1().getY(), drepte.get(0).getPunct2().getX(), drepte.get(0).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(1).getPunct1().getX(), drepte.get(1).getPunct1().getY(), drepte.get(1).getPunct2().getX(), drepte.get(1).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(2).getPunct1().getX(), drepte.get(2).getPunct1().getY(), drepte.get(2).getPunct2().getX(), drepte.get(2).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(3).getPunct1().getX(), drepte.get(3).getPunct1().getY(), drepte.get(3).getPunct2().getX(), drepte.get(3).getPunct2().getY());

            //Dreapta Gauss
            drepte = patrulater.dreaptaGauss();
            guestView.getG().setColor(Color.ORANGE);
            guestView.getG().fillRect(drepte.get(0).getPunct1().getX()-3, drepte.get(0).getPunct1().getY()-3, 6, 6);
            guestView.getG().fillRect(drepte.get(1).getPunct2().getX()-3, drepte.get(1).getPunct2().getY()-3, 6, 6);
            guestView.getG().fillRect(drepte.get(2).getPunct1().getX()-3, drepte.get(2).getPunct1().getY()-3, 6, 6);

            guestView.getG().drawLine(drepte.get(0).getPunct1().getX(), drepte.get(0).getPunct1().getY(), drepte.get(0).getPunct2().getX(), drepte.get(0).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(1).getPunct1().getX(), drepte.get(1).getPunct1().getY(), drepte.get(1).getPunct2().getX(), drepte.get(1).getPunct2().getY());
            guestView.getG().drawLine(drepte.get(2).getPunct1().getX(), drepte.get(2).getPunct1().getY(), drepte.get(2).getPunct2().getX(), drepte.get(2).getPunct2().getY());

            //Dreapta Aubert
            Dreapta dreapta = patrulater.dreaptaAubert();
            guestView.getG().setColor(Color.MAGENTA);
            guestView.getG().drawLine(dreapta.getPunct1().getX(), dreapta.getPunct1().getY(), dreapta.getPunct2().getX(), dreapta.getPunct2().getY());

            //Punct Miguel
            Punct punctMiguel = patrulater.punctulMiquel();
            if(punctMiguel != null)
                guestView.getG().fillRect(punctMiguel.getX()-3, punctMiguel.getY()-3, 6, 6);

            //Punct Newton
            Punct punctNewton= patrulater.punctulLuiNewton();
            if(punctNewton != null)
                guestView.getG().fillRect(punctNewton.getX()-3, punctNewton.getY()-3, 6, 6);

            //Punct Mathot
            Punct punctMathot = patrulater.punctMathot();
            if(punctMathot != null){
                guestView.getG().fillRect(punctMathot.getX()-3, punctMathot.getY()-3, 6, 6);

            }

        });

        guestView.getLanguageComboBox().addActionListener(actionListener -> {
            language.setCurrentLanguage(guestView.getLanguageComboBox().getSelectedIndex());
        });
    }

    @Override
    public void update() {
        guestView.getBtnBack().setText(language.getRb().getString("back"));
        guestView.getBtnLoadPatrulater().setText(language.getRb().getString("load"));
        guestView.getBtnSavePatrulater().setText(language.getRb().getString("save"));
        guestView.getBtnAfisareDate().setText(language.getRb().getString("show"));
        guestView.getBtnSolicitareCont().setText(language.getRb().getString("register"));
        guestView.getLblDraw().setText(language.getRb().getString("draw"));
        guestView.getLblCharacteristics().setText(language.getRb().getString("show"));
        guestView.getLblCont().setText(language.getRb().getString("cont"));
        guestView.getLblSelecteazaLimba().setText(language.getRb().getString("language"));
        guestView.getLblSelecteazaCuloareaPentru().setText(language.getRb().getString("color"));

    }
}
