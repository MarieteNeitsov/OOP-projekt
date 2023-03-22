public class Kulud {

    private double[] kategooria;

    public Kulud(String nimetus) {
        kategooria = new double[3];
    }

    public double lisaEelarve(double summa) {
        kategooria[0] = summa;
        return kategooria[0];
    }

    public double lisaKulu(double kulu) {
        kategooria[1] = kategooria[1] + kulu;
        return kategooria[1];
    }

    public int protsent() {
        kategooria[2] = (int) Math.round(kategooria[1] * 100 / kategooria[0]);
        return (int) kategooria[2];
    }

    public void varstiÜlePiiri() {
        if (kategooria[0] - kategooria[1] > kategooria[0] * 0.75)
            System.out.println("Oled kulutanud üle 75% selle kategooria eelarvest. Ole ettetvaatlik!");
    }
}