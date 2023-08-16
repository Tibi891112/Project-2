package ProjektCislo2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedList;

public class Reklamacia extends Uloha {

    public Reklamacia(String typUlohy, String popisUlohy, String datumVzniku, String dolezitost, String datumPosunutia) {
        super(typUlohy, popisUlohy, datumVzniku, dolezitost,datumPosunutia);
    }

    public Reklamacia() {
    }

    public void reklamaciaPoPatnastichDnoch(LinkedList<Reklamacia> zoznam) {
        LocalDate dnestnyDatum = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        Iterator<Reklamacia> iterator = zoznam.listIterator();
    //    pocetReklamacii=0;

        while (iterator.hasNext()) {
    //    pocetReklamacii++;
        Uloha reklamacia = iterator.next();
        LocalDate datumVznikuUlohy = LocalDate.parse(reklamacia.getDatumVzniku(), formatter);
        long pocetDni = ChronoUnit.DAYS.between(datumVznikuUlohy,dnestnyDatum);

        if(pocetDni>15) {
            reklamacia.setDolezitost("dolezite");
        }
        }
    }

    public int pocetReklamacii(LinkedList<Reklamacia> zoznam) {
        int pocetReklamacii = 0;
        Iterator<Reklamacia> iterator = zoznam.listIterator();
        while (iterator.hasNext()) {
            Uloha reklamacia = iterator.next();
            if(reklamacia.toString().contains("Reklamacia")) {
                pocetReklamacii++;
            }
        }
        return pocetReklamacii;
    }
}
