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

    public int säästa(){
        Random suvaline = new Random();
        int protsent;
        if(tulu > 1000)
            protsent = suvaline.nextInt(20) + 1;
        else
            protsent = suvaline.nextInt(10) +1;
        return (int) (protsent/100 * tulu);

    }


}
