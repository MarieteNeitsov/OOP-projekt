import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Eelarve{

    public static void main(String[] args) {
        Object[][] andmed = {{"Üür",0,0,0},
                {"Kommunaalid",0,0,0},
                {"Söök",0,0,0},
                {"Transport",0,0,0},
                {"Meelelahutus",0,0,0},
                {"Riided/jalatsid",0,0,0},
                {"Ilu/tervis",0,0,0},
                {"Muu",0,0,0},
                {"Säästud",0,0,0}};

        Scanner scan = new Scanner(System.in);
        System.out.print("Sisesta oma selle kuu sissetulek: ");


        double  tulu = scan.nextDouble();


        System.out.print("Kas soovid sellel kuul säästa? jah/ei ");
        String vastus = scan.next();
        double säästusumma = 0;
        if(vastus.equals("jah")){
            Saastmine säästmine = new Saastmine(tulu);
            säästusumma = (int) Math.round(säästmine.säästa());
            System.out.println("Summa mille peaksid kõrvale panema: " + säästusumma);
            andmed[8][1] = säästusumma;
        }
        System.out.println();

        Kulud üür = new Kulud("üür");
        Kulud kommunaalkulud = new Kulud("kommunaalkulud");
        Kulud söök = new Kulud("söök");
        Kulud transport = new Kulud("transport");
        Kulud meelelahutus = new Kulud("meelelahutus");
        Kulud riided_ja_jalatsid= new Kulud("riided/jalatsid");
        Kulud ilu_ja_tervis= new Kulud("ilu/tervis");
        Kulud muu = new Kulud("muu");
        List<Kulud> kulud = new ArrayList<>(Arrays.asList(üür, kommunaalkulud, söök, transport, meelelahutus, riided_ja_jalatsid, ilu_ja_tervis, muu));

        int valdkonnaIndeks = 7;
        double eelarvedKokku = 0;
        int realoendur = 0;
        for (Kulud kulu:kulud) {
            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " + kulu.getNimetus());
            double valdkonnaEelarve = scan.nextDouble();
            andmed[realoendur][1] = kulu.lisaEelarve(valdkonnaEelarve);
            realoendur++;
            eelarvedKokku += valdkonnaEelarve;

            System.out.println();
            valdkonnaIndeks--;
            if(valdkonnaIndeks <= 6){
                for (Kulud kulu1 :kulud) {
                    System.out.println(kulu1.getNimetus() + ": " + kulu1.getKategooria()[0] + " eurot");
                }
                System.out.println("säästud: " + säästusumma + " eurot");
            }
            System.out.println();

            double veelJaotada = tulu - eelarvedKokku - säästusumma;

            if(veelJaotada == 0 && valdkonnaIndeks != 0 || veelJaotada <0){
                boolean valik2 = false;

                if(veelJaotada <0) System.out.println("Sinu planeeritud eelarved kokku ületavad tulu " + veelJaotada* -1.0 + " euro võrra, valdkondi jäänud: " + (valdkonnaIndeks + 1));
                else {
                    System.out.println("Oled ära jaotanud kogu oma tulu, valdkondi jäänud: " + (valdkonnaIndeks + 1));
                    System.out.println("Vajuta 1, kui sa ei soovi järgnevaid eelarveid sisestada");
                    System.out.println("Vajuta 2, kui soovid eelmist eelarvet uuesti sisestada");
                    String valik = scan.next();
                    if(valik.equals("1")) break;
                    if(valik.equals("2")) valik2 = true;
                }

                if(valik2|| veelJaotada <0 ){

                    System.out.println("Sisesta uus eelarve valdkonnale: "+ kulu.getNimetus());

                    eelarvedKokku -= valdkonnaEelarve;
                    realoendur--;
                    double uusValdkonnaEelarve = scan.nextDouble();
                    andmed[realoendur][1] = kulu.lisaEelarve(valdkonnaEelarve);
                    realoendur++;
                    eelarvedKokku += uusValdkonnaEelarve;
                }

            }
            else{
                System.out.println("Jaotada on veel: " + veelJaotada + " eurot, valdkondi jäänud: " + (valdkonnaIndeks + 1));
            }
            System.out.println();
        }

        while(true){
            System.out.println("vali tegevus\n 1 - lisa kulutus\n 2 - vaata ülevaadet\n 3 - lõpeta");
            String valik = scan.next();
            if(valik.equals("1")) {
                System.out.println("Vali valdkond\n 1 - üür\n 2 - kommunaalkuludsöök\n 3 - söök\n 4 - transport\n 5 - meelelahutus\n 6 - riided ja jalatsid\n 7 - ilu ja tervis\n 8 - muu");
                String valdkond = scan.next();
                System.out.println("Sisesta summa, mis selles valdkonnas kulutasid: ");
                double kulutus = scan.nextDouble();
                switch (valdkond) {
                    case "1":
                        üür.lisaKulu(kulutus);
                        break;
                    case "2":
                        kommunaalkulud.lisaKulu(kulutus);
                        break;
                    case "3":
                        söök.lisaKulu(kulutus);
                        break;
                    case "4":
                        transport.lisaKulu(kulutus);
                        break;
                    case "5":
                        meelelahutus.lisaKulu(kulutus);
                        break;
                    case "6":
                        riided_ja_jalatsid.lisaKulu(kulutus);
                        break;
                    case "7":
                        ilu_ja_tervis.lisaKulu(kulutus);
                        break;
                    case "8":
                        muu.lisaKulu(kulutus);
                        break;

                }
            }
            else if(valik.equals("2")){
                SwingUtilities.invokeLater(() -> new KuludeTabel(andmed));
            }
            else if(valik.equals("3"))
                break;
        }








    }
}