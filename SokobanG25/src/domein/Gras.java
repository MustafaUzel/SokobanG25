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
public class Gras extends SpelbordObject
{
    public Gras(int x,int y)
    {
        super(x,y);
    }
    public Gras(int x,int y,boolean isBezet)
    {
        super(x,y,isBezet);
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
        return false;
    }
}
