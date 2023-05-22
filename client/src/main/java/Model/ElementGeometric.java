package Model;

public abstract class ElementGeometric {
    protected String tip;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public abstract void desenare();
}
