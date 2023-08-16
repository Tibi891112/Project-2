package ProjektCislo2;

import java.util.LinkedList;
import java.util.Scanner;

public class Ovladanie implements Farba {
    private Uloha uloha;
    private ZoznamUloh zoznamUloh;
    private Reklamacia reklamacia;
    private Administrativa administrativa;
    private SuboryCsv suborVyrieseneUlohy;
    private SuboryCsv suborNevyrieseneUlohy;
    private SuboryCsv suborOdlozeneODen;
    private VypisovanieTextu vypisovanieTextu;
    private Scanner scanner;
    private LinkedList<Reklamacia> reklamaciaList;
    private LinkedList<Administrativa> administrativaList;
    private LinkedList<Administrativa> odlozeneOJedenDen;
    private LinkedList vyrieseneUlohy;
    private LinkedList neVyrieseneUlohy;
    private boolean ovladacCyklu = true;

    private String volba = "";
    public Ovladanie() {
        uloha = new Uloha();
        reklamacia = new Reklamacia();
        administrativa = new Administrativa();
        zoznamUloh= new ZoznamUloh();
        vypisovanieTextu = new VypisovanieTextu();
        vyrieseneUlohy = new LinkedList<>();
        neVyrieseneUlohy = new LinkedList<>();
        suborVyrieseneUlohy = new SuboryCsv("Vyriesene_Ulohy",false);
        suborNevyrieseneUlohy = new SuboryCsv("Nevyriesene_Ulohy",false);
        suborOdlozeneODen = new SuboryCsv("Posunute_o_den", true);
        scanner = new Scanner(System.in);
        reklamaciaList = zoznamUloh.finalnyZoznamReklamacia();
        administrativaList = zoznamUloh.finalnyZoznamAdministrativa();
        odlozeneOJedenDen = zoznamUloh.finalnyZoznamOdlozeneOJedenDen();

        while (true) {
            administrativa.porovnanieDatumuAPocetUloh(odlozeneOJedenDen);
            System.out.println(MODRA + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░Program TV░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░" + DEFAULT);
            System.out.println("Nacitany subor: " + uloha.getNazovSuboru());
        //    System.out.println("Pocet Reklamacii: " + reklamacia.getPocetReklamacii());
            System.out.println("Pocet Reklamacii: " + reklamacia.pocetReklamacii(reklamaciaList));
            //  Urobit odlozene pocet
            System.out.println("Pocet Odlozenych uloh: " + administrativa.getpocetOdlozenychUloh());
            if(administrativa.getPocetOdlozenychUlohViacAkoDvaDni()>0){
                System.out.println(ZLTA + "Pocet Odlozenych uloh ktorych datum ulozenia je viac ako den: " + administrativa.getPocetOdlozenychUlohViacAkoDvaDni() + DEFAULT);
            }
    // toto je zapisane pokus
       //     suborVyrieseneUlohy.zapisCelyZoznam(administrativaList,"administrativaaa");
         //   System.out.println("Dalsia uloha: \nA = Administrativa    R = Reklamacia    O = Odlozene ulohy    U = Ulozit zoznamy    N = Vloz nove ulohy do zoznamu    K = Koniec a uloz");

            vypisovanieTextu.hlavneMenu(administrativaList.size(),reklamaciaList.size(),administrativa.getpocetOdlozenychUloh(),administrativa.getPocetOdlozenychUlohViacAkoDvaDni());
            volba = scanner.nextLine();

            if(volba.equalsIgnoreCase("a")&&(vypisovanieTextu.isAdministrativaKladnyPocet())) {
                zoznamUloh.vypisUlohu(administrativaList);
                System.out.println(MODRA+"\nP = Prevziat ulohu"+ DEFAULT);
                volba = scanner.nextLine();

                if(volba.equalsIgnoreCase("p")) {
                    zoznamUloh.prevziatUlohu(administrativaList);
                        if(zoznamUloh.getUloha().getDolezitost().equals("dolezite")) {
                            System.out.println(MODRA + "\nV = Vyriesit ulohu" + DEFAULT);
                            volba = scanner.nextLine();
                            if(volba.equalsIgnoreCase("v")) {
                                zoznamUloh.vyriesitUlohu(vyrieseneUlohy);
                            //    suborVyrieseneUlohy.zapisRiadok(zoznamUloh.getUloha());
                            }
                         }
                        else {
                            System.out.println(MODRA + "\nV = Vyriesit ulohu   O = Odlozit ulohu" + DEFAULT);
                            volba = scanner.nextLine();
                            if(volba.equalsIgnoreCase("v")){
                    // Sa zapisovala uloha do suboru na jeden riadok
                    //            suborVyrieseneUlohy.zapisRiadok(zoznamUloh.getUloha());
                                zoznamUloh.vyriesitUlohu(vyrieseneUlohy);
                            }
                            else if(volba.equalsIgnoreCase("o")){
                                zoznamUloh.getUloha().setDatum(suborOdlozeneODen.dnestnyDatumFormatPreUlozenieDoSuboru());
                          //      suborOdlozeneODen.zapisRiadok(zoznamUloh.getUloha());
                                zoznamUloh.vyriesitUlohu(odlozeneOJedenDen);
                            }
                            else{
                                System.out.println(CERVENA + "Nespravny vyber" + DEFAULT);
                            }
                        }
                }
                else{
                    System.out.println(CERVENA + "Nespravny vyber" + DEFAULT);
                }
            }
            else if(volba.equalsIgnoreCase("r")&&(vypisovanieTextu.isReklamaciaKladnyPocet())) {
                zoznamUloh.vypisUlohu(reklamaciaList);
                System.out.println(MODRA + "\nP = Prevziat ulohu" + DEFAULT);
                volba = scanner.nextLine();

                if(volba.equalsIgnoreCase("p")) {
                    // treba dopisat
                    zoznamUloh.prevziatUlohu(reklamaciaList);
                    System.out.println(MODRA + "\nV = Vyriesit ulohu   Z = Zrusit ulohu" + DEFAULT);
                    volba = scanner.nextLine();
                    if(volba.equalsIgnoreCase("v")){
                  //      Sa zapisovala uloha do suboru na jeden riadok
                  //      suborVyrieseneUlohy.zapisRiadok(zoznamUloh.getUloha());
                        zoznamUloh.vyriesitUlohu(vyrieseneUlohy);
                    }
                    else if(volba.equalsIgnoreCase("z")){
                    //    reklamacia.zrusitUlohu(reklamaciaList);
                        System.out.println(MODRA + "Uloha bola zrusena" + DEFAULT);
                    }
                    else{
                        System.out.println(CERVENA + "Nespravny vyber" + DEFAULT);
                    }
                }
                else{
                    System.out.println(CERVENA + "Nespravny vyber" + DEFAULT);
                }
            }
            else if(volba.equalsIgnoreCase("o") && (vypisovanieTextu.isOdlozeneODenKladnyPocet()||administrativa.getPocetOdlozenychUlohViacAkoDvaDni()>0)) {
                zoznamUloh.vypisUlohu(odlozeneOJedenDen);
                administrativa.setIteratorPosunuteODen(odlozeneOJedenDen);
                ovladacCyklu = true;
                System.out.println(MODRA + "\nP = Prevziat ulohu   D = Dalsia uloha" + DEFAULT);
                while (ovladacCyklu) {

                    volba = scanner.nextLine();

                    if(volba.equalsIgnoreCase("p")) {
                 //       administrativa.prevziatUlohuOdlozenaODen();
                        administrativa.prevziatUlohu(odlozeneOJedenDen);
                        System.out.println(MODRA + "\nV = Vyriesit ulohu" + DEFAULT);
                        volba = scanner.nextLine();
                        if(volba.equalsIgnoreCase("v")) {
                            administrativa.vyriesitUlohu(vyrieseneUlohy);
                        //    suborVyrieseneUlohy.zapisRiadok(zoznamUloh.getUloha());
                        }
                        ovladacCyklu = false;
                    }
                    else if(volba.equalsIgnoreCase("d")&& (administrativa.getpocetOdlozenychUloh()+administrativa.getPocetOdlozenychUlohViacAkoDvaDni())>administrativa.getIteratorPosunuteODenNextIndex()){
                        administrativa.iteratorDalsiaUloha();
                    }
                    else if(volba.equalsIgnoreCase("l") && administrativa.getStavMedziNextAPrevious()==1 && administrativa.getIteratorPosunuteODenPreviousIndex()>0 ||
                            (volba.equalsIgnoreCase("l") && administrativa.getStavMedziNextAPrevious()==0 && administrativa.getIteratorPosunuteODenPreviousIndex()>-1)){
                        administrativa.iteratorPredoslaUloha();
                    }
                    else{
                        System.out.println(administrativa.getStavMedziNextAPrevious());
                        System.out.println(CERVENA + "Nespravny vyber" + DEFAULT);
                    }
                }
            }
            else if(volba.equalsIgnoreCase("u")) {
            suborVyrieseneUlohy.zapisCelyZoznam(vyrieseneUlohy);
            suborOdlozeneODen.vytvorNovyCistySubor();
            suborOdlozeneODen.zapisCelyZoznam(odlozeneOJedenDen);
            suborNevyrieseneUlohy.spojiDveZoznamy(reklamaciaList,administrativaList);
            suborNevyrieseneUlohy.kopirujeSuborDoDalsiehoSuboru("Main_file");
            }
            else if(volba.equalsIgnoreCase("n")) {
            //    suborNevyrieseneUlohy.spojiDveZoznamy(reklamaciaList,administrativaList);
                System.out.println("Nakopiruj subor k suborom s ulohami...");
                System.out.println("Zadaj nazov suboru s novymi ulohami: ");
                volba = scanner.nextLine();

                suborNevyrieseneUlohy.setPlnyNazovSuboru(volba);
                suborNevyrieseneUlohy.kopirujeSuborDoDalsiehoSuboru("Main_file");


                /*
                try {
                    File subor = new File("Main_file.csv");
                    subor.delete();
                    Files.copy(new File(volba +".csv").toPath(),new File("Main_file.csv").toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                 */


                suborNevyrieseneUlohy.setPlnyNazovSuboru("Main_file");

                suborNevyrieseneUlohy.zapisCelyZoznam(reklamaciaList);
                suborNevyrieseneUlohy.zapisCelyZoznam(administrativaList);
                suborNevyrieseneUlohy.kopirujeSuborDoDalsiehoSuboru("Nevyriesene_Ulohy" + suborNevyrieseneUlohy.dnestnyDatumPreNazovSuboru());
                suborNevyrieseneUlohy.setPlnyNazovSuboru("Nevyriesene_Ulohy" + suborNevyrieseneUlohy.dnestnyDatumPreNazovSuboru());
                return;
            }
            else if(volba.equalsIgnoreCase("k")) {
                System.out.println("Prajete si skutocne ukoncit program? a/n");
                volba = scanner.nextLine();
                if(volba.equalsIgnoreCase("a")) {
                    System.exit(0);
                }
                else if (volba.equalsIgnoreCase("n")) {}
                else {
                    System.out.println("Nespravny vyber");
                }
            }
            else if(!vypisovanieTextu.isAdministrativaKladnyPocet()||
                    !vypisovanieTextu.isReklamaciaKladnyPocet()||
                    !vypisovanieTextu.isOdlozeneODenKladnyPocet()||
                    volba.equalsIgnoreCase("a")||
                    volba.equalsIgnoreCase("r")||
                    volba.equalsIgnoreCase("o")){
                System.out.println(POZADIEMODRE + "Zoznam zvolenej ulohy je prazdny... zvol iny typ ulohy alebo nacitaj dalsi subor s ulohami" + DEFAULT);
            }
             else {
                System.out.println(CERVENA + "Nespravny vyber" + DEFAULT);
            }
        }
    }
}
