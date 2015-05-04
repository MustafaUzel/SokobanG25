package persistentie;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domein.Speler;

public class SpelerMapper
{

       public List<Speler> geefSpelers()
    {
        
        List<Speler> spelers = new ArrayList<Speler>();
/*
        //code zonder databank 
        Speler speler = new Speler("Janssens", "Jan", "jan.janssens@hogent.be", new GregorianCalendar(1995,2,15),"aaa123");
        spelerLijst.add(speler);
        speler = new Speler("Pieters", "Piet", "piet.pieters@hogent.be", new GregorianCalendar(1990,5,7),"bbb123");
        spelerLijst.add(speler);
        return spelerLijst;
        */
             
        // create Statement for querying database
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM speler");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");
                    String voornaam = rs.getString("voornaam");
                    String gebruikersnaam = rs.getString("gebruikersnaam");
                    String wachtwoord = rs.getString("wachtwoord");
                    boolean administrator = rs.getBoolean("isadmin");
                    

                    spelers.add(new Speler(naam, voornaam, gebruikersnaam, wachtwoord, administrator));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelers;
        
    }

    public void voegSpelerToe(Speler speler) 
    {

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO speler (naam, voornaam, gebruikersnaam, wachtwoord, administrator)"
                    + "VALUES (?, ?, ?, ?, ?)");
            query.setString(1, speler.getNaam());
            query.setString(2, speler.getVoornaam());
            query.setString(3, speler.getGebruikersnaam());
            query.setString(4, speler.getWachtwoord());
            query.setBoolean(5, speler.isAdminrechten());
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM speler WHERE gebruikersnaam = ?");
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String naam = rs.getString("naam");
                    String voornaam = rs.getString("voornaam");                    
                    String wachtwoord = rs.getString("wachtwoord");
                    boolean administrator = rs.getBoolean("isadmin");
                    

                    speler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord, administrator);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }
}