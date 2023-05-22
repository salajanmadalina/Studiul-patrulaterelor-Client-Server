package Model;

import java.util.ArrayList;

public class Desen {
    private ArrayList<ElementGeometric> desene;

    public Desen(ArrayList<ElementGeometric> desene) {
        this.desene = desene;
    }

    public Desen(){

    }

    public ArrayList<ElementGeometric> getDesene() {
        return desene;
    }

    public void setDesene(ArrayList<ElementGeometric> desene) {
        this.desene = desene;
    }

    public void addElementGeometric(ElementGeometric elementGeometric){
        desene.add(elementGeometric);
    }

    public void removeElementGeometric(ElementGeometric elementGeometric){
        desene.remove(elementGeometric);
    }
}
