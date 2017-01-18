package fr.utt.divinae.api.cartes.croyant;

import fr.utt.divinae.api.cartes.types.Capacite;
import fr.utt.divinae.api.cartes.types.Croyant;
import fr.utt.divinae.api.cartes.types.Dogme;
import fr.utt.divinae.api.cartes.types.Origine;

public class Ermite extends Croyant {

  public Ermite (Dogme[] dogme, int id) {
		super("Ermite", Origine.Jour, "Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit"
				+ " la carte. La capacite sp�ciale du sacrifice est jou�e.", dogme, 1, id);
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", getJoueurLie(), getJoueurLie().getPartie());
  }
}
  
