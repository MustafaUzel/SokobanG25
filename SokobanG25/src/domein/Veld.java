/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Saleeem
 */
public class Veld extends SpelbordObject
{
    private boolean doel;
    private boolean isErkistGeplaatst;
    public Veld(int x,int y,boolean doel)
    {
        super(x,y,doel,false);
        setKistGeplaatst(false);
        setDoel(doel);
    }
    
    public Veld(int x,int y,boolean doel,boolean isBezet)
    {
        
        super(x,y,doel,isBezet);
        setDoel(doel);
        setKistGeplaatst(false);
        
    }
    
    public void setDoel(boolean d)
    {
        this.doel = d;
    }
    
    @Override
    public boolean isDoel()
    {
        return this.doel;
    }


    @Override
    public boolean isKistGeplaatst() {
        return isErkistGeplaatst;
    }



    @Override
    public void setKistGeplaatst(boolean kistGeplaatst) 
    {
        
        this.isErkistGeplaatst = kistGeplaatst;
    }
    
 
    
    
    @Override
    public int getX()
    {
        return super.getX();
    }
    
    @Override
    public int getY()
    {
        return super.getY();
    }
    
    @Override
    public String toString()
    {

        return this.getClass().getSimpleName().toUpperCase();
         
        
    }
    @Override
    public boolean magIkDaar()
    {
        
            return true;
    }
}
