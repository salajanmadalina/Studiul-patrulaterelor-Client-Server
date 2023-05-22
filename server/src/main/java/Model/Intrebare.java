package Model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Intrebare {
    private String intrebare;
    private String raspuns;
    private int id;

    public Intrebare(String intrebare, String raspuns, int id) {
        this.intrebare = intrebare;
        this.raspuns = raspuns;
        this.id = id;
    }

    public Intrebare(){}

    @Override
    public String toString() {
        return  intrebare + '\'';
    }
}
