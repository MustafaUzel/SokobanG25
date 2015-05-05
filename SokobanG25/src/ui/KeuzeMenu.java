/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domein.DomeinController;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author hilmiemrebayat
 */
public class KeuzeMenu {

    Scanner s = new Scanner(System.in);
    DomeinController dc;

    public void keuzeMenu(DomeinController dc) {
        this.dc = dc;
        Scanner sc = new Scanner(System.in);

        // ----- Aangemeld gebruiker is GEEN admin -----
        System.out.printf(dc.getLabels().getString("menuNietAdmin"));
        int keuze = sc.nextInt();

        while (keuze != 1 || keuze != 2) {
            if (keuze == 1) {
                SpeelSpel(dc, dc.getLabels());

            } else if (keuze == 2) {
                System.out.printf(dc.getLabels().getString("stopApp"));
                System.exit(0);

            } else {
                System.out.printf(dc.getLabels().getString("fouteKeuze"));

            }
        }

    }

    public void SpeelSpel(DomeinController dc, ResourceBundle gekozenTaal) {
        SpeelSpel speelSpel = new SpeelSpel(dc, gekozenTaal);
        int level = 1;

        String gekozen = "";
        boolean spelKeuze;

        int keuze2 = 0;
        speelSpel.setLevel(level);

        do {

            toonKiesSpelMenu(gekozenTaal);
            gekozen = s.next();
            spelKeuze = dc.bestaatSpel(gekozen);
        } while (!spelKeuze);
        speelSpel.keuzeMenu(gekozen);
        dc.spelbordenToevoegen();
        do {
            speelSpel.setLevel(level);
            speelSpel.vulHetSpelbord();
            System.out.println(dc.spelbordObject());
            speelSpel(speelSpel);
            speelSpel.setSpelbordVoltooid();
            level++;
            speelSpel.toonVoltooideSpelborden();
            speelSpel.toonAlleSpelbordenInDitSpel();
            if (level <= dc.getMaxLevel()) {
                do {

                    System.out.println(gekozenTaal.getString("menuSpelVerder"));
                    keuze2 = s.nextInt();

                } while (keuze2 < 1 || keuze2 > 2);
            } else {
                keuze2 = 3;
                System.out.println(gekozenTaal.getString("spelVoltooid"));
            }

        } while (keuze2 == 1);

        if (keuze2 == 2) {
            System.exit(1);
        }

    }

    public void toonKiesSpelMenu(ResourceBundle gekozenTaal) {
        System.out.println(gekozenTaal.getString("menuSpel"));
        List<String> spellijst = dc.geefSpelMenuList();
        for (int i = 0; i < spellijst.size(); i++) {
            System.out.println(spellijst.get(i));
        }
        System.out.print(gekozenTaal.getString("keuze"));
    }

    public void speelSpel(SpeelSpel speelSpel) {
        SpeelSpel uc = speelSpel;
        uc.speelSpel();
    }
}
