package cartes.croyant;

import cartes.Capacite;
import cartes.Dogme;
import cartes.Origine;

public  class Alchimistes extends Croyant {
		
	private int option;

	public Alchimistes(String nom, Origine origine, String capacite, Dogme [] dogme, int nombreCroyant, int option) {
		super(nom, Origine.Nuit, capacite, dogme, 2);
		this.option = option;
		// TODO Auto-generated constructor stub
	}
	
	public void activerCapacite() {
		switch (option) {
		case 1 : 
			Capacite.empecherSacrifice(Dogme.Humain, Dogme.Mystique, "Croyant" , this.getJoueurLie().getPartie());
			break;
		case 2 : 
			Capacite.empecherSacrifice(Dogme.Humain, Dogme.Symboles, "GuideSpirituel" , this.getJoueurLie().getPartie());
			break;
		case 3 : 
			Capacite.prendreCartes(this, 2, this.getJoueurLie().getPartie());
			break;
		default : System.out.println("Problème ! Cette option de capacité n'est pas prise en compte dans le code");
		
		}
	}

}
