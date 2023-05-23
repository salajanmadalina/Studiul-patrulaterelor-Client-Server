package Model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class User{
    private String nume;
    private int id;
    private String password;
    private String rol;

    private User(UserBuilder userBuilder) {
        this.nume = userBuilder.nume;
        this.password = userBuilder.password;
        this.rol = userBuilder.rol;
        this.id = userBuilder.id;
    }

    private User(){}

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", id=" + id +
                ", password=" + password +
                ", rol=" + rol +
                '}' + "\n";
    }

    public static class UserBuilder{
        private String nume;
        private int id;
        private String password;
        private String rol;

        public UserBuilder(String nume, String password, String rol, int id){
            this.nume = nume;
            this.password = password;
            this.rol = rol;
            this.id = id;
        }

        public UserBuilder(){

        }

        public User build(){
            return new User(this);
        }

    }
}
