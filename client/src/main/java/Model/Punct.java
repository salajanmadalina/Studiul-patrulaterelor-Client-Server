package Model;

import static java.lang.Math.sqrt;

public class Punct extends ElementGeometric {
    private int x;
    private int y;

    public Punct(){

    }

    public Punct(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public float distanta(Punct punct){
        return (float) sqrt((this.x - punct.getX()) * (this.x - punct.getX()) +
                (this.y - punct.getY()) * (this.y - punct.getY()));
    }

    @Override
    public void desenare() {

    }
}
