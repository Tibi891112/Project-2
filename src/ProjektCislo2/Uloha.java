package ProjektCislo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Uloha <T> implements Farba {
    private String typUlohy;
    private String popisUlohy;
    private String datumVzniku;
    private String dolezitost;
    private String datum;
    private Uloha<T> uloha;
    private String[] zoznam;
    private Scanner vstupSubor;
    private boolean posunutieODen = false;
    protected String nazovSuboru = "Main_file.csv";

    public Uloha(String typUlohy, String popisUlohy, String datumVzniku, String dolezitost, String datumPosunutia) {
        this.typUlohy = typUlohy;
        this.popisUlohy = popisUlohy;
        this.datumVzniku = datumVzniku;
        this.dolezitost = dolezitost;
        this.datum = datumPosunutia;
    }

    public Uloha() {
    }

    public String getTypUlohy() {
        return typUlohy;
    }

    public String getPopisUlohy() {
        return popisUlohy;
    }

    public String getDatumVzniku() {
        return datumVzniku;
    }

    public String getDolezitost() {
        return dolezitost;
    }

    public String getDatum() {
        return datum;
    }

    public void setDolezitost(String dolezitost) {
        this.dolezitost = dolezitost;
    }

    public void nacitajSubor(String nazovSuboru) {
        try {
            vstupSubor = new Scanner (new File(nazovSuboru));
        } catch ( NullPointerException e ) {
            System.out.println("Zly nazov suboru");
            System.exit(0);
            throw new RuntimeException(e);
        }
        catch (FileNotFoundException e) {
            System.out.println("Nenaslo subor");
            System.exit(0);
            throw new RuntimeException(e);
        }
    }

    public List<T> vytvorLinkedlist(String nazovObjektu) {
        LinkedList<T> uloha = new LinkedList<>();
            while (vstupSubor.hasNext()) {
                String riadok = vstupSubor.nextLine();
                if(riadok.contains(nazovObjektu)){
                    uloha.add((T)stringNaObjekt(riadok));
                }
            }
            vstupSubor.close();
            return uloha;
    }
    public void urciPriorituDolezity(LinkedList<Uloha> uloha) {
        ArrayList<Uloha> docastnyList = new ArrayList<>();
        for (Iterator<Uloha> iter =  uloha.iterator(); iter.hasNext(); ) {
            Uloha riadok = iter.next();

            if(riadok.getDolezitost().equals("dolezite")) {
                docastnyList.add(riadok);
                iter.remove();
            }
        }
        for (int i =docastnyList.size()-1;i>=0;i--) {
            uloha.addFirst(docastnyList.get(i));
        }
    }

    public Uloha stringNaObjekt(String riadok) {
        zoznam = riadok.split(",");
        typUlohy = zoznam[0];
        popisUlohy = zoznam[1];
        datumVzniku = zoznam[2];
        if (zoznam.length==4 && zoznam[3].equals("dolezite")) {
            dolezitost = zoznam[3];
            return new Uloha(typUlohy,popisUlohy,datumVzniku,dolezitost,datum);
        }
        else if (zoznam.length==4) {
            dolezitost = "";
            datum = zoznam[3];
            return new Uloha(typUlohy,popisUlohy,datumVzniku,dolezitost,datum);
        }
        else {
            dolezitost = "";
            return new Uloha(typUlohy,popisUlohy,datumVzniku,dolezitost,datum);
        }
    }

    public void usporiadajZoznam(LinkedList<Uloha> uloha) {
        Collections.sort(uloha, new Comparator<Uloha>() {
            @Override
            public int compare(Uloha o1, Uloha o2) {
                try {
                    return new SimpleDateFormat("dd.MM.yyyy").parse(o1.getDatumVzniku()).compareTo(new SimpleDateFormat("dd.MM.yyyy").parse(o2.getDatumVzniku()));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }
        });
   //     uloha.stream().sorted((o1,o2) -> o1.getDatumVzniku().compareTo(o2.getDatumVzniku()));
    }

    public String getNazovSuboru() {
        return nazovSuboru;
    }

    @Override
    public String toString() {
        String vypis =
           "\nTyp ulohy: "+typUlohy +
                "\nPopis ulohy: " + popisUlohy +
                "\nDatum vzniku ulohy: " + datumVzniku +
                "\nPriority ulohy: " + dolezitost;
        if (datum!=null) {
            vypis=vypis+"\nDatum odlozenia: " + ZLTA +  datum + DEFAULT;
        }
        return vypis;
    }

    public String ulohaToStringLine(Uloha uloha) {
        String riadok ="";
        if(posunutieODen) {
            riadok= uloha.getTypUlohy() + "," + uloha.getPopisUlohy() + "," + uloha.getDatumVzniku() + "," + uloha.getDatum();
        }
        else {
            riadok = uloha.getTypUlohy() + "," + uloha.getPopisUlohy() + "," + uloha.getDatumVzniku() + "," + uloha.getDolezitost();
        }
        return riadok;
    }


    public void prevziatUlohu(LinkedList<T> zoznam) {
    uloha = (Uloha<T>) zoznam.remove();
    }

    public void vyriesitUlohu(LinkedList<T> vyrieseneZoznam) {
        vyrieseneZoznam.add((T) uloha);
    }

    public Uloha<T> getUloha() {
        return uloha;
    }

    public void setUloha(Uloha<T> uloha) {
        this.uloha = uloha;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setPosunutieODen(boolean posunutieODen) {
        this.posunutieODen = posunutieODen;
    }

    public void setScanner() {
        vstupSubor.close();
    }
}
