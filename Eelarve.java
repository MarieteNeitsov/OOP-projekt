import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Eelarve{

    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta oma selle kuu sissetulek: ");


        double  tulu = scan.nextDouble();


        System.out.println("Kas soovid sellel kuul säästa? jah/ei");
        String vastus = scan.next();
        if(vastus.equals("jah")){
            Saastmine säästmine = new Saastmine(tulu);
            int säästusumma = säästmine.säästa();
            System.out.println("Summa mille peaksid kõrvale panema: " + säästusumma);
        }
        /*Kulud: üür, kommud, söök, transport, meelelahutus, riided/jalatsid, ilu/tervis, muu
        Peale iga kategooria summa sisestamist kuvab ekraanile, mis summa veel jaotada on.
        Alates meelelahutusest annab hoiatuse, kui vähemalt 75% eelarvest on kulutatud.
        Kasutajal on valikud:
        Lisa kulutus
        Vaata ülevaadet
        Lõpeta*/
        Kulud üür = new Kulud("üür");
        Kulud kommunaalkulud = new Kulud("kommunaalkulud");
        Kulud söök = new Kulud("söök");
        Kulud transport = new Kulud("transport");
        Kulud meelelahutus = new Kulud("meelelahutus");
        Kulud riided_ja_jalatsid= new Kulud("riided/jalatsid");
        Kulud ilu_ja_tervis= new Kulud("ilu/tervis");
        Kulud muu = new Kulud("muu");
        List<Kulud> kulud = new ArrayList<>();
        kulud.addAll(Arrays.asList(üür,kommunaalkulud,söök,transport,meelelahutus,riided_ja_jalatsid,ilu_ja_tervis,muu));

        int valdkonnaIndeks = 7;
        double eelarvedKokku = 0;
        for (Kulud kulu:kulud) {

            if(valdkonnaIndeks <= 6){
                for (Kulud kulu1 :kulud) {
                    System.out.println(kulu1.getNimetus() + ": " + kulu1.getKategooria()[0] + " eurot");
                }
            }
            System.out.println("\n");

            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " + kulu.getNimetus());
            Double valdkonnaEelarve = scan.nextDouble();
            kulu.lisaEelarve(valdkonnaEelarve);
            eelarvedKokku += valdkonnaEelarve;

            double veelJaotada = tulu - eelarvedKokku;

            if(veelJaotada < 0){
                System.out.println("Sinu planeeritud eelarved kokku ületavad tulu ");
                //vaja lõpetada
            }
            else if(veelJaotada == 0 && valdkonnaIndeks != 0){
                System.out.println();
                // vaja lõpetada
            }
            else{
                System.out.println("\nJaotada on veel: " + veelJaotada + " eurot " + " valdkondi jäänud: " +valdkonnaIndeks +"\n");
            }
            valdkonnaIndeks--;

        }
        while(true){
            System.out.println("vali tegevus\n 1 - lisa kulutus\n 2 - vaata ülevaadet\n 3 - lõpeta");
            String valik = scan.next();
            if(valik.equals("1")){

            }
            else if(valik.equals("2")){

            }
            else if(valik.equals("3"))
                break;
        }








    }
}