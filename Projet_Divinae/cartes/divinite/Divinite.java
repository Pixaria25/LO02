package cartes.divinite;

import cartes.Carte;
import cartes.Dogme;
import cartes.Origine;
import joueur.Joueur;

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
		System.out.println("carte" + nom + "cr�e");
		
	}
	
	public Dogme[] getDogme() {
		return dogme;
	}

	public Joueur getJoueurLie() {
		return joueurLie;
	}

	@Override
	public void activerCapacite() {
		// TODO Auto-generated method stub
		if(!capaciteActivee) {
			System.out.println(getJoueurLie()+" active la capacite de "+getNom());
			capaciteActivee = true;
		} else {
			System.out.println("Vous ne pouvez pas activer la capacite de votre divinite.");
		}
	}
	
	
}
