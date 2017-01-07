package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Travailleurs extends Croyant {
	private int option; 
	

	public Travailleurs(String nom, Dogme [] dogme, int option) {
		super(nom, Origine.Jour, "EmpÃªche une DivinitÃ© possÃ©dant le Dogme Nature ou "
				+ "le Dogme Mystique de sacrifier une de ses cartes de Croyants duant ce tour.", dogme, 2);
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
		default : throw new RuntimeException("Problème ! Cette option de capacité n'est pas prise en compte dans le code");
		}
	}
	
	
}
