package fr.utt.divinae.api.cartes.types;

import fr.utt.divinae.api.joueur.Joueur;

/**
 * La classe Carte represente les cartes du jeu.
 * @author Thomas, Abraham
 *
 */
public abstract class Carte {

	private Origine origine;
	private String capacite; //description de la capacite
	private String nom;
	private Joueur joueurLie; //reference au joueur possedant la carte
	private boolean protectionCiblage = false;	//indique si la carte peut etre ciblee par une capacite
	private boolean autorisationSacrifice = true;	//indique si la carte peut etre sacrifiee
	private int id;	//numero utilise pour acceder a l'image correspondante a une carte
	private String categorie;
		
	public Carte(String nom, String categorie, Origine origine, String capacite, int id) {
		this.nom = nom;
		this.categorie = categorie;
		this.origine = origine;
		this.capacite = capacite;
		this.joueurLie = null;
		this.id = id;
	}
	
	/**
	 * Active un effet dependant de la carte jouee.
	 */
	public abstract void activerCapacite ();
	
	/**
	 * Retourne une chaine representant le contenu d'une liste de dogmes.
	 * @param dogmes un tableau de dogmes
	 * @return une chaine contenant les dogmes du tableau
	 */
	public static String dogmeToString(Dogme[] dogmes) {
		String retour = "";
		for(int i = 0; i < dogmes.length; i++) {
			retour += dogmes[i]+", ";
		}
		return retour;
	}

	public String toString() {
		return nom+"\n Origine: "+origine+"\n Capacite: "+capacite;
	}
	
	//Getters et setters
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
	
	public boolean isAutorisationSacrifice () {
		return autorisationSacrifice;
	}
	
	public void setAutorisationSacrifice(boolean b) {
		autorisationSacrifice = b;

	}
	
}
