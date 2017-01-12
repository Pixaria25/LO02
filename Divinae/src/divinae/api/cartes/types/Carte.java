package divinae.api.cartes.types;

import divinae.api.joueur.Joueur;

public abstract class Carte {

	private Origine origine;
	private String capacite;
	private String nom;
	private Joueur joueurLie;
	private boolean protectionCiblage = false;
	private int id;
	private String categorie;
		
	public Carte(String nom, String categorie, Origine origine, String capacite, int id) {
		this.nom = nom;
		this.categorie = categorie;
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

	public String getCategorieEtNom() {
		return categorie+":"+nom;
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
