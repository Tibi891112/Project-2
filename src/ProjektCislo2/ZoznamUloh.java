package ProjektCislo2;

import java.util.Iterator;
import java.util.LinkedList;

public class ZoznamUloh extends Uloha {
    private LinkedList<Administrativa> administrativa;
    private LinkedList<Reklamacia> reklamacia;
    private LinkedList<Administrativa> odlozeneOJedenDen;
    private Iterator<Uloha> iterator = null;
    private boolean pravdivost = true;


    public ZoznamUloh() {
        administrativa = new LinkedList<>();
        reklamacia = new LinkedList<>();
        odlozeneOJedenDen = new LinkedList<>();
    }
    public void administrativaList() {
        administrativa = (LinkedList<Administrativa>) vytvorLinkedlist(Administrativa.class.getSimpleName());
    }

    public void reklamaciaList() {
        reklamacia = (LinkedList<Reklamacia>) vytvorLinkedlist(Reklamacia.class.getSimpleName());
    }

    public void odlozeneOJedenDenList() {
        odlozeneOJedenDen = (LinkedList<Administrativa>) vytvorLinkedlist(Administrativa.class.getSimpleName());
    }

    public LinkedList<Administrativa> getAdministrativa() {
        return administrativa;
    }

    public LinkedList<Reklamacia> getReklamacia() {
        return reklamacia;
    }
    public LinkedList<Reklamacia> finalnyZoznamReklamacia() {
        Reklamacia reklamaciaObj = new Reklamacia();
        nacitajSubor("Main_file.csv");
        reklamaciaList();
        usporiadajZoznam(reklamacia);
        reklamaciaObj.reklamaciaPoPatnastichDnoch(reklamacia);
        urciPriorituDolezity(reklamacia);
        return reklamacia;
    }

    public LinkedList<Administrativa> finalnyZoznamAdministrativa() {
        Administrativa administrativaObj = new Administrativa();
        nacitajSubor("Main_file.csv");
        administrativaList();
        setScanner();
        usporiadajZoznam(administrativa);
        urciPriorituDolezity(administrativa);
        return administrativa;
    }

    public LinkedList<Administrativa> finalnyZoznamOdlozeneOJedenDen() {
        nacitajSubor("Posunute_o_den.csv");
        odlozeneOJedenDenList();
        setScanner();
        return odlozeneOJedenDen;
    }

    private Iterator vytvorIterator(LinkedList<Uloha> zoznam) {
        return zoznam.listIterator();
    }
    public void vypisUlohu(LinkedList zoznam) {
     iterator= vytvorIterator(zoznam);
     System.out.println(iterator.next().toString());
    }

}


