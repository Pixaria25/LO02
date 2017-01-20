package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Esprits extends Croyant {
  
  public Esprits (Dogme[] dogme, int id) {
	 	super("Esprit", Origine.Neant, "Donne un point d'Action d'Origine N�ant.", dogme, 2, id);
	}
  
  public void activerCapacite() {
		Capacite.donnerPointAction(1, Origine.Neant, this.getJoueurLie());
	}
}
