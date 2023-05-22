package Model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Patrulater extends FiguraGeometrica{
    private Punct p1;
    private Punct p2;
    private Punct p3;
    private Punct p4;

    public Patrulater() {
    }

    public Patrulater(Punct p1, Punct p2, Punct p3, Punct p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Patrulater(ArrayList<Integer> coords){
        this.p1 = new Punct(coords.get(0), coords.get(1));
        this.p2 = new Punct(coords.get(2), coords.get(3));
        this.p3 = new Punct(coords.get(4), coords.get(5));
        this.p4 = new Punct(coords.get(6), coords.get(7));
    }

    public ArrayList<Integer> lungimiLaturi(){
        ArrayList<Integer> lungimi = new ArrayList<Integer>();
        lungimi.add((int) p1.distanta(p2));
        lungimi.add((int) p2.distanta(p3));
        lungimi.add((int) p3.distanta(p4));
        lungimi.add((int) p4.distanta(p1));
        return lungimi;
    }

    public ArrayList<Integer> masuriUnghiuri() {
        ArrayList<Integer> masuri = new ArrayList<Integer>();
        float l1 = p1.distanta(p2);
        float l2 = p2.distanta(p3);
        float l3 = p3.distanta(p4);
        float l4 = p4.distanta(p1);

        float semiperimetru1 = (float)(l1 + l4 + p2.distanta(p4)) / 2;
        float aria1 = (float) Math.sqrt(semiperimetru1 * (semiperimetru1 - l1) * (semiperimetru1 - l4) * (semiperimetru1 - p2.distanta(p4)));
        float sin = ((float)(aria1 * 2)/(float)(l1 * l4));
        float unghi1 = (float) Math.asin(sin);
        int degree = (int) Math.toDegrees(unghi1);
        masuri.add(degree);

        semiperimetru1 = (float)(l1 + l2 + p1.distanta(p3)) / 2;
        aria1 = (float) Math.sqrt(semiperimetru1 * (semiperimetru1 - l1) * (semiperimetru1 - l2) * (semiperimetru1 - p1.distanta(p3)));
        sin = ((float)(aria1 * 2)/(float)(l1 * l2));
        unghi1 = (float) Math.asin(sin);
        degree = (int) Math.toDegrees(unghi1);
        masuri.add(degree);

        semiperimetru1 = (float)(l2 + l3 + p2.distanta(p4)) / 2;
        aria1 = (float) Math.sqrt(semiperimetru1 * (semiperimetru1 - l2) * (semiperimetru1 - l3) * (semiperimetru1 - p2.distanta(p4)));
        sin = ((float)(aria1 * 2)/(float)(l2 * l3));
        unghi1 = (float) Math.asin(sin);
        degree = (int) Math.toDegrees(unghi1);
        masuri.add(degree);

        semiperimetru1 = (float)(l3 + l4 + p1.distanta(p3)) / 2;
        aria1 = (float) Math.sqrt(semiperimetru1 * (semiperimetru1 - l3) * (semiperimetru1 - l4) * (semiperimetru1 - p1.distanta(p3)));
        sin = ((float)(aria1 * 2)/(float)(l3 * l4));
        unghi1 = (float) Math.asin(sin);
        degree = (int) Math.toDegrees(unghi1);
        masuri.add(degree);

        return masuri;
    }

    public int perimetru(){
        int perimetru = 0;
        for (Integer lungime : lungimiLaturi()) {
            perimetru += lungime;
        }
        return perimetru;
    }

    public int razaCerculuiCircumscris(){
        //verificam daca patrulaterul este convex
        if(convexSauConcav().equals("Concav"))
            return 0;

        ArrayList<Integer> laturi = lungimiLaturi();

        //verificam daca patrulaterul este circumscriptibil
        if(Math.round(laturi.get(0)) + Math.round(laturi.get(2)) != Math.round(laturi.get(1)) + Math.round(laturi.get(3))) {
            Dreapta ab = new Dreapta(p1, p2);
            Dreapta bc = new Dreapta(p2, p3);
            Dreapta cd = new Dreapta(p3, p4);
            Dreapta da = new Dreapta(p4, p1);
            Punct e = ab.punctIntersectie(cd);
            Punct f = bc.punctIntersectie(da);

            if(Math.round(p2.distanta(e)) + Math.round(p2.distanta(f)) != Math.round(p4.distanta(e)) + Math.round(p4.distanta(f))){
                return 0;
            }
        }
        // calculam raza cercului circumscris cu formula lui Euler
        return (int) ((laturi.get(0) * laturi.get(1) * laturi.get(2) * laturi.get(3)) / (4 * aria()));
    }

    public int razaCerculuiInscris(){
        //verificam daca patrulaterul este convex
        if(convexSauConcav().equals("Concav"))
            return 0;
        // calculam raza cercului inscris cu formula lui Brahmagupta
        return (int) ((2 * aria()) / (perimetru()));
    }

    public Cerc cercCircumscris(){
        float raza = razaCerculuiCircumscris();
        Dreapta d1 = new Dreapta(p1, p3);
        Dreapta d2 = new Dreapta(p2, p4);
        Punct centre = d1.punctIntersectie(d2);

        return new Cerc(centre, raza);
    }

    public Cerc cercInscris(){
        float razaInscris = razaCerculuiInscris();
        Punct centru = new Punct(
                (p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4,
                (p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4);
        return new Cerc(centru, razaInscris);
    }

    public String convexSauConcav(){
        for(int i = 0; i < 4; i ++){
            if(masuriUnghiuri().get(i) > 180.0f)
                return "Concav";
        }
        return "Convex";
    }

    public Punct punctulLuiNewton() {
        if(razaCerculuiCircumscris() != 0){
            int x = Math.round((p1.getX() + p2.getX() + p3.getX() + p4.getX()) / 4);
            int y = Math.round((p1.getY() + p2.getY() + p3.getY() + p4.getY()) / 4);

            return new Punct(x, y);
        }

       return null;
    }

    public Punct punctulMiquel() {

        if(convexSauConcav().equals("Convex")){
            ArrayList<Integer> laturi = lungimiLaturi();

            float AB = laturi.get(0);
            float BC = laturi.get(1);
            float CD = laturi.get(2);
            float DA = laturi.get(3);
            float AC = p1.distanta(p3);
            float BD = p2.distanta(p4);

            int x = Math.round((AB * CD * (p2.getY() - p4.getY()) + BC * DA * (p3.getY() - p1.getY()) + AC * BD * (p1.getY() + p3.getY() - p2.getY() - p4.getY())) / (2 * (AB * CD + BC * DA + AC * BD)));
            int y = Math.round((AB * CD * (p4.getX() - p2.getX()) + BC * DA * (p1.getX() - p3.getX()) + AC * BD * (p4.getX() + p2.getX() - p1.getX() - p3.getX())) / (2 * (AB * CD + BC * DA + AC * BD)));

            if(x < 0){
                x = -x;
            }
            if(y < 0){
                y = -y;
            }

            return new Punct(x, y);
        }
       return null;
    }

    public Punct punctMathot(){
        if(razaCerculuiInscris() != 0) {
            Punct centruCercInscris = cercInscris().getCentru();
            float d1 = p1.distanta(centruCercInscris);
            float d2 = p2.distanta(centruCercInscris);
            float d3 = p3.distanta(centruCercInscris);
            float d4 = p4.distanta(centruCercInscris);

            int sum = (int) (d1 + d2 + d3 + d4);
            int xm = (int) ((d1 * p1.getX() + d2 * p2.getX() + d3 * p3.getX() + d4 * p3.getX()) / sum);
            int ym = (int) ((d1 * p1.getY() + d2 * p2.getY() + d3 * p3.getY() + d4 * p4.getY()) / sum);

            return new Punct(xm, ym);
        }

        return null;
    }

    public ArrayList<Dreapta> diagonale(){
        ArrayList<Dreapta> drepte = new ArrayList<>();
        drepte.add(new Dreapta(p1, p3));
        drepte.add(new Dreapta(p2, p4));
        return drepte;
    }

    public ArrayList<Dreapta> bimediane(){
        ArrayList<Dreapta> drepte = new ArrayList<>();
        drepte.add(new Dreapta(new Punct(Math.round((float)(p1.getX() + p2.getX())/2), Math.round((float)(p1.getY() + p2.getY())/2)), new Punct(Math.round((float)(p3.getX() + p4.getX())/2), Math.round((float)(p3.getY() + p4.getY())/2))));
        drepte.add(new Dreapta(new Punct(Math.round((float)(p3.getX() + p2.getX())/2), Math.round((float)(p3.getY() + p2.getY())/2)), new Punct(Math.round((float)(p1.getX() + p4.getX())/2), Math.round((float)(p1.getY() + p4.getY())/2))));

        return drepte;
    }

    public ArrayList<Dreapta> bisectoare(){
        ArrayList<Dreapta> drepte = new ArrayList<>();

        ArrayList<Integer> unghi = masuriUnghiuri();
        for(int i = 0; i < 4; i ++){
            float unghiBisector = (float)(unghi.get(i) + unghi.get((i + 1) % 4)) / 2;
            int x = (int) (p1.getX() + Math.cos(Math.toRadians(unghiBisector)));
            int y = (int) (p1.getY() + Math.sin(Math.toRadians(unghiBisector)));
            drepte.add(new Dreapta(p1, new Punct(x, y)));
        }

        return drepte;
    }

    public ArrayList<Dreapta> dreaptaGauss(){
        ArrayList<Punct> midPoints = new ArrayList<>();
        ArrayList<Dreapta> drepte = new ArrayList<>();
        midPoints.add(new Punct(Math.round((float)(p1.getX() + p3.getX())/2), Math.round((float)(p1.getY() + p3.getY())/2)));
        midPoints.add(new Punct(Math.round((float)(p2.getX() + p4.getX())/2), Math.round((float)(p2.getY() + p4.getY())/2)));

        Dreapta dreapta1 = new Dreapta(p1, p2);
        Dreapta dreapta2 = new Dreapta(p3, p4);
        Punct intersection1 = dreapta1.punctIntersectie(dreapta2);

        Dreapta dreapta3 = new Dreapta(p1, p4);
        Dreapta dreapta4 = new Dreapta(p3, p2);
        Punct intersection2 = dreapta3.punctIntersectie(dreapta4);

        midPoints.add(new Punct(Math.round((float)(intersection1.getX() + intersection2.getX())/2), Math.round((float)(intersection1.getY() + intersection2.getY())/2)));

        drepte.add(new Dreapta(midPoints.get(0), midPoints.get(2)));
        drepte.add(new Dreapta(midPoints.get(0), midPoints.get(1)));
        drepte.add(new Dreapta(midPoints.get(2), midPoints.get(1)));

        return drepte;
    }

    private Punct ortocentru(Punct p1, Punct p2, Punct p3){
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
        int x3 = p3.getX();
        int y3 = p3.getY();

        double a = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - y3, 2));
        double b = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        double c = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        double ha = 2 * area / a;
        double hb = 2 * area / b;
        double hc = 2 * area / c;

        int x = (int) ((ha * x1 + hb * x2 + hc * x3) / (ha + hb + hc));
        int y = (int) ((ha * y1 + hb * y2 + hc * y3) / (ha + hb + hc));

        return new Punct(x, y);
    }

    public Dreapta dreaptaAubert(){
        Dreapta dreapta1 = new Dreapta(p1, p2);
        Dreapta dreapta2 = new Dreapta(p3, p4);
        Punct e = dreapta1.punctIntersectie(dreapta2);

        Dreapta dreapta3 = new Dreapta(p1, p4);
        Dreapta dreapta4 = new Dreapta(p3, p2);
        Punct f = dreapta3.punctIntersectie(dreapta4);

        Punct ortocentru1 = ortocentru(p4, e, p3);
        //Punct ortocentru2 = ortocentru(p2, p3, f);
        Punct ortocentru3 = ortocentru(p1, p4, f);
        //Punct ortocentru4 = ortocentru(p1, p2, e);

         return new Dreapta(ortocentru1, ortocentru3);
    }

    @Override
    public void desenare() {

    }

    @Override
    public float aria() {
        ArrayList<Integer> laturi = lungimiLaturi();

        float semiperimetru = perimetru() / 2;

        // calculam aria patrulaterului cu formula lui Brahmagupta
        float arie = (float) Math.sqrt((semiperimetru - laturi.get(0)) * (semiperimetru - laturi.get(1)) * (semiperimetru - laturi.get(2)) * (semiperimetru - laturi.get(3)));

        return arie;
    }

    @Override
    public String toString() {

        String information = "Convex sau concav ? " + convexSauConcav() + "\n";
        String laturi = "";
        String unghiuri = "";

        for(int i = 0; i < lungimiLaturi().size(); i ++){
            laturi += lungimiLaturi().get(i);
            unghiuri += masuriUnghiuri().get(i);

            if(i < 3){
                laturi += ", ";
                unghiuri += ", ";
            }
        }

        information += "Lungimile laturilor sunt: " + laturi + ";\n";
        information += "Masurile unghiurilor sunt: " + unghiuri + ";\n";
        information += "Perimetrul este " + perimetru() + ";\n";
        information += "Aria este " + aria() + ";\n";
        information += "Raza cercului circumscris este " + razaCerculuiCircumscris() + ";\n";
        information += "Raza cercului inscris este " + razaCerculuiInscris() + ";\n";
        Punct p = punctulLuiNewton();
        if(p != null)
            information += "Punctul lui Newton este de coordonate " + p.getX() + ", " + p.getY() + ";\n";
        else
            information += "Punctul lui Newton nu poate fi calculat deoarece patrulaterul nu indeplineste conditiile necesare;\n";
        Punct q = punctulMiquel();
        if(q != null)
            information += "Punctul lui Miquel este de coordonate " + q.getX() + ", " + q.getY() + ";\n";
        else
            information += "Punctul lui Miguel nu poate fi calculat deoarece patrulaterul nu indeplineste conditiile necesare;\n";

        return information;
    }
}
