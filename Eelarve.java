import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Eelarve{
    static int valdkonnaIndeks = 8;
     static double valdkonnaEelarve = 0;
    static double eelarvedKokku = 0;
    static double kuludKokku = 0;
    static int realoendur = 0;
    static double veelJaotada =0.0;
    static Object[][] andmed = {{"Üür",0,0,0},
            {"Kommunaalid",0,0,0},
            {"Söök",0,0,0},
            {"Transport",0,0,0},
            {"Meelelahutus",0,0,0},
            {"Riided/jalatsid",0,0,0},
            {"Ilu/tervis",0,0,0},
            {"Muu",0,0,0},
            {"Säästud",0,0,0}};



    public static void main(String[] args) throws Exception{
        /*Object[][] andmed = {{"Üür",0,0,0},
                {"Kommunaalid",0,0,0},
                {"Söök",0,0,0},
                {"Transport",0,0,0},
                {"Meelelahutus",0,0,0},
                {"Riided/jalatsid",0,0,0},
                {"Ilu/tervis",0,0,0},
                {"Muu",0,0,0},
                {"Säästud",0,0,0}};*/

        Kulud üür = new Kulud("üür");
        Kulud kommunaalkulud = new Kulud("kommunaalkulud");
        Kulud söök = new Kulud("söök");
        Kulud transport = new Kulud("transport");
        Kulud meelelahutus = new Kulud("meelelahutus");
        Kulud riided_ja_jalatsid= new Kulud("riided/jalatsid");
        Kulud ilu_ja_tervis= new Kulud("ilu/tervis");
        Kulud muu = new Kulud("muu");
        Kulud säästud = new Kulud("säästud");
        List<Kulud> kulud = new ArrayList<>(Arrays.asList(üür, kommunaalkulud, söök, transport, meelelahutus, riided_ja_jalatsid, ilu_ja_tervis, muu));

        Scanner scan = new Scanner(System.in);
        System.out.print("Sisesta oma selle kuu sissetulek: ");
        double  tulu = scan.nextDouble();

        System.out.println("Kas soovid sellel kuul säästa? jah/ei ");
        String vastus = scan.next();
        double säästusumma = 0;
        if(vastus.equals("jah")){
            Saastmine säästmine = new Saastmine(tulu);
            säästusumma = säästmine.säästa();
            System.out.println("Summa, mille peaksid kõrvale panema: " + säästusumma);
            andmed[8][1] = säästud.lisaEelarve(säästusumma);
            andmed[8][2] = säästud.lisaKulu(säästusumma);
            andmed[8][3] = säästud.protsent();
        }
        System.out.println();

        /*int valdkonnaIndeks = 8;
        double valdkonnaEelarve = 0;
        double eelarvedKokku = 0;
        double kuludKokku = 0;
        int realoendur = 0;*/
        boolean valik2 = false;


        for (Kulud kulu:kulud) {

            if (!valik2) {
                System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " + kulu.getNimetus());
                valdkonnaEelarve = scan.nextDouble();
                andmed[realoendur][1] = kulu.lisaEelarve(valdkonnaEelarve);
                realoendur++;
                eelarvedKokku += valdkonnaEelarve;

                System.out.println();
                valdkonnaIndeks--;
                if (valdkonnaIndeks <= 7) {
                    for (Kulud kulu1 : kulud) {
                        System.out.println(kulu1.getNimetus() + ": " + kulu1.getKategooria()[0] + " eurot");
                    }
                    System.out.println("säästud: " + säästusumma + " eurot");
                }
                System.out.println();
            }

            //double veelJaotada = tulu - eelarvedKokku - säästusumma;
            veelJaotada = tulu - eelarvedKokku - säästusumma;

            if(veelJaotada == 0 && valdkonnaIndeks != 0 || veelJaotada <0){
                if(veelJaotada <0) System.out.println("Sinu planeeritud eelarved kokku ületavad tulu " + veelJaotada* -1.0 + " euro võrra, valdkondi jäänud: " + (valdkonnaIndeks));
                else {
                    System.out.println("Oled ära jaotanud kogu oma tulu, valdkondi jäänud: " + (valdkonnaIndeks));
                    System.out.println("Vajuta 1, kui sa ei soovi järgnevaid eelarveid sisestada");
                    System.out.println("Vajuta 2, kui soovid eelmist eelarvet uuesti sisestada");
                    String valik = scan.next();
                    if(valik.equals("1")) break;
                    if(valik.equals("2")) valik2 = true;
                }

                if(valik2|| veelJaotada <0 ){

                   /* boolean x = true;
                    boolean lopp = false;
                    while(x){*/
                        for (int i = 0; i < realoendur; i++) {
                            System.out.println(i+1 + ". " + kulud.get(i).getNimetus());
                        }
                    System.out.print("Sisesta valdkonna number, mille eelarvet soovid parandada: ");
                    int valdkond = scan.nextInt();
                    Kulud kategooria = kulud.get(valdkond-1);
                    eelarveParandus(andmed,valdkond,kategooria);
                    /*double eelmineEelarve = 0;

                    System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " +kategooria.getNimetus());

                    eelmineEelarve = (double) andmed[valdkond-1][1];
                    valdkonnaEelarve = scan.nextDouble();
                    andmed[valdkond-1][1] = kategooria.lisaEelarve(valdkonnaEelarve);

                    veelJaotada += eelmineEelarve;
                    veelJaotada -= valdkonnaEelarve;
                    eelarvedKokku -= eelmineEelarve;
                    eelarvedKokku += valdkonnaEelarve;*/
                    if (veelJaotada == 0) {
                        for (int i = 0; i < realoendur; i++) {
                            System.out.println(i+1 + ". " + kulud.get(i).getNimetus());
                        }
                        System.out.print("Sisesta valdkonna number, mille eelarvet soovid parandada: ");
                        valdkond = scan.nextInt();
                        kategooria = kulud.get(valdkond-1);
                        eelarveParandus(andmed,valdkond,kategooria);
                        /*System.out.println("Oled ära jaotanud kogu oma tulu, valdkondi jäänud: " + (valdkonnaIndeks));
                        System.out.println("Vajuta 1, kui sa ei soovi järgnevaid eelarveid sisestada");
                        System.out.println("Vajuta 2, kui soovid eelmist eelarvet uuesti sisestada");
                        String valik = scan.next();
                        if(valik.equals("1")) { lopp = true; break;};
                        if(valik.equals("2")) x = true;
                    }
                    if(x!=true) x=false;

                    } if(lopp) break;*/}
                    System.out.println("Jaotada on veel: " + veelJaotada + " eurot, valdkondi jäänud: " + (valdkonnaIndeks));

                }

            }
            else if (valdkonnaIndeks == 0 && veelJaotada > 0) {
                andmed[8][2] = säästud.lisaKulu(veelJaotada);
                andmed[8][3] = säästud.protsent();
                System.out.println("Sul jäi eelarvetest üle " + veelJaotada + " eurot, summa lisatud säästudesse");
            } else if (valdkonnaIndeks == 0 && veelJaotada == 0) {
                System.out.println("Kogu sissetulek edukalt eelarvete vahel ära jaotatud");
            } else{
                System.out.println("Jaotada on veel: " + veelJaotada + " eurot, valdkondi jäänud: " + (valdkonnaIndeks));
            }
            System.out.println();
        }

        while(true){
            System.out.println("vali tegevus\n 1 - lisa kulutus\n 2 - vaata ülevaadet\n 3 - lõpeta");
            String valik = scan.next();
            if(valik.equals("1")) {
                System.out.println("Vali valdkond\n 1 - üür\n 2 - kommunaalkuludsöök\n 3 - söök\n 4 - transport\n 5 - meelelahutus\n 6 - riided ja jalatsid\n 7 - ilu ja tervis\n 8 - muu");
                int valdkond = scan.nextInt();
                System.out.println("Sisesta summa, mis selles valdkonnas kulutasid: ");
                double kulutus = scan.nextDouble();
                Kulud kategooria = kulud.get(valdkond-1);
                andmed[valdkond-1][2] = kategooria.lisaKulu(kulutus);
                andmed[valdkond-1][3] = kategooria.protsent();
                kategooria.varstiÜlePiiri();
                kuludKokku += kulutus;


            }
            else if(valik.equals("2")){
                SwingUtilities.invokeLater(() -> new KuludeTabel(andmed));
                if(eelarvedKokku + /*((double) andmed[8][2])*/ -kuludKokku > 0) System.out.println("Võimalik veel kulutada: " + (eelarvedKokku + /*((double) andmed[8][2])*/-kuludKokku));
                else if(eelarvedKokku + /*((double) andmed[8][2])*/-kuludKokku== 0) System.out.println("Oled kõik eelarvetele planeeritud raha ära kulutanud!");
                else System.out.println("Sinu kulud on ületanud eelarvetele planeeritud summa " + (eelarvedKokku + /*((double) andmed[8][2])*/ -kuludKokku) * -1 + " euro võrra");
            }
            else if(valik.equals("3"))
                System.out.println("Sinu kulutused kokku: " + kuludKokku);
                if(eelarvedKokku-kuludKokku >0){
                    System.out.println("Kuna sinu kulud jäid alla planeeritud eelarvete summa, siis sa säästsid " + ((double) andmed[8][2] + (eelarvedKokku-kuludKokku))+ " eurot");
                }else{

                }
                break;
        }








    }
    public static void eelarveParandus(Object[][] andmed, int valdkond, Kulud kategooria){
        Scanner scan = new Scanner(System.in);
        double eelmineEelarve = 0;

        System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " +kategooria.getNimetus());

        eelmineEelarve = (double) andmed[valdkond-1][1];
        valdkonnaEelarve = scan.nextDouble();
        andmed[valdkond-1][1] = kategooria.lisaEelarve(valdkonnaEelarve);

        veelJaotada += eelmineEelarve;
        veelJaotada -= valdkonnaEelarve;
        eelarvedKokku -= eelmineEelarve;
        eelarvedKokku += valdkonnaEelarve;

    }

}