package ProjektCislo2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class SuboryCsv {
    private LocalDate dnestnyDatum;
    private Scanner subor;
    private PrintWriter zapisovac;
    private String plnyNazovSuboru;
    private ZoznamUloh zoznamUloh;

    public SuboryCsv(String nazovSuboru, boolean posunutie) {
        this.dnestnyDatum = LocalDate.now();
        plnyNazovSuboru = (posunutie) ? nazovSuboru : nazovSuboru + dnestnyDatumPreNazovSuboru();
        zoznamUloh = new ZoznamUloh();
        zoznamUloh.setPosunutieODen(posunutie);
        try {
            if(!Files.exists(Path.of(plnyNazovSuboru + ".csv"))) {
                Files.createFile(Path.of(plnyNazovSuboru + ".csv"));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String dnestnyDatumPreNazovSuboru() {
        return  "_" + dnestnyDatum.getDayOfMonth() + "_" + dnestnyDatum.getMonthValue() + "_" + dnestnyDatum.getYear();
    }

    public String dnestnyDatumFormatPreUlozenieDoSuboru() {
        return  dnestnyDatum.getDayOfMonth()+ "." + dnestnyDatum.getMonthValue() + "." + dnestnyDatum.getYear();
    }

   //zapise do noveho cisteho zoznamu + prepise stary
   public void zapisCelyZoznam(LinkedList zoznam){
       Iterator iterator = zoznam.iterator();

       try {
           if(!Files.exists(Path.of(plnyNazovSuboru+ "2.csv"))) {
               Files.createFile(Path.of(plnyNazovSuboru+ "2.csv"));
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       File subor1 = new File(plnyNazovSuboru + "2.csv");
       File subor2 = new File(plnyNazovSuboru + ".csv");

       try {
           this.subor = new Scanner(subor2);
           this.zapisovac = new PrintWriter(subor1);
       } catch (FileNotFoundException e) {
           System.out.println("Nenasiel sa subor");
       }
       if(!subor.hasNext()) {
           while(iterator.hasNext()) {
               zapisovac.write(zoznamUloh.ulohaToStringLine((Uloha) iterator.next()));
               zapisovac.write("\n");
           }
       }

       else {
           while (subor.hasNextLine()) {
               String riadok = subor.nextLine();
               if(!riadok.isEmpty()) {
                   zapisovac.write(riadok);
                   zapisovac.write("\n");
               }
           }
           while(iterator.hasNext()) {
               zapisovac.write(zoznamUloh.ulohaToStringLine((Uloha) iterator.next()));
               zapisovac.write("\n");
           }
       }
       subor.close();
       zapisovac.close();

       String menoSuboru = subor2.getName();
       subor2.delete();
       subor1.renameTo(new File(menoSuboru));
   }

   public void spojiDveZoznamy(LinkedList prvyZoznam,LinkedList druhyZoznam) {
       Iterator iterator = prvyZoznam.iterator();
       boolean pravda = true;
       boolean druhyIterator = true;

       try {
           if(!Files.exists(Path.of(plnyNazovSuboru+ "2.csv"))) {
               Files.createFile(Path.of(plnyNazovSuboru+ "2.csv"));
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       File subor1 = new File(plnyNazovSuboru + "2.csv");
       File subor2 = new File(plnyNazovSuboru + ".csv");

       try {
           this.zapisovac = new PrintWriter(subor1);
       } catch (FileNotFoundException e) {
           System.out.println("Nenasiel sa subor");
       }

       while(pravda) {
           while(iterator.hasNext()) {
               zapisovac.write(zoznamUloh.ulohaToStringLine((Uloha) iterator.next()));
               zapisovac.write("\n");
           }
           if(druhyIterator) {
               iterator = druhyZoznam.iterator();
           }
           else {
               pravda=false;
           }
           druhyIterator =false;
       }

       zapisovac.close();

       String menoSuboru = subor2.getName();
       subor2.delete();
       subor1.renameTo(new File(menoSuboru));
   }

   public void vytvorNovyCistySubor() {
       try {
           if(!Files.exists(Path.of(plnyNazovSuboru + "2.csv"))) {
               Files.createFile(Path.of(plnyNazovSuboru + "2.csv"));
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       File subor1 = new File(plnyNazovSuboru + "2.csv");
       File subor2 = new File(plnyNazovSuboru + ".csv");

       String menoSuboru = subor2.getName();
       subor2.delete();
       subor1.renameTo(new File(menoSuboru));
   }

   public void kopirujeSuborDoDalsiehoSuboru(String nazovSuboru) {
       try {
           File subor = new File(nazovSuboru + ".csv");
           subor.delete();
           Files.copy(new File(plnyNazovSuboru +".csv").toPath(),new File( nazovSuboru + ".csv").toPath());
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

    public void setPlnyNazovSuboru(String plnyNazovSuboru) {
        this.plnyNazovSuboru = plnyNazovSuboru;
    }
}
