package ui;

import domein.DomeinController;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SpeelSpel {

    private DomeinController domeinController;
    Scanner input = new Scanner(System.in);
    int aantalverplaatsingen = 0;

    public SpeelSpel(DomeinController domeinController, ResourceBundle gekozenTaal) {
        setDomeinController(domeinController);
    }

    private void setDomeinController(DomeinController dc) {
        this.domeinController = dc;
    }

    public void setLevel(int niveau) {
        domeinController.setLevel(niveau);
    }

    public void vulHetSpelbord() {
        domeinController.vulHetSpelbord();
    }

    public void keuzeMenu(String keuze) {
        domeinController.setSpel(domeinController.geefSpel(keuze));

    }

    public void speelSpel() {
        boolean spelVoltooid;
        do {
            int[] startPos = domeinController.geefStartPositie();
            int startX = startPos[0];
            int startY = startPos[1];
            int eindX;
            int eindY;
            int[] einde;
            boolean magIk;

            List<String> verplaatsLijstje = domeinController.geefVerplaatsLijstjeGui();
            do {

                einde = toonVerplaatsMenu(startX, startY, verplaatsLijstje);
                if (einde[0] == 10 || einde[1] == 10) {
                    System.exit(1);
                }
                eindX = einde[0];
                eindY = einde[1];

                magIk = domeinController.toegang(eindX, eindY);

            } while (!magIk);

            DR_VERPLAATS(startX, startY, eindX, eindY);
            aantalverplaatsingen++;
            System.out.print(domeinController.getLabels().getString("my.verplaatsY") + aantalverplaatsingen);
            // na keuze opnieuw spelbord tonen
            System.out.println(domeinController.spelbordObject());
            spelVoltooid = domeinController.isSpelbordVoltooid();
            // WHILE ( DR_VoltooiSpelbord )
        } while (!spelVoltooid);

    }

    public int[] toonVerplaatsMenu(int x, int y, List<String> verplaatsLijstje) {
        int[] eind = new int[2];
        int keuzex = 6;
        int keuzey = 6;
        try {

            System.out.print(domeinController.getLabels().getString("my.startpositie") + x + domeinController.getLabels().getString("my.y") + y + " \n");
            for (int i = 0; i < verplaatsLijstje.size(); i++) {
                System.out.print(verplaatsLijstje.get(i));
                if (verplaatsLijstje.get(i).equals("boven")) {
                    System.out.print(" X:" + (x - 1) + " Y: " + y + "\n");
                } else if (verplaatsLijstje.get(i).equals("onder")) {
                    System.out.print(" X:" + (x + 1) + " Y: " + y + "\n");
                } else if (verplaatsLijstje.get(i).equals("links")) {
                    System.out.print(" X:" + (x) + " Y: " + (y - 1) + "\n");
                } else if (verplaatsLijstje.get(i).equals("rechts")) {
                    System.out.print(" X:" + (x) + " Y: " + (y + 1) + "\n");
                }
            }
            System.out.print(domeinController.getLabels().getString("stopSpel"));
            System.out.print("\nX:");
            keuzex = input.nextInt();
            if (keuzex != 10) {
                System.out.print("\nY:");
                keuzey = input.nextInt();
            } if (keuzex == 10){
                System.out.println(domeinController.getLabels().getString("stopApp"));
            }

        } catch (Exception e) {
            System.out.println("Foute invoer! Probeer opnieuw!");
            this.input.nextLine(); // Clear buffer
        }
        eind[0] = keuzex;
        eind[1] = keuzey;

        return eind;
    }

    public void DR_VERPLAATS(int startX, int startY, int eindplaatsX, int eindplaatsY) {
        /*
         Bij het verplaatsen verschuift het mannetje 1 vakje.
         Mogelijke richtingen om te verplaatsen: links, rechts, boven, onder.
         Opgelet:
         - Het mannetje kan niet op een muur geplaatst worden.
         - Het mannetje kan niet op een kist geplaatst worden. Het mannetje verschuift de kist 1 vakje
         in dezelfde richting van de verplaatsing indien mogelijk. Die kist kan niet geplaatst worden op
         een muur of een andere kist. Naast de kist moet dus een vrije plaats zijn.
         */
        domeinController.verplaats(startX, startY, eindplaatsX, eindplaatsY);

    }

    public void toonVoltooideSpelborden() {
        System.out.println(domeinController.toonVoltooideSpelborden());
    }

    public void toonAlleSpelbordenInDitSpel() {
        System.out.println(domeinController.getLabels().getString("spelVoltooid"));
        System.out.println(domeinController.toonAlleSpelbordenInDitSpel());

    }

    public void setSpelbordVoltooid() {
        domeinController.setHuidigSpelbordAlsVoltooid();
    }

}
