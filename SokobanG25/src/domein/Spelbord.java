/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Muhammet
 */
public class Spelbord 
{
    private int startX;
    private int startY;
    
    boolean voltooid;
    int spelbordID;
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    
    
    SpelbordObject[][] spelbordOb = new SpelbordObject[10][10];
    
    public Spelbord()     {
        
    }
     public Spelbord(int sd)     
     {
         
         setID(sd);
        
    }
     
     private void setID(int x)
     {
         this.spelbordID = x;
     }
     
     public int getID()
     {
         return this.spelbordID;
     }
   
    
    public void setVoltooid(boolean b)
    {
        this.voltooid = b;
    }
    
    public boolean isVoltooid()
    {
        return this.voltooid;
    }

    
    public Spelbord(SpelbordObject[][] objecten,int id)    {
        setSpelbordOb(objecten);
        setID(id);
    }
    
    
    public int[][] geefSpelbordInt()    
    {
        int[][] array = new int[10][10];
        /*
        ObjectenLijst.add(leeg);
        ObjectenLijst.add(muur);
        ObjectenLijst.add(veld);
        ObjectenLijst.add(doel);
        ObjectenLijst.add(kist);
        ObjectenLijst.add(start);
        */
  
        
        for(int i = 0;i<spelbordOb.length;i++)
        {
            
            for(int j = 0;j<spelbordOb[i].length;j++)
            {
                if(spelbordOb[i][j].isBezet())
                {
                    
                    array[i][j] = 5;
                    
                }
                else
                {
                    if(spelbordOb[i][j].toString().equals("VELD"))
                    {
                        if(spelbordOb[i][j].isDoel() && spelbordOb[i][j].isKistGeplaatst())
                        {
                            
                        }
                        else
                        {
                                if(spelbordOb[i][j].isKistGeplaatst())
                            {
                                
                                array[i][j] = 4;
                            }
                            else if(spelbordOb[i][j].isDoel())
                            {
                                
                                array[i][j] = 3;
                            }
                            else
                            {
                                
                                array[i][j] = 2;
                            }
                        }
                        
                        
                            

                    }                    
                    else if(spelbordOb[i][j].toString().equals("GRAS"))
                    {
                        
                        array[i][j] = 0;
                    }
                    else if(spelbordOb[i][j].toString().equals("MUUR"))
                    {
                        
                        array[i][j] = 1;
                        
                    }
                        
                
                
                }
                
                
            }
           
            
        }
        
        return array;
     }
    public String toonSpelbordMetOb()    {
        String output ="\n"+ 
                "-------------------------------------"
                + "--------------------------------\n"
                    ;
        
        for(int i = 0;i<spelbordOb.length;i++)
        {
            
            for(int j = 0;j<spelbordOb[i].length;j++)
            {
                if(spelbordOb[i][j].isBezet())
                {
                    output+= ANSI_BLUE + "HERE" + ANSI_RESET;
                    
                }
                else
                {
                    switch (spelbordOb[i][j].toString()) {
                        case "VELD":
                            if(spelbordOb[i][j].isDoel() && spelbordOb[i][j].isKistGeplaatst())
                            {
                                output+= ANSI_YELLOW + ("GOAL") + ANSI_RESET;
                            }
                            else
                            {
                                if(spelbordOb[i][j].isKistGeplaatst())
                                {
                                    output+= ANSI_PURPLE + ("KIST") + ANSI_RESET;
                                }
                                else if(spelbordOb[i][j].isDoel())
                                {
                                    output+= ANSI_RED + "DOEL" + ANSI_RESET;
                                }
                                else
                                {
                                    output+= (spelbordOb[i][j].toString());
                                }
                            }   break;
                        case "GRAS":
                            output+= ANSI_GREEN + (spelbordOb[i][j].toString()) + ANSI_RESET;
                            break;
                        case "MUUR":
                            output+= ANSI_CYAN +(spelbordOb[i][j].toString()) + ANSI_RESET;
                            break;
                    }
                        
                
                
                }
                output+= " | ";
                
            }
            output+= "\n"
                    + "----------------------------------"
                    + "-----------------------------------"
                    + "\n";
            
        }
        
        return output;
     }
    
    public SpelbordObject[][] getSpelbordOb() 
    {
        return spelbordOb;
    }
   
    private void setSpelbordOb(SpelbordObject[][] spelbordOb) {
        this.spelbordOb = spelbordOb;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getAantalDoelen() 
    {
        int aantaldoelen = 0;
        for(int i = 0;i<spelbordOb.length;i++)
        {
            for(int j = 0;j<spelbordOb[i].length;j++)
            {
                if(spelbordOb[i][j].toString().equals("VELD") && spelbordOb[i][j].isDoel() )
                        {
                            aantaldoelen++;
                        }
            }
        }
        return aantaldoelen;
    }
    
   
    
    
    
    
    
}
