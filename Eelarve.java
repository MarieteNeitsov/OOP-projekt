import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Eelarve{

    public static void main(String[] args) throws Exception {
        Object[][] andmed = {{"Üür",0,0,0},
                {"Kommunaalid",0,0,0},
                {"Söök",0,0,0},
                {"Transport",0,0,0},
                {"Meelelahutus",0,0,0},
                {"Riided/jalatsid",0,0,0},
                {"Ilu/tervis",0,0,0},
                {"Muu",0,0,0},
                {"Säästud",0,0,0}};

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

        int valdkonnaIndeks = 8;
        double valdkonnaEelarve = 0;
        double eelarvedKokku = 0;
        double kuludKokku = 0;
        int realoendur = 0;
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

            double veelJaotada = tulu - eelarvedKokku - säästusumma;

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
                    for (int i = 0; i < kulud.size(); i++) {
                        System.out.println(i+1 + ". " + kulud.get(i).getNimetus());
                    }
                    System.out.print("Sisesta valdkonna number, mille eelarvet soovid parandada: ");
                    String valdkond = scan.next();
                    double eelmineEelarve = 0;
                    switch (valdkond) {
                        case "1":
                            eelmineEelarve = (double) andmed[0][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: üür");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[0][1] = üür.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "2":
                            eelmineEelarve = (double) andmed[1][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: kommunaalkulud");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[1][1] = kommunaalkulud.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "3":
                            eelmineEelarve = (double) andmed[2][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: söök");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[2][1] = söök.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "4":
                            eelmineEelarve = (double) andmed[3][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: transport");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[3][1] = transport.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "5":
                            eelmineEelarve = (double) andmed[4][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: meelelahutus");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[4][1] = meelelahutus.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "6":
                            eelmineEelarve = (double) andmed[5][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: riided/jalatsid");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[5][1] = riided_ja_jalatsid.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "7":
                            eelmineEelarve = (double) andmed[6][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: ilu/tervis");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[6][1] = ilu_ja_tervis.lisaEelarve(valdkonnaEelarve);
                            break;
                        case "8":
                            eelmineEelarve = (double) andmed[7][1];
                            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: muu");
                            valdkonnaEelarve = scan.nextDouble();
                            andmed[7][1] = muu.lisaEelarve(valdkonnaEelarve);
                            break;
                    }
                    veelJaotada += eelmineEelarve;
                    veelJaotada -= valdkonnaEelarve;
                    eelarvedKokku -= eelmineEelarve;
                    eelarvedKokku += valdkonnaEelarve;
                    if (veelJaotada == 0) {
                        valik2 = true;
                    }
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
                String valdkond = scan.next();
                System.out.println("Sisesta summa, mis selles valdkonnas kulutasid: ");
                double kulutus = scan.nextDouble();
                switch (valdkond) {
                    case "1":
                        andmed[0][2] = üür.lisaKulu(kulutus);
                        andmed[0][3] = üür.protsent();
                        kuludKokku += kulutus;
                        break;
                    case "2":
                        andmed[1][2] = kommunaalkulud.lisaKulu(kulutus);
                        andmed[1][3] = kommunaalkulud.protsent();
                        kuludKokku += kulutus;
                        break;
                    case "3":
                        andmed[2][2] = söök.lisaKulu(kulutus);
                        andmed[2][3] = söök.protsent();
                        kuludKokku += kulutus;
                        break;
                    case "4":
                        andmed[3][2] = transport.lisaKulu(kulutus);
                        andmed[3][3] = transport.protsent();
                        kuludKokku += kulutus;
                        break;
                    case "5":
                        andmed[4][2] = meelelahutus.lisaKulu(kulutus);
                        andmed[4][3] = meelelahutus.protsent();
                        meelelahutus.varstiÜlePiiri();
                        kuludKokku += kulutus;
                        break;
                    case "6":
                        andmed[5][2] = riided_ja_jalatsid.lisaKulu(kulutus);
                        andmed[5][3] = riided_ja_jalatsid.protsent();
                        riided_ja_jalatsid.varstiÜlePiiri();
                        kuludKokku += kulutus;
                        break;
                    case "7":
                        andmed[6][2] = ilu_ja_tervis.lisaKulu(kulutus);
                        andmed[6][3] = ilu_ja_tervis.protsent();
                        ilu_ja_tervis.varstiÜlePiiri();
                        kuludKokku += kulutus;
                        break;
                    case "8":
                        andmed[7][2] = muu.lisaKulu(kulutus);
                        andmed[7][3] = muu.protsent();
                        muu.varstiÜlePiiri();
                        kuludKokku += kulutus;
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