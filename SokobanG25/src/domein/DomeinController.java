package domein;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class DomeinController {

    private final SpelerRepository spelerRepository;
    private final SpelRepository spelRepository;
    private Speler speler;
    private Spel huidigSpel;
    private int level;
    private Locale locale;
    private ResourceBundle resourceBundle;
    private final Map languages;
    private final Map countries;
    boolean correct = false;

    public DomeinController() {
        languages = new HashMap();
        languages.put(1, "en");
        languages.put(2, "nl");
        languages.put(3, "fr");
        countries = new HashMap();
        countries.put(1, "UK");
        countries.put(2, "NL");
        countries.put(3, "FR");
        spelerRepository = new SpelerRepository();
        spelRepository = new SpelRepository();
    }

    public ResourceBundle getLabels() {
        return resourceBundle;
    }

    public void setLocale(int taal) {
        locale = new Locale((String) languages.get(taal), (String) countries.get(taal));
        resourceBundle = ResourceBundle.getBundle("resourceBundle.Sokoban", locale);
        setTaal(resourceBundle);
    }

    public void meldAan(String gebruikersnaam, String wachtwoord) {
        Speler gevondenSpeler = spelerRepository.geefSpeler(gebruikersnaam, wachtwoord);
        if (gevondenSpeler != null) {
            setSpeler(gevondenSpeler);
        } else {
            throw new IllegalArgumentException(getLabels().getString("probleemWachtwoordGebruikersnaam"));
        }
    }

    public void stelSpelEnSpelbordInAdhvInt(int spel, int spelbord) {
        setSpel(spel);
        List<Spelbord> spelbordLijst = huidigSpel.geefSpelborden();
        huidigSpel.setSpelbord(spelbordLijst.get(spelbord));

    }

    public boolean gebruikersnaamBezet(String naam) {
        return spelerRepository.gebruikersnaamBezet(naam);
    }
    /*  
     geefSpeler: eerste commando kijkt of de huidige speler is aangemeld. Er wordt een array aangemaakt met 3 lijnen.
     Voornaam , naam en adminrechten
     Als deze zijn aangevuld wordt de info teruggegeven.
     */

    public void setSpel(int id) {
        List<String> spellenlijst = geefSpelMenuList();
        String[] list = new String[spellenlijst.size()];
        for (int i = 0; i < spellenlijst.size(); i++) {
            list[i] = spellenlijst.get(i);
        }

        Spel spel = geefSpel(list[id]); // maak spel adhv meegegeven naam
        DomeinController.this.setSpel(spel); // stel huidig spel in
        spelbordenToevoegen();
    }

    public String geefObjectStringAdhvPositie(int x, int y) {
        return huidigSpel.geefObjectStringAdhvPositie(x, y);
    }

    public String toonSpelbordMenu(String naam) {
        Spel spel = geefSpel(naam); // maak spel adhv meegegeven naam
        DomeinController.this.setSpel(spel); // stel huidig spel in
        spelbordenToevoegen();  // vul het spel met de spelborden van de databank
        String output = "";
        output += huidigSpel.toonAlleSpelborden();
        return output;

    }

    public String[] geefSpeler() {
        if (speler == null) {
            return null;
        }
        Boolean isAdmin;
        String[] spelerS = new String[3];
        spelerS[0] = speler.getVoornaam();
        spelerS[1] = speler.getNaam();
        isAdmin = speler.isAdminrechten();
        spelerS[2] = isAdmin.toString();

        return spelerS;
    }

    public int[][] geefSpelbordInt() {
        return huidigSpel.geefSpelbordInt();
    }

    public List<String> geefVerplaatsLijstjeGui() {
        return huidigSpel.geefVerplaatsLijstj(resourceBundle);
    }
    /*
     Geeft string terug die methode heeft gevraagt aan speler
     */

    public boolean isGNaamInGebruik(String naam) {
        return spelerRepository.naamInGebruik(naam);
    }

    public void setTaal(ResourceBundle rb) {
        this.resourceBundle = rb;
    }

    public ResourceBundle getTaal() {
        return resourceBundle;
    }

    public void spelconfiguratie(String naam) {
        Spel s = new Spel(naam);
        DomeinController.this.setSpel(s);

    }

    public void spelconfiguratieSpelborden() {
        Spelbord sb = new Spelbord();
        voegSpelbordToeAanSpel(sb);
        huidigSpel.setSb(sb);
    }

    public Spelbord stelHuidigSpelbordInEnGeefSpelbordTerug(int[][] appelflap, int x, int y, int id) {
        return huidigSpel.stelHuidigSpelbordInEnGeefSpelbordTerug(appelflap, x, y, id);
    }

    public Spelbord slaWijzigingenInSpelbordOp(int[][] appelflap, int x, int y) {
        int id = huidigSpel.geefHuidigSpelbordID();
        return huidigSpel.stelHuidigSpelbordInEnGeefSpelbordTerug(appelflap, x, y, id);
    }

    public void stelHuidigSpelbordInAdhvEnkelArrayEnID(int[][] appelflap, int nr) {
        huidigSpel.fixDieHandelEffe(appelflap, nr);
    }

    public void voegSpelToe() {
        spelRepository.voegSpelToe(huidigSpel);
    }

    public String geefGebruikersnaam() {
        return speler.getGebruikersnaam(); 
    }

    /*
     methode die zorgt voor de registratie van een speler
     er wordt gecontroleerd of de meegegeven wachtwoord niet gelijk is aan de wachtwoordbevestiging
     dan wordt exception gethrowed
       
     De nieuwe speler wordt aangemaakt met de meegegeven gegevens
     de huidige speler wordt toegekend aan DomeinController (set)
     we gebruiken de methode voegtoe van SR en geven de huidige speler mee als argument.
       
     */
    public void registreer(String naam, String voornaam, String gebruikersnaam, String wachtwoord) { // maak speler aan en valideer gegevens in constructor van Speler
        speler = new Speler(naam, voornaam, gebruikersnaam, wachtwoord, false);

        spelerRepository.maakSpeler(speler);

    }

    public boolean valideerRegistratie(String gebruikersnaam, String wachtwoord) {
        if (gebruikersnaam.length() < 8) {
            System.out.printf(getLabels().getString("controleGebruikersnaam"));
            return false;
        } else if (!wachtwoord.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
            System.out.printf(getLabels().getString("controleWachtwoord"));
            return false;
        } else {
            return true;
        }
    }

    public boolean isAdmin() {
        return speler.isAdminrechten();
    }

    private void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public void vulHetSpelbord() {
        List<Spelbord> sb = spelRepository.geefSpelbordenAdhvNaam(huidigSpel.getNaam());
        int spelbordnummer = sb.get(level - 1).getID();
        Spelbord spelb = spelRepository.geefSpelbord(spelbordnummer);
        huidigSpel.setSpelbord(spelb);

      
    }
    public void spelbordenToevoegen() {
        List<Spelbord> sb = spelRepository.geefSpelbordenAdhvNaam(huidigSpel.getNaam());
        int spelbordnummer;

        for (int i = 0; i < sb.size(); i++) {
            spelbordnummer = sb.get(i).getID();
            huidigSpel.voegSpelbordToeAanSpel(spelRepository.geefSpelbord(spelbordnummer));
        }
    }

    public int getMaxLevel() {
        List<Spelbord> sb = spelRepository.geefSpelbordenAdhvNaam(huidigSpel.getNaam());

        return sb.size();
    }

    public String spelbordObject() {
       return huidigSpel.spelbordObject();

    }

    public int[] geefStartPositie() {
        return huidigSpel.geefStartPositie();
    }

    public void setSpel(Spel spel) {
        this.huidigSpel = spel;

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public boolean isSpelbordVoltooid() {
        return huidigSpel.isSpelbordVoltooid();
    }

    public void voegSpelbordToe(Spelbord sb, int id) {
        spelRepository.voegSpelbordToe(sb, id, huidigSpel.getNaam());
    }

    public Spel geefSpel(String naam) {
        return spelRepository.geefSpel(naam);
    }

    public boolean toegang(int eindX, int eindY) {
        return huidigSpel.magIkDaarNaartoe(eindX, eindY);

    }

    public void verplaats(int startX, int startY, int eindplaatsX, int eindplaatsY) {
        huidigSpel.verplaats(startX, startY, eindplaatsX, eindplaatsY);
    }

    public String toonVoltooideSpelborden() {
        String output = resourceBundle.getString("my.spelbordvoltooid")
                + "------------------";
        output += huidigSpel.geefVoltooideSpelborden();

        return output;

    }

    public List<String> geefAlleVoltooideSpelborden() {
        List<String> lijst;
        lijst = huidigSpel.geefAlleVoltooideSpelborden();
        return lijst;

    }

    public List<String> geefAlleSpelborden() {
        List<String> lijst;
        lijst = huidigSpel.geefAlleSpelbordenGUI();
        return lijst;

    }

    public void setHuidigSpelbordAlsVoltooid() {
        huidigSpel.setHuidigSpelbordAlsVoltooid(this.level);
    }

    public String toonAlleSpelbordenInDitSpel() {
        String output = "";
        output += huidigSpel.toonAlleSpelborden();

        return output;
    }

    private void voegSpelbordToeAanSpel(Spelbord sb) {
        huidigSpel.voegSpelbordToeAanSpel(sb);
    }

    public boolean isNaamInGebruik(String spelNaam) {
        return spelRepository.isNaamInGebruik(spelNaam);
    }

    public boolean MagIkDaarNaarToeInGemaakteSpelbord(int x, int y) {
        return huidigSpel.MagIkDaarNaarToeInGemaakteSpelbord(x, y);
    }

    public boolean isAfgewerkt(int[][] spelbord) {
        return huidigSpel.isAfgewerkt(spelbord);
    }

    public void voegSpelbordToeAanSpel(int[][] spelbord, int x, int y, int spelbordnummer) {
        Spelbord sb = huidigSpel.stelHuidigSpelbordInEnGeefSpelbordTerug(spelbord, x, y, spelbordnummer);
        huidigSpel.voegSpelbordToeAanSpel(sb);
    }

    public int geefHuidigAantalMaxSpelborden() {
        return spelRepository.geefHuidigAantalSpelborden();
    }

    public boolean MagIkDaarNaarToeInTemporarySpelbordINT(int[][] spelbord, int x, int y) {
        return huidigSpel.MagIkDaarNaarToeInTemporarySpelbordINT(spelbord, x, y);

    }

    public List<String> geefSpelMenuList() {
        List<String> lijst = new ArrayList<>();
        List<Spel> spellenLijst = spelRepository.geefSpellen();
        for (int i = 0; i < spellenLijst.size(); i++) {
            lijst.add(spellenLijst.get(i).getNaam());
        }
        return lijst;

    }

    public boolean bestaatSpel(String gekozen) {
        boolean gekozenSpel = false;
        List<Spel> spellenLijst = spelRepository.geefSpellen();
        for (int i = 0; i < spellenLijst.size(); i++) {
            if (spellenLijst.get(i).getNaam().equals(gekozen)) {
                gekozenSpel = true;
                break;
            }
        }
        return gekozenSpel;
    }

    public int[][] getPosities() {
        return huidigSpel.getPosities();
    }

    public void updateSpelbord(int[][] array, int startx, int starty) {

        int spelbordid = huidigSpel.geefHuidigSpelbordID();
        Spelbord s = stelHuidigSpelbordInEnGeefSpelbordTerug(array, startx, starty, spelbordid);
        spelRepository.updateSpelbord(s);

    }

}
