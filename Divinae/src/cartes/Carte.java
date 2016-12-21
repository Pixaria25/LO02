package cartes;

import joueur.Joueur;

public abstract class Carte {

	private Origine origine;
	private String capacite;
	private String nom;
	private Joueur joueurLie;
	
	
	public Carte(String nom, Origine origine, String capacite) {
		// TODO Auto-generated constructor stub
		this.nom = nom;
		this.origine = origine;
		this.capacite = capacite;
		System.out.println("carte" + nom + "créée");
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

	public abstract void activerCapacite ();

}
