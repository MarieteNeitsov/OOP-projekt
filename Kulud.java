import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class Kulud {

    public double[] kategooria;
    private double[][] andmed;

    public Kulud() {
        kategooria = new double[3];
        andmed = new double[8][3];
    }

    public double[] getKategooria() {
        return kategooria;
    }

    public void setKategooria(double[] kategooria) {
        this.kategooria = kategooria;
    }

    public double lisaEelarve(double summa) {
        kategooria[0] = summa;
        return kategooria[0];
    }

    public double tegelikKulu(double kulu) {
        kategooria[1] = kategooria[1] + kulu;
        return kategooria[1];
    }

    public double vahe() {
        kategooria[2] = kategooria[0] - kategooria[1];
        return kategooria[2];
    }

    public void varstiÜlePiiri() {
        if (kategooria[0] - kategooria[1] > kategooria[0]*0.75)
            System.out.println("Oled kulutanud üle 75% selle kategooria eelarvest. Ole ettetvaatlik!");
    }

    @Override
    public String toString() {
        return "\t" + kategooria[0] + "\t" + kategooria[1] + "\t" + kategooria[2];
    }
}
