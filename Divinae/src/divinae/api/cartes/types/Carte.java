package divinae.api.cartes.types;

import divinae.api.joueur.Joueur;

public abstract class Carte {

	private Origine origine;
	private String capacite;
	private String nom;
	private Joueur joueurLie;
	private boolean protectionCiblage = false;
	private int id;
		
	public Carte(String nom, Origine origine, String capacite, int id) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.origine = origine;
		this.capacite = capacite;
		this.joueurLie = null;
		this.id = id;
	}
	
	public Origine getOrigine() {
		return origine;
	}
	
	public void setOrigine(Origine origine) {
		this.origine = origine;
	}
	
	public String getCapacite() {
		return capacite;
	}
	
	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}
	
	public String getNom() {
		return nom;
	}
	
		
	public Joueur getJoueurLie() {
		return joueurLie;
	}

	
	public void setJoueurLie(Joueur joueurLie) {
		this.joueurLie = joueurLie;
	}

	public boolean isProtectionCiblage() {
		return protectionCiblage;
	}

	public void setProtectionCiblage(boolean protectionCiblagle) {
		this.protectionCiblage = protectionCiblagle;
	}

	public int getId() {
		return id;
	}

	public static String dogmeToString(Dogme[] dogmes) {
		String retour = "";
		for(int i = 0; i < dogmes.length; i++) {
			retour += dogmes[i]+", ";
		}
		return retour;
	}
	
	public abstract void activerCapacite ();
	
	public String toString() {
		return nom+"\n Origine: "+origine+"\n Capacite: "+capacite;
	}
}
