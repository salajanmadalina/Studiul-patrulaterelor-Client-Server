package Model;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class Dreapta extends ElementGeometric{
    protected Punct punct1;
    protected Punct punct2;

    public Dreapta() {
    }

    public Dreapta(Punct punct1, Punct punct2) {
        this.punct1 = punct1;
        this.punct2 = punct2;
    }

    public float panta(){
        return (float) (punct2.getY() - punct1.getY()) / (punct2.getX() - punct1.getX());
    }

    public boolean paralele(Dreapta dreapta){
        return this.panta() == dreapta.panta();
    }

    public boolean perpendiculare(Dreapta dreapta){
        return this.panta() * dreapta.panta() == -1;
    }

    public Punct punctIntersectie(Dreapta dreapta){
        float x = (dreapta.panta() * dreapta.punct1.getX() - this.panta() * this.punct1.getX() + this.punct1.getY() - dreapta.punct1.getY()) / (dreapta.panta() - this.panta());
        float y = this.panta() * x + this.punct1.getY() - this.panta() * this.punct1.getX();
        return new Punct(Math.round(x), Math.round(y));
    }

    public boolean apartine(Punct punct){
        float m = this.panta();
        float b = this.punct1.getY() - m * this.punct1.getX();
        return punct.getY() == Math.round(m * punct.getX() + b);
    }

    @Override
    public void desenare() {

    }

}
