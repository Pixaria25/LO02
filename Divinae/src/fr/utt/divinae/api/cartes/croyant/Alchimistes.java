package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public  class Alchimistes extends Croyant {
		
	private int option;

	public Alchimistes( Dogme [] dogme, int option, int id) {
		super("Alchimistes", Origine.Nuit, "", dogme, 2, id);
		this.option = option;
	}
	
	public void activerCapacite() {
		switch (option) {
		case 1 : 
			Capacite.empecherSacrifice(Dogme.Humain, Dogme.Mystique, "Croyant" , this, getJoueurLie().getPartie());
			break;
		case 2 : 
			Capacite.empecherSacrifice(Dogme.Humain, Dogme.Symboles, "GuideSpirituel" , this, getJoueurLie().getPartie());
			break;
		case 3 : 
			Capacite.prendreCartes(this, 2, getJoueurLie().getPartie());
			break;
		default : 
			throw new RuntimeException("Probl�me ! Cette option de capacit� n'est pas prise en compte dans le code");
		}
	}

}
