package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

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
		default : System.out.println("Probl�me ! Cette option de capacit� n'est pas prise en compte dans le code");
		}
	
	}
}