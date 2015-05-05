/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Muhammet
 */
public class Spel 
{
    
    private String naam; // unieke spelnaam
    private Spelbord sb; // een speler kan maar 1 spelbord spelen op een bepaald moment.
    private final List<Spelbord> spelborden = new ArrayList<>(); // elk spel heeft x - aantal spelborden
    int [][] posities; // array met posities ( x , y ) waar de speler zich naartoe beweegt
    ResourceBundle resource; // huidige taal ( resourcebundle )
    public Spel(String nm)      
    {
        setNaam(nm);
        
        
    }
    
    // geeft het huidige spelbord terug in een 2D int-array
    public int[][] geefSpelbordInt() 
    {
        return this.sb.geefSpelbordInt();
        
    }
    // maakt adhv een 2D int-array een spelbord aan en geeft hem terug
    // x en y zijn de parameters voor de startpositie in het spelbord
    // id is het unieke int van een spelbord
    
    private Spelbord maakSpelbordAdhvInt(int [][] jwz,int x, int y,int id)    {
        int [][] op = jwz;
        
        SpelbordObject[][] spelbordOb  = new SpelbordObject[10][10];

        for(int i=0;i<op.length;i++)
        {
            for(int j=0;j<op[i].length;j++)
            {
                if(op[i][j]==0)
                {
                    spelbordOb[i][j] = new Gras(i,j);
                }
                else if(op[i][j]==1)
                {
                    spelbordOb[i][j] = new Muur(i,j);
                }
                else if(op[i][j]==2)
                {
                    spelbordOb[i][j] = new Veld(i,j,false);
                }
                else if(op[i][j]==3)
                {
                    spelbordOb[i][j] = new Veld(i,j,true);
                }
                else if(op[i][j]==4)
                {
                    Veld v = new Veld(i,j,false);
                    v.setKistGeplaatst(true);
                    spelbordOb[i][j] = v;
                }
            }
        }
        
        spelbordOb[x][y].setBezet(true);
        Spelbord temp;
        temp = new Spelbord(spelbordOb,id);
        temp.setStartX(x);
        temp.setStartY(y);
        
        return temp;
    }
    
    // geeft een lijst terug met alle spelborden in dit spel
    public List<Spelbord> geefSpelborden()
    {
        return this.spelborden;
    }
    
    // stelt huidige spelbord in
    public void setSpelbord(Spelbord ssb)    
    {
        
        sb = ssb;
    }
    
    // geeft String van de voltooide spelborden in dit spel terug
     public String geefVoltooideSpelborden()
    {
        String output = "";
        for(int i =0;i<spelborden.size();i++)
        {
            if(spelborden.get(i).isVoltooid())
            {
                output+= "\nNiveau " + (i+1);
            }
            
        }
        
        return output + "\n";
        
    }
     // geeft List string van de voltooide spelborden in dit spel terug
     public List<String> geefAlleVoltooideSpelborden()
     {
         List<String> lijst = new ArrayList<>();
        for(int i =0;i<spelborden.size();i++)
        {
            if(spelborden.get(i).isVoltooid())
            {
                lijst.add("Niveau " + (i+1));
            }
            
        }
        
        return lijst;
         
     }
     
     // geeft list string van alle spelborden terug
     public List<String> geefAlleSpelbordenGUI()
     {
         List<String> lijst = new ArrayList<>();
        for(int i =0;i<spelborden.size();i++)
        {      
                lijst.add("Niveau " + (i+1));
        }
        
        return lijst;
         
     }
     
     // dit is de toString van spelbord
    public String spelbordObject()    
    {
        return sb.toonSpelbordMetOb();
    }
    

    // geeft naam v/h spel terug
    public String getNaam() {
        return naam;
    }
    
    // kijkt of huidig spelbord voltooid is
    // dit is een ingewikkelde methode die in het begin werd gemaakt en sindsdien niet vereenvoudigt
    // KAN VEREENVOUDIGT WORDEN !
    public boolean isSpelbordVoltooid()
    {
        SpelbordObject[][] spelbord = sb.getSpelbordOb();
        int[][]doelen = new int[sb.getAantalDoelen()][2];
        int plaats = 0;
        
        boolean appelflap = false;
        int teller = 0;
        int temp;
        
        for(int i=0;i<spelbord.length;i++)
        {
            for(int j=0;j<spelbord[i].length;j++)
            {
                if(spelbord[i][j].toString().equals("VELD") && spelbord[i][j].isDoel())
                {
                    doelen[plaats][0] = i;
                    doelen[plaats][1] = j;
                    plaats++;
                    
                    teller++;
                    
                }
            }
        }
        temp = KijkOfElkeDoelEenKistHeeft(doelen);
        //appelflap = kijkOfZeAllemaalKistenHebben(doelen);
        if(temp==teller)
        {
            appelflap = true;
        }
        
        return appelflap;
        
    }
    
   // geeft huidig spelbord terug
    public Spelbord getSb() {
        return sb;
    }
    

    
    private void setNaam(String naam) {
        this.naam = naam;
    }
   

    
    public void setSb(Spelbord sb) {
        this.sb = sb;
    }

    // geeft array terug met X en Y positie van startpositie
    public int[] geefStartPositie() 
    {
        SpelbordObject[][] spelbord = sb.getSpelbordOb();
        int[] info = new int[2];
        for(int i= 0;i<spelbord.length;i++)
        {
            for(int j = 0;j<spelbord[i].length;j++)
            {
                if(spelbord[i][j].isBezet())
                {
                    info[0]=i;
                    info[1]=j;
                }
            }
        }
        return info;
     /*
        int[]jwz = new int[2];
        jwz[0] = sb.getStartX();
        jwz[1] = sb.getStartY();
        
        return jwz;
             */
    }

   // geeft een List string met verplaatsopties voor de speler
      public List<String> geefVerplaatsLijstj(ResourceBundle rb) 
    {
        this.resource=rb;
        
        List<String>lijstje = new ArrayList<>();
       int[] startp = geefStartPositie();
       SpelbordObject[][] spelbord = sb.getSpelbordOb();
       int startX = startp[0];
       int startY = startp[1];
       String verplaats = resource.getString("my.lijstje");
       int pos[][]=new int[5][];
        pos[0]=new int[2];
        pos[1]=new int[2];
        pos[2]=new int[2];
        pos[3]=new int[2];
        pos[4]=new int[1];
        boolean inRange = true;
       /*
        output += " startpositie" + multi[startX][startY];
        output += " boven" + multi[startX-1][startY];
        output += " onder" + multi[startX+1][startY];
        output += " links" + multi[startX][startY-1];
        output += " rechts" + multi[startX][startY+1];
       */
       
        if((startX-1)<10 && (startX-1)> -1) // bove in range
        {
          if(spelbord[startX-1][startY].magIkDaar() ) // boven
            {
                if(spelbord[startX-1][startY].isKistGeplaatst())
                {
                     if((startX-2)<10 && (startX-2)> -1) // buite kist in range
                     {
                         if(spelbord[startX-2][startY].magIkDaar())
                            {
                                if(!spelbord[startX-2][startY].isKistGeplaatst())
                                {
                                    verplaats += " x: "+(startX-1)+ " y: "+(startY)+resource.getString("my.boven");
                                    pos[0][0] = startX-1;
                                    pos[0][1] = startY;
                                    lijstje.add("boven");
                                }

                            }
                     }
                    
                    

                }
                else
                {
                    verplaats += " x: "+(startX-1)+ " y: "+(startY)+resource.getString("my.boven");
                    pos[0][0] = startX-1;
                    pos[0][1] = startY;
                    lijstje.add("boven");
                }

            }  
        }
        if((startX+1)<10 && (startX+1)> -1) // onder in range
        {
          if(spelbord[startX+1][startY].magIkDaar()) // onder
          {
              if(spelbord[startX+1][startY].isKistGeplaatst())
              {
                  if((startX+2)<10 && (startX+2)> -1) // buite kist in range
                  {
                    if(spelbord[startX+2][startY].magIkDaar())
                    {
                        if(!spelbord[startX+2][startY].isKistGeplaatst())
                        {
                            verplaats += " x: "+(startX+1)+ " y: "+(startY)+resource.getString("my.onder");
                            pos[1][0] = startX+1;
                            pos[1][1] = startY;
                            lijstje.add("onder");
                        }

                    }  
                  }
                  

              }
              else
              {
                  verplaats += " x: "+(startX+1)+ " y: "+(startY)+resource.getString("my.onder");
                  pos[1][0] = startX+1;
                  pos[1][1] = startY;
                  lijstje.add("onder");
              }

          }  
        }
        if((startY-1)<10 && (startY-1)> -1) // links in range
        {
           if(spelbord[startX][startY-1].magIkDaar()) // links
            {
                if(spelbord[startX][startY-1].isKistGeplaatst())
                {
                    if((startY-2)<10 && (startY-2)> -1) // buite kist in range
                    {
                        if(spelbord[startX][startY-2].magIkDaar())
                        {
                            if(!spelbord[startX][startY-2].isKistGeplaatst())
                            {
                                verplaats += " x: "+(startX)+ " y: "+(startY-1)+resource.getString("my.links");
                                pos[2][0] = startX;
                                pos[2][1] = startY-1;
                                lijstje.add("links");
                            }

                        }
                    }
                    

                }
                else
                {
                    verplaats += " x: "+(startX)+ " y: "+(startY-1)+resource.getString("my.links");
                    pos[2][0] = startX;
                    pos[2][1] = startY-1;
                    lijstje.add("links");
                }

            } 
        }
        if((startY+1)<10 && (startY+1)> -1) // rechts in range
        {
          if(spelbord[startX][startY+1].magIkDaar()) // rechts
            {
                if(spelbord[startX][startY+1].isKistGeplaatst())
                {
                    if((startY+2)<10 && (startY+2)> -1) 
                    {
                        if(spelbord[startX][startY+2].magIkDaar())
                        {
                            if(!spelbord[startX][startY+2].isKistGeplaatst())
                            {
                                verplaats += " x: "+(startX)+ " y: "+(startY+1)+resource.getString("my.rechts");
                                pos[3][0] = startX;
                                pos[3][1] = startY+1;
                                lijstje.add("rechts");
                            }

                        }
                    }

                    


                }
                else
                {
                    verplaats += " x: "+(startX)+ " y: "+(startY+1)+resource.getString("my.rechts");
                    pos[3][0] = startX;
                    pos[3][1] = startY+1;
                    lijstje.add("rechts");
                }

            }  
        }
        
            verplaats += "\n";
            pos[4][0] = 10;
            /////////////./////////kisten controleren////////////////////////////////////
           
        
       
       
       setPosities(pos);
       return lijstje;
    }
    


    public boolean magIkDaarNaartoe(int eindX, int eindY) 
    {

        boolean toegang = false;
        
        if(posities[0][0]== eindX && posities[0][1]== eindY )
        {
            toegang = true;
        }
        else if(posities[1][0]== eindX && posities[1][1]== eindY )
        {
            toegang = true;
        }
        else if(posities[2][0]== eindX && posities[2][1]== eindY )
        {
            toegang = true;
        }
        else if(posities[3][0]== eindX && posities[3][1]== eindY )
        {
            toegang = true;
        }
        
        return toegang;
        
    }

    public int[][] getPosities() {
        return posities;
    }

    public void setPosities(int[][] posities) {
        this.posities = posities;
    }

    // zie domeinregels verplaats ( past deze toe)
    
    public void verplaats(int startX, int startY, int eindplaatsX, int eindplaatsY) 
    { // 5,5 -> 5,6
       /*
        output += " boven" + multi[startX-1][startY];
        output += " onder" + multi[startX+1][startY];
        output += " links" + multi[startX][startY-1];
        output += " rechts" + multi[startX][startY+1];
        */
        
       SpelbordObject[][] spelbord = sb.getSpelbordOb();
       spelbord[startX][startY].setBezet(false);
       if(spelbord[eindplaatsX][eindplaatsY].isKistGeplaatst())
       {
           if(eindplaatsX==(startX-1))
            { // if boven
               spelbord[eindplaatsX][eindplaatsY].setBezet(true);
               spelbord[eindplaatsX][eindplaatsY].setKistGeplaatst(false);
               spelbord[eindplaatsX-1][eindplaatsY].setKistGeplaatst(true);
                        // public Kist(int x,int y,boolean isBezet)
                        // public Veld(int x,int y,boolean doel,boolean isBezet)

            }
            else if(eindplaatsX==(startX+1))
            { // if onder
               spelbord[eindplaatsX][eindplaatsY].setBezet(true);
               spelbord[eindplaatsX][eindplaatsY].setKistGeplaatst(false);
               spelbord[eindplaatsX+1][eindplaatsY].setKistGeplaatst(true);

            }
            else if(eindplaatsY==(startY-1))
            { // if links
               spelbord[eindplaatsX][eindplaatsY].setBezet(true);
               spelbord[eindplaatsX][eindplaatsY].setKistGeplaatst(false);
               spelbord[eindplaatsX][eindplaatsY-1].setKistGeplaatst(true);
            }
            else if(eindplaatsY==(startY+1))
            { // if rechts
               spelbord[eindplaatsX][eindplaatsY].setBezet(true);
               spelbord[eindplaatsX][eindplaatsY].setKistGeplaatst(false);
               spelbord[eindplaatsX][eindplaatsY+1].setKistGeplaatst(true);
            }
           
       }
       else
           spelbord[eindplaatsX][eindplaatsY].setBezet(true);
           
       
       
        
    }

   
    private int KijkOfElkeDoelEenKistHeeft(int[][] doelen) 
    {
         int te = 0;
         int x=0,y=0;
        SpelbordObject[][] spelbord = sb.getSpelbordOb();
        // 0,0 -> 3   0,1  -> 2
        for(int i = 0;i<doelen.length;i++)
        {
            for(int j = 0;j<doelen[i].length;j++)
            {
                if(j==0)
                {
                   x = doelen[i][j];  
                }
                else
                    y = doelen[i][j];
               
                
                
                
                
                
                
            }
            if(spelbord[x][y].isKistGeplaatst())
            {
                te++;
            }
            
        }
        
        
        
        return te;
        
        }

    public void setHuidigSpelbordAlsVoltooid(int x)
    {
        sb.setVoltooid(true);
        spelborden.get(x-1).setVoltooid(true);
    }
    public String toonAlleSpelborden()
    {
        String output = "\n----------------------";
       for(int i =0;i<spelborden.size();i++)
        {
            output+= "\nNiveau " + (i+1);
        }
        
        
        return output;
    }

    void voegSpelbordToeAanSpel(Spelbord sb) 
    {
        spelborden.add(sb);
        
        
    }

    public Spelbord stelHuidigSpelbordInEnGeefSpelbordTerug(int[][] appelflap,int x,int y,int nr) 
    {
        Spelbord jijNietWeet;
        jijNietWeet = maakSpelbordAdhvInt(appelflap,x,y,nr);
        setSb(jijNietWeet);
        return jijNietWeet;
        
    }

    public boolean MagIkDaarNaarToeInGemaakteSpelbord(int x, int y) 
    {
        int sx = x;
        int sy = y;
        boolean toegang = false;
        SpelbordObject[][] spelbord = sb.getSpelbordOb();
        if(spelbord[x][y].magIkDaar())
        {
            toegang = true;
        }
        
        return toegang;
   }

    public void fixDieHandelEffe(int[][] appelflap,int nr) 
    {
        Spelbord jijNietWeet = maakSpelbordAdhvInt(appelflap,5,5,nr);
        setSb(jijNietWeet); 
    }

    public boolean isAfgewerkt(int[][] spelbord) 
    {
        boolean isAfgewerkt =true;
        for(int i=0;i<spelbord.length;i++)
        {
            for(int j=0;j<spelbord[i].length;j++)
            {
                if(spelbord[i][j]==10)
                {   
                    isAfgewerkt= false;
                }
            }
        }
        return isAfgewerkt;
    }

    public boolean MagIkDaarNaarToeInTemporarySpelbordINT(int[][] spelbord, int x, int y) 
    {
        boolean appelflap = false;
        if(spelbord[x][y]>1 && spelbord[x][y]<4)
        {
            appelflap = true;
        }
        return appelflap;
    }

    public String geefObjectStringAdhvPositie(int x, int y)
    {
        SpelbordObject[][] spelbord = sb.getSpelbordOb();
        String tekst ="";
                if(spelbord[x][y].isBezet())
                {
                    tekst= "HERE";
                    
                }
                else
                {
            switch (spelbord[x][y].toString()) {
                case "VELD":
                    if(spelbord[x][y].isDoel() && spelbord[x][y].isKistGeplaatst())
                    {
                        tekst = "GOAL";
                    }
                    else
                    {
                        if(spelbord[x][y].isKistGeplaatst())
                        {
                            tekst = "KIST";
                        }
                        else if(spelbord[x][y].isDoel())
                        {
                            tekst = "DOEL";
                        }
                        else
                        {
                            tekst = "VELD";
                        }
                    }
                    break;
                case "GRAS":
                    tekst = "GRAS";
                    break;
                case "MUUR":
                    tekst = "MUUR";
                    break;
            }
            
            
                
                }
                
                return tekst;
        
        
    }

    public int geefHuidigSpelbordID() 
    {
        return this.sb.getID();
    }
    
    
    
}
