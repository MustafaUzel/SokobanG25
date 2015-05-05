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
public class SpelbordObject 
{
    private int X;
    private int Y;
    private boolean Bezet;
    private boolean doel;
    boolean kistGeplaatst;
    
    
    public SpelbordObject(int x,int y)
    {
        setX(x);
        setY(y);
        setBezet(false);
    }
    
    public SpelbordObject(int x,int y,boolean isBezet)
    {
        setX(x);
        setY(y);
        setBezet(isBezet);
    }
     public SpelbordObject(int x,int y,boolean doel,boolean isBezet)
    {
        setX(x);
        setY(y);
        setBezet(isBezet);
        setDoel(doel);
    }    
     
     public boolean isKistGeplaatst() 
    {
        return kistGeplaatst;
    }



    public void setKistGeplaatst(boolean kistGeplaatst) 
    {
        this.kistGeplaatst = kistGeplaatst;
    }
    
    public boolean isDoel()
    {
        return this.doel;
    }
    
    private void setDoel(boolean d)
    {
        this.doel = d;
    }
    public boolean isBezet() {
        return Bezet;
    }

 

    public void setBezet(boolean isBezet) {
        this.Bezet = isBezet;
    }

  

    
    private void setX(int X) {
        this.X = X;
    }

    private void setY(int Y) {
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
    
    
    public boolean magIkDaar()
    {
        return false;
    }
    
}
