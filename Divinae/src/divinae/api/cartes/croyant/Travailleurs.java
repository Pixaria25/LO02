package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Travailleurs extends Croyant {
	private int option; 
	

	public Travailleurs(Dogme [] dogme, int option, int id) {
		super("Travailleurs", Origine.Jour, "Empeche une Divinite possedant le Dogme Nature ou "
				+ "le Dogme Mystique de sacrifier une de ses cartes de Croyants durant ce tour.", dogme, 2, id);
		this.option = option;
		// TODO Auto-generated constructor stub
	}
	
	public void activerCapacite() {
		switch (option) {
		case 1 : 
			Capacite.empecherSacrifice(Dogme.Nature, Dogme.Mystique, "Croyant" , this, this.getJoueurLie().getPartie());
			break;
		case 2 : 
			Capacite.empecherSacrifice(Dogme.Chaos, Dogme.Mystique, "GuideSpirituel" , this, this.getJoueurLie().getPartie());
			break;
		case 3 : 
			Capacite.prendreCartes(this, 2, this.getJoueurLie().getPartie());
			break;
		default : throw new RuntimeException("Probl�me ! Cette option de capacit� n'est pas prise en compte dans le code");
		}
	}
	
	
}
