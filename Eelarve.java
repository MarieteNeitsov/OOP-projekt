import java.util.Scanner;

public class Eelarve{

    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(System.in);
        System.out.println("Sisesta oma selle kuu tulu");
        double tulu = scan.nextDouble();

        Saastmine säästmine = new Saastmine(tulu);

        System.out.println("Kas soovid sellel kuul säästa? jah/ei");
        String vastus = scan.next();
        if(vastus.equals("jah")){
            int säästusumma = säästmine.säästa();
            System.out.println("Summa mille peaksid kõrvale panema: " + säästusumma);
        }






    }
}