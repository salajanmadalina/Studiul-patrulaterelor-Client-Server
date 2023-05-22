package Model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Test {
    private int id;
    private String intrebari;
    private int punctaj;
    private int idUser;

    public Test(int id, int punctaj, String intrebari, int idUser) {
        this.intrebari = intrebari;
        this.id = id;
        this.punctaj = punctaj;
        this.idUser = idUser;
    }

    public Test(){}

}
