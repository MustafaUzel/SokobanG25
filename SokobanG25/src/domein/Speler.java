package domein;

public class Speler {

	private String naam;
	private String voornaam;
        private String gebruikersnaam;
	private String wachtwoord;
	private boolean isAdmin;
	
        public Speler(String naam, String voornaam, String gebruikersnaam, String wachtwoord, boolean isAdmin)
        {
		setNaam(naam);
                setVoornaam(voornaam);
                setGebruikersnaam(gebruikersnaam);
                setAdminrechten(isAdmin);
                setWachtwoord(wachtwoord);
                
	}
	public String getNaam() {
		return this.naam;
	}

	private void setNaam(String naam) 
        {

		this.naam = naam;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	private void setVoornaam(String voornaam) {

		this.voornaam = voornaam;
	}

        public String getGebruikersnaam() {
            return gebruikersnaam;
        }

    private void setGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam == null || gebruikersnaam.length() == 0) {
            throw new IllegalArgumentException("verplichtGebruikersnaam");
        }
        this.gebruikersnaam = gebruikersnaam;
    }

        

	public String getWachtwoord() {
		return this.wachtwoord;
	}

    private void setWachtwoord(String wachtwoord) {
        if (wachtwoord == null || wachtwoord.length() == 0) {
            throw new IllegalArgumentException("verplichtWachtwoord");
        }
        this.wachtwoord = wachtwoord;
    }

	public boolean isAdminrechten() {
		return this.isAdmin;
	}

	private void setAdminrechten(boolean adminrechten) {
		this.isAdmin = adminrechten;
	}


}       