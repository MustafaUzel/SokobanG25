/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
import ui.Applicatie;

/**
 *
 * @author hilmiemrebayat
 */
public class StartUp
{
    public static void main(String[] args)
    {

        DomeinController dc = new DomeinController();
        Applicatie applicatie = new Applicatie();
        applicatie.start(dc);
    }
}
