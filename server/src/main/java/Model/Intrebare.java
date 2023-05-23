package Model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Intrebare {
    private String intrebare;
    private String raspuns;
    private int id;

    private Intrebare(IntrebareBuilder intrebareBuilder) {
        this.intrebare = intrebareBuilder.intrebare;
        this.raspuns = intrebareBuilder.raspuns;
        this.id = intrebareBuilder.id;
    }

    private Intrebare(){}

    @Override
    public String toString() {
        return  intrebare + "\n";
    }

    public class IntrebareBuilder{
        private String intrebare;
        private String raspuns;
        private int id;

        public IntrebareBuilder(String intrebare, String raspuns, int id) {
            this.intrebare = intrebare;
            this.raspuns = raspuns;
            this.id = id;
        }

        public IntrebareBuilder(){

        }

        public Intrebare build(){
            return new Intrebare(this);
        }
    }
}
