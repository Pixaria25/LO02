package divinae.api.cartes.types;


import divinae.api.joueur.Joueur;

public abstract class Divinite extends Carte {

	private Dogme[] dogme;
	private String description;
	private Joueur joueurLie;
	private boolean capaciteActivee;
	
	public Divinite (String nom, Origine origine, String capacite,Dogme[] dogme, String description) {
		// TODO Auto-generated constructor stub
		super(nom, origine, capacite);
		this.dogme = dogme;
		this.description = description;
		this.capaciteActivee = false;
		
	}
	
	public Dogme[] getDogme() {
		return dogme;
	}

	public Joueur getJoueurLie() {
		return joueurLie;
	}

	public boolean capaciteActivee() {
		return capaciteActivee;
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		capaciteActivee = true;
		
	}
	
	@Override
	public String toString() {
		return "Divinite " + getNom() + "\n " + description + "\n " + getOrigine() + "\n " + dogme + "\n " + getCapacite();
	}
}
