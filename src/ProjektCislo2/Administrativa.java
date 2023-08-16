package ProjektCislo2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Administrativa extends Uloha implements Farba {
    private int pocetOdlozenychUloh = 0;
    private int pocetOdlozenychUlohViacAkoDvaDni = 0;
    private int stavMedziNextAPrevious = 1;
    private ListIterator<Administrativa> iteratorPosunuteODen = null;
    private Uloha uloha;

    public Administrativa(String typUlohy, String popisUlohy, String datumVzniku, String dolezitost, String datumPosunutia) {
        super(typUlohy, popisUlohy, datumVzniku, dolezitost, datumPosunutia);
    }

    public Administrativa() {
    }

    public void porovnanieDatumuAPocetUloh(LinkedList<Administrativa> zoznam) {
        LocalDate dnestnyDatum = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        Iterator<Administrativa> iterator = zoznam.listIterator();

        long pocetDni = 0;
        pocetOdlozenychUloh = 0;
        pocetOdlozenychUlohViacAkoDvaDni = 0;

        while (iterator.hasNext()) {
        Uloha uloha = iterator.next();
        LocalDate datumOdlozeniaUlohy = LocalDate.parse(uloha.getDatum(), formatter);
        pocetDni = ChronoUnit.DAYS.between(datumOdlozeniaUlohy,dnestnyDatum);

        if(pocetDni==1) {
        pocetOdlozenychUloh++;
        }
        else if (pocetDni>1) {
        pocetOdlozenychUlohViacAkoDvaDni++;
        }
        }

    }

    public int getpocetOdlozenychUloh() {
        return pocetOdlozenychUloh;
    }

    public int getPocetOdlozenychUlohViacAkoDvaDni() {
        return pocetOdlozenychUlohViacAkoDvaDni;
    }

    public void iteratorDalsiaUloha() {
        if(iteratorPosunuteODen.hasNext() && stavMedziNextAPrevious == 1) {
            System.out.println(iteratorPosunuteODen.next());
            if (iteratorPosunuteODen.hasNext()){
                System.out.println(MODRA + "\nP = Prevziat ulohu   D = Dalsia uloha   L = Predchadzajuca uloha"+ DEFAULT);
            }
            else {
                System.out.println(MODRA + "\nP = Prevziat ulohu   L = Predchadzajuca uloha"+DEFAULT);
            }
        }
        else if (iteratorPosunuteODen.hasNext()) {
            iteratorPosunuteODen.next();
            System.out.println(iteratorPosunuteODen.next());
            stavMedziNextAPrevious = 1;
            if (iteratorPosunuteODen.hasNext()){
                System.out.println(MODRA+ "\nP = Prevziat ulohu   D = Dalsia uloha   L = Predchadzajuca uloha" +DEFAULT);
            }
            else {
                System.out.println(MODRA+ "\nP = Prevziat ulohu   L = Predchadzajuca uloha"+DEFAULT);
            }
        }
    }

    public void iteratorPredoslaUloha() {
        if(iteratorPosunuteODen.hasPrevious() && stavMedziNextAPrevious == 0) {
            uloha = iteratorPosunuteODen.previous();
            System.out.println(uloha);
            if (iteratorPosunuteODen.hasPrevious()){
                System.out.println(MODRA + "\nP = Prevziat ulohu   D = Dalsia uloha   L = Predchadzajuca uloha" + DEFAULT);
            }
            else {
                System.out.println(MODRA+ "\nP = Prevziat ulohu   D = Dalsia uloha" + DEFAULT);
            }
        }
        else if (iteratorPosunuteODen.hasPrevious()) {
            iteratorPosunuteODen.previous();
            uloha = iteratorPosunuteODen.previous();
            System.out.println(uloha);
            stavMedziNextAPrevious = 0;
            if (iteratorPosunuteODen.hasPrevious()){
                System.out.println(MODRA +"\nP = Prevziat ulohu   D = Dalsia uloha   L = Predchadzajuca uloha"+ DEFAULT);
            }
            else {
                System.out.println(MODRA +"\nP = Prevziat ulohu   D = Dalsia uloha"+ DEFAULT);
            }
        }
    }

    public void setIteratorPosunuteODen(LinkedList zoznam) {
        iteratorPosunuteODen = zoznam.listIterator();
        iteratorPosunuteODen.next();
    }
    public int getIteratorPosunuteODenNextIndex() {
        return iteratorPosunuteODen.nextIndex();
    }

    public int getIteratorPosunuteODenPreviousIndex() {
        return iteratorPosunuteODen.previousIndex();
    }

    public int getStavMedziNextAPrevious() {
        return stavMedziNextAPrevious;
    }
}
