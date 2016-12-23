package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public  class Alienies extends Croyant {

	private int option;

	public Alienies(String nom, String capacite, Dogme[] dogme, int option) {
		super(nom, Origine.Neant, capacite, dogme, 2);
		this.option = option;
		// TODO Auto-generated constructor stub
	}
	
	public void activerCapacite() {
		switch (option) {
		case 1 : 
			Capacite.empecherSacrifice(Dogme.Nature, Dogme.Mystique, "Croyant" , this.getJoueurLie().getPartie());
			break;
		case 2 : 
			Capacite.empecherSacrifice(Dogme.Chaos, Dogme.Mystique, "GuideSpirituel" , this.getJoueurLie().getPartie());
			break;
		case 3 : 
			Capacite.prendreCartes(this, 2, this.getJoueurLie().getPartie());
			break;
		default : System.out.println("Problème ! Cette option de capacité n'est pas prise en compte dans le code");
		}
	
	}
}
