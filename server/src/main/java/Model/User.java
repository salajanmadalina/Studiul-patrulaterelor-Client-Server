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

    public User(String nume, String password, String rol, int id) {
        this.nume = nume;
        this.password = password;
        this.rol = rol;
        this.id = id;
    }

    public User(){}

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", id=" + id +
                ", password=" + password +
                ", rol=" + rol +
                '}' + "\n";
    }
}
