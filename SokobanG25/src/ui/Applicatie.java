/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.Registreren;
import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Muhammet
 */
public class Applicatie {

    public void start(DomeinController dc) 
    {
        Scanner input = new Scanner(System.in);
        int keuze = 1;
        boolean fouteInvoer = true;
        boolean fouteTaalInvoer = true;
        boolean fouteAanmeldGegevens = true;
        boolean fouteRegistratie = true;
        int taalkeuze;

        //Taalkeuze
        do {
            try {
                System.out.printf("Kies uw taal A.U.B.:%n%n1. Engels%n2. Nederlands%n3. Frans%n%nUw keuze is: ");
                taalkeuze = input.nextInt();
                if (taalkeuze < 1 || taalkeuze > 3) {
                    throw new IllegalArgumentException("Verkeerde keuze! Probeer opnieuw!");
                }
                fouteTaalInvoer = false;
                dc.setLocale(taalkeuze);
                
            } catch (IllegalArgumentException e) {
                System.out.printf("%n%s%n%n", e.getMessage());
            } catch (InputMismatchException e) {
                System.out.printf("%n%s%n%n", "Verkeerde keuze! Probeer opnieuw!");
                input.next();
            }
        } while (fouteTaalInvoer);

        do {
            try {
                System.out.printf(dc.getLabels().getString("startMenu"));
                keuze = input.nextInt();
                if (keuze < 1 || keuze > 3) {
                    throw new IllegalArgumentException(dc.getLabels().getString("fouteKeuze"));
                }
                fouteInvoer = false;
            } catch (IllegalArgumentException e) {
                System.out.printf("%n%s%n%n", e.getMessage());
            } catch (InputMismatchException e) {
                System.out.printf("%n%s%n%n", dc.getLabels().getString("fouteKeuze"));
                input.next();
            }
        } while (fouteInvoer);

        do {
            try {
                if (keuze == 1) {
                    Aanmelden aanmeld = new Aanmelden();
                    aanmeld.meldAan(dc);
                    fouteAanmeldGegevens = false;
                } else if (keuze == 2) {
                    Registreren.registreer(dc);
                    fouteRegistratie = false;
                } else if (keuze == 3) {
                    System.out.printf("%n%s%n%n", dc.getLabels().getString("stopApp"));
                    System.exit(0);
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.printf("%n%s%n%n", e.getMessage());
            } catch (NullPointerException n) {
                System.out.printf("%n%s%n%n", dc.getLabels().getString("error"));
            } 
        } while (fouteAanmeldGegevens && fouteRegistratie);
    }
}
