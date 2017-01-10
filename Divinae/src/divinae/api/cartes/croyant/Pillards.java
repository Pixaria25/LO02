package divinae.api.cartes.croyant;

import divinae.api.cartes.types.Capacite;
import divinae.api.cartes.types.Croyant;
import divinae.api.cartes.types.Dogme;
import divinae.api.cartes.types.Origine;

public  class Pillards extends Croyant {
  
  public Pillards () {
	 	super("Pillards", Origine.Nuit, "Récupérez les points d'Action d'une Divinité n'ayant pas encore joué durant "
	 			+ "ce tour. Les points d'Action dardents leur Origine. La Divinité perd ses points.", 
	 			new Dogme [] {Dogme.Symboles, Dogme.Nature, Dogme.Mystique}, 4, 25);
		// TODO Auto-generated constructor stub
	}
  
  public void activerCapacite() {
		Capacite.recupererPointAction(this, this.getJoueurLie().getPartie());;
	}
}
