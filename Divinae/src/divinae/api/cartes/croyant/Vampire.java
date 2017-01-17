package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Vampire extends Croyant {

 
  public Vampire (Dogme[] dogme, int id) {
		super("Vampire", Origine.Nuit, "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le "
				+ "sacrifi�. La capacit� sp�ciale du sacrifice est jou�e.", dogme, 1, id);

	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", getJoueurLie(), getJoueurLie().getPartie());
  } 
}
