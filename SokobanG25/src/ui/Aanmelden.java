/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author hilmiemrebayat
 */
public class Aanmelden {

    public void meldAan(DomeinController dc) {
        Scanner sc = new Scanner(System.in);

        String gebruikersnaam, wachtwoord;

        // Aanmelden
        System.out.printf(dc.getLabels().getString("geefGebruikersnaam"));
        gebruikersnaam = sc.next();
        System.out.printf(dc.getLabels().getString("geefWachtwoord"));
        wachtwoord = sc.next();
        dc.meldAan(gebruikersnaam, wachtwoord);
        System.out.printf(dc.getLabels().getString("succesAanmelden"));
        if (dc.isAdmin()) 
        {
            KeuzeMenuAdmin kza = new KeuzeMenuAdmin();
            kza.keuzeMenuAdmin(dc);
        } else {
            KeuzeMenu km = new KeuzeMenu();
            km.keuzeMenu(dc);
        }

    }
}
