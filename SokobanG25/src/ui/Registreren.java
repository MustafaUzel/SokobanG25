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
public class Registreren {

    public static void registreer(DomeinController dc) {

        String naam, voornaam, gebruikersnaam, wachtwoord;
        Scanner sc = new Scanner(System.in);            
        System.out.printf(dc.getLabels().getString("naam"));
        naam = sc.nextLine();
        System.out.printf(dc.getLabels().getString("voornaam"));
        voornaam = sc.nextLine();
        
        try {
            do {
                System.out.printf(dc.getLabels().getString("gebruikersnaam"));
                gebruikersnaam = sc.nextLine();
                System.out.printf(dc.getLabels().getString("wachtwoord"));
                wachtwoord = sc.nextLine();
            } while (!dc.valideerRegistratie(gebruikersnaam,wachtwoord));

            dc.registreer(naam, voornaam, gebruikersnaam, wachtwoord);
            System.out.printf(dc.getLabels().getString("succesRegistratie"));
            
//            dc.meldAan(gebruikersnaam, wachtwoord);
            
            // ----- MENU -----
//            if (dc.isAdmin()) {
//                KeuzeMenuAdmin.keuzeMenuAdmin(dc);
//            } else {
//                KeuzeMenu.keuzeMenu(dc);
//            }
        } catch (IllegalArgumentException e) {
            System.err.printf(dc.getLabels().getString("probleemWachtwoordGebruikersnaam"));
            throw new IllegalArgumentException(e);
        }
    }
}
