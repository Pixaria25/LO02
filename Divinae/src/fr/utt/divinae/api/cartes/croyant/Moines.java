package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Moines extends Croyant {
  
  public Moines (Dogme [] dogme, int id) {
	 	super("Moines", Origine.Jour, "Donne un point d'action d'Origine jour", dogme, 2, id);
	}


	public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Jour, this.getJoueurLie());
	}
	
	
}
