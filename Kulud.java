public class Kulud {

    private double[] üür;
    private double[] kommunaalid;
    private double[] söök;
    private double[] transport;
    private double[] meelelahutus;
    private double[] riided_jalatsid;
    private double[] ilu_tervis;

    public double[] getÜür() {
        return üür;
    }

    public void setÜür(double[] üür) {
        this.üür = üür;
    }

    public double[] getKommunaalid() {
        return kommunaalid;
    }

    public void setKommunaalid(double[] kommunaalid) {
        this.kommunaalid = kommunaalid;
    }

    public double[] getSöök() {
        return söök;
    }

    public void setSöök(double[] söök) {
        this.söök = söök;
    }

    public double[] getTransport() {
        return transport;
    }

    public void setTransport(double[] transport) {
        this.transport = transport;
    }

    public double[] getMeelelahutus() {
        return meelelahutus;
    }

    public void setMeelelahutus(double[] meelelahutus) {
        this.meelelahutus = meelelahutus;
    }

    public double[] getRiided_jalatsid() {
        return riided_jalatsid;
    }

    public void setRiided_jalatsid(double[] riided_jalatsid) {
        this.riided_jalatsid = riided_jalatsid;
    }

    public double[] getIlu_tervis() {
        return ilu_tervis;
    }

    public void setIlu_tervis(double[] ilu_tervis) {
        this.ilu_tervis = ilu_tervis;
    }

    public double kategooriasJäänud(double[] kategooria, double kulu) {
        kategooria[1] = kategooria[0] - kulu;
        return kategooria[1];
    }

    public void varstiÜlePiiri(double[] kategooria) {
        if (kategooria[1] > kategooria[0]*0.75)
            System.out.println("Oled kulutanud üle 75% selle kategooria eelarvest. Ole ettetvaatlik!");
    }
}
