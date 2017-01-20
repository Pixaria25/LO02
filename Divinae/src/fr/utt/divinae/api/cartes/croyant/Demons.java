package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public  class Demons extends Croyant {
  
  public Demons (Dogme[] dogme, int id) {
	 	super("Demons", Origine.Nuit, "Donne un point d'Action d'Origine Nuit.", dogme, 2, id);
	}
  
  public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Nuit, this.getJoueurLie());
	}
}
