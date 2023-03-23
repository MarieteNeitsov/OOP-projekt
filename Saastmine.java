import java.util.Random;
public class Saastmine {
    double tulu;

    public Saastmine(double tulu) {
        this.tulu = tulu;
    }

    public double getTulu() {
        return tulu;
    }

    public void setTulu(double tulu) {
        this.tulu = tulu;
    }

    public double säästa(){
        int protsent;
        if(tulu > 1000)
            protsent = (int) (Math.random() * 20);
        else
            protsent = (int) (Math.random() * 10);
        return protsent * tulu / 100;

    }


}
