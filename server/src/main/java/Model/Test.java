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

    private Test(TestBuilder testBuilder) {
        this.intrebari = testBuilder.intrebari;
        this.id = testBuilder.id;
        this.punctaj = testBuilder.punctaj;
        this.idUser = testBuilder.idUser;
    }

    private Test(){}

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", intrebari='" + intrebari + '\'' +
                ", punctaj=" + punctaj +
                ", idUser=" + idUser +
                '}' + "\n";
    }

    public static class TestBuilder{
        private int id;
        private String intrebari;
        private int punctaj;
        private int idUser;

        public TestBuilder(int id, int punctaj, String intrebari, int idUser) {
            this.id = id;
            this.intrebari = intrebari;
            this.punctaj = punctaj;
            this.idUser = idUser;
        }

        public Test build(){
            return new Test(this);
        }

        public TestBuilder(){

        }
    }
}
