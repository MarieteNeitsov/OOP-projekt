import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Eelarve {
    static int valdkonnaIndeks = 8;
    static double valdkonnaEelarve = 0;
    static double eelarvedKokku = 0;
    static double kuludKokku = 0;
    static int realoendur = 0;
    static double veelJaotada = 0.0;
    static List<Kulud> kulud;
    static Object[][] andmed = {{"Üür", 0, 0, 0},
            {"Kommunaalid", 0, 0, 0},
            {"Söök", 0, 0, 0},
            {"Transport", 0, 0, 0},
            {"Meelelahutus", 0, 0, 0},
            {"Riided/jalatsid", 0, 0, 0},
            {"Ilu/tervis", 0, 0, 0},
            {"Muu", 0, 0, 0},
            {"Kokku", 0, 0, 0},
            {},
            {"Säästud", 0, 0, 0}};

    public static void main(String[] args) {
        //kulude isendid iga valdkonna eelarve jaoks
        Kulud üür = new Kulud("üür");
        Kulud kommunaalkulud = new Kulud("kommunaalkulud");
        Kulud söök = new Kulud("söök");
        Kulud transport = new Kulud("transport");
        Kulud meelelahutus = new Kulud("meelelahutus");
        Kulud riided_ja_jalatsid = new Kulud("riided/jalatsid");
        Kulud ilu_ja_tervis = new Kulud("ilu/tervis");
        Kulud muu = new Kulud("muu");
        Kulud kokku = new Kulud("kokku");
        Kulud säästud = new Kulud("säästud");
        kulud = new ArrayList<>(Arrays.asList(üür, kommunaalkulud, söök, transport, meelelahutus, riided_ja_jalatsid, ilu_ja_tervis, muu));

        Scanner scan = new Scanner(System.in);
        System.out.print("Sisesta oma selle kuu sissetulek: ");
        double tulu = scan.nextDouble();
        System.out.println("Kas soovid sellel kuul säästa? jah/ei");
        String vastus = scan.next();
        double säästusumma = 0;

        // kasutaja soovil säästame juhusliku summa tulust ja lisame ka anmdetesse
        if (vastus.equals("jah")) {
            Saastmine säästmine = new Saastmine(tulu);
            säästusumma = säästmine.säästa();
            System.out.println("Summa, mille peaksid kõrvale panema: " + säästusumma);
            andmed[10][1] = säästud.lisaEelarve(säästusumma);
            andmed[10][2] = säästud.lisaKulu(säästusumma);
            andmed[10][3] = säästud.protsent();
        }
        System.out.println();
        boolean valik2 = false;

        // iga valdkonna eelarve jaoks küsime kasutajalt summat
        for (Kulud kulu : kulud) {
            System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " + kulu.getNimetus());
            valdkonnaEelarve = scan.nextDouble();
            andmed[realoendur][1] = kulu.lisaEelarve(valdkonnaEelarve);
            realoendur++;
            eelarvedKokku += valdkonnaEelarve;

            System.out.println();
            valdkonnaIndeks--;

            // väljastame eelarvete nimetused koos lisatud summadega ekraanile
            if (valdkonnaIndeks <= 7) {
                for (Kulud kulu1 : kulud)
                    System.out.println(kulu1.getNimetus() + ": " + kulu1.getKategooria()[0] + " eurot");
                System.out.println("säästud: " + säästusumma + " eurot");
            }
            System.out.println();
            andmed[8][1] = kokku.lisaEelarve(eelarvedKokku);

            veelJaotada = tulu - eelarvedKokku - säästusumma;


            //kui kasutaja on läinud eelarvetega üle tulu või on kõik ära jaotanud enne viimast eelarvet, siis tehakse vajaliku parandused
            if (veelJaotada == 0 && valdkonnaIndeks != 0 || veelJaotada < 0) {
                if (veelJaotada == 0) {
                    if (kasÜlejäänudeelarvedNulliks()) break;
                    else valik2 = true;
                }

                if (valik2 || veelJaotada < 0) {
                    // kui eelarved on üle tulu või on kasutaja valinud valiku 2 kasÜlejäänudeelarvedNulliks() meetodis anname võimaluse teise abimeetodiga käesolevat eelarvet parandada
                    eelarveParandus(andmed, kulud);
                    // kui peale parandamist on kõik tulud jaotatud küsime uuesti kas soovitakse parandada
                    if (veelJaotada == 0) {
                        if (kasÜlejäänudeelarvedNulliks()) break;
                        else eelarveParandus(andmed, kulud);

                    }
                    System.out.println("Jaotada on veel: " + veelJaotada + " eurot, valdkondi jäänud: " + (valdkonnaIndeks));
                }
            } else if (valdkonnaIndeks == 0 && veelJaotada > 0) {
                andmed[10][2] = säästud.lisaKulu(veelJaotada);
                andmed[10][3] = säästud.protsent();
                System.out.println("Sul jäi eelarvetest üle " + veelJaotada + " eurot, summa lisatud säästudesse");
            } else if (valdkonnaIndeks == 0)
                System.out.println("Kogu sissetulek edukalt eelarvete vahel ära jaotatud");
            else
                System.out.println("Jaotada on veel: " + veelJaotada + " eurot, valdkondi jäänud: " + (valdkonnaIndeks));
        }

        // siin on võimalus korduvalt lisada tehtud kulutusi ja vaadata ülevaadet oma eelarvetest ja kulutustest
        while (true) {
            System.out.println();
            System.out.println("Vali tegevus\n 1 - Lisa kulutus\n 2 - Vaata ülevaadet\n 3 - Lõpeta");
            String valik = scan.next();
            System.out.println();
            if (valik.equals("1")) {
                System.out.println("Vali valdkond\n 1 - üür\n 2 - kommunaalkulud\n 3 - söök\n 4 - transport\n 5 - meelelahutus\n 6 - riided/jalatsid\n 7 - ilu/tervis\n 8 - muu");
                int valdkond = scan.nextInt();
                System.out.println();
                System.out.println("Sisesta summa, mis selles valdkonnas kulutasid: ");
                double kulutus = scan.nextDouble();
                Kulud kategooria = kulud.get(valdkond - 1);
                andmed[valdkond - 1][2] = kategooria.lisaKulu(kulutus);
                if (kategooria.protsent() < 0)
                    andmed[valdkond - 1][3] = "Eelarve puudub!";
                else
                    andmed[valdkond - 1][3] = kategooria.protsent();

                //kui kulutused on alates meelelahutuse valdkonnast üle 75%, siis saab kasutaja hoiatuse
                if (valdkond > 4) {
                    System.out.println();
                    kategooria.ülePiiri();
                }
                kuludKokku += kulutus;
                kokku.lisaKulu(kulutus);
                andmed[8][2] = kuludKokku;
                andmed[8][3] = kokku.protsent();

            } else if (valik.equals("2")) {
                SwingUtilities.invokeLater(() -> new KuludeTabel(andmed));
                if (eelarvedKokku - kuludKokku > 0)
                    System.out.println("Võimalik kulutada veel " + (eelarvedKokku - kuludKokku) + " eurot");
                else if (eelarvedKokku + /*((double) andmed[8][2])*/-kuludKokku == 0)
                    System.out.println("Oled kõik eelarvetele planeeritud raha ära kulutanud!");
                else
                    System.out.println("Sinu kulud on ületanud eelarvetele planeeritud summa " + (eelarvedKokku + /*((double) andmed[8][2])*/ -kuludKokku) * -1 + " euro võrra");
            } else if (valik.equals("3")) {
                System.out.println("Sinu kulutused kokku: " + kuludKokku + " eurot");
                if (eelarvedKokku - kuludKokku > 0)
                    System.out.println("Kuna sinu kulud jäid alla planeeritud eelarvete summa, siis säästsid sel kuul kokku " + ((double) andmed[10][2] + (eelarvedKokku - kuludKokku)) + " eurot. Tubli!");
                else if (eelarvedKokku - kuludKokku < 0)
                    System.out.println("Sinu kulud ületasid planeeritud eelarvete summa " + (kuludKokku - eelarvedKokku) + " euro võrra. Proovi uuel kuul paremini");
                else
                    System.out.println("Jäid oma kulutustega täpselt planeeritud eelarvete summa piiridesse. Hea töö!");
                break;
            }
        }
    }

    public static void eelarveParandus(Object[][] andmed, List<Kulud> kulud) {
        if (veelJaotada < 0)
            System.out.println("Sinu planeeritud eelarved kokku ületavad tulu " + veelJaotada * -1.0 + " euro võrra, valdkondi jäänud: " + (valdkonnaIndeks));
        for (int i = 0; i < realoendur; i++)
            System.out.println(i + 1 + ". " + kulud.get(i).getNimetus());

        Scanner scan = new Scanner(System.in);

        System.out.print("Sisesta valdkonna number, mille eelarvet soovid parandada: ");
        int valdkond = scan.nextInt();
        Kulud kategooria = kulud.get(valdkond - 1);
        double eelmineEelarve;

        System.out.println("Sisesta summa, mille planeerid kulutada valdkonnas: " + kategooria.getNimetus());
        System.out.println();
        eelmineEelarve = (double) andmed[valdkond - 1][1];
        valdkonnaEelarve = scan.nextDouble();
        andmed[valdkond - 1][1] = kategooria.lisaEelarve(valdkonnaEelarve);


        //lisame tagasi valesti seatud eelarve ja lahutame maha uue
        veelJaotada += eelmineEelarve;
        veelJaotada -= valdkonnaEelarve;
        eelarvedKokku -= eelmineEelarve;
        eelarvedKokku += valdkonnaEelarve;
        for (Kulud kulu : kulud)
            System.out.println(kulu.getNimetus() + ": " + kulu.getKategooria()[0] + " eurot");
        System.out.println("säästud: " + andmed[10][2] + " eurot");
    }

    public static boolean kasÜlejäänudeelarvedNulliks() {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("Oled ära jaotanud kogu oma tulu, valdkondi jäänud: " + (valdkonnaIndeks));
        System.out.println("Vajuta 1, kui sa ei soovi järgnevaid eelarveid sisestada");
        System.out.println("Vajuta 2, kui soovid mõnda eelnevat eelarvet uuesti sisestada");
        String valik = scan.next();
        boolean otsus = true;
        if (valik.equals("2"))
            otsus = false;
        return otsus;
    }
}