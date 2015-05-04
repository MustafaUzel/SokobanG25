package domein;

import exceptions.GebruikersnaamInGebruikException;
import java.util.List;
import persistentie.SpelerMapper;

public class SpelerRepository {

    private final SpelerMapper mapper;
    //private List<Speler> spelers; //de lijst wordt eigenlijk niet gebruikt 

    public SpelerRepository() {
        mapper = new SpelerMapper();
        //spelers = new ArrayList<>();
    }
        public void maakSpeler(Speler s) {
        //validatie als speler al bestaat
        if (mapper.geefSpeler(s.getGebruikersnaam()) == null) {
            mapper.voegSpelerToe(s);
        } else {
            throw new IllegalArgumentException("spelerAanwezig");
        }
    }
    public void voegToe(Speler speler) {
       if (bestaatSpeler(speler.getGebruikersnaam()))
            throw new GebruikersnaamInGebruikException();
       
       mapper.voegSpelerToe(speler);
    }

    public boolean bestaatSpeler(String gebruikersnaam){
        return mapper.geefSpeler(gebruikersnaam)!=null;
    }
    
    public boolean testLogin(String naam,String ww)
    {
        Speler s = geefSpeler(naam,ww);
        if(s==null)
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    public boolean gebruikersnaamBezet(String naam)
    {
        return bestaatSpeler(naam);
    }
    
    /*
    methode om speler van spelermapper te halen. 
    */
        public Speler geefSpeler(String gebruikersnaam, String wachtwoord){
        Speler s = mapper.geefSpeler(gebruikersnaam);
        if (s.getWachtwoord().equals(wachtwoord))
            return s;
        else {
            throw new IllegalArgumentException("probleemWachtwoordGebruikersnaam");
        }
        
        
    }
    
    public List<Speler> geefSpelers()
    {
        return mapper.geefSpelers();
    }
    
    public boolean naamInGebruik(String naam)
    {
     return bestaatSpeler(naam);
 
    }
    
    
}
