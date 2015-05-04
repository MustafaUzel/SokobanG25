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
public class KeuzeMenuAdmin {

    Scanner input = new Scanner(System.in);
    DomeinController dc;

    public void keuzeMenuAdmin(DomeinController dc) {
        this.dc = dc;
        Scanner sc = new Scanner(System.in);

        System.out.printf(dc.getLabels().getString("menuAdmin"));
        int keuze = sc.nextInt();
        if (keuze == 1 || keuze == 2 || keuze == 3 || keuze == 4) {
            switch (keuze) {
                case 1:
                    SpeelSpel(dc, dc.getLabels());
                    break;
                case 2:
                    System.out.printf(dc.getLabels().getString("nietGeimplementeerd"));
                    break;
                case 3:
                    System.out.printf(dc.getLabels().getString("nietGeimplementeerd"));
                    break;
                case 4:
                    System.out.printf(dc.getLabels().getString("stopApp"));
            }
        } else {
            System.out.printf(dc.getLabels().getString("fouteKeuze"));
            KeuzeMenuAdmin keuzeMenuAdmin = new KeuzeMenuAdmin();
            keuzeMenuAdmin.keuzeMenuAdmin(dc);
        }
    }

    public void SpeelSpel(DomeinController dc, ResourceBundle gekozenTaal) {
        SpeelSpel speelSpel = new SpeelSpel(dc, gekozenTaal);
        //uc3.startNieuwSpel();
        int level = 1;

        String gekozen = "";
        boolean spelkeuze;

        int keuze2 = 0;
        speelSpel.setLevel(level);

        do {

            toonKiesSpelMenu(gekozenTaal);
            gekozen = input.next();
            spelkeuze = dc.bestaatSpel(gekozen);
        } while (!spelkeuze);
        speelSpel.keuzeMenu(gekozen);
        dc.spelbordenToevoegen();
        do {
            speelSpel.setLevel(level);
            speelSpel.vulHetSpelbord();
            System.out.println(dc.spelbordObject());
            speelSpel.speelSpel();
            speelSpel.setSpelbordVoltooid();
            level++;
            speelSpel.toonVoltooideSpelborden();
            speelSpel.toonAlleSpelbordenInDitSpel();
            if (level <= dc.getMaxLevel()) {
                do {

                    System.out.println(gekozenTaal.getString("menuSpelVerder"));
                    keuze2 = input.nextInt();

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

}
