/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Muhammet
 */
public class SpelMapper 
{
      public List<Spel> geefSpellen()
    {
        
        List<Spel> spellen = new ArrayList<Spel>();

        // create Statement for querying database
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM spel");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("naam");
                    spellen.add(new Spel(naam));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spellen;
        
    }
      
    public List<Spelbord> geefSpelborden()
    {
        List<Spelbord> spelborden= new ArrayList<>();

        // create Statement for querying database
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM spelbord");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    int idspelbord = rs.getInt("idspelbord");
                    
                    spelborden.add(new Spelbord(idspelbord));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelborden;
    }
    public void updateObject(int x,int y,int objectnr,int spelbordID)
    {
        /*
        UPDATE spelbordobjecten 
        SET objectnr = 0
        WHERE x = 0 AND y = 0 AND spelbord_idspelbord = 1
        */
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) 
        {
            /*String commando = "UPDATE spelbordobjecten SET objectnr =" + objectnr+
                    "WHERE x ="+x + "AND y = "+ y +"AND spelbord_idspelbord ="+spelbordID;*/
            
            PreparedStatement query = conn.prepareStatement("UPDATE spelbordobjecten SET objectnr = (?)"
                    + "WHERE x = (?) AND y = (?) AND spelbord_idspelbord = (?)");
             query.setInt(1, objectnr);
             query.setInt(2, x);
             query.setInt(3, y);
             query.setInt(4, spelbordID);
             
             query.executeUpdate();
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
            
        
    }
     
    public void voegSpelToe(Spel s) {

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO spel (naam)"
                    + "VALUES (?)");
            query.setString(1, s.getNaam());
            
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public void voegSpelbordEnObjectenToe(Spelbord sb,int Spelbordnr,String nm) 
    {
            SpelbordObject[][] objectenLijst = sb.getSpelbordOb();
            voegSpelbordToe(sb,nm);
            int spelbordid = sb.getID();
            List<SpelbordObject> spelbord = new ArrayList<>();
            spelbord = convertArrayNaarList(objectenLijst,spelbord);
                      
           /*
            ry (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO speler (naam, voornaam, gebruikersnaam, wachtwoord, isAdmin)"
                    + "VALUES (?, ?, ?, ?, ?)");
            */
           
           String Prep = "INSERT INTO spelbordobjecten (x, y, objectnr, isbezet, spelbord_idspelbord)";
           
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) 
        {
            for (int i = 0; i < spelbord.size(); i++) 
            { 
                PreparedStatement query = conn.prepareStatement(Prep
                    + "VALUES (?, ?, ?, ?, ?)");
            query.setInt(1, spelbord.get(i).getX());
            query.setInt(2, spelbord.get(i).getY());
            if(spelbord.get(i).toString().equals("GRAS"))
            {
                query.setInt(3, 0);
            }
            else if(spelbord.get(i).toString().equals("MUUR"))
            {
                 query.setInt(3, 1);
            }
            else if(spelbord.get(i).toString().equals("VELD"))
            {
                if(spelbord.get(i).isDoel()==false)
                {
                    if(spelbord.get(i).isKistGeplaatst()==true)
                    {
                        query.setInt(3, 4);
                    }
                    else
                        query.setInt(3, 2);
                }
                else if(spelbord.get(i).isDoel()==true)
                {
                    query.setInt(3, 3);
                }
                
                    
                 
            }
            
            
            query.setBoolean(4, spelbord.get(i).isBezet());
            
            
             if(spelbord.get(i).toString().equals("HERE"))
            {
                 query.setBoolean(4, true);
            }
             
             query.setInt(5, spelbordid);
             
             query.executeUpdate();
			//spelbord.get(i)
            }
           

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void voegSpelbordToe(Spelbord sp,String nm) {

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO spelbord (idspelbord, spel_naam)"
                    + "VALUES (?, ?)");
            query.setInt(1, sp.getID());
            query.setString(2, nm);

            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Spelbord geefSpelbord(int spelbordNr)
    {
        Spelbord sb;
        List<SpelbordObject> spelbord = geefSpelbordObjectenLijst(spelbordNr);
        int teller = 0;
        SpelbordObject[][] objectenLijst = new SpelbordObject[10][10];
        
        for(int i = 0;i< objectenLijst.length;i++)
        {
            for(int j= 0;j< objectenLijst[i].length;j++)
            {
                objectenLijst[i][j] = spelbord.get(teller);
                teller++;
            }
        }
        
        sb = new Spelbord(objectenLijst,spelbordNr);
        
        return sb;
    }
    
    public List<SpelbordObject> geefSpelbordObjectenLijst(int SpelbordNr) {
        
        
        
        
        List<SpelbordObject> spelbordobjecten = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            
            String Prep = "SELECT * FROM spelbordobjecten WHERE spelbord_idspelbord =" + SpelbordNr;
            PreparedStatement query = conn.prepareStatement(Prep);           
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next())  
                {
                    int x = rs.getInt("x");
                    int y = rs.getInt("y");                    
                    int objectnr = rs.getInt("objectnr");
                    boolean isBezet = rs.getBoolean("isbezet");
                    int spelbordid = rs.getInt("spelbord_idspelbord");
                    
                    if(objectnr==0)
                    {
                        spelbordobjecten.add(new Gras(x,y,isBezet));
                
                    }
                    else if(objectnr==1)
                    {
                        spelbordobjecten.add(new Muur(x,y,isBezet));
                
                    }
                    else if(objectnr==2)
                    {
                        spelbordobjecten.add(new Veld(x,y,false,isBezet));
                
                    }
                    else if(objectnr==3)
                    {
                        spelbordobjecten.add(new Veld(x,y,true,isBezet));
                
                    }
                    else if(objectnr==4)
                    {
                        Veld v = new Veld(x,y,false,isBezet);
                        v.setKistGeplaatst(true);
                        spelbordobjecten.add(v);
                
                    }
                    

                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelbordobjecten;
    }

    private List<SpelbordObject> convertArrayNaarList(SpelbordObject[][] objectenLijst, List<SpelbordObject> spelbord) 
    {
        SpelbordObject[][] sb = objectenLijst;
        List<SpelbordObject> list = spelbord;
        
        for(int i = 0;i< sb.length;i++)
        {
            for (int j = 0; j < sb[i].length; j++)
            {
                list.add(sb[i][j]);
            }
        }
        
        return list;
    }

    
    public List<Spelbord> geefAlleSpelbordenVanSpel(String naam) {
        List<Spelbord>lijst = new ArrayList<>();
        Spelbord sb = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM spelbord WHERE spel_naam = ?");
            query.setString(1, naam);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    int idspelbord = rs.getInt("idspelbord");
                                       
                    
                    

                    sb = new Spelbord(idspelbord);
                    lijst.add(sb);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return lijst;
    }

    public void updateSpelbord(Spelbord s) 
    {
        SpelbordObject[][] spelbord = s.getSpelbordOb();
        for(int i = 0;i<spelbord.length;i++)
        {
            for(int j = 0 ; j<spelbord[i].length;j++)
            {
                //updateObject(int x,int y,int objectnr,int spelbordID)
                int x = spelbord[i][j].getX();
                int y = spelbord[i][j].getY();
                int spelbordID = s.getID();
                int objectnr = 0;
                if(spelbord[i][j].toString().equals("VELD"))
                {

                        
                    if(spelbord[i][j].isKistGeplaatst())
                    {
                       
                        objectnr = 4;
                    }
                    else if(spelbord[i][j].isDoel())
                    {
                       objectnr = 3;
                    }
                    else
                    {
                        
                        objectnr = 2;
                    }
                        
                }

                else if(spelbord[i][j].toString().equals("MUUR"))
                {
                    objectnr = 1;
                }
                else if(spelbord[i][j].toString().equals("GRAS"))
                {
                    objectnr = 0;
                }
                updateObject(x,y,objectnr,spelbordID);
            }
        }
        
    }
    
}
