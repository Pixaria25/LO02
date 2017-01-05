package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public class Vampire extends Croyant {

 
  public Vampire (String nom, Dogme[] dogme) {
		super(nom, Origine.Nuit, "Impose le sacrifice d'un Croyant d'un autre joueur. Celui-ci choisit le "
				+ "sacrifié. La capacité spéciale du sacrifice est jouée.", dogme, 1);

		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
  } 
}
