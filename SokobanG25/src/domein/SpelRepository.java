/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import persistentie.SpelMapper;

/**
 *
 * @author Muhammet
 */
public class SpelRepository 
{

    private final SpelMapper mapper;
    //private List<Speler> spelers; //de lijst wordt eigenlijk niet gebruikt 

    public SpelRepository() 
    {
        mapper = new SpelMapper();
        //spelers = new ArrayList<>();
    }
    
    public void voegSpelToe(Spel spel) 
    {
       mapper.voegSpelToe(spel);
    }
    /*
    methode om speler van spelermapper te halen. 
    */
    public void voegSpelbordToe(Spelbord sb,int Spelbordnr,String nm)
    {
        mapper.voegSpelbordEnObjectenToe(sb, Spelbordnr,nm);
    }
    public void updateObject(int x,int y,int objectnr,int spelbordID)
    {
        mapper.updateObject(x,y,objectnr,spelbordID);
        
    }
    public Spelbord geefSpelbord(int spelbordNr)
    {
        return mapper.geefSpelbord(spelbordNr);
    }
    public List<Spelbord> geefSpelborden()
    {
        return mapper.geefSpelborden();
    }
    public List<Spel> geefSpellen(){
        List<Spel> list = mapper.geefSpellen();
        return list;
    }

   public boolean isNaamInGebruik(String spelNaam) 
    {
        boolean gebruikt = false;
        List<Spel> lijst;
        lijst = geefSpellen();
        for (int i = 0; i < lijst.size(); i++) 
        {
            if(lijst.get(i).getNaam().equals(spelNaam))
            {
                gebruikt = true;
                break;
            }
			
	}
        return gebruikt;
        
    }

    public int geefHuidigAantalSpelborden() 
    {
        List<Spelbord> sb = geefSpelborden();
        return sb.size();
    }

    public List<Spelbord> geefSpelbordenAdhvNaam(String naam) 
    {
        
        return mapper.geefAlleSpelbordenVanSpel(naam);
    
    }

    
    Spel geefSpel(String naam)
    {
        List<Spel> spellenLijst;
        
        spellenLijst = geefSpellen();
        Spel tempSpel = new Spel("Temp");
        
      
        for (int i = 0; i < spellenLijst.size(); i++) 
        {
            if(spellenLijst.get(i).getNaam().equals(naam))
            {
                tempSpel = spellenLijst.get(i);
                break;
            }
			
	}
        
        return tempSpel;
    }

    public void updateSpelbord(Spelbord s) 
    {
        mapper.updateSpelbord(s);
    }
    
    
}
