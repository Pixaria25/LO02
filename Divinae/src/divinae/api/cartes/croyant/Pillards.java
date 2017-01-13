package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public  class Pillards extends Croyant {
  
  public Pillards () {
	 	super("Pillards", Origine.Nuit, "R�cup�rez les points d'Action d'une Divinit� n'ayant pas encore jou� durant "
	 			+ "ce tour. Les points d'Action dardents leur Origine. La Divinit� perd ses points.", 
	 			new Dogme [] {Dogme.Symboles, Dogme.Nature, Dogme.Mystique}, 4, 25);
	}
  
  public void activerCapacite() {
		Capacite.recupererPointAction(getJoueurLie(), this.getJoueurLie().getPartie());;
	}
}
