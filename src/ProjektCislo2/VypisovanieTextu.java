package ProjektCislo2;

public class VypisovanieTextu implements Farba {

    private String reklamacia = "R = Reklamacia";
    private String odlozene = "O = Odlozene";
    private String koniec= "K = Koniec a uloz";
    private boolean administrativaKladnyPocet;
    private boolean reklamaciaKladnyPocet;
    private boolean odlozeneODenKladnyPocet;

    public void VypisovanieTextu() {
    }

    public void hlavneMenu(int andministrativaUlohaPocet, int reklamaciaUlohaPocet, int odlozeneUlohyPocet,int odlozeneUlohyViacdni) {
        System.out.println("Dalsia uloha: "+ MODRA);
       if(andministrativaUlohaPocet>0) {
           System.out.print("A = Administrativa    ");
           administrativaKladnyPocet = true;
       }
       else {
           administrativaKladnyPocet = false;
       }

       if(reklamaciaUlohaPocet>0) {
           System.out.print("R = Reklamacia    ");
           reklamaciaKladnyPocet = true;
       }
       else {
           reklamaciaKladnyPocet = false;
       }
       if(odlozeneUlohyPocet>0||odlozeneUlohyViacdni>0) {
           System.out.print("O = Odlozene ulohy    ");
           odlozeneODenKladnyPocet = true;
       }
       else{
           odlozeneODenKladnyPocet = false;
       }
        System.out.println("U = Ulozit zoznamy    N = Vloz nove ulohy do zoznamu    K = Koniec" + DEFAULT);
        }

    public boolean isAdministrativaKladnyPocet() {
        return administrativaKladnyPocet;
    }
    public boolean isReklamaciaKladnyPocet() {
        return reklamaciaKladnyPocet;
    }
    public boolean isOdlozeneODenKladnyPocet() {
        return odlozeneODenKladnyPocet;
    }
}


