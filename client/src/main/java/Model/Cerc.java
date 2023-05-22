package Model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Cerc extends FiguraGeometrica{

    private Punct centru;
    private float raza;

    public Cerc(Punct centru, float raza) {
        this.centru = centru;
        this.raza = raza;
    }

    public Cerc(Punct centru) {
        this.centru = centru;
    }

    public Cerc(){

    }

    @Override
    public void desenare() {
        
    }

    @Override
    public float aria() {
        return 0;
    }

    @Override
    public String toString() {
        return Math.round(centru.getX()) + " " + Math.round(centru.getY()) + " " + Math.round(raza);
    }

    public ArrayList<Integer> toInt(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(centru.getX());
        list.add(centru.getY());
        list.add((int)Math.round(raza));

        return list;
    }
}
