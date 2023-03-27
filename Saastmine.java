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


    public int säästa(){ //kui tulu on üle 1000 euro genereerib
        Random suvaline = new Random();
        int protsent;
        if(tulu > 1000) {
            protsent = suvaline.nextInt(20) + 1;
        }
        else
            protsent = suvaline.nextInt(10) + 1;
        double sääst = protsent / 100.0 * tulu;
        int tulemus = (int) Math.ceil(sääst);
        return tulemus;
    }


}
