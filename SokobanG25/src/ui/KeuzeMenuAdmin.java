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
                     SpeelSpel uc = new SpeelSpel(dc, dc.getLabels());
                     uc.SpeelSpel(dc, dc.getLabels());
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

    

}
