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
                SpeelSpel uc = new SpeelSpel(dc, dc.getLabels());
                uc.SpeelSpel(dc, dc.getLabels());

            } else if (keuze == 2) {
                System.out.printf(dc.getLabels().getString("stopApp"));
                System.exit(0);

            } else {
                System.out.printf(dc.getLabels().getString("fouteKeuze"));

            }
        }

    }
  }

