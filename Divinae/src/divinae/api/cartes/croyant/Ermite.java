package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public abstract class Ermite extends Croyant {

  public Ermite (String nom, Dogme[] dogme) {
		super(nom, Origine.Jour, "Impose le sacrifice d'un Croyant d'un autre joueur, qui choisit"
				+ " la carte. La capacite spéciale du sacrifice est jouée.", dogme, 1);
		
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
	  	Capacite.imposerSacrifice("Croyant", this.getJoueurLie().getPartie());
  }
}
  
